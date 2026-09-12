# Tasks

Ordered by uncertainty, not by dependency. Section 1 can refute the change's central
assumption — that the codegen holds everything the 66 pages contain — and it does so before any
generator is wired into the build.

## 1. Check the assumption that the pages are fully derivable

- [x] 1.1 `. r (internal)` Write a throwaway script under `./tmp/` that emits one component
  reference page from the classified model, for a component with several parts and an enum
  (`card`). Diff it against `docs/reference/card.md`. Record what the model cannot supply.
- [x] 1.2 `. d` Record the finding in `design.md`. **If anything is missing that a reader
  needs, revise the proposal before continuing** — including abandoning the change if the gap
  is large. This is the task the whole plan rests on.
- [x] 1.3 `. r (internal)` Repeat 1.1 for the three shapes most likely to break the template: a
  component with no parts (`badge`), one whose parts render different elements (`drawer`, whose
  overlay is a `LABEL`), and the one that motivated this change (`megamenu`, whose
  `daisyMegamenuActive` is a `SPAN` and whose `daisyMegamenuPanel` has no class of its own).

## 2. Enabling refactorings — generated Kotlin stays byte-identical

- [x] 2.1 `. r (internal)` Pin the current Kotlin output: confirm `just generate` leaves
  `lib/generated/**` unchanged, so every later task has a zero-diff baseline to prove against.
- [x] 2.2 `^ r (internal)` Extract from the Kotlin emitter whatever the Markdown emitter also
  needs — the per-function rendered tag, receiver type, parameter list and defaults — into a
  shared shape. Verify `lib/generated/**` is byte-identical.
- [x] 2.3 `. d` Move the 66 editorial summaries into `codegen-config.json` → `docSummaries`,
  verbatim from the pages, plus the longer page `description` for the three that have one
  (`aura`, `megamenu`, `otp`). Pure data move — nothing reads the section yet. Added by the
  section 1 finding; see `design.md` decision 5.

## 3. The Markdown emitter

- [x] 3.1 `^ F (internal)` Emit one component page from the shared shape, with the
  `GENERATED — DO NOT EDIT` header naming its DaisyUI input. The description comes from
  `docSummaries`, falling back to the first sentence of the frontmatter `desc` when absent.
  Test against the `card` expectation from 1.1.
- [x] 3.2 `^ F (internal)` Emit the pages for the three shapes from 1.3.
- [x] 3.3 `^ F (internal)` Emit `docs/reference/index.md`'s component table.

## 4. Move the prose out of `index.md`

- [x] 4.1 `. d` Move **Common parameters**, **Core utility** and **Requirements**
  (`docs/reference/index.md:85-118`) into `docs/explanation.md`, next to the existing class-merging
  material. No wording changes in this commit — a move that also edits is not a move.
- [x] 4.2 `. d` Repoint every link to the moved sections. `docs/reference.md`,
  `docs/tutorials/index.md` and `docs/tutorials/getting-started.md` link into `reference/`;
  check each.
  **Nothing to repoint.** Searched every `.md` in the repository for the three section
  anchors (`#common-parameters`, `#core-utility`, `#requirements`) and for links into
  `docs/reference/`: no link targeted a moved section. `docs/reference.md:7` points at
  `reference/index.md`, which still holds the table; `docs/tutorials/index.md:30` points at
  `docs/reference.md`; `getting-started.md` links only to the repository root and to
  `build-a-dashboard.md`. The task's premise was wrong, which is the finding.

## 5. Wire it up

- [ ] 5.1 `^ F (internal)` Add the Gradle task alongside `generateComponents`,
  `generateComponentTests`, `generateHeroicons`, `generateHeroiconTests`.
- [ ] 5.2 `. F (internal)` Add it to `just generate`.
- [ ] 5.3 `^ F (internal)` Generate all 66 pages plus the index, and commit them.
- [ ] 5.4 `. d` Read the full diff of all 66 pages against what they replaced. Every difference
  is a correction or a deliberate template decision; record any that is neither and fix it
  before the drift job is extended.

## 6. Close the loop — the part that makes it worth anything

- [ ] 6.1 `^ F (internal)` Extend `generated-sources-drift` to `docs/reference/`.
- [ ] 6.2 `. r (internal)` Prove the gate bites: hand-edit a generated page on a throwaway
  branch, observe the job fail and name the file, then revert. Record the run. The existing job
  was proven this way on PR #231 and the spec cites it.

## 7. The copies that remain

- [ ] 7.1 `. d` Fix `README.md`'s Megamenu row by hand — it is missing `daisyMegamenuPanel`
  today — and add a comment marking the table as a hand-maintained copy that
  `verify-doc-snippets` is expected to take over.
- [ ] 7.2 `. d` Correct the stale `.js` references left by the TypeScript port:
  `.opencode/skills/kdaisyui-testing/SKILL.md:15` and `:20`, and
  `openspec/specs/generated-sources/spec.md:22`, `:87`, `:115`. The line citations at `:87`
  moved too — `frontmatter.ts:336-342` and `index-heroicons.ts:49` are the current locations.
- [ ] 7.3 `. d` Update `AGENTS.md` and the `kdaisyui-codegen` skill: `docs/reference/**` joins
  the never-hand-edit list, and the codegen gains a fifth output.

## 8. Gate and adoption

- [ ] 8.1 Full green per `openspec/config.yaml` — repo-wide compile, complete suite including
  e2e, coverage, `analyze_change_set`.
- [ ] 8.2 `openspec validate --all --strict`.
- [ ] 8.3 Write the evaluation under `./tmp/` for Oliver to adopt.
