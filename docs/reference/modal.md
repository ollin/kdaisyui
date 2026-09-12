<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md
Regenerate: just generate
-->

# Modal

[DaisyUI documentation →](https://daisyui.com/components/modal/)

Modal dialogs. Renders `<dialog class="modal ...">`.

```kotlin
fun FlowContent.daisyModal(
    id: HtmlId? = null,
    bottom: Boolean = false,  // Moves the modal to bottom
    end: Boolean = false,  // Moves the modal to end horizontally
    middle: Boolean = false,  // Moves the modal to middle
    open: Boolean = false,  // Keeps the modal open (you can add this class using JS)
    start: Boolean = false,  // Moves the modal to start horizontally
    top: Boolean = false,  // Moves the modal to top
    extraClasses: String? = null,
    attrs: (DIALOG.() -> Unit)? = null,
    content: (DIALOG.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyModalBox(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyModalAction(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyModalBackdrop(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyModalToggle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyModalPopover(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
