Every generated function now takes **one** lambda, `block`, in place of
`attrs` and `content`. A function's `text` is appended after the block
instead of being dropped.

## Why

`daisyButton("Save") { popoverTarget(menu) }` bound the trailing lambda
to `content`, which silently dropped the label. Two lambdas on one
element also cost every reader, and every agent reading `llms-full.txt`,
a second parameter on 138 functions.

## What changes

- **Breaking, and not a compile error:** `text` plus a trailing lambda
now renders both. The block runs first, because kotlinx.html rejects an
attribute once text is written (pinned in `AttributeOrderTest`). No call
in the repository relied on the old behaviour.
- **Breaking:** `attrs` is gone. `content` becomes `block: (TAG.() ->
Unit) = {}`, last and optional on every function, void elements
included.
- The generated tests put a second component's classes in the block:
`daisyDropdownContent { daisyMenuClasses() }`.
- Example app, docs, both `llms` templates and both API baselines are
migrated. `docs/upgrading.md` has **How to migrate from 0.8.x**. The
version is 0.9.0.
- PIT now leaves the generated default `{}` lambdas out. Their only
mutant returns `null` instead of `Unit` to a caller that discards it, so
it is equivalent. The reasoning sits next to `excludedMethods` in
`lib/build.gradle.kts`.

OpenSpec change:
`openspec/changes/archive/2026-11-02-one-block-lambda/`.

## Checked locally

| Gate | Result |
|---|---|
| `./gradlew check` incl. E2E | green |
| Kover | 100% line and branch |
| `:lib:pitest`, `:doc-snippets:pitest`, `:build-checks:pitest` | 100%
test strength |
| StrykerJS on the changed `codegen/src` lines | 71 of 71 killed |
| CodeScene change set | passed; every changed hand-written and codegen
file at 10.0 |
