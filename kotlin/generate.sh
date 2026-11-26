#!/bin/sh
# Generate Kotlin (Retrofit 2 + Gson, matching the Android app's stack) for every service spec into
# kotlin/src/main/kotlin/de/swimresults/api/<svc>/. Requires Java 17+.
# Usage: kotlin/generate.sh <path-to-openapi-generator-cli.jar>
set -eu
JAR=$1
ROOT=$(cd "$(dirname "$0")/.." && pwd)
OUT="$ROOT/kotlin/src/main/kotlin"
TMP=$(mktemp -d)
trap 'rm -rf "$TMP"' EXIT

rm -rf "$OUT/de/swimresults/api"
mkdir -p "$OUT"
for svc in meeting athlete start user import; do
  pkg="de.swimresults.api.$svc"
  # `import` is a Kotlin soft keyword but valid in package names only when escaped; use `imports`
  [ "$svc" = import ] && pkg="de.swimresults.api.imports"
  java -jar "$JAR" generate -q \
    -g kotlin --library jvm-retrofit2 \
    -i "$ROOT/specs/$svc.yaml" -o "$TMP/$svc" \
    --package-name "$pkg" \
    --additional-properties=serializationLibrary=gson,dateLibrary=java8,enumPropertyNaming=UPPERCASE,omitGradleWrapper=true,useCoroutines=true,sourceFolder=src/main/kotlin \
    --global-property=apiTests=false,modelTests=false,apiDocs=false,modelDocs=false
  cp -R "$TMP/$svc/src/main/kotlin/." "$OUT/"
done
echo "generated Kotlin sources in $OUT"
