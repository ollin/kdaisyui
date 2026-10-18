<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/progress/+page.md
Regenerate: just generate
-->

# Progress

[DaisyUI documentation →](https://daisyui.com/components/progress/)

Progress bar indicator. Renders `<progress class="progress ...">`.

```kotlin
// ProgressColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
fun FlowContent.daisyProgress(
    id: HtmlId? = null,
    color: ClassValues<ProgressColor>? = null,
    extraClasses: String? = null,
    attrs: (PROGRESS.() -> Unit)? = null,
    content: (PROGRESS.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyProgressClasses(color: ClassValues<ProgressColor>? = null)
```
