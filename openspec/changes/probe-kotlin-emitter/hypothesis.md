## Belief

A Kotlin generator built on a typed component model and KotlinPoet would be easier to
read and to change than the current JavaScript, which builds Kotlin source by
concatenating strings — and the typed model would make the parameter shape a decision we
make deliberately, rather than one that falls out of the input format.

## Why it is plausible

Three observations, not a proof:

- **The plumbing is hand-built where commodity exists.** `codegen/src/parser/frontmatter.js`
  hand-rolls a ~100-line YAML state machine with four hardcoded indent levels, while
  `js-yaml` sits unused in `codegen/package.json:10`. `generator-new.js` assembles Kotlin
  with manual `"        "` indentation and an import sorter whose `startsWith('kdaisyui')`
  branch never matches, because the imports start with `io.github.ollin.kdaisyui`. None of
  this is where the library differentiates; it is plumbing that was written by hand because
  nothing else was reached for.
- **The information we want survives classification and dies at emission.**
  `classifier.js:42-49` keeps `styles`, `directions` and `placements` as separate axes;
  `generator-new.js:44-82` flattens all of them into one undifferentiated list of booleans.
  `daisyButton` ends up with 19 parameters, 11 of them `Boolean`, five of which are DaisyUI's
  mutually exclusive `style` axis — so `daisyButton(outline = true, ghost = true)` compiles
  and emits nonsense. The evidence that this hurts today: `codegen-config.json:147-190`
  hand-writes `end/start/top/bottom/left/right/center` booleans back onto `dropdown`,
  compensating for structure the emitter had already discarded.
- **Same language on both sides.** A Kotlin emitter can be unit-tested in the language it
  emits, with the compiler checking the model. Today the generator has no tests at all —
  only the tests it generates.

## What we do not know

- Whether KotlinPoet expresses what we need: extension functions on kotlinx.html receivers,
  lambda parameters with receivers (`BUTTON.() -> Unit`), default arguments, and generated
  enums — and whether expressing them reads better than a string template or merely
  differently.
- Whether a typed model actually makes the taxonomy change small, or whether DaisyUI's five
  axes resist a clean Kotlin shape for reasons we cannot see from either side alone.
- Whether the port is worth its cost at all. The subtractive alternative — delete the dead
  `index.js`/`generator.js`/`classify.js` fork, swap the hand-rolled YAML for the `js-yaml`
  already present, and redesign the API in the JavaScript that exists — is not ruled out by
  anything except preference. Shoup: "If you don't end up regretting your early technology
  decisions, you probably over-engineered."
- How much of the 2.800 lines is genuinely needed versus accumulated. A parallel
  implementation that stalls at 30% would tell us something a reading of the source would
  not.

## What would change our mind

- The KotlinPoet version of Button reads no better than `generator-new.js` — or worse.
- Expressing kotlinx.html receiver lambdas in KotlinPoet needs escape hatches that put us
  back to string assembly.
- The parallel implementation stalls on something the JavaScript handles easily, and the
  workaround is uglier than what it replaces.
