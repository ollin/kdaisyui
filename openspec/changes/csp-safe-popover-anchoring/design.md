## Context

See proposal.md, Why. Two writers of inline style exist: `Tag.popoverTarget` in core and the
`anchoredPopover` branch of the Kotlin emitter. Both take the name from `HtmlId.anchorName`.
The library ships one CSS-build input today, `kdaisyui-classes.txt`, generated into
`lib/generated/main/resources` and read by the Gradle plugin from the jar on the runtime
classpath (`MapOnClasspath.kt` does the same for the component map).

## Goals / Non-Goals

**Goals:**

- Anchored popovers work with no inline `style` anywhere in kdaisyui's output.
- The anchor pairing stays derived in one place and tested in a browser.

**Non-Goals:**

- A general "attributes to CSS" mechanism. Two rules, for one pair of properties.
- Nonce or hash support for inline styles. Nothing in kdaisyui should need an inline style.

## Decisions

### 1. Data attributes plus typed `attr()`, not nonces, hashes or per-id classes

**Chosen:** `data-anchor-name` / `data-position-anchor`, read by two rules with
`attr(<name> type(<custom-ident>))`. Proven by the probe in A1.

**Rejected: `'unsafe-hashes'` with a hash per anchor value.** The policy would have to list one
`sha256-…` per anchored popover on the page, computed from a string kdaisyui derives. The
application would need the set of ids per response to build its header. Not a library concern,
and fragile.

**Rejected: a nonce on a `<style>` element.** kotlinx.html has no notion of a per-request nonce;
every page would thread one through its render, and the rules would be repeated per page.

**Rejected: a generated class per anchor.** A class name is a dashed-ident too, but the rule
`.anchor-dash_menu { anchor-name: --dash_menu }` has to exist in the compiled stylesheet, which
means Tailwind would have to see every id at build time. Ids are runtime values.

### 2. The rules ship as a jar resource the plugin writes next to the class list

**Chosen:** `lib/src/main/resources/kdaisyui.css`, hand-written, two rules and a comment. The
plugin's `kdaisyuiClasses` task copies it from the jar to `build/kdaisyui/kdaisyui.css`, the
same way it reads the component map, and the README's entry CSS gains
`@import "./build/kdaisyui/kdaisyui.css";`. The example app's `app.css` imports it from its
mount point.

**Rejected: a snippet in the README the reader pastes.** Two lines, no machinery, and the
proposal lists it as the subtractive option (A6). It loses to the resource on one point: the
rules are part of what makes kdaisyui's HTML work, and a pasted copy is a second source that
drifts when a later change adds a third rule. The plugin already exists to stop exactly that
kind of hand-kept copy for the class list.

**Rejected: the rules inside `kdaisyui-classes.txt`.** That file is a Tailwind `@source`, a bag
of candidate class names; CSS inside it would be scanned for names, not applied.

**Rejected: generating the file.** Nothing in the model determines it. Hand-written, under
`lib/src/main/resources`, is honest about that; the jar-presence test is the same shape as
`TailwindClassListTest`.

### 3. `popoverTarget` stops merging `style`

The `listOfNotNull(attributes["style"], …)` branch existed only to append to a style the caller
had set. With no style written, the branch and its test go. One behaviour per commit: the
attribute first, the deletion second.

### 4. Enablers and the desired change

Enablers, provable by diff or by an empty regeneration: the plugin writing a second file whose
content it reads from the jar (the example app's stylesheet is unchanged until the import is
added); the jar-presence test. Desired changes: the two attribute writes, the two rules, the
import, the deletion of the style branch.

## Risks / Trade-offs

- [A consumer upgrades and forgets the import] → the migration note, and the plugin's output
  file is named in the README right beside the class list. The symptom, a popover opening in the
  middle of the viewport, is what the E2E scenario measures here, so the library's own copy
  cannot regress silently.
- [A browser without typed `attr()`] → the same fallback DaisyUI already has for anchor
  positioning: `daisyDropdown` renders `<details>` and needs none of this. The how-to names
  Chromium 133 as the floor for the popover form, beside the 125 it names today.
- [Tailwind rewrites or drops the `attr()` rule] → task 1.2 compiles it in the example app and
  reads the output before anything is built on it.
