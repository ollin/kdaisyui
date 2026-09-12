# Tasks

Ordered by uncertainty before dependency. Section 1 checks the three assumptions the change rests
on; a red result there rewrites the plan rather than being worked around.

## 1. Check the assumptions before building on them

- [ ] 1.1 `. r (internal)` Assert in a codegen unit test that the current `noContent` list is
  exactly the set of components whose element is void. Green says deriving loses nothing; red says
  the rule is too narrow and **the change must be rewritten**.
- [ ] 1.2 `. r (internal)` Assert that the docs route — the tag carrying `$$<componentClass>` in a
  fenced ```html block of `+page.md` — yields an element for all 66 components, and that it
  disagrees with the generator exactly three times (`otp`, `tab`, `calendar`). A fourth
  disagreement is a finding that belongs in the proposal before the guard lands.
- [ ] 1.3 `. r (internal)` Assert that exactly two config entries are unreachable, both in
  `noContent`. More would mean the consumption guard cannot land green.

## 2. The void-element rule

- [ ] 2.1 `^ r (internal)` Add the void-element set to `component-shape.ts` with a comment naming
  the HTML specification, and a predicate on `TagClass`. Nothing reads it yet.
- [ ] 2.2 `^ B (internal)` Derive the content parameter from the element instead of
  `ComponentConfig.noContent`. Test first: the failing assertion is that `daisyMask`,
  `daisyFileInput` and `daisyThemeController` declare no `content`.
- [ ] 2.3 `. d` Delete the `noContent` section from `codegen-config.json`.
- [ ] 2.4 `^ B` Regenerate; read the diff — three component files and their tests lose a parameter,
  nothing else. Commit generated Kotlin and tests.
- [ ] 2.5 `^ B` Regenerate `docs/reference/` and commit.

## 3. The element cross-check

- [ ] 3.1 `^ F (internal)` Parse the documented root element from `+page.md`'s fenced ```html
  blocks via the `$$` marker. Unit-tested against `swap` (`<label>`), `menu` (`<ul>`) and
  `modal` (`<dialog>`) — three of the nine the old route could not answer.
- [ ] 3.2 `^ F (internal)` Fail generation when the chosen element disagrees and no exception is
  recorded, naming component, chosen and documented element.
- [ ] 3.3 `^ F (internal)` Fail generation on an exception that is no longer needed, so the list
  cannot accumulate.
- [ ] 3.4 `. d` Record the `tab` and `calendar` exceptions with a reason and a tracking issue each.
  Open those two issues first — an exception pointing at nothing is the thing this guard exists to
  prevent.
- [ ] 3.5 `^ B` Add `componentElements: {"otp": "label"}`; regenerate; commit Kotlin, tests and
  reference pages.
- [ ] 3.6 `^ B` Follow the receiver change in `example-app/.../WhatsNewFragment.kt`, and confirm the
  E2E scenario covering that page still passes.

## 4. The citations

- [ ] 4.1 `^ B (internal)` Build the `// Source:` header from `ComponentSource.componentDir`.
  Test first: a multi-word component cites its real directory.
- [ ] 4.2 `^ B (internal)` Use `htmlTag` rather than `tagBuilder` in generated doc-comment prose,
  leaving emitted code on `tagBuilder`. Test first, with `FIELDSET`.
- [ ] 4.3 `. B` Regenerate and commit: 10 headers and 2 doc comments change, no signature does.

## 5. The consumption guard

- [ ] 5.1 `^ F (internal)` Record consumed keys in `readComponentConfig` and fail the run on any
  component-keyed entry nothing read, naming section and key.
- [ ] 5.2 `. r (internal)` Prove it bites: a unit test adds an unread entry and asserts the failure
  names it.

## 6. The Kotlin API baseline

- [ ] 6.1 `^ F (internal)` Emit a stable Kotlin signature dump from `ComponentShape` — name,
  receiver, parameter names, types with lambda receivers, order, defaults. Unit-tested for
  stability: same shape in, byte-identical dump out.
- [ ] 6.2 `^ F (internal)` Add `updateComponentApi` and `checkComponentApi` tasks. `check` depends
  on the second; **`just generate` must not run the first** — that is what makes it a gate.
- [ ] 6.3 `. d` Commit the baseline `lib/api/components.api`.
- [ ] 6.4 `^ F (internal)` Add the CI step, alongside `api-baseline` rather than inside it, so an
  erased-by-JVM change and a JVM-visible one are distinguishable in the checks list.
- [ ] 6.5 `. r (internal)` Prove it bites: flip a lambda receiver locally, observe
  `checkComponentApi` fail where `checkKotlinAbi` passes, revert. Record both results — the
  passing one is half the point.

## 7. Migration and documentation

- [ ] 7.1 `. d` **How to migrate** in `README.md`: `daisyFileInput`, `daisyThemeController` and
  `daisyMask` lose their trailing lambda; `daisyOtp`'s receivers change `DIV` → `LABEL`. Say why
  for each — the element cannot hold children; the element was wrong — and note that the `otp`
  change is invisible in `lib/api/lib.api`.
- [ ] 7.2 `. d` Update the `kdaisyui-codegen` skill: `noContent` is gone and derived, the element
  is cross-checked against the docs route, config entries are consumption-checked, and there is a
  second API baseline with a different question.

## 8. Gate and adoption

- [ ] 8.1 Full green per `openspec/config.yaml` — repo-wide compile, complete suite including e2e,
  coverage, `analyze_change_set`.
- [ ] 8.2 `openspec validate --all --strict`.
- [ ] 8.3 Write the evaluation under `./tmp/` for Oliver to adopt. Name the breaking surface —
  four functions — and the two deferred defects explicitly.
