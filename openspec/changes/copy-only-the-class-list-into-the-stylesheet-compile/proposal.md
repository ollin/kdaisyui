## Why

`kdaisyuiStylesheet` copies the whole directory `build/kdaisyui` into its container as its
`classList` input, and writes its output to `build/kdaisyui/app.css` by convention — into that same
directory. Its own output therefore changes its input: after every real compile the next run is
not up to date and compiles once more in Docker, in every consumer's build
(`archive/2026-11-10-check-the-themes-the-stylesheet-compiles/findings.md`, F2, adopted by Oliver
2026-11-10).

## What Changes

- **The task's input is the class-list file, not its directory.** `classList` becomes a
  `RegularFileProperty` (`build/kdaisyui/kdaisyui-classes.txt`), copied to
  `/deps/project/kdaisyui/kdaisyui-classes.txt` — the path every entry's
  `@source "../kdaisyui/kdaisyui-classes.txt"` already names, so no entry changes.
- The output keeps its documented default, `build/kdaisyui/app.css` (README, extension KDoc).
- `docs/upgrading.md`: a build that set the task's `classList` itself sets a file now.

### The subtractive option, stated

Move the output's default elsewhere (`build/kdaisyui-css/app.css`), the fix the finding first
proposed. Rejected: it changes a documented path every consumer may serve from, and the loop
returns the day someone points `output` into `build/kdaisyui` again. Narrowing the input removes
the cause; nothing is added.

## Out of scope

- Any other input or output path; the container layout.

## Assumptions

| ID | Assumption | Checked by | Status |
|---|---|---|---|
| A1 | Testcontainers copies a single file to `/deps/project/kdaisyui/kdaisyui-classes.txt` and creates the missing parent directory | code | confirmed in task 2.1 - `NodeContainerRunTest`'s real compile copied the single file to that path and its class `badge`, named by no source, reached the output |
| A2 | With the file as input, a second run of the example app's `kdaisyuiStylesheet` is UP-TO-DATE | code | to check in task 3.1 - two runs in a row |
| A3 | Nothing else sets or reads the task's `classList` as a directory | code | confirmed at proposal - grep: `KdaisyuiPlugin.kt` sets it, `KdaisyuiStylesheet.plan()` reads it, three plugin tests name it; no doc or example app does |
| A4 | No requirement of a main spec this builds on contradicts it: `css-delivery` ("The plugin compiles the stylesheet in a container", "The narrowed stylesheet styles the example application") | code | confirmed at proposal - the fixed paths stay; the entry's `@source` lines resolve as before |

## Capabilities

### Modified Capabilities

- `css-delivery`: the stylesheet task does not recompile after its own output.

## Impact

- `gradle-plugin/` (`KdaisyuiStylesheet.kt`, `KdaisyuiPlugin.kt`, three tests), `docs/upgrading.md`.
