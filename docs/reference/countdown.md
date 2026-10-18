<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/countdown/+page.md
Regenerate: just generate
-->

# Countdown

[DaisyUI documentation →](https://daisyui.com/components/countdown/)

Timer countdown display. Renders `<span class="countdown ...">`.

```kotlin
fun FlowContent.daisyCountdown(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCountdownClasses()
```
