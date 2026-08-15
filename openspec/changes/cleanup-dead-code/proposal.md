## Why

Committing the generated sources (`commit-generated-sources`) meant reading the codegen
closely, and that surfaced leftovers which are not that change's business. Collecting them
here keeps that change honest about its scope and gives the removals somewhere to be
reviewed on their own.

The largest item is a **complete, unreachable v1 codegen pipeline**. kdaisyui once generated
components from 22 hand-maintained YAML descriptions; it now derives them from DaisyUI's own
documentation frontmatter. The old pipeline was never deleted:

| Live (reachable from the three entry points) | Dead |
|---|---|
| `index-new.js`, `index-heroicons.js`, `test-generator.js` | `index.js` |
| `generator-new.js`, `generator-heroicons.js` | `generator.js` |
| `classifier.js` | `classify.js` |
| `parser/frontmatter.js`, `parser/llms-txt.js`, `parser/svg-heroicons.js` | `config/*.yml` (22 files) |

Verified by tracing every `import` in `codegen/src/**`: nothing imports `index.js`, and
`package.json` declares only `index-new.js` and `index-heroicons.js` as scripts. `index.js`
is the sole reader of `config/*.yml` (`index.js:24`); `generator.js` merely names them in an
emitted comment (`generator.js:220`). The three modules form a closed cluster that nothing
outside reaches.

It is not harmless clutter. It actively misleads:

- `index.js:7` writes to `lib/src/main/kotlin/kdaisyui/components` — the **wrong package**
  (`kdaisyui`, not `io.github.ollin.kdaisyui`), and inside the hand-written tree that
  `commit-generated-sources` just moved generated output out of.
- Two people have now spent effort on it under the impression it was live. During
  `commit-generated-sources` its duplicate import comparator was analysed and then edited
  (`6d8f784`) before anyone noticed the file is unreachable. That is the cost being removed.

## What Changes

- **Delete the v1 pipeline**: `codegen/src/index.js`, `codegen/src/generator.js`,
  `codegen/src/classify.js` and all 22 `codegen/src/config/*.yml`.
- **The three tracked `.idea` files stop being tracked** — `misc.xml`, `gradle.xml` and
  `vcs.xml`, all added in `e24148b`. `.gitignore:15` ignores `.idea/`, which does not apply
  to files already tracked, so the rule has been aspirational. `misc.xml` in particular
  cannot be tracked quietly: it carries `project-jdk-name`, a local SDK-table name, so it
  churns per machine. Decided 2026-08-15; the reasoning per file is in `tasks.md` section 3.
- **Prune merged local branches**: `chore/minimize-codegen-yaml-configs`,
  `chore/remove-version-consistency-ci`, `fix/renovate-action-version` — all at 0 ahead /
  0 behind. Local `main` is 164 behind `origin/main`.
- **Decide on `just generate-heroicons`**: now a strict subset of `just generate`. Keep it as
  a convenience or drop it; either is fine, leaving it undecided is not.

## Capabilities

### New Capabilities

None. Removing unreachable code changes no observable behaviour.

### Modified Capabilities

None. `skip_specs: true` is set in `.openspec.yaml`.

## Assumptions

1. **The v1 cluster is genuinely unreachable.** *Verified* by tracing all imports under
   `codegen/src/**` and reading `codegen/package.json`. *Wrong if:* something outside
   `codegen/` invokes `node src/index.js` directly — a CI job, a `just` recipe, a doc
   instructing a reader to run it. Task 1.1 checks exactly that before anything is deleted.
2. **Nothing needs the YAML configs as reference material.** They encode per-component
   decisions that the frontmatter classifier now makes automatically. *Wrong if:* the
   upcoming `daisyui-taxonomy-api` work wants them as a record of intended API shape — in
   which case they are worth reading once and summarising, not keeping as dead input files.
   Git history preserves them either way.

## Impact

- `codegen/src/` — 25 files deleted, roughly a third of the generator directory.
- `.gitignore` or `.idea/misc.xml` — one of the two.
- No change to `lib/`, to generated output, or to anything a consumer sees. The proof is
  that regenerating after the deletion leaves `lib/generated` byte-identical.

**Deliberately out of scope**
- The `.tool-versions` and README documentation nachzug — owned by `commit-generated-sources`
  task 7.2.
- Porting the generator to Kotlin — `codegen-to-kotlin`.
- Anything about the emitted API — `daisyui-taxonomy-api`.
