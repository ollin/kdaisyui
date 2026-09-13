---
name: kdaisyui-codegen
description: >-
  Changing how kdaisyui components, icons, component tests or the docs/reference pages are
  generated - the codegen-config.json knobs, the classifier, the five generator entry points,
  adding a DaisyUI component, or fixing a wrong CSS-class-to-Kotlin mapping. NOT for merely using
  the generated components.
---

# kdaisyui — Codegen

All components, the Heroicons wrappers, most component tests **and every page under
`docs/reference/`** are **generated**. Hand-written Kotlin exists only in
`lib/src/main/kotlin/io/github/ollin/kdaisyui/core/` (`ClassNames.kt`, `TagId.kt`) and
`ktor-integration/`.

## Generation is wired into the build — you rarely run it by hand

From `lib/build.gradle.kts`:

| Gradle task | Entry point | Output |
|---|---|---|
| `generateComponents` | `codegen/src/index-new.ts` | `lib/generated/main/kotlin/io/github/ollin/kdaisyui/components/` |
| `generateHeroicons` | `codegen/src/index-heroicons.ts` | `lib/generated/main/kotlin/io/github/ollin/kdaisyui/icons/` |
| `generateComponentTests` | `codegen/src/test-generator.ts` | `lib/generated/test/kotlin/io/github/ollin/kdaisyui/components/` |
| `generateHeroiconTests` | `codegen/src/test-generator-heroicons.ts` | `lib/generated/test/kotlin/io/github/ollin/kdaisyui/icons/` |
| `generateReferenceDocs` | `codegen/src/index-docs.ts` | `docs/reference/` |

The fifth is the only one that is not Kotlin, and the only one writing outside `lib/`. It reads
the same parsed frontmatter and the same classified model as `generateComponents`, which is the
point: a component whose rendered element changes cannot update one without the other.

That output is **committed**, and **compilation does not depend on these tasks**. A clone
builds and tests with no Node, no npm and no git submodules; only regeneration needs them:

```
just generate      # all five tasks, then shows the resulting diff
```

What keeps the committed output honest is CI's `generated-sources-drift` job: it regenerates
and fails if `lib/generated` **or `docs/reference`** changed. So the loop is regenerate → review
the diff → commit it.

So **no build regenerates** — `just generate` is the only way, and it drives the same five
Gradle tasks rather than a separate npm path.

Submodules are pinned and checked out automatically: `checkoutDaisyuiTag` and
`checkoutHeroiconsTag` read `daisyui` / `heroicons` from `gradle/libs.versions.toml` and check
out the matching `v<version>` tag.

## Where the element and class data come from

`codegen/src/parser/llms-txt.ts` reads two sources, in this order:

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
fix. `megamenu` carried the same defect until `support-popover-megamenu`, which added
`componentAttributes` for it — so the example is historical, but the blind spot it came from is
not: nothing in the class-based safety net would catch the next one either.

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

## Three things the generator refuses to be trusted about

Added by `verify-generator-assertions` (2026-09-12). Each of these once shipped a defect that
changed no CSS class — and every other gate in this repository keys on class names, so nothing
could see any of them.

**1. Whether a component may take children is DERIVED from its element, not configured.** An
element the HTML specification calls void cannot hold children, full stop. There is no
`noContent` section any more; do not add one. The list that existed was a transcription of that
rule and was wrong in both directions — two of its eight entries were spelled as directory names
in a section read by PascalCase name, so they never matched, and `mask` renders `<img>` and was
never listed at all. All three demanded a required `content` lambda for an element that cannot
have one.

**2. The element is cross-checked against DaisyUI's own documentation, and a disagreement fails
the run.** The authority is the fenced ```html examples in each component's `+page.md`, where
daisyUI class names carry a `$$` marker — so "the tag bearing `$$<componentClass>`" is a
statement rather than an inference. That route answers all 66 components; the `skills/` Syntax
blocks answer 57.

Three disagreements exist. `otp` was fixed (`componentElements: {"otp": "label"}`). `tab` and
`calendar` carry entries in `elementCrossCheckExceptions`, each with a reason and a tracking
issue, and **a stale exception is itself an error** — when a component stops disagreeing its
entry must go, or the list becomes the hand-maintained thing item 1 deleted.

**3. A config entry that no lookup reads fails the run.** A mis-keyed entry is
indistinguishable from an absent one at run time: the lookup returns `undefined`, the caller's
`?? []` swallows it, and the component is generated as though nothing were configured. A
key-*existence* check would not help — `file-input` is a real DaisyUI directory; the entry was
not a typo, it was unread.

`CONFIG_SECTIONS` in `codegen/src/config-consumption.ts` lists every section and how it is keyed.
**Add a new section there or the guard cannot police it.** Two keying conventions coexist —
`skip`, `componentElements`, `docSummaries` and `elementCrossCheckExceptions` by DaisyUI
directory name, the rest by lower-cased PascalCase name, `subComponentElements` by part class.
That inconsistency is the underlying defect; the guard is what makes deferring its fix safe.

## Two API baselines, answering different questions

| File | Sees | Updated by |
|---|---|---|
| `lib/api/lib.api` | JVM descriptors: added and removed members, arity changes | `:lib:updateKotlinAbi` |
| `lib/api/components.api` | lambda receiver types, parameter names, defaults | `:lib:updateComponentApi` |

JVM descriptors have no representation for a lambda's receiver, for parameter names — which are
part of the API in Kotlin, because callers use named arguments — or for default values. So
`daisyOtp` moving from `DIV` to `LABEL` breaks every caller's lambda body and produces **no**
diff in `lib.api`. Measured twice: it also left the hand-written `example-app` compiling,
because its lambda used only `span { }`.

Both are kept: removing a parameter is visible in the JVM dump and not specially in the other.

**Neither update task is part of `just generate`, and `checkComponentApi` is not part of
`check`.** The first separation is the gate — a baseline rewritten by the command that
regenerates the code would follow every change in silence. The second is the no-Node promise:
`checkComponentApi` reads the submodule and runs Node, so it lives in CI beside
`generated-sources-drift`.

## Never edit generated files

`lib/generated/**` **and `docs/reference/**`** are committed and readable — deliberately, so the
API, its documentation and every DaisyUI bump can be reviewed. Neither is yours to edit: `just
generate` overwrites both wholesale, and CI's `generated-sources-drift` job fails any commit that
hand-edited either. Change the pipeline instead.

`docs/reference/` joined that list on 2026-09-12 after five days in which
`docs/reference/megamenu.md` documented a `DIV` lambda receiver for a function taking a `SPAN`,
and shipped wrong in v0.4.0. Every page carries a `<!-- GENERATED — DO NOT EDIT -->` comment
naming the DaisyUI source it came from.

`docs/reference.md` — the singular file, the hand-written entry point — is **not** generated.
Neither is anything else under `docs/`.

### The one fact on a page that is not derivable

A page's one-line editorial description is hand-written and lives in
`codegen-config.json` → `docSummaries`, keyed by DaisyUI's **directory** name (`file-input`):

```json
"docSummaries": {
  "card": { "summary": "Content containers with body and title" },
  "megamenu": { "summary": "Horizontal menu with popover navigation blocks", "description": "…" }
}
```

`summary` is the index-table cell and the page's opening sentence; the optional `description` is a
longer opening paragraph, used by three components. A component with no entry falls back to the
first sentence of DaisyUI's own `desc` and the generator warns — so a DaisyUI bump is never
blocked, but a new component wants a summary added.

All 66 descriptions were hand-written: **none** matched DaisyUI's `desc`. Do not "simplify" this
away by using the frontmatter text; `index.md` is a 66-row table with a one-cell Description
column and some DaisyUI descriptions run to 48 words.

## The codegen is TypeScript, run directly by Node — no build step

Since 2026-09-11, `codegen/src/**` is `.ts` and Node executes it as-is. Type stripping is
stable and on by default in the Node pinned by `.tool-versions`. There is **no compile step and
no emitted JavaScript**, which is what keeps `just generate` the only thing in the repository
that needs Node at all.

**Zero dependencies ended on 2026-09-12.** `codegen/package.json` now has exactly one:
`htmlparser2`, pinned exactly. The element cross-check reads the markup DaisyUI documents, and a
regex over markup is unreadable — and measurably wrong on a `>` inside an attribute value, on an
HTML comment, and on `<script>` content. The `--save-exact` pin is deliberate: a generator whose
output is drift-checked cannot have a parser that floats.

Consequences to know before touching the build:

- Every task that runs Node against `codegen/` depends on `installCodegenDeps` (`npm ci`), and
  regeneration needs the network on a cold `node_modules`.
- `package-lock.json` is a declared **input** of all five generator tasks and `testCodegen`. Adding
  a generator without it means a parser upgrade leaves Gradle reporting UP-TO-DATE.
- Both Node-running CI jobs read `.tool-versions`; `generated-sources-drift` previously used the
  runner's Node and no longer does.

A second dependency is a decision, not a habit. The next obvious candidate is named rather than
hidden: `parseYamlFrontmatter` is a hand-rolled YAML reader, and YAML is far harder than finding a
start tag.

**Nothing type-checks it.** Node strips types without checking them, and there is deliberately
no `tsc` step. Types serve the editor and the reader; the gates are the codegen unit tests and
`generated-sources-drift`, exactly as before.

### Stay inside erasable syntax, or Node refuses to run the file

Node erases types; it does not transpile. These produce `ERR_UNSUPPORTED_TYPESCRIPT_SYNTAX`
at startup, not a compile warning:

| Do not use | Instead |
|---|---|
| `enum` | a union of string literals, or `as const` |
| `namespace` containing runtime code | a module |
| parameter properties (`constructor(private x)`) | an explicit field |
| decorators, import aliases, `.tsx` | — |

Two more that fail **silently or at runtime**, and cost the most time:

- **`import type { X }` is mandatory for type-only imports.** Without the `type` keyword Node
  treats it as a value import and the module fails to load at runtime.
- **`.ts` extensions are mandatory in import specifiers.** `import './parser/frontmatter'`
  does not resolve; it must be `'./parser/frontmatter.ts'`.

Node ignores `tsconfig.json` entirely, so `paths` aliases are unavailable.

### Where the types actually help

Not uniformly, and it is worth knowing which before adding more. Plain annotations cannot
distinguish two strings — `fieldSet` and `fieldset` are both `string`, which is the very
defect that prompted the port. What pays:

- **Shapes.** Every parser had a JSDoc `@typedef` that nothing checked, and three had already
  drifted from the code — `ClassifiedComponent` documented 13 fields where 14 are returned.
- **Unions.** `ClassCategory = keyof Classnames` makes a mistyped category name unwritable,
  where before it returned `undefined`, got swallowed by `?? []`, and silently dropped every
  modifier in that category.
- **Branded names**, where two string KINDS genuinely coexist: `TagName`/`BuilderName` in
  `html-names.ts`, `KebabName`/`PascalName`, `ComponentName`/`PascalComponentName`. Each costs
  one cast where the value is created. Do NOT brand strings that are merely strings — four
  fragments of one output string gain nothing and add noise.

`html-names.ts` holds the shared vocabulary because brands are **nominal**: two declarations of
`TagName` would be two incompatible types, which is worse than having none.

## Where to change what

| Symptom | Edit |
|---|---|
| Component needs an extra parameter | `codegen/codegen-config.json` → `extras` |
| Component should take inline text | → `textParams` |
| Component must not accept children | **derived, not configured** — see below |
| Wrong HTML role or input type | → `roles`, `inputTypes` |
| Component should not be generated at all | → `skip` (currently `accordion`, `pagination`) |
| Component needs a second wrapper / an alternative construction method | → `customParts` |
| Main component must always carry an attribute (e.g. `popover`) | → `componentAttributes` |
| A sub-component part must be a specific element | → `subComponentElements` |
| A reference page's one-line description is wrong or missing | → `docSummaries` |
| A component renders the wrong element | → `componentElements`, and see the cross-check below |
| CSS class lands in the wrong category | `codegen/src/classifier.ts` |
| **What functions a component has, their parameters or their element** | `codegen/src/component-shape.ts` |
| **Which components get generated at all** | `codegen/src/component-set.ts` |
| Kotlin output shape is wrong | `codegen/src/generator-new.ts` |
| Reference page layout is wrong | `codegen/src/generator-docs.ts` |
| Generated tests are wrong | `codegen/src/test-generator.ts` |
| Icon output is wrong | `codegen/src/generator-heroicons.ts` |

**`component-shape.ts` is the one to reach for first.** It answers *what* a component's API is —
functions, parameters, defaults, the element each renders — and both emitters render from it.
Changing a parameter list there changes the Kotlin and the documentation together, which is the
whole reason it exists. `generator-new.ts` and `generator-docs.ts` only decide how their language
says it.

Three element names live on the shape and they are not interchangeable: `element` is the
kotlinx.html tag CLASS (`FIELDSET`, also the lambda receiver type), `tagBuilder` is the builder
the generated Kotlin calls (`fieldSet`), and `htmlTag` is the actual HTML element prose must name
(`fieldset`). All three are branded, because picking the wrong one produces a plausible value
rather than an error.

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

`codegen/src/test-generator.ts` mirrors the field: each static attribute becomes an assertion in
the generated test. Keep the two in step — a construction method that adds **no CSS class** is
otherwise invisible to the whole safety net, because both the generated tests and
`generated-sources-drift` key on class names. That blind spot is how the popover modal was
recorded as delivered while `daisyModal` still emitted only `<dialog>`.

### The same attribute on the MAIN component is `componentAttributes`

`staticAttributes` reaches only `customParts` entries — a component's extra wrappers. When the
attribute belongs on the component's own function, the one carrying the size and modifier
parameters, use the sibling key:

```json
"componentAttributes": {
  "megamenu": { "popover": "" }
}
```

That makes `daisyMegamenu` emit `<div class="megamenu …" popover>`. Same placement rules as
`staticAttributes` — after `id`, before `extraClasses`, so `attrs()` still runs last — and
`test-generator.ts` mirrors it as a `renders_static_attributes` test.

Two keys rather than one because they address different functions. If a third case turns up,
that is the moment to unify them.

### `subComponentElements` overrides the part-element heuristic

`inferPartElement` guesses a part's element from its name, and a name cannot say when the choice
is load-bearing. `megamenu-active` has to be a `<span>`: DaisyUI selects the open panel with
`[popover]:nth-of-type(N)`, `:nth-of-type` counts among siblings of the same tag, and a `<div>`
indicator takes div index 1 and shifts every `<div popover>` panel by one.

```json
"subComponentElements": { "megamenu-active": "SPAN" }
```

Worth knowing how that was found: the key sat in the config with two entries and **nothing read
it** until `support-popover-megamenu`. It went unnoticed because the heuristic happened to
return exactly what both entries said.

**Neither key is visible to the class-based safety net.** Generated tests and
`generated-sources-drift` both key on class names, so an attribute or an element choice that
changes no class is invisible to them — which is how the popover modal was recorded as delivered
while `daisyModal` still emitted only `<dialog>`. `megamenu` carried the same defect until
`support-popover-megamenu` fixed it; only an E2E that measures the rendered page catches this
class of thing.

**And the `api-baseline` gate does not catch a changed lambda receiver.** Turning
`daisyMegamenuActive` from `DIV` to `SPAN` broke compilation for callers and produced **no**
diff in `lib/api/lib.api`, because both lambda types erase to `Function1`.

## Pipeline

```
daisyui/packages/docs/src/routes/(routes)/components/<name>/+page.md
  ├ YAML frontmatter → codegen/src/parser/frontmatter.ts
  └ fenced ```html   → codegen/src/parser/documented-element.ts   ($$-marked classes)
  → codegen/src/parser/llms-txt.ts      (element heuristic, cross-checked against the above)
  → codegen/src/classifier.ts           (colors / sizes / styles / modifiers / parts)
  → codegen/src/component-set.ts        (WHICH components, once, for all three emitters)
  → codegen/src/component-shape.ts      (what the API IS: functions, parameters, elements)
      ├→ codegen/src/generator-new.ts        → lib/generated/**        (Kotlin)
      ├→ codegen/src/generator-docs.ts       → docs/reference/**       (Markdown, + docSummaries)
      └→ codegen/src/component-api-dump.ts   → lib/api/components.api  (Kotlin API baseline)
```

The fork at `component-shape.ts` is the load-bearing part: all three emitters read one
description of the API, so they cannot disagree about it. `component-set.ts` is the same idea one
level up — it had three copies, each with a comment asking the next person to keep them
identical, which is not how anything stays identical.

Heroicons runs a separate path: `parser/svg-heroicons.ts` → `generator-heroicons.ts`.

## Verifying a codegen change

1. `mcp_Gradle_gradle` → `:lib:generateComponents` (compiling no longer regenerates — the
   build reads the committed sources), or run configuration `kdaisyUI [:lib:generateComponents]`
2. `:lib:testCodegen` — the codegen's own unit tests, which pin the shape both emitters read
3. `:lib:test`
4. `:lib:generateReferenceDocs` whenever the change can reach a signature, an element or a
   parameter — which is nearly always, since the pages document exactly those
5. `:lib:checkComponentApi` — fails if a signature moved. If the change is intended, run
   `:lib:updateComponentApi`, **read** the diff, and commit it; a breaking one also needs a
   **How to migrate** entry in `README.md`
6. Inspect the produced files under `lib/generated/…` and `docs/reference/…` — read only, never edit
7. Review `git diff -- lib/generated docs/reference lib/api` and commit it. An unreviewed
   regeneration diff is the thing this layout exists to prevent, and CI fails if you leave it
   uncommitted
