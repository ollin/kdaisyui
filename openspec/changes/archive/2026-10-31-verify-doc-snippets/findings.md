# Findings

## F1 - `example-app` cannot be the source of most snippets

- **What happened:** task 2.1's survey, 2026-10-31. Of the 41 Kotlin snippets that use the
  library, about 12 have an honest equivalent in `example-app`. The getting-started tutorial uses
  standalone `createHTML()` snippets. The dashboard tutorial shows intermediate states of code
  that `example-app` holds only finished. 5 of the 7 in `explanation.md` are deliberately not
  library usage: a raw string, plain kotlinx.html, a signature, a hypothetical pattern.
- **Cause:** the plan assumed the documentation demonstrates what the application does. Tutorials
  demonstrate how code grows, which a finished application cannot quote.
- **Cost:** the spec's first requirement was false by its own falsifier. One question round with
  Oliver (who chose to compile snippets in place), and a rewrite of the proposal, design, spec and
  tasks before any code.

## F2 - Two tasks were settled before the change started

- **What happened:** task 5.2 asked whether `llms.txt` belongs to this change. It has been
  generated whole by `generateReferenceDocs` since 0.7.0. Task 5.1 asked to inject README's
  three-row *What's new in 0.2.0* table, a section two minor versions stale whose own comment says
  its rows have been wrong before.
- **Cause:** six weeks between proposing the change and applying it, and 0.7.0 and 0.8.0 in
  between.
- **Cost:** none. Both tasks were replaced: `llms.txt` is out of scope, and the stale section is
  deleted rather than injected.

## F3 - Three snippets are broken today, two of them by a bad edit

- **What happened:** task 1.1's compile. `docs/tutorials/getting-started.md` step 5 and
  `docs/tutorials/build-a-dashboard.md` step 6 each carry a pasted leftover: a second, partial
  `daisyStats { … }` after the block closes. README's 0.7.x migration note uses `{ … }`, which is
  not Kotlin.
- **Cause:** nothing compiles the documentation, which is what this change is for.
- **Cost:** none to the plan; fixed in section 3.

## F4 - CI does not run `check`

- **What happened:** task 1.2. The plan said a snippet compiled by `check` would fail in CI's
  `unit-tests` job. That job runs `:lib:test koverVerify koverXmlReport`, and no job runs `check`.
- **Cause:** an assumption about CI that nobody had written down, so nobody had checked it. It is
  now A5.
- **Cost:** a design correction before any code; the job gains one task (task 4.1).

## F5 - The branch gate reverts idiomatic null handling

- **What happened:** two TCR steps in section 2 were reverted by `koverVerify`'s 100% branch rule,
  not by an assertion. `?.groupValues?.get(1)` and `?.removePrefix(…) ?: default` each compile
  to a null check on a value that can never be null, so one branch is unreachable.
- **Cause:** the gate measures bytecode branches, and Kotlin emits them for safe calls whose
  result cannot be null.
- **Cost:** two reverts, about 3 minutes; each was retried in a form with no unreachable branch
  (`?.groupValues[1]`, `(marker ?: default).removePrefix(…)`).

## F6 - Kover takes in every source set that is not a test

- **What happened:** the first wiring of `compileSnippets` was reverted. `./gradlew build` compiled
  the new `snippets` source set, and failed on every snippet not yet in step, because Kover
  instruments and measures every non-test compilation of a module it is applied to.
- **Cause:** a Kover default nobody had needed to know before this module.
- **Cost:** one revert, about 2 minutes; the task was split, and the source set is excluded with
  `kover { currentProject { sources { excludedSourceSets } } }`.

## F7 - A skipped build script can still be wrong

- **What happened:** task 3.1. `docs/tutorials/getting-started.md` step 1 tells a reader to
  `includeBuild("../kdaisyui")` and depend on `project(":lib")`. That cannot work from another
  build without dependency substitution, and the library has been on Maven Central since 0.1.1,
  which README shows. The script is marked `skip`, because a Gradle script does not compile
  against the library.
- **Cause:** `skip` takes a block out of the check and says why. It does not make the block
  right.
- **Cost:** none to this change, which does not fix it: a tutorial setup is a content decision,
  not a compile error.

## F8 - `check --warning-mode all` is not at zero problems, on main either

- **What happened:** task 6.1. The `kdaisyui-build` skill says the build ends with `Problems: 0`.
  It reports 15, all one deprecation, `Configuration.setVisible(boolean)`, raised by the Kover
  plugin. `:ktor-integration:check --rerun` on `origin/main` reports the same 15, so this change
  added none.
- **Cause:** a Kover or Gradle bump since the skill was written; nothing checks the skill's claim.
- **Cost:** about 5 minutes to tell a pre-existing problem from a new one.

## F9 - The mutation gate does not reach the new module

- **What happened:** task 6.1. `:lib:pitest` and CI's `mutation-tests` job cover `:lib` only. The
  extractor was measured by applying PIT to `:doc-snippets` temporarily, uncommitted: 50 killed,
  2 survived, 1 without coverage. The survivors led to two refactorings: a multiline regex instead
  of an inlined `any {}`, and `Skip` as an object, since nothing read its reason. The rerun gave
  47 killed and 1 survivor, an equivalent mutant: `<` to `<=` in `declarationOf` cannot change
  the result, because a fence never looks up a name it declares itself.
- **Cause:** the PIT configuration lives in `lib/build.gradle.kts`, not in the convention plugin.
- **Cost:** about 10 minutes, and a module that CI does not mutation-test.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | fixed - in this change (the plan was revised to compile in place) |
| F2 | - | fixed - in this change (task 3.5) |
| F3 | - | fixed - in this change (section 3) |
| F4 | - | fixed - #423: the `unit-tests` job runs `check`, so no task of it can miss CI |
| F5 | A sentence in `kdaisyui-testing`, beside the branch gate that causes it, not in `fwb-tcr`: no check can tell an unreachable branch from an untested one, and `fwb-tcr` is fwb-application's | fixed - in the kdaisyui-testing skill (section 2) |
| F6 | - | none - record only; the exclusion is commented in `doc-snippets/build.gradle.kts` |
| F7 | getting-started's build script uses the Maven Central coordinates README shows | fixed - in docs/getting-started-from-maven-central, with the dashboard tutorial's, which had the same `project(":lib")` |
| F8 | `kdaisyui-build`'s "Problems: 0" becomes a recorded number that a check compares, instead of a sentence | fixed - in fix/config-time-process: the build's own problem removed, and CI fails on any problem `gradle/allowed-gradle-problems.txt` does not accept |
| F9 | `:doc-snippets` joins the PIT configuration and CI's `mutation-tests` job | fixed - in chore/mutation-test-doc-snippets: 47 of 47 killed, after the one equivalent mutant's comparison was refactored away |
