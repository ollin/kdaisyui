<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/rating/+page.md
Regenerate: just generate
-->

# Rating

[DaisyUI documentation →](https://daisyui.com/components/rating/)

Star rating input. Renders `<div class="rating ...">`.

```kotlin
// RatingSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyRating(
    id: HtmlId? = null,
    size: ClassValues<RatingSize>? = null,
    half: Boolean = false,  // To shows half of the shapes. Useful for half star ratings
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyRatingClasses(size: ClassValues<RatingSize>? = null, half: Boolean = false)
```

```kotlin
// Classes only, for a child DaisyUI gives no class of its own:
fun Tag.daisyRatingItemClasses(hidden: Boolean = false)
```
