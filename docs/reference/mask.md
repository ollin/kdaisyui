<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/mask/+page.md
Regenerate: just generate
-->

# Mask

[DaisyUI documentation →](https://daisyui.com/components/mask/)

CSS masks for cropping images to shapes. Renders `<img class="mask ...">`.

```kotlin
// MaskShape: Squircle | Heart | Hexagon | Hexagon2 | Decagon | Pentagon | Diamond | Square | Circle | Star | Star2 | Triangle | Triangle2 | Triangle3 | Triangle4
// MaskModifier: Half1 | Half2
fun FlowContent.daisyMask(
    id: HtmlId? = null,
    shape: MaskShape? = null,
    modifier: MaskModifier? = null,
    extraClasses: String? = null,
    attrs: (IMG.() -> Unit)? = null,
)
```
