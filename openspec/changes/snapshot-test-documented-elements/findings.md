# Findings

## F1 - test-generator.ts drops from 418 survivors to 104

- **What happened:** task 4.1. The snapshot test over all 68 pages (160 ms) took the file from
  62.1 % to 80.5 %; moving the runner out took it to 89.5 % (878 killed, 7 timeouts, 104 survived).
  The rest are mostly the coverage half's regex tolerance recorded in
  `snapshot-test-coverage-generator` (F2), plus documented-half branches no pinned page reaches.

## F2 - The documented-element half had its own path to the DaisyUI pages

- **What happened:** task 2.1. `DOCS_DIR` resolved `../../daisyui/…` from the source file, a second
  copy of what `frontmatter.ts` already finds through `submodules.ts`. Replaced with
  `readComponentPage` and `parseFrontmatter`; the copy is gone.

## F3 - Two arguments a skipped page could not use

- **What happened:** task 4.1. A skipped page passed `classTests` its page name, used only to put
  its own model first (it has none), and an empty main-function name (no function has one). Their
  mutants survived because nothing could depend on them. `classTests` now takes the ordered models
  and an optional main name, and the skipped page passes neither.

## F4 - The runner lived in the generator module

- **What happened:** task 4.1, as with the icon test generator. Arguments, file reading and writing
  and `main()` moved to `index-test-generator.ts`; `test-generator.ts` now holds only functions of
  their inputs, and no longer runs anything when executed.

## Proposals

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - the remaining survivors are recorded; the gate covers changed lines |
| F2-F4 | - | none - fixed in this change |
