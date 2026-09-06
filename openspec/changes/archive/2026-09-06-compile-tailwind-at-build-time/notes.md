# Notes

## 1.1 — the premise was wrong: five prefixes work, the rest do not

**Refuted.** The proposal claimed no Tailwind variant of a DaisyUI class compiles in the setup the
docs teach. Measured on the running example app:

| Variant prefix | In the prebuilt `daisyui.css` webjar | Rules |
|---|---|---|
| `sm:` `md:` `lg:` `xl:` | yes | 1198 each |
| `hover:` | yes | 60 |
| `max-sm:` `max-lg:` … | **no** | — |
| `dark:` `focus:` | **no** | — |

Exactly five distinct prefixes across the whole 1.1 MB file.

The Tailwind **browser** build contributes nothing to DaisyUI classes: `lg:btn-lg` is absent from
its 5.8 KB of output. It only generates variants of Tailwind's own utilities, which is why
`sm:hidden` works. Everything that works for a DaisyUI class works because DaisyUI pre-generated
it, not because anything compiles at runtime.

**Consequence for the docs:** `docs/explanation.md:137` promises `lg:btn-lg`, and that promise
holds. The earlier reading of it as a false promise was wrong.

### Measurement

`/tailwind-variant` renders a matched pair: `btn btn-lg` as control, `btn lg:btn-lg` as subject.
Comparing the two hard-codes no size and survives a DaisyUI restyle.

| Viewport | control | subject `lg:btn-lg` | subject `max-lg:btn-lg` |
|---|---|---|---|
| 1280 | 48px | 48px | — |
| 800 | 48px | 40px (correct: below `lg`) | **40px — should be 48px** |

## 1.1b — the gate, seen failing

```
Expected #variant-control and #variant-subject-max to share height, got 48px and 40px
```

`max-lg:btn-lg` at 800px leaves the button at base size. That is the assertion 3.2 has to make
pass, and it has now been watched failing. Reverted rather than landed red; the button it needs
stays on the page, inert, so 3.2 adds only the scenario.

## Why the change still stands, on a different reason

The original justification — "variants do not work" — is gone. The real problem is subtler:

**It works just often enough to teach a wrong general rule.** A developer tries `lg:btn-lg`, sees
it work, and concludes DaisyUI classes accept Tailwind variants. Later `max-sm:card-side` or
`dark:alert-info` silently does nothing, and they now hold a prior belief saying the fault must be
elsewhere. Nothing marks the boundary: no error, no warning, no failing test.

The boundary is also not a contract. It is whatever DaisyUI chose to pre-generate in the release
we happen to pin, and a future DaisyUI could shrink that set without it being breaking for anyone
except consumers of a prebuilt stylesheet.

## 1.2 — compiling works, and needs no standalone binary

Probe run entirely in `/tmp/tw-probe`, nothing committed: `npm install tailwindcss@4
@tailwindcss/cli@4 daisyui@5.7.17`, an entry CSS of `@import "tailwindcss"; @plugin "daisyui";
@source "./classes.txt"`, and a class list naming the cases that fail today.

| Class | In compiled output |
|---|---|
| `lg:btn-lg` | PRESENT |
| `max-lg:btn-lg` | **PRESENT** |
| `max-sm:megamenu-vertical` | **PRESENT** |
| `dark:alert-info` | **PRESENT** |
| `focus:input-primary` | **PRESENT** |
| `.megamenu-vertical`, `.btn` | PRESENT |

Every prefix the prebuilt webjar lacks compiles fine. The DaisyUI plugin resolves from a plain npm
install with no special setup.

**Two things this changes about the plan.**

1. **The 112 MB standalone binary is unnecessary.** It was the assumed route because it avoids
   Node. But Node is already required for `just generate`, so it buys nothing here and costs a
   per-platform download — six release assets, 80-112 MB each, needing OS and libc detection.
2. **The output is 32 KB, against 1.1 MB for the prebuilt webjar** — a 97% reduction, because a
   compile emits only what the sources use. That was not a goal and is the larger practical win.

## 1.3 — REVISED (Oliver): compile in the Gradle build, in a Docker container

Supersedes the decision recorded below, which was to compile in `just generate` and commit the
result. Oliver chose compilation in the build; Docker is what makes that possible without putting
Node on the host.

**Measured**, `node:26-slim`, `max-sm:megamenu-vertical` and `.megamenu-vertical` and
`dark:alert-info` all present in the output:

| | |
|---|---|
| Warm run, image and npm cache present | **0.99 s** |
| Cold run, image pull | ~15 s, once per machine |
| Output | 23 KB |
| Host requirement | Docker. **No Node, no npm.** |

**The one real obstacle, and its fix.** A default `docker run` writes output as `root`, which
would leave root-owned files in `build/` and break `./gradlew clean` for the user who ran it. The
container must run as the calling user:

```
-u "$(id -u):$(id -g)" -e HOME=/tmp -e NPM_CONFIG_CACHE=/tmp/.npm
```

Worth stating because the failure is delayed and confusing: the first root run succeeds, and the
*next* run fails with npm exit 243 because it cannot overwrite the root-owned `node_modules` the
first one left. Found by running the probe twice, which is the only reason it was found at all.

**What this changes about the project's promises.** `AGENTS.md` says a clone "compiles and tests
with no Node, no npm and no git submodules". That stays literally true — and becomes misleading
unless it also says Docker is now required, because `:e2e-tests` depends on `:example-app:classes`
and therefore `./gradlew check` will pull the CSS compilation in. `:lib` and `:ktor-integration`
remain free of it. This is a deliberate trade, not a side effect, and it belongs in the
documentation rather than in a commit body.

**Open, and to settle in section 3:** pinning the image by digest rather than by tag, declaring
Gradle task inputs and outputs so the compile is incremental and cacheable, and adding Docker to
the four CI jobs that build `example-app`.

## ~~1.3 — decision: compile in `just generate`, commit the result~~ (superseded)

Not in the Gradle build. The repository already has exactly this pattern and it is the reason a
clone needs no Node: **generated output is committed and a CI drift job keeps it honest.**
Components, component tests and Heroicons all work this way.

A compiled stylesheet is the same kind of artefact. So:

- `just generate` gains a step that compiles `example-app`'s CSS;
- the result is committed, like `lib/generated/`;
- `generated-sources-drift` regenerates and fails if it changed;
- `example-app` serves the committed file, and `./gradlew build` needs no Node.

Rejected alternatives:

- **Compile in the Gradle build.** Puts Node on the critical path for a plain `./gradlew build`,
  which is the constraint the project deliberately removed when it started committing generated
  sources. It would be undoing a decision this project already made and documented.
- **Standalone binary.** Solves a Node problem that only exists if the previous option is chosen.
- **Keep the webjar.** The status quo, and the thing being fixed.

**One consequence to hold on to:** the committed stylesheet is compiled against the example app's
own class usage, so it is *not* a general-purpose DaisyUI build and must never be offered to
consumers as one. What consumers need is the instructions, which is section 5.

## 2.1 — scanning works on disk, and is unavailable to consumers

Two findings that point in opposite directions.

### Scanning `.kt` works, with no configuration

Pointing `@source` at `lib/generated/main/kotlin` finds everything. No extension setting was
needed — Tailwind 4 scans a `@source` directory by content:

| Class | Occurrences in output |
|---|---|
| `.btn` | 74 |
| `.btn-primary`, `.btn-lg`, `.alert-success`, `.badge-outline` | 1 each |
| `.card` | 15 |
| `.megamenu` | 52 |
| `.tooltip-info` | 4 |

**Assumption 3 refuted:** a safelist is *not* required, because every class the library can emit
appears as a string literal in a generated enum constructor, and the scanner reads them. That is a
much better answer than the safelist the proposal assumed.

*(An earlier run of this same probe reported most of these absent. The grep required `{` or `,`
immediately after the class name and Tailwind emits neither. Fifth instrument defect of the
session; the numbers above come from counting occurrences instead of matching punctuation.)*

### But a consumer has no sources to scan

```
kdaisyui-0.2.0.jar   kt entries: 0    class entries: 1088
```

The published jar carries compiled classes only. A consumer's Tailwind, pointed at their own
sources, finds nothing: they write `daisyButton(variant = ButtonVariant.Primary)`, and the string
`btn-primary` lives in **our** source, not theirs. So the mechanism that works in this repository
is exactly the one unavailable to the people the documentation is for.

A `kdaisyui-<version>-sources.jar` *is* published, so the classes are obtainable — but a jar is
not a directory and Tailwind cannot read into one.

### The cost that comes with it

Scanning the library's full generated surface produces **399,879 bytes** — every class the library
can emit, because every one of them is a literal in the sources. Against:

| Approach | Output |
|---|---|
| Prebuilt webjar (today) | 1,123,000 B |
| Scan the library's whole generated surface | 399,879 B |
| Scan only the classes an app actually uses | 32,373 B |

So the consumer choice is not "correct or broken" but "correct-and-400 KB" versus
"small-and-you-maintain-a-list". Both are defensible; what is not defensible is leaving it
undocumented, which is the current state.

## 2.3 — decision: ship the class list in the jar, and never ask anyone to hand-maintain one

**The documented path is a generated class list, packaged as a resource inside the existing
`kdaisyui` jar.** A consumer extracts it with a short Gradle task and points `@source` at it:

```kotlin
val kdaisyuiClasses by configurations.creating
dependencies { kdaisyuiClasses("io.github.ollin.kdaisyui:kdaisyui:<version>") }

tasks.register<Copy>("extractKdaisyuiClasses") {
    from(zipTree(kdaisyuiClasses.singleFile)) { include("kdaisyui-classes.txt") }
    into(layout.buildDirectory.dir("kdaisyui"))
}
```

```css
@import "tailwindcss";
@plugin "daisyui";
@source "./build/kdaisyui/kdaisyui-classes.txt";
@source "./src/main/kotlin";
```

Properties that decided it:

- **No new published artifact.** The list rides in the jar that consumers already depend on, so
  the release pipeline and its `verifyStagingComplete` guard are untouched. That guard exists
  because v0.1.1 shipped a partial bundle; adding a fourth artifact is exactly the kind of change
  it was written to catch, and there is no reason to provoke it.
- **Generated, committed, drift-checked** — the same treatment as every other generated artefact
  here, so it cannot silently fall behind the components.
- **Small.** A class list is text; the 400 KB is the *compiled CSS*, not the input.
- **Always correct.** It contains every class the library can emit, so no consumer can be caught
  out by a component they used but did not think to list.

**Rejected: a hand-maintained safelist as the documented optimisation.** It was the obvious way to
get from 400 KB to 32 KB, and it is the wrong instruction to publish. A list a human keeps in step
with their component usage is a rule that is followed until the day it is not, and its failure mode
is the same silent one this whole change exists to remove — a class quietly missing from the
stylesheet, with nothing failing.

**The narrowing is a tooling problem, and a separate change.** Getting from "every class the
library can emit" to "the classes this application actually uses" means knowing which generated
functions and which enum entries a codebase references. That is real machinery — KSP, or bytecode
analysis of the consumer's compiled output — and it is worth doing precisely because it cannot be
asked of a person. Recorded here as a follow-up rather than smuggled into this change.

**Consequence for section 5:** the docs offer one path, not two. 400 KB, correct, no list to keep.
The optimisation is named as future tooling, not as homework.

## 3.5 — one CI job needs Docker, not four

Measured with `koverVerify --dry-run`: the task graph contains no `:example-app` task at all, so
the `unit-tests` job (`:lib:test koverVerify koverXmlReport`) is unaffected. Same for
`generated-sources-drift` (`:lib:generate*`) and `api-baseline` (`:lib:checkKotlinAbi`).

| Job | Builds `:example-app` | Needs Docker |
|---|---|---|
| `unit-tests` | no | no |
| `generated-sources-drift` | no | no |
| `api-baseline` | no | no |
| **`e2e-tests`** | yes, via `:example-app:classes` | **yes** |

Blast radius is one job, not the four assumed when the plan was written. `compileTailwind` is
reached through `processResources`, which `classes` depends on.

**Assumed:** that `ubuntu-latest` provides a working Docker daemon to a plain `docker build` /
`docker run` from Gradle. It is documented to, but this repository has never used it. *Wrong if:*
the `e2e-tests` job fails on the pull request — which is the honest place to find out, because
`ci.yml` triggers on pull requests and pushes to `main`, so a topic-branch push proves nothing.
**Check this on the PR before adoption**, and add the ~15 s image build to the job's expected time.

## 4.3 — the consumer recipe, verified from the jar alone

Run as a consumer would: take the published jar, extract nothing else, compile.

1. `unzip -j kdaisyui-0.2.1.jar kdaisyui-classes.txt` → 560 lines. What the documented Gradle
   `Copy` task does.
2. A source file calling `daisyButton(variant = ButtonVariant.Primary, size = ButtonSize.Lg)` and
   `daisyCard(extraClasses = "max-sm:card-side")`. **`btn-primary` occurs 0 times in it.**
3. Compile with `@source` on the extracted list plus their own sources.

| Class | In their stylesheet |
|---|---|
| `.btn-primary` | PRESENT — reachable only through the class list |
| `.btn-lg` | PRESENT — same |
| `.max-sm\:card-side` | PRESENT — from their own source, and a prefix the webjar never ships |
| `.card` | PRESENT |

**552 KB**, more than the 400 KB estimated in 2.1: the class list is complete where a scan of the
generated Kotlin was not. The honest comparison for the documentation:

| Setup | Size | Correct? |
|---|---|---|
| Prebuilt webjar — today's documented path | 1123 KB | **no**, five variant prefixes only |
| Compile with the class list | 552 KB | yes |
| Compile with only what an application uses | ~32 KB | yes, but needs tooling that does not exist |

The documented path halves the payload *and* fixes correctness. The remaining 17× is the
follow-up tooling change, not homework for a reader.

## Method note

The `lg:` scenarios passing is what exposed the mistake. Had this change started by building the
Gradle task, the five working prefixes would have kept everything looking correct and the wrong
justification would have shipped in `README.md`. Writing the assertion first cost about twenty
minutes and replaced a false premise with a measured one.
