## Why

The migration guides took about 590 of README's 873 lines. Moving *Quick start* ahead of them
(PR #437) helped a reader who starts at the top, but README stayed mostly a migration archive, and
every release with a breaking change makes it longer. Oliver asked on 2026-11-01 for the guides to
live in their own file under `docs/`, with README only pointing at it.

## What Changes

- **New** `docs/upgrading.md` holds every *How to migrate from …* section, unchanged apart from
  one heading level. README keeps a short *Upgrading* section and a *Quick links* entry that point
  at it.
- `doc-snippets` compiles `docs/upgrading.md`, so its snippets stay checked as they were in README.
  Every one of them carries the same `skip` marker it had there.
- `llms.txt` links the guide. The index generator would refuse to run otherwise, because every
  `docs/**/*.md` page must be linked.
- The rule "a breaking change needs a **How to migrate** entry" now names `docs/upgrading.md` in
  `AGENTS.md`, the `kdaisyui-release`, `kdaisyui-codegen` and `kdaisyui-daisyui-upgrade` skills, the
  `/daisyui-upgrade` command, the `ci.yml` comment and the `checkComponentApi` failure message.

**Subtractive option considered:** delete the guides for releases nobody runs any more. Rejected:
the guides cost nothing in their own file, and a consumer jumping from 0.3 to 0.9 needs all of
them.

**Out of scope:** the historical quotations that say "README.md", such as v0.2.0's release body in
`kdaisyui-release` and the archived changes. They describe what was true then.

**Not breaking.** No published artifact changes.

## Capabilities

### New Capabilities

<!-- none -->

### Modified Capabilities

- `api-change-detection`: the migration entry lives in `docs/upgrading.md`.
- `component-model`: two provenance citations point at the guide's new home.
- `documentation-snippets`: `docs/upgrading.md` joins the compiled documents.

## Impact

- **New**: `docs/upgrading.md`.
- **Edited**: `README.md`, `codegen/llms-template.md`, `llms.txt` (regenerated),
  `doc-snippets/build.gradle.kts`, `codegen/src/index-component-api.ts` (one message),
  `.github/workflows/ci.yml` (one comment), `AGENTS.md`, three skills and one command.
