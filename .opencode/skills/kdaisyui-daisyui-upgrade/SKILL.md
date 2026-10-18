---
name: kdaisyui-daisyui-upgrade
description: >-
  Moving kdaisyui to a new DaisyUI version, or reading a failure from one of the guards that
  fire when it moves - the model completeness gate, generated-sources-drift, checkComponentApi,
  api-baseline. Covers the order the steps must run in, how to read `just daisyui-delta`, and
  how a new or removed DaisyUI class reaches codegen/model/. Read this before bumping `daisyui`
  in gradle/libs.versions.toml or before re-dumping anything a guard complained about.
---

# kdaisyui — upgrading DaisyUI

## The shape of the problem

The generated API is declared in `codegen/model/`, one JSON file per DaisyUI component. DaisyUI
supplies only the class list and the descriptions, from each page's frontmatter. So a bump does
not change the API by itself: it changes which classes exist, and generation refuses to run
until the model says what each new class is. A human decides; nothing is inferred.

Three committed artefacts are compared against their inputs:

| Committed artefact | Guard | Regenerate with |
|---|---|---|
| `lib/generated/**`, `docs/reference/**`, `llms.txt` | `generated-sources-drift` | `just generate` |
| `lib/api/lib.api` | `api-baseline` | `just update-api` |
| `lib/api/components.api` | `:lib:checkComponentApi` | `just update-api` |

**A guard is not a chore.** Re-dumping an artefact to make a check green without reading its
diff throws away the one signal the repository has.

## The order

```
1. gradle/libs.versions.toml            daisyui = "x.y.z"
2. just sync-daisyui                    check out the matching submodule tag
3. just daisyui-delta v<old> v<new>     what changed in DaisyUI's documentation
4. edit codegen/model/                  place every new class, drop every removed one
5. just generate                        components, tests, icons, reference pages, llms.txt
6. just update-api                      both Kotlin API baselines; read the diff
7. README.md                            a "How to migrate" entry, if anything broke
8. ./gradlew check                      plus ./gradlew :lib:pitest
```

Step 1 has a constraint stated in `libs.versions.toml` itself: the version must have a
**published webjar**, because `:ktor-integration` serves it. A version without one makes
generated components reference CSS the library cannot deliver.

Steps 3 and 4 come before generation because generation fails until the model matches.

## Step 3: reading the delta

`just daisyui-delta v5.7.17 v5.7.42` reads both tags through `git show`, so the submodule's
checkout does not move. It always exits 0. It reports four kinds of change:

| Line | What to do in the model |
|---|---|
| `Components: added x` | write `codegen/model/x.json`, or `{ "component": "x", "skip": "…" }` |
| `Classes of c: added k` | place `k`: an axis member, a toggle, a part's class, or `notOffered` with a reason |
| `Classes of c: removed k` | delete it from wherever the model places it |
| `Elements of k (c): A became B` | DaisyUI moved the class to another element; decide whether the function that offers it still renders the right one |
| `Join items: added c` | set `"joinItem": true` on `c`'s main function, if the library should mark it |

Example, the replay used as the report's positive control: 5.7.17 to 5.7.42 reports exactly
`Classes of mask: removed mask-square`.

Read DaisyUI's `CHANGELOG.md` back to the pinned version as well. The report names classes and
elements; a new **construction method** — a different element or attribute, like the popover
modal in 5.6.0 — changes no class and only the changelog says so.

## Step 4: placing a class

Every class the frontmatter declares must be placed exactly where the model says. Generation
fails, naming component and class, when:

- DaisyUI declares a class the model places nowhere;
- the model places a class DaisyUI does not declare;
- one function offers one class twice;
- a DaisyUI directory has no model file, or a model file names no directory;
- a function or an enum is named against the naming rule (`kdaisyui-codegen` has the rule).

Choosing between an axis and a toggle is a design decision. An axis is one parameter; two of its
members cannot be passed together except through `extraClasses`. Make a new class an axis member
when it is one more value of something the function already chooses (a new size, a new style);
make it a toggle when it switches a feature on and has no alternative.

## When a guard fires

**Generation failed with "The model disagrees with DaisyUI's classes or with the naming rule".**
That is step 4 not done. Read the named classes against `just daisyui-delta`.

**`generated-sources-drift` failed.** The committed sources no longer match the model and the
submodule. Run `just generate` and commit the result. It reports untracked files too, so a new
component counts as drift.

**`checkComponentApi` or `api-baseline` failed.** Read the diff before re-dumping. A breaking
change needs a **How to migrate** entry in `README.md` before it is released.

## What CI checks

`generated-sources-drift` runs the generators and `checkComponentApi`; it is the only job with the
submodules. `codegen-tests` runs the codegen unit tests with no submodule and no JDK; it reads
`codegen/model/` itself, so a broken model file fails there first. Never make a codegen unit test
read the submodule.

## Prerequisites on the machine

- Node and the git submodules, for `just generate` and `just daisyui-delta`
- Docker, for `./gradlew check`, because `:example-app` compiles its stylesheet in a container

A plain clone still compiles and tests with none of these.
