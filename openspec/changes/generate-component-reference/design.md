## Context

The codegen already parses DaisyUI's frontmatter into a classified component model and emits
Kotlin from it. This change adds a second emitter over the same model. Nothing about the
parsing, classification or Kotlin output changes.

The constraint that shapes every decision below: **`codegen/` has zero runtime dependencies, by
decision**, recorded in `AGENTS.md`. A documentation generator that needs a package tree would
re-open a settled architectural property, and it would do so for the half of the documentation
that needs it least.

## Decisions

### 1. Whole-file generation in the codegen, not snippet injection

**Chosen:** a Markdown emitter in `codegen/src/`, writing each page in full.

**Rejected — `markdown-magic` (or any comment-block injector).** It injects content into
regions of files that continue to be hand-maintained. For pages that are 100% derivable that is
the wrong shape twice over: 66 file scaffolds would still be maintained by hand, and the tool
brings a dependency tree into a package that has none. Its `id=` / `section=` markers and its
throw-on-missing-marker behaviour are good — verified in
`packages/core/src/transforms/code/index.js` — but they solve snippet *injection into prose*,
which is `verify-doc-snippets`' problem, not this one.

**Rejected — a Gradle/Kotlin doc generator.** It would put the generator on the other side of
the Node boundary from the model it reads, duplicating the frontmatter parser. The parser is the
expensive part; the emitter is a template.

### 2. `index.md` is generated whole, and its prose moves out

**Chosen:** generate `docs/reference/index.md` entirely; move its **Common parameters**, **Core
utility** and **Requirements** sections (lines 85–118) into `docs/explanation.md`.

**Rejected — generate only the table, keep the prose in place.** That requires an injector,
which is decision 1 again. It also leaves a file that is half generated and half not, with no
header that can honestly say either.

**Rejected — leave `index.md` hand-written.** It is the one page that lists all 66 components;
it is the most likely thing to be wrong after a DaisyUI bump, and the least likely to be
noticed.

The prose being moved is genuinely prose — `addClassNames`' semantics, the escape-hatch
parameters, the pointers to `.tool-versions` and `libs.versions.toml`. `docs/explanation.md`
already exists for exactly this and already covers class merging, so the move puts related
material together rather than scattering it.

### 3. `README.md`'s component table stays hand-maintained — and is named as debt

The README lists all 66 components with their functions, which makes it a third copy of the
same facts. It is **not** generated here: the README is a single file mixing badges, quick
start, migration notes and the table, so generating it means the injector from decision 1.

This change fixes the table by hand and records it in the tasks as a known remaining copy. That
is an honest deferral, not an oversight — and `verify-doc-snippets`, which brings the injector
anyway, is where it belongs.

### 4. Enablers versus the desired change

**Enablers** (behaviour-preserving, provable by diff — the generated Kotlin must stay
byte-identical through all of them):

- extracting whatever the Kotlin emitter holds that the Markdown emitter also needs (the
  rendered tag per function, the enum entries, the parameter list with defaults)

**Desired change:**

- the Markdown emitter itself
- the Gradle task and its wiring into `just generate`
- the drift job's scope

## Risks / trade-offs

**The generated page may be worse than the hand-written one.** 66 hand-written pages have had
small human judgements applied — comment placement, which enum values are worth inlining. A
template applies one judgement everywhere. Mitigated by making the first task the diff of
generated-against-existing for all 66, read before anything is wired up: every difference must
be a correction or a deliberate template decision.

**`docs/` becomes regeneration-gated.** Today a typo fix in a reference page is a one-line edit;
afterwards it requires Node and the submodules. This is the same trade already accepted for
`lib/generated/**`, and the same answer applies — the pages are output, and output is edited at
its source.

**One more thing to forget in the drift job.** The spec already records that a generator missing
from the drift job is worse than one absent. Adding a fifth output adds a fifth chance. The task
that extends the job comes with a deliberate failure observation, the way the existing job was
proven on PR #231.

## Open question

Whether the generated pages should carry the DaisyUI class names and their descriptions — the
classifier holds `descs` for every class, and the hand-written pages currently show them only as
inline comments on boolean parameters. Adding them would make the pages strictly more useful
than what they replace, but it is scope the "no loss" check does not force. Decide when the
first diff is on screen, not before.
