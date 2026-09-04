# Notes

Evidence gathered while working the tasks. Newest section last.

## 1.1 — `customParts` alone cannot express the popover method (2026-09-04)

**Probe.** Added a throwaway entry to `codegen/codegen-config.json`:

```json
"modal": [
  { "name": "Popover", "element": "DIV", "cssClass": "modal" }
]
```

then ran `:lib:generateComponents` and `:lib:generateComponentTests` with `--rerun-tasks`,
read the diff under `lib/generated/`, and reverted both the config entry and the output.

**Observed.** `Modal.kt` gained exactly what the task predicted:

```kotlin
/** Renders `<div class="modal ...">`. */
fun FlowContent.daisyModalPopover(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
```

The element is right and the class is right. **The `popover` attribute is absent**, and there
is no configuration key that would add it: `generateCustomPartFunction`
(`codegen/src/generator-new.js:269`) builds `params` and `body` from fixed literals, reading
only `name`, `element`, `cssClass` and `receiver` off the config entry.

**Assumption 1 of this change is therefore confirmed, not refuted.** `customParts` gets the
shape of the wrapper right and stops one attribute short.

**Two further observations, both bearing on section 2.**

1. The test generators already follow `customParts` without being told to. The same probe
   produced a `custom_part_popover_renders_div` case in `ModalTest.kt` and a
   `modalPopover_defaults` / `modalPopover_all_flags` pair in `ModalCoverageTest.kt`. So
   task 2.2 is an extension of a working path, not new wiring — and the coverage gate will
   cover a new branch automatically once the generator emits one.
2. Every generated assertion is about **classes** (`substringAfter("class=\"")`) or about
   `data-*` markers passed through `attrs`. Nothing asserts a bare valueless attribute today.
   That is the same blind spot the proposal names as the reason this defect survived
   `adapt-daisyui-5-6`: a construction method that adds no CSS class is invisible to both the
   generated tests and `generated-sources-drift`. Task 2.2 has to close it deliberately.

## 4.1 — gates green with no test-generator fix needed (2026-09-04)

- `:lib:test --rerun-tasks` — 1478 passed, 0 failed, 0 skipped.
- `koverVerify --rerun-tasks` — 1491 passed (adds `:ktor-integration`), gate held at 100%
  line **and** branch.

No branch of `daisyModalPopover` was left uncovered, so the escape hatch in this task ("fix the
*test generator*, never the generated file") went unused. That is not luck: the generated
coverage test already exercises every parameter of a custom part, and the new function adds no
parameter — the static attribute is unconditional, so it introduces no branch at all. A
`staticAttributes` entry can therefore never open a coverage hole in the generated Kotlin.
The branch it *does* add is in the generator, which Kover does not measure and no JS test
covers; what stands behind that one is the zero diff in tasks 2.1 and 2.2.

## 4.2 — no drift (2026-09-04)

Ran the drift job's own command locally — all four generators
(`:lib:generateComponents :lib:generateComponentTests :lib:generateHeroicons
:lib:generateHeroiconTests`) with `--rerun-tasks` — then `git status --porcelain`, which is
what `ci.yml:39` checks. Working tree clean: the committed `lib/generated` matches what the
generators produce from the current config.

## 7.1 — `adapt-daisyui-5-6` archived this requirement as satisfied when it was not

That change closed 20/20 carrying a requirement that regeneration surfaces "the `modal` popover
attribute". Nothing implemented it. The archived change is left untouched — rewriting an archive
hides the lesson instead of recording it — so the correction lives here.

**How it survived every gate.** The popover method adds no CSS class, and that single fact
defeats the whole safety net at once:

- generated component tests assert **class strings**, so there was nothing to fail;
- `generated-sources-drift` compares regenerated output against committed output, and the two
  agreed — the generator was consistently wrong, which a drift check cannot see;
- coverage stayed at 100%, because the code that would have been uncovered was never generated;
- and the task that recorded it as done was satisfied by reading the regenerated diff, in which
  the absence of a thing does not appear.

**What to take from it.** A requirement of the form "the new modifier is exposed" can be checked
by reading a diff. One of the form "the component is reachable" cannot — it needs something that
*uses* it. That is why this change ends in an example-app route and an E2E scenario rather than
in a generated test: a consumer is the only gate that would have caught the original defect.

And the mirror deserves the same suspicion as the code. A change reading 20/20 with none of that
work done was trusted precisely because it looked complete. Ticking a box is a claim, worth
exactly as much as the check behind it.
