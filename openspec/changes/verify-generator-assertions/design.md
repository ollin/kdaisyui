# Design

## Context

All six defects were found while implementing `generate-component-reference`, none by a failing
test. Four were found by reading generated output against its inputs. Two — `otp`, and then `tab`
and `calendar` — were found only by building the cross-check below and asking what it disagrees
with, which is the argument for having it.

Measurements were taken on 2026-09-12 against DaisyUI 5.7.17 and are reproducible from the
committed tree.

## Goals / Non-Goals

**Goals:** replace hand-maintained restatements of facts with derivation; make the generator fail
when its own claims disagree with an authority that exists; make every self-citation resolve; make
Kotlin-level API changes visible in review.

**Non-Goals.** Turning mutually-exclusive boolean groups into enums — 164 boolean parameters, and
`daisyTooltip(top = true, bottom = true)` compiles today. That is a real defect class and a
separate change; folding it in would put 164 signatures into a diff that also carries six defect
fixes. Also not here: unifying the config's two keying conventions, which the consumption guard
makes safe to defer.

## Decisions

### 1. The authority for the element is DaisyUI's own documentation, and this took two attempts

**First draft:** the HTML specification alone decides whether a component may have children, and
DaisyUI's markup should not be trusted, because parsing intent out of example markup is the
heuristic that produced the `dropdown` defect.

**Second draft, after checking:** DaisyUI writes void elements self-closed, and over the 57
components whose `skills/…/*.md` file has a `#### Syntax` block, its convention and the HTML
void-element set agree 57 times out of 57. Corroborating, not competing — but silent for nine.

**Third draft, and the one implemented:** the nine are not silent, they were being read from the
wrong file. Every component's `packages/docs/…/<name>/+page.md` body carries fenced ```html
examples in which daisyUI class names are marked with `$$`:

```html
<label class="$$swap">
  <div class="$$swap-on">ON</div>
</label>
```

The `$$` marker is what makes this mechanical rather than heuristic: it distinguishes daisyUI
classes from Tailwind utilities, so "the tag carrying `$$<componentClass>`" is a statement, not a
guess. **Measured: this route has an opinion for all 66 components — zero silent.**

| | HTML spec | `skills/` Syntax | docs `+page.md` |
|---|---|---|---|
| Components answered | 66 (element always known) | 57 | **66** |
| Answers | may it have children | element + self-closing | element + self-closing |

**Chosen:** the HTML void set remains the *rule* for content, because it is a closed set that
cannot be ambiguous; the docs route is the *cross-check* for the element, because it is complete
and it is DaisyUI's own statement. Self-closing in the docs agrees with the HTML void set on every
component where both speak — checked, zero disagreements.

*Wrong if:* DaisyUI stops marking classes with `$$`, or the first documented example ceases to be
the canonical one. Both are loud — the cross-check fails rather than guessing.

Worth recording: the docs route agrees with `componentElements: {dropdown: details}` *without* the
override. The heuristic that needed the override was reading the skills file, whose Syntax block
lists the popover variant first. So this route would have avoided that defect too.

### 2. Three disagreements, three different difficulties — and only one is fixed here

The cross-check is worth nothing if it lands red, because a guard that reports a known defect on
the day it arrives gets suppressed rather than obeyed. It is worth just as little if landing it
green means rushing a design decision.

| Component | Disagreement | Why |
|---|---|---|
| `otp` | `<div>` vs `<label>` | **Fixed here.** One `componentElements` entry. |
| `tab` | `<button class="tabs">` vs `<div>` | Deferred. `tabs` is the container class and `tab` the item class; the element is wrong *because* the component/part classification is. Correcting the element alone would leave `daisyTab` rendering a container and `daisyTabTab` rendering `<div class="tab">` where an item wants a button or anchor. |
| `calendar` | `<div>` vs a custom element | Deferred. The docs show Cally's `<calendar-date>`; kotlinx.html has no tag class for a custom element, so this needs a decision — a generic custom-tag mechanism, or `skip`. |

**Chosen:** an exception list, `elementCrossCheckExceptions`, carrying a reason and the tracking
issue per entry. `tab` and `calendar` get one each; the guard lands green and neither defect is
invisible again.

**And a stale exception is itself an error.** If a component stops disagreeing, its entry must go,
or the list grows into the thing it was meant to replace. That check costs one comparison and
makes the list self-cleaning.

### 3. The defects a hand-maintained list could not have caught are the ones that matter

`file-input` and `theme-controller` are transcription errors — right idea, unreachable key. A
careful reviewer could spot those. `mask`, `otp`, `tab` and `calendar` could not be spotted:
`mask` renders `<img>` and was never listed; the other three render perfectly well and merely do
the wrong thing. A derived rule and a cross-check supply the mechanism a list cannot.

### 4. Breaking, and the JVM baseline will not tell you

Removing a required trailing lambda is source-breaking for three functions. Making it optional
instead would keep compilation working and keep the lie, since the lambda still could not legally
render anything.

`daisyOtp` is worse in a specific way. `lib/api/lib.api` is a dump of JVM descriptors, verified:

```
daisyOtp (Lkotlinx/html/FlowContent;L…/HtmlId;L…/OtpVariant;L…/OtpSize;Z
          Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V
```

No receiver types, no parameter names — which in Kotlin are part of the API because of named
arguments — and no default values, only a `$default` synthetic saying that some exist. So `DIV` →
`LABEL` is invisible there, while the generated Kotlin that does show it is `linguist-generated`
and collapsed by default in review.

**Chosen:** `lib/api/components.api`, dumped from `ComponentShape`, which already holds every one
of those facts. Updated only by an explicit task — deliberately *not* part of `just generate`, so
a change goes red in CI until a human re-dumps and reads the diff, which is the discipline
`updateKotlinAbi` already establishes.

**Rejected — an element-only table.** Cheaper, and it would have caught `otp`, but a renamed
parameter is source-breaking in Kotlin and equally invisible to the JVM dump. The shape holds
names and defaults already; leaving them out would be choosing a narrower gate for no saving.

**Rejected — removing `linguist-generated` from `lib/generated/`.** It would surface the change in
review, at the cost of making every DaisyUI bump a 457-file diff. The collapsing is what makes the
committed output reviewable at all.

Note what this does *not* claim: `daisyMask`, `daisyFileInput` and `daisyThemeController` losing a
parameter **is** visible in `lib.api` — one `Function1` disappears and the arity changes. The new
baseline is for the case the old one cannot see, not a replacement.

### 5. The consumption guard is the part that outlives this change

`readComponentConfig` records which keys it consumed; the generator fails at the end of a run on
any component-keyed entry nothing read.

**Verified** that it starts green: measured across all seven component-keyed sections, exactly two
entries are unreachable, both in `noContent`, which this change deletes. `extras`, `customParts`,
`roles`, `inputTypes`, `componentAttributes` and `additionalBooleans` are clean, so a future
failure is a new finding rather than inherited debt.

A key-**existence** check would not have worked: `file-input` *is* a real DaisyUI directory. The
entry is not a typo, it is unread, and only consumption distinguishes the two.

### 6. Two element names, already modelled, used inconsistently

`generate-component-reference` put three names on the shape — `element` (`FIELDSET`, the tag class
and lambda receiver type), `tagBuilder` (`fieldSet`, what the generated Kotlin calls) and `htmlTag`
(`fieldset`, what prose must say) — and had the Markdown emitter use the third. The Kotlin emitter
still uses `tagBuilder` for both, which is right for emitted code and wrong for prose.

**Verified**: exactly two files are affected, `Fieldset.kt` and `Textarea.kt`. `INPUT` spells
builder and element identically.

## Section 1 measured, 2026-09-12

Against DaisyUI 5.7.17, all 66 non-skipped components. Throwaway probe under `./tmp/`; not a unit
test, for the reason recorded in `tasks.md`.

**1.1 — `noContent` adds nothing to the HTML rule.** Zero components are listed that are not void,
so deriving loses nothing. Nine components render a void element; eight entries exist, of which
**six are actually reachable** and one void component (`mask`) was never listed at all. The
assumption holds and the change proceeds.

**1.2 — the docs route answers all 66, and disagrees exactly three times.**

```
no opinion: 0
calendar   generator=<div>     docs=<calendar-date>
otp        generator=<div>     docs=<label>
tab        generator=<button>  docs=<div>
self-closing vs HTML void set: agree everywhere
```

As planned, with one refinement: `calendar`'s documented element is `<calendar-date>`, Cally's
custom element. An earlier probe reported it as `calendar` because its tag regex stopped at the
hyphen — a parsing artefact in the probe, not in the data. The refined reading makes the deferral
reason sharper: this is not a kotlinx.html tag that was chosen wrongly, it is an element
kotlinx.html has no tag class for at all.

The self-closing line is worth keeping: DaisyUI's convention and the HTML void set agree on every
component where both speak, so the rule and the cross-check corroborate rather than compete.

**1.3 — exactly two config entries are unreachable, both in `noContent`.**

```
noContent[] "file-input"
noContent[] "theme-controller"
```

Checked across all component-keyed sections, including `subComponentElements` by part class and
the three directory-keyed sections. Since this change deletes `noContent`, the consumption guard
lands green and any later failure is a new finding rather than inherited debt.

## Risks / Trade-offs

**Consumers passing an empty lambda stop compiling, and `otp` callers change silently at the type
level.** Intended for the first. The second is the dangerous one, which is what `components.api`
and the migration note are for.

**The void-element set is duplicated knowledge.** kotlinx.html knows which tags are void; we
restate fourteen strings. Extracting it from that library's internals would couple the codegen to
them, and the set has not changed since HTML5. The cross-check means a wrong restatement is caught
rather than trusted.

**Two deferred defects ship.** `tab` renders a container class on the wrong element and `calendar`
renders a `<div>` for a custom element, in this release as in the last. The change makes them
named and tracked rather than unknown, which is the honest improvement available without letting a
design question block six fixes.
