// StrykerJS for codegen/ (openspec/specs/mutation-testing). `npm run mutate` mutates all of src/
// and gates nothing: the whole directory scored 43.7 % when this was introduced, mostly generator
// code that only the drift job proves. CI's codegen-tests job mutates a pull request's changed
// lines only (src/index-mutation-scope.ts) and sets STRYKER_BREAK=100, so any survivor fails it.
//
// A module, not JSON, for that one variable: the CLI has no flag for the break threshold.
const breakAt = process.env.STRYKER_BREAK

export default {
  testRunner: 'command',
  // The npm test script's invocation in one process. The `test/` argument keeps node's default
  // patterns off src/test-generator.ts, which they would otherwise execute as a test file.
  // `--test-isolation=none` because the command runner reruns the whole suite per mutant, and
  // with each worker starting one process per test file, 54 mutants took 77 s on a CI runner;
  // in one process the same run took half as long locally, with the same mutants killed. The
  // suite passes either way (`npm test` stays isolated).
  commandRunner: { command: 'node --test --test-isolation=none test/' },
  mutate: ['src/**/*.ts', '!src/index-*.ts'],
  // The command runner reruns the whole suite per mutant; it cannot measure per-test coverage.
  coverageAnalysis: 'off',
  reporters: ['clear-text', 'html', 'json'],
  clearTextReporter: { reportMutants: true, reportScoreTable: true },
  tempDirName: '.stryker-tmp',
  thresholds: { high: 100, low: 80, break: breakAt === undefined ? null : Number(breakAt) },
}
