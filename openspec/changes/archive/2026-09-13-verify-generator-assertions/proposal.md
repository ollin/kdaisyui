# Verify the generator's assertions instead of trusting them

## Why

Six defects, one shape: **the generator states something it could have checked, and what it
states is wrong.** None is caught by any gate, because none changes a CSS class — and every gate
in this repository keys on class names.

**1. Three components demand children from an element that cannot have them.**
`daisyFileInput`, `daisyThemeController` and `daisyMask` each declare a **required** trailing
`content` lambda on a void element — two `<input>`, one `<img>`. HTML forbids children there, so
the lambda's contents cannot legally render.

The cause is a hand-maintained list. `codegen-config.json` → `noContent` is read with
`componentName.toLowerCase()`, but two of its eight entries are written as DaisyUI directory
names: `file-input` is read as `fileinput`, `theme-controller` as `themecontroller`. Neither has
ever matched. `mask` fails worse — it renders `<img>` and was simply never added, which a
hand-maintained list has no mechanism to prevent.

**2. Three components render an element DaisyUI does not document.** `daisyOtp` emits `<div>`
where the docs show `<label>` — as a `<label>` a click focuses the wrapped input, as a `<div>` it
does not. `daisyTab` emits `<button class="tabs">`, where `tabs` is the *container* class and the
docs show `<div>`. `daisyCalendar` emits `<div>` where the docs show a custom element. All three
render, and none does what it should. Found by the cross-check this change introduces.

**3. Ten generated files cite a source that does not exist.** `FileInput.kt` says
`// Source: …/components/fileinput/+page.md`; the directory is `file-input`. The header
lower-cases the PascalCase name instead of using the directory name already passed in.

**4. Two doc comments name something that is not an HTML element.** `Fieldset.kt` says
`Renders <fieldSet …>`, `Textarea.kt` says `<textArea …>` — kotlinx.html *builder* names. The
reference pages already get this right; the Kotlin emitter still conflates the two.

**5. Nothing notices a config entry that is never read.** That is what let defect 1 survive, and
the same failure mode left `subComponentElements` unread for months.

**6. The API baseline cannot see a changed lambda receiver.** `lib/api/lib.api` is a dump of JVM
descriptors, which carry neither receiver types nor parameter names nor default values: both of
`daisyOtp`'s lambdas appear as `Lkotlin/jvm/functions/Function1;`. So defect 2's fix is a
source-breaking change that the designated "did the API change" artifact reports as no change at
all — while the generated Kotlin that *does* show it is `linguist-generated` and collapsed by
default in review. Invisible where people look, hidden where it is visible.

## What Changes

- **`noContent` is deleted** and the rule derived: a component whose element is an HTML void
  element takes no `content` parameter. Subtraction — the fix removes the thing that was wrong.
- **The generator cross-checks its chosen element against DaisyUI's own documentation** and fails
  on disagreement. The authority is the fenced ```html examples in each component's `+page.md`,
  where daisyUI class names carry a `$$` marker, so the tag bearing `$$<componentClass>` is the
  documented root. Measured: this route has an opinion for **all 66** components.
- **`componentElements` gains `otp: label`.** `tab` and `calendar` get entries in a new, reasoned
  exception list rather than a rushed fix — see Impact.
- **`ComponentSource.componentDir` supplies the `// Source:` header**; **`FunctionShape.htmlTag`
  supplies doc-comment prose** while `tagBuilder` keeps emitting code.
- **The generator fails on a config entry no lookup consumed**, and on an exception entry that is
  no longer needed.
- **A Kotlin-level API baseline** is dumped from `ComponentShape` into `lib/api/components.api`,
  updated only by an explicit task and checked in CI — carrying receiver types, parameter names,
  order and defaults, the three things the JVM descriptor dump erases.

## Capabilities

- `daisyui-component-coverage` — a generated function must render the element DaisyUI documents,
  and must not require content that element cannot hold.
- `generated-sources` — a generated file's attribution must resolve; configuration that is never
  read is an error; and the Kotlin-level API surface is a committed, explicitly-updated artefact.

## Impact

- **BREAKING, four functions.** `daisyFileInput`, `daisyThemeController` and `daisyMask` lose a
  required trailing lambda; `daisyOtp`'s `attrs` and `content` receivers change from `DIV` to
  `LABEL`. Needs a **How to migrate** entry in `README.md`. The `otp` receiver change produces no
  `lib/api/lib.api` diff — which is defect 6, and why `lib/api/components.api` arrives here.
- **Deferred with a reason, not silently**: `tab` needs its component/part classification sorted
  out before its element can be corrected, and `calendar` needs a decision about custom elements
  that kotlinx.html cannot express. Both get an exception entry naming the issue that tracks them,
  so the cross-check lands green and neither defect is invisible again.
- **Rewritten**: four components' generated Kotlin and tests, ten `// Source:` headers, two doc
  comments, the corresponding `docs/reference/` pages.
- **Changed by hand**: `example-app/.../WhatsNewFragment.kt` calls `daisyOtp` and follows the
  receiver change; E2E coverage of that page is re-checked.
- **Removed**: the `noContent` config section, eight entries.
- **A new dependency, deliberately.** The codegen takes an HTML parser and gives up its
  zero-dependency property — decided 2026-09-12, because a regex over HTML is unreadable and the
  existing 27 regex sites are debt rather than precedent. Costs an `npm ci` step in CI and in the
  Gradle generator tasks, a real lockfile, and a network requirement during regeneration. Does
  **not** cost the promise that a clone compiles and tests with no Node — `check` never runs a
  generator. See `design.md` decision 0.
