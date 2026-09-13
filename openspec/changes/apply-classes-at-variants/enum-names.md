# Enum names — the editorial decision block 3 needs

Everything mechanical is already decided: `codegen/exclusivity.json` says which groups are a
single choice, and `classifyGroups` refuses a name that contradicts it. What no measurement can
supply is **what question each enum answers**, and that is this file.

Fill in the `Name` column. Anything left blank fails the generation run with a message naming
the group — deliberately, because a measured choice nobody named would otherwise let a caller
set two contradictory answers at once.

The proposals are proposals. Overwrite them freely.

## The naming rule

**Stay close to DaisyUI, so a reader can find the documentation.** The enum type is named after
the component and DaisyUI's own category for the group — `LoadingStyle` for the `style` entries
of `loading`, `TabPlacement` for the `placement` entries of `tab`. A reader who meets
`LoadingStyle.Spinner` can open DaisyUI's Loading page, find the Style table, and read the row
for `loading-spinner`.

This overrides the name-by-intention rule an earlier draft used. `LoadingAnimation` reads better
in isolation and costs the reader the trail back to the source; DaisyUI is the authority here and
the vocabulary should say so.

**The members already trace directly**: each is the class suffix in PascalCase, so
`MaskStyle.Squircle` is `mask-squircle`, `TabPlacement.Bottom` is `tab-bottom`. Nothing to
decide there.

Documentation for a component lives at `https://daisyui.com/components/<directory>/` and in the
submodule at `daisyui/packages/docs/src/routes/(routes)/components/<directory>/+page.md`. The
Group column below is `<directory>.<category>`, so both paths follow from it.

Where the DaisyUI-close name is genuinely poor, the **Alternative** column holds the
intention-named version. Pick either; the column exists so the trade is visible rather than
argued.

---

## 1. Single choices — 15 groups, one enum each

Every pair in these was measured `exclusive`.

The class column shows the SUFFIX; the real class is `<component>-<suffix>`, so row 1 is
`alert-vertical` and `alert-horizontal`.

| # | Group | DaisyUI classes | Proposed name | Alternative | Question it answers |
|---|---|---|---|---|---|
| 1 | `alert.directions` | `vertical` `horizontal` | `AlertDirection` | `AlertOrientation` | which way the alert lays out its content |
| 2 | `card.styles` | `border` `dash` | `CardStyle` | `CardBorder` | which border the card draws |
| 3 | `card.modifiers` | `side` `image-full` | `CardModifier` | `CardLayout` | how the card arranges its image against its body |
| 4 | `carousel.modifiers` | `start` `center` `end` | `CarouselModifier` | `CarouselSnap` | where an item comes to rest when it snaps |
| 5 | `divider.directions` | `vertical` `horizontal` | `DividerDirection` | `DividerOrientation` | which way the divider runs |
| 6 | `join.directions` | `vertical` `horizontal` | `JoinDirection` | `JoinOrientation` | which way the joined items are stacked |
| 7 | `list.modifiers` | `col-wrap` `col-grow` | `ListModifier` | `ListColumn` | which column absorbs the spare width |
| 8 | `loading.styles` | `spinner` `dots` `ring` `ball` `bars` `infinity` | `LoadingStyle` | `LoadingAnimation` | which animation is shown |
| 9 | `mask.styles` | `squircle` `heart` `hexagon` `hexagon-2` `decagon` `pentagon` `diamond` `square` `circle` `star` `star-2` `triangle` `triangle-2` `triangle-3` `triangle-4` | `MaskStyle` | `MaskShape` | what shape the content is masked to |
| 10 | `mask.modifiers` | `half-1` `half-2` | `MaskModifier` | `MaskHalf` | which half of the shape is kept |
| 11 | `menu.directions` | `vertical` `horizontal` | `MenuDirection` | `MenuOrientation` | which way the menu runs |
| 12 | `pagination.directions` | `vertical` `horizontal` | `PaginationDirection` | `PaginationOrientation` | which way the pages are laid out |
| 13 | `stat.directions` | `horizontal` `vertical` | `StatDirection` | `StatOrientation` | which way the stats are laid out |
| 14 | `steps.directions` | `vertical` `horizontal` | `StepsDirection` | `StepsOrientation` | which way the steps progress |
| 15 | `tab.placements` | `top` `bottom` | `TabPlacement` | `TabPosition` | which edge the tabs sit on |

**Two of these were withdrawn earlier and came back.** Rows 3 and 7 were dropped when no source
settled them; the browser calls both pairs exclusive.

### One risk the `…Modifier` names carry — rows 3, 4, 7, 10

`modifier` is DaisyUI's catch-all category, and these four groups happen to hold ALL of their
component's modifiers today. `CardModifier` is therefore accurate right now and would start
lying the moment DaisyUI adds a card modifier that composes: some modifiers would be enum
constants and others booleans, under a name claiming to cover the category.

`direction`, `style` and `placement` do not have this problem to the same degree — they are
narrower categories, and `verifyExclusivity` fails the build when any of them gains a member,
so the lie cannot land silently either way.

If that risk bothers you, the Alternative column for those four rows is the answer: those names
describe the group rather than the category, so a new member cannot make them false.

Row 9 is the one I would take from the Alternative column regardless — `MaskShape` for fifteen
shapes is both closer to what the caller is doing AND unambiguous in the docs, since DaisyUI's
own Mask page is a grid of shapes.

---

## 2. Two-axis groups — 3 of them work, 1 does not

These hold two independent questions. One enum would force the caller to answer only one.

### 2a. `indicator.placements` and `toast.placements` — identical shape

Members: `start` `center` `end` `top` `middle` `bottom`.
Measured: `{start,center,end}` is a clique, `{top,middle,bottom}` is a clique, every pair
across them composes.

Both axes are `placement` in DaisyUI, so the category name alone cannot separate them and a
qualifier is unavoidable. Keeping `Placement` in both keeps the trail to the docs.

| Axis | Members | Proposed name | Alternative |
|---|---|---|---|
| vertical | `top` `middle` `bottom` | `IndicatorVerticalPlacement` / `ToastVerticalPlacement` | `IndicatorVertical` / `ToastVertical` |
| horizontal | `start` `center` `end` | `IndicatorHorizontalPlacement` / `ToastHorizontalPlacement` | `IndicatorHorizontal` / `ToastHorizontal` |

The proposed names are long. The trade is real: `IndicatorVerticalPlacement.Top` says exactly
which DaisyUI table to open, `IndicatorVertical.Top` reads better and says slightly less. Your
call — the call site is `daisyIndicator(verticalPlacement = …)` either way.

### 2b. `tooltip.placements`

Members: `top` `bottom` `left` `right` `start` `center` `end`.
Measured: `{top,bottom,left,right}` is a clique, `{start,center,end}` is a clique. `top` is
exclusive against the alignment members too, which is why the split cannot be derived and has
to be declared.

| Axis | Members | Proposed name | Alternative |
|---|---|---|---|
| side | `top` `bottom` `left` `right` | `TooltipSidePlacement` | `TooltipSide` |
| alignment | `start` `center` `end` | `TooltipAlignPlacement` | `TooltipAlign` |

Same trade as Indicator and Toast — and here the short forms are unusually good, because
DaisyUI's own Tooltip page already calls these "placement" and the members read as sides and
alignments without help.

### 2c. `dropdown.placements` — BLOCKED, needs your decision

Members: `start` `center` `end` `top` `bottom` `left` `right`.

The obvious split is the same as Tooltip's — side against alignment. **The measurement refuses
it.** The only exclusive pairs are:

```
start|center   start|end   center|end   top|bottom
```

`left` and `right` compose with everything, including each other and including `top`/`bottom`.
So `{top,bottom,left,right}` is not a clique, and `checkAxisIsExclusive` rejects it — correctly,
because declaring it would make `dropdown-left dropdown-top` inexpressible when the browser says
that combination reaches CSS neither class reaches alone.

Three ways out. Please pick one:

| | Option | Cost |
|---|---|---|
| **A** | Leave the whole group boolean | Seven booleans; `start`/`center`/`end` lose an enum they earned |
| **B** | One enum `DropdownAlignPlacement` (or `DropdownAlign`) = `{start,center,end}`, and `top` `bottom` `left` `right` stay boolean | Needs `checkSplitCoverage` relaxed to allow unassigned members — which currently exists to stop a new DaisyUI class silently becoming a boolean |
| **C** | Declare the side axis anyway, overriding the measurement for this group | Makes a reachable combination inexpressible. Contradicts the asymmetric-cost rule the whole change rests on |

**A is the honest default and B is probably what you want**, but B changes a guard, so it is
not mine to decide. C is listed for completeness; I would argue against it.

Worth knowing before you choose: the co-occurrence probe observed `dropdown-center`/`dropdown-end`
together with `top`/`bottom`/`left`/`right` in DaisyUI's own docs, which is what established that
the group has two axes at all. It says nothing about whether `left` and `top` conflict.

---

## 3. What happens to the rest

The other 25 measured groups stay boolean, including several that had names in `design.md`:
`AlertEmphasis`, `BadgeEmphasis`, `ButtonEmphasis`, `ButtonLayout`, `AuraEffect`, `ChatSide`,
`ModalPosition`, `CarouselOrientation`, `FooterOrientation`, `TimelineOrientation`,
`DividerAlignment`, `MegamenuWidth`, `CollapseIndicator`, `CollapseState`, `DropdownState`.

Nothing to decide there — the measurement settled it. `design.md` carries the reasoning under
"Probe 4c".

---

## 4. Five boolean parameters also get renamed

Decided earlier, listed here so block 3 has one place to read from. These are NOT enums; they
are booleans whose names do not say what `true` means.

| Component | Class | Current parameter | New name |
|---|---|---|---|
| `rating` | `rating-hidden` | `hidden` | `clearOption` |
| `dropdown` | `dropdown-hover` | `hover` | `openOnHover` |
| `rating` | `rating-half` | `half` | `halfStars` |
| `menu` | `menu-focus` | `focus` | `focused` |
| `timeline` | `timeline-box` | `box` | `boxed` |
