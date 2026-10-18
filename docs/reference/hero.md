<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/hero/+page.md
Regenerate: just generate
-->

# Hero

[DaisyUI documentation →](https://daisyui.com/components/hero/)

Hero banner with overlay. Renders `<div class="hero ...">`.

```kotlin
fun FlowContent.daisyHero(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyHeroClasses()
```

```kotlin
fun FlowContent.daisyHeroContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyHeroContentClasses()
```

```kotlin
fun FlowContent.daisyHeroOverlay(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyHeroOverlayClasses()
```
