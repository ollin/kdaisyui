## Estimate

Frozen 2026-11-16, raw developer-only minutes. Ledger run
`2026-11-16-kdaisyui-link-migration-guide-in-release-notes`. Forecast: 45min (1h 20min DevOnly).
The probe behind A1 and A2 ran before the estimate and is excluded.

| Block | Estimate |
|---|---|
| 1. The changelog line | 20m |
| 2. Declare 0.12.1, archive | 20m |
| 3. Pull request and release | 40m |
| **Total** | **80m** |

## 1. The changelog line

- [x] 1.1 Append the link to the breaking line in `build.gradle.kts`'s changelog `format`; re-run
  `jreleaserChangelog --rerun` on the current range and record that no line changed (no breaking
  commit since v0.12.0). `! F (internal)`

## 2. Declare 0.12.1, archive

- [x] 2.1 `gradle.properties`: 0.12.1, and what it ships. `. d (internal)`
- [x] 2.2 Mark F1 of `require-breaking-change-footer` adopted; archive this change. `. d (internal)`

## 3. Pull request and release

- [ ] 3.1 Rebase, push, open the pull request, merge when green.
- [ ] 3.2 Tag `v0.12.1`; check the release run, the release page and Maven Central.
