# Tasks

Ordered uncertainty before dependency. Section 1 decides the shape of everything after it;
if 1.2 refutes the assumption, revise this change rather than working around it.

## 1. Establish whether the generator can express the popover method

- [x] 1.1 Add a throwaway `customParts` entry for `modal`
  (`{ "name": "Popover", "element": "DIV", "cssClass": "modal" }`) to
  `codegen/codegen-config.json`, run `just generate`, and record in the change notes what
  `Modal.kt` actually gained. Expected: a `daisyModalPopover` emitting `<div class="modal">`,
  with **no** `popover` attribute, because `generateCustomPartFunction`
  (`codegen/src/generator-new.js:269`) emits a fixed parameter list and a fixed body.
  Revert the config entry afterwards; this task produces evidence, not code.
  → `. d`

- [x] 1.2 Decide how the `popover` attribute gets emitted, and write the decision into
  `design.md`: extend `customParts` with a static-attributes field, or reject that and choose
  another mechanism. The requirement forbids pushing it onto callers via `attrs`, so "callers
  pass it themselves" is not an available answer. **If no mechanism is workable within the
  generator, stop and revise the proposal** — the subtractive option it already names
  (drop the popover clause from the spec) becomes the live one.
  → `. d`

## 2. Teach the generator to emit static attributes on a custom part

**Correction (task 1.2 follow-on).** Both tasks below originally called for "a codegen-level
assertion". There is no JS test infrastructure in `codegen/` — no runner, no test files, no
devDependencies — so that assertion had nowhere to live. Introducing one is a separate change.
What replaces it is stronger, not weaker: no component declares `staticAttributes` until task
3.1, so each of these tasks must leave `just generate` producing a **zero diff**, which proves
it changed nothing; and the field's real behaviour is pinned by the generated Kotlin test that
lands with the output in 3.2, which asserts the attribute in rendered HTML rather than
asserting a string inside the generator.

- [x] 2.1 Extend `generateCustomPartFunction` so a `customParts` entry may declare static
  attributes, emitted into the function body after `id` and ahead of `extraClasses` — so
  `attrs()` still runs last and can override them. No config uses the field yet, so
  `just generate` must produce a zero diff.
  → `. r`

- [x] 2.2 Mirror the same field in `codegen/src/test-generator.js` so a generated test asserts
  the rendered attribute, not only the class. Zero diff for the same reason.
  → `. r`

## 3. Configure the popover modal

- [ ] 3.1 Add the real `modal` `customParts` entry, carrying `popover`, to
  `codegen/codegen-config.json`. Config only; nothing regenerates until 3.2.
  → `. r`

- [ ] 3.2 Run `just generate`, review the diff under `lib/generated/`, and commit the
  regenerated output. Confirm `daisyModal` itself is untouched. The new public wrapper and the
  generated test that pins it land together here.
  → `. F` — regeneration is tool-produced and drift-checked, so it stays `.` however many lines
  it spans; precedent `2297257 . F Regenerate lib/generated at DaisyUI 5.7.16`

## 4. Hold the gates

- [ ] 4.1 Run `:lib:test` and root `koverVerify` with `--rerun-tasks`. If any branch of the new
  function is uncovered, fix the *test generator*, never the generated file.
  → `^ f` (or `. d` if already green and only evidence is recorded)

- [ ] 4.2 Confirm `generated-sources-drift` passes on the branch, so the committed tree matches
  its inputs.
  → `. d`

## 5. Prove it works in a browser

- [ ] 5.1 Render a popover modal on a demo route in `example-app`, opened by a `popovertarget`
  button. This is the first real consumer and will expose whether the wrapper is usable.
  → `^ F`

- [ ] 5.2 Add a Cucumber scenario and step asserting the served HTML carries the `modal` class
  and the `popover` attribute, and that the modal opens without JavaScript.
  → `^ F`

## 6. Leave the trail

- [ ] 6.1 Record in `.opencode/skills/kdaisyui-codegen/SKILL.md` that `customParts` can carry
  static attributes, and that an alternative construction method is configured there rather than
  through `componentElements` (which replaces the single root element instead of adding one).
  → `. d`

- [ ] 6.2 Check whether `llms.txt` enumerates modal methods for library consumers; update it if
  so. Skip with a note if it does not.
  → `. d`

## 7. Close the loop on the finding that started this

- [ ] 7.1 The archived `adapt-daisyui-5-6` recorded this requirement as satisfied when it was
  not. Add a line to that effect in this change's notes so the archive is not silently trusted
  next time — do not edit the archived change itself.
  → `. d`
