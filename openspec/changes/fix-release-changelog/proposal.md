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
3. **`skipMergeCommits` is not available to us**, even though it would deduplicate the Renovate
   entries in one line. The house convention puts the Conventional Commit on the *merge*
   commit, so skipping merge commits would delete the only properly-formed entry in the
   changelog. *Wrong if:* the deduplication turns out to matter more than that entry, in which
   case the convention itself is what needs revisiting — a bigger conversation than this
   change.

## Impact

- `build.gradle.kts` — the `changelog` block.
- The `risk-aware-commits` skill and the `kdaisyui-release` skill — the footer rule.
- No artifact, no published behaviour, no code.

**Verifiable without releasing.** `jreleaserChangelog` calculates the changelog as a task, so
the whole change can be checked locally against the real `v0.1.2..v0.2.0` range before the next
tag exists.

**Out of scope**
- Repairing the v0.2.0 release notes. The release is immutable.
- Changing the Arlo-on-commits convention.
