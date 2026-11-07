## ADDED Requirements

### Requirement: A modal the server renders open shows and closes without a script

A `daisyModal` rendered with the `open` attribute SHALL be visible when it reaches the page,
in the document or swapped in by htmx, and a `form method="dialog"` button inside it SHALL hide
it, with no script beyond htmx. The example application SHALL render one on a demo route, the
E2E suite SHALL assert the showing and the closing, and the how-to SHALL show the recipe.

**Verified** in DaisyUI's CSS, `daisyui/packages/daisyui/src/components/modal.css`, 2026-11-07:
`.modal` is `display: grid` and `invisible` (line 3), and the rule that makes it visible
matches `&[open]` (lines 34-39); so a closed dialog is hidden by `visibility`, and removing
`open` hides it again. **Assumed** for the htmx swap; *wrong if* task 2.2 does not see the
dialog after the click, or 2.3 still sees it after Close.

#### Scenario: Served open

- **WHEN** the fragment route is requested
- **THEN** the body contains `class="modal"` and `open=""`

#### Scenario: Swapped in open

- **WHEN** the user clicks the button that fetches the modal fragment
- **THEN** the dialog is visible, with its box and its Close button

#### Scenario: Closed by the form

- **WHEN** the user clicks Close
- **THEN** the dialog is hidden
- **AND** the page's request log holds no request after the click
