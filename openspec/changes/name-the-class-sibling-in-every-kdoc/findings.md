# Findings

## F1 - The sibling was documented in two of three places, and the third is the one read

- **What happened:** at proposal, 2026-11-07. Checking where the class sibling is named found
  it on every reference page and in `llms-full.txt`, and absent from the generated KDoc. The
  Deploy-Radar's author used completion in the IDE, so the two documented places did not help.
- **Cause:** `generate-component-reference` and `llms-txt-index` wrote the sentence into the
  documents they generate; nobody wrote it into the Kotlin emitter, which has its own KDoc path.
- **Cost:** none here; it narrowed the change to one emitter line and one how-to section.

## F2 - The sibling condition already exists, and is wider than the proposal assumed

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`) found A2 false: the
  condition for a sibling is `hasSibling` in `codegen/src/component-shape.ts:367`, an own
  class or modifier classes or a class-writing parameter, used by the emitter at
  `generator-new.ts:380`; the proposal had assumed "own class is not null" and planned an
  enabler to extract it. The review also corrected the count (129 sentences in 66 files, not
  129 files), the A3 citations, and the unspecified link syntax.
- **Cause:** A2 was confirmed from the sibling emitter's body instead of its call site.
- **Cost:** about 10 minutes; the enabler task is gone, the change is smaller, and the exact
  emitted string is pinned in the design and the spec.

## F3 - The emitter step and the regeneration cannot be two TCR runs

- **What happened:** at apply, 2026-11-10. Tasks 2.1 (emitter and its unit test) and 2.2 (the
  regeneration) were planned as two commits. `codegen/test/generator-snapshots.test.ts` compares
  the emitter's output with the committed `lib/generated`, so the root TCR run of 2.1 alone goes
  red on the snapshot. The two became one run, `! F`, as the earlier emitter changes were
  (`97e8abf`, `fc4e552`).
- **Cause:** the plan split the steps by kind of file, not by what one green run needs.
- **Cost:** about 5 minutes; no code was written against the old plan.
