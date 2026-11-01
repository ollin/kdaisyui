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

for prose in README.md docs/how-to.md docs/tutorials/getting-started.md docs/screenshots/overview.png \
    codegen/llms-template.md codegen/llms-full-template.md llms.txt llms-full.txt; do
  printf '%s\n' "$prose" | expect prose "$prose"
done
printf 'docs/reference/button.md\n' | expect full "a generated reference page"
printf 'docs/reference.md\n' | expect prose "the hand-written reference entry point"
printf 'codegen/llms-template.md.orig\n' | expect full "a template name with a suffix"

printf 'openspec/a.md\nREADME.md\n' | expect prose "planning and prose: the wider scope"
printf 'README.md\nopenspec/a.md\n' | expect prose "prose and planning, in the other order"
printf 'README.md\nlib/build.gradle.kts\nAGENTS.md\n' | expect full "prose, source and planning: full"
printf 'lib/build.gradle.kts\nREADME.md\n' | expect full "full is not narrowed by a later prose path"

[ "$failures" -eq 0 ] || { echo "$failures classifier control(s) failed"; exit 1; }
