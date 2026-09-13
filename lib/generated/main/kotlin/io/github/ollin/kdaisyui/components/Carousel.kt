// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Modifier variants for this component (CSS prefix: `carousel-`) */
enum class CarouselModifier(internal val className: String) : ClassValues<CarouselModifier> {
    /** CSS: `carousel-start` — Snap elements to start */
    Start("carousel-start"),
    /** CSS: `carousel-center` — Snap elements to center */
    Center("carousel-center"),
    /** CSS: `carousel-end` — Snap elements to end */
    End("carousel-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Carousel show images or content in a scrollable area. Renders `<div class="carousel ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param modifier — Modifier variant
 * @param horizontal — Horizontal layout (default)
 * @param vertical — Vertical layout
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCarousel(
    id: HtmlId? = null,
    modifier: ClassValues<CarouselModifier>? = null,
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("carousel")
        addClassNames(modifier)
        if (horizontal) addClassNames("carousel-horizontal")
        if (vertical) addClassNames("carousel-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="carousel-item ...">`. */
fun FlowContent.daisyCarouselItem(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("carousel-item")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
