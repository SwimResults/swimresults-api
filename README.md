# swimresults-api

API contracts for the SwimResults services: one OpenAPI 3.0.3 spec per service, the tooling that keeps
them in sync with the Go code, and client packages generated from them for Go, TypeScript, Swift and Kotlin.

| Spec | Service | Public base URL |
|---|---|---|
| [specs/meeting.yaml](specs/meeting.yaml) | [meeting-service](https://github.com/SwimResults/meeting-service) | `https://api.swimresults.de/meeting/v1` |
| [specs/athlete.yaml](specs/athlete.yaml) | [athlete-service](https://github.com/SwimResults/athlete-service) | `https://api.swimresults.de/athlete/v1` |
| [specs/start.yaml](specs/start.yaml) | [start-service](https://github.com/SwimResults/start-service) | `https://api.swimresults.de/start/v1` |
| [specs/user.yaml](specs/user.yaml) | [user-service](https://github.com/SwimResults/user-service) | `https://api.swimresults.de/user/v1` |
| [specs/import.yaml](specs/import.yaml) | [import-service](https://github.com/SwimResults/import-service) | `https://api.swimresults.de/import/v1` |

admin-service is still a template and has no spec yet.

## How the specs relate to the code

1. The **baselines** were generated from the service source by [`tools/specgen`](tools/specgen/main.go), which loads
   each service with full Go type information and reads:
   - routes registered via `router.<METHOD>` and `security.Route(...)`
   - permissions (`security.Permission*`, service-core defaults, excluded paths)
   - path and query params, JSON bodies, and response status codes with their Go types
2. From now on **the specs are the source of truth**: add summaries, descriptions, examples and fix types by hand.
3. **CI checks for drift** on every push and daily: every route in the service's `master` must appear in the spec,
   with the same `x-permission`, and vice versa. Request and response schemas are not compared yet.

When you add or change a route in a service: update the spec in the same change set. To check locally:

```sh
make check   # routes/permissions: code vs specs (needs ../server/<svc>-service checkouts)
make lint    # OpenAPI 3.0.3 validation (redocly)
```

## Conventions in the specs

- **Paths** are written without the gateway prefix; the `servers` entries carry `/<svc>/v1`.
- **`x-permission`** on every operation, as enforced by service-core:
  | value | meaning |
  |---|---|
  | `public` | no auth (some handlers still read the user from the token — then `security: bearerAuth`) |
  | `admin` | realm role `admin` |
  | `manager` | realm role `manager` or `admin`, any meeting |
  | `meeting` | `admin`, or `manager` with the meeting id in the token `events` claim |
  | `default` | not registered → meeting rule if a meeting id is present, else `admin` |
  | `none` | excluded from the auth middleware (`/actuator`, `/easywk/*`) |

  `x-permission-source: implicit|excluded` marks permissions that come from service-core defaults
  rather than an explicit `security.Route` registration.
- **Security schemes**: `bearerAuth` (Keycloak JWT) or `serviceKey` (`X-Swimresults-Service`, internal only).
- **Schemas** use Go JSON semantics:
  - fields without `omitempty` are `required`
  - nil-able Go types (pointers, slices, maps) are nullable
  - `ObjectId` is a 24-character hex string
  - `time.Time` is `date-time`
  - typed Go constants become `enum`s
- **Errors** are `{"message": "..."}` (`Error` schema).

## Known gaps in the baselines

- Handlers that respond with values built inside other packages get the Go type as declared. Values built from
  `gin.H` maps are generic objects.
- Some routes respond `501 Not Implemented` today (e.g. `PUT /location`, `DELETE /location/{id}`,
  `GET /meeting/between/...`); the specs say so on purpose.
- Implicit consumers (Homepage, Livestreaming, DownloadPage) depend on specific fields; see
  [SwimResults docs/architecture.md](https://github.com/SwimResults/SwimResults/blob/master/docs/architecture.md#known-contract-drift-to-be-fixed-via-shared-specs).

## Packages

One version for everything: a git tag `vX.Y.Z` releases all packages (see [Releasing](#releasing)).

| Language | Package | Generator | Consumers |
|---|---|---|---|
| Go | `github.com/swimresults/swimresults-api/go/<svc>api` | [oapi-codegen](https://github.com/oapi-codegen/oapi-codegen) v2.8.2 (models + typed client) | services calling other services |
| TypeScript | [`@swimresults/api`](https://www.npmjs.com/package/@swimresults/api) (`/meeting`, `/athlete`, …) | [openapi-typescript](https://openapi-ts.dev) 7.13.0 (types only) | WebApplication, ManagerApplication |
| Swift | SwiftPM package at this repo's root, products `SwimResults<Svc>API` + `SwimResultsAPISupport` | [swift-openapi-generator](https://github.com/apple/swift-openapi-generator) 1.14.4 | iosApplication, iosManagerApplication |
| Kotlin | `de.swimresults:swimresults-api` (Maven Central), packages `de.swimresults.api.<svc>` | [openapi-generator](https://openapi-generator.tech) 7.27.0, `kotlin` / Retrofit 2 + Gson | AndroidApplication |

`<svc>` is `meeting`, `athlete`, `start`, `user` or `import` (the Kotlin package is `imports`, because
`import` is a keyword). All packages are GPL-3.0, like the rest of SwimResults.

### Go

```go
import "github.com/swimresults/swimresults-api/go/meetingapi"

c, _ := meetingapi.NewClientWithResponses("http://meeting-service:8080",
    meetingapi.WithRequestEditorFn(func(ctx context.Context, r *http.Request) error {
        r.Header.Set("X-Swimresults-Service", os.Getenv("SR_SERVICE_KEY"))
        return nil
    }))
res, err := c.GetEventsByMeetIdWithResponse(ctx, "IESC19") // res.JSON200 is *[]meetingapi.Event
```

The Go types mirror the services' own models, so code moving from a service's `client`/`model` packages mostly
changes imports:
- **Field names** match the Go models (kept in the specs as `x-go-name`, e.g. `_id` → `Identifier`).
- **Optional fields** are plain values with `omitempty`, not pointers.
- **Types:** Go `int` stays `int`, and `time.Duration` stays `time.Duration` (`x-go-type`).
- **IDs:** `ObjectId` is a `string`. The services decode `""` like an unset ObjectID.
- **Optional query parameters** are pointers, so unset ones are not sent.

Requires Go 1.24. The runtime is pinned to `oapi-codegen/runtime` v1.8.0 because v1.9 needs Go 1.26.

### Swift

```swift
.package(url: "https://github.com/SwimResults/swimresults-api", from: "0.1.0"),
.package(url: "https://github.com/apple/swift-openapi-urlsession", from: "1.0.0"),
// target dependencies: "SwimResultsMeetingAPI", "SwimResultsAPISupport", "OpenAPIURLSession"

let client = Client(serverURL: try Servers.Server1.url(), configuration: .swimResults, transport: URLSessionTransport())
let events = try await client.getEventsByMeetId(path: .init(meetId: "IESC19")).ok.body.json
```

Always pass `configuration: .swimResults`. It decodes Go timestamps with and without fractional seconds; the
default decoder rejects `2023-09-19T03:01:09.904Z`. `Date.isGoZeroTime` / `nilIfGoZeroTime` detect
`0001-01-01T00:00:00Z` (meaning "not set"), and `Date.goZeroTime` sends it.

`SwimResultsAPISupport` also has:

- `SwimResultsServer.production` / `.development` and `.url(for: "meeting")`, for the service base URLs.
- `BearerTokenMiddleware { try await freshAccessToken() }`, for routes that need a login.
- `.swimResults(encodingIn: timeZone)`, which writes request dates with that time zone's offset instead
  of UTC. Use it where the service reads the time zone from the timestamp, e.g. `update_time_zone`.

### Kotlin / Android

```kotlin
implementation("de.swimresults:swimresults-api:0.1.0")

val api = de.swimresults.api.meeting.infrastructure.ApiClient(baseUrl = "https://api.swimresults.de/meeting/v1/")
    .createService(de.swimresults.api.meeting.apis.EventApi::class.java)
val events = api.getEventsByMeetId("IESC19").body() // suspend fun, retrofit2.Response<List<Event>>
```

### TypeScript

See [typescript/README.md](typescript/README.md).

## Development

```sh
make generate   # regenerate all packages from specs/ (needs Go, Node, Swift, Java 17)
make test       # build + test all packages
make check      # routes/permissions: code vs specs
make lint       # OpenAPI validation
```

Generated code is committed:
- **CI** regenerates it and fails if the committed code is stale, then builds and tests every package. The
  Swift job also builds for iOS.
- **Live check:** `go/contract_live_test.go` decodes real responses strictly into the generated types:
  `SR_CONTRACT_LIVE=https://api-dev.swimresults.de go test ./go/ -run Live -v`.

## Releasing

1. Merge spec changes to `master` together with the regenerated packages (`make generate`).
2. `git tag v0.2.0 && git push origin v0.2.0`.
3. [`release.yml`](.github/workflows/release.yml) then runs:
   - verifies the tag
   - publishes to npm and Maven Central (once enabled, see below)
   - creates the GitHub release

   The tag itself is the Go and Swift release.

To publish an **existing** tag again (e.g. after enabling a registry), run
`gh workflow run release.yml -f tag=v0.1.0`. Versions already on npm or Maven Central are skipped. A new version
always needs a new tag; the manual run doesn't create one.

Versioning is semver over the **API contract**. A breaking change in any spec (removed field or route, changed
type) bumps the minor version while in `0.x`, and the major version from `1.0` on. From v2 on, Go also needs a
`/v2` module path.

### Publishing setup (one time)

**npm** (`@swimresults/api`, trusted publishing with provenance; no long-lived token once set up):
1. Create the npm organization `swimresults` on npmjs.com.
2. Do the first publish, either:
   - locally: `cd typescript && npm run build && npm publish --access public`, or
   - by adding a granular automation token as repo secret `NPM_TOKEN` for the first release.
3. On npmjs.com, open `@swimresults/api` → Settings → Trusted publishing and add GitHub Actions:
   - repository `SwimResults/swimresults-api`
   - workflow `release.yml`
   - environment `release`
4. Delete the `NPM_TOKEN` secret if you created one, then set repo variable `NPM_PUBLISH=true`.

**Maven Central** (`de.swimresults:swimresults-api`):
1. Sign in at [central.sonatype.com](https://central.sonatype.com) and add namespace `de.swimresults`. Verify it
   with the DNS TXT record shown, on `swimresults.de`.
2. Generate a user token (Account → Generate User Token) and store it as repo secrets
   `MAVEN_CENTRAL_USERNAME` / `MAVEN_CENTRAL_PASSWORD`.
3. Set up a GPG signing key:
   - create the key
   - publish the public key: `gpg --keyserver keyserver.ubuntu.com --send-keys <id>`
   - store the armored private key and its passphrase as secrets `SIGNING_KEY` / `SIGNING_KEY_PASSWORD`
4. Set repo variable `MAVEN_PUBLISH=true`.

Put these secrets on the GitHub environment `release` rather than on the repo, and limit that environment to
`v*` tags. You can also require a manual approval there.

## Roadmap

- Adopt the packages one consumer at a time, starting with the Go service-to-service clients. They replace
  the per-service `client/` packages and their version skew.
- Schema drift check: compare Go struct fields with the spec schemas.
- Example payloads (`examples/`) used as contract-test fixtures.
