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

### 3. The remaining copies stay hand-maintained — and are named as debt

**Corrected during task 7.1.** This decision said the README holds a third copy of the 66
components. It does not. The README has a three-row *What's new in 0.2.0* table, and the
66-component copy is in **`llms.txt`** — which also carries a *Detailed component signatures*
section for all 66, making it the largest copy of facts the codegen already holds, larger than
the reference pages this change generates.

Both are stale in the same way and were fixed by hand here: the Megamenu row in each omitted
`daisyMegamenuPanel`, and `llms.txt`'s Drawer row omitted `daisyDrawerButton`. `llms.txt` carries
one defect that was left: none of its 66 signatures mentions `id: HtmlId?`, the parameter every
generated function takes.

Neither is generated here. The README mixes badges, quick start, migration notes and a table, so
generating it means the injector from decision 1. `llms.txt` needs no injector and is a strong
candidate for whole-file generation from this same shape — but it is a different artifact with a
different audience, and folding it in would double the diff this change asks a reader to adopt.
Tracked as its own change.

Both tables now carry a comment saying they are hand-maintained, naming what went wrong, and
pointing at `docs/reference/` as the generated thing to check against. Marking is the part that
survives a fix.

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

## The 66-page diff, read (task 5.4)

67 files, 729 insertions, 148 deletions. The requirement is that every difference be a
correction or a deliberate template decision and never a loss, so the diff was classified
mechanically first and then read.

**No page lost a function, an enum entry or a parameter that the code actually has.** The check
was per file: a removed line counts as surviving only if its text reappears in the same file's
added lines. 25 removals did not, and every one is accounted for below.

### Corrections — things the hand-written pages got wrong

| Page | Said | The code says |
|---|---|---|
| `button.md` | `class="button ..."` | `btn` |
| `calendar.md` | `class="calendar ..."` | `cally` |
| `hover3d.md` | `class="hover3d ..."` | `hover-3d` |
| `stat.md` | `class="stat ..."` | `stats` |
| `tab.md` | `class="tab ..."` | `tabs` |
| `megamenu.md` | `<div class="megamenu ...">` | `<div class="megamenu ..." popover>` |
| `tooltip.md` | `TooltipVariant` includes `Neutral` | it does not |
| `validator.md` | takes a `content` lambda | it does not — `validator` is in `noContent` |
| every page, every function | no `id` parameter | `id: HtmlId? = null` is the second parameter |
| `breadcrumbs.md` | 1 function | 3 |
| `drawer.md` | 5 functions | 6 — `daisyDrawerButton` was missing |
| `megamenu.md` | 2 functions | 3 — `daisyMegamenuPanel` was missing |
| `modal.md` | 5 functions | 6 — `daisyModalPopover` was missing |

The first five are the worst of these and were invisible: a reader copying `class="button"` into
their own HTML gets no styling at all, because that class does not exist. Five of 66 pages named
a CSS class DaisyUI does not ship.

Also corrected, silently and everywhere: `Requirements` in `index.md` pointed at
`../.tool-versions`, which resolved to `docs/.tool-versions`. Moving the section to
`docs/explanation.md` in task 4.1 made all three of those links resolve.

### Deliberate template decisions

- **One comment line per enum instead of one for both.** 12 pages. No entry set changed — checked
  by normalising both forms and comparing — except `tooltip`, which is a correction above.
- **Inline comments on every DaisyUI boolean.** Most of the 148 deletions are boolean parameter
  lines reappearing with a description. Six of them had a hand-written comment already, in
  `aura`, `megamenu` and `otp`, and those are now DaisyUI's own wording with two spaces before
  the `//` rather than hand alignment. The hand-written phrasing was terser in places — *"dropdown
  fills the page width"* against *"megamenu dropdown will fill the entire width of the page"* —
  and that is a real trade, accepted because the generated one cannot go stale.
- **Paragraphs are emitted on one line, not re-wrapped.** Affects the three pages whose
  description was hard-wrapped: `aura`, `megamenu`, `otp`. Markdown renders both identically, and
  a generator that re-flows prose is a generator fighting its input.
- **One fenced block per function.** Only `megamenu.md` combined two, and it now matches the
  other 65.
- **The do-not-edit attribution**, five lines of HTML comment per page.

### What the index proves

`index.md`'s 66 table rows are byte-identical to the hand-written ones. Its whole diff is the
attribution header plus a trailing newline the file lacked. The table was the part most likely to
be quietly wrong after a DaisyUI bump, and reproducing it exactly is the strongest evidence that
the generator reads the same facts the humans did.

## The drift gate, observed failing (task 6.2)

A gate nobody has watched fail is a gate nobody should trust, which is why the job itself was
proven this way on PR #231 rather than assumed.

Throwaway branch `prove-docs-drift`, one commit, reintroducing by hand the exact defect this
change exists to prevent: `docs/reference/megamenu.md`'s `daisyMegamenuActive` lambda receiver
turned back from `SPAN` to `DIV`. Draft PR #339 against `main`, since the workflow triggers on
`pull_request` and a push to a topic branch does not run it.

Run [34689586943](https://github.com/ollin/kdaisyui/actions/runs/34689586943), job
`generated-sources-drift`:

```
##[error]lib/generated or docs/reference does not match what the generators produce.
Run 'just generate' and commit the result.

 docs/reference/megamenu.md | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
 M docs/reference/megamenu.md
##[error]Process completed with exit code 1.
```

It failed, and it named the file.

**The second half of the proof matters as much as the first.** `unit-tests`, `codegen-tests`,
`mutation-tests` and `api-baseline` all passed on the same commit. So the failure is specific to
this gate rather than a broken build — and, read the other way, four other gates looked at a
reference page documenting the wrong lambda receiver and had nothing to say about it. That is
precisely the blind spot the change closes, demonstrated rather than argued.

PR closed unmerged, branch deleted from the remote and locally.

## Open question — resolved by the same probe

Whether the generated pages should carry the DaisyUI class names and their descriptions, which
the classifier holds in `descs`.

**Resolved: yes, as inline comments on boolean parameters, because that is what the pages
already do** — `megamenu.md` comments all three of its booleans. The probe showed `descs`
reproduces them, with different wording (DaisyUI's own, e.g. `dash outline style`). Emitting
them everywhere makes 63 pages strictly more informative and costs nothing; the wording
difference on the three commented ones is a template decision to record in task 5.4, not a loss.
