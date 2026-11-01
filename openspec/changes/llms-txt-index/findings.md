# Findings

## F1 - The test expected a period the summary line strips

- **What happened:** task 3.1's first TCR run was reverted. The test expected the DaisyUI
  fallback note to end in a period; `summaryLine` returns the first sentence without it, as the
  reference index does.
- **Cause:** an assumption about an existing function, written into a test without reading it.
- **Cost:** one revert, about 2 minutes. The step was split: the link line first, the order and
  the fallback as a second step with the observed expectation.

## F2 - A component's note does not help an agent pick a part

- **What happened:** task 5.2. A subagent given only `llms.txt` answered all three questions with
  one fetch each and no 404. For the dropdown question it picked the page by the component name;
  the note ("Context menus and action lists") added nothing, and the rule "the receiver is the
  element the function renders" would have misled a guess, since `daisyDropdown` renders
  `<details>` and `daisyDropdownContent` `<ul>`. Its suggestion: name the functions and their
  elements in the note, so the question needs no fetch.
- **Cause:** the note is the model's one-line summary, written for the reference index's table
  cell, not for choosing a link.
- **Cost:** none for this change; the specified behaviour is met.

## F3 - README's Quick start sits behind a long Upgrading section

- **What happened:** task 5.2. The subagent found the dependency block in one fetch, and noted
  that README's *Quick start* begins at line 643, after 590 lines of *Upgrading*; a tool that
  truncates would miss it.
- **Cause:** README's order; outside this change.
- **Cost:** none here.

## F4 - `codegen/` has no mutation tool

- **What happened:** task 5.3. The house rule asks for a mutation run on the changed code before
  a merge request. `:lib`, `:doc-snippets`, `:build-checks` and `:gradle-plugin` run PIT; the
  TypeScript under `codegen/` has nothing, and StrykerJS would be a second dependency, which
  `AGENTS.md` calls a decision.
- **Cause:** pre-existing; this change added a module with gates whose tests have not been shown
  to fail by mutation, only by the hand-run proofs in task 5.1.
- **Cost:** none to the plan; a gap the evaluation names.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only |
| F2 | The component note names the functions and their elements: `Context menus and action lists — daisyDropdown <details>, daisyDropdownContent <ul>, …`; measured first against A3's 16 KB | open - Oliver's call; it changes the `llms-txt` spec's note requirement |
| F3 | Move *Upgrading* below *Quick start* in README, or point the `README` note at the section | open - its own `docs` change |
| F4 | Introduce StrykerJS with the `command` runner for `codegen/`, as its own change | open - a second dependency is a decision |
