## Why

`guarantee-strict-csp` (archived 2026-11-08) found that DaisyUI draws `loading`, `mask-*`, the
`chat` tail, the `tooltip` arrow, a `mockup` control and its noise with `data:` SVGs, which is why
kdaisyui's policy allows `img-src 'self' data:` (its finding F5). The README names those
components. Nothing notices when a DaisyUI bump adds a component that draws with `data:`, or drops
one: the list would silently go wrong. Oliver adopted a proposal for this on 2026-11-08.

## What Changes

- **A codegen test** reads `daisyui/packages/daisyui/src/**/*.css` at the pinned submodule and
  collects every component whose CSS holds a `url("data:` outside a comment; it fails when that
  set differs from the list in the README's "Under a strict Content-Security-Policy", naming the
  difference.

### Deviation from the adopted wording, stated

The adopted proposal said "the bump report lists them". A test is the stronger form (house rules
§9, order 1: a check that fails before a report someone has to read): the bump's pull request goes
red until the README follows. Open with Oliver as A3.

### The subtractive option, stated

Nothing: the list is checked by hand at each bump. That is what F5 found not happening.

## Out of scope

- The bump report itself; it reads documentation pages, not CSS.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The codegen tests can read DaisyUI's CSS through `src/submodules.ts`, as the snapshot tests read the pages | code | to check in task 1.1 |
| A2 | No requirement of a main spec this builds on contradicts it: `content-security-policy` (the README names the policy and why) | code | to check in task 1.1 |
| A3 | Oliver accepts a failing test instead of a report line | user | open - ask at review |

## Impact

- `codegen/test/` gains one test; `README.md` may gain a marker the test reads the list by.
