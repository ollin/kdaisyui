## Context

See proposal.md. The codegen tests already find the pinned submodules through `src/submodules.ts`.

## Decisions

### 1. A test, not a report line

See the proposal's deviation. The report stays a reading aid for the model; the README's claim is
checked.

### 2. Comments do not count

`status.css` carries two `data:` URLs inside comments; the test strips CSS comments first.
