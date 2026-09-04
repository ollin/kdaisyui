# Tasks

Ordered uncertainty before dependency. Section 1 is a browser question, and everything after it
assumes a particular answer. If 1.1 refutes assumption 1, revise this change — the proposal names
the subtractive option (`skip` megamenu) and the weaker one (`popover` as a parameter rather than
a static attribute).

## 1. Find out what markup a working megamenu actually needs

- [ ] 1.1 Hand-write DaisyUI's documented megamenu markup — root `<div class="megamenu" popover>`
  with a `popovertarget` button, a `megamenu-active` span and two inner `<div popover>` panels —
  on a throwaway route in `example-app`, and open it in a browser at a **desktop** viewport and a
  **small** one. Record what is visible in each, and whether the root's `popover` has to be
  present in both. This is the assumption `megamenu.css` is too intricate to settle by reading.
  → `. d`

- [ ] 1.2 Decide from 1.1 whether `popover` on the root is a static attribute or a parameter, and
  write it into `design.md`. **If a `<div class="megamenu" popover>` cannot be made visible on
  desktop from generated markup alone, stop and revise the proposal.**
  → `. d`

## 2. Teach the generator to put static attributes on a main component

- [ ] 2.1 Lift `staticAttributes` from `generateCustomPartFunction` to the main component path in
  `codegen/src/generator-new.js`, reading a new `componentAttributes` config key. No component
  declares it yet, so `just generate` must produce a **zero diff** — that is the proof it changed
  nothing.
  → `. r`

- [ ] 2.2 Mirror it in `codegen/src/test-generator.js` so the generated component test asserts the
  attribute. Zero diff for the same reason.
  → `. r`

## 3. Configure megamenu

- [ ] 3.1 Add the `componentAttributes` entry for `megamenu` and the `customParts` entry for its
  inner panel (`cssClass: null`, `staticAttributes: { "popover": "" }`). Config only.
  → `. r`

- [ ] 3.2 Run `just generate`, review the diff under `lib/generated/`, commit the regenerated
  output. The new public wrapper and its generated tests land together.
  → `. F` — regeneration is tool-produced and drift-checked; precedent `2297257`

- [ ] 3.3 Re-dump the API baseline with `just update-api` and **read the diff**. This is an
  additive change, so no `README.md` migration entry should be needed — confirm that rather than
  assume it.
  → `. r`

## 4. Hold the gates

- [ ] 4.1 `:lib:test` and root `koverVerify` with `--rerun-tasks`. Any uncovered branch is fixed in
  the *test generator*, never in the generated file.
  → `^ f` (or `. d` if already green)

- [ ] 4.2 Confirm `generated-sources-drift` and `api-baseline` both pass on the branch.
  → `. d`

## 5. Prove it opens in a browser

- [ ] 5.1 Replace the megamenu card in `example-app`'s `WhatsNewFragment.kt` with one built from
  the generated wrappers and opened by a `popovertarget` button — or give it its own route if the
  htmx-loaded fragment cannot be reached with JavaScript disabled, as was the case for the modal.
  Decide which from 1.1's finding.
  → `. r` for the page, then `^ F` with its scenario — a route with no test cannot be `^`
  (the lesson from `support-popover-modals` 5.1)

- [ ] 5.2 Add a Cucumber scenario asserting the served HTML carries `class="megamenu"` and
  `popover`, and — tagged `@nojs` — that a panel reaches `:popover-open` on a click. The `@nojs`
  hook and the `:popover-open` steps already exist from `support-popover-modals`.
  → `^ F`

## 6. Settle the two loose ends this change found

- [ ] 6.1 Decide whether `megamenu-active` must be a `<span>` (DaisyUI documents it so and calls
  it mandatory; the generator emits a `div`). Fix via `subComponentElements` if it matters, record
  that it does not if it does not.
  → `. r` or `. d`

- [ ] 6.2 Update the `kdaisyui-codegen` skill: `componentAttributes` next to `staticAttributes`,
  and remove megamenu from the list of components still carrying this defect — that line was added
  by `support-popover-modals` and becomes false here.
  → `. d`
