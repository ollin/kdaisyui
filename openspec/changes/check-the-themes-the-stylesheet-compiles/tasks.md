# Tasks

**Estimate, frozen 2026-11-10:** `2h 40min (5h DevOnly)` by the ledger's `forecast`; 300 minutes
developer-only is the frozen raw figure. Ledger run
`2026-11-10-kdaisyui-check-the-themes-the-stylesheet-compiles`.

## 1. Open assumptions

- [ ] 1.1 A3: a constant-pool reader in `gradle-plugin/.../ConstantPool.kt`, tested against the
  `DaisyThemeTest.class` bytes and a hand-made pool; if it exceeds ~80 lines with tests, revise the
  design toward ASM before block 2. `^ F (internal)` per step, each ≤ 8 lines.

## 2. The check, under TCR at the root

- [ ] 2.1 `^ F` `themesNamedIn(classFile)`: entry `Fieldref`s give their theme names.
- [ ] 2.2 `^ F` `getEntries`, `values`, `valueOf` and `daisyThemeOf` give "every theme".
- [ ] 2.3 `^ F` `getValue` and other members give none.
- [ ] 2.4 `^ F` `themesCarriedBy(css)`: the `[data-theme=…]` names of a stylesheet.
- [ ] 2.5 `^ F` `missingThemes(named, carried)` and the warning text with the entry line.
- [ ] 2.6 `! F` The task `kdaisyuiThemes`, registered and wired into `check`; a TestKit test
  (in the shape of `KdaisyuiPluginTest`) shows the warning for a build that names `Nord`.
- [ ] 2.7 `. F (internal)` pitest scope of `:gradle-plugin` covers the new files; 100 % strength.

## 3. Docs

- [ ] 3.1 `. d` `docs/how-to.md`, "Let users choose a theme", Step 1: the warning and what to do.

## 4. Gate, adoption and archive

- [ ] 4.1 Full green: `check` with E2E, Kover, `:gradle-plugin:pitest`, CodeScene change set.
- [ ] 4.2 The evaluation under `tmp/check-the-themes-the-stylesheet-compiles/`.
- [ ] 4.3 After adoption: archive on the implementation branch, Proposals table, `. d (internal)`.
