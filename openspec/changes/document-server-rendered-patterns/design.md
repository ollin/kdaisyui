## Context

See proposal.md, Why. The example app serves fragments with `respondHtmlFragment` and three
`hx*` helpers (`HtmxUtil.kt`); the how-to already has "Use htmx for partial loading" and "Add a
dropdown". `daisyModal` renders `<dialog class="modal">` and its `open` parameter adds
`modal-open`.

## Goals / Non-Goals

**Goals:**

- A reader finds each of the four patterns in the how-to, as compiled Kotlin.
- The modal pattern is proven in a browser, not only read from CSS.

**Non-Goals:**

- Any new function or parameter.

## Decisions

### 1. The server-opened modal writes the `open` attribute, not the `modal-open` class

**Chosen:** `daisyModal { attributes["open"] = ""; … }`. The attribute is what
`form method="dialog"` removes, so the same markup closes without a script. `modal-open` is
for a dialog a script keeps open, which is what DaisyUI's description says.

**Rejected: `daisyModal(open = true)`.** The class keeps the dialog visible after the form
closes it; the recipe would need a script to remove the class, which is the Deploy-Radar's
`data-close`.

**Rejected: a typed way to write the attribute.** kotlinx.html's `DIALOG` has no `open`
property (A3); adding one in kdaisyui is an API decision this docs change does not make. A6
records the question.

### 2. Close by `form method="dialog"`, not by htmx or a script

A dialog with the `open` attribute closes on a submit from a `method="dialog"` form, by the
HTML specification, with no request and no handler. htmx could swap the dialog out
(`hx-swap="delete"` on a request), but that is a round trip for what the browser does alone.

### 3. The clipboard listener is shown, not shipped

Six lines, bound to `document` so an htmx swap does not lose it, reading the id from
`data-copy`. It is the application's because it names the application's ids and lives at the
application's origin under the policy. Shown in a `js` fence, which `:doc-snippets` does not
compile and does not need to.

### 4. The form in a dropdown

`daisyDropdown` with `summary { daisyButtonClasses(size = ButtonSize.Sm, color = …) }`, then
`daisyDropdownContent { form { daisyFieldset { daisyLabel; daisyInput(size = InputSize.Sm) } ;
daisyButton(…) } }`. Every class the Deploy-Radar wrote by hand has a typed home.

### 5. What the stylesheet carries, stated as what DaisyUI guarantees

The theme's custom properties on `:root` (`--color-base-100` and the rest DaisyUI documents
under "Theme variables") survive minification and are the marker a consumer's test can read;
a comment does not. One paragraph, with the example from the Deploy-Radar's own test.

## Risks / Trade-offs

- [A later DaisyUI renames a theme variable] → the paragraph names DaisyUI's documented list as
  the source, not a fixed name.
- [A swapped-in `<dialog open>` is not modal: no top layer, no focus trap] → said in the how-to;
  DaisyUI paints the backdrop and covers the page itself, which is what the Deploy-Radar needed.
