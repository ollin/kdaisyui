## Why

`openspec/specs/daisyui-component-coverage/spec.md` already requires that regeneration surface
"the `modal` popover attribute". It does not, and nothing noticed: `daisyModal` emits
`<dialog class="modal">` only, no test fails, and `generated-sources-drift` stays green because
nothing about the popover method is a class name. The requirement was archived as satisfied by
`adapt-daisyui-5-6`, so `openspec/specs/` currently asserts something untrue.

The requirement is also unbuildable as written. It says the modifier arrives "as new enum entries
or parameters on the corresponding existing wrappers", but DaisyUI's popover modal is not a
modifier of the dialog method — it is a different construction method with a different root
element (`<div class="modal" popover>` opened by `popovertarget` buttons). No parameter on a
`<dialog>` wrapper can produce it.

DaisyUI documents four modal methods. Method 1 (dialog) is implemented; methods 3 and 4 are
marked Legacy; method 2 (popover) is current and unreachable from the DSL.

## What Changes

- Make the popover modal method expressible from Kotlin, as a generated wrapper — not hand-written,
  and not by editing `lib/generated/**`.
- **Correct the existing requirement** so it distinguishes a *modifier* (a class toggled by a
  parameter) from an *alternative construction method* (a different element). The present wording
  cannot be satisfied and would mislead the next reader the same way it misled this one.
- Record in the spec that method 3 (checkbox) and method 4 (anchor link) are deliberately
  unsupported, so their absence stops reading as an oversight.

**Considered and rejected — the subtractive option:** delete the popover clause from the
requirement and declare the dialog method the only supported one. That would make the spec true
again for one line of work, and it is genuinely tempting given `<dialog>` is DaisyUI's own
recommendation and is strictly more capable (it locks background interaction; popover does not).
It is rejected because popover is the only method that opens and closes with **no JavaScript**,
which is precisely the audience of a server-rendered HTML library — the same reason this project
pairs with htmx. Dropping it would quietly narrow what kdaisyui can express server-side.

Out of scope, and owned elsewhere:

- The one-element-per-component limit in general. `findComponentInSyntax` picks a single element
  per component and the same limit affects `menu` (which may now be a `<menu>` element). This
  change fixes modal only; a general mechanism is a separate change if the pattern recurs a third
  time.
- Responsive class prefixes (`md:footer-horizontal`), reachable today only via `extraClasses`.
- The DaisyUI version. This change does not move it.

## Capabilities

### New Capabilities

None. The behaviour belongs to an existing capability.

### Modified Capabilities

- `daisyui-component-coverage`: the "New modifiers on existing components are exposed" requirement
  currently claims the modal popover attribute is surfaced as a parameter. Split the conflated
  concepts, state the popover method as its own requirement with its own scenarios, and name
  methods 3 and 4 as out of scope.

## Impact

- `codegen/codegen-config.json` and/or `codegen/src/generator-new.js` — a component must be able to
  emit a second root wrapper. Whether the existing `parts` mechanism already suffices is the
  central open assumption and is checked first in `tasks.md`.
- `lib/generated/main/kotlin/io/github/ollin/kdaisyui/components/Modal.kt` — regenerated output.
- `lib/generated/test/kotlin/.../ModalTest.kt` — generated tests; the 100% line-and-branch gate
  applies to every new branch.
- `example-app` and `e2e-tests` — house rule: no UI change ships without E2E coverage.
- No change to `gradle/libs.versions.toml`, and no release.
