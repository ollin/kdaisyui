<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/swap/+page.md
Regenerate: just generate
-->

# Swap

[DaisyUI documentation →](https://daisyui.com/components/swap/)

Toggle between two states. Renders `<label class="swap ...">`.

```kotlin
// SwapStyle: Rotate | Flip
fun FlowContent.daisySwap(
    id: HtmlId? = null,
    style: ClassValues<SwapStyle>? = null,
    active: Boolean = false,  // Activates the swap (no need for checkbox)
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
