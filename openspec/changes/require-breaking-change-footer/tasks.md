## Estimate

Frozen 2026-11-15 before implementation, raw developer-only minutes. Ledger run
`2026-11-15-kdaisyui-require-breaking-change-footer`. Forecast: 2h 35min (4h 55min DevOnly).

| Block | Estimate |
|---|---|
| 1. Open assumptions | 30m |
| 2. The checker in `:build-checks` | 120m |
| 3. The workflow job | 60m |
| 4. Documentation | 30m |
| 5. Gates, archive, first live run | 55m |
| **Total** | **295m** |

## 1. Open assumptions

- [x] 1.1 A5: in a scratch clone, run the job's git steps by hand against PR #471 and #492
  (`git fetch origin refs/pull/<n>/head`, `git diff`, `git show <head>:docs/upgrading.md`) and
  record that they need no checkout of the head. `. d (internal)`
- [x] 1.2 A4: put it to Oliver at review; record the answer in proposal.md. `. d (internal)`

## 2. The checker in `:build-checks`, one behaviour per TCR run

- [x] 2.1 A title without `!` and a diff without a removed API line gives no finding. `! F (internal)`
- [x] 2.2 A removed line in either API baseline under a title without `!` gives "title lacks `!`". `! F (internal)`
- [x] 2.3 A breaking pull request whose last paragraph does not start with `BREAKING CHANGE:` gives
  "body lacks the footer", including a footer followed by another paragraph. `! F (internal)`
- [x] 2.4 A footer that names no `How to migrate from <x>`, or one `docs/upgrading.md` lacks,
  gives "heading missing" with its text. `! F (internal)`
- [ ] 2.5 Pin #471, #492 and #515 as fixtures: the first two fail with the findings they would
  have had, the third passes. `. r (internal)`
- [ ] 2.6 `main` reads the four files, prints each finding and exits 1 on any;
  `:build-checks:checkBreakingChange` runs it. `! F (internal)`

## 3. The workflow job

- [ ] 3.1 `breaking-change-footer` in `pr-conventional-commits.yml`: checkout of `main`, fetch of
  the head, the four files, the Gradle task. Body and title through `env:` only. `! F (internal)`

## 4. Documentation

- [ ] 4.1 `kdaisyui-release`: the footer is part of "ready to merge", and the gate says so. `. d (internal)`

## 5. Gates, archive, first live run

- [ ] 5.1 `./gradlew check`, `:build-checks:pitest` at 100% test strength.
- [ ] 5.2 CodeScene on the changed files, the server's values before the push.
- [ ] 5.3 Archive on this branch. `. d (internal)`
- [ ] 5.4 A6: after the merge, the next pull request shows a `breaking-change-footer` run; record
  it in findings.md.
- [ ] 5.5 Oliver adds `breaking-change-footer` to the required checks of ruleset 14483746.
