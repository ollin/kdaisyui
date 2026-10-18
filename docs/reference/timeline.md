<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/timeline/+page.md
Regenerate: just generate
-->

# Timeline

[DaisyUI documentation →](https://daisyui.com/components/timeline/)

Vertical or horizontal timeline. Renders `<ul class="timeline ...">`.

```kotlin
// TimelineDirection: Horizontal | Vertical
fun FlowContent.daisyTimeline(
    id: HtmlId? = null,
    direction: ClassValues<TimelineDirection>? = null,
    compact: Boolean = false,
    snapIcon: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTimelineClasses(direction: ClassValues<TimelineDirection>? = null, compact: Boolean = false, snapIcon: Boolean = false)
```

```kotlin
fun FlowContent.daisyTimelineStart(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTimelineStartClasses(box: Boolean = false)
```

```kotlin
fun FlowContent.daisyTimelineMiddle(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTimelineMiddleClasses(box: Boolean = false)
```

```kotlin
fun FlowContent.daisyTimelineEnd(
    id: HtmlId? = null,
    box: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyTimelineEndClasses(box: Boolean = false)
```
