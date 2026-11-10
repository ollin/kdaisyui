# Upgrading

Every release that breaks the API says here what to change. Read the sections from the version
you have up to the one you move to; the [release notes](https://github.com/ollin/kdaisyui/releases)
list everything else.

## How to migrate from 0.1.x

**1. `TooltipVariant.Neutral` was removed.** DaisyUI dropped the `tooltip-neutral` class, so
the enum entry went with it. This is a compile error, not a silent change:

<!-- snippet: skip - a migration note from 0.1.x, written against the API of its release -->
```kotlin
// before
daisyTooltip("Copy to clipboard", variant = TooltipVariant.Neutral) { … }

// after — pick another variant, or omit it for the default styling
daisyTooltip("Copy to clipboard", variant = TooltipVariant.Primary) { … }
daisyTooltip("Copy to clipboard") { … }
```

**2. Switch positional arguments to named ones.** This one *is* silent, and it is the reason
to act even if you never used `Neutral`.

Generated parameters are sorted alphabetically, so a DaisyUI release that adds a modifier
inserts it into the *middle* of an existing signature. In 0.2.0, `center` and `end` landed
between `bottom` and `left` on `daisyTooltip`:

<!-- snippet: skip - a migration note from 0.1.x, written against the API of its release -->
```kotlin
// 0.1.x — third boolean was `left`
daisyTooltip("Hint", null, false, true) { … }   // meant left = true

// 0.2.0 — the same call now means center = true
```

Every modifier is `Boolean = false`, so the compiler cannot notice. Named arguments are
immune, and they are the only call style this project can keep stable across DaisyUI releases:

<!-- snippet: skip - a migration note from 0.1.x, written against the API of its release -->
```kotlin
daisyTooltip("Hint", left = true) { … }
```

## How to migrate from 0.3.x

**`daisyMegamenuActive` now renders a `<span>`, not a `<div>`.** Its `attrs` and `content`
lambdas therefore receive `SPAN` instead of `DIV`:

<!-- snippet: skip - a migration note from 0.3.x, written against the API of its release -->
```kotlin
// before
daisyMegamenuActive(attrs = { /* this: DIV */ }) { /* this: DIV */ }

// after
daisyMegamenuActive(attrs = { /* this: SPAN */ }) { /* this: SPAN */ }
```

Most call sites need no edit — an empty body, `+"text"`, or anything reached through
`attributes[…]` compiles unchanged. Only code naming `DIV` explicitly, or calling a
`DIV`-specific member, breaks, and it breaks at compile time.

Why it changed: DaisyUI documents this element as a `<span>` and calls it mandatory.
`megamenu.css` picks the open panel with `[popover]:nth-of-type(N)`, and `:nth-of-type` counts
among siblings of the *same tag*. The panels are `<div popover>`, so a `<div>` indicator took
div index 1 and shifted every panel by one — the indicator slid under the trigger *after* the
one whose panel was open.

Note that `lib/api/lib.api` does not show this change: both lambda types erase to
`Function1`, so the ABI dump — and the `api-baseline` CI gate that reads it — cannot see a
changed lambda receiver. That gate catches removals and arity changes, not this.

**Since 0.5.0 there is a second baseline that does see it** — `lib/api/components.api`, checked
in CI. This paragraph is kept as written because it was true when 0.4.0 shipped, and because
that blind spot is exactly what the new baseline was built for.

## How to migrate from 0.4.x

Four functions change, and three of the four changes break at compile time. The fourth is the
dangerous one.

**1. `daisyFileInput`, `daisyThemeController` and `daisyMask` no longer take a `content`
lambda.** All three render a void element — two `<input>`, one `<img>` — and HTML forbids
children there, so anything that lambda wrote could never legally render:

<!-- snippet: skip - a migration note from 0.4.x, written against the API of its release -->
```kotlin
// before
daisyMask(circle = true) { }
daisyFileInput(size = FileInputSize.Sm) { }

// after
daisyMask(circle = true)
daisyFileInput(size = FileInputSize.Sm)
```

Drop the trailing `{ }`. This breaks at compile time and `lib/api/lib.api` shows it, because
the arity changes.

**2. `daisyOtp` now renders a `<label>`, not a `<div>`.** Its `attrs` and `content` lambdas
receive `LABEL` instead of `DIV`:

<!-- snippet: skip - a migration note from 0.4.x, written against the API of its release -->
```kotlin
// before
daisyOtp(joined = true, attrs = { /* this: DIV */ }) { /* this: DIV */ }

// after
daisyOtp(joined = true, attrs = { /* this: LABEL */ }) { /* this: LABEL */ }
```

Why it changed: DaisyUI documents the component as `<label class="otp">`, and that is what
makes a click focus the input the component wraps. As a `<div>` it did not.

**This one may compile silently.** Most call sites need no edit — an empty body, `+"text"`,
nested `span { }`, or anything reached through `attributes[…]` compiles unchanged, because both
`DIV` and `LABEL` are `FlowContent`. The example app in this repository is one such site and
needed no change at all. Only code naming `DIV` explicitly, or calling a `DIV`-specific member,
breaks.

So read this entry rather than relying on the compiler: a call site that keeps compiling is now
emitting a `<label>` where it emitted a `<div>`, which changes the rendered HTML and can change
CSS that selected on the tag.

`lib/api/lib.api` does not show this change either, for the reason given above. `lib/api/components.api`
does, and shipped in this release for that reason.

## How to migrate from 0.5.x

The largest API change so far, and — unlike 0.4.x — **every part of it breaks at compile time**.
A boolean that became an enum entry is gone as a parameter, so the compiler names each call site.

**1. Mutually exclusive booleans became one enum parameter.** Where a browser confirmed that two
of a component's classes cannot both take effect, the booleans were replaced by a single
parameter:

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before — nothing stopped you asking for both
daisyMenu(vertical = true, horizontal = true) { … }

// after — the question has one answer
daisyMenu(direction = MenuDirection.Vertical) { … }
```

The enum entry is the old parameter name in PascalCase, so the replacement is mechanical:

| Component | New parameter | Enum | Replaces the booleans |
|---|---|---|---|
| Alert | `direction` | `AlertDirection` | `vertical`, `horizontal` |
| Card | `style` | `CardStyle` | `border`, `dash` |
| Card | `modifier` | `CardModifier` | `side`, `imageFull` |
| Carousel | `modifier` | `CarouselModifier` | `start`, `center`, `end` |
| Divider | `direction` | `DividerDirection` | `vertical`, `horizontal` |
| Join | `direction` | `JoinDirection` | `vertical`, `horizontal` |
| List | `modifier` | `ListModifier` | `colWrap`, `colGrow` |
| Loading | `style` | `LoadingStyle` | `spinner`, `dots`, `ring`, `ball`, `bars`, `infinity` |
| Mask | `style` | `MaskStyle` | `squircle`, `heart`, `hexagon`, … 15 in total |
| Mask | `modifier` | `MaskModifier` | `half1`, `half2` |
| Menu | `direction` | `MenuDirection` | `vertical`, `horizontal` |
| Stat | `direction` | `StatDirection` | `horizontal`, `vertical` |
| Steps | `direction` | `StepsDirection` | `vertical`, `horizontal` |
| Tab | `placement` | `TabPlacement` | `top`, `bottom` |
| Alert | `style` | `AlertStyle` | `outline`, `dash` — `soft` stays a boolean |
| Avatar | `modifier` | `AvatarModifier` | `online`, `offline` — `placeholder` stays |
| Badge | `outlineStyle` | `BadgeOutlineStyle` | `outline`, `dash` |
| Badge | `fillStyle` | `BadgeFillStyle` | `soft`, `ghost` |
| Dropdown | `modifier` | `DropdownModifier` | `hover`, `open` — `close` stays |
| Dropdown | `verticalPlacement` | `DropdownVerticalPlacement` | `top`, `bottom` — `left`, `right` stay |

The last five rows are cliques *beside* booleans: `alert-outline` and `alert-dash` exclude each
other, `alert-soft` composes with both. A clique of two or more is an enum wherever it sits;
the booleans around it stay booleans.

**2. Four placement groups split into two independent axes**, because their classes compose
*across* the split even though each axis is a single choice:

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before
daisyTooltip("Copy", top = true, start = true) { … }

// after — two questions, two answers
daisyTooltip("Copy", sidePlacement = TooltipSidePlacement.Top,
                     alignPlacement = TooltipAlignPlacement.Start) { … }
```

| Component | Axes | Replaces |
|---|---|---|
| Indicator | `horizontalPlacement` (`Start`/`Center`/`End`), `verticalPlacement` (`Top`/`Middle`/`Bottom`) | six booleans |
| Toast | `horizontalPlacement`, `verticalPlacement` — same entries | six booleans |
| Tooltip | `sidePlacement` (`Top`/`Bottom`/`Left`/`Right`), `alignPlacement` (`Start`/`Center`/`End`) | seven booleans |
| Dropdown | `horizontalPlacement` (`Start`/`Center`/`End`), `verticalPlacement` (`Top`/`Bottom`) | five booleans — `left`, `right` stay |

The axis names are DaisyUI's where it has them: its reference table describes `--indicator-y`
as "vertical position of the indicator" and `--anchor-h` as "horizontal position of the
anchor", so those axes are `Vertical`/`Horizontal`. The tooltip's variables carry no direction
— its alignment is relative to the side — so `Side`/`Align` are ours.

`daisyDropdown` keeps `left` and `right` as booleans because the browser says `dropdown-left
dropdown-top` reaches CSS that neither class reaches alone; `top`/`bottom` exclude each other
and are an axis.

That asymmetry is the whole rule. A group became an enum only where *every* pair in it was
measured mutually exclusive; where the measurement was unsure, the classes stayed booleans. A
wrong enum takes away something DaisyUI permits; a wrong boolean only permits something useless.

**3. Enum parameters are typed `ClassValues<T>`, not `T`.** No call site changes:
`size = ButtonSize.Lg` compiles exactly as before, because the enum *is* a `ClassValues<ButtonSize>`.
The wider type exists so one parameter can later also accept a class applied at a Tailwind
breakpoint. That composition is built but **not yet public** — see `docs/explanation.md`; until it
is, apply a variant as a literal through `extraClasses`, which is the form Tailwind can see.

Only code that names the type explicitly is affected:

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before
val size: ButtonSize? = if (compact) ButtonSize.Sm else null

// after — or just drop the annotation and let it infer
val size: ClassValues<ButtonSize>? = if (compact) ButtonSize.Sm else null
```

**4. Use named arguments.** The advice from the 0.1.x entry now matters more: removing booleans
and inserting enum parameters shifts every positional argument on 37 of the 66 components. Named
arguments are immune.

Both baselines show this change in full — the parameters are removed and added, not merely
retyped, so neither blind spot from 0.4.x applies.

**5. A class is now declared on the function whose element wears it.** DaisyUI's own markup says
which element carries each class, and where the generator had put a parameter on the container
while DaisyUI puts the class on a child, the parameter moved. The class had been reaching the
page and styling nothing.

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before — the placement classes sat on the container, where they do nothing
daisyIndicator(verticalPlacement = IndicatorVerticalPlacement.Top) { daisyIndicatorItem { … } }

// after — they are declared on the item, which is what DaisyUI shows them on
daisyIndicator { daisyIndicatorItem(verticalPlacement = IndicatorVerticalPlacement.Top) { … } }
```

`timeline-box` moved the same way, from `daisyTimeline` to the parts that wear it.

**6. Several functions render a different element, so their lambdas receive a different type.**
Sixteen parts now render the element DaisyUI documents rather than one guessed from their name,
and `daisyStatus` renders `<div>` — measured, because DaisyUI shows `status` on a `<div>` 16
times and a `<span>` once, and all thirteen of its variant classes on `<div>` alone.

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before
daisyStatus(attrs = { /* this: SPAN */ }) { /* this: SPAN */ }
// after
daisyStatus(attrs = { /* this: DIV */ })  { /* this: DIV */ }
```

Most call sites need no edit: an empty body, `+"text"`, or anything reached through
`attributes[…]` compiles unchanged. Only code naming the tag type explicitly breaks, and it
breaks at compile time. `lib/api/components.api` shows every one of these; `lib/api/lib.api`
cannot, for the reason given under 0.3.x.

**7. Two parts are now extensions of their parent, not of `FlowContent`.** kotlinx.html opens
`<legend>` only inside `<fieldset>` and `<li>` only inside `<ul>`, so `daisyFieldsetLegend` and
`daisyStepsStep` extend `FIELDSET` and `UL`. Calling them anywhere else no longer compiles —
which is the point: it never rendered valid HTML.

**8. `daisySkeleton(text = true)` is now `daisySkeletonText { }`.** DaisyUI documents
`skeleton-text` in exactly one markup, `<span class="skeleton skeleton-text">`, and the boolean
offered it on a `<div>` instead — for a modifier whose purpose is animating *text* colour.

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before
daisySkeleton(text = true) { +"AI is thinking harder..." }
// after
daisySkeletonText { +"AI is thinking harder..." }
```

**9. `MaskShape` is `MaskStyle`.** The enum is named after DaisyUI's own category word rather
than a hand-written entry. A rename, nothing else.

**10. Three toggles no longer take a `content` lambda.** `daisyDrawerToggle`,
`daisyFilterReset` and `daisyModalToggle` render `<input>`, which the HTML specification says
cannot hold children — so the lambda described children that were never rendered.

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before — the lambda ran, and nothing it wrote appeared
daisyModalToggle { }
// after
daisyModalToggle()
```

**11. `daisyJoin`'s `content` lambda receives a `JoinScope`, not a `DIV`.** Inside it, a
component call DaisyUI documents as a join item marks itself:

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before — join-item was unreachable except as a raw string
daisyJoin {
    daisyButton("One", extraClasses = "join-item")
}

// after — the call is the marking
daisyJoin {
    daisyButton("One")
}
```

**Most call sites need no change at all.** `JoinScope` extends the `<div>` the join already
rendered, so every kotlinx.html builder still opens, `attrs` still receives a `DIV`, and
`this` is still a `DIV` wherever one is wanted. What stops compiling is naming the old type:
a lambda stored in a `DIV.() -> Unit` variable, or a helper declared to take one, has to be
retyped to `JoinScope.() -> Unit`.

An existing `extraClasses = "join-item"` is now redundant but harmless — classes are collected
into a set, so it renders once, not twice.

Two behaviours are worth knowing before you rely on them, both of which follow DaisyUI's own
markup rather than being choices:

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
daisyJoin {
    daisyButton("One")                       // marked
    div {
        daisyButton("Two")                   // NOT marked — the scope is hidden here
        this@daisyJoin.daisyButton("Three")  // marked, named deliberately
    }
}
```

A nested call is not marked because kotlinx.html's `@HtmlTagMarker` is a `@DslMarker`, and
that matches what DaisyUI documents: of 72 direct children of a `.join`, the three that do not
carry `join-item` are wrappers whose own child carries it.

There is no parameter for this and there will not be one. `.join` writes four corner-radius
variables onto its direct children and `.join-item` reads them, so outside a join the class
strips the element's own corners rather than doing nothing — a parameter would be writable
exactly where it does harm. The seven components with a member are derived from DaisyUI's
markup, so a release that documents a new one adds it without a config edit.

**12. `MaskStyle.Square` is gone, because the CSS class behind it does not exist.** DaisyUI
removed `mask-square` from the library **in v5** and only removed it from the documentation in
5.7.19 ([daisyui#4722](https://github.com/saadeghi/daisyui/pull/4722), *"remove mask-square
which was removed from the library in v5"*). kdaisyui generated the constant from those docs,
so it has been emitting a class no stylesheet matches.

<!-- snippet: skip - a migration note from 0.5.x, written against the API of its release -->
```kotlin
// before — compiled, rendered class="mask mask-square", and no rule ever matched it
daisyMask(style = ClassValues.of(MaskStyle.Square)) { src = "/cat.png" }

// after — pick a shape that exists
daisyMask(style = ClassValues.of(MaskStyle.Squircle)) { src = "/cat.png" }
```

Source-breaking, and it replaces a silent failure with a compile error: code using it was
already not getting a square. There is no rename to apply — the shape was withdrawn, not
renamed. `MaskStyle` still offers fourteen shapes, `Squircle` being the closest rounded square.

This is the failure mode the class-based test layers cannot see: the class was in the markup,
every assertion passed, and nothing rendered. Only DaisyUI withdrawing the documentation
surfaced it.

## What deliberately did NOT change

`daisyBadge` still renders `<span>`, although every one of its variant classes is documented on a
`<div>`. DaisyUI picks the tag by context: all seven of its `<span>` examples sit inside an
`<h1>`–`<h5>` or a `<p>`, where a `<div>` is invalid HTML. `<span>` is valid in all 54 documented
positions and `<div>` in 47, so the element that keeps `daisyBadge` usable everywhere wins over
the commoner one. Tracked as #356. `daisyDropdown` keeps `<details>` for the same kind of reason
— the `<div>` shape needs attributes the generator cannot emit — tracked as #357.

## How to migrate from 0.6.x

**1. `daisyDropdown` lost the placement booleans that 0.6.0 should already have removed.** The
0.5.x entry above says `horizontalPlacement` and `verticalPlacement` *replace* five booleans.
In 0.6.0 they did not: `end`, `start`, `center`, `top` and `bottom` were still parameters beside
the enums, so `daisyDropdown(end = true, horizontalPlacement = DropdownHorizontalPlacement.Start)`
compiled and emitted both classes. They are gone now, and it breaks at compile time:

<!-- snippet: skip - a migration note from 0.6.x, written against the API of its release -->
```kotlin
// before — compiled in 0.6.0
daisyDropdown(end = true, top = true) { … }

// after
daisyDropdown(
    horizontalPlacement = DropdownHorizontalPlacement.End,
    verticalPlacement = DropdownVerticalPlacement.Top,
) { … }
```

| Boolean in 0.6.0 | Use instead |
|---|---|
| `start`, `center`, `end` | `horizontalPlacement = DropdownHorizontalPlacement.Start` / `.Center` / `.End` |
| `top`, `bottom` | `verticalPlacement = DropdownVerticalPlacement.Top` / `.Bottom` |
| `left`, `right` | unchanged — they compose with the vertical axis, so they stay booleans |

The five had come from a hand-written configuration block older than the measured enums, and
nothing compared the two. The generator now refuses to run while any function offers one class
both as a boolean and as an enum constant, so this cannot come back for any component.

**2. `kdaisyui-ktor-integration` no longer brings Ktor or any webjar.** Up to 0.6.0 it exported
Ktor Resources, Ktor Webjars and the htmx, Tailwind and DaisyUI webjars as `api` dependencies.
Gradle's conflict resolution picks the highest requested version, so a consumer pinned to htmx 2.x
was moved to htmx 4 — a major version — with no compile error and no warning. The artifact now
depends on `kdaisyui` alone.

Add what you actually use, at the versions you choose:

<!-- snippet: skip - a Gradle build script, not code against the library -->
```kotlin
// build.gradle.kts
implementation("io.ktor:ktor-server-resources")            // needed to compile against the integration
implementation("io.ktor:ktor-server-webjars")              // only if you serve webjars
implementation("org.webjars.npm:htmx.org:YOUR_VERSION")    // only if you relied on the transitive one
implementation("org.webjars.npm:daisyui:DAISYUI_VERSION")  // only for the prebuilt stylesheet
```

**Missing Ktor Resources breaks at compile time**, naming the class. A missing webjar does not: the
page loads and `/webjars/htmx.org/…` or `/webjars/daisyui/…` answers 404. Check what your pages
reference under `/webjars/` before upgrading.

The BOM still aligns only the kdaisyui artifacts. It deliberately carries no constraint for Ktor or
an asset: a Gradle constraint takes part in conflict resolution exactly like a dependency, so it
would re-create the silent upgrade for anyone importing the BOM.

**3. `llms.txt` describes the API that actually ships.** Nothing to change in your code — but if
you pointed an AI tool at `llms.txt`, what it read until now was the **0.1.x** API: badge and
dropdown booleans that 0.6.0 had replaced with enums, a `DIV` receiver for `daisyDropdownContent`
where it takes a `UL`, and no `id` parameter anywhere. Code an assistant wrote from it will not
have compiled against 0.6.0. The file is now generated from the same model as the library and
`docs/reference/`, and CI fails when it drifts, so it is exact for the release it ships with.

## How to migrate from 0.7.x

0.8.0 renames most of the API on purpose. Compatibility with 0.7.x was not a goal: every name now
follows one rule, and class groups that were booleans are now one parameter each. Almost every
change is a compile error at the call site. **One is not** — read the tab section first.

**1. Tabs: `daisyTab` now means the item, not the bar.** A 0.7.x call `daisyTab { }` with no
arguments still compiles and now renders one tab instead of a tab bar.

| 0.7.x | 0.8.0 | Renders |
|---|---|---|
| `daisyTab(...)` | `daisyTabs(...)` | `<div role="tablist" class="tabs">`, no longer a `<button>` |
| `daisyTabTab` | `daisyTab` | `<a role="tab" class="tab">` |
| — | `daisyTabRadio` | `<input type="radio" role="tab" class="tab">`, for DaisyUI's JavaScript-free tab panels |
| `daisyTabTabContent` | `daisyTabContent` | `<div class="tab-content">` |
| `daisyTab(tabActive = true)` | `daisyTab(active = true)` | on the item, emitting `tab-active` |
| `daisyTab(box = true)` | `daisyTabs(style = TabsStyle.Box)` | also `Border`, `Lift` |
| `TabSize`, `TabPlacement` | `TabsSize`, `TabsPlacement` | |

`tabActive` and `tabDisabled` emitted `tabs-tab-active` and `tabs-tab-disabled` in 0.7.x, classes
DaisyUI does not have, so an active tab never looked active.

**2. Every `variant` is now `color`, and its enum `<Name>Color`.**

<!-- snippet: skip - the 0.7.x API, shown for migration -->
```kotlin
// before
daisyButton("Save", variant = ButtonVariant.Primary)
```

```kotlin
// after
daisyButton("Save", color = ButtonColor.Primary)
```

That covers `Alert`, `Badge`, `Button`, `Checkbox`, `Divider`, `FileInput`, `Input`, `Link`, `Otp`,
`Progress`, `Radio`, `Range`, `Select`, `Status`, `Textarea`, `Toggle` and `Tooltip`. Two moved as
well, because DaisyUI puts their classes on a part: `ChatVariant` on `daisyChat` is now
`ChatBubbleColor` on `daisyChatBubble`, and `StepsVariant` on `daisySteps` is now `StepColor` on
`daisyStep`. Their entries lost the doubled prefix: `ChatVariant.BubblePrimary` is
`ChatBubbleColor.Primary`, `StepsVariant.StepPrimary` is `StepColor.Primary`. The step colors also
emitted `steps-step-*` in 0.7.x, which DaisyUI does not have.

**3. Functions are named after the class their element carries.**

| 0.7.x | 0.8.0 |
|---|---|
| `daisyStat` | `daisyStats` |
| `daisyStatStat`, `daisyStatStatTitle`, `…Value`, `…Desc`, `…Figure`, `…Actions` | `daisyStat`, `daisyStatTitle`, `daisyStatValue`, `daisyStatDesc`, `daisyStatFigure`, `daisyStatActions` |
| `StatDirection` | `StatsDirection` |
| `daisyStepsStep`, `daisyStepsStepIcon` | `daisyStep`, `daisyStepIcon` |

**4. Booleans that belonged to a group are now one parameter.** Passing a parameter twice does not
compile, so two members of one group cannot be asked for at once by accident.

| Function | 0.7.x booleans | 0.8.0 parameter |
|---|---|---|
| `daisyButton` | `outline`, `dash`, `soft`, `ghost`, `link` | `style = ButtonStyle.Outline` … |
| `daisyButton` | `square`, `circle` | `shape = ButtonShape.Square` … |
| `daisyButton` | `wide`, `block` | `width = ButtonWidth.Wide` … |
| `daisyAura` | `dual`, `rainbow`, `holo`, `gold`, `silver`, `glow` | `style = AuraStyle.Dual` … |
| `daisySwap` | `rotate`, `flip` | `style = SwapStyle.Rotate` … |
| `daisyCarousel`, `daisyFooter`, `daisyTimeline` | `horizontal`, `vertical` | `direction = CarouselDirection.Horizontal` … |
| `daisyChat`, `daisyDivider` | `start`, `end` | `placement = ChatPlacement.Start` … |
| `daisyStack` | `top`, `bottom`, `start`, `end` | `placement = StackPlacement.Top` … |
| `daisyCollapse` | `arrow`, `plus` | `icon = CollapseIcon.Arrow` … |
| `daisyCollapse` | `open`, `close` | `state = CollapseState.Open` … |
| `daisyModal` | `top`, `middle`, `bottom` | `verticalPlacement = ModalVerticalPlacement.Top` … |
| `daisyModal` | `start`, `end` | `horizontalPlacement = ModalHorizontalPlacement.Start` … |
| `daisyMegamenu` | `wide`, `full` | `width = MegamenuWidth.Wide` … |

The booleans that remain are on/off features, such as `zebra`, `active`, `soft` on the alert, or
`hover` on the dropdown.

**5. Axes are named after what they choose.**

| 0.7.x | 0.8.0 |
|---|---|
| `daisyAvatar(modifier = AvatarModifier.Online)` | `daisyAvatar(presence = AvatarPresence.Online)` |
| `daisyCard(modifier = CardModifier.Side)` | `daisyCard(layout = CardLayout.Side)` |
| `daisyCarousel(modifier = CarouselModifier.Start)` | `daisyCarousel(snap = CarouselSnap.Start)` |
| `daisyList(modifier = ListModifier.ColWrap)` | on the row's child: `daisyListCellClasses(colWrap = true)`, see 9 |
| `daisyMask(modifier = MaskModifier.Half1)` | `daisyMask(half = MaskHalf.Half1)` |
| `daisyTooltip(sidePlacement = …, alignPlacement = …)` | `daisyTooltip(side = TooltipSide.…, align = TooltipAlign.…)` |
| `IndicatorHorizontalPlacement`, `IndicatorVerticalPlacement` | `IndicatorItemHorizontalPlacement`, `IndicatorItemVerticalPlacement` |

`CardLayout.ImageFull` now emits `image-full`, the class DaisyUI defines; 0.7.x emitted
`card-image-full`, which matches no rule.

**6. The dropdown takes the tooltip's shape.** DaisyUI's examples combine one side with at most
one alignment, so that is what the parameters say:

<!-- snippet: skip - the 0.7.x API, shown for migration -->
```kotlin
// before
daisyDropdown(verticalPlacement = DropdownVerticalPlacement.Top, horizontalPlacement = DropdownHorizontalPlacement.End, left = false)
```

```kotlin
// after
daisyDropdown(side = DropdownSide.Top, align = DropdownAlign.End) { /* … */ }
```

`left` and `right` are `DropdownSide.Left` and `.Right`. `DropdownModifier.Open` and the `close`
boolean are `state = DropdownState.Open` and `.Close`; `DropdownModifier.Hover` is the boolean
`hover`.

**7. Twelve combinations lose their typed spelling.** Two members of one axis cannot be passed
together, so these go through `extraClasses`: button `outline` or `dash` with `ghost` or `link`,
and `soft` with `link`; aura `holo` with `dual`, `gold` or `silver`; swap `rotate` with `flip`; and
any two of the tab bar's `box`, `border`, `lift`.

```kotlin
daisyButton("Save", style = ButtonStyle.Outline, extraClasses = "btn-ghost")
```

**8. `kdaisyui-classes.txt` lists only classes a component can emit.** It drops the two drawer
variant prefixes and `react-day-picker`, which no function renders. If you write one of those
yourself, it is in your own sources, where Tailwind finds it.

**9. Classes that sat on the container now sit on the element DaisyUI puts them on.** In 0.7.x
these parameters wrote their class on the container, where DaisyUI's CSS does nothing (#347):

| 0.7.x | 0.8.0 |
|---|---|
| `daisyMenu(active = true)` | on the item: `li { a { daisyMenuItemClasses(active = true) } }` |
| `daisyMenu(disabled = true)` | on the item: `li { daisyMenuItemClasses(disabled = true) }` |
| `daisyMenu(dropdownShow = true)` | `daisyMenuDropdown(show = true)` and `daisyMenuDropdownToggle(dropdownShow = true)` |
| `daisyDock(active = true)` | on the item: `daisyDockItem(active = true) { … }`, a `<button>` |
| `daisyRating(hidden = true)` | on the first radio: `input { daisyRatingItemClasses(hidden = true) }` |
| `daisyList(modifier = ListModifier.ColGrow)` | on a child of `daisyListRow`: `daisyListCellClasses(colGrow = true)` |

`ListModifier` has no successor enum. New functions for what had none: `daisyListRow`,
`daisyFloatingLabel`, `daisyAvatarGroup` and `daisyVc`, Vanilla Calendar Pro's root.

**10. Every function has a class sibling, for two components on one element and for an element
you write yourself.** `Tag.daisy<Name>Classes(…)` takes the function's class parameters and adds
the same classes to the element it is called on. Not breaking; it replaces the `extraClasses`
strings 0.7.x needed for such cases:

<!-- snippet: skip - the 0.8.x API, whose `attrs` 0.9.0 removed -->
```kotlin
// <ul class="dropdown-content menu menu-sm">
daisyDropdownContent(attrs = { daisyMenuClasses(size = MenuSize.Sm) }) { /* … */ }
// <a class="btn btn-primary">
a { daisyButtonClasses(color = ButtonColor.Primary) }
```

**11. `daisyCalendar` renders `<calendar-date class="cally">`, not a `<div>`.** DaisyUI's `cally`
styles the [cally](https://github.com/WickyNilliams/cally) web component, so a `<div class="cally">`
rendered nothing to look at. The lambda receiver is now `CALENDAR_DATE`, from
`io.github.ollin.kdaisyui.core`; load cally's script as DaisyUI's documentation says.

## How to migrate from 0.8.x

0.9.0 gives every generated function one lambda, `block`, in place of the two it had, `attrs` and
`content`. Most calls keep compiling, because a trailing lambda binds to `block` as it bound to
`content`. **One change does not show as a compile error** — read it first.

**1. `text` and a trailing lambda now render both.** In 0.8.x the lambda replaced the text, so
this rendered a button with no label. It now renders the label too, appended after whatever the
block writes:

```kotlin
daisyButton("Save") { attributes["data-action"] = "submit" }
// 0.8.x: <button class="btn" data-action="submit"></button>
// 0.9.0: <button class="btn" data-action="submit">Save</button>
```

A call that relied on the old behaviour, passing `text` only to have the lambda replace it, drops
the `text` argument.

**2. `attrs` is gone.** Move its statements to the start of the block. On a component that takes
children, put them before the first child or text, which kotlinx.html requires:

<!-- snippet: skip - the 0.8.x API, shown for migration -->
```kotlin
// before
daisyButton("Save", attrs = { attributes["data-action"] = "submit" })
daisyDropdownContent(attrs = { daisyMenuClasses() }) { li { a { +"Item" } } }
daisyInput(attrs = { name = "email" })
```

```kotlin
// after
daisyButton("Save") { attributes["data-action"] = "submit" }
daisyDropdownContent { daisyMenuClasses(); li { a { +"Item" } } }
daisyInput { name = "email" }
```

`<input>` and the other void elements take the block as well. It sets attributes only, since the
element cannot hold children.

**3. `content` is called `block`, and no call has to pass one.** Only a call that names it changes:
`content = { … }` becomes `block = { … }`, or a trailing lambda. `daisyCard()` now compiles and
renders an empty card.

## How to migrate from 0.9.x

Nothing breaks: every 0.9.x call compiles and renders a working page. **One parameter is
deprecated**, and the markup it wrote is gone.

**1. `popoverTarget(…, anchored = true)` writes no anchor any more, so drop `anchored`.** In 0.9.x
it put `style="anchor-name:--…"` on the trigger, and `daisyDropdownPopover` put
`style="position-anchor:--…"` on the menu. A strict `Content-Security-Policy` (`default-src
'self'`) refuses both. Neither is needed: the browser makes the trigger that opens a popover its
anchor (Chrome 133, Safari 26, Firefox 147), so the menu opens beside it with no inline style at
all. The parameter is accepted and ignored, with a deprecation warning that offers the fix:

<!-- snippet: skip - the deprecated 0.9.x call, shown for migration -->
```kotlin
// before
daisyButton("Menu") { popoverTarget(menu, anchored = true) }
```

<!-- snippet: in FlowContent -->
```kotlin
// after
val menu = htmlId("menu")
daisyButton("Menu") { popoverTarget(menu) }
```

A popover you open from script rather than by `popovertarget` has no trigger to anchor to: pass
`source` to `showPopover()`.

**2. `HtmlId.anchorName` is deprecated.** Nothing in kdaisyui writes an anchor name now. It still
returns the same string, for code of your own that names one.

## How to migrate from 0.10.x

Nothing breaks in source: every 0.10.x call compiles unchanged. **One function's JVM signature
changed**, so code compiled against 0.10.x must be recompiled, not only re-linked.

**1. `daisyThemeController` takes a `theme`.** It writes the input's `value`, which is what
DaisyUI matches to switch the page's theme. A library compiled against 0.10.x that calls
`daisyThemeController` fails at runtime with `NoSuchMethodError` until it is rebuilt. Your own
call sites need no change; where they write the value by hand, the parameter says the same thing
typed:

<!-- snippet: skip - the 0.10.x call, shown for migration -->
```kotlin
// before
daisyThemeController { type = InputType.checkBox; value = "dark" }
```

<!-- snippet: in FlowContent -->
```kotlin
// after
daisyThemeController(theme = io.github.ollin.kdaisyui.themes.DaisyTheme.Dark) { type = InputType.checkBox }
```

`DaisyTheme`, `daisyTheme` and `daisyThemeOf` are new; see "Let users choose a theme" in the
[how-to](how-to.md).

**2. The plugin task `kdaisyuiStylesheet` takes the class list as a file.** Its `classList` was the
directory `build/kdaisyui` and is now the file `build/kdaisyui/kdaisyui-classes.txt`; the container
path, and so your entry's `@source "../kdaisyui/kdaisyui-classes.txt"`, is unchanged. Only a build
that set the task's `classList` itself changes: set the file. The fix: with the directory as input,
the task's own output (`build/kdaisyui/app.css`) made every compile out of date, so it ran twice.
