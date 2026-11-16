## Why

Finding F1 of `require-breaking-change-footer`, adopted by Oliver on 2026-11-16. The required
check makes a breaking pull request carry its footer, but the release page still depends on that
footer's wording for a reader to find the migration guide. A fixed link after every 🚨 line costs
nobody anything to remember and survives a footer that names its heading in prose.

## What Changes

- The JReleaser changelog line of a breaking commit ends with
  `— [How to migrate](https://github.com/ollin/kdaisyui/blob/main/docs/upgrading.md)`.
- `gradle.properties` declares 0.12.1, the patch release that ships it together with the gate.

**Subtractive option:** none removes the need; the footer stays required, the link is the floor
under it. **Out of scope:** an anchor to the exact section, which the template cannot compute (A1).

## Capabilities

### New Capabilities

None.

### Modified Capabilities

- `api-change-detection`: a breaking release-note line links the migration guide.

## Impact

`build.gradle.kts` (the changelog `format`), `gradle.properties`. No library code.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | A commit's changelog template can name the release's tag | code | false - a clone of v0.9.0 with the line added, `jreleaserChangelog --rerun -Pversion=0.9.0`: `{{tagName}}` and `{{projectVersion}}` both render empty there. The link points at `main` instead, whose `docs/upgrading.md` keeps every section (finding F1) |
| A2 | Only the 🚨 line gains the link | code | confirmed at proposal - the same run: 1 of 43 changelog lines carries it, the line of #471 |
| A3 | No requirement of `api-change-detection` contradicts it | code | confirmed at proposal - its two release requirements ask for the migration entry and the footer, which this keeps |
