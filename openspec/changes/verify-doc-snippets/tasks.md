# Tasks

**Revised 2026-10-31** (`findings.md` F1, F2). The first plan injected snippets from
`example-app`; its survey (old tasks 1.1 to 2.2, done) refuted that, and Oliver chose to compile
the snippets in place. Ordered by uncertainty: section 1 settles the two open assumptions before
anything is wired up.

**Estimate, frozen 2026-10-31 before the revision:** 325 minutes across the first plan's seven
sections. It stays as it was; the revision is measured as part of the run.

## 0. Survey (first plan, done)

- [x] 0.1 Count and classify the fenced blocks: 68 Kotlin (41 usage, 21 migration, 6 build
  script), 9 bash, 5 HTML, 1 CSS, 2 untagged.
- [x] 0.2 Measure markdown-magic 4.11.0: 71 packages, 18 MB installed.
- [x] 0.3 Check `example-app` as the source: about 12 of 41 usage snippets have a home there.
  Refutes the first plan (F1).

## 1. Open assumptions

- [x] 1.1 `. d` (A2) Wrap every Kotlin block with the default context in a throwaway build under
  `tmp/verify-doc-snippets/`, compile, and count which compile, which need another context, which
  are deliberately not code, and which are broken today. Decide the directive set from the counts,
  and record them in `design.md`.
- [x] 1.2 `. d` (A3) Check that a new module's `main` source set can join the aggregated Kover
  gate, and how.

## 2. The extractor, under TCR

- [x] 2.1 `. F (internal)` Add the `:doc-snippets` module with its first behaviour: find every
  `kotlin` fence in a document, with the line of its opening fence.
- [x] 2.2 `. F (internal)` Wrap a snippet in the default context, in its own package, in a file
  named for document and line.
- [x] 2.3 `. F (internal)` `in <Receiver>` wraps a snippet in an extension on that receiver.
- [x] 2.3a `. F (internal)` Document scope: a block imports the other blocks' top-level
  declarations, the nearest before it winning, else the nearest after it.
- [x] 2.4 `. F (internal)` `skip - <reason>` excludes a snippet; `skip` without a reason fails.
- [x] 2.5 `. F (internal)` An unknown directive fails, naming document and line.
- [x] 2.6 `. F (internal)` A `compileSnippets` task runs the extractor over the documents and
  compiles the result. Not yet part of `check`, so the documents can be brought in step first.

## 3. Make the documentation compile

Each document is one task, checked with `compileSnippets`: fix what no longer compiles, mark what
must not.

- [x] 3.1 `. d` `docs/tutorials/getting-started.md`
- [x] 3.2 `. d` `docs/tutorials/build-a-dashboard.md`
- [x] 3.3 `. d` `docs/how-to.md`
- [x] 3.4 `. d` `docs/explanation.md`
- [x] 3.5 `. d` `README.md`, deleting the stale *What's new in 0.2.0* section (F2)

## 4. Wire it into `check`

- [x] 4.1 `. F (internal)` `check` depends on `compileSnippets`, the root's Kover aggregation
  includes `:doc-snippets`, and CI's `unit-tests` job runs `:doc-snippets:compileSnippets` (A5).
  Record the added build time.
- [ ] 4.2 `. d` Prove the failure: break one snippet, observe `check` fail naming document and
  line, restore. Record the output.

## 5. Close the loop

- [ ] 5.1 `. d` Update `AGENTS.md` and the `kdaisyui-testing` skill: the marker convention, and
  that `check` compiles the documentation.

## 6. Gate and adoption

- [ ] 6.1 Full green per `openspec/config.yaml`: repo-wide compile, the complete suite including
  e2e, coverage, `analyze_change_set`, and a mutation run on the extractor (or the reason there is
  none).
- [ ] 6.2 `openspec validate verify-doc-snippets --strict`.
- [ ] 6.3 Write the evaluation under `tmp/verify-doc-snippets/` for Oliver to adopt.
