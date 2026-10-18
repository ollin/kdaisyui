---
name: kdaisyui-codegen
description: >-
  Changing how kdaisyui components, icons, component tests or the docs/reference pages are
  generated - the committed component model in codegen/model/, its naming rule and completeness
  gate, the five generator entry points, adding a DaisyUI component, or fixing a wrong
  class-to-Kotlin mapping. NOT for merely using the generated components.
---

# kdaisyui — Codegen

All components, the Heroicons wrappers, most component tests, every page under `docs/reference/`
and `llms.txt` are **generated**. Since `declare-the-component-model` (0.8.0) the component API is
**declared** in `codegen/model/`, one JSON file per DaisyUI component directory, and nothing about
it is inferred from DaisyUI's prose or example markup.

## What reads what

```
codegen/model/<dir>.json  ─┐   elements, receivers, functions, axes, toggles, attributes,
                           │   extras, join items, summaries, declared stems, notOffered
DaisyUI frontmatter ───────┤   the class list (for the completeness gate) and descriptions (KDoc)
                           ▼
              model.ts  shapeFromModel  →  component-shape.ts (the shape both emitters render)
                           ├→ generator-new.ts       → lib/generated/main/**   (Kotlin)
                           ├→ generator-docs.ts      → docs/reference/**       (Markdown)
                           ├→ generator-llms.ts      → llms.txt                (from llms-template.md)
                           └→ component-api-dump.ts  → lib/api/components.api  (explicit update only)
test-generator.ts  ← the model's bindings + DaisyUI's documented examples as test input
                   → lib/generated/test/**
daisyui-delta.ts   ← two DaisyUI tags, for `just daisyui-delta`; never on the generation path
```

The test generator is the one reader of DaisyUI's fenced examples during generation: each example
becomes a call against the declared API, and the expected classes are the ones DaisyUI wrote, so
a wrong model fails a test rather than agreeing with itself.

| Gradle task | Entry point | Output |
|---|---|---|
| `generateComponents` | `codegen/src/index-new.ts` | components + `kdaisyui-classes.txt` |
| `generateHeroicons` | `codegen/src/index-heroicons.ts` | icons |
| `generateComponentTests` | `codegen/src/test-generator.ts` | component tests |
| `generateHeroiconTests` | `codegen/src/test-generator-heroicons.ts` | icon tests |
| `generateReferenceDocs` | `codegen/src/index-docs.ts` | `docs/reference/`, `llms.txt` |

All five declare `codegen/model` as an input. A new generator that reads the model must too, or a
model edit leaves it UP-TO-DATE — that happened once, between the switch and the fix.

The output is **committed**; compiling never regenerates. `just generate` is the only way, and
CI's `generated-sources-drift` fails any commit whose output no longer matches its inputs.

## The model file

```json
{
  "component": "button",
  "name": "Button",
  "class": "btn",
  "enums": [
    { "name": "ButtonColor", "label": "Color variants", "classes": ["btn-neutral", "btn-primary", "…"] },
    { "name": "ButtonStyle", "label": "Style variants", "classes": ["btn-outline", "btn-dash", "…"] }
  ],
  "functions": [
    {
      "kind": "main", "name": "daisyButton", "stem": "Button",
      "receiver": "FlowContent", "element": "BUTTON", "class": "btn", "text": true,
      "parameters": [
        { "kind": "enum", "name": "color", "enum": "ButtonColor", "doc": "Color variant" },
        { "kind": "toggle", "name": "active", "class": "btn-active" },
        { "kind": "extra", "name": "disabled", "type": "Boolean", "default": "false",
          "apply": "if (disabled) { this.disabled = true; addClassNames(\"btn-disabled\") }",
          "writes": ["btn-disabled"] }
      ],
      "joinItem": true
    }
  ],
  "summary": { "summary": "Actions and triggers" }
}
```

- **`kind`**: `main`, `part`, or `custom` for a construction method such as `daisyModalPopover`.
- **`parameters`** are only the function's own, between `id` and the escape hatches; `text`, `id`,
  `extraClasses`, `attrs` and `content` are added by `functionParameters`. `content` is absent for
  an HTML void element — a rule of HTML, derived, never configured.
- **`always`**: classes written on every call beside `class` (`daisySkeletonText`).
- **`attributes`**: written on every call (`popover`, `role="tablist"`).
- **`role`, `inputType`**: main function only.
- **`writes`** on an extra: the class its fragment emits, so the completeness gate counts it.
- **`joinScope`** on the join, **`joinItem`** on a main function: the join scope's members.
- **`notOffered`**: frontmatter classes no function offers, each with its reason.
- A skipped directory is `{ "component": "x", "skip": "configured-skip" }`.

Keep the layout: a value that fits in 120 columns stays on one line. `codegen/test/committed-model.test.ts`
reads every file in the unit tests, so a broken one fails without the submodule.

## The naming rule, enforced at generation

- A main function or part is `daisy` + its stem, the PascalCase of the class its element carries:
  `stats` → `daisyStats`, `stat-title` → `daisyStatTitle`. Two classes are not the component's
  name and declare a `stem`: `btn` → `Button`, `cally` → `Calendar`. A construction method's name
  is declared.
- An axis is named after what it chooses: `color`, `size`, `style`, `direction`, `placement`
  where DaisyUI's category word says so; otherwise a word such as `side`, `align`, `shape`,
  `width`, `presence`. Never `variant` or `modifier`.
- An enum is the function's stem + the axis: `ButtonColor`, `StepColor`, `IndicatorItemVerticalPlacement`.
- An entry is the class suffix after the owning function's class, else after the component's:
  `StepColor.Primary` for `step-primary`, `IndicatorItemHorizontalPlacement.Start`.
- A toggle is the class suffix in camelCase: `zebra` for `table-zebra`.

`namingFindings` fails generation for functions and enums named otherwise.

## The completeness gate

`model-completeness.ts`, run by `index-new.ts`. Generation fails, naming component and class, when
a frontmatter class is placed nowhere, the model places a class the frontmatter lacks, one
function offers one class twice, or directories and model files do not match. A class on several
functions is fine: `modal` is the class of two constructions, `timeline-box` a toggle on three parts.

## Where to change what

| Want | Edit |
|---|---|
| a component's element, parts, parameters, names, attributes | its model file |
| a class group as one choice, or a class as an on/off toggle | its model file: `enum` vs `toggle` |
| a new DaisyUI component | a new model file; generation fails until it exists |
| a reference page's one-line description | `summary` in the model file |
| the shape every function shares | `component-shape.ts` (`functionParameters`) |
| how Kotlin says it | `generator-new.ts` |
| how a reference page says it | `generator-docs.ts` |
| the generated tests | `test-generator.ts` |
| icons | `generator-heroicons.ts` |

## Two API baselines

| File | Sees | Updated by |
|---|---|---|
| `lib/api/lib.api` | JVM descriptors | `:lib:updateKotlinAbi` |
| `lib/api/components.api` | lambda receivers, parameter names, defaults | `:lib:updateComponentApi` |

Neither is part of `just generate`, on purpose: a baseline rewritten by the regeneration command
would follow every change in silence. `components.api` still earns its keep beside the model: a
change to generator code can move a signature with no model edit.

## The codegen is TypeScript run directly by Node

No build step, no emitted JavaScript, nothing type-checks it. Stay inside erasable syntax or Node
refuses the file:

| Do not use | Instead |
|---|---|
| `enum` | a union of string literals, or `as const` |
| `namespace` with runtime code | a module |
| constructor parameter properties | an explicit field assigned in the constructor |
| decorators, import aliases, `.tsx` | — |

`import type` is mandatory for type-only imports, and `.ts` is mandatory in import specifiers.

One dependency, `htmlparser2`, pinned exactly: the test generator and the bump report read
DaisyUI's marked examples with it. A second dependency is a decision, not a habit.

## Verifying a codegen change

1. `cd codegen && node --test test/` — Node, not only Bun: Bun accepts syntax Node refuses.
2. Regenerate (`just generate`, or the four `node src/…` entry points) and read
   `git status -- lib/generated docs/reference llms.txt`. A refactoring shows no diff.
3. `./gradlew :lib:test` whenever generated Kotlin changed, and compile `:example-app` and
   `:e2e-tests` when a public name changed.
4. `just update-api` when a signature moved; read both diffs; a breaking one needs a
   **How to migrate** entry in `README.md`.

`mcp_Tcr` run on `codegen/` executes the Bun suite only. Run steps 1 and 2 before it, and step 3
by hand for anything that changes generated Kotlin: from the repository root it runs DaisyUI's
own submodule tests and reverts on their failures.
