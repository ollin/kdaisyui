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

# Regenerate the committed sources in lib/generated and the reference pages in
# docs/reference, and show what changed. The build never does this by itself —
# committed output plus CI's drift check is what keeps both honest. Needs Node
# and the git submodules.
generate:
    ./gradlew :lib:generateComponents :lib:generateComponentTests :lib:generateHeroicons :lib:generateHeroiconTests :lib:generateReferenceDocs
    @echo
    @git status --short lib/generated docs/reference || true
    @git --no-pager diff --stat -- lib/generated docs/reference || true

# Re-dump the committed public API baseline in lib/api and show what changed.
# Only run this when an API change is intended: the diff is the change, and a
# breaking one needs a "How to migrate" entry in README.md before its release.
update-api:
    ./gradlew :lib:updateKotlinAbi
    @echo
    @git --no-pager diff -- lib/api || true

# Re-measure which DaisyUI classes can be worn at once, rewriting codegen/exclusivity.json,
# and show what changed. That file decides which class groups become a Kotlin enum, so a
# changed verdict is a changed public API — read the diff, never re-dump it blindly.
# Needs Node, the submodules and a system Chromium; run it after a DaisyUI version bump.
measure-exclusivity:
    ./gradlew :e2e-tests:measureExclusivity
    @echo
    @git --no-pager diff --stat -- codegen/exclusivity.json || true

# Fail when codegen/exclusivity.json no longer describes the DaisyUI in the submodule.
# Cheap and browser-free; this is what CI runs. Use `measure-exclusivity` to fix a failure.
verify-exclusivity:
    ./gradlew :lib:verifyExclusivity

# Sync DaisyUI submodule to the tag matching the daisyui version in gradle/libs.versions.toml
sync-daisyui:
    ./gradlew :lib:checkoutDaisyuiTag

# Sync Heroicons submodule to the tag matching the heroicons version in gradle/libs.versions.toml
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
