## MODIFIED Requirements

### Requirement: A breaking change carries a migration note before release
A public API change identified as breaking SHALL have a **How to migrate** entry in
`docs/upgrading.md` before the release that ships it, and the release documentation SHALL treat the API check and
that entry as part of what "ready to tag" means.

**Verified:** `README.md` already carries a "How to migrate" section for 0.2.0, written after the
fact, which is the shape the rule generalises. **Assumed:** that this remains a documented rule
rather than an automated one. *Wrong if:* a release ships a breaking change without the entry,
which would show that a rule nothing enforces is not a rule.

The entries lived in `README.md` until `move-migration-guides` (2026-11-01) moved them to
`docs/upgrading.md`, which README links.

#### Scenario: A reader of a breaking release can act on it
- **WHEN** a release removes or changes a public declaration
- **THEN** `docs/upgrading.md` explains what to change at the call site
- **AND** the entry exists before the tag is pushed, not after
