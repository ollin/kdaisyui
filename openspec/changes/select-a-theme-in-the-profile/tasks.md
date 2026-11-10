# Tasks

**Estimate, frozen 2026-11-10:** `4h 30min (8h 30min DevOnly)` by the ledger's `forecast`
(ratio 0.52 over 168 runs); 510 minutes developer-only is the frozen raw figure. Block 1: 30,
block 2: 210, block 3: 150, block 4: 60, block 5: 60. Ledger run
`2026-11-10-kdaisyui-select-a-theme-in-the-profile`. Tasks 2.8 and 4.4 were added after the
freeze by A7 (finding F3), the review split 2.3 and 3.3 and added 2.2 (finding F4), and the re-review folded the API
re-dump into each step that changes the API (finding F5); the
figure stands, the added time is recorded as unplanned.

Ordered by uncertainty: A3 is the one thing only a compile can answer, so it runs before any
code, and it also observes A2 in the compiled output. A5, the one browser-only assumption, is the
scenario of 3.4; nothing in block 2 depends on it, because the enum and the two functions are
correct whatever a browser renders. Every step in block 3 carries its own scenario, so root TCR,
which runs the E2E suite, sees test and code together.

Literals this change replaces, grepped 2026-11-10: `@plugin "daisyui";` occurs once,
`example-app/src/main/css/app.css:19` (task 3.3); `li { a { +"Profile" } }` once,
`example-app/src/main/kotlin/kdaisyui/example/DashboardShell.kt:89` (task 3.9). No generator or
test hard-codes either.

## 1. Open assumptions

- [x] 1.1 A3 and A2: in a scratch copy under `tmp/select-a-theme-in-the-profile/`, change the
  entry to `@plugin "daisyui" { themes: all; }`, run `./gradlew :example-app:kdaisyuiStylesheet`,
  and record the minified size against today's 150,330 bytes, and that `[data-theme=nord]`
  occurs in the output although the narrowed class list names no theme. No commit of the CSS here
  (it lands with its scenario in 3.3); the proposal's A2 and A3 rows are updated in one `. d`. If
  the compile fails or a theme is missing, revise the change before block 2.

A7 and A8 Oliver decided on 2026-11-10. A9 depends on the enum and is checked in task 2.8; A5 in
task 3.4.

## 2. The library API, under TCR

- [x] 2.1 `^ F (internal)` `codegen/src/themes.ts`: `themeNames(fileNames)` returns the stems of
  the `*.css` names, sorted; its `node --test` passes three CSS names out of order and one
  non-CSS name. A pure function over names: reading the directory belongs in the entry point
  (task 2.5), which the mutation gate leaves out (`kdaisyui-codegen`, Mutation testing).
- [x] 2.2 `^ F (internal)` `themeNames` fails on a stem that is not lowercase letters only,
  naming the file, so the PascalCase rule of the spec cannot meet a hyphen or a digit unseen; the
  test passes `foo-bar.css`.
- [x] 2.3 `^ F (internal)` `renderDaisyThemeEnum(names)` returns the Kotlin source of
  `DaisyTheme` in `io.github.ollin.kdaisyui.themes`, the GENERATED header the other outputs carry,
  one entry per name as `Nord("nord")`; asserted for two names.
- [x] 2.4 `. F (internal)` `lib/build.gradle.kts:254`: `generateComponents`, whose output is the
  components directory today, also declares
  `lib/generated/main/kotlin/io/github/ollin/kdaisyui/themes` as an output. Nothing writes there
  yet; `./gradlew :lib:generateComponents` still leaves the tree clean.
- [x] 2.5 `! F` `index-new.ts` (the `generateComponents` entry point, so `just generate` and the
  drift job need no new task) writes `DaisyTheme.kt` into that directory from the submodule's
  `packages/daisyui/src/themes` via `submodules.ts`; `just generate`; the 35-entry file is
  committed, `./gradlew :lib:updateKotlinAbi` re-dumps `lib/api/lib.api` in the same run (it
  gains `DaisyTheme` and nothing else; `checkKotlinAbi` runs in `check`, so a stale baseline
  would revert the step), and the tree is clean afterwards. `!` because the committed generated file counts
  about 40 lines under the notation's counting, which does not exempt generated Kotlin here;
  nothing calls it yet. Done with `lib/src/test/…/themes/DaisyThemeTest.kt` asserting 35 entries
  and `Nord.value`, because Kover's 100% gate covers generated code and would fail on an enum no
  test loads.
- [x] 2.6 `! F` (planned `^`, see finding F7) `lib/src/main/kotlin/io/github/ollin/kdaisyui/themes/DaisyThemes.kt`:
  `Tag.daisyTheme(theme)` writes `data-theme`; a test in `lib/src/test/…/themes/` renders
  `html { daisyTheme(DaisyTheme.Nord) }` and asserts `data-theme="nord"`; `lib.api` re-dumped in
  the same run (gains `DaisyThemesKt.daisyTheme`).
- [ ] 2.7 `! F` (finding F7) `daisyThemeOf(value)`: the test asserts `"nord"` gives `Nord`, `"Nord"` and `""`
  give `null`, and every entry round-trips (which also executes every enum entry for Kover);
  `lib.api` re-dumped in the same run (gains `daisyThemeOf`).
- [ ] 2.8 `! F` `codegen/model/theme-controller.json` declares the `theme` extra (design,
  decision 7); `just generate`; `ThemeController.kt`, its generated tests,
  `docs/reference/themeController.md` and `lib/api/components.api` follow (the parameter's name
  and type), `lib/api/lib.api` is re-dumped in the same run (the one changed
  `daisyThemeController` descriptor, read before committing), and `koverVerify` sees
  both branches of the `apply` (A9). One commit, not two: a model edit without its regenerated
  output fails `generated-sources-drift`, so neither half is a green commit alone. `!` because the
  two together exceed eight counted lines. If the test generator or Kover objects, revise first.
- [ ] 2.9 `. F (internal)` `lib/build.gradle.kts`: `targetClasses` gains
  `io.github.ollin.kdaisyui.themes.*`; `./gradlew :lib:pitest` reports 100% test strength.

## 3. The profile page and its scenarios, under TCR at the root

- [ ] 3.1 `^ F` `@Resource("/profile") class ProfilePage` in `DashboardResources.kt`, a route in
  `DashboardRoutes.kt`, and `ProfilePage.kt` rendering `html` with `pageAssets()` and an `h1`;
  a new `profile.feature` asserts status 200 and the heading, in the shape of
  `popover_modal.feature:4-7`.
- [ ] 3.2 `! F` A `form method=post` with one `daisyRadio(name = "theme")` per `DaisyTheme` and
  one `value="system"`, plus a Save `daisyButton`; ids in `Ids.kt`; the scenario counts 36 radios.
  `!` because form, ids and scenario exceed eight counted lines.
- [ ] 3.3 `^ F` `app.css` says `themes: all`; an endpoint scenario asserts that `/static/app.css`
  holds a `[data-theme=` rule for `nord` and for `abyss` (A2, A3 on the served stylesheet).
- [ ] 3.4 `! F` Each theme's radio sits in a `label` with a card carrying `daisyTheme(theme)` and
  the five swatches (design, decision 4); the scenario asserts, for every card but `light`'s, that
  its computed `--color-base-100` differs from the page's (A5 in a browser). If any card computes
  the default, revise the change first. `!` because card, swatches and scenario exceed eight
  counted lines.
- [ ] 3.5 `^ F` `POST /profile/theme` with a theme sets the cookie and answers 303 to `/profile`
  (design, decision 5); the scenario chooses `nord`, saves, and asserts the URL and the cookie.
- [ ] 3.6 `^ F` A value `daisyThemeOf` refuses answers 400 and sets no cookie; an endpoint scenario.
- [ ] 3.7 `^ F` `dashboardShell` and the profile page write `html { daisyTheme(…) }` from the
  cookie, and the profile checks the stored choice; the scenario saves `nord`, opens `/`, and
  asserts `html[data-theme=nord]` and the body's computed background equals nord's base-100.
- [ ] 3.8 `^ F` `system` deletes the cookie; the scenario saves `nord`, then "System default",
  and asserts `html` has no `data-theme`.
- [ ] 3.9 `^ F` The avatar menu's "Profile" links to `href(ProfilePage())`; the scenario opens the
  menu, clicks it, and lands on the profile.

## 4. Docs

- [ ] 4.1 `. d` `docs/how-to.md`, "Let users choose a theme": the entry's `themes` line and naming
  fewer; the webjar's `themes.css` carrying all; `html { daisyTheme(…) }`; the preview card;
  the POST route (`<!-- snippet: in Route -->`); a light/dark toggle with
  `daisyThemeController(theme = DaisyTheme.Dark)` beside the `daisyToggle` +
  `daisyThemeControllerClasses()` form; that `DaisyTheme` names every built-in theme
  while the stylesheet decides which exist; a custom theme as `attributes["data-theme"]`.
- [ ] 4.2 `. d` `codegen/llms-template.md` names the recipe, `codegen/llms-full-template.md`
  describes `DaisyTheme`, `daisyTheme`, `daisyThemeOf`; `just generate`; tree clean afterwards.
- [ ] 4.3 `. d (internal)` `AGENTS.md`'s list of hand-written Kotlin gains `themes/DaisyThemes.kt`;
  `.opencode/skills/kdaisyui-codegen` names the theme step of `generateComponents`.
- [ ] 4.4 `. d` `docs/upgrading.md`, 0.11.0: `daisyThemeController` gained `theme`; source
  unchanged, binaries compiled against 0.10.x recompile (**How to migrate**: rebuild; optionally
  replace `value = "dark"` in the block by `theme = DaisyTheme.Dark`).

## 5. Gate, adoption and archive

- [ ] 5.1 Full green: `./gradlew check` with E2E and its result files asserted on disk,
  `koverVerify`, `checkKotlinAbi`, `:lib:checkComponentApi`, `:doc-snippets:compileSnippets`,
  `:lib:pitest`, `./gradlew :lib:testCodegen`, `just generate` leaving the tree clean (what
  `generated-sources-drift` runs),
  StrykerJS on the added `codegen/src/` lines with no survivor, `analyze_change_set`. The
  evaluation says that no mutation tool covers `e2e-tests` or `example-app`.
- [ ] 5.2 `openspec validate select-a-theme-in-the-profile --strict`.
- [ ] 5.3 The evaluation under `tmp/select-a-theme-in-the-profile/` for Oliver to adopt.
- [ ] 5.4 After adoption: archive on this branch, `. d (internal)`, the change's `tmp/` deleted.
