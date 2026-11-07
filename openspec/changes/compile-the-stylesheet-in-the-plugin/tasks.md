# Tasks

**Estimate, frozen 2026-11-07:** 295 minutes developer-only, `2h 40min (4h 55min DevOnly)` by
the ledger's `forecast` (ratio 0.53 over 153 runs). Block 1: 25, block 2: 30, block 3: 45,
block 4: 90, block 5: 45, block 6: 20, block 7: 40.

Ordered by uncertainty: A2 can sink the plan (a POM that does not resolve) and is settled
before a line of the task exists; A6 shapes how the task is split, so it is read before block 4.

## 1. Open assumptions

- [ ] 1.1 `. d` (A2) Add `testcontainers` to the catalog and the plugin's `implementation`,
  `publishToMavenLocal` the plugin, resolve it from a scratch consumer in `/tmp/opencode/`
  with `mavenLocal()`; record whether the dependency is on the consumer's build classpath.
- [ ] 1.2 `. d` (A6, A7) Read `css-delivery`, `coverage-enforcement` and `mutation-testing`
  against a task that starts a container, and count the branches the design's split leaves in
  the thin method; record.

## 2. The versions, under TCR in `gradle-plugin`

- [ ] 2.1 `^ F (internal)` `processResources` expands `kdaisyui-versions.properties` with the
  three versions from the catalog and `.tool-versions`; a test reads the built resource.
- [ ] 2.2 `^ F` `KdaisyuiVersions.tailwind`, `.daisyui`, `.node` read it.

## 3. The configuration, under TCR

- [ ] 3.1 `^ F` `kdaisyui { stylesheet { entry; sources; output; nodeImage; npmRegistry;
  npmUser; npmPassword; minify } }`, with the defaults the design names.
- [ ] 3.2 `^ F` `NpmConfig.npmrc(registry, user, password)`: the registry line alone, or with
  `_auth` when both are given.
- [ ] 3.3 `^ F` A half credential throws, naming the missing half.
- [ ] 3.4 `^ F` `StylesheetCompile.command(versions, minify)`: the install of the three pinned
  packages with `--prefer-offline`, then the compile, `--minify` when asked.

## 4. The task, under TCR

- [ ] 4.1 `! F` `KdaisyuiStylesheet`: inputs (entry, sources, the class-list directory, image,
  versions, registry), output; `NodeContainerRun.compile` copies in, runs one-shot, copies out.
  One test compiles a two-line entry in a real container and reads `--color-base-100` in the
  output.
- [ ] 4.2 `^ F` The npm cache directory is the named volume `kdaisyui-npm-cache`.
- [ ] 4.3 `^ F (internal)` The plugin registers the task, depending on `kdaisyuiClasses`, when
  the extension's entry is set; a `ProjectBuilder` test reads the wiring, and one that it is
  absent without an entry.

## 5. The example application

- [ ] 5.1 `^ F` `example-app/build.gradle.kts` configures the extension and `processResources`
  takes the task's output; `app.css` uses the design's mount points; E2E green.
- [ ] 5.2 `^ r` Delete `tailwind/Dockerfile`, `buildTailwindImage`, `compileTailwind` and
  `callingUserArgs`.
- [ ] 5.3 `. d` Record the compile time before and after, cold and with the cache volume, in
  `findings.md`.

## 6. Docs

- [ ] 6.1 `. d` README, "Give those classes some CSS": the task and its configuration first,
  the mirror settings, the volume, the manual route as the alternative with repository paths.
  `ci.yml`'s comment on the Docker compile.

## 7. Gate and adoption

- [ ] 7.1 Full green: `check` with E2E, `koverVerify` of the root and of `gradle-plugin`,
  `:gradle-plugin:pitest`, `analyze_change_set`.
- [ ] 7.2 `openspec validate compile-the-stylesheet-in-the-plugin --strict`.
- [ ] 7.3 The evaluation under `tmp/compile-the-stylesheet-in-the-plugin/` for Oliver to adopt.
