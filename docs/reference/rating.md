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
    size: RatingSize? = null,
    half: Boolean = false,  // To shows half of the shapes. Useful for half star ratings
    hidden: Boolean = false,  // For the first radio to make it hidden so user can clear the rating
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
