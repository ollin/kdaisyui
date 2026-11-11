Estimate, frozen 2026-11-11: **6h 50min (13h DevOnly)** — ledger run
`2026-11-11-kdaisyui-validate-openspec-strictly`.

Every task is documentation or CI tooling, `. d (internal)` unless it says otherwise. Each split is
checked with `openspec validate <spec> --type spec --strict` before its commit.

## 1. Open assumptions

- [x] 1.1 A5 and A6 answered by Oliver at review; record the answers in the proposal.

## 2. Evidence as one-line metadata (D1)

- [x] 2.1 Script: join every `**Verified**`/`**Assumed**` paragraph of `openspec/specs/*/spec.md`
  into one line with the colon; commit the result with the script's diff check (old text equals new
  text with newlines as spaces). Re-measure.
- [ ] 2.2 By hand: evidence continued in an unmarked paragraph or a list (at least
  `component-model` "declared in a committed model", `generated-sources` lines 130 and 235) folded
  into its metadata line, or moved into a scenario where it is an example.

## 3. Split the requirements still over 500 characters (D2), one commit per spec

- [x] 3.1 `api-change-detection` (1)
- [ ] 3.2 `ci-job-selection` (1)
- [ ] 3.3 `content-security-policy` (1)
- [ ] 3.4 `css-delivery` (1)
- [ ] 3.5 `mutation-testing` (1)
- [ ] 3.6 `generated-sources` (5)
- [ ] 3.7 `component-model` (8)
- [ ] 3.8 `daisyui-component-coverage` (12)

Counts from the measurement at proposal; 2.1 and 2.2 may lower them.

## 4. The rest of the strict failures

- [ ] 4.1 `document-server-rendered-patterns`: its two overlong ADDED requirements, in that change's
  delta, the same way.
- [ ] 4.2 Proposals tables: one row per finding with a valid status in
  `snapshot-test-documented-elements` and `snapshot-test-generators`; F2 of
  `document-custom-themes` becomes `adopted -> validate-openspec-strictly`.
  `fwb_tasks.findings verify` passes.

## 5. Keep it passing

- [ ] 5.1 `.github/openspec-config/validate.sh` and its control `validate.test.sh` (overlong
  requirement fails, metadata form passes); both run locally with Node 26.13.0.
- [ ] 5.2 Two steps in the `openspec-config` job of `.github/workflows/ci.yml`, after
  `actions/setup-node` with `.tool-versions`.
- [ ] 5.3 The planning rule for the `specs` artifact in `openspec/config.yaml`; `check.sh` passes.
- [ ] 5.4 The pull request's CI run: `openspec-config` green with both new steps (A4).

## 6. Verification

- [ ] 6.1 `openspec validate --all --strict` exits 0; evaluation in
  `tmp/validate-openspec-strictly/`.
