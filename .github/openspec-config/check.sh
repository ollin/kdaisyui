#!/usr/bin/env bash
# Fails when the OpenSpec config does not parse as YAML. The OpenSpec CLI does not fail on such a
# file: it warns on stderr, ignores it, and serves no project rules at all, so every rule written
# there silently stops reaching the agents (planning-rules-from-popover-anchoring, finding F1).
# Run by the CI job `openspec-config`; its control is check.test.sh.
set -u
file="${1:-openspec/config.yaml}"
if ! error="$(yq '.' "$file" 2>&1 >/dev/null)"; then
  echo "::error file=$file::$file does not parse as YAML; the OpenSpec CLI would ignore it and serve no project rules"
  printf '%s\n' "$error"
  exit 1
fi
echo "ok    $file parses"
