## Context

See proposal.md. The plugin scans compiled classes with a regex over the bytes
(`componentClassesNamedIn`) and reads `kdaisyui-components.txt` from the runtime classpath
(`resourceIn`, `ComponentMap`). It does not depend on `lib`.

## Decisions

### 1. The theme list comes from a generated resource of the library

**Chosen:** codegen writes `kdaisyui-themes.txt` (entry and value per line) next to the component
map; the plugin reads it like the map. An older library without it: the check logs at info level
that it cannot check, and passes. **Rejected: loading `DaisyTheme` from the classpath** — a class
loader over the consumer's runtime classpath inside the build for one table. **Rejected: deriving
the value by lowercasing** the entry (A8): true today, not a contract.

### 2. Parse the constant pool (confirmed by the probe of task 1.1)

A theme needs the class AND the member of a `Fieldref`, stored as indices into other pool entries.
**Chosen:** a small reader (UTF-8, Class, NameAndType, Fieldref, Methodref).
**Rejected: ASM** — an `implementation` dependency in every consumer's build classpath for one
table; shading it would add a build step the plugin has none of. **Rejected: a regex for `Nord`
near `DaisyTheme`** — the pool's order is the compiler's, not a contract.

### 3. A finalizer of the stylesheet task, not part of `check`

**Chosen:** `kdaisyuiStylesheet.finalizedBy(kdaisyuiThemes)`. The check runs exactly when a
stylesheet is built, and never where none is configured. **Rejected: `check` depends on it** — it
would pull a Docker compile into every `check` and fail builds without an entry (review,
2026-11-10). **Rejected: inside `kdaisyuiStylesheet`** — that task is cacheable; a cache hit would
skip the warning. The task is `@DisableCachingByDefault` and declares no output, so it re-runs.

### 4. Warn, do not write the `themes` line

**Rejected: a `themes` option that writes the entry's `@plugin` block** — the entry is the
consumer's CSS, which may configure DaisyUI otherwise; the warning names the line instead.

### 5. Tested in-process

The logic is pure functions returning values and the warning text; the task action is called on a
`ProjectBuilder` project with a hand-written stylesheet. No TestKit: it runs in another daemon,
which Kover cannot see and pitest would re-run per mutant. `logger` calls are excluded from
mutation (`avoidCallsTo`), so tests assert the text, not the log.

## Enablers and desired changes

- Enablers: none.
- Desired changes: the resource, the reader, each recognition rule, the stylesheet reader, the
  comparison, the warning, the task, its wiring.

## Risks / Trade-offs

- [A finalizer's behaviour on an up-to-date task changes in a later Gradle] → A7 was observed on
  9.8; the plugin's own test asserts the `finalizedBy` wiring, not Gradle's scheduling.
