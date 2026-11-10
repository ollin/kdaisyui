#!/usr/bin/env bash
# Positive controls for classify.sh: fixed path lists with known scopes. Run by the CI `scope` job
# before it classifies a pull request, and locally with `bash .github/ci-scope/classify.test.sh`.
# A wrong answer fails the run, and a failed `scope` job makes every test job run.
set -u
here="$(dirname "$0")"
failures=0

# expect <scope> <label> [path...]: the paths go in one per line, in this shell, never through a
# pipe into expect. A pipe would run expect in a subshell and lose the failure count, which once
# made every control unable to fail.
expect() {
  local scope="$1" label="$2" actual
  shift 2
  if [ "$#" -gt 0 ]; then
    actual="$(printf '%s\n' "$@" | bash "$here/classify.sh")"
  else
    actual="$(bash "$here/classify.sh" < /dev/null)"
  fi
  if [ "$actual" = "$scope" ]; then
    echo "ok    $label: $actual"
  else
    echo "FAIL  $label: expected $scope, got $actual"
    failures=$((failures + 1))
  fi
}

expect full "an empty list"
expect full "a source path" lib/src/main/kotlin/X.kt

for planning in openspec/changes/x/tasks.md .opencode/skills/x/SKILL.md .idea/vcs.xml \
    AGENTS.md CLAUDE.md CREDITS.md LICENSE opencode.jsonc renovate.json justfile \
    .github/workflows/release.yml .github/workflows/renovate.yml .github/workflows/pr-conventional-commits.yml; do
  expect none "$planning" "$planning"
done
expect none "two planning files" openspec/a.md AGENTS.md
expect full "a planning name with a suffix" AGENTS.md.bak
expect full "a planning name in another directory" lib/AGENTS.md
expect full "the CI workflow itself" .github/workflows/ci.yml
expect full ".gitignore, which the drift job's git status reads" .gitignore

for prose in README.md docs/how-to.md docs/tutorials/getting-started.md docs/screenshots/overview.png \
    codegen/llms-template.md codegen/llms-full-template.md llms.txt llms-full.txt; do
  expect prose "$prose" "$prose"
done
expect full "a generated reference page" docs/reference/button.md
expect prose "the hand-written reference entry point" docs/reference.md
expect full "a template name with a suffix" codegen/llms-template.md.orig
expect full "a docs directory below the root" lib/docs/x.md
expect none "a blank line between paths" AGENTS.md "" openspec/a.md

expect prose "planning and prose: the wider scope" openspec/a.md README.md
expect prose "prose and planning, in the other order" README.md openspec/a.md
expect full "prose, source and planning: full" README.md lib/build.gradle.kts AGENTS.md
expect full "full is not narrowed by a later prose path" lib/build.gradle.kts README.md

[ "$failures" -eq 0 ] || { echo "$failures classifier control(s) failed"; exit 1; }
