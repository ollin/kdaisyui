## Why

Every generated function takes two lambdas on the same element, `attrs` and `content`, and the
functions with a `text` parameter let `content` silently drop the text. The ordinary Kotlin call
`daisyButton("Save") { popoverTarget(menu) }` binds the trailing lambda to `content` and renders a
button with no label. That cost a red end-to-end test to diagnose, and
`lib/src/test/.../components/TextAndContentPrecedenceTest.kt` now pins the trap instead of removing
it. Two lambdas for one element is also the most expensive part of the signature for a human and
for an agent reading `llms-full.txt`: 138 `attrs` parameters, and a receiver documented twice.

## What Changes

- **BREAKING** Every generated rendering function loses its `attrs` parameter.
- **BREAKING** Its `content` parameter is renamed `block`, typed `TAG.() -> Unit` and defaulting to
  `{}`, the shape kotlinx.html's own builders have. It is no longer nullable and no longer required.
- **BREAKING** A void element (`<input>`, `<img>`, …) now takes a `block` as well. It has no
  children, so the block sets attributes; this replaces its `attrs`.
- **BREAKING** `text` and `block` add up: the block runs first, then the text is appended. Neither
  drops the other. `daisyButton("Save") { popoverTarget(menu) }` renders the label and the
  attribute.
- The join scope's members follow the same frame.
- Generated tests, the hand-written tests, the example app, every prose document, both `llms`
  templates and both API baselines move to `block`. `docs/upgrading.md` gets a "How to migrate from
  0.8.x" entry, and the version becomes 0.9.0.

**Subtractive option, chosen.** The change removes a parameter from 138 functions and a branch from
every body, and deletes the precedence rule rather than documenting it better. The additive
alternative, keeping `attrs` and adding a compile-time warning for "text plus trailing lambda", was
rejected: it keeps the second lambda and adds a third mechanism to explain the first two.

**Out of scope, owned elsewhere** (all from the same API review, each its own change):

- Narrowing `ClassValues<T>?` parameters to the plain enum type.
- Generating `*Classes` siblings only where the model declares them.
- Replacing DaisyUI class literals in the example app and the how-to with builders.
- One rule for toggles and pass-through HTML parameters (`ghost`, `placeholder`, `value`).
- Replacing `JoinScope` with a marker call. This change only renames its lambda.

## Capabilities

### New Capabilities

None.

### Modified Capabilities

- `daisyui-component-coverage`: "A generated function never requires content its element cannot
  hold" becomes "Every generated function takes one optional block", covering the void case and
  the text/block combination. The coverage-gate scenario stops naming an attrs branch.
- `component-model`: the documented-element tests and the class-sibling requirement pass a second
  component's classes in the call's `block` instead of its `attrs`.

## Impact

- **Codegen**: `codegen/src/component-shape.ts`, `generator-new.ts`, `join-scope.ts`,
  `test-generator.ts`, `documented-elements.ts`, `generator-docs.ts`, `generator-llms.ts`, their
  tests and snapshots.
- **Generated, regenerated not edited**: `lib/generated/**`, `docs/reference/**`, `llms.txt`,
  `llms-full.txt`.
- **Hand-written**: `lib/src/test/**` (the precedence test is rewritten), `example-app/**`,
  `README.md`, `docs/how-to.md`, `docs/explanation.md`, `docs/tutorials/*.md`, `docs/upgrading.md`,
  `codegen/llms-template.md`, `codegen/llms-full-template.md`, `gradle.properties`.
- **API baselines**: `lib/api/lib.api` and `lib/api/components.api` change on every function.
- **Consumers**: every call that names `attrs =` or `content =` stops compiling. A trailing lambda
  keeps compiling, and on a function with `text` it now renders both.
