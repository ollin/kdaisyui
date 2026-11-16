## ADDED Requirements

### Requirement: A breaking line of the release notes links the migration guide

The release notes SHALL end the line of every breaking commit with a link to `docs/upgrading.md` on
`main`, whatever that commit's footer says. A line that is not breaking SHALL carry no such link.

**Verified** 2026-11-16 in a clone of v0.9.0 with the template changed, by
`jreleaserChangelog --rerun -Pversion=0.9.0`: the line of #471 ended with the link and none of the
other 42 lines did. **Verified** the same way that the template cannot name the release's tag,
which is why the link points at `main`.

#### Scenario: A breaking commit
- **WHEN** the release contains a commit whose message is `feat!: …`
- **THEN** its changelog line ends with `[How to migrate](https://github.com/ollin/kdaisyui/blob/main/docs/upgrading.md)`

#### Scenario: A commit that breaks nothing
- **WHEN** the release contains `fix: …`
- **THEN** its changelog line carries no migration link
