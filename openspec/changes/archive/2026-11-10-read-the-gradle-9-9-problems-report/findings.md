# Findings

## F1 - The first boundary step was reverted for an uncovered branch

- **What happened:** task 1.1, 2026-11-10. `summaryStart.find(report)?.let { … } ?: report` added a
  branch for a report with neither marker, which no test reaches; `koverVerify` failed at 99.98 %
  branch coverage and TCR reverted. `report.split(summaryStart, limit = 2).first()` states the same
  without a branch and went green.
- **Cause:** a fallback written for a case the reader never meets.
- **Cost:** one TCR run, about 2 minutes.

## F2 - The same trap again: a safe call before an elvis is a branch no test reaches

- **What happened:** task 1.2, 2026-11-10. `anchor.find(entry)?.groupValues[1] ?: nameOf(entry)`
  compiles to two null checks; the second, "the group is null", is unreachable, so `koverVerify`
  failed and TCR reverted. `anchor.findAll(entry).map { it.groupValues[1] }.firstOrNull() ?: …`
  has one null check, both sides covered, and went green.
- **Cause:** the same kind of hidden branch as F1, and close kin of the dense-when trap that
  `kdaisyui-testing` names; the skill does not name the `?.` + `?:` shape.
- **Cost:** one TCR run, about 2 minutes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F1 | - | none - record only; `koverVerify` is the check, and it caught it before a commit |
| F2 | `kdaisyui-testing` names the `?.` + `?:` shape beside the dense-when trap | fixed - in this change, a table in `.opencode/skills/kdaisyui-testing/SKILL.md` (Oliver 2026-11-10: adopted, made clearer). The skill had named the first row already and recommended `m?.groupValues[1]`, without saying that an `?:` behind it brings the dead branch back. A skill sentence: no check can see an unreachable branch before Kover does |
