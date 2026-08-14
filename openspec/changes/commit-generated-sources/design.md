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

### Fix the import comparator before capturing the baseline, not after

`generator-new.js:344-348` never matches its own predicate, so ordering is decided by
`localeCompare`. Whether or not the fall-through is the *cause* of any drift, the baseline
should be captured from a comparator that means what it says — otherwise the first later fix
produces a large diff that is pure noise and buries the signal.

*Alternative rejected:* leave it and treat the resulting order as the contract. Rejected
because the contract would then be "whatever this machine's collation does", which is not a
contract.

*Alternative rejected:* fix it as part of `daisyui-taxonomy-api` later. Rejected because
that change is about the emitted API; this is about the emitted *bytes*, and it blocks this
change rather than that one.

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

- **Determinism does not hold across machines** → the whole drift check is worthless and
  requirement 4 has to go. This is why it is task 1. If it fails, the change shrinks to
  "commit a baseline, keep the build coupled", or is abandoned in favour of the throwaway
  baseline (assumption 2).
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
