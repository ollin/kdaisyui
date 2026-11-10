## Context

See proposal.md. `problemsIn` splits the report at `{"problemId":`, takes each entry's last
`problemId` name as its id and its `pluginId`, and treats every entry after `"problemsReport"` as
a summary.

## Decisions

### 1. The id is the documentation anchor, falling back to the name

**Chosen:** the anchor of the entry's `documentationLink` (`deprecate-visible-property`), else the
last `problemId` name. One anchor per deprecation in Gradle's upgrade guide; stable across 9.8 and
9.9 (A1). **Rejected:** `contextualLabel`, a sentence with spaces that the line format
`<id> <plugin>` cannot hold without changing; `Method usage`, which accepts any method.

### 2. Summaries match by name

A summary has no link (A2), so "a summary of a listed problem" compares `problemId` names, not ids.
An unlisted summary keeps its name as its id and no plugin, as today.

### 3. The boundary is either marker

The first of `"problemsReport"` and `id="problems-summary"`. Both versions keep working, so this
lands on 9.8.2 before the Gradle update does.

## Enablers and desired changes

No enabler. Two behaviours, in uncertainty order: the boundary (A2 is the shakier read), then the
id with the allowlist.
