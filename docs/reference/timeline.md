<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/timeline/+page.md
Regenerate: just generate
-->

# Timeline

[DaisyUI documentation →](https://daisyui.com/components/timeline/)

Vertical or horizontal timeline. Renders `<ul class="timeline ...">`.

```kotlin
fun FlowContent.daisyTimeline(
    id: HtmlId? = null,
    box: Boolean = false,
    compact: Boolean = false,
    horizontal: Boolean = false,
    snapIcon: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTimelineStart(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTimelineMiddle(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyTimelineEnd(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
