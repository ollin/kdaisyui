<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/swap/+page.md
Regenerate: just generate
-->

# Swap

[DaisyUI documentation →](https://daisyui.com/components/swap/)

Toggle between two states. Renders `<label class="swap ...">`.

```kotlin
fun FlowContent.daisySwap(
    id: HtmlId? = null,
    active: Boolean = false,  // Activates the swap (no need for checkbox)
    flip: Boolean = false,  // Adds flip effect to swap
    rotate: Boolean = false,  // Adds rotate effect to swap
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
)
```

```kotlin
fun FlowContent.daisySwapOn(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisySwapOff(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisySwapIndeterminate(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
