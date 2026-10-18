<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/validator/+page.md
Regenerate: just generate
-->

# Validator

[DaisyUI documentation →](https://daisyui.com/components/validator/)

Form validation wrapper. Renders `<input class="validator ...">`.

```kotlin
fun FlowContent.daisyValidator(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyValidatorClasses()
```

```kotlin
fun FlowContent.daisyValidatorHint(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (P.() -> Unit)? = null,
    content: (P.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyValidatorHintClasses()
```
