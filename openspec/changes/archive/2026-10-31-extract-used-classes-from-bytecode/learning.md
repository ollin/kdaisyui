## Signal read

**Dampen.** It is driven by one observation: the optimistic and pessimistic stylesheets are
**4,856 bytes gzip apart**, under the 5 KB the probe set in advance (`observations.md`, step 2).
The probe wrote down what that means before it ran: precision buys nothing, and the simple answer
(ship per component, no bytecode reading) wins.

This is not a partial amplify. Two of the three amplify conditions held, but the third, the
`at()` pair, was never tested. And the probe says plainly that two out of three is no green light.

## What we learned

- **The size problem is real, and it is a per-component problem.** A consumer that uses 19 of 66
  components gets 46.0 KB gzip where 22.6 KB would do: 51% of today's stylesheet is CSS for
  components it never renders. That holds even under the most pessimistic rule, so it does not
  depend on reading anything clever out of the consumer's code.
- **Enum-level precision is worth 4.9 KB.** That is the whole value of resolving
  `ButtonSize.Lg` to `btn-lg` rather than "the button component", and it is too little to carry a
  build-time component that reads bytecode.
- **The `at()` argument stands alone now, and is smaller.** Unparking `at(Breakpoint, …)` is the
  one reason left to resolve enum entries. The September measurement already showed both halves
  sit in the constant pool. This probe did not reopen that, and it is not by itself the size
  prize the hypothesis paired it with.
- **About how we work:** a flat result across very different inputs was the instrument failing
  (Tailwind's automatic source detection), not the system behaving. Distrusting it cost one
  command; trusting it would have produced a false dampen for the wrong reason.

## What is now specifiable

Nothing is specified by this change, which keeps `skip_specs: true`. One pattern is stable enough
for a follow-up **spec-driven** change, if Oliver wants the 23 KB:

- **Per-component class delivery.** The library emits its class list per component (beside, or
  instead of, the single `kdaisyui-classes.txt`). The consumer, or a small Gradle task, sources
  only the components the build uses. Which components are used can come from the consumer
  declaring them, or from the method references to `daisy*` functions in compiled classes. That is
  a much smaller reading than enum resolution, and it fails safe: an unresolvable call can fall
  back to the component's full list, never to less.

Open choices for that change, none settled here: declared or detected; whether DaisyUI's own
`include:` option can carry it once the base layers it drops are named; and what happens to
`kdaisyui-classes.txt` as the documented integration.

## What is still unknown

- Whether one consumer generalises: the example app is still the only one measured. 19 of 66
  components may say more about a demo than about real use.
- The `at()` question, steps 3 to 5 of `probe.md`. It needs its own hypothesis if it is wanted,
  because it is no longer bundled with a size argument.
- What `include:` drops beyond `rootscrolllock` and one theme block, and whether naming the base
  layers makes it equivalent to the per-component list.
