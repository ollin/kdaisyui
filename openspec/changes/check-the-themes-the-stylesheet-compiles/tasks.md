# Tasks

**Estimate, frozen 2026-11-10:** `2h 40min (5h DevOnly)` by the ledger's `forecast`; 300 minutes
developer-only is the frozen raw figure. Ledger run
`2026-11-10-kdaisyui-check-the-themes-the-stylesheet-compiles`. The review of the same day added the
resource, the finalizer wiring and the probes; the figure stands, the added work is unplanned.

## 1. Open assumptions (probes, nothing committed but the row updates)

- [x] 1.1 A3: in `tmp/check-the-themes-the-stylesheet-compiles/`, a throwaway reader pairs the
  `Fieldref`s of `DaisyThemeTest.class` with class and member; its size and shape decide reader or
  ASM. `. d` updates A3 and design decision 2.
- [x] 1.2 A4: compile the example app's stylesheet with `minify = false` in a scratch copy and record
  how `[data-theme=nord]` appears; `. d` updates A4 and, if quoted, the stylesheet reader's rule.
- [x] 1.3 A7: in a scratch copy of the example app, a finalizer of `kdaisyuiStylesheet` that prints
  a line, run twice; does the second (up-to-date) run print? `. d` updates A7; if not, revise
  design decision 3 before block 2.

## 2. The theme map, under TCR at the root

- [x] 2.1 `^ F (internal)` `codegen/src/themes.ts`: `renderThemeMap(names)` — header and
  `Entry<TAB>value` lines; unit test for two names.
- [x] 2.2 `! F` `index-new.ts` writes `kdaisyui-themes.txt` beside the component map;
  `lib/build.gradle.kts` declares it as an output; `just generate`; the 35-line resource committed.
  Done with `lib/src/test/.../themes/ThemeMapTest.kt`: the packaged resource equals the enum's
  entries and values, so it cannot stop being packaged silently.

## 3. The check, under TCR at the root

- [x] 3.1 `! F (internal)` Pool reader: entry count, UTF-8, Class, NameAndType and Fieldref entries,
  every other tag skipped by its size; a `Fieldref` resolved to owner and name. One run instead of
  the planned two: without the skip table the reader needs an `else` branch no test reaches, which
  the 100 % branch gate refuses.
- [x] 3.2 (folded into 3.1)
- [x] 3.3 `! F (internal)` Pool reader: Methodref and InterfaceMethodref resolved like Fieldref.
  After a `^ r` that picks an entry by conditions instead of `when (tag)` (finding F3).
- [x] 3.4 `^ F (internal)` Pool reader: Long and Double take two slots; the test also reads a real
  class file of the plugin.
- [x] 3.5 `^ F (internal)` `themesNamedIn(classFile, map)`: an entry `Fieldref` gives its value.
- [x] 3.6 `^ F (internal)` `getEntries`, `values`, `valueOf` give every theme.
- [x] 3.7 `^ F (internal)` `DaisyThemesKt.daisyThemeOf` gives every theme; `getValue` gives none.
- [x] 3.8 `^ F (internal)` `themeMap(resource)`: the resource's lines read back.
- [x] 3.9 `^ F (internal)` `themesCarriedBy(css)`: the `[data-theme=…]` names (both forms of 1.2).
- [x] 3.10 `^ F (internal)` `missingThemes(named, carried)`.
- [x] 3.11 `! F (internal)` `themeWarning(missing)`: the missing themes and where to add them,
  the entry's existing themes shown as `…` (finding F4).
- [x] 3.12 `! F` Task class `KdaisyuiThemes` (`@DisableCachingByDefault`, `@InputFiles` classes,
  `@InputFile` stylesheet, `@Classpath` runtime classpath); its action, called on a `ProjectBuilder`
  project with a hand-written stylesheet, returns the warning; without the resource it passes and
  says so at info level.
- [ ] 3.13 `^ F` Registration and `kdaisyuiStylesheet.finalizedBy(kdaisyuiThemes)`;
  `KdaisyuiPluginTest` asserts the finalizer and that `check` does not depend on either task.

## 4. Docs

- [ ] 4.1 `. d` `docs/how-to.md`, "Let users choose a theme", Step 1: the warning and what to do.
- [ ] 4.2 `. d` `codegen/llms-full-template.md` (plugin paragraph) names the warning; `just generate`.

## 5. Gate, adoption and archive

- [ ] 5.1 Full green: `check` with E2E (the example app's `themes: all` build logs no warning — the
  positive control), `koverVerify` and `:gradle-plugin`'s own Kover 100 % and `:gradle-plugin:pitest`
  100 %, StrykerJS on the changed `codegen/src/` lines, `just generate` clean, CodeScene change set.
- [ ] 5.2 The evaluation under `tmp/check-the-themes-the-stylesheet-compiles/`.
- [ ] 5.3 After adoption: archive on the implementation branch, Proposals table, `. d (internal)`.
