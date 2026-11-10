## Context

See proposal.md. `KdaisyuiStylesheet.plan()` maps host files to container paths;
`NodeContainerRun` copies each with `withCopyToContainer`.

## Decisions

### 1. Narrow the input to the file

**Chosen:** `classList: RegularFileProperty`, `@InputFile`, copied to
`/deps/project/kdaisyui/kdaisyui-classes.txt`. **Rejected: a new output default** (proposal,
subtractive option). **Rejected: excluding `*.css` from the directory input** — it keeps a
directory input whose content the task does not need and hides the cause behind a filter.

### 2. Not a migration for consumers' entries

The container path of the list is unchanged, so no entry and no example changes. The task property's
type changes; the extension does not. One line in `docs/upgrading.md` covers a build that set the
task property itself.

## Enablers and desired changes

- Enablers: none.
- Desired change: the property's type and the copy target.
