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

## Finding — the probe of tasks 1.1 and 1.3, run 2026-09-12

Measured over all 66 non-skipped components, not the four samples the tasks asked for. The
probe scripts were throwaway and lived under `tmp/`.

**The structural claim holds.** No page carries a `##` heading — 0 of 66. There is no prose
section, usage example or note anywhere in `docs/reference/*.md` beyond the heading, the DaisyUI
link, one description paragraph and the fenced signature blocks. So the page shape is a
template, as the proposal assumed.

**Generating corrects three classes of real drift**, found in four sampled pages alone:

| Page | What the hand-written page says | What the code does |
|---|---|---|
| all four sampled | no `id` parameter in any signature | `id: HtmlId? = null` is the second parameter of every function |
| `drawer.md` | five functions | six — `daisyDrawerButton` is absent from the page |
| `megamenu.md` | `daisyMegamenuActive` takes a `DIV` lambda | it takes a `SPAN` |
| `megamenu.md` | two functions | three — `daisyMegamenuPanel` is absent |
| `megamenu.md` | `Renders <div class="megamenu ...">` | `<div class="megamenu ..." popover>` |

The missing `id` is the largest of these and was invisible: it is absent from **every** signature
on **every** page, so nothing looked inconsistent.

**One element is NOT derivable: the description.** All 66 descriptions are hand-written editorial
summaries, and **0 of 66** match DaisyUI's frontmatter `desc`. This is not a sampling artefact,
it is universal:

```
card   hand: Content containers with body and title.
       daisy: Cards are used to group and display content in a way that is easily readable.
alert  hand: Status messages and notifications.
       daisy: Alert informs users about important events.
```

### 5. The editorial summary becomes a generator input

**Chosen:** `codegen-config.json` gains a `docSummaries` map — a required one-line `summary`
per component, plus an optional longer `description` for the page. The 66 existing summaries
move there verbatim.

This keeps the pages generated output in the full sense the spec requires (reproducible from
committed inputs, drift-checked) while keeping the editorial text that a DaisyUI marketing
sentence cannot replace.

**Rejected — use DaisyUI's `desc` verbatim.** It is not viable, and the reason is mechanical
rather than aesthetic: `docs/reference/index.md` is a 66-row table whose Description column is
one cell wide. Aura's frontmatter `desc` is 48 words. A table with 66 paragraph-length cells is
not a table, and the index page exists to be scanned. Some short summary therefore has to exist
somewhere, which forces this decision rather than leaving it to taste.

**Rejected — one field only, shortening the three long pages.** 63 of 66 pages already use the
same text as their index row; only `aura`, `megamenu` and `otp` say more on the page than in the
table. Collapsing to one field would delete that extra sentence from three pages, which the
"no loss" check forbids. Two fields, with 63 components needing only the first.

**Fallback, so a DaisyUI bump is never blocked:** a component with no `docSummaries` entry falls
back to the first sentence of its frontmatter `desc`. A new component then documents itself
immediately and badly, rather than failing the build or documenting itself not at all — and the
bad summary is visible in the regeneration diff, which is where a human notices it.

### What this does to the change's premise

The premise survives, narrowed. The proposal said every fact on the page already exists in the
codegen; the truth is that every *mechanical* fact does — signatures, receivers, rendered tag,
enum entries, element — and the one *editorial* fact does not. Moving that one to a committed
config file is what makes the rest generable, and it is a smaller price than the plan's fallback
positions (abandoning, or accepting a loss).

## Open question — resolved by the same probe

Whether the generated pages should carry the DaisyUI class names and their descriptions, which
the classifier holds in `descs`.

**Resolved: yes, as inline comments on boolean parameters, because that is what the pages
already do** — `megamenu.md` comments all three of its booleans. The probe showed `descs`
reproduces them, with different wording (DaisyUI's own, e.g. `dash outline style`). Emitting
them everywhere makes 63 pages strictly more informative and costs nothing; the wording
difference on the three commented ones is a template decision to record in task 5.4, not a loss.
