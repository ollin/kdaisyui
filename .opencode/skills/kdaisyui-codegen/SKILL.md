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
build with `ENOENT`. **That ceiling is gone**; the fallback removed it and the project runs
5.7.16.

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
