## Context

See proposal.md. The plugin already scans compiled classes (`KdaisyuiClasses`,
`componentClassesNamedIn`, a regex over the class bytes) and compiles the stylesheet
(`KdaisyuiStylesheet`).

## Decisions

### 1. Parse the constant pool, do not regex the bytes

A component is found by its class name alone; a theme needs the class AND the member of a
`Fieldref`, which the constant pool stores as indices into other entries. **Chosen:** a small
reader of the pool (tags, UTF-8, Class, NameAndType, Fieldref, Methodref). **Rejected: ASM** — a
dependency the published plugin would carry into every consumer build for one table. **Rejected: a
regex for `Nord` near `DaisyTheme`** — the pool's order is the compiler's, not a contract.

### 2. A task of its own, after the stylesheet, in `check`

**Chosen:** `kdaisyuiThemes`, inputs the classes and the compiled stylesheet, no output but the
log; `check` depends on it. **Rejected: inside `kdaisyuiStylesheet`** — that task is cacheable and
a cache hit would skip the warning.

### 3. Warn, do not write the `themes` line

**Rejected: a `themes` option that writes the entry's `@plugin` block** — the entry is the
consumer's CSS, which may configure DaisyUI otherwise (`--prefersdark`, custom themes); rewriting
it would take that away. The warning names the line instead.

## Enablers and desired changes

- Enablers: none.
- Desired changes: the pool reader, the theme references, the stylesheet reader, the task.

## Risks / Trade-offs

- [A theme named only in a dependency's classes is not scanned] → the same scope as
  `kdaisyuiClasses`, stated in the warning's KDoc.
