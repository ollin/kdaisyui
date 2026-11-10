## Why

Renovate's Gradle 9.9.0 update fails CI's `unit-tests` job at `:build-checks:checkGradleProblems`
(run 42837684977), naming three problems no allowlist line accepts: `Method usage` from Kover, from
the PIT plugin, and from "this build". Measured locally on that branch with
`./gradlew check -x :e2e-tests:test --rerun-tasks` and the check: the build reports the same two
`Configuration.setVisible` deprecations as on 9.8.2, from the same two plugins, and nothing else.
Gradle 9.9.0 changed the report's format in two places:

1. **The problem id is generic.** 9.8.2 named it
   `the-configuration-setvisible-boolean-method-has-been-deprecated`; 9.9.0 names it `Method usage`,
   for every deprecated method. The specific deprecation is left in `contextualLabel` and in the
   anchor of `documentationLink`, `#deprecate-visible-property`, which 9.8.2 writes too.
2. **The summaries moved.** 9.8.2 put them under a `"problemsReport"` key, which the reader uses as
   the boundary of the listed diagnostics; 9.9.0 puts them in their own
   `<script type="application/json" id="problems-summary">`. The reader finds no boundary, reads
   the summary as a diagnostic without a plugin, and reports it as the build's own.

## What Changes

- The reader identifies a problem by the anchor of its `documentationLink` where it has one,
  else by its most specific `problemId` name, as today. A summary still matches a listed problem by
  that name, since summaries carry no link.
- The listed diagnostics end at `"problemsReport"` or at `id="problems-summary"`, whichever the
  report has.
- `gradle/allowed-gradle-problems.txt` accepts `deprecate-visible-property` from Kover and PIT,
  in place of the 9.8 id.
- Gradle stays at 9.8.2 here; Renovate's branch goes green once this is on `main`.

### The subtractive option, stated

Allowlist `Method usage` per plugin and change nothing else. Rejected by Oliver 2026-11-10: it
accepts every future deprecated method of both plugins unseen, which is what the check exists to
prevent. The summary misreading would also remain and report the build's own problem forever.
Nothing can be removed: the two plugins still call `setVisible`.

## Out of scope

- The Gradle 9.9.0 update itself: Renovate's `renovate/gradle-9.x`.
- The `armored` deprecation warning in `build.gradle.kts:168`: a Kotlin compiler warning about
  JReleaser's API, not a Gradle problem, and present on 9.8.2 too.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Gradle 9.8.2 and 9.9.0 both write `documentationLink` with the anchor `#deprecate-visible-property` on each `setVisible` diagnostic | code | confirmed at proposal - `build/reports/problems/problems-report.html` of both versions, 15 occurrences each, read 2026-11-10 |
| A2 | A summary carries no `documentationLink`, in either version | code | confirmed at proposal - the summaries of both reports hold `problemId` and `count` only |
| A3 | Using the anchor as the id is the right identity | user | confirmed by the user 2026-11-10 - the anchor, over `contextualLabel` and over `Method usage` |
| A4 | No requirement of a main spec this builds on contradicts it. It builds on none: `grep -rl checkGradleProblems openspec/specs` finds nothing | code | confirmed at proposal |

## Impact

- `build-checks/src/main/kotlin/.../GradleProblem.kt` and its test; `gradle/allowed-gradle-problems.txt`;
  `.opencode/skills/kdaisyui-build/SKILL.md`, which names the 9.8 id's meaning.
