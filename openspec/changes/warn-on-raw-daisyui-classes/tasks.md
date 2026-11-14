# Tasks

**Estimate, frozen 2026-11-14:** `4h 5min (7h 45min DevOnly)` by the ledger's `forecast`
(ratio 0.52 over 192 runs); 465 minutes developer-only is the frozen raw figure. Ledger run
`2026-11-14-kdaisyui-warn-on-raw-daisyui-classes`. Block 1: 15, block 2: 120, block 3: 90,
block 4: 150, block 5: 30, block 6: 60.

Ordered by uncertainty. A1, A2 and A6 were measured at proposal. A5 is the one open assumption
about Gradle, and it gets checked live in 3.3. Block 4 carries the Assumed requirement about the
example app, but it needs the check that block 3 builds as its own control.

## 1. Open assumptions

- [ ] 1.1 `. d` Record the probe in the change: the rule and its counts as measured by
  `/tmp/opencode/warn-on-raw-daisyui-classes/probe.py`. Copy the script to
  `tmp/warn-on-raw-daisyui-classes/` for the evaluation, then delete the `/tmp` copy.

## 2. Detection, under TCR at the repository root

- [ ] 2.1 `^ F (internal)` `ConstantPool.kt`: tag 8 kept as a string constant;
  `stringConstantsIn(classFile)` returns the UTF-8 text of each. The test reads a real class file
  of the plugin's own tests holding a known literal.
- [ ] 2.2 `^ F (internal)` `rawClassLists.kt`: `classTokens(string)` splits on whitespace and
  `\u0001`, and returns null unless every token is class-shaped.
- [ ] 2.3 `^ F (internal)` `daisyClassesIn(tokens, classes)`: the bare tokens in the map. Empty
  when no token holds `-` or `:`; a variant-prefixed token never counts.
- [ ] 2.4 `^ F (internal)` `componentClasses(resource)`: `kdaisyui-components.txt` read back as
  class → component. Reuse `ComponentMap.kt`'s parsing if it already holds the third column.
- [ ] 2.5 `^ F (internal)` `RawClassList(owner, string, components)` for one class file: the
  owner without `$…`, and equal strings once.
- [ ] 2.6 `! F (internal)` `rawClassesWarning(found)`: null for none; otherwise the text of design
  decision 6, sorted.

## 3. The task, under TCR at the repository root

- [ ] 3.1 `! F` `KdaisyuiRawClasses` (`@DisableCachingByDefault`, `@InputFiles` classes,
  `@Classpath` runtime classpath, `@Input failBuild`). The action returns the warning on a
  `ProjectBuilder` project, and logs at info level and passes without the component map.
- [ ] 3.2 `! F` `failBuild`: the action throws a `GradleException` with the warning text.
  `RawClassesSettings` is nested in `KdaisyuiExtension`, with the convention `false`.
- [ ] 3.3 `! F` Registration and `kdaisyuiClasses.finalizedBy(kdaisyuiRawClasses)`;
  `KdaisyuiPluginTest` asserts the finalizer and that `check` depends on neither. Checked live on
  the example app, run twice: the second run, `kdaisyuiClasses` UP-TO-DATE, logs the warning
  again (A5). If it does not, revise design decision 3 before block 4. Record the live warning,
  which should hold 27 lines, in the evaluation.

## 4. The example app, under TCR at the repository root (E2E included)

Each step is `^ r`: the same class set and the E2E suite unchanged. The live warning shrinks by
the step's lines.

- [ ] 4.1 `^ r` `DashboardShell.kt`: drawer, drawer toggle, content, side, overlay and button
  through the drawer siblings; loading, the two dropdowns, the button classes and the two
  `menu-active` (14 sites).
- [ ] 4.2 `^ r` The cards: `CardsRow1Fragment.kt`, `CardsRow2Fragment.kt`,
  `WhatsNewFragment.kt`, `RepoFragment.kt` and `TeamFragment.kt`'s cards and `card-body`, through
  `daisyCardClasses` / `daisyCardBodyClasses` with the layout classes beside them (14 sites).
- [ ] 4.3 `^ r` `StatsFragment.kt`, the mask in `TeamFragment.kt`, `MegamenuReferencePage.kt`'s two
  menus, and `DisabledStatePage.kt`'s `btn btn-disabled` through `daisyButtonClasses(disabled =
  true)`, which writes the class and not the attribute, as the page compares (5 sites).
- [ ] 4.4 `^ r` Rename the id `tab-active` in `TabsPage.kt` and `e2e-tests/.../TabsTest.kt`. The
  live warning is now empty.
- [ ] 4.5 `! F` `example-app/build.gradle.kts` sets `rawClasses { failBuild = true }`. Checked
  live: a raw `"btn btn-primary"` added to a page fails `:example-app:classes`; removed, it passes.

## 5. Docs

- [ ] 5.1 `. d` `docs/how-to.md`, "Put a component's classes on another element": the warning,
  what it reports, the variant exemption and `failBuild`, in a compiled snippet where it is
  Kotlin.
- [ ] 5.2 `. d` `codegen/llms-full-template.md`, the plugin paragraph, names the check;
  `just generate`; `llms-full.txt` regenerated and the working tree clean.

## 6. Gate, adoption and archive

- [ ] 6.1 Full green: `check` with E2E, `koverVerify`, `:gradle-plugin`'s own Kover 100% and
  `:gradle-plugin:pitest` 100% test strength, `:doc-snippets:compileSnippets`,
  `analyze_change_set` and the touched hotspots on the CodeScene server.
- [ ] 6.2 `openspec validate warn-on-raw-daisyui-classes --strict`.
- [ ] 6.3 The evaluation under `tmp/warn-on-raw-daisyui-classes/` for Oliver to adopt, with
  the hand-over for the Deploy-Radar: the 14 sites the check will name there.
- [ ] 6.4 After adoption: archive on this branch, `. d (internal)`, the change's `tmp/` deleted.
