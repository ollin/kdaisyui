---
name: kdaisyui-daisyui-upgrade
description: >-
  Moving kdaisyui to a new DaisyUI version, or reading a failure from one of the guards that
  fire when it moves - generated-sources-drift, verifyExclusivity, checkComponentApi,
  api-baseline. Covers the order the steps must run in, what each committed artefact means,
  and the re-measurement that needs a browser. Read this before bumping `daisyui` in
  gradle/libs.versions.toml or before re-dumping anything a guard complained about.
---

# kdaisyui — upgrading DaisyUI

## The shape of the problem

Almost everything this library exposes is DERIVED from DaisyUI: the components, their
parameters, the icons, the reference pages, and — since the exclusivity measurement — which
parameters are enums and which are booleans. A version bump therefore changes the public API,
and the whole mechanism exists to make that change **visible and reviewed** rather than
absorbed silently.

Four artefacts are committed and compared against their inputs. Each has a guard, and each
guard has a matching command that regenerates it:

| Committed artefact | Guard | Regenerate with |
|---|---|---|
| `lib/generated/**`, `docs/reference/**` | `generated-sources-drift` | `just generate` |
| `codegen/exclusivity.json` | `:lib:verifyExclusivity` | `just measure-exclusivity` |
| `lib/api/lib.api` | `api-baseline` | `just update-api` |
| `lib/api/components.api` | `:lib:checkComponentApi` | `just update-api` |

**A guard is not a chore.** Each one is the only place a particular kind of silent breakage
becomes visible, and re-dumping the artefact to make the check green throws away the one
signal the repository has.

## The order, and why it is not arbitrary

```
1. gradle/libs.versions.toml      daisyui = "x.y.z"
2. just sync-daisyui              check out the matching submodule tag
3. just generate                  components, component tests, icons, reference pages
4. just measure-exclusivity       re-measure which classes conflict — needs a browser
5. just update-api                both Kotlin API baselines
6. README.md                      a "How to migrate" entry, if anything broke
7. ./gradlew check                plus ./gradlew :lib:pitest
```

Step 1 has a constraint stated in `libs.versions.toml` itself: the version must be one with a
**published webjar**, because `:ktor-integration` serves that webjar and the measurement reads
it. A version without one makes generated components reference CSS the library cannot deliver.

Steps 3 and 4 both read the submodule, so step 2 comes first. Step 5 comes last because it
records the API that steps 3 and 4 produced — running it earlier bakes in a baseline that the
next step invalidates.

## Step 4 in detail: the exclusivity measurement

`codegen/exclusivity.json` records, for every pair of classes in every multi-member DaisyUI
class group, whether the two can be worn at once. It decides which groups become a Kotlin
enum. The rule is one sentence:

> **A group becomes an enum only when EVERY pair in it is measured `exclusive`.**

Because the cost of being wrong is asymmetric:

| wrong as | consequence |
|---|---|
| enum | a combination DaisyUI permits **cannot be expressed** |
| boolean | a combination that does nothing **can be expressed** |

The first defeats the library's purpose; the second is a harmless mistake the `extraClasses`
escape hatch permits anyway. So `same` — "the probe could not tell" — falls to boolean along
with `compose`.

### It cannot be reasoned out, only measured

Six earlier attempts in this repository tried to derive exclusivity by reading CSS or by the
shape of class names. All six were wrong, several confidently. `modifiers` was assumed to be
the untrustworthy category and `styles` the reliable one; the browser says `chat.placements`
and `carousel.directions` compose while `card.modifiers` and `list.modifiers` are exclusive.

**Do not hand-edit the file, and do not reason about a verdict.** Run the measurement.

### What `just measure-exclusivity` does

Unpacks `daisyui.css` from the webjar, builds a page holding one case per class and one per
pair — each injected into DaisyUI's own documented example for that component — renders it in
the system Chromium through `:e2e-tests:measureExclusivity`, and writes the whole file.

The verdict for a pair `a`, `b`:

```
sig(ab) == sig(a) or sig(ab) == sig(b)   ->  one is inert against the other  ->  exclusive
sig(ab) differs from both                ->  a third result neither produces ->  compose
sig(a) == sig(b), neither of the above   ->  this example cannot tell        ->  same
```

### Reading the diff after a re-measurement

A changed verdict is a changed public API:

| change | effect |
|---|---|
| a group becomes all-`exclusive` | it is now a choice — the run FAILS until `enumNames` names it |
| a group loses its last non-exclusive pair | same |
| a group gains a `compose` or `same` pair | its enum must be withdrawn; that is a breaking change |
| a new group appears | unmeasured until re-measured; `verifyExclusivity` says so |

The `enumNames` config is checked against the measurement, not trusted: naming a group the
browser refutes fails the run, as does declaring an axis that is not a clique, or splitting a
group whose axes are exclusive across as well as within.

### Four details of the probe that must never be lost

Each was a defect that produced wrong verdicts silently, and each now has a test named after
it in `codegen/test/exclusivity-probe.test.ts`:

1. **The `$$` sentinel.** DaisyUI's docs write `class="$$btn"`. Left in, the element matches no
   rule and every pair looks identical.
2. **Strip the group's own members from the example.** The mask example is
   `class="mask mask-squircle"`; injecting `mask-square` otherwise measures stylesheet order.
3. **Hold one colour class constant.** `alert-outline` and `alert-soft` both mix
   `--alert-color`; unlit, both compute to the same transparent black.
4. **Load the whole `daisyui.css` bundle.** A hand-picked subset omitted every theme, so colour
   variables were undefined, and omitted `utilities/join.css`, where `join-vertical` lives.

If a re-measurement suddenly reports many `same` verdicts, suspect the probe before believing
the finding — "indistinguishable" was the failure signature of all three defects above.

### The known limitation, which is not a defect

`chat-start|chat-end` composes, and no author would combine them. Two classes that each set a
*different* subset of properties toward the same intent always will. That leaves a useless
combination expressible, which is the cheap direction, and it is recorded in the file's own
`limitation` field rather than worked around.

## When a guard fires

**`:lib:verifyExclusivity` failed.** It names each group and what is wrong with it: unmeasured,
missing a pair DaisyUI added, holding a class DaisyUI removed, or describing a group that no
longer exists. Run `just measure-exclusivity` and read the diff.

**`generated-sources-drift` failed.** The committed sources no longer match their inputs. Run
`just generate`. Note it reports untracked files too, so a new component counts as drift rather
than slipping through.

**`checkComponentApi` or `api-baseline` failed.** Read the diff before re-dumping. A breaking
change needs a **How to migrate** entry in `README.md` before it is released; `just update-api`
prints the diff for exactly that reason.

**`codegen-tests` failed on a pin.** `codegen/test/exclusivity.test.ts` pins the fifteen groups
the browser calls a single choice and the four needing an axis split. A bump that moves one is
an API change — update the pin deliberately, in the same commit as the config that reacts to it.

## What CI checks, and what it does not

`generated-sources-drift` runs the generators, `checkComponentApi` and `verifyExclusivity`. It
is the only job with the submodules and Node.

`codegen-tests` runs the codegen unit tests and is deliberately free of submodules and a JDK —
`ci.yml` says so at the job. **Never make a codegen test read the submodule**; that is the
signal the test has drifted into integration territory the drift job already covers.

CI does **not** re-measure exclusivity. That needs a browser and stays a deliberate step, which
is why the cheap guard exists: it cannot take the measurement but it can tell that the
committed one has gone stale.

## Prerequisites on the machine

- Node and the git submodules — `just generate`, `just measure-exclusivity`, `just verify-exclusivity`
- A system Chromium — `just measure-exclusivity` only. It is passed explicitly as an argument;
  `PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH` does not redirect Playwright's bundled headless shell.
- Docker — `./gradlew check`, because `:example-app` compiles its stylesheet in a container

A plain clone still compiles and tests with none of these. Only regeneration needs them.
