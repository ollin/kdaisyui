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
