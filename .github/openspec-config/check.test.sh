#!/usr/bin/env bash
# Positive control for check.sh: a good file must pass and the line that broke openspec/config.yaml
# on 2026-11-08 must fail. Without the second answer, a check.sh that accepts everything would pass.
set -u
here="$(dirname "$0")"
fixtures="$(mktemp -d)"
trap 'rm -rf "$fixtures"' EXIT
failures=0

cat > "$fixtures/good.yaml" <<'YAML'
rules:
  tasks:
    - Plan a step's marker from its counted lines, not from its behaviour. A changed
      line counts as one removed plus one added.
YAML
cat > "$fixtures/broken.yaml" <<'YAML'
rules:
  tasks:
    - Plan a step's marker from its counted lines, not from its behaviour: a changed
      line counts as one removed plus one added.
YAML

if bash "$here/check.sh" "$fixtures/good.yaml" >/dev/null; then
  echo "ok    a good file passes"
else
  echo "FAIL  a good file failed"; failures=$((failures + 1))
fi
if bash "$here/check.sh" "$fixtures/broken.yaml" >/dev/null; then
  echo "FAIL  the broken line of 2026-11-08 passed"; failures=$((failures + 1))
else
  echo "ok    the broken line of 2026-11-08 fails"
fi

[ "$failures" -eq 0 ]
