## Why

`README.md`, `docs/how-to.md`, `docs/explanation.md` and the two tutorials hold **68 Kotlin code
blocks, 634 lines**, counted 2026-10-31: 41 that use the library, 21 migration before/after
snippets and 6 build scripts. **Not one of them is compiled.** A snippet can name a function
that was renamed, pass a parameter that was removed, or use a lambda receiver that changed (the
same `DIV` → `SPAN` move that once made `docs/reference/megamenu.md` wrong), and nothing fails.

A tutorial is what a new user types first, so a broken snippet is the library's first impression.
Reference pages are generated and drift-checked; the prose around them cannot be generated and
must stay hand-written. Only the code inside it can be kept honest.

**Revised 2026-10-31** (`findings.md` F1). This change first planned to *inject* the snippets
from named regions in `example-app`. Its own survey refuted that: only about 12 of the 41 usage
snippets have an honest home there, because tutorials show code growing step by step and
`example-app` holds only the finished state. Oliver chose to compile the snippets where they stand.

## What Changes

- Every Kotlin code block in the prose documentation is **compiled by `./gradlew check`**, where
  it stands. The Markdown stays the single place the snippet is written.
- A new, unpublished module **`:doc-snippets`** does it. Its `main` source set is the extractor, a
  small Kotlin program unit-tested under the repository's 100% coverage gate. It writes one Kotlin
  file per snippet, and a second source set compiles those against `:lib`, `:ktor-integration`,
  kotlinx.html and Ktor.
- A snippet that cannot compile on purpose (the *before* half of a migration note, a signature
  quoted for reading, a hypothetical pattern) says so in an invisible marker right above its
  fence, **with a reason**. A marker without a reason fails the build.
- A snippet that stops compiling fails `check`, naming the document and the line of the fence.
- Snippets that no longer compile today are fixed in this change, and README's stale *What's new
  in 0.2.0* section is deleted (`findings.md` F2).
- **No Node and no new dependency.** The extractor is plain Kotlin, so `check` keeps the promise
  that a clone builds with no Node, npm or submodules.

**Removed from the first plan:** markdown-magic (measured at 71 packages, 18 MB), region markers in
`example-app`, the CI drift job (nothing generated is committed, so there is nothing to drift),
and `llms.txt` (generated since 0.7.0).

**Not breaking.** No published artifact changes.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | No requirement of a main spec this builds on contradicts it. It touches none of `generated-sources`, `coverage-enforcement`, `api-change-detection` or `css-delivery`; it only adds a module that publishes nothing | code | confirmed at proposal - the specs' requirements name `lib/generated/**`, `codegen/`, the published artifacts and the aggregated gate, none of which this changes |
| A2 | Most usage snippets compile with one default context (a statement inside a `FlowContent` builder, standard imports), so markers stay the exception | code | to check in task 1.1 |
| A3 | A new module's `main` source set can join the aggregated 100% Kover gate the way `:lib` and `:ktor-integration` do | code | to check in task 1.2 |
| A4 | `example-app` can be the source of the snippets | code | false - about 12 of 41 have a home there; replaced by compiling in place (finding F1) |

## Capabilities

### New Capabilities

- `documentation-snippets`: every Kotlin snippet in the prose documentation is compiled by the
  build, or says why it is not; a snippet that stops compiling fails the build and names where it
  is.

### Modified Capabilities

<!-- none: generated-sources covers generated output; these documents stay hand-written. -->

## Impact

- **New module**: `doc-snippets/` (settings, build file, extractor, its tests). Not published,
  not in the BOM.
- **Edited**: `README.md`, `docs/how-to.md`, `docs/explanation.md`, `docs/tutorials/*.md`, for
  markers and for snippets that no longer compile.
- **Edited**: root `build.gradle.kts` for the Kover aggregation, if A3 holds.
- **Edited**: `AGENTS.md` and the `kdaisyui-testing` skill, for the marker convention.
- No change to `lib`, `ktor-integration`, `bom`, CI workflows or any published artifact.
