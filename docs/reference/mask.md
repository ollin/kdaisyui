<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/mask/+page.md
Regenerate: just generate
-->

# Mask

[DaisyUI documentation →](https://daisyui.com/components/mask/)

CSS masks for cropping images to shapes. Renders `<img class="mask ...">`.

```kotlin
fun FlowContent.daisyMask(
    id: HtmlId? = null,
    circle: Boolean = false,  // circle
    decagon: Boolean = false,  // decagon
    diamond: Boolean = false,  // diamond
    half1: Boolean = false,  // Crops only the first half of mask
    half2: Boolean = false,  // Crops only the second half of mask
    heart: Boolean = false,  // heart
    hexagon: Boolean = false,  // hexagon vertical
    hexagon2: Boolean = false,  // hexagon horizontal
    pentagon: Boolean = false,  // pentagon
    square: Boolean = false,  // square
    squircle: Boolean = false,  // squircle
    star: Boolean = false,  // star
    star2: Boolean = false,  // star (bold)
    triangle: Boolean = false,  // triangle pointing top
    triangle2: Boolean = false,  // triangle pointing down
    triangle3: Boolean = false,  // triangle pointing left
    triangle4: Boolean = false,  // triangle pointing right
    extraClasses: String? = null,
    attrs: (IMG.() -> Unit)? = null,
)
```
