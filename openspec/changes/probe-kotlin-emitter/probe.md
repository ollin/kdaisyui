## The experiment

Build a **parallel** Kotlin generator beside the JavaScript one. The JS generator keeps
working and stays the one the build uses; the Kotlin one runs on the side and we compare
what comes out and what it took to write.

Button first, because it exercises every axis DaisyUI has: `color` (8), `size` (5),
`style` (5, mutually exclusive), `modifier` (4), `behavior` (2) — plus `text`, `id`,
`extraClasses`, `attrs` and `content`.

- [ ] 1 Add a `codegen-kotlin` module with KotlinPoet 2.3.0. One `include` line in
      `settings.gradle.kts`; nothing else in the existing build is touched
- [ ] 2 Define the typed component model — the five axes as distinct types rather than one
      bag of booleans. This is where the parameter shape becomes a decision instead of a
      side effect
- [ ] 3 Parse Button's frontmatter into that model, using a real YAML library rather than a
      hand-rolled parser
- [ ] 4 Emit `Button.kt` with KotlinPoet into `codegen-kotlin/build/probe-output/`
- [ ] 5 Put it side by side with today's `lib/build/generated/.../components/Button.kt` and
      with `codegen/src/generator-new.js`, and write down what we actually see
- [ ] 6 Push until it breaks or until the timebox: try a second, differently-shaped
      component (`Tooltip` — `placement` axis) to find out whether Button was a lucky case

## Timebox

One working session. Stop and look regardless of how far step 6 got — "we reached step 4
and step 5 was already convincing" and "we stalled at step 2" are both usable results.

## Amplify signal

- The emitter source is visibly shorter or clearer than `generator-new.js` for the same
  output, and the difference is one a reader would notice without being told to look.
- The five axes land as distinct types, and `daisyButton(outline = true, ghost = true)`
  becomes impossible to write rather than merely discouraged.
- KotlinPoet handles receiver lambdas and default arguments without escape hatches.
- Tooltip works from the same model without special-casing.

## Dampen signal

- KotlinPoet needs `CodeBlock.of("…")` string assembly for the constructs we care about — in
  which case we swapped one string-building approach for a heavier one.
- The typed model needs a per-component escape hatch to fit DaisyUI's real data, which is
  the same disease `codegen-config.json` already has.
- Emitting Button takes substantially more Kotlin than the JS it replaces, without reading
  better.
- Tooltip's `placement` axis does not fit the model built for Button's `style` axis, and the
  fix is a special case.

## How we throw it away

`rm -rf codegen-kotlin/` and revert the single `include("codegen-kotlin")` line in
`settings.gradle.kts`. Nothing else refers to it. The JS generator is untouched throughout,
so the build never depended on this.

## What must still work

**Blast radius: one line of shared config.** `settings.gradle.kts` gains an `include`, so
this is not purely confined to a throwaway directory.

Therefore: `:lib:test` must still pass and `:lib:generateComponents` must still produce the
same output at the end of the probe — evidence that adding the module did not disturb the
existing build. Everything *inside* `codegen-kotlin/` may be broken, half-finished or
abandoned mid-file; that is the part being thrown away, and demanding it compile cleanly
would turn a cheap probe into a commitment.

The e2e suite is **not** required. It exercises the example app, which this probe does not
touch, and running it would cost more than the signal it could give.
