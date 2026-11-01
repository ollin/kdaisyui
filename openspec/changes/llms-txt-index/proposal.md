## Why

`llms.txt` has been generated since 0.7.0, and it is not what https://llmstxt.org/ specifies.
Measured 2026-11-01: 964 lines, 49 KB, H2 sections of prose and code, 67 H3 headings, and one
link list at the very end. The specification (v2, modified 2026-08-10, section *Format*) asks for
an H1, a blockquote, prose without headings, and then H2 sections that are **lists of links** to
Markdown an agent fetches on demand: "the file itself stays small enough to fit in context. The
detail lives behind the links." What ships today is a full API dump under the index's name. An
agent that reads it to find the tutorial, the how-to or the explanation finds none of them: the
file links to no prose page at all.

The second gap is process. The generated half (66 component sections) cannot go stale, because the
drift job regenerates it. The hand-written half can: a new documentation page, a new hand-written
core feature, a new module. Nothing checks that either reaches `llms.txt` before a release, and
`findings.md` of `verify-doc-snippets` (F2) records a section of README that was two minor
versions stale for the same reason.

## What Changes

- **`llms.txt` becomes the index the specification describes, generated.** An H1, a blockquote,
  at most a handful of reading rules, then only H2 link lists: `## Docs` (README quick start, the
  tutorials, how-to, explanation; hand-written in the template), `## Components` (one link per
  generated reference page with the model's one-line summary as the note; generated),
  `## Full API` (the file below) and `## Optional` (DaisyUI, kotlinx.html, the releases page).
- **Today's content moves to `llms-full.txt`**, the name Anthropic, OpenAI and the documentation
  platforms use for the single-file variant. Its template moves with it, to
  `codegen/llms-full-template.md`. The content is otherwise unchanged.
- **Every link is an absolute URL under `https://raw.githubusercontent.com/ollin/kdaisyui/main/`.**
  That host serves the committed Markdown as `text/plain` (verified 2026-11-01 with `curl -I` on
  `docs/reference/button.md`), which is the "LLM-friendly content" the specification asks the
  links to point at. `github.com/…/blob/…` serves HTML.
- **Three gates in the generator**, in the pattern of the model completeness gate: the rendered
  index must have the specified shape (nothing but H2 headings and link items after the first
  H2); every linked repository path must exist; and `README.md` plus every `docs/**/*.md` outside
  `docs/reference/` must be linked. Each failure names the line, the link or the page.
- **`llms-full.txt` joins the drift job**, the `just generate` status paths and the Gradle task's
  declared outputs beside `llms.txt`.
- **The release skill gets the step the gates cannot do.** `kdaisyui-release`'s *Ready to tag*
  list gains: for every `feat` since the last tag that is not a generated component, the two
  templates say it; then `just generate`, and the drift job proves the generated half.
  `kdaisyui-codegen`, `AGENTS.md` and README's *AI support* table name both files and both
  templates.

**Subtractive option considered:** drop the full file and link only to the 66 reference pages.
Rejected: one URL that holds the whole API is what README has told library users to fetch since
0.7.0 and what single-fetch tools need, and keeping it costs one rename. The *Reading the
signatures* rules stay in the full file, where the signatures are; the index carries the five
that matter before following a link.

**Deliberately out of scope**

- A gate that every public type in the hand-written `core` package (readable from `lib/api/lib.api`)
  is named in `llms-full.txt`. The release-skill step covers it by hand; a mechanical version is
  its own change once the step has been forgotten once.
- Serving `llms.txt` from a website, `rel="describedby"` links, `Link:` headers: there is no
  website. The repository is the site, and the raw host is its Markdown endpoint.
- Attaching either file to the GitHub release as an asset.
- Tag-pinned URLs: see `design.md`, decision 2.

**Not breaking.** No published artifact changes. A tool that fetched `llms.txt` for the full API
now gets the index, whose `## Full API` section is one link away from what it fetched before.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | The specification's shape is as read on 2026-11-01: H1, blockquote, non-heading prose, then H2 link lists; `## Optional` by convention for skippable links | reading | verified at proposal, https://llmstxt.org/ section *Format*, quoted in `specs/llms-txt/spec.md` |
| A2 | `raw.githubusercontent.com` serves the repository's Markdown as plain text | measurement | verified at proposal: `curl -I` on `docs/reference/button.md` returned `content-type: text/plain; charset=utf-8` |
| A3 | The index with 66 component links stays well inside a context window: under 16 KB | measurement | open, task 3.6 records the size of the regenerated file |
| A4 | The hand-written documentation is exactly `README.md`, `docs/reference.md`, `docs/how-to.md`, `docs/explanation.md` and `docs/tutorials/*.md`; nothing else under `docs/` is a page | `ls` | verified at proposal; the gate reads the directory, so the list is not baked in |
| A5 | The reference-docs generator is the one the drift job already runs, so a second output file needs no new job | reading | verified at proposal: `.github/workflows/ci.yml` runs `:lib:generateReferenceDocs`; `lib/build.gradle.kts:381-400` declares `llms.txt` as its output |
| A6 | The canonical parser (the specification's JavaScript implementation) accepts a link item with and without the `: note` suffix and tolerates blank lines between items | reading | open, task 1.1 |

## Capabilities

### New Capabilities

- `llms-txt`: the repository's agent-facing entry point: a small `llms.txt` in the shape
  llmstxt.org specifies, every link an absolute URL to Markdown served as plain text, complete
  over the hand-written pages and the generated reference pages by construction, with the full
  API one link away in `llms-full.txt`.

### Modified Capabilities

- `generated-sources`: the requirement *Committed output that no longer matches its inputs is
  rejected* names `llms.txt` and `llms-full.txt` among the outputs the drift job covers.

## Impact

- **Edited**: `codegen/src/generator-llms.ts` (the full file), `codegen/src/index-docs.ts`
  (writes both files), `codegen/test/generator-llms.test.ts`.
- **New**: `codegen/src/llms-index.ts` and its gates, `codegen/test/llms-index.test.ts`,
  `codegen/llms-template.md` rewritten as the index template, `llms-full.txt` (generated).
- **Renamed**: `codegen/llms-template.md` → `codegen/llms-full-template.md`.
- **Edited**: `lib/build.gradle.kts` (inputs and outputs of `generateReferenceDocs`),
  `.github/workflows/ci.yml` (drift paths), `justfile` (status paths), `.gitattributes` if it
  marks `llms.txt` as generated.
- **Edited**: `AGENTS.md`, `README.md` (*AI support*), `.opencode/skills/kdaisyui-release/SKILL.md`,
  `.opencode/skills/kdaisyui-codegen/SKILL.md`, `.opencode/skills/kdaisyui-daisyui-upgrade/SKILL.md`
  (the paths table).
- No change to `lib`, `ktor-integration`, `bom`, `gradle-plugin` or any published artifact.
