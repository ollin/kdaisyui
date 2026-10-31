# Tasks

**Estimate, frozen 2026-10-31:** 150 minutes, the probe included (30), the generator (90) and
verification (30).

## 1. Open assumptions

- [x] 1.1 (A2, A3) Probe the shape on Card, Dropdown, Menu and Button; recorded in `design.md`.

## 2. The generator, under TCR

- [x] 2.1 `^ r (internal)` Every coverage assertion takes an `Expected`; regenerate.
- [x] 2.2 `^ r (internal)` An all-flags test is one `allFlags` call over the four hatch constants; regenerate.
- [x] 2.3 `^ r (internal)` One table test per enum axis; regenerate.
- [x] 2.3a `^ r (internal)` A class sibling's all-flags test is one `assertRenders` call (F1).
- [x] 2.4 `. d` (A4) `:lib:pitest` at 100% test strength, and all 66 coverage tests scored by CodeScene.

## 3. Gate

- [x] 3.1 Full green: `check` with E2E, `koverVerify`, both PIT runs, `analyze_change_set`.
- [x] 3.2 `openspec validate coverage-tests-without-duplication --strict`.
