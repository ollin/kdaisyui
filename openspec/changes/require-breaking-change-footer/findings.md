# Findings

## F1 - Two releases since v0.9.0 broke the release-note rule, not one

- **What happened:** checking A3 found #492 (v0.11.0) besides #471 (v0.9.0): a signature change in
  `lib/api/lib.api` with a migration entry, under a title without `!`, so the release page did not
  mark it breaking.
- **Cause:** the rule lived in a skill read at tag time; nothing checked a pull request.
- **Cost:** none in this change; it widened the gate from "footer on `!` titles" to "a removed API
  line also requires `!`".
