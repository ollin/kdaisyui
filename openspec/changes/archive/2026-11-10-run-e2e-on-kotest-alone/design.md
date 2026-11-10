## Context

See `proposal.md` for why. What shapes the how:

- `mcp_Tcr` at the repository root runs `./gradlew build`, which includes `:e2e-tests:test`, so
  every step runs the whole E2E suite (37 s on 2026-11-10).
- The E2E suite has no mutation tool. A ported test that cannot fail would pass every gate. So
  each port also gets a control: one expectation is broken on purpose, the spec is run alone and
  goes red, and the expectation is restored. The control is never committed.
- Under TCR's Relaxed variant the test files are never reverted, and everything here is a test
  file. A red run therefore reverts nothing and means "fix the port", not "take a smaller step".
  Each port is still one run.

## Goals / Non-Goals

**Goals:** one framework. Every Cucumber-only assertion keeps an equivalent in Kotest, and every
scenario name survives as a test name.

**Non-Goals:** new assertions beyond A1's gaps, shared step helpers designed ahead of need, or
data-driven testing.

## Decisions

Enablers: D1, D2, D3. Desired change: D4 (Cucumber leaves the build).

### D1. JavaScript off is a constructor argument of `PlaywrightSpec`

**Chosen:** `abstract class PlaywrightSpec(javaScriptEnabled: Boolean = true)`. The `beforeTest`
context uses the flag. A feature that mixes runs with and without JavaScript becomes two specs,
for example `DropdownPopoverTest` and `DropdownPopoverWithoutJavaScriptTest`. *In the event no split was needed*: the scripted half of each such feature only fetched
HTML, which does not depend on the browser's scripting, so each feature became one spec with
scripting off.

**Rejected: a per-test tag read in `beforeTest`.** It needs Kotest's test-config API inside a
lifecycle hook, which nobody here has used yet, and it buys one class fewer for three features.
**Rejected: a `withoutJavaScript { }` helper that opens a second context.** That context would
have no console-error teardown, unless the teardown were duplicated, and the teardown is what this
change consolidates.

### D2. Screenshots as a protected function, not a step

**Chosen:** `protected fun saveScreenshot(name: String)` in `PlaywrightSpec`, with
`ScreenshotSteps`' body (full page, animations disabled, written to `build/reports/screenshots/`).
Viewport changes are a plain `page.setViewportSize(…)` in the test.

### D3. Helpers only where two specs need them

The Cucumber steps were shared by construction. Their bodies move into the one spec that uses
them. Only a helper that two ported specs need moves into `PlaywrightSpec` or a file of
top-level functions. Candidates: the computed-style comparison (disabled state, tabs), the
stylesheet rule lookup (assets, tailwind variant) and the popover open/closed checks.

**Rejected: port the step classes as one helper object first.** That keeps Gherkin's vocabulary
without Gherkin, and most steps have one caller.

### D4. Remove Cucumber last, in one step

**Chosen:** once no `.feature` file is left, remove the runner, hooks, world, step package,
dependencies, catalog entries and the naming-strategy property in one run, and count the tests
(A3). **Rejected: remove the dependencies feature by feature.** Every feature needs the engine
until the last one is ported.

### D5. Duplicates go after their twin is proven complete

**Chosen:** task 1.1 compares each duplicated feature with its Kotest twin, step by step, and
records the comparison in `tasks.md`. A missing assertion is added to the twin, and that addition
is its own step, before the feature is deleted.

## Risks / Trade-offs

- [A port quietly weakens an assertion] → the per-port control, and test names kept 1:1 so a
  reviewer can lay the feature next to the spec.
- [Splitting specs by JavaScript doubles some class names] → three pairs at most, named for the
  difference.
- [Kotest runs specs in parallel, unlike Cucumber] → `PlaywrightSpec` already gives each test its
  own context; the Cucumber scenarios ran in the same JVM against the same server.
  `kotest.properties` and any project config are left as they are.

## Migration Plan

One branch, one pull request. Rollback: revert the merge commit.
