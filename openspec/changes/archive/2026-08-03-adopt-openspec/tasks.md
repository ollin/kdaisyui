Each task is sized to become exactly one Risk-Aware (Arlo) commit. The intended prefix is
given per task. No task changes production code, so every one is `.` (no risk) with intention
`d` (documentation) or `r` (refactoring).

Sub-bullets are the definition of done inside a task, not separate commits.

## 0. Working-tree hygiene

- [x] 0.1 `. d` Complete the AI-scratch ignore rules in `.gitignore`
  - Pre-existing uncommitted work, kept deliberately: `**/.settings/`, `**/bin/`, `.omo/`,
    `.junie/`, `.playwright-mcp/`. Add the missing `.air/`
  - Independent of this change's subject, but landing it first keeps `git status` readable
    for every task that follows
  - Verify: `git status` lists no scratch directory as untracked

## 1. Commit the CLI scaffold

- [x] 1.1 `. d` Commit the OpenSpec 1.7.0 scaffold verbatim
  - `openspec/config.yaml`, `openspec/specs/`, `openspec/changes/`
  - `.opencode/skills/openspec-{propose,apply-change,update-change,sync-specs,archive-change,explore}/`
  - `.opencode/commands/` — the six `opsx-*` slash commands
  - **Not** `.github/prompts/` or `.github/skills/` — those are the Copilot integration and
    are removed in task 3.2
  - No hand edits to any of it — it is CLI output and must stay diffable against the next
    `openspec update`
  - Note: `openspec/specs/` is empty and git does not track empty directories, so it appears
    only once the first spec is written
  - Verify: `git status` shows none of these paths as untracked
- [x] 1.2 `. d` Commit this change's own planning artifacts
  - `openspec/changes/adopt-openspec/` — authored, not CLI output, hence a separate commit
  - Verify: `openspec validate adopt-openspec --strict` passes from a clean tree
- [x] 1.3 `. d` Commit the in-progress `commit-generated-sources` proposal
  - Only `proposal.md` exists so far; the remaining artifacts are written when that change
    is planned out
  - Verify: `openspec list` resolves both changes

## 2. Fill `openspec/config.yaml`

- [x] 2.1 `. d` Add the `context` block
  - Generated vs. hand-written source boundary; `io.github.ollin.kdaisyui` as the package;
    `gradle.properties` as the single version source; the four modules; no release automation
  - Link to AGENTS.md rather than restating it (design.md — Decision 1)
  - Verify: `openspec instructions proposal --change adopt-openspec --json` returns the new
    text in its `context` field
- [x] 2.2 `. d` Add the `rules` block
  - `tasks`: every task must be sized as one Arlo commit
  - `proposal`: state the subtractive option next to the additive one
  - Verify: the rules appear in the `rules` field of `openspec instructions` for those
    artifacts
- [x] 2.3 `. d` Add `operations.apply.guidance`
  - The definition of green **for this repo**: repo-wide `compileTestKotlin`, `:lib:test`
    **and** `:e2e-tests:test` (a scoped run is not green), coverage of new code, CodeScene
    change-set scan
  - Commit-after-every-task cadence
  - Verify: `openspec instructions apply --change adopt-openspec --json` serves it
- [x] 2.4 `. d` Add `operations.archive.guidance`
  - Archive on the implementation branch, in the same MR as the code
  - Verify: `openspec instructions archive --change adopt-openspec --json` serves it

## 3. Remove the GitHub Copilot integration

- [x] 3.1 `. d` Delete `.github/copilot-instructions.md`
  - Per design.md — Decision 2: Copilot is not used
  - Verify: no tracked file outside `daisyui/` mentions Copilot
- [x] 3.2 `. d` Keep `.github/prompts/` and `.github/skills/` out of the repository
  - Both were created by `openspec init` for Copilot and are still untracked — delete them
    from the working tree rather than committing them in task 1.1
  - Verify: `git status` lists neither as untracked; `.opencode/skills/openspec-*` is
    unaffected

Scope note, corrected during implementation: the `README.md` Copilot row was **not** already
gone. Oliver's uncommitted edit removing it was reverted while cleaning the working tree, so
task 3.1 had to remove it again after deleting the file it linked to.

The `.gitignore` and `.tool-versions` work also landed here rather than in
`commit-generated-sources`: `.tool-versions` pinned `nodejs 24.16.0`, which is not installed,
which broke both the Gradle codegen and the `openspec` CLI inside the repo. That had to be
fixed before anything in this change could be verified.

## 4. Point the agent guide at the workflow

- [x] 4.1 `. d` Add an OpenSpec section to `AGENTS.md`
  - Where changes live, that `openspec/config.yaml` carries the planning rules, and that
    `skip_specs: true` is the correct answer for a change with no behavior delta
  - Verify: a reader of AGENTS.md alone can find the planning workflow

## 5. Verification gate

- [x] 5.1 `. r` Confirm the change validates and the build is untouched
  - `openspec validate adopt-openspec --strict` passes
  - `openspec validate --scope changes` reports `commit-generated-sources` as invalid — it
    has only `proposal.md` so far. Expected, and not this change's problem
  - `./gradlew :lib:test` still green — proves this change touched no production path
  - `openspec instructions apply` serves both the `context` and the `operationGuidance`
    blocks, which is the real proof that the `config.yaml` hooks are wired
  - No `./tmp/` evaluation for this one. It changes no production code, every task is
    verifiable from the repo itself, and the ceremony would outweigh the change
