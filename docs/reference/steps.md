<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/steps/+page.md
Regenerate: just generate
-->

# Steps

[DaisyUI documentation →](https://daisyui.com/components/steps/)

Step-by-step progress indicator. Renders `<ul class="steps ...">`.

```kotlin
// StepColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// StepsDirection: Vertical | Horizontal
fun FlowContent.daisySteps(
    id: HtmlId? = null,
    direction: ClassValues<StepsDirection>? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStepsClasses(direction: ClassValues<StepsDirection>? = null)
```

```kotlin
fun UL.daisyStep(
    id: HtmlId? = null,
    color: ClassValues<StepColor>? = null,
    extraClasses: String? = null,
    attrs: (LI.() -> Unit)? = null,
    content: (LI.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStepClasses(color: ClassValues<StepColor>? = null)
```

```kotlin
fun FlowContent.daisyStepIcon(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStepIconClasses()
```
