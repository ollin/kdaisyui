# Findings

## F1 - `example-app` cannot be the source of most snippets

- **What happened:** task 2.1's survey, 2026-10-31. Of the 41 Kotlin snippets that use the
  library, about 12 have an honest equivalent in `example-app`. The getting-started tutorial uses
  standalone `createHTML()` snippets. The dashboard tutorial shows intermediate states of code
  that `example-app` holds only finished. 5 of the 7 in `explanation.md` are deliberately not
  library usage: a raw string, plain kotlinx.html, a signature, a hypothetical pattern.
- **Cause:** the plan assumed the documentation demonstrates what the application does. Tutorials
  demonstrate how code grows, which a finished application cannot quote.
- **Cost:** the spec's first requirement was false by its own falsifier. One question round with
  Oliver (who chose to compile snippets in place), and a rewrite of the proposal, design, spec and
  tasks before any code.

## F2 - Two tasks were settled before the change started

- **What happened:** task 5.2 asked whether `llms.txt` belongs to this change. It has been
  generated whole by `generateReferenceDocs` since 0.7.0. Task 5.1 asked to inject README's
  three-row *What's new in 0.2.0* table, a section two minor versions stale whose own comment says
  its rows have been wrong before.
- **Cause:** six weeks between proposing the change and applying it, and 0.7.0 and 0.8.0 in
  between.
- **Cost:** none. Both tasks were replaced: `llms.txt` is out of scope, and the stale section is
  deleted rather than injected.

## F3 - Three snippets are broken today, two of them by a bad edit

- **What happened:** task 1.1's compile. `docs/tutorials/getting-started.md` step 5 and
  `docs/tutorials/build-a-dashboard.md` step 6 each carry a pasted leftover: a second, partial
  `daisyStats { … }` after the block closes. README's 0.7.x migration note uses `{ … }`, which is
  not Kotlin.
- **Cause:** nothing compiles the documentation, which is what this change is for.
- **Cost:** none to the plan; fixed in section 3.

## F4 - CI does not run `check`

- **What happened:** task 1.2. The plan said a snippet compiled by `check` would fail in CI's
  `unit-tests` job. That job runs `:lib:test koverVerify koverXmlReport`, and no job runs `check`.
- **Cause:** an assumption about CI that nobody had written down, so nobody had checked it. It is
  now A5.
- **Cost:** a design correction before any code; the job gains one task (task 4.1).
