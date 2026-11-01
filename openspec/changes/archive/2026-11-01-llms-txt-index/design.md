## Context

See `proposal.md`, *Why*. The constraints that shape the approach:

- `codegen/` is TypeScript run directly by Node: erasable syntax only, `.ts` import specifiers,
  no constructor parameter properties, one dependency (the HTML parser) and no appetite for a
  second. Its gates are `node --test` and the drift job.
- `generateReferenceDocs` (`lib/build.gradle.kts:381-400`) runs `codegen/src/index-docs.ts` once
  and already declares `llms.txt` as an output; CI's `generated-sources-drift` job runs that task
  and diffs `lib/generated docs/reference llms.txt`.
- The component shapes and the per-component summaries are in memory when the pages are written,
  which is what makes the index's component list free: same shapes, same run, no second parse.
- There is no website. The repository on GitHub is the site, and the raw host is its Markdown
  endpoint.

## Goals / Non-Goals

**Goals:**

- `llms.txt` parses under the specification's grammar and stays an index.
- Nothing that can be generated is hand-written, and nothing hand-written can silently be
  incomplete: a missing page, a dead link or a broken shape fails generation.
- The step that cannot be mechanised (a new hand-written feature reaching the templates before a
  tag) is written where the release checklist is read.

**Non-Goals:**

- Validating that the raw URLs are reachable from CI (network in the drift job). The path gate
  checks what the repository can know; the host is GitHub's.
- A `core`-API completeness gate against `lib/api/lib.api` (proposal, out of scope).
- Versioned URLs, release assets, a documentation site.

## Decisions

### 1. An index beside a full file, not one file

**Chosen:** `llms.txt` is the index; the current content becomes `llms-full.txt`.

**Rejected: keep one file and reshape it.** The specification wants links, and the API dump is
what a single fetch should return for tools that take one URL. One file cannot be both.

**Rejected: drop the full file.** README has pointed library users at one file since 0.7.0, and
66 reference pages are 66 fetches. The rename costs one commit.

### 2. URL base is `raw.githubusercontent.com/ollin/kdaisyui/main/`

**Chosen:** the raw host at `main`, as one constant in the index generator, and the blockquote
says the file describes `main`.

**Rejected: `github.com/ollin/kdaisyui/blob/main/…`.** Serves HTML; the specification asks for
Markdown.

**Rejected: the release tag.** The generator would need the version, so every version bump would
drift the file, and the file on `main` would describe an older tag than the tree it sits in. A
consumer who needs the tagged state replaces `main` in the URL; the blockquote says so.

**Rejected: relative links.** Valid Markdown, but an agent that fetched `llms.txt` by URL and
reads a relative link has to resolve it against a base it may not have kept.

### 3. Three gates, in the generator, in the model-completeness pattern

**Chosen:** the index generator renders, then checks its own output and throws with the line, link
or page. Generation fails; nothing is written.

- **Shape**: after the first `## ` line, every non-blank line matches `^## ` or
  `^- \[[^\]]+\]\([^)]+\)(: .*)?$`. Before it, no line starts with `#` except the single H1.
- **Links**: every URL under the raw base, stripped of it, exists as a file in the repository root
  the generator is given. The attribution comment is skipped.
- **Completeness**: `README.md` plus every `*.md` under `docs/` whose path does not start with
  `docs/reference/`, compared against the set of linked repository paths. Generated reference pages
  are covered by construction and not scanned.

**A6, checked 2026-11-01 (task 1.1).** The reference parsers at https://llmstxt.org/llmstxt-js.html
and https://llmstxt.org/core.html split the text at `^##\s*(.*?)$` and then collect, inside each
section, every match of `-\s*\[title\]\(url\)(?::\s*desc)?$`. So the note is optional, blank
lines between items are ignored, and so is anything else in a section: the canonical parser
tolerates what our shape gate refuses, which is the point of having the gate. Two consequences
for the template: the H1 is matched as `^#\s*title$` anywhere (the attribution comment before it
is harmless), and the summary is **one** `> ` line, because the parser captures a single line
and would push a second blockquote line into the free-form info.

**Rejected: a check in CI only.** The drift job would catch a changed output, not a missing link;
and a failing generation names the cause, a failing diff names a file.

**Rejected: a declared page list in the template.** A list to maintain is the thing the gate exists
to replace. If a page ever must not be linked, an explicit exclusion in the template's attribution
comment is the first thing to add; today there is none to exclude.

### 4. Where the code lives

**Chosen:** `codegen/src/llms-index.ts` holds rendering and the three gates; `generator-llms.ts`
keeps the full file and is renamed in its exports only where the name would lie
(`generateLlmsFull`). `index-docs.ts` writes both. One test file per module.

**Rejected: everything in `generator-llms.ts`.** Two outputs with different rules in one module is
how the file grows past what Code Health accepts.

### 5. The templates

`codegen/llms-template.md` becomes the index template and keeps its name, because the Gradle
input, the skill text and the generated header all point at it; `codegen/llms-full-template.md`
is today's template moved. The index template's `{{COMPONENTS}}` is replaced by the sorted link
list; the full template's by the signatures, as today. A template without the placeholder fails,
as today.

The index preamble carries five rules, not the thirty lines the full file carries: named
arguments, enums are choices, booleans are DaisyUI classes, the lambda receiver is the rendered
element, every function has a `…Classes` sibling. The rest is read where the signatures are.

### 6. The process half

`kdaisyui-release`, *Ready to tag*, gains one bullet: list the `feat` commits since the last tag;
for each that is not a generated component, check that `codegen/llms-template.md` or
`codegen/llms-full-template.md` says it; run `just generate`; the drift job proves the rest.
`kdaisyui-codegen` gains the second output in its diagram and table. `kdaisyui-daisyui-upgrade`'s
paths table gains `llms-full.txt`. `AGENTS.md`'s `llms.txt` paragraph is rewritten for two files
and two templates; README's *AI support* table gets a row per file.

**Rejected: a new skill.** Nothing here starts a kind of work; it adds one step to work that has a
skill, and a skill nobody loads before tagging would not be read.

## Risks / Trade-offs

- [The raw host changes URL scheme or the repository moves] → one constant in `llms-index.ts`; the
  path gate keeps the repository half honest, the host half is a manual check at release.
- [The completeness gate fires on a page someone meant to keep private] → the failure names the
  page; add it to the template or declare the exclusion (decision 3). Today no such page exists.
- [A tool cached `llms.txt` as the full API] → the index links the full file in its first list
  after the docs; README's table says which file is which.
- [The index drifts from the specification as the specification changes] → the shape gate encodes
  the v2 grammar; a v3 is a template-and-gate edit, and the spec file quotes the version it
  verified against.

## Open Questions

None that would change the specs or the tasks. A6 (the canonical parser's tolerance for blank
lines and the optional note) is checked first in `tasks.md`; either answer is a one-line change
to the shape regex.
