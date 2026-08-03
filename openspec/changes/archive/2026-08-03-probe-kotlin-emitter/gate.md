## What are we deciding

Whether replacing the hand-rolled JavaScript string-concatenation emitter
(`codegen/src/generator-new.js`, ~470 lines) with a Kotlin emitter built on KotlinPoet is
work whose answer we can look up, or work we have to find out by doing.

## Evidence for COMPLICATED

| What we would need to know | Source | Cost to fetch |
|---|---|---|
| Can KotlinPoet emit what we emit today — extension functions on kotlinx.html receivers, lambda parameters with receivers, default arguments, nested enums? | KotlinPoet API docs + its test suite | ~1h reading |
| What the current generator actually emits | `lib/build/generated/sources/kotlin/main/…` — already produced, 63 files on disk | 20s (`./gradlew :lib:generateComponents`) |
| DaisyUI's category taxonomy that drives the parameters | `daisyui/packages/docs/src/routes/(routes)/components/*/+page.md` frontmatter, 65 files | already read this session |
| Whether a Gradle task can run a Kotlin generator instead of Node | Gradle docs, `buildSrc` | known, standard |
| Which JS constructs have no Kotlin equivalent | read all 12 files in `codegen/src/` | ~2h |

Every mechanical question about the port has a nameable source and a bounded cost. Nothing
in the *how* is unknown.

## Evidence for COMPLEX

The mechanics are not what we are actually asking. The real questions have no source:

- **"Does it feel better?"** — the stated goal is simpler creation and a more comprehensible
  codebase. No document decides that. Only writing one component and looking at it does.
- **Does a typed model make the API redesign easier, or just differently awkward?** This
  depends on how DaisyUI's five-axis taxonomy meets Kotlin's type system — an interaction
  nobody has tried. Reading both sides separately does not predict it.
- **Is the port worth its cost at all?** Shoup's "if you don't end up regretting your early
  technology decisions, you probably over-engineered" cuts directly at this, and the honest
  subtractive alternative — delete the dead forks, swap the hand-rolled YAML parser for the
  `js-yaml` that is already a dependency, redesign the API in the JavaScript that exists —
  has not been ruled out by anything except my preference.

**Note for the verdict: this change as currently scoped mixes both domains.** "Can KotlinPoet
express our constructs" is complicated and cheap to answer. "Is this better" is complex and
only answerable by building something. That may mean the gate should split this rather than
classify it.

## Verdict

**Domain:** complex
**Decided by:** Oliver
**Date:** 2026-07-30
**Reasoning:** The mechanical half has sources and would be answered in an afternoon, but
answering it settles nothing — knowing that KotlinPoet *can* express our constructs says
nothing about whether the result reads better, which is the actual question and has no
source. So: build a parallel Kotlin implementation next to the existing JavaScript one and
look at the facts. Parallel rather than replacing, so the working generator keeps working
and the experiment stays discardable. Oliver's call: "ich denke dieses experiment ist es
wert."

Scope note: option (b) — establishing KotlinPoet coverage as a separate complicated task
first — was declined. The lookups happen inside the probe, where a blocker would itself be
a signal rather than a detour.

<!-- Agent's read, offered as input and not as the decision:

The complicated half is real but small, and answering it changes nothing on its own —
knowing that KotlinPoet *can* express the constructs tells us nothing about whether the
result reads better. The question we care about is the complex one.

Two ways to take it:

(a) One probe, scoped to the complex question. Emit Button with KotlinPoet, put it next to
    today's Button.kt, look at both. The KotlinPoet lookups happen inside the probe as
    needed rather than as a separate phase.

(b) Split. A short complicated task establishes KotlinPoet's coverage first; the probe then
    runs on cleared ground.

I lean (a): the lookup is cheap enough to do inside the probe, and doing it separately front
-loads exactly the kind of analysis that this gate exists to question. But (b) is defensible
if the risk of discovering a KotlinPoet blocker mid-probe feels worse than the up-front cost.
-->

## Re-entries

<!-- Append dated entries when the reading changes. -->
