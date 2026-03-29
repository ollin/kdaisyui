# kdaisyUI — task runner
# Run `just` or `just --list` to see all available recipes.
# Requires: just (see .tool-versions), JDK, Node.js

# List all recipes
default:
    @just --list

# ── Build ────────────────────────────────────────────────────────────────────

# Build all Gradle modules
build:
    ./gradlew build

# Clean all build artifacts
clean:
    ./gradlew clean
    rm -rf e2e-tests/playwright-report e2e-tests/test-results

# ── Testing ──────────────────────────────────────────────────────────────────

# Run library unit tests
test:
    ./gradlew :lib:test

# Run E2E tests (Playwright starts and stops the Ktor server automatically)
e2e:
    cd e2e-tests && npm install --silent && npm test

# Run all tests: unit + E2E
test-all: test e2e

# ── Development ──────────────────────────────────────────────────────────────

# Start the example app (http://localhost:8080)
dev:
    ./gradlew :example-app:run
