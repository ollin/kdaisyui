# Design

## Context

This change exists because of a question Oliver asked — *"since we know all the classes, should we
build them as a Kotlin enum and leave the string as an alternative?"* — and because measuring that
question moved the answer somewhere else.

## Decisions

### 1. Not an enum of all 555 classes. The gap is prefixes.

**Rejected, and the measurement is the reason.** An enum over every known DaisyUI class would
restate what the API already says. Measured 2026-09-12 over all 66 non-skipped components:

```
known DaisyUI classes:             555
already reachable typed:           536   (an enum entry or a boolean on their own component)
NOT reachable through a parameter:  19
```

So 97% of such an enum would duplicate the generated enums and booleans, offering a second way to
say `ButtonSize.Lg`.

And it would still not solve the case the escape hatch is actually used for. Measured over every
`extraClasses` and `addClassNames` call in `example-app`, `e2e-tests`, `lib/src` and `docs` —
47 distinct tokens:

| | count | examples |
|---|---|---|
| a known DaisyUI class | 12 | `join-item`, `btn-primary`, `input-error` |
| **carrying a variant prefix** | **12** | `lg:btn-lg`, `max-sm:megamenu-vertical`, `xl:stats-horizontal` |
| not a DaisyUI class | 35 | `rounded-full`, `w-full`, `p-2`, `col-span-12`, `bg-base-100` |

**74% of what the escape hatch receives is not a DaisyUI class at all**, and a flat enum cannot
express a prefix on the remainder. So the enum covers roughly a quarter of real use, badly.

The prefix, by contrast, is the whole of the DaisyUI usage: ten of the twelve DaisyUI tokens carry
one.

### 2. `extraClasses: String?` stays, unnarrowed

The same measurement says so. 35 of 47 tokens are Tailwind utilities — `flex`, `p-2`,
`col-span-12` — and typing those is not this library's job. A string escape hatch is the correct
tool for them, and the correct lesson from the measurement is that it is being used for two
different things, only one of which this library can improve.

### 3. Stacking is in scope, because Tailwind stacks — decided 2026-09-13

Oliver's answer that state prefixes (`dark:`, `hover:`, `focus:`) are in scope is what forces this.
Breakpoints alone are mutually exclusive: a class applies at `md` or at `lg`, never both. States
are not — `dark:` and `md:` can hold at once, and Tailwind writes that as `dark:md:btn-primary`.

So the type cannot be a single enum with an `at`. It has to express "these variants, together",
while forbidding the combinations that mean nothing.

**This is the change's expensive decision and it is priced as such** (task 2.3, 1.2 h for the
block). Type-level separation — breakpoints one type, states another, only states stackable — is
the obvious shape and is written down as a decision rather than assumed, because "obvious" is how
a design gets an unexamined constraint.

### 4. The DaisyUI-specific variants connect this to #345

`is-drawer-open:` and `is-drawer-close:` ship today in `kdaisyui-classes.txt` **as if they were
classes**, trailing colon and all — filed as #345 because the file promises Tailwind can generate
CSS for every line and cannot for those.

They are not broken classes. They are DaisyUI's own *variants*, and DaisyUI's prose says so:
*"we use the `is-drawer-open` and `is-drawer-close` variants. For example `is-drawer-close:hidden`."*

So #345 deletes them from one place and this change may give them a home in another. Worth
deciding together in task 2.2 rather than separately: #345's fix is right either way, but if the
variant type models them, the deletion is a move rather than a loss.

## Block 1 measured, 2026-09-13 — two assumptions refuted

Both *Wrong if* clauses in the proposal fired. That is the clause working, not the plan failing.

**The first measurement asked the wrong question and is recorded as such.** It counted variants
across every class in DaisyUI's examples, including Tailwind utilities this library will never
type — 26 distinct variants, 16 uncategorised, 9 of them open-ended forms like `peer-checked` and
`[--tglbg:…]`. That says the variant set is unbounded, which is true and irrelevant: the type only
has to express variants applied to a **DaisyUI** class. Re-measured on that basis, with a
tokeniser that respects brackets, because arbitrary values contain colons.

### What is applied to a DaisyUI class

63 prefixed tokens in DaisyUI's own examples, **8 distinct variants**:

| | count |
|---|---|
| `sm` `lg` `md` `xl` `max-sm` `max-lg` `max-md` | 17, 17, 13, 6, 4, 1, 1 |
| `is-drawer-close` | 4 |
| **anything else** | **0** |

### 1.1 — stacking is refuted, in both sources

Our code: 12 prefixed tokens, **0 stacked**. DaisyUI's examples: **0 stacked on a DaisyUI class**.

Four DaisyUI examples do stack — `max-lg:peer-checked:block`, `checked:[--tglbg:…]` — and every
one applies its stack to a **Tailwind utility**, never to a DaisyUI class. So stacking is real in
Tailwind and absent from the surface this change types.

**Decided: drop stacking.** One variant per application. This removes the expensive decision from
block 2 — the one that needed a type-level rule forbidding `Breakpoint.Md at Breakpoint.Lg` while
allowing `Dark at Breakpoint.Md`. There is nothing to forbid, because there is nothing to combine.

### 1.2 — the variant set IS closeable, for this surface

Seven breakpoints and DaisyUI's own drawer variants. Nothing open-ended reaches a DaisyUI class.

**Decided: a closed set, no escape hatch.** `extraClasses` remains for everything outside it,
which is what it is for.

### States: kept, on better evidence than the instruction

DaisyUI applies **no** state variant to its own classes — `hover:`, `focus:`, `checked:` occur
only on Tailwind utilities. So the measurement does not support including them, and Oliver's
instruction to include them is unattested by DaisyUI's documentation.

It is attested here. `AGENTS.md` records `dark:alert-info` as a case that *silently did nothing*
before `css-delivery` compiled the stylesheet properly — a real person wanted a state on a DaisyUI
class and got no CSS. That is one recorded use and zero documented ones, which is thin but is
evidence, and it points the same way as the instruction.

**Decided: keep states, and say why they are in on that basis rather than on frequency.** The cost
is now small, since dropping stacking makes a state and a breakpoint the same shape.

### What this does to the estimate

Block 2's frozen 1.2 h priced **three** decisions. The measurement removed one and settled a
second. **The estimate is NOT revised** — per the calibration protocol a frozen number stands, and
revising it here would destroy the data point this change exists to produce. The actual will be
measured against 1.2 h, and the gap recorded as "scope removed by measurement", the same way run 1
kept its two-valued block 2 estimate rather than overwriting it.

## The shape changed twice more, and both times the measurement did it

### A prefix mostly lands on a BOOLEAN, which has no value to pass

The call shape decision (2.1) turned on something nobody had measured: what kind of typed class
actually receives a prefix. Of the 63 prefixed daisyUI tokens in DaisyUI's examples:

| backed by | count | example |
|---|---|---|
| an **enum** — a value exists today | 16 | `lg:btn-lg` |
| a **boolean** — no value exists | 43 | `lg:card-side`, `xl:stats-horizontal` |
| the component class or a part | 4 | `lg:tooltip` |

**68% of the need is on booleans.** `lg:card-side` is `side: Boolean = false` — there is nothing
to hand to a function.

**This partly reverses the rejection in decision 1.** Oliver proposed values for the classes; the
measurement said 536 are already typed and I concluded values were redundant. They are redundant
for *naming* a class. They are the *prerequisite* for prefixing one, and that had not been checked.

### Option C, and a measurement error worth keeping

C — turn mutually-exclusive groups into enums, which gives them values — was chosen on
2026-09-13. Measured before building, because three assumptions had already fallen in this change.

**The first run of that measurement was wrong, and the error is the interesting part.** It counted
two classes of one category on one element as proof the category is not exclusive. But
`menu-vertical lg:menu-horizontal` is not two classes holding at once — it is *one* class at two
breakpoints, which is precisely the pattern this change exists to type. Counting it as
co-occurrence concludes a group is not exclusive **from evidence that it is**.

Re-measured over unprefixed classes only:

| | first run | corrected |
|---|---|---|
| groups that genuinely co-occur | 14 of 59 | **6 of 59** |
| prefixed boolean classes C would cover | 3 of 19 | **11 of 19** |

### What the remaining six co-occurrences actually are

Four are not counter-examples either. `indicator-top indicator-start`, `toast-top toast-end`,
`dropdown-top dropdown-center`, `tooltip-top tooltip-start` are **two axes** — a vertical and a
horizontal placement — that DaisyUI files under one category. Split by axis, each is exclusive,
and six more prefixed classes become covered.

Two are genuinely independent flags and stay boolean: `avatar-online` + `avatar-placeholder`,
`table-pin-rows` + `table-pin-cols`.

### Coverage, finally

| | count |
|---|---|
| covered by exclusive-group enums | 11 |
| covered by splitting placements into two axes | +6 |
| **lone members with no group to join** — `drawer-open`, `megamenu-vertical` | 2 |
| **total prefixed boolean classes** | **19** |

The last two need a generated constant each, which is decision 1's rejected option applied where
it is actually necessary rather than to all 555. `max-sm:megamenu-vertical` is in this
repository's own example app, so it is a real need and not a completeness itch.

## Naming: the name is the test — decided 2026-09-13

Oliver's constraint: **name by intention, not implementation.** Three consequences, and the
second one found a defect in this change's own measurement.

### 1. Where a category is coherent, one rule covers many groups

`directions` is nine groups saying the same thing — vertical or horizontal. The established name
for that axis is **`Orientation`**: `MenuOrientation`, `StatOrientation`, `TimelineOrientation`.
Alert, Carousel, Divider, Footer, Join, Menu, Stat, Steps and Timeline in one decision.

### 2. Where no intention name exists, the group is wrong — and this catches what the
### exclusivity measurement misses

Co-occurrence in DaisyUI's examples is **weak evidence** of exclusivity: those examples are small,
so absence proves little. Two groups pass the measurement and fail the name:

```
Collapse   modifiers   arrow, plus, open, close
Menu       modifiers   disabled, active, focus, dropdown-show, paged
```

`arrow`/`plus` is the indicator icon; `open`/`close` is the state. Two concepts in one category.
Menu is not a choice at all — three item states and a layout mode.

**Failing to find a name is the signal.** `Collapse` splits into `CollapseIndicator` and
`CollapseState`; `Menu` stays boolean. That is cheaper than a better exclusivity measurement and
catches exactly what it lets through.

### 3. Names are editorial, so they live in config

DaisyUI supplies the classes, not a name for the group. Not derivable — so `enumNames` in
`codegen-config.json`, exactly like `docSummaries`, policed by the consumption guard so a stale
entry cannot rot.

### The two hard cases, settled by reading the CSS

Both were flagged as weak and both were checked against `button.css` rather than argued from
their names.

**`outline, dash, soft, ghost, link` → `ButtonEmphasis`.** All five set the *same six* variables —
`--btn-bg`, `color`, `--btn-border`, `background-image`, `--btn-inset`, `--btn-shadow`. One
dimension, confirmed.

My suspicion that `link` was the outlier was intuition and the CSS refutes it. The real outlier is
`dash`: it shares a rule block with `.btn-outline` and adds only `--btn-border-style: dashed`, so
it is outline with a dashed border rather than a sibling. Kept as a fifth entry anyway, because
`btn-dash` alone is DaisyUI's documented form and modelling it as `Outline` + `dashed: Boolean`
would emit `btn-outline btn-dash` instead.

The sequence filled → soft → outline → ghost → link is decreasing emphasis, the same axis Material
Design names *emphasis*.

**`wide, block, square, circle` → `ButtonLayout`.** All four set width — `w-full max-w-64`,
`w-full`, and `width: var(--size)` for the two icon shapes — so they are genuinely exclusive.

But the intention splits: `wide`/`block` is how much horizontal space, `square`/`circle` is an icon
button. Rule 2 would say split them, and splitting would lose the exclusivity guarantee, since two
separate parameters could both set width.

**This is the exception to rule 2, and it is recorded rather than the rule being quietly bent:**
the group is real — the CSS proves it — and only the name is uncomfortable. Rule 2 rejects a group
whose members share no *effect*; here they share one and the name is merely imperfect.

## The rule applied to all 36 groups — 2026-09-13

**Eight of 36 fail the name test.** That is 22%, and it is the rule earning its place: every one
of those eight passed the co-occurrence measurement, so without it they would have shipped as
enums whose members share no intention.

### Named (28)

| Rule | Name | Groups |
|---|---|---|
| `directions` → `Orientation` | `AlertOrientation`, `CarouselOrientation`, `DividerOrientation`, `FooterOrientation`, `JoinOrientation`, `MenuOrientation`, `StatOrientation`, `StepsOrientation`, `TimelineOrientation` | 9 |
| surface treatment → `Emphasis` | `AlertEmphasis`, `BadgeEmphasis`, `ButtonEmphasis` | 3 |
| the rest, named individually | see below | 16 |

```
Aura      styles      AuraEffect        dual, rainbow, holo, gold, silver, glow
Button    modifiers   ButtonLayout      wide, block, square, circle      (documented exception)
Card      styles      CardBorder        border, dash  → Solid, Dashed; absent = no border
Card      modifiers   CardLayout        side, image-full
Carousel  modifiers   CarouselSnap      start, center, end
Chat      placements  ChatSide          start, end
Divider   placements  DividerAlignment  start, end
List      modifiers   ListColumn        col-wrap, col-grow
Loading   styles      LoadingAnimation  spinner, dots, ring, ball, bars, infinity
Mask      styles      MaskShape         15 shapes
Mask      modifiers   MaskHalf          half-1, half-2
Megamenu  modifiers   MegamenuWidth     wide, full
Stack     modifiers   StackAlignment    top, bottom, start, end
Swap      styles      SwapAnimation     rotate, flip
Tab       styles      TabAppearance     box, border, lift
Tab       placements  TabPosition       top, bottom
```

### Failed the name test (8)

Each is a category DaisyUI's frontmatter groups by where the class lives, not by what it means.

| Group | Members | Why no name fits | Outcome |
|---|---|---|---|
| `Button.behaviors` | active, disabled | Not a choice — a button can be neither, and `disabled` already exists as an `extras` parameter driving the HTML attribute | stay boolean |
| `Collapse.modifiers` | arrow, plus, open, close | Indicator icon **and** state | split: `CollapseIndicator`, `CollapseState` |
| `Dropdown.modifiers` | hover, open, close | `hover` is a trigger mode, `open`/`close` a forced state | split: `hover` boolean + `DropdownState` |
| `Menu.modifiers` | disabled, active, focus, dropdown-show, paged | Three item states and a layout mode | stay boolean |
| `Rating.modifiers` | half, hidden | `rating-half` is half-star granularity; `rating-hidden` is a hidden zero input. Unrelated | stay boolean |
| `Tab.modifiers` | tab-active, tab-disabled | Already broken — the doubled prefix is #342's inverted classification | defer to #342 |
| `Timeline.modifiers` | snap-icon, box, compact | Icon alignment, a look, and spacing | stay boolean |
| `Modal.placements` | top, middle, bottom, start, end | Vertical **and** horizontal axis, like `indicator` and `toast` | split by axis |

### What the failures have in common

Seven of the eight are `modifiers` — DaisyUI's catch-all category. `styles`, `directions` and
`placements` describe one dimension each and named cleanly; `modifiers` means "everything else"
and grouping by it produces a group by accident.

**So the generator should treat `modifiers` as boolean by default and require an explicit
`enumNames` entry to promote one**, rather than the other way round. That inverts the default for
the one category that cannot be trusted, and the consumption guard catches a promotion that stops
being justified.

## A boolean must make `true` unambiguous — decided 2026-09-13

Oliver's condition for leaving `modifiers` boolean by default: **only if the parameter name makes
clear what `true` means.** Otherwise an enum, named by intention.

Tested against the seven groups that stay boolean, reading `daisyui/packages/daisyui/src/components/*.css`
rather than guessing from the class names. **Five fail**, and two of the five are actively
misleading:

| today | what the CSS does | what `true` means | renamed |
|---|---|---|---|
| `hidden` (rating) | `w-2 bg-transparent` | a narrow transparent item serving as the **zero** choice — it hides nothing | `clearOption` |
| `hover` (dropdown) | `&.dropdown-hover:hover` | **opens on hover**, not "is hovered" | `openOnHover` |
| `half` (rating) | `* { width: calc(var(--size) * 0.5) }` | **half-star granularity** | `halfStars` |
| `focus` (menu) | `&.menu-focus, &:focus-visible` | forces the focused **appearance** without focus | `focused` |
| `box` (timeline) | border, radius, background, shadow | content in a **card** | `boxed` |

`hidden = true` is the worst: it reads as "invisible" and produces "no stars selected".

Unchanged and clear: `zebra`, `pinRows`, `pinCols`, `compact`, `disabled`, `active`,
`placeholder`.

### The rule has two outs, and for all five it is the second

1. **Enum** — when the members form a choice.
2. **Rename** — when they are genuinely independent flags with bad names.

An enum would be wrong here: `rating-hidden` and `rating-half` have nothing to do with each other.
This is the mirror of the name test — there, failing to name the *group* meant the group was
wrong; here, failing to name the *flag* means only the name was.

### Where the names live

`parameterNames` in `codegen-config.json`, a third editorial section beside `docSummaries` and
`enumNames`. Decided by Oliver over the alternative of folding all three into one `naming` section
per component: `docSummaries` already ships, so consolidating means migrating committed config for
a tidiness gain.

All three are policed by the consumption guard, so a rename for a class DaisyUI has removed fails
the run rather than rotting.

**Not derivable, and that is the point.** `toCamelCase` of `rating-hidden` gives `hidden`, which is
exactly the wrong answer — only a human reading the CSS knows it means the clear option.

## The two-axis placements, read from the CSS — 2026-09-13

Four groups were flagged as "two axes filed under one category". Reading the CSS corrects one of
them and shows the other three are **two different patterns**, not one.

| component | axis 1 | axis 2 | evidence |
|---|---|---|---|
| `indicator` | `IndicatorVertical` top/middle/bottom | `IndicatorHorizontal` start/center/end | sets `--indicator-t/-b/-y` vs `--indicator-s/-e/-x` |
| `toast` | `ToastVertical` top/middle/bottom | `ToastHorizontal` start/center/end | sets `--toast-y` vs `--toast-x` |
| `dropdown` | `DropdownSide` top/bottom/left/right | `DropdownAlign` start/center/end | sides set `--anchor-v`/`--anchor-h`; alignment sets only `--anchor-h` |
| `tooltip` | `TooltipSide` top/bottom/left/right | `TooltipAlign` start/center/end | sides set `transform`; alignment sets `--tt-inset`, `--tt-trans` |

`indicator` and `toast` are genuine X/Y coordinates. `dropdown` and `tooltip` follow the popover
convention — **which side**, then **alignment along that side** — the same split Floating UI calls
`placement = side + alignment`. Different enough that one shared rule would be wrong.

### `modal` is NOT two axes — my flag was wrong

```
modal-top/middle/bottom   place-items-start/center/end  + w-full / w-11/12
modal-start/end           place-items-start/end         + h-screen, w-auto
```

All five set `place-items` **and** the box's dimensions, so they conflict with each other:
`modal-top modal-start` leaves the last `place-items` winning and the sizing contradictory. They
are five mutually exclusive anchor positions — vertical sheets and side sheets in one axis.

One enum, `ModalPosition`. Recorded because the earlier flag came from the pattern of the member
names rather than from what they do, which is the mistake this section exists to avoid.

## Probe: is exclusivity derivable from the CSS? — refuted 2026-09-13

Hypothesis: two classes conflict iff they write the same declaration, so grouping a category's
members by that relation yields its axes mechanically, and both the category rule and most of
`enumNames` become derivable. Measured over all 41 multi-member groups
(`tmp/probe-css-overlap.ts`): 25 one-axis, 11 multi-axis, 5 all-boolean.

**It confirmed the three cases I was least sure of, including my own error:**

```
Indicator.placements  2-AXES  [start center end] [top middle bottom]
Toast.placements      2-AXES  [start center end] [top middle bottom]
Modal.placements      ONE-AXIS  [top middle bottom start end]
```

**And then it got enough wrong that it cannot run unattended.** Two failure modes:

- **False merge.** `Tooltip.placements` and `Dropdown.placements` come back as ONE-AXIS with all
  seven members, because the sides and the alignments both touch `--anchor-h`. `Table.modifiers`
  merges `zebra pin-cols pin-rows`, which demonstrably combine.
- **False split.** `Button.styles` splits `link` off from `outline dash soft ghost`, `Mask.styles`
  splits `square` off from the other fourteen shapes, `Tab.styles` splits `border` from
  `box lift`. In each case one member of a real choice is simply implemented differently.

Shared declarations are evidence of *implementation*, and exclusivity is a statement about
*meaning*. They correlate — which is why the placements came out right — but not closely enough
to generate an API from.

**Kept as a check rather than a source.** Where the heuristic and the hand classification
disagree, one of them is wrong and it is worth knowing which. It already disagrees with three
hand names: `Card.modifiers`, `List.modifiers` and `Swap.styles` come back ALL-BOOLEAN against
`CardLayout`, `ListColumn` and `SwapAnimation`. Those three need re-reading.

### What the config actually costs today

| | entries |
|---|---|
| `docSummaries` | 66 |
| the other eleven sections together | 36 |

102 entries, and two thirds of them are the one section that is editorial by decision. The file
is **not** a large legacy artefact — the consumption guard has kept it honest.

That reframes the naming question: 28 `enumNames` entries would nearly **double** the
non-editorial config. The argument against them is not that exclusivity is derivable — the probe
just showed it is not — but that the *name* can default to the category and be overridden only
where a human word is clearly better.

## Probe 2: the examples veto what the CSS gets wrong — 2026-09-13

Example co-occurrence is **asymmetric** evidence, and the asymmetry is what makes it useful:

```
observed together  ->  certainly NOT mutually exclusive   (a fact)
never together     ->  weak evidence of exclusivity       (could be unexemplified)
```

It can never establish an axis. It can only refute one. Measured over all 44 multi-member
groups (`tmp/probe-cooccurrence.ts`): **6 groups carry evidence, 38 are silent.**

**All four two-axis placements come out exactly right, and the structure is explicit:**

```
Indicator   {start center end} × {top middle bottom}   9 pairs, no pair within an axis
Toast       {start center end} × {top middle}          6 pairs
Dropdown    {center end}       × {top bottom left right}
Tooltip     {top bottom left right} × {start end}
```

Never `start+center`, never `top+middle` — the co-occurrence graph is complete-multipartite, and
its colour classes *are* the axes. `Modal.placements` shows no co-occurrence at all, consistent
with the one axis the CSS also reported.

**Every false merge the CSS probe produced is refuted here.** `Dropdown` and `Tooltip` were the
two worst CSS errors — sides and alignments collapsed into one seven-member axis — and the
examples separate them cleanly. `Table.modifiers` is refuted by `pin-rows+pin-cols`,
`Avatar.modifiers` by `online+placeholder`. Three for three on the merges.

The false *splits* — `link` off `Button.styles`, `square` off `Mask.styles`, `border` off
`Tab.styles` — are not contradicted by any example, so the default "same category, never observed
together" gives the right answer for all three.

### The two sources are complementary, and neither is sufficient

| | CSS declarations | documented examples |
|---|---|---|
| covers | every group | 6 of 44 |
| establishes an axis | unreliably | never |
| refutes an axis | no | authoritatively |
| fails by | false merges *and* false splits | silence |

Where the examples speak they are authoritative; where they are silent the CSS still carries a
signal. `dropdown-start` is the case that needs both: no example pairs it with anything, and only
the shared `--anchor-h` puts it with `center` and `end`.

So the answer to "can this be decided deterministically" is **no, not from either source alone,
and not from both for 38 of 44 groups** — but the four splits that would otherwise have been
hand-configured are now derived, and the two heuristics disagreeing is a signal worth failing on.

## Reading `button.css` settles the muster case — and both probes were wrong

All five members write the same two variables:

```
.btn-outline, .btn-dash   --btn-bg: #0000   --btn-border: var(--btn-color)   solid
.btn-dash                                   --btn-border-style: dashed
.btn-ghost                --btn-bg: #0000   --btn-border: #0000
.btn-soft                 --btn-bg: 8% mix  --btn-border: 10% mix           solid
.btn-link                 --btn-bg: #0000   --btn-border: #0000   + underline
```

Combining two does not compose, it overrides: `btn-outline btn-ghost` sets
`--btn-border: var(--btn-color)` and then `#0000`, and ghost is later in the stylesheet, so the
outline does nothing. **`ButtonEmphasis` stands, with all five members.**

Both probes said otherwise and both were wrong, in instructive ways:

- The CSS probe split `link` off the other four. They demonstrably share `--btn-bg`,
  `--btn-border`, `background-image`, `--btn-inset` and `--btn-shadow`, so that is an
  implementation defect in the probe, not a finding.
- The template corpus showed `outline+ghost` used together. The CSS shows that combination is
  inert. **Production markup contains mistakes; curated documentation mostly does not**, and a
  single observation from the noisier source is not evidence.

### `card`, `list` and `swap` stay boolean — because nothing established otherwise

`list-col-wrap` sets `row-start-2` while `list-col-grow` sizes a column; `swap-rotate` applies
rotations while `swap-flip` sets `transform-style` and `perspective`; `card-side` sets
`flex-direction: row` while `card-image-full` repositions the figure. No shared declaration, no
observed co-occurrence — **no source settles them either way.** They are three of the 36 groups
that stay silent.

So `CardLayout`, `ListColumn` and `SwapAnimation` are withdrawn.

### The rule this produces: the cost of being wrong is asymmetric

| wrong as | consequence |
|---|---|
| **enum** | a combination DaisyUI permits **cannot be expressed** |
| **boolean** | a combination that does nothing **can be expressed** |

The first defeats the library's purpose, which is to reach the CSS DaisyUI ships. The second
merely fails to prevent a harmless mistake — and the untyped `extraClasses` escape hatch permits
it anyway.

**So: an enum requires exclusivity to be positively established — a shared declaration the
members overwrite, with no example contradicting it. Everything else stays boolean.** That
replaces the earlier category-based default, which had `styles`, `directions` and `placements`
becoming enums merely by living in those categories.

## Non-Goals

**Typing Tailwind utilities.** 35 of 47 tokens, an unbounded set maintained by another project.

**Fixing the 19 unreached classes wholesale.** Task 1.3 triages them, and most are defects that
belong to their own issues: eight `step-*` are a missing part, `tab-active` and `tab-disabled`
belong to #342, four are #345's non-classes. Only `join-item`, `indicator-item`, `avatar-group`,
`floating-label` and `list-row` are plausibly this change's business, and 1.3 decides that on
evidence rather than here.

## Risks / Trade-offs

**A typed variant API that is more awkward than the string it replaces would be worse than
nothing.** The whole value is that `size = ButtonSize.Lg at Breakpoint.Lg` reads at least as well
as `extraClasses = "lg:btn-lg"`. Task 2.1 has that as its explicit constraint, and if no shape
clears it, the honest outcome is to abandon the change rather than ship ceremony.

**The variant set may not be closeable.** Tailwind's variants include `group-hover`,
`peer-checked` and arbitrary `data-[…]` forms. Task 1.2 measures whether DaisyUI's own examples
stay inside a closed set. If they do not, the type needs an escape hatch — and a typed API whose
escape hatch is a string is only worth having if the typed part covers the common case.

**Both API baselines move.** Additive, so nothing breaks, but `lib/api/lib.api` and
`lib/api/components.api` both need reading and re-dumping. The second is new since
`verify-generator-assertions` and will show parameter-type changes the first cannot.
