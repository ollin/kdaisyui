<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/aura/+page.md
Regenerate: just generate
-->

# Aura

[DaisyUI documentation →](https://daisyui.com/components/aura/)

Border light effect that wraps around any component, for highlighting a single important button, card or container. Renders `<div class="aura ...">`.

```kotlin
// AuraSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyAura(
    id: HtmlId? = null,
    size: AuraSize? = null,
    dual: Boolean = false,  // Uses two light effects
    glow: Boolean = false,  // Uses a glowing effect for the light instead of a rotating border
    gold: Boolean = false,  // Uses gold colors for the light effect
    holo: Boolean = false,  // Uses holographic colors for the light effect
    rainbow: Boolean = false,  // Uses rainbow colors for the light effect
    silver: Boolean = false,  // Uses silver colors for the light effect
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
