<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/diff/+page.md
Regenerate: just generate
-->

# Diff

[DaisyUI documentation →](https://daisyui.com/components/diff/)

Side-by-side diff viewer. Renders `<figure class="diff ...">`.

```kotlin
fun FlowContent.daisyDiff(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FIGURE.() -> Unit)? = null,
    content: (FIGURE.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDiffClasses()
```

```kotlin
fun FlowContent.daisyDiffItem1(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDiffItem1Classes()
```

```kotlin
fun FlowContent.daisyDiffItem2(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDiffItem2Classes()
```

```kotlin
fun FlowContent.daisyDiffResizer(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyDiffResizerClasses()
```
