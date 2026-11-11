## Why

`openspec validate --all --strict` fails on `main`: 11 of 15 items, all for one warning,
"Requirement text is very long (>500 characters)", on 56 of the 94 main-spec requirements and two
ADDED requirements of the open change `document-server-rendered-patterns` (measured 2026-11-11 with
`openspec validate --all --strict --json`, OpenSpec 1.17.0). Nothing runs that command in CI, so the
failures accumulate without anyone noticing, and a change that pushes one more requirement over the
limit cannot be seen either: `document-custom-themes` nearly did (its finding F2, adopted here).

Separately, `fwb_tasks.findings verify` reports missing rows in two archived Proposals tables, which
were written as ranges (`F2-F4`) and with statuses the vocabulary does not have.

## What Changes

- **Evidence becomes one-line metadata.** Every `**Verified** …` and `**Assumed** …` paragraph in
  `openspec/specs/` is rewritten as one unwrapped line, `**Verified**: …` or
  `**Assumed**: … *Wrong if:* …`. OpenSpec reads a line of the form `**Word**:` as metadata and does
  not count it towards the 500 characters (A1). This alone brings 26 requirements under the limit.
- **The 30 requirements whose wording itself exceeds 500 characters are split**, each into
  requirements that state one behaviour, with examples and edge cases moved into scenarios, as the
  warning asks. Every new requirement keeps a scenario; no behaviour is added or dropped.
- **The two ADDED requirements of `document-server-rendered-patterns`** are shortened the same way,
  in that change's delta.
- **The three Proposals tables** (`snapshot-test-documented-elements`, `snapshot-test-generators`,
  and F2 of `document-custom-themes`) get one row per finding with a valid status.
- **CI fails when the specs do not validate strictly**: a step in the required `openspec-config`
  job runs `openspec validate --all --strict` at a pinned OpenSpec version, with a positive control
  that proves an overlong requirement fails and a metadata line does not.
- **A planning rule** in `openspec/config.yaml` for the `specs` artifact: evidence as one
  `**Verified**:` / `**Assumed**:` line, and requirement text within 500 characters.

### The subtractive option, stated

Turn the warning off instead: OpenSpec has no setting for the limit, so that would mean running
`validate` without `--strict`, which also drops every other strict check. Rejected. What this
change removes: 56 warnings, and the wrapped evidence paragraphs that read as requirement text.

## Out of scope

- **Renovate updating the pinned OpenSpec version.** The npm manager only reads
  `e2e-tests/package.json`; the pin sits in the check script, beside the reason. Its own change if
  wanted.
- **`fwb_tasks.findings verify` in CI.** It runs from a private repository CI cannot reach.
- **Rewording requirements for content.** A split keeps every statement; anything found wrong while
  splitting is recorded as a finding, not fixed here.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | OpenSpec does not count a `**Word**:` line towards the 500 characters, and counts a wrapped evidence paragraph from its second line on | code | confirmed at proposal - `dist/core/parsers/requirement-text.js` skips `METADATA_LINE = /^\*\*[^*]+\*\*:/`; probe in `/tmp/opencode/strict-openspec/probe`: the one-line `**Verified**:` form of a 600-character evidence exits 0, the same text as `**Verified**` paragraph exits 1 with one "very long" warning |
| A2 | `openspec validate --all --strict` exits non-zero on a warning | code | confirmed at proposal - exit 1 on `main` and in the A1 probe |
| A3 | A failing step in `openspec-config` blocks the merge | code | confirmed at proposal - `repository_ruleset_read(get_rules_for_branch, main)`: ruleset 14483746 requires `openspec-config` among seven checks, strict policy on |
| A4 | `npx -y @fission-ai/openspec@1.17.0` runs in the `openspec-config` job after `actions/setup-node` with `.tool-versions` | code | to check in task 5.3 - the pull request's own CI run; locally the same command under Node 26.13.0 is task 5.1 |
| A5 | The main specs are edited directly, not through MODIFIED/REMOVED/ADDED deltas | user | open - ask at review. Recommended: the edit changes no behaviour, and 30 splits as deltas would be about 90 delta blocks whose archive the validator does not length-check for MODIFIED anyway |
| A6 | Rationale that is not a behaviour ("because …", history) moves to a one-line `**Why**:` metadata line where the requirement still exceeds 500 characters after the split | user | open - ask at review. Recommended: it keeps the reason beside the rule, in the form A1 established; the alternative is deleting rationale, which loses why a rule exists |
| A7 | No requirement of a main spec this builds on contradicts it. `fwb_tasks.constraints list --change validate-openspec-strictly` names `ci-job-selection`, `generated-sources` and `openspec-config-check` | code | confirmed at proposal - `ci-job-selection`: `openspec/**` is planning scope, which runs no test job, and `openspec-config` has no `needs`, so it runs whatever the scope; `generated-sources` 157 concerns the drift job, untouched; `openspec-config-check` gains a requirement and keeps its own |

## Capabilities

### New Capabilities

### Modified Capabilities

- `openspec-config-check`: CI also fails when `openspec validate --all --strict` does.

## Estimate

Frozen 2026-11-11, before implementation: **6h 50min (13h DevOnly)**, ledger run
`2026-11-11-kdaisyui-validate-openspec-strictly`. Blocks (developer-only): proposal 1h, evidence
reformat 1h, 30 splits 8h, open change and findings tables 30min, CI check, control and rule 1h 30min,
gate, archive and pull request 1h.
