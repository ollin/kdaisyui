## Context

See proposal.md, Why. DaisyUI 5 emits each compiled theme as custom properties under
`:root:has(input.theme-controller[value=<name>]:checked),[data-theme=<name>]`
(`daisyui/packages/daisyui/functions/pluginOptionsHandler.js:27-31`). The example app compiles
its stylesheet with the kdaisyui plugin's `kdaisyuiStylesheet` task from `example-app/src/main/css/app.css`,
which today writes `@plugin "daisyui";` and so gets `light` and `dark` only. The app sends
`default-src 'self'; img-src 'self' data:`. Its avatar menu (`DashboardShell.kt:89`) has a
"Profile" `a` with no `href`.

## Goals / Non-Goals

**Goals:**

- A consumer names a theme with the compiler's help and reads a stored one back safely.
- The example app shows the whole pattern: compile the themes, pick one with a preview, persist
  it, render it - with no script.

**Non-Goals:**

- A live page-wide preview, custom themes, a picker component or a Ktor helper in the library
  (proposal, Out of scope).

## Decisions

### 1. The enum is generated from `src/themes/*.css`, not hand-written

**Chosen:** a codegen step lists the theme files of the daisyui submodule through `submodules.ts`
and writes `DaisyTheme.kt`, sorted by name.

**Rejected: a hand-written enum in `lib/src`.** It is 35 lines today and wrong the day a bump
adds a theme, with nothing to notice; generated, `generated-sources-drift` notices.

**Rejected: reading `functions/themeOrder.js`.** Its order (light, dark first) is nicer for a
picker, but it is JavaScript, and reading it means a regex over source code - the kind of
inference `AGENTS.md` keeps out of the generators. The directory listing is the same set (A1);
an application orders its picker itself.

### 2. A package of its own, `io.github.ollin.kdaisyui.themes`

**Chosen:** the generated enum and the hand-written `DaisyThemes.kt` live in `…kdaisyui.themes`,
the way `…kdaisyui.icons` holds a generated set and two support files.

**Rejected: `…kdaisyui.components`,** where consumers already have a wildcard import. The Gradle
plugin reads every class under that package as a component, and one it does not know makes it
write the full class list (A4) - the example app's stylesheet would silently stop being narrow.

**Rejected: an entry in `kdaisyui-components.txt`** for the enum with no CSS classes. It would
work, but it makes the map describe something that is not a component, to keep a package choice.

### 3. The setter and the parser are hand-written in `lib/src`

**Chosen:** `Tag.daisyTheme(theme)` and `daisyThemeOf(value)` in `DaisyThemes.kt`, with a unit
test, inside `:lib:pitest`'s scope.

**Rejected: emitting them from the generator.** Two functions that never change with DaisyUI do
not need a generator; generated, they would be tested only by a generated test, and mutation
testing would not reach them.

**Rejected: `DaisyTheme.Companion.of(value)`.** A companion must be emitted with the enum, which
puts behaviour back into the generator (above).

### 4. The preview is `data-theme` on a card, not the theme controller

**Chosen:** each choice is a `label` wrapping a plain `daisyRadio` and a card that carries
`daisyTheme(theme)` and paints `bg-base-100`, `bg-primary`, `bg-secondary`, `bg-accent`,
`bg-neutral` swatches.

**Rejected: `daisyThemeController` radios.** A checked `.theme-controller` re-themes `:root`
(selector above), which is the live preview Oliver did not choose; it also leaves the page
re-themed by a choice that was never saved.

### 5. Persist in a cookie, redirect after POST

**Chosen:** `POST /profile/theme` with a `theme` form field; `daisyThemeOf` validates it, the
response sets `theme=<value>; Path=/; SameSite=Lax; HttpOnly; Max-Age=31536000`, or deletes the
cookie for "system", and answers `303 See Other` to `/profile`. Pages read the cookie into
`html { daisyTheme(…) }`.

**Rejected: an in-memory profile store** - a second source of truth for a demo with no users.
**Rejected: localStorage** - needs a script file and paints the default theme first.

### 6. "System default" writes no attribute

With no `data-theme`, DaisyUI's `light --default` applies, and `dark` under
`prefers-color-scheme: dark` - which `themes: all` keeps (`pluginOptionsHandler.js:42-50`). So the
system choice is the absence of the attribute, not a theme.

### 7. The controller's theme is a model `extra`, not an overload

**Chosen (Oliver, 2026-11-10, A7):** `codegen/model/theme-controller.json` declares
`{ "kind": "extra", "name": "theme", "type": "DaisyTheme?", "default": "null",
"apply": "if (theme != null) this.value = theme.value" }` with the enum's import, the shape of
`button.json`'s `type`. It is source-compatible; the JVM signature changes, so a consumer
recompiles, which `docs/upgrading.md` says.

**Rejected: an overload `daisyThemeController(theme: DaisyTheme, …)`** that would keep the old
signature. The model declares one function per name; teaching the generators overloads for one
parameter is a larger change than the binary break of a pre-1.0 library that ships a migration
note anyway.

**Rejected: no parameter** (the recommendation at proposal). `value = DaisyTheme.Dark.value` in
the block is already typed, and the common toggle uses `daisyToggle` with
`daisyThemeControllerClasses()`; Oliver chose the parameter for discoverability.

## Enablers and desired changes

- **Enablers** (behaviour-preserving, provable by diff): none. Every step adds behaviour.
- **Desired changes:** the enum and its generator; the two functions; the controller's parameter; `themes: all` in the entry;
  the profile page, its POST, the shell's attribute; the docs.

## Risks / Trade-offs

- [The stylesheet grows by every theme, ~30 KB minified (A3)] → the example app offers all on
  purpose; the how-to shows naming fewer.
- [`DaisyTheme` offers themes a consumer's stylesheet lacks] → the how-to says so; finding F1
  proposes a plugin check.
- [A theme renamed in a DaisyUI bump becomes a binary-breaking enum change] → `api-baseline`
  shows it, and `kdaisyui-daisyui-upgrade` routes it to `docs/upgrading.md`.
