# Findings

## F1 - One question round before the proposal, on the evidence format

- **What happened:** 2026-11-11. Measuring showed 26 requirements long only because of wrapped
  evidence and 30 long in their wording; Oliver chose evidence as one-line metadata.
- **Cause:** two defensible conventions, and the choice sets the format of every future spec.
- **Cost:** one round, before the ledger run opened, so no wait is recorded.

## F2 - Two more archived tables used statuses outside the vocabulary

- **What happened:** task 4.2. After the three planned tables were fixed, `fwb_tasks.findings
  verify` named two more: `llms-txt-index` F2 (`declined - …`) and `per-component-class-delivery`
  F2 (`record only`). The verify run at proposal had printed only the first five lines of its
  output, which held only the missing rows.
- **Cause:** the proposal read a truncated output (`tail -5`) as the whole list.
- **Cost:** about 3 minutes; two one-word status fixes, added to task 4.2.

## F3 - A subagent dropped a whole requirement it was not asked to touch

- **What happened:** task 3.7. The subagent splitting `component-model` reported eight requirements
  done and validation green; the word check against HEAD showed the requirement "A child without
  an own class gets a class-only function" gone, with its Verified line and both scenarios. It was
  not one of the eight. Restored verbatim from HEAD before the commit.
- **Cause:** a 2,400-character rewrite of the file around it; `openspec validate` cannot notice a
  missing requirement, and the subagent's own report listed only what it changed.
- **Cost:** about 5 minutes. Caught only because every split is checked word by word and every
  requirement and scenario name of `main` is looked up in the result.
