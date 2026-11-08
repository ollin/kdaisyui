# Tasks

**Estimate, frozen 2026-11-07:** `2h 40min (4h 55min DevOnly)` by the ledger's `forecast`
(ratio 0.53 over 153 runs); 295 minutes developer-only is the frozen raw figure. Block 1: 35,
block 2: 40, block 3: 45, block 4: 90, block 5: 45, block 6: 20, block 7: 20. The review of
the same day split task 4.1, added the version-mismatch warning and the stale references
(finding F2); the figure stands, the miss is recorded.

Markers re-planned 2026-11-08 by counted lines (`planning-rules-from-popover-anchoring`): the
steps estimated above eight counted lines, test included, are `!`; finding F3.

Ordered by uncertainty: A2 can sink the plan (a plugin that does not load in a consumer) and
is settled before a line of the task exists; A8, A9 and A10 were decided by Oliver on
2026-11-07 as recommended; A6 shapes how the task is split.

## 1. Open assumptions

- [x] 1.1 `^ F (internal)` (A2) `testcontainers` in the catalog and the plugin's
  `implementation`, the `ProjectBuilder` tests still green; then `publishToMavenLocal` and a
  scratch consumer in `/tmp/opencode/` that applies the plugin from `mavenLocal()` and runs
  `kdaisyuiClasses`. Record in `proposal.md` A2 and `findings.md` whether the plugin loads with
  Testcontainers on its classpath beside Gradle's own libraries.
- [x] 1.2 `. d` (A6, A7, A11) Read `css-delivery`, `coverage-enforcement` and `mutation-testing`
  against a task that starts a container; decide and record whether `NodeContainerRun` is
  excluded from PIT's target classes with its reason, as `lib/build.gradle.kts` does for the
  bridges, since every mutant of it would need a container run. Record in `proposal.md`.
- [x] 1.3 `. d` Revise this change after `csp-safe-popover-anchoring` (its finding F7): that
  change ships no `kdaisyui.css`. Drop the `@import "../kdaisyui/kdaisyui.css"` from the design
  (lines 97-98) and the `kdaisyui.css` sentence from the proposal (lines 54-55); the compile copies
  only the class list.

## 2. The versions, under TCR in `gradle-plugin`

- [x] 2.1 `! F (internal)` `processResources` expands `kdaisyui-versions.properties` with the
  three versions from the catalog and `../.tool-versions`; a test reads the built resource.
- [x] 2.2 `! F` `KdaisyuiVersions.tailwind`, `.daisyui`, `.node` read it.
- [x] 2.3 `! F` The plugin warns when the `kdaisyui` jar it reads the component map from
  carries a version other than its own, naming both; a test with a renamed jar.

## 3. The configuration, under TCR

- [x] 3.1 `! F` `kdaisyui { stylesheet { entry; sources; output; nodeImage; npmRegistry;
  npmUser; npmPassword; minify } }`, with the defaults the design names; `minify` on (A10).
- [x] 3.2 `! F` `NpmConfig.npmrc(registry, user, password)`: the registry line alone, or with
  `_auth` when both are given; a half credential throws, naming the missing half. One step with
  3.3: the condition "both given" has a branch "only one", which Kover's 100% branch gate refuses
  to leave untested (finding F7).
- [x] 3.4 `! F` `StylesheetCompile.command(versions, minify)`: the install of the three pinned
  packages with `--prefer-offline`, then the compile, `--minify` when asked.
- [x] 3.5 `! F` `StylesheetCompile.mountPoints(sources)`: each source directory at
  `/deps/project/sources/<index>-<last segment>`, so two directories with one name cannot
  collide.

## 4. The task, under TCR

- [x] 4.1 `! F` `KdaisyuiStylesheet`, a `@CacheableTask`: inputs (entry, sources, the class-list
  directory, image, versions, registry, minify), output; its action builds the
  `StylesheetCompile` and fails on a half credential before any container; the test needs no
  Docker.
- [x] 4.2 `! F` `NodeContainerRun.compile(StylesheetCompile)`: copies the inputs in, runs the
  command one-shot, copies the stylesheet out. One test compiles a two-line entry with one
  source directory in a real container and reads `--color-base-100` in the output; it needs
  Docker and the network, as `check` already does for the example app.
- [x] 4.3 `^ F` The npm cache directory is the named volume `kdaisyui-npm-cache` (A8).
- [x] 4.4 `! F (internal)` The plugin registers `kdaisyuiStylesheet` beside `kdaisyuiClasses`,
  wired lazily from the extension and depending on `kdaisyuiClasses` through its output; a
  `ProjectBuilder` test reads the wiring. Registered always, not "when the entry is set": that
  would need `afterEvaluate`, and without an entry Gradle already fails the task naming `entry`
  (finding F8).

## 5. The example application

- [x] 5.1 `! F` `example-app/build.gradle.kts` configures the extension and `processResources`
  takes the task's output; `app.css` uses the design's mount points; E2E green.
- [x] 5.2 `^ r` Delete `tailwind/Dockerfile`, `buildTailwindImage`, `compileTailwind` and
  `callingUserArgs`.
- [ ] 5.3 `. d` The five references to the deleted task follow: `app.css:2-6`,
  `Assets.kt:9`, `Application.kt:21`, `.opencode/skills/kdaisyui-testing/SKILL.md:218`, and
  `ci.yml:274`'s comment.
- [ ] 5.4 `. d` Record the compile time before and after, cold and with the cache volume, in
  `findings.md`; the design's figures are estimates until then.

## 6. Docs

- [ ] 6.1 `. d` README, "Give those classes some CSS": the task and its configuration first,
  the mirror settings, the volume and how to remove it, that a further `@plugin` of the
  consumer's own is not installed, and the manual route as the alternative with repository
  paths.

## 7. Gate, adoption and archive

- [ ] 7.1 Full green: `check` with E2E and the E2E result files asserted on disk,
  `koverVerify` of the root and of `gradle-plugin`, `:gradle-plugin:pitest`,
  `analyze_change_set`.
- [ ] 7.2 `openspec validate compile-the-stylesheet-in-the-plugin --strict`.
- [ ] 7.3 The evaluation under `tmp/compile-the-stylesheet-in-the-plugin/` for Oliver to adopt.
- [ ] 7.4 After adoption: archive on this branch, `. d (internal)`, `tmp/` of the change deleted.
