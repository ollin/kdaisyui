## Context

See proposal.md — Why. The generators run as three `Exec` tasks in
`lib/build.gradle.kts:149-192`, wired into compilation at `:194-201`, writing into
`lib/build/generated/sources/kotlin/{main,test}` which `sourceSets` adds as source roots at
`:128-135`. `.gitignore:6` ignores `build`, so none of it is ever committed.

The change's three assumptions are listed in proposal.md — Assumptions. This design exists
mainly to say what happens **when the first one is refuted**, because that determines how
much of the rest survives.

## Goals / Non-Goals

**Goals**
- Generated Kotlin readable in a fresh clone, no toolchain required.
- Build decoupled from Node, npm and submodules.
- A drift check that is trustworthy enough to be believed when it fails.

**Non-Goals**
- Changing what the generators emit. The committed output is today's output, warts included.
- Rewriting the generator (`codegen-to-kotlin`), removing the 5.5.20 ceiling
  (`drop-llms-txt-ceiling`) or redesigning the API (`daisyui-taxonomy-api`).

## Decisions

### Verify determinism before building anything on it

Both Assumed requirements in the spec reduce to one question, so it is checked first, by
regenerating twice and diffing — same machine, then two `LANG` values, then the CI runner
against a local baseline.

*Alternative rejected:* commit the sources first and let the drift job discover
non-determinism in CI. Rejected because a drift job that fails on its first real run teaches
everyone to ignore it, and the failure would arrive after ~450 files are already in history.

### ~~Fix the import comparator before capturing the baseline~~ — refuted 2026-08-14

This decision said the comparator had to be fixed first, so that a later fix would not
produce a large noise diff that buried the signal. Section 1 measured the premise and it is
false: **the fix produces no diff.** All 1911 generated import lines fall under `io`,
`kotlin` and `kotlinx`; `io` already sorts first, so the intended grouping is what plain
alphabetical order gives, and the branches — which test for a literal `kdaisyui` prefix that
zero imports have — cannot fire.

What survives is smaller and differently shaped: two unreachable branches to **delete**, at
no ordering cost, sequenced wherever convenient. Recorded as task 3.1.

The wider lesson for this change: the fall-through is real and observable (the order is
ICU-collated, not byte-ordered), but "observable" and "a drift risk" are different claims,
and the second one did not survive being measured.

### The enabling half and the desired half are separate

**Enablers** (behaviour-preserving, provable by diff):
- deterministic ordering — output changes once, then never again for the same input;
- moving the source root — the same bytes, at a different path.

**Desired changes** (new behaviour):
- the build no longer runs the generators;
- CI rejects drift.

The enablers land first and are verifiable by regenerating and diffing. Only then does the
build wiring change, so a broken build cannot be confused with a broken generator.

### Where the committed sources live

A dedicated top-level source root per module — a sibling of `src/`, not inside it — so that
"never edit generated code" stays a statement about a directory rather than about a naming
convention. `sourceSets` already takes an arbitrary `srcDir` (`lib/build.gradle.kts:128-135`),
so only the path changes.

*Alternative rejected:* put them under `lib/src/main/kotlin/…/components/`. Rejected because
generated and hand-written Kotlin would then be interleaved in one tree, and the one rule
this project repeats most often is that the two must not be confused.

## Risks / Trade-offs

- ~~**Determinism does not hold across machines**~~ → discharged 2026-08-14: tasks 1.1-1.4
  measured byte-identical output across runs, locales and a clean `ubuntu-latest` runner.
  The risk this line guarded against did not materialise.
- **The `linguist-generated` attribute only affects GitHub's rendering** → local `git diff`,
  `git log -p` and IDE search still show ~450 files. Mitigation: none available; this is a
  cost, and it is the cost assumption 3 is supposed to justify.
- **A drift check adds a CI job that can fail for reasons unrelated to the author's change**
  → mitigated only by task 1 being convincing. If task 1 is marginal rather than clean, do
  not build the job.
- **Decoupling the build hides a stale-submodule problem** → today a wrong submodule state
  produces wrong output loudly, at build time; afterwards it produces nothing until someone
  regenerates. The drift check is what replaces that signal, which is another reason it is
  not optional.

## Migration Plan

Regeneration becomes opt-in via a `just` recipe alongside the existing `generate` and
`generate-heroicons` (`justfile:15-20`). Rollback is restoring the four `dependsOn` lines at
`lib/build.gradle.kts:194-201`; the committed sources can stay in place while coupled, so
rollback does not require deleting them.

## Open Questions

None that are deferrable. The three that mattered are assumptions with tasks against them,
not open questions — per the schema, a question that would change what gets built is not
allowed to be deferred.
