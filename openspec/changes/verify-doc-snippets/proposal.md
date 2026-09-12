## Why

`docs/how-to.md`, `docs/tutorials/**`, `docs/explanation.md` and `README.md` contain roughly
1100 lines of hand-written prose, and inside it a large number of Kotlin snippets. **Not one of
them is compiled.** A snippet can name a function that was renamed, pass a parameter that was
removed, or use a lambda receiver that changed — the same `DIV` → `SPAN` move that made
`docs/reference/megamenu.md` wrong — and nothing anywhere fails.

This is a worse failure than the reference-page drift that `generate-component-reference`
addresses, for two reasons. A reference page is at least mechanically derivable, so it *can* be
generated; prose cannot, and must stay hand-written. And a tutorial is what a new user types
first, so a broken snippet is the library's first impression.

## What Changes

- Snippets in the prose documentation are **injected from real source files** rather than typed
  into Markdown, using named regions in the source — not line numbers, which would relocate the
  drift rather than remove it.
- The source of a snippet is `example-app/`, which compiles as part of `check` and is driven
  through a real browser by `:e2e-tests`. A snippet therefore carries two guarantees: it
  compiles, and the page it belongs to is asserted to render.
- A missing or renamed region **fails loudly** rather than silently emitting nothing.
- CI gains a check that the injected documentation is in step with its sources, on the same
  terms as `generated-sources-drift`.
- `README.md`'s component table — a third copy of the 66 components — is taken over by the same
  mechanism, closing the gap `generate-component-reference` deliberately left open.

**Not breaking.** No published artifact changes.

### The subtractive option, stated

The alternative to injecting snippets is **removing them**: cut the tutorials down to prose that
points at `example-app` and let readers read the real thing. That is genuinely cheaper and it is
not obviously worse — a tutorial that shows five lines and links to the working file may serve a
reader better than one that inlines forty.

It is rejected for the tutorials, because a tutorial that cannot be read linearly stops being a
tutorial. It should be **taken** wherever a snippet is long enough that nobody reads it inline;
the first task measures which snippets those are, so the decision is made against a list rather
than a feeling.

A second subtraction is available and should be weighed during implementation: several
`how-to.md` recipes may duplicate what a tutorial already shows, and deleting the duplicate
beats injecting it twice.

## Out of scope — owned by `generate-component-reference`

The 66 per-component pages and `docs/reference/index.md`. Those are fully derivable and are
generated wholesale by that change, with no dependency added. **This change depends on it
having landed**, for one substantive reason beyond ordering: `generate-component-reference`
moves prose out of `index.md` into `docs/explanation.md`, and this change then treats
`explanation.md` as prose to be snippet-verified. Doing them in the other order means touching
the same sections twice.

## Capabilities

### New Capabilities

- `documentation-snippets`: code shown in prose documentation is extracted from sources that
  compile and are exercised by tests, and a snippet whose source moves or disappears fails the
  build rather than going stale.

### Modified Capabilities

<!-- none: generated-sources covers generated output, which this is not — the prose files stay
     hand-written and only their fenced code blocks are injected. -->

## Impact

- **New dependency**: `markdown-magic`, or a small purpose-built transform. This is the change's
  central cost and its first task decides it, because `AGENTS.md` records `codegen/`'s
  zero-dependency property as deliberate. If the tool is adopted it goes in its **own package**,
  not in `codegen/`, so that property survives where it was declared.
- **Edited**: `docs/how-to.md`, `docs/tutorials/**`, `docs/explanation.md`, `README.md`.
- **Edited**: `example-app/**` gains region markers in comments.
- **CI**: one more check.
- No change to `lib`, `ktor-integration`, `bom` or any published artifact.
