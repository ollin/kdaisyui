## Why

The v0.2.0 release notes are unusable. The artifacts were fine — all three landed on Maven
Central, signed, byte-identical to the locally staged bundle — so this is entirely about what
a reader sees on the release page.

Three defects, in the order they hurt:

**1. The breaking-change notice is mangled.** The single most important line for a consumer
renders as broken markdown in the middle of a bullet, and it swallowed the rest of the commit
body:

```
- 30b991d 🚨 commit generated sources, … - *TooltipVariant.Neutral is removed; DaisyUI dropped
tooltip-neutral. See "How to migrate" in README.md, which also covers
positional arguments shifting as generated signatures gain modifiers.

Four OpenSpec changes: commit-generated-sources, cleanup-dead-code, …*
```

Cause, and it is mine: I wrote `BREAKING CHANGE:` in the *middle* of the merge commit message.
Conventional Commits defines that footer as running to the **end** of the message, so every
paragraph after it became part of the note.

**2. Roughly 120 dependency lines, each one twice.** The changelog spans `v0.1.2` (21 June) to
now, and the merge with `main` brought two months of Renovate history with it — once as the PR
merge commit `(#228)` and once as the underlying commit. Three real features are buried.

The duplication is a symptom, not the disease: none of these entries belong in release notes at
all. Hiding the category removes both halves at once, which is why no merge-commit filtering is
needed — see **Constraint** below, which matters more than it looks.

**3. 117 raw Arlo commits, uncategorized.** The `conventional-commits` preset cannot classify
`. d Plan detect-api-changes`, so they land in the uncategorized bucket, which is shown by
default. This is the house convention (Arlo on commits, Conventional on the merge commit)
meeting a generator that reads every commit.

None of this is fixable after the fact: the release reports `"immutable": true`.

## What Changes

- Configure `jreleaser.release.github.changelog` to hide the uncategorized bucket and the
  dependency category.
- Record the commit-message rule that `BREAKING CHANGE:` must be the **last** paragraph.

## Capabilities

### New Capabilities

None. `skip_specs: true` — this is generator configuration plus a writing rule; nothing a
consumer of the library can observe changes.

If `detect-api-changes` ends up creating a release-hygiene capability (its section 5 adds "a
breaking change requires a migration note"), these rules belong together and this one should
fold into it rather than growing a capability of its own.

## Assumptions

1. **The preset's category keys can be determined by running the generator.** The
   `conventional-commits` preset is a resource inside the JReleaser jar, not in the sources, so
   it cannot be read from the dependency-source view. The rendered titles were "🚀 Features",
   "🧰 Tasks" and "📝 Documentation", but `hide.categories` matches on **keys**, not titles.
   *Wrong if:* `jreleaserChangelog` does not expose the keys — then read them from JReleaser's
   published preset file instead, or replace the preset with explicit `categories` and own the
   mapping.
2. **`hide { uncategorized; categories }` does what its API says.** Verified to *exist*:
   `org.jreleaser.model.api.release.Changelog.Hide` declares `isUncategorized()`,
   `getCategories()` and `getContributors()`. *Wrong if:* hiding a category also drops entries
   the preset assigned to it that we wanted to keep — checked by diffing the generated
   changelog before and after.
## Constraint: merge commits must stay in the changelog

Not an assumption — a decision, and the one thing this change must not break.

**`skipMergeCommits` stays off.** The house convention puts the Conventional Commit on the
*merge* commit, so in v0.2.0 the merge commit `30b991d` was the **only** properly-formed entry
in the entire changelog. Enabling this option would delete precisely the line worth keeping.

Verified: `Changelog.isSkipMergeCommits()` returns `null != skipMergeCommits && skipMergeCommits`
(`jreleaser-model-impl/.../release/Changelog.java:334`), so unset means false and today's
behaviour is already correct. This change must keep it that way — and say so in the build
file, because the option is an obvious-looking cure for the duplicated dependency entries, and
reaching for it would silently gut the changelog.

The duplicates need no such cure: hiding the dependency category removes the merge-commit
entry *and* the underlying commit together, which is task 2.2.

## Impact

- `build.gradle.kts` — the `changelog` block.
- The `risk-aware-commits` skill and the `kdaisyui-release` skill — the footer rule.
- No artifact, no published behaviour, no code.

**Verifiable without releasing.** `jreleaserChangelog` calculates the changelog as a task, so
the whole change can be checked locally against the real `v0.1.2..v0.2.0` range before the next
tag exists.

## This change does not get its own release

Considered and rejected 2026-08-16: cutting a 0.2.1 to "publish corrected release notes".

- It would not repair v0.2.0. That page stays as it is; a new release is a new page.
- **Its own notes would be nearly empty, so the fix could not be demonstrated by it.**
  JReleaser computes `v0.2.0..v0.2.1`, which would hold only this change's own configuration
  commits — and `hide { uncategorized }` would hide most of them. Every piece of evidence lives
  in `v0.1.2..v0.2.0`: the 120 dependency lines, the 117 uncategorized commits, the mangled
  breaking-change note.
- It would republish byte-identical artifacts to Maven Central, permanently, for a docs fix.

`jreleaserChangelog` verifies the fix against the range that actually contains the problem, so
a release adds no evidence. The improved notes go to the next release that carries real
content.

**Out of scope**
- Repairing the v0.2.0 release notes from here. The API reports `immutable: true` and no tool
  available in this setup can edit a release body. Whether GitHub's immutable releases lock the
  body or only the tag and assets was not established — Oliver is trying the UI's *Edit
  release* directly, which would fix the visible problem without any new version.
- Changing the Arlo-on-commits convention.
