---
name: kdaisyui-release
description: >-
  Releasing, versioning or publishing kdaisyui - what the release setup actually is (and what
  it is not), the version scheme, and what CI does and does not do. Read this before claiming
  anything about release automation in this repo.
---

# kdaisyui — Release & Publishing

## There is no release automation

Older documentation in this repo described a release-please pipeline. **None of it exists.**
Verified 2026-07-28:

| Previously documented | Actual |
|---|---|
| `release-please-config.json` | absent |
| `.release-please-manifest.json` | absent |
| `.github/workflows/release-please.yml` | absent |
| `.github/workflows/publish.yml` | absent |

`.github/workflows/` contains exactly three files: `ci.yml`, `pr-conventional-commits.yml`,
`renovate.yml`. **No workflow publishes anything.**

Consequence: do not "fix" version handling on the assumption that release-please owns
`gradle.properties`. Nothing owns it. A version bump is a manual edit.

## Versioning

`gradle.properties`:

```
daisyui.version=5.5.20      # single source of truth — see kdaisyui-codegen for the ceiling
heroicons.version=2.2.0
version=5.5.20-SNAPSHOT     # <daisyui.version>-<local revision>
```

Scheme is Debian-style `<daisyui-version>-<local-revision>`, e.g. `5.5.20-1`. The project
version tracks the DaisyUI base version it wraps, so bumping `daisyui.version` means bumping
`version` in the same commit.

`lib/build.gradle.kts` uses `-Pversion` when supplied, else falls back to `0.0.1-SNAPSHOT` —
note that fallback is *not* the `gradle.properties` value's shape, so always pass `-Pversion`
explicitly when publishing.

## Publishing — manual only

`lib/build.gradle.kts` carries a complete, Maven-Central-shaped `publishing` block:

- `groupId` `io.github.ollin.kdaisyui`, `artifactId` `kdaisyui`
- `sourcesJar` + `javadocJar` + full POM (licence, developer, SCM)
- repository `GitHubPackages` → `https://maven.pkg.github.com/ollin/kdaisyui`
- credentials from `GITHUB_ACTOR` / `GITHUB_TOKEN`
- JAR manifest embeds `SCM-Revision`, `DaisyUI-Version` and `Kotlin-Version`

So publishing is a deliberate `:lib:publish` with those environment variables set. Nothing
triggers it automatically.

`just build` additionally runs `:lib:publishToMavenLocal`, which is how the example app and
downstream experiments consume the library locally.

**README says "not yet published" — that is the accurate claim.** Do not change it to imply a
released artifact exists on GitHub Packages until one actually does.

## What CI does enforce

`pr-conventional-commits.yml` validates PR titles against the Conventional Commits spec. It is
real, it is enforced, and it is the only release-adjacent automation present. Keep PR titles
conforming.

`ci.yml` runs unit and E2E tests on pushes and PRs to `main`. It does not build or upload
artifacts.

## If you set up release automation

That is a feature change, not a documentation fix. It would make `version=` release-please
owned, at which point the manual-bump rule above becomes wrong and this skill must be updated
in the same change.
