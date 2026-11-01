#!/usr/bin/env bash
# Reads the changed paths of a pull request on standard input, one per line, and prints the CI
# scope: none, prose or full. Anything it does not know is full, so a mistake costs time, never a
# skipped job. The rules are in openspec/specs/ci-job-selection/spec.md; the controls that prove
# them are in classify.test.sh.
set -u

# Read by no CI job: planning, agent and editor files, and the workflows that are not ci.yml.
planning='^(openspec/.*|\.opencode/.*|\.idea/.*|\.codeseeker/.*|AGENTS\.md|CLAUDE\.md|CREDITS\.md|LICENSE|opencode\.jsonc|renovate\.json|justfile|\.github/workflows/(release|renovate|pr-conventional-commits)\.yml)$'

# Read only by the snippet compile (unit-tests) and the llms.txt gate (generated-sources-drift).
# docs/reference/ is generated, so it is not prose.
prose='^(README\.md|docs/.*|codegen/llms-template\.md|codegen/llms-full-template\.md|llms\.txt|llms-full\.txt)$'
generated='^docs/reference/'

scope=none
count=0
while IFS= read -r path; do
  [ -n "$path" ] || continue
  count=$((count + 1))
  if [[ "$path" =~ $prose ]] && ! [[ "$path" =~ $generated ]]; then [ "$scope" = full ] || scope=prose
  elif ! [[ "$path" =~ $planning ]]; then scope=full
  fi
done
[ "$count" -gt 0 ] || scope=full
echo "$scope"
