# Migrate codegen to TypeScript

## Why

`codegen/` is ~2000 lines of JavaScript that parses hand-written YAML frontmatter from a
submodule we do not control and emits Kotlin source. Every value crossing that boundary is
`any`, and the defects found while testing it in `test-and-clean-codegen` were all shape
errors a type would have named:

- `DEFAULT_OUTPUT_DIR` pointed at the hand-written tree — a string that was the wrong string.
- `parseTestCases` desynchronised its fence state — four mutable locals with no declared
  relationship.
- `buildClassMappings` took a `componentName` argument nothing read.
- `closesTagAssert` was handed a kotlinx.html *builder* name where an HTML *tag* name was
  meant. Both are `string`.

That last one is the argument in miniature. `fieldSet` and `fieldset` are the same type and
different things, and the mistake surfaced as four failing generated tests rather than as a
compile error.

## What Changes

Port `codegen/src/**` from `.js` to `.ts`, executed **directly by Node** — no build step, no
runtime dependency, no emitted JavaScript.

**Verified** 2026-09-11 against the Node 26.8.2 docs, the version pinned in `.tool-versions`:
type stripping is *stable* (since v25.2.0) and *enabled by default* (since v23.6.0), and
`--experimental-transform-types` was removed in v26.0.0. Node replaces type syntax with
whitespace and runs the file.

### Decided: no `tsc`, no type-check step, no `noEmit`

Oliver, 2026-09-11. Types are for the editor and the reader. They are **not** enforced by a
build.

State the consequence plainly rather than discovering it later: **nothing in CI will reject a
type error.** Node strips types without checking them, so a wrong annotation is invisible at
runtime and a genuinely wrong *value* is caught by the same two gates as today — the 19
codegen unit tests and `generated-sources-drift`. This change buys editor feedback and
documentation, not a new gate.

It also keeps `codegen/package.json` at **zero dependencies**, which is what keeps
`just generate` the only step in the repository needing Node at all.

### The syntax rules this imposes

Node errors rather than transpiling, so the ported code must stay *erasable*:

| Forbidden | Because |
|---|---|
| `enum`, `namespace` with runtime code, parameter properties, import aliases | require code generation |
| decorators | TC39 stage 3, not transformed |
| `.tsx` | unsupported |

And two rules that bite silently:

- **`import type { X } from './m.ts'`** — without the `type` keyword Node treats it as a value
  import and fails at runtime.
- **`.ts` extensions are mandatory in import specifiers.** `import './file'` does not resolve.

Node ignores `tsconfig.json` entirely, so `paths` aliases are unavailable; `#`-prefixed
subpath imports are the substitute if one is ever needed.

## Impact

- **`lib/generated/**` must not change by one byte.** That is the acceptance criterion for
  every task, checked the way CI checks it: regenerate, require an empty diff.
- **No new dependency, no build step.** `just generate` and the four Gradle `Exec` tasks keep
  working unchanged; only the file extension they invoke changes.
- **Per generator, not all at once.** Drift is all-or-nothing per generator, so a per-file port
  keeps each step's failure attributable.
- **Assumed:** that the existing code is already erasable-syntax-compatible — it is plain
  ES modules with no classes to decorate and no enums. Falsified the moment Node raises
  `ERR_UNSUPPORTED_TYPESCRIPT_SYNTAX`, which is a loud failure, not a silent one.
- **Assumed:** that annotating the parser boundary is where the value is. Falsified if the
  first ported file gains types that restate what the code already says and name nothing that
  was previously ambiguous — in which case stop and reconsider, rather than port four more
  files for the sake of consistency.

## Relationship to the entry-point guards

`test-generator.js` and `test-generator-heroicons.js` each carry
`import.meta.url === pathToFileURL(process.argv[1]).href` so importing them for a test does not
run the generator. That guard is what makes the modules testable at all and **must survive the
port** — `import.meta.url` is unaffected by type stripping, so this is a preservation
requirement, not a redesign.
