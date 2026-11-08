# Tasks

## 1. Open assumptions

- [x] 1.1 `. d (internal)` (A1) Push `check.sh` and `check.test.sh` with task 2.1; this pull
  request's `openspec-config` run shows the control's two answers on the runner's `yq`. Record them. If the
  broken fixture passes, switch the parser to `python3` with PyYAML in the same task.
- [x] 1.2 `. d (internal)` (A2) Read `ci-job-selection` against a new step in `scope`; record it.

## 2. The check

- [x] 2.1 `! F (internal)` `.github/openspec-config/check.sh` and `check.test.sh`, and the job
  `openspec-config` that runs the control and then the check on `openspec/config.yaml`. Test: the
  control.
- [ ] 2.2 (Oliver's go, no commit) `openspec-config` joins the `main` ruleset's required status
  checks, after this pull request's run reported it once.

## 3. Gate and archive

- [ ] 3.1 `openspec validate fail-ci-on-an-unparsable-openspec-config --strict`; this pull
  request's CI green with the check's lines in the `scope` log.
- [ ] 3.2 Archive on the implementation branch, `. d (internal)`.
