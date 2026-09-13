<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
Regenerate: just generate
-->

# Carousel

[DaisyUI documentation →](https://daisyui.com/components/carousel/)

Slideshow of images or content. Renders `<div class="carousel ...">`.

```kotlin
// CarouselModifier: Start | Center | End
fun FlowContent.daisyCarousel(
    id: HtmlId? = null,
    modifier: CarouselModifier? = null,
    horizontal: Boolean = false,  // Horizontal layout (default)
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
