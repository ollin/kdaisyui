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
