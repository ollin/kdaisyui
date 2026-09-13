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
