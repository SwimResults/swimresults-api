# Service checkouts are expected next to this repo, as in the SwimResults workspace:
#   <workspace>/server/<svc>-service  and  <workspace>/swimresults-api
SERVER_DIR ?= ../server
SERVICES   := meeting athlete start user import
REDOCLY    := npx -y @redocly/cli@1.34.5

.PHONY: check lint bundle baseline

## check: fail if routes/permissions in the service code differ from the specs
check:
	@cd tools && for s in $(SERVICES); do \
		go run ./specgen -service $$s -dir ../$(SERVER_DIR)/$$s-service -check ../specs/$$s.yaml || exit 1; \
	done

## lint: validate specs against OpenAPI 3.1 + redocly.yaml rules
lint:
	$(REDOCLY) lint specs/*.yaml

## bundle: write fully resolved single-file specs to dist/ (input for generators)
bundle:
	@mkdir -p dist && for s in $(SERVICES); do $(REDOCLY) bundle specs/$$s.yaml -o dist/$$s.yaml; done

## baseline SVC=<name>: regenerate one spec from code (overwrites hand edits!)
baseline:
	@test -n "$(SVC)" || (echo "usage: make baseline SVC=meeting" && exit 1)
	cd tools && go run ./specgen -service $(SVC) -dir ../$(SERVER_DIR)/$(SVC)-service -out ../specs/$(SVC).yaml -force
