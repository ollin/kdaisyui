## Why

A consumer that renders 19 of the 66 components receives CSS for all 66. The probe
`extract-used-classes-from-bytecode` (archived 2026-10-31) measured it on `example-app`: **46.0 KB**
gzip today, **22.6 KB** if the stylesheet covered only the components the app calls, even under
the most pessimistic rule (every class of every touched component). Half of the stylesheet is
CSS for components the page never renders.

The same probe settled what the saving does **not** need. Resolving the enum entries a consumer
names (`ButtonSize.Lg` → `btn-lg`) would save another 4.9 KB, which is under its own dampen
threshold, so a bytecode extractor at enum precision is not worth building. Granularity at the
component is the prize, and it is a much smaller problem.

**Not scheduled.** This proposal records the follow-up the probe named, so it is not lost. The
specs, design and tasks are written when the change is picked up.

## What Changes

- The codegen emits the class list **per component** as well as whole, for example
  `kdaisyui-classes/<component>.txt` beside `kdaisyui-classes.txt`.
- A consumer's CSS build sources only the lists of the components its code calls. Which those
  are is the open design question (see Assumptions A2): declared by the consumer and checked, or
  detected from the `daisy*` method references in compiled classes. Detection at component
  granularity is a much smaller reading than the enum resolution the probe rejected.
- An unresolvable call falls back to more CSS, never to less: the failure mode of a narrower list
  must stay a bigger stylesheet, not an unstyled page.
- `example-app/src/main/css/app.css` says `source(none)`, so its stylesheet depends only on the
  sources it declares (probe finding F3).
- The whole `kdaisyui-classes.txt` stays the documented default.

**Not breaking** by intent: the existing resource and setup keep working.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | No requirement of a main spec this builds on contradicts it. It builds on `css-delivery`, whose "The size and its remedy are stated honestly" forbids telling a reader to **hand-maintain** a narrower list | code | to check in task 1.1 - a declared component list is only allowed if something checks it against the code |
| A2 | Detecting the called components from compiled classes is simpler and safer than a declaration | code | to check in task 1.2 |
| A3 | The 22.6 KB of one demo app generalises to real consumers | external | open - only `example-app` has been measured |
| A4 | DaisyUI's `include:` option cannot carry it as it stands, because it also drops base layers nobody named (`rootscrolllock`, one theme block) | code | to check in task 1.3 - observed in the probe, not explored |

## Capabilities

### Modified Capabilities

- `css-delivery`: the library ships its class list per component as well as whole, and the
  documentation shows how to narrow the stylesheet without hand-maintaining a list.

## Impact

- `codegen/`: one more generated resource family.
- `lib`: the resources ship in the jar.
- `example-app`: the stylesheet entry, and possibly a narrowed setup as the demonstration.
- Documentation of the CSS setup, in README.md and `docs/how-to.md`.
