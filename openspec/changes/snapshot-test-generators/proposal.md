## Why

After `snapshot-test-coverage-generator`, the generators that unit tests barely reach are the
component emitter and the Heroicons pipeline. Full StrykerJS run, 2026-11-01:

| File | Survivors |
|---|---|
| `generator-new.ts` | 199 |
| `generator-heroicons.ts` | 101 |
| `test-generator-heroicons.ts` | 88 |
| `parser/svg-heroicons.ts` | 76 |

Their outputs are committed and drift-checked, like the coverage tests were. Their inputs are not
all in the repository: the component emitter takes descriptions from DaisyUI's `+page.md`, and the
icon emitter takes four SVGs per icon from the Heroicons submodule. Neither submodule is checked
out in `codegen-tests`, and codegen tests read none by decision (`kdaisyui-testing`). Oliver chose
on 2026-11-01 to vendor a few inputs as fixtures instead of changing that decision.

## What Changes

- **Fixtures under `codegen/test/fixtures/`**, copied verbatim from the pinned submodules: the
  frontmatter of three DaisyUI pages (`kbd`, `card`, `dropdown`: a small one, one with parts and
  enums, one with custom constructions), and the four SVGs of two icons. Each fixture directory
  says where it came from and how to refresh it.
- **Three snapshot tests**, each comparing a generator with committed output:
  1. the component emitter, from the committed model and a fixture's descriptions, against
     `lib/generated/main/…/components/<Name>.kt`;
  2. the icon emitter, from a fixture's four SVGs, against `lib/generated/main/…/icons/<Name>.kt`;
  3. the icon test generator, from the committed icon files, against the committed
     `HeroIconsGeneratedTest.kt`. It needs no fixture.
- **A refresh recipe**, `just refresh-codegen-fixtures`, copies the fixtures from the submodules
  again. `kdaisyui-daisyui-upgrade` runs it after a bump; a stale fixture fails its snapshot test
  with a message naming the recipe.
- What still survives is measured and recorded, as before.

**Subtractive option considered:** check the submodules out in `codegen-tests` and compare every
component and icon, with no fixtures. Rejected by Oliver: it reverses the documented decision, and
`node --test` would fail on a clone without submodules unless the tests skipped there, which the
house rule forbids.

## Deliberately out of scope

- The documented-element half of `test-generator.ts` (267 survivors). It reads the examples of a
  whole page, not just its frontmatter; vendoring those is a larger fixture decision.
- Join-scope components. `withDeclaredScopes` needs every join item's model, which is committed,
  but the fixture set stays small; the three chosen components are in no join scope.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A component's frontmatter alone yields the descriptions its generated file carries | probe | open, task 1.1 |
| A2 | `parseIconFiles` given four fixture directories yields the icon the committed file was generated from | probe | open, task 1.1 |
| A3 | The icon test generator is a pure function of the committed icon files | reading | open, task 1.2 |
| A4 | The three tests together kill most of the four files' mutants | measurement | open, task 4.1; *wrong if* more than a third survive in `generator-new.ts` |

## Capabilities

<!-- skip_specs: generated output and every gate are unchanged. -->

## Impact

- **New**: `codegen/test/fixtures/daisyui/<component>/+page.md` (frontmatter only),
  `codegen/test/fixtures/heroicons/<size>/<style>/<icon>.svg`, three test files, one `just` recipe.
- **Edited**: generator entry points only where a pure function has to be split out; the
  `kdaisyui-daisyui-upgrade` skill (the refresh step); `kdaisyui-codegen` (the fixtures).
- No generated file changes.
