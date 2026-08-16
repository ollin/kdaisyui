# Aura

[DaisyUI documentation →](https://daisyui.com/components/aura/)

Border light effect that wraps around any component, for highlighting a single important
button, card or container. Renders `<div class="aura ...">`.

```kotlin
// AuraSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyAura(
    id: HtmlId? = null,
    size: AuraSize? = null,
    dual: Boolean = false,      // two light effects
    glow: Boolean = false,      // glow instead of a rotating border
    gold: Boolean = false,
    holo: Boolean = false,
    rainbow: Boolean = false,
    silver: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
