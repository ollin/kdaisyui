# Tailwind + DaisyUI compiler for :example-app.
#
# Why an image rather than `npm install` per build: the install is the slow part, and
# baking it makes the container command a single binary. No shell runs on either side
# of the boundary, and a compile is ~70ms.
#
# Every version is a build argument, passed by Gradle from gradle/libs.versions.toml
# and .tool-versions, so nothing here is a second place to update.
ARG NODE_VERSION
FROM node:${NODE_VERSION}-slim

ARG TAILWIND_VERSION
ARG DAISYUI_VERSION

# Dependencies live one level ABOVE the mounted project on purpose. Node resolves
# `@plugin "daisyui"` by walking up from the entry CSS, so /deps/node_modules is found
# without NODE_PATH tricks or container-absolute paths leaking into the CSS.
WORKDIR /deps
RUN npm install --no-fund --no-audit --silent \
      "@tailwindcss/cli@${TAILWIND_VERSION}" \
      "tailwindcss@${TAILWIND_VERSION}" \
      "daisyui@${DAISYUI_VERSION}"

WORKDIR /deps/project
ENTRYPOINT ["/deps/node_modules/.bin/tailwindcss"]
