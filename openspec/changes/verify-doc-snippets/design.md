## Context

This change follows `generate-component-reference`, which handles the derivable half of the
documentation with no new dependency. What is left is prose, which cannot be generated — only
the code inside it can be kept honest.

The governing constraint is the same one, from the other side: `AGENTS.md` records that
`codegen/` has **zero dependencies**, deliberately. This change is the one that may need one.
That is why it is separate rather than folded into the first: the dependency decision gets its
own proposal, its own first task, and its own chance to be refused.

## Decisions

### 1. The tool decision is a task, not a premise

`markdown-magic` is the obvious candidate and it was read rather than assumed
(`packages/core/src/transforms/code/index.js`, 2026-09-12):

- `id="NAME"` matches `CODE_SECTION:NAME:START` / `:END` markers, strips common indentation, and
  supports a `// CODE_SECTION:INCLUDE ` prefix for lines that should appear in the doc but be
  commented out in the source
- `section="NAME"` matches `// NAME` … `// NAME`
- `lines="22-44"` also exists and is **not** to be used — see decision 2
- it throws on a missing or inverted marker, which is the behaviour the spec requires

Against it: it is a pnpm monorepo whose consumable package pulls a tree, and the alternative — a
transform that reads a file, finds two markers, dedents and emits a fence — is perhaps forty
lines. Re-implementing a maintained tool is not free either; it is a maintenance liability
wearing the costume of simplicity.

The first task decides it by measuring: how many snippets, how many distinct transforms are
actually needed, and what `markdown-magic`'s installed footprint is. A decision made against
those three numbers is worth more than one made here.

**Either way it lives in its own package**, not in `codegen/`. The zero-dependency property was
declared about `codegen/` and survives intact.

### 2. Named regions, never line ranges

A line range is drift with extra steps: insert a line above it and the snippet silently shows
different code, with nothing to fail. Named regions move with the code they name and fail when
they disappear.

The cost is markers in `example-app` source. That is real noise in a file that is also a
demonstration of good style, and it is the price of the guarantee.

### 3. `example-app` is the source, not the test sources

**Chosen:** snippets come from `example-app/`.

**Rejected — `e2e-tests/`.** Its code asserts rather than demonstrates; a snippet lifted from a
Cucumber step reads like test scaffolding, not like something a user would write.

**Rejected — `lib/generated/test/`.** Generated, and shaped by coverage rather than by teaching.

`example-app` is the only tree that is simultaneously idiomatic, compiled by `check`, and driven
end to end by a browser.

### 4. The README table comes along

`generate-component-reference` deliberately leaves `README.md`'s 66-row component table
hand-maintained, because generating it needs an injector. This change has the injector, so the
table is taken over here. That is the whole reason the two changes are ordered rather than
independent.

## Risks / trade-offs

**The dependency may not be worth it.** If the survey finds a dozen snippets and one transform,
forty lines beats a package tree; if it finds eighty snippets and four transforms, the reverse.
The risk is deciding before measuring, which task 1.1 exists to prevent.

**Markers make the example app less exemplary.** Mitigated by keeping regions few and coarse:
mark a whole function, not three lines inside one.

**A snippet that compiles can still be wrong.** Compiling proves the API exists, not that the
advice is good. This change does not claim otherwise; the e2e coverage is what raises it from
"compiles" to "demonstrably renders", and nothing raises it to "is the right thing to do".

**Some snippets have no honest home in `example-app`.** Writing application code purely so a
document can quote it inverts the relationship and makes the example app worse. Where that
happens, the honest outcome is a snippet left hand-written and *marked* as unverified — the spec
names this as the falsifier for its first requirement rather than hiding it.

## Open question

Whether the documentation build joins `check` or stays a separate CI step. Joining it makes
staleness impossible to merge but puts Node on the path of a task that today needs none — which
is the same boundary `AGENTS.md` protects for the codegen. Leaning towards a separate step, with
the spec's second Assumed requirement recording what would show that was wrong.
