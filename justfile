# kdaisyui — task runner
# Run `just` or `just --list` to see all available recipes.
# Requires: just (see .tool-versions), JDK, Node.js

# List all recipes
default:
    @just --list

# ── Build ────────────────────────────────────────────────────────────────────

# Build all Gradle modules and publish to Maven Local
build:
    ./gradlew build :lib:publishToMavenLocal

generate:
    cd codegen && npm install && npm run generate

# Regenerate Heroicons Kotlin source from Heroicons SVG submodule
generate-heroicons:
    ./gradlew :lib:generateHeroicons

# Sync DaisyUI submodule to the tag matching daisyui.version in gradle.properties
sync-daisyui:
    ./gradlew :lib:checkoutDaisyuiTag

# Sync Heroicons submodule to the tag matching heroicons.version in gradle.properties
sync-heroicons:
    ./gradlew :lib:checkoutHeroiconsTag

# Clean all build artifacts
clean:
    ./gradlew clean
    rm -rf e2e-tests/build

# ── Testing ──────────────────────────────────────────────────────────────────

# Run library unit tests
test:
    ./gradlew :lib:test

# Run E2E tests (Playwright + in-process Ktor server via Gradle)
e2e:
    ./gradlew :e2e-tests:test

# Run all tests: unit + E2E
test-all: test e2e

# ── Development ──────────────────────────────────────────────────────────────

# Start the example app (http://localhost:8080)
dev:
    ./gradlew :example-app:run
