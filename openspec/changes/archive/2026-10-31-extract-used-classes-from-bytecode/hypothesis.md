# Hypothesis

## Belief

A consumer's **compiled classes** say enough about which DaisyUI classes they can put on a page
that the library could ship them that CSS instead of all 560 classes — and the same reading is
what would let `at(Breakpoint.Lg, ButtonSize.Lg)` reach Tailwind at all.

## Why it is plausible

Two measurements from `apply-classes-at-variants`, 2026-09-13.

**The mechanism is confirmed at its crux.** A consumer class that calls
`daisyButton(size = at(Breakpoint.Xl, ButtonSize.Lg))` carries constant-pool `Fieldref`s to
**both** `io/github/ollin/kdaisyui/components/ButtonSize.Lg` and
`io/github/ollin/kdaisyui/core/Breakpoint.Xl`. Both halves are named as constants in the pool, so
recovering the pair needs no dataflow analysis — the hard part of the idea is the part already
shown to work. The CSS string itself lives in the library's own `ButtonSize.class`, so the
extractor reads a name from the consumer and a value from the library.

**The headroom is large.** `example-app` names 15 distinct enum entries and calls 27 of 66
component functions, against **560 classes delivered today**. It uses `values()`, `valueOf` and
`entries` **zero** times, so the reflective escape that would defeat static extraction outright is
not in use in the one real consumer available.

**And there is a reason to want it beyond size.** `at()` is built and parked precisely because
Tailwind emits CSS per candidate **string** found while scanning files as text, and a class
composed at run time appears in no file. An extractor that emits the composed classes is the named
gate for unparking it. Measured cost if the composition simply shipped instead: ~9.5 KB gzip per
variant over the 325 enum-member classes, linear — 49.2 KB baseline to 77.8 KB at three variants.

## What we do not know

- **The boolean fallback, which is the whole soft spot.** `daisyCard(border = true)` names no
  class — `border` is a parameter name, and the string `card-border` exists only inside the
  library. So granularity drops from the class to the *component*, and a consumer calling
  `daisyCard` at all may have to receive every `card-*` class. Unmeasured: how much of the 560 is
  reachable only that way. The 235 boolean-backed classes are the upper bound, but the real number
  depends on how many components a consumer touches.
- **What the resulting CSS actually weighs.** Every number so far is about the class LIST. Nobody
  has compiled a stylesheet from an extracted list and weighed it. Tailwind's output is not linear
  in the input list — shared declarations, `@property` blocks and the preflight do not shrink.
- **Whether one real consumer generalises.** `example-app` is a demo written by the same person as
  the library. Its 27-of-66 ratio may say more about the demo than about consumers.
- **Where it runs.** A Gradle plugin, a task in the consumer's build, or something the Tailwind
  step invokes — each has a different failure mode when the consumer's build is not Gradle at all.
- **What happens on a miss.** If the extractor misses a class, the page renders unstyled with no
  error. That is the same silent failure `at()` was parked for, reintroduced from the other side.
- **Whether `kdaisyui-classes.txt` survives.** Today's file is the fallback and the documented
  integration. Whether the extractor replaces it, or narrows it, or sits beside it, is undecided.

## What would change our mind

- The boolean fallback turns out to reach most of the 560 anyway — then the extractor buys little
  over the file that already ships, and the honest answer is to keep it.
- A compiled stylesheet from an extracted list is not appreciably smaller than today's — then the
  size argument is gone and only the `at()` unparking remains, which is a smaller prize and may
  not carry a build-time component on its own.
- Any consumer build that is not Gradle makes the delivery path impossible to state simply — then
  this belongs upstream in Tailwind's scanning, not in this library.
- A miss cannot be made loud. If the extractor cannot report "this class was named and I could not
  resolve it", it trades a known cost for a silent one, and this project has now recorded that
  trade going wrong four times in one change.
