#!/usr/bin/env bash
# Reads the changed paths of a pull request on standard input, one per line, and prints the CI
# scope: none, prose or full. Anything it does not know is full, so a mistake costs time, never a
# skipped job. The rules are in openspec/specs/ci-job-selection/spec.md; the controls that prove
# them are in classify.test.sh.
set -u
echo full
