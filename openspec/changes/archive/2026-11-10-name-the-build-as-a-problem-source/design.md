## Context

See proposal.md.

## Decisions

### 1. A sealed `Source` with a `ThisBuild` null object, not a nullable string

**Chosen:** `Source.ThisBuild` carries the meaning and its printed form. **Rejected:** a value
class `PluginId?`: it names the plugin but keeps the `null`, so the translation stays.

### 2. `ReportEntry` as a value class over the entry's text

**Chosen:** `@JvmInline value class ReportEntry(private val json: String)`, its properties
computed on access. **Rejected:** a data class parsed once: the reader reads each field once
anyway, and a value class costs no allocation.

### 3. Order

`Source` first: it changes the type every other file uses, so it is the step the tests must
follow (a signature change and its call sites, one `^ r` run). `ReportEntry` second, production
only.
