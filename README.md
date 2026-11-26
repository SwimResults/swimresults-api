# swimresults-api

API contracts for the SwimResults services: one OpenAPI 3.1 spec per service, plus the tooling that keeps
them in sync with the Go code. Generated client packages for Go, TypeScript, Swift and Kotlin come next
(see [Roadmap](#roadmap)).

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
make lint    # OpenAPI validation (redocly)
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

## Roadmap

- **Phase 3 — generated packages** (versioned with this repo's git tags `vX.Y.Z`):
  - Go: `github.com/swimresults/swimresults-api/go/<svc>`. Replaces the per-service `client/` packages and the
    version skew between them.
  - TypeScript: an npm package for WebApplication and ManagerApplication.
  - Swift: an SPM package (`Package.swift` at the repo root) for the iOS app.
  - Kotlin: a Maven artifact for the Android app.
- Schema drift check: compare Go struct fields with the spec schemas.
- Example payloads (`examples/`) used as contract-test fixtures.
