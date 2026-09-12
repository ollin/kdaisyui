<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
Regenerate: just generate
-->

# Carousel

[DaisyUI documentation →](https://daisyui.com/components/carousel/)

Slideshow of images or content. Renders `<div class="carousel ...">`.

```kotlin
fun FlowContent.daisyCarousel(
    id: HtmlId? = null,
    center: Boolean = false,  // Snap elements to center
    end: Boolean = false,  // Snap elements to end
    horizontal: Boolean = false,  // Horizontal layout (default)
    start: Boolean = false,  // Snap elements to start
    vertical: Boolean = false,  // Vertical layout
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyCarouselItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
