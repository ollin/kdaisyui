# Findings

## F1 - Two reverts on one step, both from a branch inside an inlined stdlib call

- **What happened:** task 3.5's first half, reading the map from a classpath entry. Both TCR runs
  were reverted by `:gradle-plugin:koverVerify` at 94.44% branch coverage, not by an assertion.
  The first was blamed on the inlined `use {}` and retried with `try`/`finally`, which changed
  nothing. Kover's report then named line 21,
  `getInputStream(it).bufferedReader().readText()`: the inlined `bufferedReader()` checks
  `if (this is BufferedReader)`, which an `InputStreamReader` never is. Work stopped there, per the
  rule that two reverts on one step stop coding.
- **Cause:** the same pattern as `verify-doc-snippets` F5, in calls the testing skill's sentence
  does not name; and a guess at the cause before reading the coverage report.
- **Cost:** two reverts and a stop, about 10 minutes; resumes with `readBytes().decodeToString()`.

## F2 - A red run from the test fixture, after a run that never reached a verdict

- **What happened:** task 3.5. The first TCR run of the task class was interrupted, and its
  production file was gone afterwards. The second run of the same code went red in two tests with
  `FileNotFoundException`: the tests called the task action directly, and only Gradle creates an
  `@OutputFile`'s directory before a task runs. The fixture now creates it; the third run of the
  unchanged code was green.
- **Cause:** a test bypassing what the framework does around the code it calls.
- **Cost:** two runs, about 5 minutes.

## F3 - The source set's runtime classpath holds the project's own resources

- **What happened:** before task 4.1. The plugin read the map from `main.runtimeClasspath`, which
  also holds the project's own resources. `example-app`'s stylesheet is such a resource and will
  depend on `kdaisyuiClasses`, so that would have been a task cycle. The plugin now reads the
  `runtimeClasspath` configuration, the dependencies only.
- **Cause:** the source set's classpath and the configuration's look alike from the API.
- **Cost:** one `^ B` step, caught before it bit.

## F4 - PIT's worker JVM does not open java.lang for ProjectBuilder

- **What happened:** the plugin's first PIT run failed "without mutation": a test using
  `ProjectBuilder` threw `Could not inject synthetic classes`, because ProjectBuilder needs
  `--add-opens java.base/java.lang=ALL-UNNAMED`, which Gradle's test task passes and PIT's worker
  JVM does not. The PIT configuration now passes it.
- **Cause:** a JVM setting the normal test run hides.
- **Cost:** about 5 minutes.

## F5 - The fallback's reason was only in the log

- **What happened:** PIT's survivors on the fallback warning showed that nothing observable carried
  the reason a build got every class. The written file's header now states it, which the tests
  assert, and which a reader of the file sees without the build's log.
- **Cause:** a warning is the natural first place for a reason, and the least observable one.
- **Cost:** one `! F` step.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | Extend the testing skill's sentence on unreachable branches: `bufferedReader()`, `use {}`, `any {}` and `none {}` hide branches the same way `?.` and `?:` do | fixed - in the testing skill, 2026-11-01; `use {}` turned out innocent |
| F2 | none - a fixture bypassing Gradle, recorded only | none - record only |
| F3 | read the `runtimeClasspath` configuration, not the source set's | fixed - in this change |
| F4 | pass `--add-opens java.base/java.lang=ALL-UNNAMED` to PIT's worker JVM | fixed - in this change |
| F5 | state the fallback's reason in the written file's header | fixed - in this change |
