# Findings

## F1 - The working implementation lives in another repository

- **What happened:** at proposal, 2026-11-07. The Deploy-Radar's `compileTailwind` in
  fwb-application is the implementation this change generalises; it was read from the worktree
  `fwb-application--work-on--deployment-risk-tool`, since the `main` checkout did not yet carry
  it. The proposal cites it by path and line; the code itself is the employer's and is not copied.
- **Cause:** the proposal arrived before the Deploy-Radar's branch reached fwb-application's main.
- **Cost:** none; one extra directory read.

## F2 - The review found the task bundled, the citations off, and the pairing risk unstated

- **What happened:** the review of 2026-11-07 (`fwb-openspec-reviewer`): task 4.1 held the
  task class, the copy-in, the one-shot run, the copy-out and a container test in one commit,
  and no task wired the half-credential check; the cited line range of the Deploy-Radar's
  script was off by fourteen lines (128-174, not 114-160); five references to the deleted
  `compileTailwind` had no task; Testcontainers' libraries on a plugin classpath were an
  unlisted risk, since the Deploy-Radar carries it on a `buildscript` classpath instead; a
  newer plugin paired with an older library would compile against versions the components
  were not generated against; the no-CLI and mirror scenarios were not observable here; and
  `<name>` in the mount points was undefined.
- **Cause:** the tasks were written from the design's shape, not sized per commit; the line
  range was read from a `sed` offset; the risk list was written from the daemon's side only.
- **Cost:** about 25 minutes; block 4 is four steps, the version-mismatch warning is task
  2.3, the stale references task 5.3, the mount points are indexed, the spec is three
  requirements with scenarios the tests observe, and A11 records the PIT exclusion decision.

## F3 - Nine steps planned `^` that count above eight lines

- **What happened:** 2026-11-08, before the first code step, against the planning rule added the
  same day (plan a marker from counted lines). Tasks 2.1, 2.2, 2.3, 3.1, 3.2, 3.4, 4.1, 4.4 and
  5.1 each hold a class or a configuration block with its test, estimated at ten to thirty counted
  lines. They are `!` now; the remaining `^` steps (1.1, 3.3, 3.5, 4.3) stay at or under eight.
- **Cause:** the plan of 2026-11-07 predates the rule and sized by behaviour.
- **Cost:** five minutes; one bookkeeping commit.
