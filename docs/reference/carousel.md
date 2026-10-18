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

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCarouselClasses(snap: ClassValues<CarouselSnap>? = null, direction: ClassValues<CarouselDirection>? = null)
```

```kotlin
fun FlowContent.daisyCarouselItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCarouselItemClasses()
```
