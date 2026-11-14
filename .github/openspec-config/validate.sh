#!/usr/bin/env bash
# Fails when `openspec validate --all --strict` reports an error or a warning in the OpenSpec tree
# under the given directory (default: the repository root). Without --strict a warning passes,
# and 56 requirements had grown past OpenSpec's 500-character limit before anything ran this
# (validate-openspec-strictly). Run by the CI job `openspec-config`; its control is validate.test.sh.
#
# The version is pinned here, not in a package.json: it is one CLI, and a different version may
# count differently. Keep it equal to the OpenSpec the agents run locally (`openspec --version`).
set -u
OPENSPEC_VERSION=1.17.0
root="${1:-.}"
cd "$root" || exit 2
if ! OPENSPEC_TELEMETRY=0 npx --yes "@fission-ai/openspec@$OPENSPEC_VERSION" validate --all --strict --no-interactive; then
  echo "::error::openspec validate --all --strict failed: shorten the requirements it names (evidence goes on one **Verified**: line, see openspec/config.yaml)"
  exit 1
fi
