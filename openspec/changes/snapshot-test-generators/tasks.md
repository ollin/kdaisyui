# Tasks

Every step that touches a generator is a TCR run in `codegen/`, preceded by `just generate` and a
check that it left no diff.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 120 minutes. It stands for the
revised plan as well, which was revised before any work.

| Section | Minutes |
|---|---|
| 1. Open assumptions | 15 |
| 2. Submodules in the job, and a loud absence | 20 |
| 3. Three snapshot tests, under TCR | 45 |
| 4. Measure, and what survives | 25 |
| 5. Documents, gate and adoption | 15 |

## 1. Open assumptions

- [x] 1.1 `. d` (A1, A2) In a scratch script under `tmp/snapshot-test-generators/`, generate all 66
  components and all 324 icons in memory from the submodules, diff against the committed files,
  and time the run.
- [x] 1.2 `. d` (A3) Read `test-generator-heroicons.ts` for anything it reads besides the icon files.

## 2. Submodules in the job, and a loud absence

- [x] 2.1 `! F (internal)` `src/submodules.ts`: the repository root found upward from the caller
  (the directory holding `.gitmodules`), and the path of a pinned submodule there, or an error
  naming `git submodule update --init`. Found in task 1.1: the generators resolve
  `../../daisyui` from their own file, which points nowhere inside StrykerJS's sandbox under
  `codegen/.stryker-tmp/`. `frontmatter.ts`, the two Heroicons entry points and the icon test
  generator read their submodule through it, and their own `existsSync` checks go.
- [x] 2.2 `! F (internal)` `codegen-tests` checks out the submodules; record the added time (A4).

## 3. Three snapshot tests, under TCR

- [x] 3.1 `. r (internal)` The component emitter against all 66 committed components.
- [x] 3.2 `. r (internal)` The icon emitter against all 324 committed icons.
- [x] 3.3 `. r (internal)` The icon test generator against the committed `HeroIconsGeneratedTest.kt`.

## 4. Measure, and what survives

- [ ] 4.1 `. d` StrykerJS on the four files before and after (A5); per surviving group, delete code
  that cannot matter or add the case that kills it.

## 5. Documents, gate and adoption

- [x] 5.1 `^ d` `kdaisyui-testing`: the rewritten rule; `kdaisyui-codegen`: the snapshot tests.
- [ ] 5.2 Gate: `just generate` without a diff, `node --test`, the mutation gate on changed lines,
  CodeScene before the push, `openspec validate --strict`, findings, archive, pull request.
