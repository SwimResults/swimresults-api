SHELL := /bin/bash
# Service checkouts are expected next to this repo, as in the SwimResults workspace:
#   <workspace>/server/<svc>-service  and  <workspace>/swimresults-api
SERVER_DIR ?= ../server
SERVICES   := meeting athlete start user import
REDOCLY    := npx -y @redocly/cli@1.34.5

OPENAPI_GENERATOR_VERSION := 7.27.0
OPENAPI_GENERATOR_JAR     := .cache/openapi-generator-cli-$(OPENAPI_GENERATOR_VERSION).jar
OPENAPI_GENERATOR_URL     := https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/$(OPENAPI_GENERATOR_VERSION)/openapi-generator-cli-$(OPENAPI_GENERATOR_VERSION).jar
SWIFT_GENERATOR           := swift/tools/.build/release/swift-openapi-generator

.PHONY: check lint bundle baseline generate generate-go generate-ts generate-swift generate-kotlin test

## check: fail if routes/permissions in the service code differ from the specs
check:
	@cd tools && for s in $(SERVICES); do \
		go run ./specgen -service $$s -dir ../$(SERVER_DIR)/$$s-service -check ../specs/$$s.yaml || exit 1; \
	done

## lint: validate specs against OpenAPI 3.0.3 + redocly.yaml rules
lint:
	$(REDOCLY) lint specs/*.yaml

## bundle: write fully resolved single-file specs to dist/
bundle:
	@mkdir -p dist && for s in $(SERVICES); do $(REDOCLY) bundle specs/$$s.yaml -o dist/$$s.yaml; done

## baseline SVC=<name>: regenerate one spec from code (overwrites hand edits!)
baseline:
	@test -n "$(SVC)" || (echo "usage: make baseline SVC=meeting" && exit 1)
	cd tools && go run ./specgen -service $(SVC) -dir ../$(SERVER_DIR)/$(SVC)-service -out ../specs/$(SVC).yaml -force

## generate: regenerate all client packages from specs/ (commit the result)
generate: generate-go generate-ts generate-swift generate-kotlin

generate-go:
	@for s in $(SERVICES); do mkdir -p go/$${s}api; \
		(cd tools && go tool oapi-codegen -config ../go/oapi-codegen.yaml -package $${s}api -o ../go/$${s}api/$${s}api.gen.go ../specs/$$s.yaml) || exit 1; \
	done

generate-ts:
	cd typescript && npm ci --no-audit --no-fund && npm run generate

$(SWIFT_GENERATOR):
	cd swift/tools && swift build -c release --product swift-openapi-generator

generate-swift: $(SWIFT_GENERATOR)
	@for s in $(SERVICES); do t=SwimResults$$(echo $${s:0:1} | tr a-z A-Z)$${s:1}API; \
		rm -rf swift/Sources/$$t && mkdir -p swift/Sources/$$t && \
		$(SWIFT_GENERATOR) generate --config swift/openapi-generator-config.yaml --output-directory swift/Sources/$$t specs/$$s.yaml || exit 1; \
	done

$(OPENAPI_GENERATOR_JAR):
	@mkdir -p .cache && curl -sfL -o $@ $(OPENAPI_GENERATOR_URL) && \
		echo "$$(curl -sfL $(OPENAPI_GENERATOR_URL).sha1)  $@" | shasum -a 1 -c -

generate-kotlin: $(OPENAPI_GENERATOR_JAR)
	kotlin/generate.sh $(OPENAPI_GENERATOR_JAR)

## test: build and test every package
test:
	go vet ./go/... && go test ./go/...
	cd typescript && npm test
	swift test
	cd kotlin && ./gradlew build
