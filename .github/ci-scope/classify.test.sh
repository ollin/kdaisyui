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

[ "$failures" -eq 0 ] || { echo "$failures classifier control(s) failed"; exit 1; }
