#!/usr/bin/env bash
# Positive controls for classify.sh: fixed path lists with known scopes. Run by the CI `scope` job
# before it classifies a pull request, and locally with `bash .github/ci-scope/classify.test.sh`.
# A wrong answer fails the run, and a failed `scope` job makes every test job run.
set -u
here="$(dirname "$0")"
failures=0

expect() {
  local scope="$1" label="$2" actual
  actual="$(cat | bash "$here/classify.sh")"
  if [ "$actual" = "$scope" ]; then
    echo "ok    $label: $actual"
  else
    echo "FAIL  $label: expected $scope, got $actual"
    failures=$((failures + 1))
  fi
}

printf '' | expect full "an empty list"
printf 'lib/src/main/kotlin/X.kt\n' | expect full "a source path"

for planning in openspec/changes/x/tasks.md .opencode/skills/x/SKILL.md .idea/vcs.xml .codeseeker/x \
    AGENTS.md CLAUDE.md CREDITS.md LICENSE opencode.jsonc renovate.json justfile \
    .github/workflows/release.yml .github/workflows/renovate.yml .github/workflows/pr-conventional-commits.yml; do
  printf '%s\n' "$planning" | expect none "$planning"
done
printf 'openspec/a.md\nAGENTS.md\n' | expect none "two planning files"
printf 'AGENTS.md.bak\n' | expect full "a planning name with a suffix"
printf 'lib/AGENTS.md\n' | expect full "a planning name in another directory"
printf '.github/workflows/ci.yml\n' | expect full "the CI workflow itself"
printf '.gitignore\n' | expect full ".gitignore, which the drift job's git status reads"

[ "$failures" -eq 0 ] || { echo "$failures classifier control(s) failed"; exit 1; }
