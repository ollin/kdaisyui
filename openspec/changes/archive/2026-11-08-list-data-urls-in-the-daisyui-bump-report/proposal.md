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
red until the README follows. Confirmed by Oliver as A3, 2026-11-08.

### The subtractive option, stated

Nothing: the list is checked by hand at each bump. That is what F5 found not happening.

## Out of scope

- The bump report itself; it reads documentation pages, not CSS.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The codegen tests can read DaisyUI's CSS through `src/submodules.ts`, as the snapshot tests read the pages | code | confirmed in task 1.1 - `pinnedSubmodule('daisyui')` in `codegen/src/submodules.ts` returns the pinned checkout from the repository root, as `submodules.test.ts` and the snapshot tests use it |
| A2 | No requirement of a main spec this builds on contradicts it: `content-security-policy` (the README names the policy and why) | code | confirmed in task 1.1 - `content-security-policy` requires the README to show the header and say why `img-src` allows `data:`; a marker naming the components adds nothing it forbids |
| A3 | Oliver accepts a failing test instead of a report line | user | confirmed by the user 2026-11-08 - the test, as recommended |

## Impact

- `codegen/test/` gains one test; `README.md` may gain a marker the test reads the list by.
