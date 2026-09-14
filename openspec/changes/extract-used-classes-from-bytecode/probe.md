# Probe

## The experiment

Ordered by what can kill the idea cheapest, not by what has to be built first. Steps 1 and 2 need
**no extractor at all** — they decide whether writing one is worth it, using lists assembled by
hand from what is already known.

- [ ] 1 **Measure the boolean-fallback ceiling, without building anything.** For `example-app`,
      assemble the class list the *pessimistic* rule would produce: every enum-member class it
      names, plus **every** class of every component whose function it calls. Compare to 560. This
      is the upper bound of what an extractor could ever deliver, and it needs only the 27-of-66
      call list already measured.
- [ ] 2 **Weigh it.** Compile a Tailwind stylesheet from that list and record raw and gzip size
      beside today's 412 KB / 49.2 KB. Also compile from the *optimistic* list — enum entries only,
      no boolean fallback — to bracket the answer. The gap between the two brackets is what the
      extractor's precision is worth, in bytes.
- [ ] 3 **Only if 1 and 2 clear the amplify threshold:** prototype the extractor over
      `example-app`'s compiled classes. It must recover the 15 known enum entries, the 27 called
      component functions, and both halves of an `at(Breakpoint.Xl, ButtonSize.Lg)` call site.
- [ ] 4 **Try to make it lie.** Feed it a call site that names a class through a local `val`, a
      `when` branch, a function parameter and a collection. Record which of the four it loses —
      the goal is a list of what it cannot see, not a pass.
- [ ] 5 **Check the miss is loud.** Determine whether the extractor can report "a kdaisyui
      function was called and I could not resolve its classes" rather than silently emitting less.

## Timebox

**Half a day.** Steps 1 and 2 are the timebox's real content; if they are not done inside two
hours, that itself is the signal that the measurement is harder than the idea is worth.

## Amplify signal

Written now, while the outcome is still open, so it cannot be argued into afterwards. Against
today's `example-app` baseline of 560 classes, 412 KB raw, **49.2 KB gzip**:

- the pessimistic list (step 1) is **under 350 classes**, and
- its compiled stylesheet (step 2) is **at or under 30 KB gzip** — roughly 60% of today's, and
- the prototype (step 3) recovers **both halves** of the `at()` pair, which is what makes
  unparking the variant API possible at all.

All three. Two out of three is step 6, not a green light.

## Dampen signal

- The pessimistic stylesheet is **at or above 42 KB gzip** — 85% of today's. The size argument is
  then gone, and what remains is only the `at()` unparking, which is a smaller prize than a
  build-time component costs. Stop and say so.
- The optimistic and pessimistic brackets are **within 5 KB gzip of each other**. Precision then
  buys nothing, and the simple answer — ship a per-component list, no bytecode reading — wins.
- Step 4 loses **more than one** of the four indirections. A consumer who writes
  `val size = if (wide) ButtonSize.Lg else ButtonSize.Sm` is not exotic, and an extractor that
  misses ordinary Kotlin trades a known cost for a silent one.
- Step 5 finds no way to make a miss loud. Non-negotiable: this change exists to serve CSS, and
  serving too little produces an unstyled page with no error — the same silent failure `at()` was
  parked for, arriving from the other side.

## How we throw it away

Everything lives in `/tmp/kdaisyui-bytecode-probe/` and on branch
`probe/extract-used-classes-from-bytecode`. Discarding is `rm -rf` on the first and deleting the
second. Nothing is added to `lib/`, `codegen/`, `buildSrc/` or `gradle/libs.versions.toml` during
the probe; if step 3 needs a dependency, it goes in a throwaway build file under `/tmp`.

The one artifact worth keeping whatever happens is `observations.md` — the sizes from step 2 are
useful to `css-delivery` even if the extractor is never written.

## What must still work

**Nothing outside the discard path** — with one precondition that is easy to violate by accident.

Step 2 must compile Tailwind from a **copied** config under `/tmp`, never by editing
`example-app`'s. `:example-app:compileTailwind` runs in Docker and its output is what
`:e2e-tests` asserts against, so an in-place edit would put a probe's class list into the demo
application's stylesheet and turn a green e2e run into meaningless.

Preconditions: Docker for the Tailwind compile. No Chromium and no submodule work is needed —
this probe reads compiled classes and CSS, not DaisyUI's documentation.

After the probe, `git status` in the repository must be clean apart from this change's own
artifacts, and `./gradlew check` must pass unchanged.
