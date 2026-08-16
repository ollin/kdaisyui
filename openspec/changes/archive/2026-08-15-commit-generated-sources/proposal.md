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
3. ~~**The planned codegen work has no safety net.**~~ **Demoted to a side effect**
   (decided by Oliver, 2026-08-14 — see Assumptions 2). The port of the ~2.800 LoC Node
   generator is provable by an empty diff against a **throwaway** snapshot of
   `lib/build/generated/**`; tasks 1.1-1.4 already verified exactly this way, against
   in-memory hashes. Committed sources make that baseline ambient and give the API redesign
   a reviewable line-by-line record — welcome, but not what justifies the change.

The change stands on (1) and (2): the library is meant to be published and lived with —
API browsable in the repository, bumps reviewable as diffs. Those are permanent benefits
only committed sources provide; (3) was a one-time need that never required them.

## What Changes

- Generated Kotlin sources move out of `lib/build/` into a committed, explicitly-named
  generated source root, and are checked into git.
- **The build stops depending on the code generators.** `compileKotlin` no longer triggers
  `generateComponents` / `generateHeroicons`, and `compileTestKotlin` no longer triggers
  `generateComponentTests`. A fresh clone builds and tests with **no Node, no npm and no git
  submodules** — regeneration becomes an explicit, opt-in task run when DaisyUI or Heroicons
  is bumped.
- Regeneration is confirmed **deterministic** rather than made so — measured, not assumed
  (see Assumptions 1). The `startsWith('kdaisyui')` branches at `generator-new.js:344-348`
  and `generator.js:216-219` never match the real package prefix, but they are dead code, not
  a defect: the alphabetical order they fall through to already produces the grouping they
  intended. They are deleted for clarity, changing no output.
- CI gains a **drift check**: regenerate, then fail if the working tree is dirty. This is what
  keeps the committed sources honest and replaces the guarantee the build dependency used to
  give.
- ~~Stale `.gitignore` rules for the `components/` and `icons/` source paths are removed.~~
  **Already done.** Verified 2026-08-14: `.gitignore` contains no such rules; it ignores
  `build` and nothing else relevant. That cleanup landed with `adopt-openspec`.
- `AGENTS.md` is corrected: the "never edit generated code" rule must name the new committed
  path, and the version-source list must stop citing `.tool-versions`.
- **Folded in from the working tree** (pre-existing uncommitted work): the `.tool-versions`
  edit and the `README.md` AI-context table edit. The `.gitignore` additions for
  `**/.settings/`, `**/bin/` and the AI scratch directories are **already committed**
  (verified 2026-08-14). Note that `.tool-versions` is not emptied by removing `nodejs` and
  `just` — it still pins the JDK that runs Gradle, and nine places in the docs cite it.

No public API changes. No **BREAKING** changes for consumers.

## Capabilities

### New Capabilities

- `generated-sources`: How generated Kotlin is produced, stored, verified and kept from
  drifting — where it lives, that it is committed, that regeneration is deterministic and
  opt-in, and that CI rejects committed output which no longer matches its inputs.

### Modified Capabilities

None. `openspec/specs/` is empty; this is the first capability in the project.

## Assumptions

Carried over from this change's `gate.md`, which the gate-free workflow retired. Each one
names what would show it is wrong; `tasks.md` orders their verification first.

1. ~~**Regeneration is byte-identical across machines, locales and Node versions.**~~
   **VERIFIED — no longer an assumption.** Everything
   the CI drift check is worth rests on this. Measured 2026-08-14 (tasks 1.1-1.3):
   450 files, byte-identical across two consecutive runs and across `LANG=C` and
   `LANG=de_DE.UTF-8`. The `localeCompare` fall-through is real but harmless for pure-ASCII
   identifiers, and the two branches feeding it turned out to be unreachable dead code that
   changes no output. `codegen/package-lock.json` **is** committed (verified, since
   `c7b85cc`), so npm resolution is not a variance source.
   **Fully verified 2026-08-14** (task 1.4): a clean `ubuntu-latest` runner produced the same
   aggregate hash as this machine. The assumption is discharged.
2. ~~**A committed baseline is the only practical way to verify the codegen port.**~~
   **REFUTED — and that is fine** (decided by Oliver, 2026-08-14, tasks 2.1-2.2). A throwaway
   snapshot serves the port equally well; tasks 1.1-1.4 demonstrated the exact procedure
   (snapshot, regenerate, compare hashes) without committing anything. Justification (3) is
   therefore demoted to a side effect, and the change stands on (1) and (2) alone — which it
   does, because the library is meant to be published and lived with, not only ported.
3. **A committed diff makes a DaisyUI bump reviewable.** This is a claim about human
   attention, not about tooling, and it is the weakest of the three. *Wrong if:* the first
   bump after this change is approved without anyone commenting on a signature change. There
   is no cheap way to check it in advance; it is taken on faith deliberately, and named here
   so it is not mistaken for a verified requirement.

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
included (11 booleans on `daisyButton`, plain-collated imports). That is the point: it is a
baseline, not an improvement.
