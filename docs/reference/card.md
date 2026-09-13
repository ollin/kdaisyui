<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/card/+page.md
Regenerate: just generate
-->

# Card

[DaisyUI documentation →](https://daisyui.com/components/card/)

Content containers with body and title. Renders `<div class="card ...">`.

```kotlin
// CardSize: Xs | Sm | Md | Lg | Xl
// CardStyle: Border | Dash
// CardModifier: Side | ImageFull
fun FlowContent.daisyCard(
    id: HtmlId? = null,
    size: CardSize? = null,
    style: CardStyle? = null,
    modifier: CardModifier? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyCardTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)
```

```kotlin
fun FlowContent.daisyCardBody(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyCardActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
