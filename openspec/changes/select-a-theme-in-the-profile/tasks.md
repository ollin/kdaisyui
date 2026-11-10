# Tasks

**Estimate, frozen 2026-11-10:** `4h 30min (8h 30min DevOnly)` by the ledger's `forecast`
(ratio 0.52 over 168 runs); 510 minutes developer-only is the frozen raw figure. Block 1: 30,
block 2: 210, block 3: 150, block 4: 60, block 5: 60. Ledger run
`2026-11-10-kdaisyui-select-a-theme-in-the-profile`.

Ordered by uncertainty: A3 is the one thing only a compile can answer, so it runs before any
code; the browser checks of A2, A3 and A5 are the scenarios of 3.3. Every step in block 3 carries
its own scenario, so root TCR, which runs the E2E suite, sees test and code together.

Literals this change replaces, grepped 2026-11-10: `@plugin "daisyui";` occurs once,
`example-app/src/main/css/app.css:19` (task 3.3); `li { a { +"Profile" } }` once,
`example-app/src/main/kotlin/kdaisyui/example/DashboardShell.kt:89` (task 3.8). No generator or
test hard-codes either.

## 1. Open assumptions

- [ ] 1.1 A3: in a scratch copy under `tmp/select-a-theme-in-the-profile/`, change the entry to
  `@plugin "daisyui" { themes: all; }`, run `./gradlew :example-app:kdaisyuiStylesheet`, and
  record the minified size against today's 150,330 bytes and that `[data-theme=nord]` occurs in
  the output. No commit of the CSS here (it lands with its scenario in 3.3); the proposal's A3 row
  is updated in its own `. d`. If the compile fails, revise the change before block 2.

A7 is Oliver's, asked at review; A8 he decided on 2026-11-10.

## 2. The library API, under TCR

- [ ] 2.1 `^ F (internal)` `codegen/src/themes.ts`: `themeNames(directory)` returns the stems of
  `*.css` in a directory, sorted; its `node --test` reads a fixture directory of three files and
  one non-CSS file.
- [ ] 2.2 `^ F (internal)` `renderDaisyThemeEnum(names)` returns the Kotlin source of
  `DaisyTheme` in `io.github.ollin.kdaisyui.themes`, the GENERATED header the other outputs carry,
  one entry per name as `Nord("nord")`; asserted for two names.
- [ ] 2.3 `! F` `index-new.ts` (the `generateComponents` entry point, so `just generate` and the
  drift job need no new task) writes `lib/generated/main/kotlin/io/github/ollin/kdaisyui/themes/DaisyTheme.kt`
  from the submodule's `packages/daisyui/src/themes` via `submodules.ts`, and the task in
  `lib/build.gradle.kts:254`, whose output is the components directory today, declares the themes
  directory as an output too; `just generate`; the
  35-entry file is committed and the working tree is clean afterwards. `!` because the generated
  file counts ~40 lines; nothing calls it yet.
- [ ] 2.4 `^ F` `lib/src/main/kotlin/io/github/ollin/kdaisyui/themes/DaisyThemes.kt`:
  `Tag.daisyTheme(theme)` writes `data-theme`; a test in `lib/src/test/…/themes/` renders
  `html { daisyTheme(DaisyTheme.Nord) }` and asserts `data-theme="nord"`.
- [ ] 2.5 `^ F` `daisyThemeOf(value)`: the test asserts `"nord"` gives `Nord`, `"Nord"` and `""`
  give `null`, and every entry round-trips (which also executes every enum entry for Kover).
- [ ] 2.6 `. F (internal)` `lib/build.gradle.kts`: `targetClasses` gains
  `io.github.ollin.kdaisyui.themes.*`; `./gradlew :lib:pitest` reports 100% test strength.
- [ ] 2.7 `. d (internal)` `./gradlew apiDump`; the diff of `lib/api/lib.api` is read and holds
  additions only (`DaisyTheme`, `DaisyThemesKt`); `components.api` unchanged.

## 3. The profile page and its scenarios, under TCR at the root

- [ ] 3.1 `^ F` `@Resource("/profile") class ProfilePage` in `DashboardResources.kt`, a route in
  `DashboardRoutes.kt`, and `ProfilePage.kt` rendering `html` with `pageAssets()` and an `h1`;
  a new `profile.feature` asserts status 200 and the heading, in the shape of
  `popover_modal.feature:4-7`.
- [ ] 3.2 `^ F` A `form method=post` with one `daisyRadio(name = "theme")` per `DaisyTheme` and
  one `value="system"`, plus a Save `daisyButton`; ids in `Ids.kt`; the scenario counts 36 radios.
- [ ] 3.3 `^ F` Each theme's radio sits in a `label` with a card carrying `daisyTheme(theme)` and
  the five swatches (design, decision 4), and `app.css` says `themes: all`; the scenario asserts,
  for every card, that its computed `--color-base-100` differs from the page's unless the card is
  `light` (A2, A3, A5 in a browser). If any card computes the default, revise the change first.
- [ ] 3.4 `^ F` `POST /profile/theme` with a theme sets the cookie and answers 303 to `/profile`
  (design, decision 5); the scenario chooses `nord`, saves, and asserts the URL and the cookie.
- [ ] 3.5 `^ F` A value `daisyThemeOf` refuses answers 400 and sets no cookie; an endpoint scenario.
- [ ] 3.6 `^ F` `dashboardShell` and the profile page write `html { daisyTheme(…) }` from the
  cookie, and the profile checks the stored choice; the scenario saves `nord`, opens `/`, and
  asserts `html[data-theme=nord]` and the body's computed background equals nord's base-100.
- [ ] 3.7 `^ F` `system` deletes the cookie; the scenario saves `nord`, then "System default",
  and asserts `html` has no `data-theme`.
- [ ] 3.8 `^ F` The avatar menu's "Profile" links to `href(ProfilePage())`; the scenario opens the
  menu, clicks it, and lands on the profile.

## 4. Docs

- [ ] 4.1 `. d` `docs/how-to.md`, "Let users choose a theme": the entry's `themes` line and naming
  fewer; the webjar's `themes.css` carrying all; `html { daisyTheme(…) }`; the preview card;
  the POST route (`<!-- snippet: in Route -->`); that `DaisyTheme` names every built-in theme
  while the stylesheet decides which exist; a custom theme as `attributes["data-theme"]`.
- [ ] 4.2 `. d` `codegen/llms-template.md` names the recipe, `codegen/llms-full-template.md`
  describes `DaisyTheme`, `daisyTheme`, `daisyThemeOf`; `just generate`; tree clean afterwards.
- [ ] 4.3 `. d (internal)` `AGENTS.md`'s list of hand-written Kotlin gains `themes/DaisyThemes.kt`;
  `.opencode/skills/kdaisyui-codegen` names the theme step of `generateComponents`.

## 5. Gate, adoption and archive

- [ ] 5.1 Full green: `./gradlew check` with E2E and its result files asserted on disk,
  `koverVerify`, `:doc-snippets:compileSnippets`, `:lib:pitest`, `./gradlew :lib:testCodegen`,
  StrykerJS on the added `codegen/src/` lines with no survivor, `analyze_change_set`. The
  evaluation says that no mutation tool covers `e2e-tests` or `example-app`.
- [ ] 5.2 `openspec validate select-a-theme-in-the-profile --strict`.
- [ ] 5.3 The evaluation under `tmp/select-a-theme-in-the-profile/` for Oliver to adopt.
- [ ] 5.4 After adoption: archive on this branch, `. d (internal)`, the change's `tmp/` deleted.
