# Findings

## F1 - The first boundary step was reverted for an uncovered branch

- **What happened:** task 1.1, 2026-11-10. `summaryStart.find(report)?.let { … } ?: report` added a
  branch for a report with neither marker, which no test reaches; `koverVerify` failed at 99.98 %
  branch coverage and TCR reverted. `report.split(summaryStart, limit = 2).first()` states the same
  without a branch and went green.
- **Cause:** a fallback written for a case the reader never meets.
- **Cost:** one TCR run, about 2 minutes.
