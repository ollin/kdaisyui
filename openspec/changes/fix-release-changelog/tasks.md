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

- [ ] 1.1 `. d` Run `jreleaserChangelog` on the current configuration and capture the output as
      the reference. It should reproduce what v0.2.0 published — if it does not, the local
      range differs from what CI saw and everything after this is measured against the wrong
      baseline.
      **Label corrected from `. r`:** running a task and recording what it produced changes no
      code and preserves no behaviour. It is evidence, so it is `d`, not `r`
- [ ] 1.2 `. r` Find the keys behind "🚀 Features", "🧰 Tasks" and "📝 Documentation" — from
      `jreleaserConfig`, the trace log, or JReleaser's published preset
- [ ] 1.3 `. d` Record the keys and where they came from

## 2. Hide the noise

- [ ] 2.1 `. r` Set `hide { uncategorized = true }` and re-run. Expect: the 117 raw Arlo
      commits disappear, everything else unchanged
- [ ] 2.2 `. r` Add the dependency category to `hide { categories }` and re-run. Expect: ~120
      lines disappear, the three real features remain
- [ ] 2.3 `. r` Diff against the 1.1 reference and confirm nothing was lost that should have
      stayed. Hiding a category is a blunt instrument — this is the check that it did not take
      a real entry with it
- [ ] 2.4 `. d` **Confirm the merge-commit entry survived**, by name: `30b991d` must still be
      in the output. It is the only Conventional Commit in the range, so it is the whole point
      of the changelog
- [ ] 2.5 `. d` Pin `skipMergeCommits = false` explicitly in `build.gradle.kts` with a comment
      saying why. It is already the default, so this adds no behaviour — it exists to stop a
      future reader reaching for the obvious-looking cure for duplicated dependency entries and
      deleting the one entry that matters. The duplicates are gone by 2.2 anyway

## 3. Stop mangling the breaking-change note

*The defect that actually hurt a reader, and the one no configuration can fix.*

- [ ] 3.1 `. d` Record the rule in the `risk-aware-commits` skill: in a merge-request title's
      commit body, `BREAKING CHANGE:` is the **last** paragraph. Everything after it is
      absorbed into the note — per the Conventional Commits spec, the footer runs to the end
- [ ] 3.2 `. d` Note the same in the `kdaisyui-release` skill, next to the existing warning
      about conflicted PRs getting no CI, with the v0.2.0 render as the worked example
- [ ] 3.3 `. r` Verify by generating a changelog from a scratch commit whose body ends with the
      footer, and confirm the note renders as one clean line

## 4. Land it

- [ ] 4.1 `^ F` Commit the `changelog` block with the before/after outputs quoted in the
      message
- [ ] 4.2 `. d` Consider whether this belongs with `detect-api-changes` section 5, which also
      adds a release-hygiene rule. Two rules about what a release must say want one home
