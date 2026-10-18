<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/modal/+page.md
Regenerate: just generate
-->

# Modal

[DaisyUI documentation →](https://daisyui.com/components/modal/)

Modal dialogs. Renders `<dialog class="modal ...">`.

```kotlin
// ModalVerticalPlacement: Top | Middle | Bottom
// ModalHorizontalPlacement: Start | End
fun FlowContent.daisyModal(
    id: HtmlId? = null,
    verticalPlacement: ClassValues<ModalVerticalPlacement>? = null,
    horizontalPlacement: ClassValues<ModalHorizontalPlacement>? = null,
    open: Boolean = false,  // Keeps the modal open (you can add this class using JS)
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
    attrs: (INPUT.() -> Unit)? = null,
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
