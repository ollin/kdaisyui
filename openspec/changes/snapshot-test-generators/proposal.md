## Why

After `snapshot-test-coverage-generator`, the generators that unit tests barely reach are the
component emitter and the Heroicons pipeline. Full StrykerJS run, 2026-11-01:

| File | Survivors |
|---|---|
| `generator-new.ts` | 199 |
| `generator-heroicons.ts` | 101 |
| `test-generator-heroicons.ts` | 88 |
| `parser/svg-heroicons.ts` | 76 |

Their outputs are committed and drift-checked. Their inputs are partly in the submodules: the
component emitter takes descriptions from DaisyUI's `+page.md`, the icon emitter four SVGs per icon
from Heroicons. The `codegen-tests` job checks out neither, and `kdaisyui-testing` says codegen
tests read no submodule.

**Revised 2026-11-01, before any work.** The first version vendored three pages and two icons as
fixtures. Reconsidered with Oliver: the rule exists only because the job had no submodules (a test
reading one died with `ENOENT`, which reads like a broken checkout), and the no-submodule promise
in `AGENTS.md` is about `./gradlew check`, which the codegen tests are deliberately not part of.
Checking the submodules out in the job removes the rule's reason, costs about 4 s (the drift job's
checkout takes 6 s with them, about 2 s without), and lets every component and every icon be
compared, with no fixture to keep fresh.

## What Changes

- **`codegen-tests` checks out the submodules**, as `generated-sources-drift` does.
- **Three snapshot tests**, each comparing a generator with committed output, for all of it:
  1. the component emitter, from the committed models and the pinned DaisyUI pages, against all 66
     `lib/generated/main/…/components/<Name>.kt`;
  2. the icon emitter, from the pinned Heroicons SVGs, against all 324 `…/icons/<Name>.kt`;
  3. the icon test generator, from the committed icon files, against `HeroIconsGeneratedTest.kt`.
- **A missing submodule fails loudly, never skips.** One helper checks for the checkout and throws
  `the DaisyUI submodule is not checked out: git submodule update --init` (or Heroicons), so the
  failure names its fix instead of an `ENOENT` deep in a parser.
- **The rule is rewritten** in `kdaisyui-testing`: codegen tests may read the pinned submodules,
  the job checks them out, and a test never skips when one is absent.
- What still survives is measured and recorded, as before.

**Rejected: vendored fixtures** (the first version of this change). Three pages and two icons where
the job can compare all of them, plus a refresh recipe and an upgrade step to keep the copies from
going stale.

## Deliberately out of scope

- The documented-element half of `test-generator.ts` (267 survivors). With the submodule in the
  job it becomes testable the same way, but it compares documented examples, not one file per
  component, and is its own change.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A full snapshot run, 66 components and 324 icons, is fast enough to rerun per mutant: under 2 s | measurement | verified in task 1.1: generating all 66 components took 13 ms and all 324 icons 12 ms, after 13 ms of imports |
| A2 | The emitters reproduce the committed files from the submodules with no other input than the committed models | probe | verified in task 1.1: 66 of 66 components and 324 of 324 icons equal their committed files, 0 mismatches |
| A3 | The icon test generator is a pure function of the committed icon files | reading | refuted in task 1.2: it parses the Heroicons SVGs, like the icon emitter. With the submodule in the job it is tested the same way |
| A4 | Checking out the submodules adds under 10 s to `codegen-tests` | measurement | open, task 2.2 |
| A5 | The three tests together kill most of the four files' mutants | measurement | open, task 4.1; *wrong if* more than a third survive in `generator-new.ts` |

## Capabilities

<!-- skip_specs: generated output and every gate are unchanged. -->

## Impact

- **Edited**: `.github/workflows/ci.yml` (`codegen-tests`: `submodules: recursive`), generator entry
  points only where a pure function has to be split out.
- **New**: three test files and one helper that locates the submodules.
- **Edited**: `kdaisyui-testing` (the rule), `kdaisyui-codegen` (the tests).
- No generated file changes.
