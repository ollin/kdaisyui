# Tasks

Ordered by uncertainty. Everything here is verifiable locally with `jreleaserChangelog`, which
calculates the changelog without releasing anything — so the whole change can be proven against
the real `v0.1.2..v0.2.0` range before another tag exists.

**Corrected 2026-09-04, measured at 1.1:** "verifiable locally" needs one caveat. JReleaser
aborts validation with `release.github.token must not be blank` before it computes anything, so
the task needs `JRELEASER_GITHUB_TOKEN` set. Any non-blank value works — generation reads git
history and makes no API call — so pass a placeholder rather than a real token.

The range also needs no special handling: with the project version at `0.2.0` and the `v0.2.0`
tag present, JReleaser ends the range at the tag, not at HEAD. Verified — the output contains no
commit later than `v0.2.0`, despite HEAD being `b942d93`.

## 1. Find the category keys

*Assumption 1. The preset lives in a jar resource, not in the sources, so the keys have to be
measured rather than read. Refuted if the generator does not reveal them — then own the
categories explicitly instead of using the preset.*

- [x] 1.1 `. d` Run `jreleaserChangelog` on the current configuration and capture the output as
      the reference. It should reproduce what v0.2.0 published — if it does not, the local
      range differs from what CI saw and everything after this is measured against the wrong
      baseline.
      **Label corrected from `. r`:** running a task and recording what it produced changes no
      code and preserves no behaviour. It is evidence, so it is `d`, not `r`
- [x] 1.2 `. d` Find the keys behind "🚀 Features", "🧰 Tasks" and "📝 Documentation" — from
      `jreleaserConfig`, the trace log, or JReleaser's published preset
- [x] 1.3 `. d` Record the keys and where they came from

**1.2 and 1.3 collapsed into one act.** Finding the keys and recording them was a single
`jreleaserConfig` run written up in `notes.md`; splitting the commit would have produced an empty
one. 1.2 is also relabelled `. r` → `. d` for the reason given at 1.1.

## 2. Hide the noise

- [x] 2.1 `! F` Set `hide { uncategorized = true }` and re-run. Expect: the 137 raw Arlo
      commits disappear, everything else unchanged
- [x] 2.2 `! F` Hide the `tasks` category and re-run. Expect: 108 lines disappear, the three
      real features remain

**Rewritten 2026-09-04, premise refuted by 1.2.** This said "add the dependency category". There
is no dependency category. `chore(deps):` is labelled `chore` — the scope is not in the labeler
regex — so it lands in `tasks`, whose only label is `chore` and whose entire content in this
range is the 108 dependency lines. Hiding `tasks` is the available move; the cost, and the
precise alternative that was rejected, are recorded in `notes.md`.

**Labels corrected 2026-09-04, and the count with them.** Both were `. r`. Neither is a
refactoring: each changes what a reader sees on the release page, which is the behaviour this
whole change exists to alter. Neither can reach `^` either, because nothing in CI asserts
changelog content — verification is running `jreleaserChangelog` and reading the output against
the reference in `notes.md`. That is the definition of `!`. The counts were estimates in the
proposal; 1.1 measured 137 and 108.

**Consequence for 4.1:** it can no longer be "commit the `changelog` block", because 2.1 and 2.2
each commit their own line of it. It becomes the final verification that the whole block behaves
together, and is relabelled there when reached.
- [x] 2.3 `. d` Diff against the 1.1 reference and confirm nothing was lost that should have
      stayed. Hiding a category is a blunt instrument — this is the check that it did not take
      a real entry with it
- [x] 2.4 `. d` **Confirm the merge-commit entry survived**, by name: `30b991d` must still be
      in the output. It is the only Conventional Commit in the range, so it is the whole point
      of the changelog
- [x] 2.5 `. d` Pin `skipMergeCommits = false` explicitly in `build.gradle.kts` with a comment
      saying why. It is already the default, so this adds no behaviour — it exists to stop a
      future reader reaching for the obvious-looking cure for duplicated dependency entries and
      deleting the one entry that matters. The duplicates are gone by 2.2 anyway

## 3. Stop mangling the breaking-change note

*The defect that actually hurt a reader, and the one no configuration can fix.*

- [x] 3.1 `. d` Record the rule in the `risk-aware-commits` skill: in a merge-request title's
      commit body, `BREAKING CHANGE:` is the **last** paragraph. Everything after it is
      absorbed into the note — per the Conventional Commits spec, the footer runs to the end
- [x] 3.2 `. d` Note the same in the `kdaisyui-release` skill, next to the existing warning
      about conflicted PRs getting no CI, with the v0.2.0 render as the worked example
- [ ] 3.3 `. r` Verify by generating a changelog from a scratch commit whose body ends with the
      footer, and confirm the note renders as one clean line

## 4. Land it

- [ ] 4.1 `^ F` Commit the `changelog` block with the before/after outputs quoted in the
      message
- [ ] 4.2 `. d` Consider whether this belongs with `detect-api-changes` section 5, which also
      adds a release-hygiene rule. Two rules about what a release must say want one home
