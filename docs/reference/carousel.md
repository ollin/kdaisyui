<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
Regenerate: just generate
-->

# Carousel

[DaisyUI documentation →](https://daisyui.com/components/carousel/)

Slideshow of images or content. Renders `<div class="carousel ...">`.

```kotlin
// CarouselSnap: Start | Center | End
// CarouselDirection: Horizontal | Vertical
fun FlowContent.daisyCarousel(
    id: HtmlId? = null,
    snap: ClassValues<CarouselSnap>? = null,
    direction: ClassValues<CarouselDirection>? = null,
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
