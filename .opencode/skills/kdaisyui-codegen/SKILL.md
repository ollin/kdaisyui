---
name: kdaisyui-codegen
description: >-
  Changing how kdaisyui components, icons or component tests are generated - the codegen-config.json
  knobs, the classifier, the three generator entry points, adding a DaisyUI component, or fixing a
  wrong CSS-class-to-Kotlin mapping. NOT for merely using the generated components.
---

# kdaisyui — Codegen

All components, the Heroicons wrappers and most component tests are **generated**. Hand-written
Kotlin exists only in `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/` (`ClassNames.kt`,
`TagId.kt`) and `ktor-integration/`.

## Generation is wired into the build — you rarely run it by hand

From `lib/build.gradle.kts`:

| Gradle task | Entry point | Output |
|---|---|---|
| `generateComponents` | `codegen/src/index-new.js` | `lib/generated/main/kotlin/io/github/ollin/kdaisyui/components/` |
| `generateHeroicons` | `codegen/src/index-heroicons.js` | `lib/generated/main/kotlin/io/github/ollin/kdaisyui/icons/` |
| `generateComponentTests` | `codegen/src/test-generator.js` | `lib/generated/test/kotlin/io/github/ollin/kdaisyui/components/` |

That output is **committed**, and **compilation does not depend on these tasks**. A clone
builds and tests with no Node, no npm and no git submodules; only regeneration needs them:

```
just generate      # all three tasks, then shows the resulting diff
```

What keeps the committed output honest is CI's `generated-sources-drift` job: it regenerates
and fails if `lib/generated` changed. So the loop is regenerate → review the diff → commit it.

So **no build regenerates** — `just generate` is the only way, and it drives the same three
Gradle tasks rather than a separate npm path.

Submodules are pinned and checked out automatically: `checkoutDaisyuiTag` and
`checkoutHeroiconsTag` read `daisyui` / `heroicons` from `gradle/libs.versions.toml` and check
out the matching `v<version>` tag.

## Where the element and class data come from

`codegen/src/parser/llms-txt.js` reads two sources, in this order:

1. `daisyui/packages/docs/static/llms.txt` — **gone since DaisyUI 5.5.23**, which deleted it
   in favour of a generated SvelteKit route.
2. `daisyui/skills/daisyui/components/*.md` — the fallback, and what is actually used today.

There used to be a hard 5.5.20 ceiling because only (1) existed and its absence killed the
build with `ENOENT`. **That ceiling is gone** — the fallback removed it. The version actually
pinned is in `gradle/libs.versions.toml`; do not restate it here.

The remaining constraint is unrelated to the parser: DaisyUI must stay at a version with a
**published Maven webjar** (`org.webjars.npm:daisyui`), because `:example-app` serves the CSS
from it. Only a subset of releases gets one, so the newest git tag is usually ahead of the
newest usable version.

**The element choice is a heuristic, and it can be wrong.** `findComponentInSyntax` takes the
first element in a component's `#### Syntax` block that carries a matching class. When DaisyUI
documents several variants, the first one wins — even if it only works with attributes the
generator cannot emit. That happened to `dropdown` at 5.7.16: the popover-API variant
(`<ul class="dropdown" popover id=… style="position-anchor:…">`) is listed first, and picking
it produced a dropdown that renders but cannot open.

The remedy is per-component and explicit, not a smarter heuristic:

```json
"componentElements": { "dropdown": "details" }
```

Watch for this on every DaisyUI bump — the committed diff under `lib/generated/` is where it
becomes visible, which is the main reason that tree is committed at all.

## Bumping the DaisyUI version — read the CHANGELOG first

Whenever `daisyui` (or `heroicons`) moves in `gradle/libs.versions.toml`, read
`daisyui/CHANGELOG.md` **back to the version currently pinned**, not just the release notes of
the single version Renovate offers. Renovate shows one entry; a bump usually spans dozens, and
the `Features` headings are the only ones that matter — `Bug Fixes` are CSS-internal and reach
us through the webjar without touching the API.

**Do not read it to find new classes.** The generator derives every class from the submodule at
the pinned tag, so regeneration plus `generated-sources-drift` catches all of them mechanically.
Verified at 5.7.17: `menu-paged` (new in 5.7.0), `btn-active` (5.6.1), `range-vertical` and
`tooltip-start|center|end` (5.6.0) were all present without anyone reading a changelog.

Read it for what the generator **structurally cannot see**:

| Changelog says | Why codegen misses it |
|---|---|
| a new way to *construct* a component (different tag or attributes) | one element per component, chosen by `findComponentInSyntax` |
| a class became responsive-prefixable | booleans emit a fixed class; `md:` needs `extraClasses` |
| a component gained a new container element | same one-element limit |
| a doc page appeared or vanished | may need a `skip` or `componentElements` entry |

Worked example: 5.6.0 added HTML-popover modals (`<div class="modal" popover>` driven by
`popovertarget`), which the docs list as method 2 of 4 and **not** legacy. `daisyModal` emits
`<dialog>` only, so that method was unreachable — and no test failed and no drift appeared,
because nothing about it is a class name. Only the changelog surfaced it. It went unnoticed long
enough to be archived as delivered; `daisyModalPopover` and `staticAttributes` (below) are the
fix. **`megamenu` still has it**, so the example is live, not historical.

Record what you find as an issue or an OpenSpec change; do not fold it into the dependency PR.

## Component shape

Every generated component follows this shape:

```kotlin
fun FlowContent.daisyButton(
    text: String? = null,
    id: HtmlId? = null,                  // type-safe id from TagId.kt
    variant: ButtonVariant? = null,      // btn-primary, btn-secondary, …
    size: ButtonSize? = null,            // btn-sm, btn-lg, …
    outline: Boolean = false,            // plain modifier → btn-outline
    extraClasses: String? = null,        // escape hatch: raw classes
    attrs: (BUTTON.() -> Unit)? = null,  // escape hatch: raw tag access
    content: (BUTTON.() -> Unit)? = null,
)
```

CSS class → Kotlin: `btn-primary` → `ButtonVariant.Primary`; plain modifiers become booleans.
Consumers never hardcode class strings — they use the enums, or `extraClasses` when nothing fits.

## Never edit generated files

`lib/generated/**` is committed and readable — deliberately, so the API and every DaisyUI
bump can be reviewed. It is still not yours to edit: `just generate` overwrites it wholesale,
and CI's `generated-sources-drift` job fails any commit that hand-edited it. Change the
pipeline instead.

## Where to change what

| Symptom | Edit |
|---|---|
| Component needs an extra parameter | `codegen/codegen-config.json` → `extras` |
| Component should take inline text | → `textParams` |
| Component must not accept children | → `noContent` |
| Wrong HTML role or input type | → `roles`, `inputTypes` |
| Component should not be generated at all | → `skip` (currently `accordion`, `pagination`) |
| Component needs a second wrapper / an alternative construction method | → `customParts` |
| CSS class lands in the wrong category | `codegen/src/classifier.js` |
| Kotlin output shape is wrong | `codegen/src/generator-new.js` |
| Generated tests are wrong | `codegen/src/test-generator.js` |
| Icon output is wrong | `codegen/src/generator-heroicons.js` |

`extras` entries are full code fragments, not flags:

```json
{
  "name": "disabled",
  "type": "Boolean",
  "default": "false",
  "apply": "if (disabled) { this.disabled = true; addClassNames(\"btn-disabled\") }"
}
```

Add an `imports` array when the type is not in the default import set (e.g.
`kotlinx.html.ButtonType`).

## An alternative construction method is a `customParts` entry — not `componentElements`

Some DaisyUI components document more than one way to build them. `modal` is the clearest case:
method 1 is `<dialog class="modal">`, method 2 is `<div class="modal" popover>` opened by a
`popovertarget` button. These are not modifiers of one wrapper — they are different elements —
so no parameter on the `<dialog>` wrapper can produce the second one.

**`componentElements` cannot express this.** It *replaces* a component's single root element, so
using it for modal would trade one method for the other. It is the right tool only when the
heuristic picked the wrong element outright, as with `dropdown`.

`customParts` adds a second generated function alongside the main one, and an entry may carry
`staticAttributes` for attributes that define the method rather than vary per call:

```json
"modal": [
  {
    "name": "Popover",
    "element": "DIV",
    "cssClass": "modal",
    "staticAttributes": { "popover": "" }
  }
]
```

That produces `daisyModalPopover` emitting `<div class="modal" popover>`. An empty value renders
as `popover=""`, which HTML treats as the attribute's default state. The attributes are emitted
before `extraClasses` and before `attrs()`, so a caller can still override one.

`codegen/src/test-generator.js` mirrors the field: each static attribute becomes an assertion in
the generated test. Keep the two in step — a construction method that adds **no CSS class** is
otherwise invisible to the whole safety net, because both the generated tests and
`generated-sources-drift` key on class names. That blind spot is how the popover modal was
recorded as delivered while `daisyModal` still emitted only `<dialog>`.

**`megamenu` has the same unfixed defect today:** DaisyUI documents it as
`<div class="megamenu …" popover>` opened by a `popovertarget` button, and `daisyMegamenu`
emits no `popover`.

## Pipeline

```
daisyui/packages/docs/src/routes/(routes)/components/<name>/+page.md   (YAML frontmatter)
  → codegen/src/parser/frontmatter.js
  → codegen/src/parser/llms-txt.js      (element rules from DaisyUI llms.txt)
  → codegen/src/classifier.js           (colors / sizes / styles / modifiers / parts)
  → codegen/src/generator-new.js
```

Heroicons runs a separate path: `parser/svg-heroicons.js` → `generator-heroicons.js`.

## Verifying a codegen change

1. `mcp_Gradle_gradle` → `:lib:generateComponents` (compiling no longer regenerates — the
   build reads the committed sources), or run configuration `kdaisyUI [:lib:generateComponents]`
2. `:lib:test`
3. Inspect the produced file under `lib/generated/…` — read only, never edit
4. Review `git diff -- lib/generated` and commit it. An unreviewed regeneration diff is the
   thing this layout exists to prevent, and CI fails if you leave it uncommitted
