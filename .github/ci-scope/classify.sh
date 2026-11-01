#!/usr/bin/env bash
# Reads the changed paths of a pull request on standard input, one per line, and prints the CI
# scope: none, prose or full. Anything it does not know is full, so a mistake costs time, never a
# skipped job. The rules are in openspec/specs/ci-job-selection/spec.md; the controls that prove
# them are in classify.test.sh.
set -u

# Read by no CI job: planning, agent and editor files, and the workflows that are not ci.yml.
planning='^(openspec/.*|\.opencode/.*|\.idea/.*|\.codeseeker/.*|AGENTS\.md|CLAUDE\.md|CREDITS\.md|LICENSE|opencode\.jsonc|renovate\.json|justfile|\.github/workflows/(release|renovate|pr-conventional-commits)\.yml)$'

scope=none
count=0
while IFS= read -r path; do
  [ -n "$path" ] || continue
  count=$((count + 1))
  [[ "$path" =~ $planning ]] || scope=full
done
[ "$count" -gt 0 ] || scope=full
echo "$scope"
