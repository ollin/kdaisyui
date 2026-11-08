## Context

See proposal.md. The `scope` job checks out the merge commit and runs
`.github/ci-scope/classify.test.sh` before classifying; a failure there fails the job, and a
failed `scope` job makes every test job run.

## Decisions

### 1. Parse with the runner's `yq`, not the OpenSpec CLI

The CLI is not installed in CI, and its parse failure is a warning, not an exit code. `yq` is on the
runner image. If A1 is false, `python3` with PyYAML is the fallback, the parser the local probe used.

### 2. A script and its positive control, beside the workflow

`check.sh <file>` exits non-zero on a parse error; `check.test.sh` runs it on a good and a broken
fixture written to a temporary directory, and fails if either answer is wrong - the shape
`classify.test.sh` already has, so a check that cannot fail is caught by its own test.

### 3. In `scope`, before the classifier

The only job a planning-only pull request runs. Its failure making every test job run is
harmless: the pull request is red either way.

## Risks / Trade-offs

- [`yq` and the CLI's parser disagree on some input] → the check proves only what it tests: F1's
  case and a good file. Another disagreement would be a new finding.
