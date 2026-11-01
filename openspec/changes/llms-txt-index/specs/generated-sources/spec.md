## MODIFIED Requirements

### Requirement: Committed output that no longer matches its inputs is rejected

CI SHALL regenerate from the committed inputs and fail when the result differs from what is
committed. This replaces the guarantee the build-time dependency used to provide. The
regeneration SHALL cover **every** generator, including the icon render tests, the reference
documentation, **and the two agent-facing files `llms.txt` and `llms-full.txt`**.

**Verified, in part**, and separately for each scope the requirement claims. Kotlin: the drift
job was observed failing on a hand-edited generated file (PR #231, 2026-08-15) while the test
jobs passed on the same commit. Reference documentation: the same observation was repeated for
the extended scope on PR #339, 2026-09-12 — a hand-edited `docs/reference/megamenu.md` failed the
job, which named the file, while `unit-tests`, `codegen-tests`, `mutation-tests` and
`api-baseline` all passed on that commit. `llms.txt`: in the job's paths since 0.7.0
(`.github/workflows/ci.yml`, the `git status --porcelain` line). `llms-full.txt`: the
`llms-txt-index` change adds it to the same line and the same Gradle task's outputs.

The second observation is the one that measures the gap this requirement exists to close: four
other gates read a page documenting the wrong lambda receiver and none of them objected.

What is still Assumed is durability — two green runs prove the job works, not that it keeps
working.

*Wrong if:* the drift job reports a diff on a run where no input changed.

A generator that is missing from the drift job is worse than one that is absent, because the
committed tree then drifts silently. That failure mode is not hypothetical: a stale input path
in `test-generator.ts` removed 66 coverage files and 8% line coverage while every build stayed
green, and it was caught by regenerating rather than by any test.

Documentation left outside the job is the same hazard with a slower fuse, and it has already
fired: `docs/reference/megamenu.md` documented a `DIV` lambda receiver for five days after the
element became a `<span>`, and shipped wrong in v0.4.0 on 2026-09-12. `llms.txt` fired it too:
hand-maintained until 0.7.0, it still showed the 0.1.x API a release after 0.6.0 had replaced it.

#### Scenario: Someone edits generated output by hand

- **WHEN** a commit modifies a committed generated file without changing any generator input
- **THEN** the drift check fails
- **AND** the failure names the files that differ

#### Scenario: Someone bumps DaisyUI without regenerating

- **WHEN** a commit raises the `daisyui` version but leaves the generated sources untouched
- **THEN** the drift check fails

#### Scenario: An ordinary change touching no generated input

- **WHEN** a commit changes only hand-written Kotlin
- **THEN** the drift check passes

#### Scenario: A generator input directory is missing

- **WHEN** a generator's declared input directory does not exist
- **THEN** the build fails
- **AND** it does not silently produce a partial set of generated files

#### Scenario: A component changes its rendered element without its page being regenerated

- **WHEN** a commit changes what a component renders but leaves `docs/reference/` untouched
- **THEN** the drift check fails
- **AND** it names the reference page that differs

#### Scenario: Someone edits an agent-facing file by hand

- **WHEN** a commit modifies `llms.txt` or `llms-full.txt` without changing a template, the
  model or a generator
- **THEN** the drift check fails
- **AND** it names the file that differs
