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
// AuraStyle: Dual | Rainbow | Holo | Gold | Silver | Glow
fun FlowContent.daisyAura(
    id: HtmlId? = null,
    size: ClassValues<AuraSize>? = null,
    style: ClassValues<AuraStyle>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyAuraClasses(size: ClassValues<AuraSize>? = null, style: ClassValues<AuraStyle>? = null)
```
