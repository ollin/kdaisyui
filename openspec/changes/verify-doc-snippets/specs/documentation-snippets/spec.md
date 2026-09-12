## Purpose
How code shown in hand-written documentation is kept true: that every snippet comes from a
source which compiles and is exercised by tests, and that a snippet whose source is renamed,
moved or deleted fails the build instead of quietly going stale.

## ADDED Requirements

### Requirement: Snippets come from compiled, exercised sources

A fenced code block in the prose documentation SHALL be extracted from a source file that is
compiled by `check` and exercised by an automated test, rather than typed into the Markdown.

The extraction SHALL address the source by a **named region** declared in that file, never by
line number. A line range silently selects different code when lines are inserted above it,
which reproduces the drift this capability exists to remove.

**Assumed** — that `example-app/` is a sufficient source for the snippets the documentation
needs. It compiles under `check` and `:e2e-tests` drives it through a real browser, so a snippet
taken from it carries both guarantees.
*Wrong if:* a survey of the existing snippets finds that a material share of them demonstrate
things `example-app` does not do and would have to be invented there purely to be quotable —
at which point the snippet is documentation wearing an application's clothes, and either the
example app grows the feature honestly or the snippet stays hand-written and is marked as
unverified.

#### Scenario: A documented function is renamed

- **WHEN** a function used by a documented snippet is renamed and `example-app` is updated
- **THEN** regenerating the documentation produces the new name
- **AND** no one edits the Markdown

#### Scenario: A documented snippet stops compiling

- **WHEN** a change breaks the code a snippet is taken from
- **THEN** `check` fails on the source, before the documentation is considered

#### Scenario: A snippet's rendered result is asserted

- **WHEN** a snippet is taken from a page of `example-app`
- **THEN** an e2e scenario exercises that page

### Requirement: A missing snippet region fails loudly

When a documentation file references a source region that does not exist, the documentation
build SHALL fail and name both the file and the missing region. It SHALL NOT emit an empty
block, leave the previous content in place, or warn and continue.

**Verified**: `markdown-magic`'s CODE transform throws
`Missing ${id} code section from ${codeFilePath}` when either marker is absent, and
`Invalid ${id} code section` when the end marker precedes the start
(`packages/core/src/transforms/code/index.js`, read 2026-09-12). A purpose-built transform
would have to reproduce this behaviour to satisfy the requirement.

#### Scenario: Someone deletes a marked region

- **WHEN** a region marker is removed from a source file while a document still references it
- **THEN** the documentation build fails
- **AND** the failure names the document, the source file and the region

#### Scenario: Someone renames a region

- **WHEN** a region is renamed in the source but not in the document
- **THEN** the documentation build fails rather than emitting the old text

### Requirement: Injected documentation that is out of step with its sources is rejected

CI SHALL re-run the injection and fail when the result differs from what is committed, on the
same terms as the drift check for generated sources. Committed documentation is the artifact a
reader sees; an injection that only runs locally leaves it exactly as trustworthy as it was
before.

**Assumed** — that running the injection in CI is enough, without also requiring the
documentation build in the default `check` task.
*Wrong if:* the injection is observed passing in CI while a local `check` left the committed
documentation stale for more than one merge, which would mean contributors are not running it
and CI is catching it too late to be useful.

#### Scenario: Source changes, document not re-injected

- **WHEN** a commit changes a marked region but leaves the documentation untouched
- **THEN** the check fails and names the document that differs

#### Scenario: An ordinary change touching no marked region

- **WHEN** a commit changes only code outside every marked region
- **THEN** the check passes
