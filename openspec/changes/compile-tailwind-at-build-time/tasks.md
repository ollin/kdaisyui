# Tasks

Uncertainty before dependency. Section 1 decides whether the rest is buildable at all; section 2
answers the question that matters to consumers. Nothing is wired in until both have answered.

## 1. Find out whether Tailwind can compile here without Node in the build

- [x] 1.1 Write the assertion that a `variant:daisy-class` applies, using `lg:btn-lg` — the one
  the docs promise — and see what it says about today's setup.
  → `. r`

  **Outcome: the premise was refuted, and the proposal is revised.** `lg:btn-lg` *works* today.
  The webjar ships `sm:` `md:` `lg:` `xl:` `hover:` pre-generated and nothing else; `max-*`,
  `dark:` and `focus:` are absent. So the gate this change needs is a **`max-*` scenario**, not
  an `lg:` one — and the `lg:` scenarios become characterization tests that must stay green.

- [ ] 1.1b Add the failing gate: the same matched-pair assertion with a `max-*` variant, which
  no shipped prefix covers. **It must fail on today's setup**, and it is what 3.2 makes pass.
  Do not land it red — write it, watch it fail, revert it, and bring it back with the fix.
  → evidence now, `^ F` with 3.2

- [ ] 1.2 Try the Tailwind standalone executable: obtain it, point it at a CSS entry file that
  `@import`s tailwindcss and `@plugin`s daisyui, and compile once by hand. Record whether it
  resolves the DaisyUI plugin without a `node_modules`.
  → `. d`

- [ ] 1.3 Decide and write into `design.md`: standalone binary, npm-in-Gradle, or checked-in
  compiled CSS. **If none is workable, stop and revise the proposal** — the fallback is to keep
  the browser build and fix only the documentation, which is a smaller but still honest change.
  → `. d`

## 2. Find out what a consumer's Tailwind can actually see

This is the half consumers need and the half we cannot answer by reading our own build.

- [ ] 2.1 Point Tailwind's content scanner at the generated Kotlin sources and compile. Record
  which classes it finds: literal strings in KDoc and enum constructors, versus classes assembled
  at runtime from an enum's `value`. Expectation: it finds some and misses the assembled ones,
  but this is worth measuring rather than assuming — if scanning is sufficient, no safelist is
  needed and the consumer story gets much simpler.
  → `. d`

- [x] 2.2 ~~If scanning is insufficient, establish that a safelist fixes it.~~ **Not needed.**
  Scanning is sufficient *where sources exist*; the problem is that consumers have a jar with no
  `.kt` in it, which no safelist mechanism changes. Superseded by 2.3's decision.
  → `. d`

- [x] 2.3 Record both outcomes and decide the consumer path.
  → `. d`

  **Decision: ship a generated class list as a resource inside the existing jar.** No new
  published artifact, so the release pipeline's `verifyStagingComplete` guard is untouched.
  Generated, committed and drift-checked like everything else here. A hand-maintained safelist is
  explicitly rejected as a documented instruction — its failure mode is the same silent one this
  change exists to remove. Narrowing 400 KB to 32 KB is a tooling problem and a separate change.

## 3. Make the example app compile its own CSS

- [ ] 3.1 Add the compilation step to `example-app/build.gradle.kts`, producing a stylesheet into
  the app's resources. Nothing serves it yet.
  → `. r`

- [ ] 3.2 Switch the page heads from the webjar stylesheet plus browser Tailwind to the compiled
  output, and make 1.1's assertion pass.
  → `^ F`

- [ ] 3.3 Confirm the existing E2E suite still passes — particularly `firstNavHasBackground` and
  the `@nojs` popover-modal scenario, which asserted against the old delivery. The `@nojs`
  scenario should now be *more* honest, because a build-time stylesheet needs no JavaScript.
  → `^ f`

- [ ] 3.4 Decide what happens to the `webjars_assets` scenarios that assert the Tailwind browser
  JS is served. If the app no longer uses it, an assertion that it is still downloadable is
  testing a dependency nobody has. Remove or repurpose, and say which in the commit.
  → `. r`

## 4. Ship the class list consumers need

Reshaped by 2.3: this is no longer a safelist for our own build — scanning covers that — but the
artefact that makes a consumer's build possible at all.

- [ ] 4.1 Emit the complete class list from the codegen pipeline, which already knows every class
  it can generate, into `lib/generated/` so `just generate` produces it and the drift job keeps it
  honest.
  → `^ f`

- [ ] 4.2 Package it as a resource in the `kdaisyui` jar. Assert it is present in the built jar —
  a file that silently stops being packaged is the failure this change is about.
  → `^ F`

- [ ] 4.3 Verify the documented consumer recipe end to end: extract the list from the jar with the
  Gradle snippet from 2.3, compile against it, and confirm a class the app never mentions in its
  own sources — one that only exists inside a generated enum — reaches the stylesheet.
  → `^ f`

## 5. Tell consumers the truth

The point of the change. A consumer cannot discover any of this from the library's API.

- [ ] 5.1 Write the `css-delivery` spec against what sections 1-2 measured — Verified where
  measured, Assumed with a falsifier where not.
  → `. d`

- [ ] 5.2 Add a CSS setup section to `README.md`: what the library provides (class names), what it
  does not (CSS), the two supported paths, and the scanner limitation with the safelist remedy.
  → `. D`

- [ ] 5.3 Correct `docs/explanation.md:137`. It promises `lg:btn-lg` works; say under which setup
  that is true.
  → `. D`

- [ ] 5.4 Correct `docs/tutorials/build-a-dashboard.md:113-116`. Either teach the build-time setup
  or mark the browser setup as prototype-only, naming what it cannot do.
  → `. D`

- [ ] 5.5 Note in the `kdaisyui-testing` skill that a component can pass every generated test and
  still render unstyled, because the tests assert class **names** and never CSS.
  → `. d`
