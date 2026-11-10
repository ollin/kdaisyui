## Why

In `build-checks`, a Gradle problem's `pluginId: String?` uses `null` to mean "the build itself
caused it". That meaning lives in three places that only agree by accident:
`CheckGradleProblems.kt` turns the `null` back into words (`?: "this build"`), `Allowlist.kt`
can never accept it only because no line can hold a `null`, and `GradleProblem.kt` produces it
from a regex that found nothing. Next to it, `GradleProblem.kt` passes each report entry around
as a raw `String` through three free functions (`entriesOf`, `idOf`, `nameOf`), and the
`?.` + `?:` shape in one of them cost a TCR revert (`read-the-gradle-9-9-problems-report`, F2).
Oliver asked on 2026-11-10 whether value objects and a null object make this simpler.

## What Changes

A refactoring, no behaviour change:

- **`Source`**, a sealed type: `Source.Plugin(id)` or `Source.ThisBuild`, the null object, which
  prints as `this build`. `GradleProblem(id, source)` replaces `GradleProblem(id, pluginId)`; the
  allowlist reads its lines into `Source.Plugin`, so the build's own problem cannot be accepted by
  type, not by accident.
- **`ReportEntry`**, a value class around one entry of the report, with `name`, `id` and `source`;
  it replaces `entriesOf`, `idOf` and `nameOf`. The one `?:` per optional value sits there.

### The subtractive option, stated

Leave it: the code is green and covered. Rejected by Oliver: the `null` carries a meaning that
three files restate. What this removes: the nullable property, the `?: "this build"` translation,
and three free functions on raw strings.

## Out of scope

- A JSON parser for the report: it would be the module's first dependency, for a reader that
  needs three fields.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The check's output stays byte-identical, `this build` included | code | confirmed in task 1.1 - `CheckGradleProblemsTest`, unchanged, asserts the full message with `(this build)` and passed |
| A2 | No requirement of a main spec this builds on contradicts it. It builds on none: `grep -rl checkGradleProblems openspec/specs` finds nothing | code | confirmed at proposal |

## Impact

- `build-checks/src/main/kotlin/.../{GradleProblem,Allowlist,CheckGradleProblems}.kt` and the
  constructors in their tests.
