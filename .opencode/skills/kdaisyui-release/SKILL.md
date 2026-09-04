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

`gradle.properties` holds one line, the project version. Everything else — dependencies,
plugins, tooling, submodule tags — lives in `gradle/libs.versions.toml`.

**The tag governs, not the file.** `release.yml` triggers on a `v*` tag and passes
`-Pversion` derived from the tag name, which overrides `gradle.properties`. That file is the
version for local builds and `publishToMavenLocal`, plus a declaration of intent for the next
release — which is why v0.1.1 and v0.1.2 both shipped while it still read `0.1.0`. Do not
trust it to tell you what is on Maven Central; check the releases.

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

## A conflicted PR gets no CI at all — and looks clean doing it

`pull_request` workflows run against the PR's **merge commit**. When the head branch conflicts
with the base, GitHub cannot compute that commit and **silently skips those workflows**. The
checks are not red; they are absent.

`pull_request_target` workflows are unaffected, because they run from the base branch. So a
conflicted PR shows exactly one green check — `Validate PR title` — and no sign that
`unit-tests`, `e2e-tests` and `generated-sources-drift` never ran.

Verified 2026-08-15: PRs #229 and #231 had `mergeable_state: "dirty"` and ran only the title
check, while #230 from Renovate ran the full suite on the same workflow file.

**Before trusting a PR's checks, confirm it is mergeable.** "No red checks" and "the checks
passed" are different statements, and this is the case where they come apart.

## A breaking API change needs a migration note before the tag

`lib`'s public API is committed at `lib/api/lib.api` and gated by the `api-baseline` CI job. A
change to it therefore cannot arrive silently — but a *failing gate* only says the surface moved,
and a consumer needs to be told what to do about it.

So: **a breaking API change requires a "How to migrate" entry in `README.md` before the release
that ships it.** The 0.2.0 entry is the shape to copy; it was written after the fact, from
recollection, which is the habit this rule replaces.

"Ready to tag" therefore means, in addition to everything else on this page:

- `:lib:checkKotlinAbi` is green, or the baseline was re-dumped **deliberately** with
  `just update-api` and the diff was read;
- every removal or signature change in that diff is either non-breaking or has its `README.md`
  migration entry;
- the PR body's `BREAKING CHANGE:` footer points at that entry — see the next section for why
  its position matters.

Why the API check and the migration note are one rule rather than two: the check is what makes
the breaking change *knowable* before release, and the note is the only part a consumer ever
sees. Either alone leaves the 0.2.0 failure mode intact — that break was found by a human
reading a diff, and documented afterwards.

## The PR body becomes the release notes — put `BREAKING CHANGE:` last

The merge commit's message is what JReleaser renders on the release page, so the PR body is
release-notes copy, not review chatter.

One rule decides whether it reads well: **`BREAKING CHANGE:` must be the last paragraph.** Per
the Conventional Commits spec the footer runs to the end of the body, so everything after it —
blank lines, further paragraphs, unrelated notes — is absorbed into the migration note. Nothing
terminates it, and no changelog configuration repairs it afterwards.

Worked example, v0.2.0. The body put a branch summary after the footer:

```
BREAKING CHANGE: TooltipVariant.Neutral is removed; DaisyUI dropped
tooltip-neutral. See "How to migrate" in README.md, …

Four OpenSpec changes: commit-generated-sources, cleanup-dead-code,
rejoin-main, adapt-daisyui-5-6.
```

which rendered as a single italic blob spanning six lines of the release page, announcing that
four OpenSpec changes were a breaking change. The fix is to swap the two paragraphs; there was
never a setting for it.

The rule is stated once, globally, in the `risk-aware-commits` skill
(`references/mr-conventional-commits.md`) — this is the local reminder, because in this
repository the consequence is a published artifact page rather than a commit message.
