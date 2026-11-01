# Tasks

Ordered by uncertainty: section 1 settles the open assumptions (A3 is measured when the file
exists, task 3.6). Every code task runs under TCR at the repository root, with its test.

**Estimate, frozen 2026-11-01 at proposal, raw and uncorrected:** 210 minutes.

| Section | Minutes |
|---|---|
| 1. Open assumptions | 15 |
| 2. The full file | 30 |
| 3. The index, under TCR | 90 |
| 4. Documents and skills | 30 |
| 5. Gate and adoption | 45 |

The estimate ledger was not among this session's tools, so the run is to be opened with
`open_run` at the start of `/opsx-apply`, and the known ratios quoted then.

## 1. Open assumptions

- [x] 1.1 `. d` (A6) Read the specification's JavaScript implementation
  (https://llmstxt.org/llmstxt-js.html) and record in `design.md`, decision 3, whether its parser
  accepts a link item without `: note` and blank lines between items. Adjust the shape regex in
  the design if not.

## 2. The full file

- [x] 2.1 `! F` The reference-docs generator writes today's content to `llms-full.txt` from
  `codegen/llms-full-template.md` (moved; title changed to say *full*); the generated header, the
  Gradle task's inputs and outputs, the drift job's paths and the `justfile` status paths name it.
  `llms.txt` is not written in this step. Regenerate and commit the output.

## 3. The index, under TCR

- [x] 3.1 `^ F` `llms-index.ts` renders the index: the template's prose, `{{COMPONENTS}}`
  replaced by one `- [Name](<raw base>/docs/reference/<file>.md): <summary>` line per component,
  sorted by display name; a template without the placeholder fails. Two commits: the links, then
  the order. One revert on the way, from a test that expected a trailing period `summaryLine`
  strips; the step was split instead of retyped.
- [x] 3.2 `^ F` Shape gate: after the first H2, a non-blank line that is neither an H2 nor a link
  item fails generation, naming the line. Plus: exactly one H1 and no other heading before it.
- [x] 3.3 `^ F` Link gate: a raw-base URL whose repository path does not exist fails generation,
  naming the link.
- [x] 3.4 `^ F` Completeness gate: `README.md` or a `docs/**/*.md` outside `docs/reference/` that
  no link targets fails generation, naming the page.
- [x] 3.5 `^ F` `index-docs.ts` writes `llms.txt` from the index generator in the same run as the
  pages and the full file; the Gradle task declares both templates as inputs and both files as
  outputs. Also as inputs: README and the docs pages, which the coverage gate reads.
- [x] 3.6 `. d` Write `codegen/llms-template.md` as the index: H1, blockquote (naming `main` and
  how to pin a tag), five reading rules, `## Docs`, `## Components` placeholder, `## Full API`,
  `## Optional`. Regenerated 2026-11-01: **106 lines, 11,482 bytes**, 78 link items (A3 holds;
  the full file is 49,555 bytes).

## 4. Documents and skills

- [x] 4.1 `. d` `AGENTS.md`: the `llms.txt` paragraph names both files and both templates;
  `README.md` *AI support*: one row per file, and the sentence pointing users at `llms.txt` says
  what the index links.
- [x] 4.2 `^ d` `kdaisyui-release` *Ready to tag* gains the llms step (design, decision 6);
  `kdaisyui-codegen` diagram and table name both outputs; `kdaisyui-daisyui-upgrade` paths table
  names `llms-full.txt`.

## 5. Gate and adoption

- [ ] 5.1 Prove each gate fails: add an H3 under `## Docs`, link a page that does not exist, and
  rename `docs/how-to.md` temporarily; observe each failure name its cause; restore. Record the
  three outputs here.
- [ ] 5.2 The specification's own test: a subagent given only the content of `llms.txt` answers
  three questions by following links (the dependency block, a primary button, the dropdown
  content's lambda receiver). Record which link answered each.
- [ ] 5.3 Full green per `openspec/config.yaml`: `./gradlew check`, `:lib:testCodegen`,
  `just generate` with no diff, `analyze_change_set`, and the mutation run or the reason there is
  none (`codegen/` has no mutation tool; say so in the evaluation).
- [ ] 5.4 `openspec validate llms-txt-index --strict`; evaluation under `tmp/llms-txt-index/`.
