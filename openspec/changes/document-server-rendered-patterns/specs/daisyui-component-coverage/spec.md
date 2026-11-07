## ADDED Requirements

### Requirement: A modal the server renders open shows and closes without a script

A `daisyModal` rendered with the `open` attribute SHALL be visible when it reaches the page,
in the document or swapped in by htmx, and a `form method="dialog"` button inside it SHALL hide
it, with no script beyond htmx. The example application SHALL render one on a demo route, the
E2E suite SHALL assert the showing and the closing, and the how-to SHALL show the recipe.

**Verified** by reading `daisyui/packages/daisyui/src/components/modal.css`, 2026-11-07: the
visible rule matches `&[open]`, and `.modal` sets `display: grid`, an author rule that wins over
the user agent's `dialog:not([open]) { display: none }`. **Assumed** for the htmx swap; *wrong
if* the E2E scenario of task 2.2 does not see the dialog after the click, or still sees it
after Close.

#### Scenario: Swapped in open

- **WHEN** the user clicks the button that fetches the modal fragment
- **THEN** the dialog is visible, with its box and its Close button

#### Scenario: Closed by the form

- **WHEN** the user clicks Close
- **THEN** the dialog is hidden
- **AND** no request was made for the close
