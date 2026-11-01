# Findings

## F1 - A test control assumed the prose rule would not exist

- **What happened:** task 2.3's first hand-run cycle went red. The control `docs/AGENTS.md`
  expected `full`, written in 2.2 to show a planning name elsewhere is not planning; under the
  prose rule it is correctly `prose`. The production code was reverted and the tests kept.
- **Cause:** the control chose a directory that a later rule claims.
- **Cost:** one revert, a few minutes. The control moved to `lib/AGENTS.md`, and the step was split
  into single-path prose, then mixed lists. The mixed-list control was added before its one-line
  fix and observed red once, a second run where the house rule asks for one.

## F2 - The self-test could not fail

- **What happened:** after section 2 was green, 13 hand-made mutants of `classify.sh` (anchors
  removed, rules dropped, the widest-scope guard removed) all survived. Each control was written
  `printf … | expect …`, so `expect` ran in a pipeline subshell and its failure count never reached
  the final check. A wrong answer printed `FAIL` and the script still exited 0.
- **Cause:** a shell pipeline semantic, and a check nobody had seen go red, which is the defect
  `AGENTS.md` names five times over.
- **Cost:** about 10 minutes. The harness takes paths as arguments; 12 of 14 mutants were then
  killed, and two new controls (a `docs/` directory below the root, a blank line) kill the other
  two. Without the mutants, CI would have run a self-test that could never fail.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only |
| F2 | The hand-made mutant list becomes a third script beside the test, run by the `scope` job, so a future rule edit is mutated too | open - decide in task 5.2 |
probe
