---
name: kdaisyui-release
description: >-
  Releasing, versioning or publishing kdaisyui - the JReleaser pipeline to Maven Central, what
  triggers it, the version scheme, and the guards that stop a partial upload. Read this before
  claiming anything about release automation in this repo.
---

# kdaisyui — Release & Publishing

## Releases are automated, and a git tag is the trigger

Pushing a tag matching `v*` runs `.github/workflows/release.yml`, which derives the version
from the tag name (`v0.1.2` → `0.1.2`) and runs `jreleaserFullRelease`. Nothing else releases;
there is no manual publish step and no release-please.

> An earlier version of this skill stated "there is no release automation". That was true of
> one long-lived branch and never of the project — the pipeline had been on `main` for months.
> If you find yourself about to write that sentence again, check `origin/main` first.

## What gets published

Three artifacts under `io.github.ollin.kdaisyui`, all listed in `build.gradle.kts` as
`publishedArtifacts`:

| Artifact | Module |
|---|---|
| `kdaisyui` | `:lib` |
| `kdaisyui-ktor-integration` | `:ktor-integration` |
| `kdaisyui-bom` | `:bom` — a `java-platform` BOM that pins the other two |

Consumers import the BOM once and then declare the artifacts without versions; that is what
`README.md` shows.

## The pipeline, in order

1. `stageAll` publishes every subproject into `build/staging-deploy/`.
2. `verifyStagingComplete` fails the release if any of the three artifacts is missing a `.pom`
   for **this** version.
3. JReleaser signs (GPG, armored), deploys to Maven Central via
   `https://central.sonatype.com/api/v1/publisher`, and creates the GitHub release with a
   changelog generated from Conventional Commits.

Steps 1 and 2 exist because of a real incident: **in 0.1.1 only the BOM reached Central.**
Gradle was free to interleave `jreleaserDeploy` with the slow `:lib` publish, and
`applyMavenCentralRules` validates POM shape, not module count, so a valid-but-partial bundle
uploaded cleanly. Maven Central publishes a version exactly once, so this is unrecoverable
without burning a version number. Do not remove those task dependencies.

## Versioning

`gradle.properties` holds one line, `version=0.1.0`, and JReleaser tags the git history with
it. Everything else — dependencies, plugins, tooling, submodule tags — lives in
`gradle/libs.versions.toml`.

The scheme is plain SemVer and is **not** tied to the DaisyUI version. A DaisyUI bump that adds
components is a `minor`; the wrapped DaisyUI version is recorded in the JAR manifest as
`DaisyUI-Version`, not in the project version.

The release workflow passes `-Pversion` from the tag, so the value in `gradle.properties` is
what local builds and `publishToMavenLocal` use.

## Local publishing

`just build` runs `:lib:publishToMavenLocal`, which is how `example-app` and downstream
experiments consume the library. The `staging` repository in each module's `publishing` block
is a local directory, not a remote — the only remote upload path is JReleaser.

## Secrets the release needs

`JRELEASER_GPG_PASSPHRASE`, `JRELEASER_GPG_PUBLIC_KEY`, `JRELEASER_GPG_SECRET_KEY`,
`JRELEASER_MAVENCENTRAL_USERNAME`, `JRELEASER_MAVENCENTRAL_PASSWORD`, plus the workflow's
`GITHUB_TOKEN`. A missing secret fails the release rather than publishing unsigned.

## What CI enforces before any of this

`ci.yml` runs three jobs on pushes and PRs to `main`: `generated-sources-drift`, `unit-tests`
(including the aggregated 100% `koverVerify` gate) and `e2e-tests`.
`pr-conventional-commits.yml` validates PR titles — and that matters more than usual here,
because JReleaser builds the release changelog from those commit messages.

**Known gap:** `ci.yml`'s `pull_request` trigger does not currently fire on this repository.
Only the `pull_request_target`-based title check runs on a PR. CI gates pushes to `main` but
not pull requests into it. Diagnose that before relying on PR checks.
