# Tasks

**Estimate, frozen 2026-11-14:** `4h 5min (7h 45min DevOnly)` by the ledger's `forecast`
(ratio 0.52 over 192 runs); 465 minutes developer-only is the frozen raw figure. Ledger run
`2026-11-14-kdaisyui-warn-on-raw-daisyui-classes`. Block 1: 15, block 2: 120, block 3: 90,
block 4: 150, block 5: 30, block 6: 60.

The review of the same day added the probes 1.2 and 1.3, an enabler in block 2, and splits of
3.1, 3.3 and block 4. The figure stands; the added work is unplanned.

Ordered by uncertainty:

- **A5** is the one open assumption about Gradle, so its probe comes first.
- **A6** is checked per step of block 4. Its tool, the class-set diff, is built in block 1,
  because the E2E suite asserts too few of the classes to prove those steps.

## 1. Open assumptions and the tools that check them (nothing committed but `. d` updates)

- [x] 1.1 `. d` Record the probe: `tmp/warn-on-raw-daisyui-classes/probe.py`, the rule, and the
  counts it measured. It already sits in `tmp/`, so delete the `/tmp/opencode` copy.
- [x] 1.2 A5: in the example app's build, temporarily add a finalizer of `kdaisyuiClasses` that
  prints one line, and run `:example-app:classes` twice. Does the second run print, with
  `kdaisyuiClasses` UP-TO-DATE? `. d` updates A5. If it does not print, revise design decision 3
  before block 2.
- [x] 1.3 A6's tool: `tmp/warn-on-raw-daisyui-classes/class-sets.py`. It fetches every page and
  fragment route of the running example app (`DashboardResources.kt`) and writes, per route, each
  element's tag, id and `class` attribute as a sorted set. Run it once on `main`'s state; that
  snapshot is the baseline every block 4 step is diffed against. `. d` names the tool in the
  change. Done: 15 routes and 1172 elements in `baseline.json`. Positive control: a
  baseline with `card-body` removed from `/fragments/team` made the diff exit 1, naming the element.

## 2. Detection, under TCR at the repository root

- [ ] 2.1 `^ F (internal)` `ConstantPool.kt`: tag 8 is kept as a string constant, and
  `stringConstantsIn(classFile)` returns the UTF-8 text of each. `8 to 2` leaves `OTHER_SIZES`,
  where it would be dead (an unreachable entry under Kover, a survivor under pitest). The test
  reads a real class file of the plugin's own tests that holds a known literal.
- [ ] 2.2 `^ r (internal)` `ComponentMap`: expose `componentOf(cssClass): String?` from the
  parsed lines, which the map already reads (`ComponentMap.kt:22-25`, private today). `select`
  is unchanged.
- [ ] 2.3 `^ F (internal)` `classTokens(string)` splits on whitespace and `\u0001`, and returns
  null unless every token is class-shaped.
- [ ] 2.4 `^ F (internal)` `daisyComponentsIn(tokens, map)`: the components of the bare tokens.
  Empty when no token holds `-` or `:`; a variant-prefixed token never counts.
- [ ] 2.5 `^ F (internal)` `rawClassLists(owner, classFile, map)`: the owner without `$…`, each
  string once, with its components.
- [ ] 2.6 `! F (internal)` `rawClassesWarning(found)`: null for none; otherwise the text of design
  decision 6, sorted and with equal lines once.

## 3. The task, under TCR at the repository root

- [ ] 3.1 `! F` `KdaisyuiRawClasses` (`@DisableCachingByDefault`, `@InputFiles` classes,
  `@Classpath` runtime classpath). The action returns the warning on a `ProjectBuilder` project
  whose classes hold a raw string.
- [ ] 3.2 `^ F` Without `kdaisyui-components.txt` on the runtime classpath, the action logs at
  info level and returns null.
- [ ] 3.3 `! F` `@Input failBuild`: the action throws a `GradleException` with the warning text,
  and passes without the map even then. `RawClassesSettings` is nested in `KdaisyuiExtension`,
  with the convention `false`.
- [ ] 3.4 `! F` Registration and `kdaisyuiClasses.finalizedBy(kdaisyuiRawClasses)`, the option
  wired into the task. `KdaisyuiPluginTest` asserts the finalizer, the wiring, and that `check`
  depends on neither task.
- [ ] 3.5 `. d` Live on the example app, run twice: both runs log the warning. It should hold 27
  lines, by the probe's count of distinct (file, string) pairs. Record the text in the evaluation.

## 4. The example app, under TCR at the repository root (E2E included)

Every literal the rule finds, from the probe's source run: 32 real and one false, on 31 lines.
Each step is `^ r` and must give an empty class-set diff (1.3) and a green E2E suite, and the
live warning shrinks by the step's lines. Line counts count a changed line as one removed plus
one added.

- [ ] 4.1 `^ r` The drawer, `DashboardShell.kt:39,44,46,53,56` and the `drawer-button` of `:80`,
  through the drawer siblings; `lg:drawer-open`, `z-10` and `lg:hidden` stay strings. About 14
  lines.
- [ ] 4.2 `^ r` The buttons: `DashboardShell.kt:80` (`btn btn-square btn-ghost`) and `:96`
  (`btn btn-circle btn-ghost`, an `extraClasses` of `daisyAvatar`, which moves into its block),
  through `daisyButtonClasses`. About 8 lines.
- [ ] 4.3 `^ r` The loader `DashboardShell.kt:70` through `daisyLoading`, and the dropdown `:94`
  with the `ul` of `:102` (`menu` and `dropdown-content`) through the dropdown and menu
  siblings. About 10 lines.
- [ ] 4.4 `^ r` `menu-active`, `DashboardShell.kt:123,125`: `daisyMenuItemClasses(active = …)`
  inside the `a { }` block, since the `classes` argument has no typed form. The comment at
  `:120-122` goes with the positional-`classes` trap it describes. About 8 lines.
- [ ] 4.5 `^ r` The cards of `dashboard/CardsRow1Fragment.kt:16,63` and
  `dashboard/CardsRow2Fragment.kt:17,39,54`, through `daisyCardClasses` beside the layout
  classes. About 10 lines.
- [ ] 4.6 `^ r` The cards of `dashboard/WhatsNewFragment.kt:25,42,73` and
  `repo/RepoFragment.kt:37,64`. About 10 lines.
- [ ] 4.7 `^ r` `team/TeamFragment.kt:24,25,42` (two cards and `card-body`) and the mask at
  `:80`. About 8 lines.
- [ ] 4.8 `^ r` `dashboard/StatsFragment.kt:13` through `daisyStatsClasses`, with
  `xl:stats-horizontal` kept as a string; `MegamenuReferencePage.kt:44,53` through
  `daisyMenuClasses`; and `DisabledStatePage.kt:54` through `daisyButtonClasses(disabled = true)`,
  which writes the class and not the attribute, as the page compares. About 8 lines.
- [ ] 4.9 `^ r` Rename the id `tab-active` in `TabsPage.kt:18` and in
  `e2e-tests/.../TabsTest.kt:27`. The class-set diff shows only the id. The live warning is now
  empty.
- [ ] 4.10 `^ F` `example-app/build.gradle.kts` sets `rawClasses { failBuild = true }`. Checked
  live: a raw `"btn btn-primary"` added to a page fails `:example-app:classes`, and with it
  removed the build passes.

## 5. Docs

- [ ] 5.1 `. d` `docs/how-to.md`, "Put a component's classes on another element": the warning,
  what it reports and misses (a single bare word), the variant exemption, and `failBuild`, in a
  compiled snippet where it is Kotlin.
- [ ] 5.2 `. d` `codegen/llms-full-template.md`, plugin paragraph: names the check. Then
  `just generate`, so `llms-full.txt` is regenerated and the working tree is clean.

## 6. Gate, adoption and archive

- [ ] 6.1 Full green: `check` with E2E, `koverVerify`, `:gradle-plugin`'s own Kover 100%,
  `:gradle-plugin:pitest` at 100% test strength, `:doc-snippets:compileSnippets`,
  `analyze_change_set`, and the touched hotspots on the CodeScene server.
- [ ] 6.2 `openspec validate warn-on-raw-daisyui-classes --strict`.
- [ ] 6.3 `findings.md` of the change: one entry per finding, and the Proposals table.
- [ ] 6.4 The evaluation under `tmp/warn-on-raw-daisyui-classes/` for Oliver to adopt. It includes
  the hand-over for the Deploy-Radar: the probe re-run by the agent over fwb-application's
  `fwb-deploy-radar/server/src/main/kotlin`, read-only, listing the 14 sites with `file:line`.
  The migration there stays fwb-application's change.
- [ ] 6.5 After adoption: archive on this branch, `. d (internal)`, the change's `tmp/` deleted.
