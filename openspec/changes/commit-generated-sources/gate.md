## What are we deciding

Whether committing the generated Kotlin into the repository — decoupling the build from the
Node generators and adding a CI drift check — is work whose answer we can look up and
measure, or work whose answer only appears once we live with it.

## Evidence for COMPLICATED

| What we would need to know | Source | Cost to fetch |
|---|---|---|
| Where generated output goes and how the build is coupled to the generators | `lib/build.gradle.kts:125-201` — source roots at 128-135, the three `Exec` tasks at 149-192, the `compileKotlin`/`compileTestKotlin` dependencies at 194-201 | already read this session, free |
| How much output there is | measured: 63 components and 63 tests under `lib/build/generated/sources/kotlin/{main,test}/…/components/`; icons claimed 324, not re-counted | seconds (`./gradlew :lib:compileTestKotlin`, then count) |
| Whether regeneration is byte-identical on one machine | run `:lib:generateComponents --rerun` twice into two directories and diff | ~2 min |
| Whether it is byte-identical across machines and Node versions | the same regeneration on the CI runner, compared against a locally produced baseline — this is exactly the drift job the change proposes, so building it *is* the measurement | one pipeline run |
| Which constructs in the generators vary run-to-run | read all 12 files in `codegen/src/`; the sorting sites are already located — `frontmatter.js:218-223`, `index-heroicons.js:47`, `generator-new.js:81,344-348`, `generator.js:216-219`, `classifier.js:102`, `classify.js:58-60`, `test-generator.js:219,263`, and the one unsorted `readdirSync` at `parser/svg-heroicons.js:48` | ~1h, partly done |
| Whether `localeCompare` is environment-dependent | ECMA-402: `String.prototype.localeCompare` with no locale argument uses the host default locale. Empirically checkable by regenerating under two `LANG` values | ~15 min |
| What Gradle needs to treat a committed directory as a source root | Gradle docs; the mechanism is already used at `lib/build.gradle.kts:128-135`, only the path changes | known, standard |
| Whether CI catches drift today | `.github/workflows/ci.yml` — two jobs, `unit-tests` and `e2e-tests`, both `submodules: recursive`, neither regenerates and diffs | read, free |
| How `linguist-generated` behaves in review | GitHub docs on `.gitattributes` | ~15 min |

Every mechanical question here has a nameable source and a bounded cost. Two facts already
fell out of the lookup and are worth recording, because they change the work:

- **The import comparator does misfire, and worse than the proposal says.**
  `generator-new.js:344-348` branches on `a.startsWith('kdaisyui')`, but every emitted import
  begins `io.github.ollin.kdaisyui…` or `kotlinx.html…`, so neither branch is ever taken and
  everything falls through to `a.localeCompare(b)`. The proposal calls that "byte order"; it
  is not. It is *locale-collated* order, which depends on the machine's default locale — so
  the misfire is not merely cosmetic grouping, it is a candidate source of cross-machine
  drift, which is the one property the CI job depends on. `generator.js:216-219` repeats it.
- **`codegen/package-lock.json` is committed** (since `c7b85cc`), so the `^4.1.0` range on
  `js-yaml` does not float. One less variance source than feared.

## Evidence for COMPLEX

The mechanics are answerable. What the change is *for* is not.

- **"A DaisyUI bump becomes reviewable" is a claim about human attention, and no document
  settles it.** The bump would produce a diff in the thousands of lines across ~450 files.
  Whether a human reads that, or waves it through the way everyone waves through a lockfile,
  is not in any manual. If it is waved through, the change has bought repository weight and
  a CI job while leaving the actual problem — nobody sees what a version bump did to the API
  — exactly where it was. The proposal's second and strongest justification rests entirely
  on this.
- **Whether the drift check becomes a gate or a nuisance emerges from use.** One green CI run
  proves determinism once. It does not tell us how the job behaves over months, across a
  contributor's machine, a Node minor bump, or a locale — and a check that fails for reasons
  nobody caused is a check people learn to bypass. Determinism is measurable; *durable*
  determinism is a track record, and we do not have one.
- **Nothing in this repository has been lived with this way.** There is no committed generated
  output anywhere today, and no `regenerate-and-diff` recipe in the `justfile` (only
  `generate` and `generate-heroicons`). We have no local experience of the review or merge
  ergonomics to reason from.

**The honest subtractive alternative has not been ruled out.** The third justification — "the
port has no safety net" — does not actually require committing anything. A baseline can be
captured as a throwaway copy of `lib/build/generated/**`, and the port proved against it by
diffing, without ~450 files entering git history permanently. If the port is the real reason,
that is a smaller change; the other two reasons then have to carry the full cost on their own.

## Verdict

<!-- AWAITING OLIVER. The agent presents; the human decides. -->

**Domain:**
**Decided by:**
**Date:**
**Reasoning:**

<!-- Agent's read, offered as input and not as the decision:

The change bundles three justifications with different domains, and that is the thing to
decide about rather than the change as a whole.

  (1) "the API is invisible"     — complicated; committing files demonstrably fixes it.
  (2) "a bump is unreviewable"   — complex; rests on whether anyone reads a 5-figure diff.
  (3) "the port has no baseline" — complicated, AND achievable without committing anything.

So the split that suggests itself: (1) and (3) are lookup-and-measure work, and (3) may not
need this change at all. (2) is the part with no source, and it is also the part that
justifies the permanent cost.

I lean complicated for the change as scoped, because the enabling half is genuinely
mechanical and the determinism question is measurable in minutes rather than months. But I
hold that loosely: the reviewability claim is doing most of the persuasive work in the
proposal, it has no source, and I notice the proposal was written by me. If the verdict is
complicated, the design should still name (2) as an assumption being taken on faith rather
than dressing it as a requirement — the provenance rule forbids Assumed wearing a SHALL.
-->

## Re-entries

<!-- Append dated entries when the reading changes. A spec-driven change that stalls
     because the information turned out not to exist is DEMOTED to a probe; a probe
     that reveals a stable, repeatable pattern is PROMOTED to a spec. Neither is a
     failure — being unsure at the start is the normal case. -->
