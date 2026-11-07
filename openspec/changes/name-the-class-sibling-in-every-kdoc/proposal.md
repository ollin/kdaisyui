## Why

Every generated function that writes a class has had a typed class sibling since 0.8.0:
`a { daisyBadgeClasses(color = BadgeColor.Info) }` puts a badge's classes on a link. The
reference page of each component shows it as a comment inside its signature fence, "The same
classes on an element you write yourself, or beside another component's"
(`docs/reference/badge.md:29`, emitted by `generator-docs.ts:133`), and `llms-full.txt:82-86`
explains it with the button-as-link example. The
Deploy-Radar (fwb-application, kdaisyui 0.9.0) still wrote raw class strings for every badge
that is a link, every button that is a link and a button that is a `<summary>`, in nine places
across five files, and used `daisyButtonClasses` exactly once (its proposal item 7,
`~/ai/kdaisyui-improvements-from-deploy-radar.md`, 2026-11-07). What its author looked at was
the IDE: the KDoc of `daisyBadge`, which says what the function renders and lists its
parameters, and does not mention that a sibling exists.

## What Changes

- **The generated KDoc of every rendering function that has a class sibling gets one
  sentence naming it**, with the call shape and a KDoc link the IDE resolves:
  ``The same classes on another element: `a { daisyBadgeClasses(…) }`; see [daisyBadgeClasses].``
  One sentence, one place in the emitter, gated on the existing `hasSibling`; 129 sentences
  across the 66 component files, by regeneration.
- **A how-to section, "Put a component's classes on another element"**, with the three cases
  the Deploy-Radar needed: a badge that is a link, a button that is a link, a button that is a
  `<summary>`; compiled by `:doc-snippets`.
- Nothing changes in the reference pages, `llms.txt`, `llms-full.txt` or either API baseline:
  the first three already name the sibling, and a KDoc is not in the API dump.

### The subtractive option, stated

Add nothing to the library and rely on the reference pages and `llms-full.txt`, which already
say it. That is the state in which the Deploy-Radar was written, so it is known not to work for
a reader whose entry point is completion in the IDE. Rejected. What this change removes: nothing
in this repository; in a consumer, raw class strings.

**Rejected alternative, for the record:** an element choice on the generated functions, such
as `daisyBadge(href = …)` rendering an `<a>`. A function renders the one element its model
declares (`daisyui-component-coverage`, "Each generated function renders the one element its
model declares"), and a block typed on `SPAN` cannot become one typed on `A`. The sibling is the
designed answer; this change makes it visible.

## Out of scope

- **A `daisyBadgeLink` or `daisyButtonLink` construction.** DaisyUI documents `<a class="btn">`
  and the generated documented-element test already expresses it as
  `a { daisyButtonClasses(…) }`; a second function per element would multiply the API for what
  one sentence solves.
- **The form-in-a-dropdown and modal recipes** — `document-server-rendered-patterns`.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | One emitter function writes the KDoc of every rendering function, main, part and custom alike | code | confirmed at proposal - `codegen/src/generator-new.ts:88`, `renderKdoc(shape)`, with only the `@param` lines conditional on `main` |
| A2 | The emitter knows at that point whether the function has a sibling | code | false - the condition is not "own class is not null" but the exported `hasSibling` of `component-shape.ts:367`, which also counts modifier classes and class-writing parameters; the emitter calls it at `generator-new.ts:380`. The sentence is gated on that predicate and the planned enabler is dropped (finding F2) |
| A3 | The reference pages and `llms-full.txt` already name the sibling, so neither generator changes | code | confirmed at proposal - `docs/reference/badge.md:29`, a comment inside the signature fence emitted by `generator-docs.ts:133`; `llms-full.txt:82-86`, the prose with the button-as-link example, and `:179`, the badge sibling's signature |
| A4 | No requirement of a main spec this builds on contradicts it. It builds on `component-model` (the sibling requirement, MODIFIED below), `generated-sources` (deterministic regeneration, drift-checked) and `mutation-testing` (changed codegen lines at 100%) | code | confirmed at proposal - a KDoc line is generated output like any other; the snapshot test kills a mutant of the sentence |
| A5 | `a` is the right example element for every function's sentence, and the wording is acceptable | user | open - ask at review, decided before task 2.1. A link is what the Deploy-Radar needed nine times; for a `daisyMenu` the example reads oddly but stays true |

## Capabilities

### Modified Capabilities

- `component-model`: a function's generated KDoc names its class sibling.

## Impact

- `codegen/src/generator-new.ts`, `codegen/test/generator-new.test.ts` and the snapshot;
  regenerated `lib/generated/main/**` (129 sentences across the 66 component files).
- `docs/how-to.md`, one section.
