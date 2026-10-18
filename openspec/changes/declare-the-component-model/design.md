## Context

See `proposal.md` for why. The current pipeline, as read on 2026-10-18:

```
frontmatter.ts ─┐
llms-txt.ts ────┼─ classifier.ts ─ class-groups.ts (exclusivity.json, axis-derivation, axis-names,
documented-*.ts ┘                   property-table) ─ component-shape.ts ─┬─ generator-new.ts
codegen-config.json (13 sections) ─────────────────────────────────────────┤  generator-docs.ts
                                                                           ├─ component-api-dump.ts
                                                                           └─ guards: element-cross-check,
                                                                              config-consumption, offered-twice
test-generator.ts ── its own second classification loop (classifyGroups + loadEvidence)
```

Three facts shape the approach:

- **Two loops.** `component-set.ts` feeds the Kotlin, the reference pages and the API dump.
  `test-generator.ts` classifies a second time on its own. A new input threaded into one loop and
  not the other produces half-updated output that looks valid (`kdaisyui-codegen`, trap 1).
- **The facts are few.** `lib/api/components.api` states the whole generated API in 338 lines.
- **What the inference modules cost.** The modules that infer or guard come to about 1,930 lines
  of source and 1,580 lines of tests, plus `exclusivity.json` at 2,015 lines, before the parts of
  `component-shape.ts`, `component-set.ts`, `join-scope.ts` and `test-generator.ts` that classify.

## Goals / Non-Goals

**Goals:**

- One place, the model, states every structural fact, keyed one way, by DaisyUI directory.
- Both generator loops read it, so they cannot disagree.
- Prove the switch is behaviour-preserving by diff before any API changes.
- Declare the axes Oliver decided on 2026-10-18, rename the whole API by one rule, and fix the tab
  inversion, all as edits to the model.

**Non-Goals:**

- A smarter heuristic, or a better guard. Both are what this change removes.
- Compatibility with 0.7.x. Oliver decided on 2026-10-18 that a clean API outweighs it.
- Changing the shape of a generated function beyond names and axes: the parameter order, the
  escape hatches and the `ClassValues` parameter type stay. `ClassValues` stays on its own merits:
  `size = ButtonSize.Lg` reads the same, and the parked `at()` needs the common supertype.
- Validating the model's own data shape beyond what the generators need. Nothing type-checks
  `codegen/` by decision, and a malformed model fails regeneration or `generated-sources-drift`.

## Enablers and desired changes

| Kind | What | Proven by |
|---|---|---|
| Enabler | Model reader, mechanical export, shape-equality test | the test: model-built shape deep-equals inferred shape, all 66 components |
| Enabler | Both loops read the model; `codegen-config.json` deleted | regeneration: no diff under `lib/generated`, `docs/reference`, `llms.txt`, `lib/api` |
| Enabler | Inference, guards and the exclusivity measurement deleted | regeneration: no diff, same set of files |
| Desired | Completeness gate | its own tests, plus one deliberately removed class |
| Desired | Bump report | fixtures, plus the 5.7.17 to 5.7.42 replay naming `mask-square` |
| Desired | Naming rule, axes and toggles, tab functions, class list from the model | generated tests, the naming test, both API baselines re-dumped and read, E2E for tabs |

Nothing in the first three rows changes a published byte.

## Decisions

### D1. One JSON file per component under `codegen/model/`

`codegen/model/<daisyui-directory>.json`, 66 files plus the two skipped components.

| Alternative | Why it lost |
|---|---|
| One large file | every component edit touches the same file; a review cannot be scoped to one component |
| TypeScript modules | data in code invites logic back in, and nothing type-checks `codegen/` anyway |
| YAML | needs a second dependency, or the hand-rolled frontmatter reader, which `kdaisyui-codegen` already names as the weakest code in the pipeline |
| Kotlin | the generator is TypeScript, and the model would need a compile step to be read |

### D2. The model states facts, never rules

Sketch, the final field names are settled in task 1.1:

```json
{
  "component": "button",
  "summary": "Actions and triggers",
  "functions": [
    {
      "name": "daisyButton",
      "receiver": "FlowContent",
      "element": "BUTTON",
      "classes": ["btn"],
      "text": true,
      "joinItem": true,
      "axes": [
        { "name": "color", "enum": "ButtonColor", "classes": ["btn-neutral", "btn-primary", "…"] },
        { "name": "size", "enum": "ButtonSize", "classes": ["btn-xs", "btn-sm", "btn-md", "btn-lg", "btn-xl"] }
      ],
      "toggles": ["btn-active", "btn-block", "…"],
      "attributes": {},
      "extras": [
        { "name": "disabled", "type": "Boolean", "default": "false",
          "apply": "if (disabled) { this.disabled = true; addClassNames(\"btn-disabled\") }",
          "writes": ["btn-disabled"] }
      ]
    }
  ],
  "notOffered": {}
}
```

- **Classes are listed explicitly, color and size included.** The alternative was to point an
  axis at a frontmatter category, such as `"classes": "color"`. It lost because a new size class
  would then enter the API without anyone placing it, which is the omission the completeness gate
  exists to refuse.
- **`writes` on an extra** lets the completeness gate see the class an extra emits. Without it,
  `btn-disabled` would read as unplaced.
- **Divergences carry their reason inline,** for example `"element": "SPAN"` beside a
  `"divergence"` note citing #356 for `badge`. It is a record for the reader. Nothing checks it.
- **Function names are declared.** That is what lets the tab container and item get honest names
  (D9).

### D3. Bootstrap by export, prove by shape equality

A throwaway script writes every model file from today's `buildComponentShape` result. A test then
builds the shape from each model file and deep-equals it against the inferred one for all 66
components. Only after that test is green do the generators switch. The export script is deleted
once the switch has landed, and the model files are hand-maintained from then on.

| Alternative | Why it lost |
|---|---|
| Write the 66 files by hand | the error this change exists to remove, done 66 times |
| Keep inferring, and use the model for overrides only | that is `codegen-config.json` today |

### D4. Frontmatter stays the class source; the gate is a set comparison

The completeness gate compares two sets per component: the frontmatter's classes, and the classes
the model places, counted with multiplicity. It fails on a difference or on a class counted twice.
It replaces `config-consumption.ts`, `offered-twice.ts` and the measured-group completeness check.

The frontmatter keeps supplying class descriptions for KDoc. Copying them into the model would put
DaisyUI's prose under our maintenance for no gain.

### D5. The bump report compares DaisyUI with itself

`just daisyui-delta <old> <new>` reads both tags' `+page.md` files through `git show` in the
submodule, with no checkout, and reuses the existing marked-example parsers
(`documented-classes.ts`, `documented-element.ts`), which move from the generation path to the
report. It prints and exits 0.

| Alternative | Why it lost |
|---|---|
| Keep the cross-check as a gate, model against docs | needs a standing exception list, 30 entries at removal, for divergences that are decisions |
| No report, read the changelog only | the changelog is prose; the element and join-item deltas are exactly what it does not state |

### D6. The exclusivity measurement is deleted, not kept as a hand tool

With axes declared, nothing reads `exclusivity.json`. A probe that no build runs stops matching
DaisyUI unnoticed, so keeping it would preserve a tool that answers wrongly by the time someone
reaches for it. Its last commit is cited in the deleting commit's body, so it can be revived from
history.

### D7. The declared axes

Names follow D12. Every `variant` becomes `color`, with the enum `<Stem>Color`; that row is the
same for all 19 and is not repeated below.

| Function | 0.7.0 | 0.8.0 axes | 0.8.0 toggles |
|---|---|---|---|
| `daisyAlert` | `style`, `direction`, boolean `soft` | `style: AlertStyle`, `direction: AlertDirection` | `soft` |
| `daisyAura` | booleans `dual` `glow` `gold` `holo` `rainbow` `silver` | `style: AuraStyle` | — |
| `daisyAvatar` | `modifier: AvatarModifier`, boolean `placeholder` | `presence: AvatarPresence` | `placeholder` |
| `daisyButton` | booleans `active` `block` `circle` `dash` `ghost` `link` `outline` `soft` `square` `wide` | `style: ButtonStyle` (Outline, Dash, Soft, Ghost, Link), `shape: ButtonShape` (Square, Circle), `width: ButtonWidth` (Wide, Block) | `active` |
| `daisyCard` | `style`, `modifier: CardModifier` | `style: CardStyle`, `layout: CardLayout` (Side, ImageFull) | — |
| `daisyCarousel` | `modifier: CarouselModifier`, booleans `horizontal` `vertical` | `snap: CarouselSnap`, `direction: CarouselDirection` | — |
| `daisyChat` | `variant: ChatVariant`, booleans `start` `end` | `placement: ChatPlacement` | — |
| `daisyChatBubble` | — | `color: ChatBubbleColor`, moved here from `daisyChat` (D13) | — |
| `daisyCollapse` | booleans `arrow` `plus` `open` `close` | `icon: CollapseIcon`, `state: CollapseState` | — |
| `daisyDivider` | `direction`, booleans `start` `end` | `direction: DividerDirection`, `placement: DividerPlacement` | — |
| `daisyDropdown` | `modifier`, `horizontalPlacement`, `verticalPlacement`, booleans `close` `left` `right` | `side: DropdownSide` (Top, Bottom, Left, Right), `align: DropdownAlign` (Start, Center, End), `state: DropdownState` (Open, Close) | `hover` |
| `daisyFooter` | booleans `center` `horizontal` `vertical` | `direction: FooterDirection` | `center` |
| `daisyIndicatorItem`, `daisyToast`, `daisyModal` | placements as booleans on modal, as enums on the other two | `horizontalPlacement`, `verticalPlacement` | modal: `open` |
| `daisyList` | `modifier: ListModifier` | `column: ListColumn`, placement unchanged under #347 | — |
| `daisyMask` | `style`, `modifier: MaskModifier` | `style: MaskStyle`, `half: MaskHalf` | — |
| `daisyMegamenu` | booleans `full` `vertical` `wide` | `width: MegamenuWidth` (Wide, Full) | `vertical` |
| `daisyStack` | booleans `top` `bottom` `start` `end` | `placement: StackPlacement` | — |
| `daisyStats` | `daisyStat(direction)` | `direction: StatsDirection` | — |
| `daisyStep` | — | `color: StepColor`, moved here from `daisySteps` (D13) | — |
| `daisySwap` | booleans `active` `flip` `rotate` | `style: SwapStyle` | `active` |
| `daisyTabs` | `daisyTab(size, placement)`, booleans `box` `border` `lift` | `size: TabsSize`, `placement: TabsPlacement`, `style: TabsStyle` | — |
| `daisyTab`, `daisyTabRadio` | `tabActive` `tabDisabled` on the container | — | `active` `disabled` |
| `daisyTimeline` | booleans `compact` `horizontal` `snapIcon` `vertical` | `direction: TimelineDirection` | `compact` `snapIcon` |
| `daisyTooltip` | `sidePlacement`, `alignPlacement` | `side: TooltipSide`, `align: TooltipAlign` | `open` |

Unchanged toggles: `daisyDock.active`, `daisyDrawer.end` and `open`, `daisyFab.flower`,
`daisyFileInput`/`daisyInput`/`daisySelect`/`daisyTextarea.ghost`, `daisyLink.hover`, the four
`daisyMenu` toggles (#347), `daisyOtp.joined`, `daisyRange.vertical`, `daisyRating.half` and
`hidden`, `daisyTable.zebra`/`pinRows`/`pinCols`, `daisyTimelineStart`/`Middle`/`End.box`. A
one-member style such as `input-ghost` stays a toggle; a one-entry enum would be ceremony.

The 29 pairs this gives up, read from `codegen/exclusivity.json` at 5.7.42, all measured
`compose`:

| Kind | Pairs |
|---|---|
| Two looks, a caller might want them (12) | button `outline|ghost` `outline|link` `dash|ghost` `dash|link` `soft|link`; aura `dual|holo` `holo|gold` `holo|silver`; swap `rotate|flip`; tabs `box|border` `box|lift` `border|lift` |
| Opposite values, or two sides DaisyUI never combines (17) | carousel `horizontal|vertical`; chat `start|end`; collapse `arrow|plus` `open|close`; divider `start|end`; dropdown `top|left` `top|right` `bottom|left` `bottom|right` `left|right` `open|close`; megamenu `wide|full`; modal `top|middle` `top|bottom` `middle|bottom` `start|end`; timeline `horizontal|vertical` |

Footer's one pair and stack's six measured `same`, meaning the probe could not tell them apart.

### D8. Dropdown takes the tooltip's shape: `side` and `align`

**Verified** 2026-10-18 at the pinned tag: every dropdown example in DaisyUI's documentation
combines exactly one of `top`, `bottom`, `left`, `right` with at most one of `center`, `end`,
twelve examples in all. That is the tooltip's shape, and none combines two sides.

| Alternative | Why it lost |
|---|---|
| Keep `verticalPlacement` and `horizontalPlacement`, add `side: DropdownSide` (Left, Right) | it kept 0.7.x names, which no longer matters, and calls an alignment "horizontal" even when the dropdown opens to the left |

### D9. Tab names follow the classes

| 0.7.0 | 0.8.0 | Renders |
|---|---|---|
| `daisyTab` | `daisyTabs` | `<div role="tablist" class="tabs …">` |
| `daisyTabTab` | `daisyTab` | `<a role="tab" class="tab">` |
| — | `daisyTabRadio` | `<input type="radio" role="tab" class="tab">`, void |
| `daisyTabTabContent` | `daisyTabContent` | `<div class="tab-content">` |

`daisyTab` changes meaning. A 0.7.x call with no arguments, `daisyTab { }`, still compiles and now
renders an item instead of a bar. The migration note says so in its first line about tabs.

| Alternative | Why it lost |
|---|---|
| `daisyTabItem` for the item, so no old name changes meaning | it breaks the naming rule (D12) for one migration hazard, and compatibility is not a constraint |
| Only the `<a>` item | DaisyUI's `tab-content` panels need the radio form to work without JavaScript |

### D10. `lib/api/components.api` stays

On 2026-10-18 this was described as redundant once the model exists. On reflection it is not: a
change to generator code can move a signature, for example parameter order or an escape hatch,
without any model edit. The dump is 94 lines of source and catches exactly that.

### D11. `kdaisyui-classes.txt` is written from the model

It lists the classes the model offers, including the ones extras and constructions write, instead
of every frontmatter class of a generated component. Classes under `notOffered`, such as the
drawer variants named in #345, drop out.

### D12. One naming rule, applied by the model and pinned by a test

The rule is in `specs/component-model`, "Every generated name follows one naming rule". In short:
a function is named after the class its element carries, an axis after what it chooses, an enum
after its function and axis, an entry after its class suffix. The model still declares every
name; the test only refuses a declared name the rule contradicts, so a new DaisyUI part cannot
arrive misnamed.

| Alternative | Why it lost |
|---|---|
| Derive names in the generator, declare none | names would be inferred again, and the two declared stems would need a config section |
| Declare names with no rule | 0.7.0 shows where that ends: `daisyStatStat`, `StepsVariant.StepNeutral` |
| Keep `variant`, it is what DaisyUI's docs call colors in prose | the frontmatter calls the category `color`, and `variant` says nothing about what is chosen |

### D13. Two color enums move to the part that wears them

`ChatVariant` sits on `daisyChat`, but DaisyUI documents `chat-bubble-primary` on
`<div class="chat-bubble chat-bubble-primary">`. `StepsVariant` sits on `daisySteps`, but DaisyUI
documents `step-primary` on `<li class="step step-primary">`. Both were checked on 2026-10-18 at
the pinned tag. The element cross-check did not report either; task 1.5 records why, because a
defect the old gate missed is evidence about the gate this change removes.

## Risks / Trade-offs

- **[A wrong model fact ships quietly between bumps.]** Nothing cross-checks the model on every
  run any more. → The bootstrap is checked once against the old cross-check (task 1.4), and every
  bump runs the report. The model is reviewed like code.
- **[The export copies today's defects into the model.]** → Intended. The model starts as exactly
  today's API, and every defect leaves it by a visible edit. The known ones are #347 and #343.
- **[29 pairs lose their typed form.]** → All stay reachable through `extraClasses`, and the
  twelve that combine two looks are listed in the migration note.
- **[fwb-ssr has to migrate nearly every call site.]** → Accepted by decision. Almost every break
  is a compile error. The one that is not, `daisyTab { }` with no arguments, leads the tab section
  of the migration note.
- **[Hand-maintained JSON drifts in style across 66 files.]** → The export writes them in one
  format, and regeneration is deterministic, so a reformatting shows up as a diff.

## Migration Plan

Released as 0.8.0 by a separate "declare" change, as with 0.7.0. `README.md` gains "How to migrate
from 0.7.x" with the renamed functions, the renamed enums, every removed boolean with its
replacement, and the tab table from D9. Rollback is a
revert of the merge. No persisted data is involved.

## Open Questions

None that change the specs or the tasks. Field names in the model schema are settled by task 1.1
and recorded there.
