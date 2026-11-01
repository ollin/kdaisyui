## Context

See `proposal.md` for the baseline. Constraints: `codegen/` is TypeScript run by Node with no
build step and one dependency, by decision; `codegen-tests` is a required check with no JDK and no
submodules; the `scope` job puts `codegen-tests` in the `full` group.

## Decisions

### 1. Gate the changed lines, not a score

**Chosen:** a gate at 100 % on exactly the lines a pull request changes.

**Rejected: a global `thresholds.break` at the baseline (43).** New code with survivors would pass
as long as the average held, which is the gap the house rule closes.

**Rejected: 100 % on every changed file.** Touching one line of `test-generator.ts` would demand
killing its 1,024 survivors first, so nobody would touch it.

**Rejected: Stryker's incremental mode.** It reuses earlier results to run fewer mutants, but the
score it gates is still the whole project's.

### 2. Line ranges from the diff, computed by a tested module

`codegen/src/mutation-scope.ts` reads `git diff -U0` output and returns Stryker `--mutate`
arguments: one `src/<file>.ts:<start>-<end>` per added or changed hunk, files under `codegen/src/`
only, `index-*.ts` excluded, pure deletions skipped. A CLI wrapper runs `git diff -U0 HEAD^1 HEAD
-- src/` and prints the arguments; the CI step runs Stryker only when there are any. It is a pure
function plus a thin wrapper, so it is unit-tested, run under TCR by the Bun suite, and mutated by
its own gate.

**Rejected: a shell pipeline in `ci.yml`.** Parsing hunk headers in `sed` is the kind of code that
fails silently, and nothing would test it.

### 3. Inside `codegen-tests`, not a seventh job

**Chosen:** two steps after the unit tests: compute the ranges, then mutate them. The job already
has Node and the dependencies, and is already required, so the ruleset does not change.

**Rejected: a new job.** It would need adding to the ruleset's required checks, a settings change,
for a step that usually runs in seconds.

### 4. Stryker as a `devDependency`, kept out of generation

`installCodegenDeps` runs `npm ci --omit=dev`, so `just generate` and the drift job install only
`htmlparser2`. The `codegen-tests` job keeps a plain `npm ci`. Measured on 2026-11-01: Stryker
makes `node_modules` 58 MB and 113 packages, against one package before.

### 5. Configuration

`codegen/stryker.config.json`: `testRunner: command`, `commandRunner.command: node --test test/`
(the npm script's own invocation, with the `test/` argument that keeps the runner off
`src/test-generator.ts`), `mutate: ["src/**/*.ts", "!src/index-*.ts"]`, `coverageAnalysis: off`
(the command runner cannot measure per-test coverage), `reporters: [clear-text, html, json]`, and
no `thresholds.break`: the gate is the CI step, which fails on any survivor in its ranges.

## Risks / Trade-offs

- [An equivalent mutant on a changed line blocks a pull request] → Stryker's
  `// Stryker disable next-line <mutator>: <reason>` comment, with a reason a reviewer can check,
  the same bar as PIT's exclusions. Or delete the code, as `AGENTS.md` says a survivor can mean.
- [The `command` runner reruns the whole suite per mutant] → 0.12 s per mutant measured; a 200-line
  change is about 300 mutants, well under a minute.
- [A rebased pull request diffs against a newer base] → the merge commit's first parent is always
  the current base, so the ranges are exactly what merging would add.
