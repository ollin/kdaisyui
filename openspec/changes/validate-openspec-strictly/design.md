## Context

OpenSpec 1.17.0 warns on requirement text over 500 characters, and `--strict` turns the warning into
exit 1. The text it measures is every non-blank, non-fenced line before the first heading, except
lines of the form `**Word**:` (A1). Here, 79 evidence paragraphs open with `**Verified**` without a
colon and wrap over several lines, so they count in full.

## Goals / Non-Goals

**Goals:** `openspec validate --all --strict` passes, stays passing because CI requires it, and the
specs say the same things they said before.

**Non-Goals:** rewording for content, Renovate for the pin, findings verification in CI (proposal,
*Out of scope*).

## Decisions

### D1 - Evidence as one-line metadata (ENABLER)

**Chosen:** a script joins every `**Verified**`/`**Assumed**` paragraph into one line and adds the
colon. Behaviour-preserving and checkable by diff: the joined text is the old text with newlines
replaced by spaces. Evidence spread over a second unmarked paragraph or a list is folded in by hand,
in its own commit.

**Rejected:** wrapped paragraphs plus splitting all 56 requirements (Oliver, 2026-11-11). The
evidence would have to be shortened as well, and evidence is not requirement text.

### D2 - Split, with examples to scenarios (DESIRED CHANGE)

**Chosen:** a requirement whose wording exceeds 500 characters becomes several, each stating one
behaviour, with its own scenario taken from the existing scenarios where one fits. Examples and edge
cases move into scenarios. Rationale goes to a `**Why**:` line only where the split alone is not
enough (A6). One commit per spec.

**Rejected:** shortening by deletion. It is faster and loses statements nobody can tell are gone.

### D3 - Main specs edited directly (A5)

**Chosen:** the commits edit `openspec/specs/` directly; this change's delta carries only the new CI
requirement.

**Rejected:** REMOVED + ADDED deltas for each split. About 90 blocks restating text that does not
change, archived by a tool that does not length-check MODIFIED blocks either.

### D4 - The check: a script beside `check.sh`, the version pinned in it

**Chosen:** `.github/openspec-config/validate.sh` runs
`npx -y @fission-ai/openspec@<pin> validate --all --strict --no-interactive` with
`OPENSPEC_TELEMETRY=0`; `validate.test.sh` is its control on two fixture trees. Two steps in the
`openspec-config` job, after `actions/setup-node` with `.tool-versions`, the way the other
Node jobs read it.

**Rejected:** a `package.json` and lockfile for one CLI (more files for one pin, and Renovate does
not read them unless configured); a separate job (it would not be a required check without a
ruleset change).

## Risks / Trade-offs

- [A future OpenSpec changes what it counts] → the pin keeps CI stable; bumping it is a deliberate
  edit whose control re-runs.
- [Long metadata lines] → accepted; editors wrap them, and diffs show a changed line whole.
- [A split changes meaning unnoticed] → each split is its own commit, reviewed against the old text.
