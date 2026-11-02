<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/skeleton/+page.md
Regenerate: just generate
-->

# Skeleton

[DaisyUI documentation →](https://daisyui.com/components/skeleton/)

Content placeholder loading indicator. Renders `<div class="skeleton ...">`.

```kotlin
fun FlowContent.daisySkeleton(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisySkeletonClasses()
```

```kotlin
fun FlowContent.daisySkeletonText(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisySkeletonTextClasses()
```
