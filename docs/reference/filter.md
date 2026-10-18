<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/filter/+page.md
Regenerate: just generate
-->

# Filter

[DaisyUI documentation →](https://daisyui.com/components/filter/)

Filter reset for search and forms. Renders `<form class="filter ...">`.

```kotlin
fun FlowContent.daisyFilter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FORM.() -> Unit)? = null,
    content: (FORM.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFilterClasses()
```

```kotlin
fun FlowContent.daisyFilterReset(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFilterResetClasses()
```
