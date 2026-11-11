# llms-txt Specification

## Purpose
The repository's entry point for an agent: a small `llms.txt` in the shape https://llmstxt.org/
specifies, whose links lead to Markdown an agent can fetch as plain text, and which is complete
over the hand-written documentation and the generated reference pages because the generator
refuses an index that is not.

## Requirements

### Requirement: The index has the specified shape

`llms.txt` SHALL consist of, in order: one H1 naming the project; one blockquote summarising it;
zero or more blocks that are not headings; then zero or more H2 sections, each of which is a
Markdown list whose every item is a link `[name](url)` optionally followed by `: note`. No other
heading level and no other content SHALL appear after the first H2. The generator SHALL refuse to
write an index that breaks this shape, naming the offending line.

**Verified**: https://llmstxt.org/, section *Format*, read 2026-11-01: "An H1 with the name of the project or site. This is the only required section. A blockquote with a short summary of the project … Zero or more markdown sections (e.g. paragraphs, lists, etc) of any type except headings … Zero or more markdown sections delimited by H2 headers, containing 'file lists' of URLs … Each 'file list' is a markdown list, containing a required markdown hyperlink `[name](url)`, then optionally a `:` and notes about the file." The same section names `## Optional` as the conventional home of links an agent may skip.

#### Scenario: The generated index

- **WHEN** the reference-docs generator runs
- **THEN** `llms.txt` opens with the generated-file attribution comment, then one H1, then one
  blockquote
- **AND** after the first H2 every non-blank line is an H2 heading or a link item

#### Scenario: A template edit adds a sub-heading to a link section

- **WHEN** the index template holds an H3 heading, a code fence or a paragraph after its first H2
- **THEN** generation fails
- **AND** the failure names the line that breaks the shape

### Requirement: Every link leads to Markdown served as plain text

Every link in `llms.txt` SHALL be an absolute URL. A link into this repository SHALL use the raw
content host, `https://raw.githubusercontent.com/ollin/kdaisyui/main/<path>`, and the generator
SHALL refuse a link whose `<path>` does not exist in the working tree, naming the link.

**Verified**: `curl -I https://raw.githubusercontent.com/ollin/kdaisyui/main/docs/reference/button.md` on 2026-11-01 returned `HTTP/2 200` and `content-type: text/plain; charset=utf-8`. The specification's *Proposal* section asks that links "point to LLM-friendly content, such as the markdown versions of pages"; the HTML view under `github.com/…/blob/…` is not that.

#### Scenario: A component link

- **WHEN** the generator renders the link for a component
- **THEN** its URL is the raw-host URL of that component's generated reference page

#### Scenario: A template links a page that was deleted

- **WHEN** the index template links a repository path that does not exist
- **THEN** generation fails
- **AND** the failure names the link

### Requirement: Every documentation page is linked

`llms.txt` SHALL link `README.md` and every Markdown file under `docs/` outside `docs/reference/`,
and SHALL link every generated component reference page, each with the component's one-line
summary as its note. The generator SHALL fail when a page is not linked, naming the page.

**Assumed**: every Markdown file under `docs/` outside `docs/reference/` is a page a reader is meant to find. Verified for today's tree on 2026-11-01 (`docs/reference.md`, `docs/how-to.md`, `docs/explanation.md`, `docs/tutorials/index.md`, `docs/tutorials/getting-started.md`, `docs/tutorials/build-a-dashboard.md`; `docs/screenshots/` holds no Markdown). *Wrong if:* a Markdown file is added under `docs/` that must not be linked, such as a draft; the gate would then need a declared exclusion, and the template is where it would be declared.

#### Scenario: A new documentation page

- **WHEN** a Markdown file is added under `docs/` outside `docs/reference/` and the template does
  not link it
- **THEN** generation fails
- **AND** the failure names the page

#### Scenario: A new component

- **WHEN** a DaisyUI release adds a component the codegen generates
- **THEN** its reference page is linked from `llms.txt` without anyone editing the template
- **AND** the note is the summary from its model file, or DaisyUI's first sentence where none is
  committed

### Requirement: The full API is one link away

The complete generated API, in the form `llms.txt` held until this change, SHALL live in
`llms-full.txt`, generated from the same component shapes in the same run as the reference pages,
and `llms.txt` SHALL link it.

**Verified**: `codegen/src/index-docs.ts:109-110` writes the file from `written.shapes` and `docSummaries`, the inputs of the pages written in the same `main()`.

#### Scenario: A tool fetches the full file

- **WHEN** an agent follows the `## Full API` link
- **THEN** it receives every component's enums and function signatures in one document
- **AND** that document names the same functions the reference pages name
