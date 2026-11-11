#!/usr/bin/env bash
# Positive control for validate.sh: the same 600-character evidence passes as one `**Verified**:`
# metadata line and fails as a wrapped `**Verified**` paragraph. Without the second answer, a
# validate.sh that accepts everything would pass.
set -u
here="$(cd "$(dirname "$0")" && pwd)"
fixtures="$(mktemp -d)"
trap 'rm -rf "$fixtures"' EXIT
failures=0
evidence="$(printf 'the probe measured it. %.0s' $(seq 1 26))"

spec() {
  mkdir -p "$1/openspec/specs/probe"
  cat > "$1/openspec/specs/probe/spec.md" <<MD
# probe Specification

## Purpose
A fixture for the positive control of the strict OpenSpec check in CI.

## Requirements

### Requirement: One short behaviour

The system SHALL do one thing.

$2

#### Scenario: It does the thing

- **WHEN** it runs
- **THEN** it does the thing
MD
}

spec "$fixtures/good" "**Verified**: $evidence"
spec "$fixtures/long" "$(printf '**Verified** %s' "$evidence" | fold -s -w 100)"

if bash "$here/validate.sh" "$fixtures/good" >/dev/null 2>&1; then
  echo "ok    evidence as one metadata line passes"
else
  echo "FAIL  evidence as one metadata line failed"; failures=$((failures + 1))
fi
if bash "$here/validate.sh" "$fixtures/long" >/dev/null 2>&1; then
  echo "FAIL  a requirement over 500 characters passed"; failures=$((failures + 1))
else
  echo "ok    a requirement over 500 characters fails"
fi

[ "$failures" -eq 0 ]
