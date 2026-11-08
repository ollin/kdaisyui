## MODIFIED Requirements

### Requirement: A pull request runs only the jobs its changes can affect

CI SHALL classify a pull request's changed files into one scope: `none` when every file is a
planning or agent file, `prose` when every file is one of those or prose documentation, and `full`
otherwise. Under `none` no test job SHALL run. Under `prose` exactly `unit-tests` and
`generated-sources-drift` SHALL run. Under `full` all test jobs SHALL run.

**Verified** that the two `prose` jobs are the only readers of prose documentation: the snippet
compile in `doc-snippets/build.gradle.kts` runs in `check`, and the `llms.txt` coverage gate in
`codegen/src/index-docs.ts` runs in `generateReferenceDocs`; every other hit for `README` or
`docs/` in a build script, Kotlin or TypeScript source is a comment or a test fixture (grep,
2026-11-01).

**Verified** that the planning and agent files are read by no test job: every hit in build
scripts, Kotlin and TypeScript is a comment (grep, 2026-11-01). The one job that reads one,
`openspec-config`, parses `openspec/config.yaml` and is not selected by the classification: it
runs on every pull request.

**Verified** end to end on 2026-11-01 with probe PRs: planning only ran no test job (#441), prose
only ran exactly the two (#443), and a broken snippet in a prose-only change still failed
`unit-tests` (#444).

*Wrong if:* a job that a `none` or `prose` pull request skipped fails on the next `full` run for a
reason that pull request introduced.

#### Scenario: A change to planning files only

- **WHEN** a pull request changes only files under `openspec/` and `.opencode/`
- **THEN** no test job runs
- **AND** every required check reports success

#### Scenario: A change to the documentation

- **WHEN** a pull request changes `README.md` and a file under `docs/tutorials/`
- **THEN** `unit-tests` and `generated-sources-drift` run
- **AND** `e2e-tests`, `mutation-tests`, `api-baseline` and `codegen-tests` are skipped

#### Scenario: A change to a generated reference page

- **WHEN** a pull request changes a file under `docs/reference/`
- **THEN** all test jobs run

#### Scenario: A change to source code

- **WHEN** a pull request changes any file outside the planning, agent and prose lists
- **THEN** all test jobs run
