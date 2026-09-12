## Why

`docs/reference/` holds 66 per-component pages plus an index. They contain function
signatures, parameter defaults, enum entries, the rendered HTML tag and the DaisyUI
description — and **every one of those facts already exists in the codegen**, which emits the
same information as Kotlin and KDoc. The pages are hand-maintained copies.

They drift, silently, and the drift ships. `daisyMegamenuActive` changed its rendered element
from `<div>` to `<span>` on 2026-09-07; `docs/reference/megamenu.md` still documented the `DIV`
lambda receiver when **v0.4.0 was released on 2026-09-12**, five days later. Nothing failed,
because nothing checks these files. The same release added `daisyMegamenuPanel`, which the page
and the README component table never mentioned at all.

This is the failure mode `generated-sources` was written to prevent — committed output that no
longer matches its inputs — applied to everything except documentation.

## What Changes

- The 66 per-component pages under `docs/reference/` become **generated output**, produced by
  the codegen from the same parsed DaisyUI frontmatter and classified component model that
  produces `lib/generated/main/**`. Each gains the `GENERATED — DO NOT EDIT` header the Kotlin
  files carry.
- `docs/reference/index.md` is generated too. Its component table (lines 1–84) is derivable;
  its three hand-written sections (**Common parameters**, **Core utility**, **Requirements**,
  lines 85–118) are **moved to `docs/explanation.md`**, where prose belongs. This is the
  subtractive half: rather than teaching the generator to preserve prose islands, the prose
  moves to a file that is not generated.
- CI's `generated-sources-drift` job extends to `docs/reference/`. Without this the change
  moves the drift instead of removing it, and buys nothing.
- `just generate` gains the reference pages, so regeneration stays one command.

**Not breaking.** No published artifact changes; `docs/` is repository content.

### The subtractive option, stated

The alternative to generating is **deleting**: drop `docs/reference/` entirely and point
readers at the KDoc, which is already correct by construction because it is generated into the
sources. That was considered and rejected here, for one reason — the jar ships no Kotlin
sources (established in the `css-delivery` work), so a consumer's IDE shows no KDoc, and there
is no published API documentation site. Deleting would leave nothing. It becomes the better
option the day Dokka publishes, and this change should be revisited then rather than defended.

A second subtraction *is* taken: the 66 pages stop being 66 maintained artifacts and become one
template.

## Out of scope — owned by `verify-doc-snippets`

Everything about **prose** documentation: `docs/how-to.md`, `docs/tutorials/**`,
`docs/explanation.md` and the README's worked examples. Those are hand-written and must stay
hand-written; their problem is that the code snippets inside them are not compiled or asserted,
which is a different mechanism (snippet injection from `example-app`) with a different cost (a
new Node dependency). This change deliberately adds **no dependency** — the codegen's
zero-dependency property, stated in `AGENTS.md`, survives it.

Also out of scope: the alphabetical parameter ordering that makes positional arguments shift
meaning between releases. It is a real defect and it is documented in the README migration
notes, but it is a codegen *output* decision, not a documentation one.

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

- `generated-sources`: the requirements currently speak of generated **Kotlin** under
  `lib/generated/**`. They are widened so that generated output includes the component
  reference documentation — meaning it is committed, carries the do-not-edit header, is
  reproducible, and is covered by the drift job on the same terms.

## Impact

- **New**: a Markdown generator in `codegen/src/`, plus a Gradle task alongside the existing
  `generateComponents` / `generateComponentTests` / `generateHeroicons` tasks.
- **Rewritten**: 66 files under `docs/reference/` and `docs/reference/index.md`.
- **Edited by hand, once**: `docs/explanation.md` receives the three prose sections;
  `README.md`'s component table row for Megamenu is corrected by the regeneration only if that
  table is itself generated — it is **not** in scope here, so it is corrected by hand in this
  change and noted as a remaining hand-maintained copy.
- **CI**: `generated-sources-drift` job scope.
- **No new dependency.** No change to `lib`, `ktor-integration`, `bom` or any published
  artifact.
