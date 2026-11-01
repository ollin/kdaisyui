# Tasks

The work came before this record: Oliver asked for the move directly, and a main spec named
`README.md`, so the change exists to carry the spec deltas. The estimate was frozen in the ledger
before the first edit, as 50 minutes across the three sections below.

## 1. Move

- [x] 1.1 `. d` Move every *How to migrate* section to `docs/upgrading.md`; README keeps a pointer
  and a *Quick links* entry; `codegen/llms-template.md` links the guide; regenerate `llms.txt`.
- [x] 1.2 `. F (internal)` `doc-snippets` compiles `docs/upgrading.md`.

## 2. Repoint the rule

- [x] 2.1 `^ d` `AGENTS.md`, the release, codegen and upgrade skills, `/daisyui-upgrade` and the
  `ci.yml` comment name `docs/upgrading.md`.
- [x] 2.2 `. F (internal)` The `checkComponentApi` failure message names `docs/upgrading.md`.

## 3. Gate

- [x] 3.1 `./gradlew check`, `just generate` without a diff, `openspec validate --strict`.
