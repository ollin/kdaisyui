## Why

**Revised after task 1.1 refuted the original premise.** The first version of this proposal claimed
no Tailwind variant of a DaisyUI class ever compiles in the setup the docs teach. That was a
generalisation from one failing case and it is wrong. What is true is narrower, and worse in a
specific way.

### What was measured

The prebuilt `daisyui.css` webjar ships a **fixed subset** of variants, pre-generated:

| Variant prefix | In the webjar | Rules |
|---|---|---|
| `sm:` `md:` `lg:` `xl:` | yes | 1198 each |
| `hover:` | yes | 60 |
| `max-sm:` `max-lg:` … | **no** | — |
| `dark:` `focus:` | **no** | — |

Five prefixes, nothing else. The Tailwind browser build contributes **nothing** to DaisyUI
classes — `lg:btn-lg` is absent from its 5.8 KB of output — because it can only generate variants
of utilities it owns.

So `lg:btn-lg`, which `docs/explanation.md:137` promises, **works today**: control and subject
buttons both measure 48px at 1280 and differ at 800. And `max-sm:megamenu-vertical` silently does
nothing, which is how this was found.

### Why that is worse than a clean failure

**It works just often enough to teach the wrong lesson.** A developer tries `lg:btn-lg`, sees it
work, and concludes variants are supported. Later they write `max-sm:card-side` or
`dark:alert-info` and it vanishes — no error, no warning, no failing test, and now with a prior
belief that says the problem must be elsewhere. A capability that holds for five prefixes and
fails for every other one, with nothing marking the boundary, is harder to work with than one that
fails consistently.

The boundary is also not ours and not stable: it is whatever DaisyUI chose to pre-generate in the
release we happen to pin, and a future DaisyUI could change the set without it being a breaking
change for anyone but us.

### The second half, which is independent of all of the above

Consumers get class names from `kdaisyui` and must produce CSS themselves. Nothing tells them
that **Tailwind's content scanner cannot see class names that exist only inside compiled Kotlin**.
A consumer writes `daisyButton(size = ButtonSize.Lg)`, their Tailwind scans their sources, finds no
`btn-lg` because it is assembled at runtime from an enum value, and omits the rule. The button
renders unstyled with no error anywhere.

This holds regardless of which CSS setup they choose, it cannot be discovered from the API, and it
is currently undocumented. It is the most valuable part of this change.

## What Changes

- Compile Tailwind at build time for `:example-app`, so **every** variant works there rather than
  the five that happen to ship.
- Document, for consumers, how to set up CSS for a kdaisyui project — the scanner limitation and
  its remedy first, because that is the part they cannot infer.
- Correct `docs/explanation.md` and `docs/tutorials/build-a-dashboard.md`: not because they lie
  today, but because they state a promise whose validity depends on a CSS setup they never mention.
- Keep a no-build path documented and honest about its limits — the prebuilt webjar plus the five
  shipped prefixes is a genuinely reasonable choice for a prototype, and DaisyUI offers it
  deliberately.

**Explicitly not in scope:** `:lib` gains no Node dependency and no CSS pipeline. A clone still
builds and tests with no Node and no submodules. This changes the demo and the docs only.

## Capabilities

### Added Capabilities

- `css-delivery`: what a kdaisyui consumer must do to get working CSS, what the library does and
  does not provide, which variants the no-build path supports, and the scanner limitation.

### Modified Capabilities

- `daisyui-component-coverage`: the coverage requirements assume a generated class reaching the
  page as working CSS. Worth stating that the library's contract ends at the class name, so a
  component can be correct and still render unstyled.

## Assumptions

Ordered by how much rests on them. `tasks.md` checks them in this order.

1. ~~No variant of a DaisyUI class compiles in the current setup.~~ **Refuted by task 1.1.**
   Replaced by: the webjar ships exactly `sm:` `md:` `lg:` `xl:` `hover:`, and nothing else.
   **Verified** by measuring the served stylesheet.
2. **Tailwind can be compiled in this Gradle build without adding Node to it.** DaisyUI documents
   a [standalone executable](https://daisyui.com/docs/install/standalone/) for exactly this case
   (`daisyui/skills/daisyui/install/SKILL.md:41`). *Wrong if:* the standalone CLI cannot resolve
   the DaisyUI plugin, or there is no sane way to obtain the binary per-platform from Gradle —
   then the choice is npm-in-Gradle or keeping the webjar and documenting the five prefixes.
   **Check this before anything is wired in.**
3. **A safelist is required, and works.** kdaisyui emits classes from compiled Kotlin, so a scan
   finds string fragments at best. *Wrong if:* scanning the generated Kotlin sources is
   sufficient — the class values *do* appear as string literals in the generated enum
   constructors, so this may work, and would make the consumer story much simpler.
4. **The generated components give us the complete class list for free.** Every class the library
   can emit is known at codegen time. *Wrong if:* classes assembled from `extraClasses` escape it
   — they do by definition, which is a limit to document rather than solve.

## Impact

- `example-app/build.gradle.kts` — a CSS compilation step; the served stylesheet changes from the
  webjar to the compiled output.
- `example-app` page heads — the browser-Tailwind `<script>` goes away.
- `e2e-tests` — assertions that a variant applies; the `max-*` one fails today and is the gate.
- `docs/explanation.md`, `docs/tutorials/build-a-dashboard.md`, `README.md` — the consumer story.
- `openspec/specs/` — the new `css-delivery` capability.
- Possibly `codegen/` — if the safelist is generated from the same data the components are.
- **Not** `:lib`, `:ktor-integration`, `:bom`, or the release pipeline.
