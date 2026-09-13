# Enum names — the editorial decision block 3 needs

Everything mechanical is already decided: `codegen/exclusivity.json` says which groups are a
single choice, and `classifyGroups` refuses a name that contradicts it. What no measurement can
supply is **what question each enum answers**, and that is this file.

Fill in the `Name` column. Anything left blank fails the generation run with a message naming
the group — deliberately, because a measured choice nobody named would otherwise let a caller
set two contradictory answers at once.

The proposals are proposals. Overwrite them freely.

## The naming rule this repo already uses

**Name by intention, never by implementation.** `LoadingAnimation` says what the caller is
choosing; `LoadingStyle` just repeats DaisyUI's category name and answers nothing. If a name
cannot be found, that is evidence the group is not really one question — say so and it stays
boolean.

---

## 1. Single choices — 15 groups, one enum each

Every pair in these was measured `exclusive`.

| # | Group | DaisyUI classes | Proposed name | Question it answers |
|---|---|---|---|---|
| 1 | `alert.directions` | `vertical` `horizontal` | `AlertOrientation` | which way the alert lays out its content |
| 2 | `card.styles` | `border` `dash` | `CardBorder` | which border the card draws |
| 3 | `card.modifiers` | `side` `image-full` | `CardLayout` | how the card arranges its image against its body |
| 4 | `carousel.modifiers` | `start` `center` `end` | `CarouselSnap` | where an item comes to rest when it snaps |
| 5 | `divider.directions` | `vertical` `horizontal` | `DividerOrientation` | which way the divider runs |
| 6 | `join.directions` | `vertical` `horizontal` | `JoinOrientation` | which way the joined items are stacked |
| 7 | `list.modifiers` | `col-wrap` `col-grow` | `ListColumn` | which column absorbs the spare width |
| 8 | `loading.styles` | `spinner` `dots` `ring` `ball` `bars` `infinity` | `LoadingAnimation` | which animation is shown |
| 9 | `mask.styles` | `squircle` `heart` `hexagon` `hexagon-2` `decagon` `pentagon` `diamond` `square` `circle` `star` `star-2` `triangle` `triangle-2` `triangle-3` `triangle-4` | `MaskShape` | what shape the content is masked to |
| 10 | `mask.modifiers` | `half-1` `half-2` | `MaskHalf` | which half of the shape is kept |
| 11 | `menu.directions` | `vertical` `horizontal` | `MenuOrientation` | which way the menu runs |
| 12 | `pagination.directions` | `vertical` `horizontal` | `PaginationOrientation` | which way the pages are laid out |
| 13 | `stat.directions` | `horizontal` `vertical` | `StatOrientation` | which way the stats are laid out |
| 14 | `steps.directions` | `vertical` `horizontal` | `StepsOrientation` | which way the steps progress |
| 15 | `tab.placements` | `top` `bottom` | `TabPosition` | which edge the tabs sit on |

**Two of these were withdrawn earlier and came back.** `CardLayout` (3) and `ListColumn` (7)
were dropped when no source settled them; the browser calls both pairs exclusive.

**Six `Orientation`s is not a mistake** — they are six distinct types and each is only ever
reachable through its own component. Say so if you would rather they were named apart.

---

## 2. Two-axis groups — 3 of them work, 1 does not

These hold two independent questions. One enum would force the caller to answer only one.

### 2a. `indicator.placements` and `toast.placements` — identical shape

Members: `start` `center` `end` `top` `middle` `bottom`.
Measured: `{start,center,end}` is a clique, `{top,middle,bottom}` is a clique, every pair
across them composes.

| Axis | Members | Proposed name | Alternatives |
|---|---|---|---|
| vertical | `top` `middle` `bottom` | `IndicatorVertical` / `ToastVertical` | `…VerticalEdge`, `…Y`, `…Row` |
| horizontal | `start` `center` `end` | `IndicatorHorizontal` / `ToastHorizontal` | `…HorizontalEdge`, `…X`, `…Column` |

`IndicatorVertical.Top` reads a little oddly as a type name. Pick whichever of the
alternatives reads best at the call site — that is the only criterion that matters here.

### 2b. `tooltip.placements`

Members: `top` `bottom` `left` `right` `start` `center` `end`.
Measured: `{top,bottom,left,right}` is a clique, `{start,center,end}` is a clique. `top` is
exclusive against the alignment members too, which is why the split cannot be derived and has
to be declared.

| Axis | Members | Proposed name |
|---|---|---|
| side | `top` `bottom` `left` `right` | `TooltipSide` |
| alignment | `start` `center` `end` | `TooltipAlign` |

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
| **B** | One enum `DropdownAlign` = `{start,center,end}`, and `top` `bottom` `left` `right` stay boolean | Needs `checkSplitCoverage` relaxed to allow unassigned members — which currently exists to stop a new DaisyUI class silently becoming a boolean |
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
