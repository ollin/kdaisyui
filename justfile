# kdaisyui — task runner
# Run `just` or `just --list` to see all available recipes.
# Requires: just and a JDK. Node and the git submodules are needed by `generate` only.

# List all recipes
default:
    @just --list

# ── Build ────────────────────────────────────────────────────────────────────

# Build all Gradle modules and publish to Maven Local
build:
    ./gradlew build :lib:publishToMavenLocal

# Regenerate the committed Kotlin sources in lib/generated and show what changed.
# The build never does this by itself — committed output plus CI's drift check
# is what keeps lib/generated honest. Needs Node and the git submodules.
generate:
    ./gradlew :lib:generateComponents :lib:generateComponentTests :lib:generateHeroicons :lib:generateHeroiconTests
    @echo
    @git status --short lib/generated || true
    @git --no-pager diff --stat -- lib/generated || true

# Sync DaisyUI submodule to the tag matching the daisyui version in gradle/libs.versions.toml
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
