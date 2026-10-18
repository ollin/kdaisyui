<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/fieldset/+page.md
Regenerate: just generate
-->

# Fieldset

[DaisyUI documentation →](https://daisyui.com/components/fieldset/)

Groups related form controls. Renders `<fieldset class="fieldset ...">`.

```kotlin
fun FlowContent.daisyFieldset(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FIELDSET.() -> Unit)? = null,
    content: (FIELDSET.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFieldsetClasses()
```

```kotlin
fun FIELDSET.daisyFieldsetLegend(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (LEGEND.() -> Unit)? = null,
    content: (LEGEND.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyFieldsetLegendClasses()
```
