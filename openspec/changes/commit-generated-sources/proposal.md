## Why

The library's entire public API — 63 DaisyUI components, 324 Heroicon functions and 63
component test files — exists only inside `lib/build/`. It is never committed. Three costs
follow from that:

1. **The API is invisible.** Nobody can read what `daisyButton` accepts by browsing the
   repository. A fresh clone shows ~200 lines of hand-written Kotlin and nothing else; the
   IDE cannot resolve a single component until a full build has run `git submodule` checkout,
   `npm install` and three Node generators.
2. **A DaisyUI bump is unreviewable.** Raising `daisyui.version` changes every generated
   signature and produces a diff of zero lines. There is no way to see what a version bump
   did to the API, which is exactly why the bump is marked "never automerged" in
   `gradle.properties` — the safeguard exists because the evidence does not.
3. **The planned codegen work has no safety net.** Porting the ~2.800 LoC Node generator to
   Kotlin, and redesigning the emitted API, both change generated output. Without a committed
   baseline neither can be verified; with one, the port is provable by an empty diff and the
   redesign becomes a readable, line-by-line record of every signature that changed.

This is the precondition for the rest of the renovation, so it goes first.

## What Changes

- Generated Kotlin sources move out of `lib/build/` into a committed, explicitly-named
  generated source root, and are checked into git.
- **The build stops depending on the code generators.** `compileKotlin` no longer triggers
  `generateComponents` / `generateHeroicons`, and `compileTestKotlin` no longer triggers
  `generateComponentTests`. A fresh clone builds and tests with **no Node, no npm and no git
  submodules** — regeneration becomes an explicit, opt-in task run when DaisyUI or Heroicons
  is bumped.
- Regeneration is made **deterministic**: byte-identical output for identical inputs. This
  requires fixing the import ordering, whose `startsWith('kdaisyui')` branch never matches
  (`io.github.ollin.kdaisyui…`) and silently degrades to byte order.
- CI gains a **drift check**: regenerate, then fail if the working tree is dirty. This is what
  keeps the committed sources honest and replaces the guarantee the build dependency used to
  give.
- Stale `.gitignore` rules for `lib/src/main/kotlin/io/github/ollin/kdaisyui/components/`,
  `…/test/…/components/` and `…/icons/*` are removed — codegen stopped writing there, so they
  currently protect nothing while implying generated code lives in `src/`.
- `AGENTS.md` is corrected: the "never edit generated code" rule must name the new committed
  path, and the version-source list must stop citing `.tool-versions`.
- **Folded in from the working tree** (pre-existing uncommitted work): removal of
  `.tool-versions`, the `.gitignore` additions for `**/.settings/`, `**/bin/` and the AI
  scratch directories, and the `README.md` AI-context table edit.

No public API changes. No **BREAKING** changes for consumers.

## Capabilities

### New Capabilities

- `generated-sources`: How generated Kotlin is produced, stored, verified and kept from
  drifting — where it lives, that it is committed, that regeneration is deterministic and
  opt-in, and that CI rejects committed output which no longer matches its inputs.

### Modified Capabilities

None. `openspec/specs/` is empty; this is the first capability in the project.

## Impact

**Build**
- `lib/build.gradle.kts` — generated source roots, removal of the `compileKotlin` /
  `compileTestKotlin` codegen dependencies, regeneration tasks become explicit.
- `justfile` — a recipe that regenerates and shows the resulting diff.
- `.github/workflows/ci.yml` — new drift-check job; the existing unit and e2e jobs get faster
  and no longer need `submodules: recursive` for a plain build.

**Repository**
- ~450 new committed files (63 components, 324 icons, 63 generated tests), roughly 1 MB.
  `.gitattributes` marks them `linguist-generated` so they do not distort language statistics
  or clutter pull-request reviews by default.
- `.gitignore`, `.tool-versions`, `README.md`, `AGENTS.md`.

**Deliberately out of scope**
- Rewriting the generator in Kotlin — change `codegen-to-kotlin`.
- Removing the `llms.txt` parser and the DaisyUI 5.5.20 ceiling — change
  `drop-llms-txt-ceiling`.
- Any change to the emitted API — change `daisyui-taxonomy-api`.

The generated output committed by this change is therefore the *current* output, warts
included (11 booleans on `daisyButton`, byte-ordered imports). That is the point: it is a
baseline, not an improvement.
