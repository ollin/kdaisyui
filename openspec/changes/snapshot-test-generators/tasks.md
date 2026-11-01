# Tasks

Every step that touches a generator is a TCR run in `codegen/`, preceded by `just generate` and a
check that it left no diff. Fixtures are copied, never edited.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 120 minutes.

| Section | Minutes |
|---|---|
| 1. Open assumptions | 15 |
| 2. Fixtures and the refresh recipe | 20 |
| 3. Three snapshot tests, under TCR | 45 |
| 4. Measure, and what survives | 25 |
| 5. Documents, gate and adoption | 15 |

## 1. Open assumptions

- [ ] 1.1 `. d` (A1, A2) In a scratch script under `tmp/snapshot-test-generators/`, build `kbd`
  from its model and its frontmatter alone, and `AcademicCap` from four copied SVGs, and diff both
  against the committed files.
- [ ] 1.2 `. d` (A3) Read `test-generator-heroicons.ts` for anything it reads besides the icon
  files.

## 2. Fixtures and the refresh recipe

- [ ] 2.1 `. F (internal)` `just refresh-codegen-fixtures`: the three frontmatter blocks and the
  eight SVGs, copied from the pinned submodules, with a `README.md` per fixture root.

## 3. Three snapshot tests, under TCR

- [ ] 3.1 `. r (internal)` The component emitter against the three committed components.
- [ ] 3.2 `. r (internal)` The icon emitter against the two committed icons.
- [ ] 3.3 `. r (internal)` The icon test generator against the committed `HeroIconsGeneratedTest.kt`.

## 4. Measure, and what survives

- [ ] 4.1 `. d` StrykerJS on the four files before and after (A4); per surviving group, delete
  code that cannot matter or add the case that kills it.

## 5. Documents, gate and adoption

- [ ] 5.1 `. d` `kdaisyui-daisyui-upgrade`: the refresh step after a bump; `kdaisyui-codegen`: the
  fixtures and why they exist.
- [ ] 5.2 Gate: `just generate` without a diff, `node --test`, the mutation gate on changed lines,
  CodeScene before the push, `openspec validate --strict`, findings, archive, pull request.
