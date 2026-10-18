## Why

The generator infers each component's structure from DaisyUI's documentation prose and example
markup: its element, its parts and their elements, which function wears which class, its static
attributes, which class groups are exclusive and what each axis is called. Every inference that
turned out wrong cost a heuristic, a config section and a guard. Fixing it was usually one line.
The expensive part was building the machinery to find the next wrong inference: 13 of the 20
archived changes are dated September 2026, the codegen holds about 10,400 lines of TypeScript,
and the API they produce fits in the 338 lines of `lib/api/components.api`.

The facts those guards protect are few and change rarely. Declaring them once in a committed,
reviewed model, and generating from that, removes the class of defect the guards were built to
catch. The guards are not made better.

## What Changes

- **A committed component model** under `codegen/model/`, one file per DaisyUI component. It
  declares for every generated function: the element, the receiver, the classes it always writes,
  its axes and their members, its on/off toggles, its static attributes, its extras, whether it
  takes text, and whether it is a join item. It also holds the editorial summary. The model is
  bootstrapped mechanically from today's inferred shape and proven equal to it before anything is
  generated from it.
- **DaisyUI's frontmatter keeps supplying the class list.** Generation fails while the model and
  the frontmatter disagree on which classes exist, in either direction. That is a set comparison
  and involves no inference.
- **Removed from the generation path:** the element heuristic, the element and class-placement
  cross-checks with their 30 exception entries, the config-consumption guard, the offered-twice
  guard, the measured class-group classification with its axis derivation and axis naming, and
  `codegen-config.json`, which the model replaces.
- **Removed entirely:** the exclusivity measurement, meaning `codegen/exclusivity.json`, its browser
  probe, `just measure-exclusivity`, `:lib:verifyExclusivity` and its CI step. Exclusivity no longer
  decides anything.
- **A DaisyUI bump gets a report instead of guards.** It lists what changed in DaisyUI's
  documentation between the pinned tag and the new one: classes added or removed, the elements a
  class is documented on, the join-item companions, and components added or removed. A human reads
  it and edits the model.
- **BREAKING:** the class-bearing boolean parameters that belong to a group become one enum-typed
  parameter per declared axis, for example `style = ButtonStyle.Ghost` instead of `ghost = true`.
  On/off features stay `Boolean = false`, named after their class as today. Passing an axis twice
  is a compile error, because it is one named parameter. Twelve class pairs that combine two looks, such as `btn-outline` with `btn-ghost`,
  lose their typed spelling and stay reachable through `extraClasses`.
- **BREAKING:** every generated name follows one naming rule, with no regard for 0.7.x. A
  function is named after the class its element carries, so `daisyStatStat` becomes `daisyStat`
  and the container `daisyStats`. An axis is named after what it chooses, so every `variant`
  becomes `color` and no parameter is called `modifier`. Decided by Oliver on 2026-10-18: a clean
  API now outweighs compatibility.
- **BREAKING:** `tab`'s container and item trade places (#342). This folds in the open change
  `tab-container-and-item-trade-places`, which is deleted.
- `kdaisyui-classes.txt` lists the classes the model offers, not every class in the frontmatter
  (#345).

### The subtractive option, stated

The cheapest alternative is to add nothing and freeze kdaisyui: Renovate bumps and drift checks
only, with the declared-model idea moved to the new library planned on 2026-10-13. That was
weighed and rejected for kdaisyui, because fwb-ssr depends on it for months and every DaisyUI bump
until then pays the inference cost again.

Within this change, subtraction is the larger half. Every guard, the measurement and the config
file go. What is added is one model file per component, a completeness gate and a bump report.

### Deliberately out of scope

- `menu-active`, `menu-disabled`, `dock-active`, `rating-hidden` and `list-col-*` stay where they
  are. They belong on child elements no function renders, and #347 owns that.
- `calendar`'s custom element stays as it is (#343).
- A popover construction for `dropdown`, which is the condition named in #357.
- Unparking `at()`, which belongs to the probe `extract-used-classes-from-bytecode`.
- Compiling the documentation snippets, which belongs to `verify-doc-snippets`.
- The heroicon generator, whose input is an SVG set and not documentation.
- Declaring the 0.8.0 release, which is its own change, as with 0.7.0.

## Capabilities

### New Capabilities

- `component-model`: the component API is declared in a committed model, the model accounts for
  every class DaisyUI declares, and a DaisyUI bump is read as a documentation delta.

### Modified Capabilities

- `daisyui-component-coverage`: exclusive groups are declared axes, not measured cliques. The
  element and the class placement are declared, not cross-checked. The join-item set is declared,
  not derived. New classes fail generation until the model places them. `tab`'s container and item
  trade places.
- `generated-sources`: the unread-configuration guard goes, because there is no configuration left
  to leave unread. The model's completeness gate replaces it.

## Impact

- **API:** nearly every generated function changes a name or a parameter. Compatibility with 0.7.x
  is deliberately not a constraint. `README.md` gains "How to migrate from 0.7.x", because the
  `api-change-detection` capability requires one for a breaking release.
- **Code:** `codegen/src/` loses the inference and guard modules and gains a model reader, the
  completeness gate and the delta report. `codegen-config.json` and `exclusivity.json` are deleted.
  `e2e-tests` loses its `exclusivity` source set.
- **CI:** `generated-sources-drift` loses its `verifyExclusivity` step. `checkComponentApi` stays,
  because a generator change can still move a signature without a model edit.
- **Consumers:** fwb-ssr's call sites with `ghost = true`, `circle = true` and similar must be
  migrated when it moves to 0.8.0.
- **Issues:** this closes #342 and #345, and lets #356 and #357 close, because the decisions they
  anchored now live in the model.
