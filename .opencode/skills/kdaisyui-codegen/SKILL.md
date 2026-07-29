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
| `generateComponents` | `codegen/src/index-new.js` | `lib/build/generated/sources/kotlin/main/io/github/ollin/kdaisyui/components/` |
| `generateHeroicons` | `codegen/src/index-heroicons.js` | `…/kotlin/main/io/github/ollin/kdaisyui/icons/` |
| `generateComponentTests` | `codegen/src/test-generator.js` | `lib/build/generated/sources/kotlin/test/…` |

```
compileKotlin      dependsOn generateComponents, generateHeroicons
compileTestKotlin  dependsOn generateComponentTests
```

So **any build regenerates**. `just generate` is a *separate* npm path
(`cd codegen && npm install && npm run generate`) and is not required for a normal build.

Submodules are pinned and checked out automatically: `checkoutDaisyuiTag` and
`checkoutHeroiconsTag` read `daisyui.version` / `heroicons.version` from `gradle.properties`
and check out the matching `v<version>` tag.

## DaisyUI version ceiling — read before bumping `daisyui.version`

`daisyui.version` in `gradle.properties` is capped at **5.5.20**, and the cap is a codegen
constraint, not caution:

| Ceiling | Value |
|---|---|
| newest git tag | v5.7.4 |
| newest published webjar | 5.7.0 |
| **newest usable codegen input** | **5.5.22** |

DaisyUI deleted the static `packages/docs/static/llms.txt` in commit `f00802cc` (2026-05-21)
and replaced it with a generated SvelteKit route,
`packages/docs/src/routes/llms.txt/+server.js`. The first release without the file is
**5.5.23**. `codegen/src/parser/llms-txt.js` resolves that static path at module level and
dies with `ENOENT` the moment it is missing:

```
Error: ENOENT: no such file or directory,
  open '…/daisyui/packages/docs/static/llms.txt'
  at parseLlmsTxt (codegen/src/parser/llms-txt.js:18)
```

`renovate.json` therefore pins `org.webjars.npm:daisyui` to `<=5.5.22`. **Lift that cap only
together with a reworked parser**, otherwise the build goes red on the next Renovate PR.

To actually go upstream, the parser needs a new source. Options, in order of how well they
preserve build determinism:

1. Aggregate the same docs sources that DaisyUI's `+server.js` reads (69 lines, present in the
   submodule) — stays offline and reproducible
2. Parse `skills/daisyui/SKILL.md`, which upstream still ships
3. Fetch `llms.txt` over HTTP at build time — **rejected by default**: it makes the build
   network-dependent and non-reproducible, which is exactly what the single-source-of-truth
   setup exists to prevent

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

`lib/build/generated/**` is build output and is overwritten on every compile. Change the
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

## Dead files — do not edit, do not revive

`codegen/src/index.js`, `generator.js` and `classify.js` are superseded by the `*-new.js` pair.
Nothing references them: `lib/build.gradle.kts` invokes only `index-new.js`,
`index-heroicons.js` and `test-generator.js`, and `test-generator.js` imports `./classifier.js`
— not `classify.js`. They are pending deletion; editing them has no effect.

Delete them and this section together. `codegen/src` is a declared input of the generate tasks,
so a `:lib:generateComponents` run after the deletion proves nothing depended on them.

## Verifying a codegen change

1. `mcp_Gradle_gradle` → `:lib:compileKotlin` (regenerates and compiles), or run configuration
   `kdaisyUI [:lib:generateComponents]`
2. `:lib:test`
3. Inspect the produced file under `lib/build/generated/…` — read only, never edit
