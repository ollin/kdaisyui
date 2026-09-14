<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/steps/+page.md
Regenerate: just generate
-->

# Steps

[DaisyUI documentation →](https://daisyui.com/components/steps/)

Step-by-step progress indicator. Renders `<ul class="steps ...">`.

```kotlin
// StepsVariant: StepNeutral | StepPrimary | StepSecondary | StepAccent | StepInfo | StepSuccess | StepWarning | StepError
// StepsDirection: Vertical | Horizontal
fun FlowContent.daisySteps(
    id: HtmlId? = null,
    variant: ClassValues<StepsVariant>? = null,
    direction: ClassValues<StepsDirection>? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyStepsStep(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyStepsStepIcon(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
