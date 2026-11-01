# Tasks

**Estimate, frozen 2026-10-31:** 310 minutes. Block 1: 40, block 2: 30, block 3: 120, block 4: 60,
block 5: 30, block 6: 30.

Ordered by uncertainty: section 1 settles whether the included build can be released at all, which
decides the plugin's place before any of it is written.

## 1. Open assumptions

- [x] 1.1 `. d` (A6, A7) Probe on a throwaway branch: an empty included build with
  `java-gradle-plugin` and `maven-publish`, staged into the root `build/staging-deploy` by `stageAll`.
  Record where the artifact and its marker land, and whether `verifyStagingComplete` can see both.
  If it cannot, revise the design before section 3.

## 2. The map, in the codegen

- [x] 2.1 `. F (internal)` The codegen writes `kdaisyui-components.txt` into the jar's resources: per
  component, its JVM classes and its CSS classes. Regenerated; the drift job covers it.

## 3. The plugin, under TCR

- [ ] 3.1 `. F (internal)` The included build `gradle-plugin/`, empty, built and measured.
- [x] 3.2 `. F` Find every `io/github/ollin/kdaisyui/components/` class name in a set of class files.
- [x] 3.3 `. F` Read the map, and select the CSS classes of the components found.
- [x] 3.4 `. F` Fall back to the full list, with a warning, on an unknown class or none found.
- [x] 3.5 `. F` The plugin registers `kdaisyuiClasses` over the main source set's classes and the
  `kdaisyui` artifact on the runtime classpath. Proven with `ProjectBuilder`, not TestKit: a TestKit
  build runs in another process, which the plugin's 100% Kover gate cannot measure; the real wiring
  is proven by `example-app` applying the plugin (4.1).

## 4. The repository uses it

- [x] 4.1 `. F (internal)` `example-app` applies the plugin; `app.css` says `source(none)` and sources
  the narrowed list. Measure the stylesheet against the full one.
- [x] 4.2 `. d` The full suite, E2E included, on the narrowed stylesheet. If a scenario fails,
  revise the change: the Assumed requirement was wrong.
- [x] 4.3 `! F (internal)` Release wiring: `publishedArtifacts` gains `kdaisyui-gradle-plugin` and
  `io.github.ollin.kdaisyui.gradle.plugin`; `stageAll` depends on the included build's
  `publishAllPublicationsToStagingRepository`; both publications carry the POM metadata and the plugin
  its sources and javadoc jars, which JReleaser's dry run requires (1.1).

## 5. Docs

- [x] 5.1 `. d` README's CSS section: the plugin first, the `Copy`-from-jar setup for builds
  without Gradle, both sizes.
- [x] 5.2 `. d` `kdaisyui-release` and AGENTS.md: the fourth artifact and its marker.

## 6. Gate and adoption

- [x] 6.1 Full green: `check` with E2E, `koverVerify`, PIT, `checkGradleProblems`, `analyze_change_set`.
- [x] 6.2 `openspec validate per-component-class-delivery --strict`.
- [x] 6.3 The evaluation under `tmp/per-component-class-delivery/` for Oliver to adopt.
