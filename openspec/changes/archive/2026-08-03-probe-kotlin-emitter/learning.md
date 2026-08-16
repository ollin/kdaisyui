## Signal read

**Amplify** — but on a narrower claim than the hypothesis made.

What actually earned it: Tooltip generated correctly from the model built for Button, with
no special-casing, and `placement` became an enum by the same rule that produced
`ButtonStyle`. That is the observation that mattered, because it tests generalisation rather
than taste. The prettier output and the shorter emitter are not evidence — the emitter is not
feature-equivalent yet, so its line count is meaningless.

What did **not** earn it: nothing in the dampen list fired. KotlinPoet needed no escape
hatch, the model needed no per-component override, and Tooltip's axis fitted.

## What we learned

**The parameter shape becomes a decision instead of a side effect.** `EXCLUSIVE_CATEGORIES`
in `Model.kt` is one readable line that determines the signature of every component. In the
JavaScript the same knowledge is split between `classifier.js:42-49`, which keeps the axes
apart, and `generator-new.js:44-82`, which flattens them again — which is why
`daisyButton(outline = true, ghost = true)` compiles today. Button went from 19 parameters
to 13, and five mutually exclusive booleans became one `ButtonStyle`.

**The cost of this port is Gradle, not translation.** All three failures were build
configuration; none were the model or the emitter. Any estimate that assumes the difficulty
lies in rewriting 2.800 lines of JavaScript is estimating the wrong thing.

**A parallel module is less isolated than it looks.** The declared blast radius was one
`include` line; it became two, because a Kotlin compiler plugin cannot be versioned inside
the module that uses it. Worth knowing before the next probe declares "confined to a
throwaway directory".

**About how we work:** I twice reached for a claim that flattered the hypothesis — the line
count, and reading KotlinPoet's `` `open` `` escaping as a caught bug — and both fell apart
on checking. Both would have survived if the probe had only had an amplify signal to satisfy.
Writing the dampen list first is what made them checkable.

**A probe is not exempt from tests, and this one proves why.** I wrote 350 lines with none,
declared amplify, and only then added 21 tests — which immediately found a defect two
generated components had hidden: the emitter derived the kotlinx.html builder function by
lowercasing the element, which breaks on `TEXTAREA` and `FIELDSET`. Tooltip had proved the
model generalises across *axes*; nothing had tested generalisation across *elements*, and I
read the first as evidence for the second.

The correction to the workflow: **a probe's evidence is only as broad as the cases it chose,
and choosing them after seeing what works is how a probe flatters itself.** Tests are what
make the chosen cases visible as choices. This belongs in `probe-driven` — a probe that
produces code produces it under the same discipline as any other code.

## What is now specifiable

**Nothing yet, and that is the honest answer.** The rule "DaisyUI's `color`, `size`, `style`,
`placement`, `direction` are exclusive; `behavior` and `modifier` are flags" held for **2 of
65 components**. Two is a promising start, not a stable pattern, and writing it into
`openspec/specs/` now would be exactly the Assumed-wearing-a-SHALL that the provenance rule
forbids.

What would make it specifiable: the same model surviving the components that are known to be
awkward — `Dropdown`, whose `codegen-config.json:147-190` hand-writes back the very
directional booleans the emitter discarded; anything using `part`; and the aliased
components the JS generator skips.

## What is still unknown

- **Feature parity cost.** `attrs`, the config-injected extras (`type: ButtonType?`), the
  `disabled` special case that also sets the HTML attribute, `part` elements, aliases, and
  text/content precedence. Until these exist, no comparison of size or clarity is honest.
- **Whether Button's `modifier` should be exclusive too.** `wide`/`block`/`square`/`circle`
  are arguably four shapes, not four independent flags — the probe kept DaisyUI's category
  as-is rather than deciding. The model makes this a one-line change, which is itself the
  point, but the answer is not known.
- **Whether the port is worth it at all.** Still not ruled out: delete the dead
  `index.js`/`generator.js`/`classify.js` fork, swap the hand-rolled YAML for the `js-yaml`
  already declared, and do the taxonomy fix in the JavaScript. This probe showed the Kotlin
  route works; it did not show the JavaScript route does not.
- **The 5.5.20 ceiling is untouched.** The probe sidesteps `llms-txt.js` by passing the
  element in from `Main.kt`, which suggests 65 hardcoded data points would remove the ceiling
  — in either language. That is a separate, much cheaper piece of work.
