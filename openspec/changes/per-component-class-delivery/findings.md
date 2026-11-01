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
