// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Carousel show images or content in a scrollable area. Renders `<div class="carousel ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param center — Snap elements to center
 * @param end — Snap elements to end
 * @param horizontal — Horizontal layout (default)
 * @param start — Snap elements to start
 * @param vertical — Vertical layout
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCarousel(
    id: HtmlId? = null,
    center: Boolean = false,
    end: Boolean = false,
    horizontal: Boolean = false,
    start: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("carousel")
        if (center) addClassNames("carousel-center")
        if (end) addClassNames("carousel-end")
        if (horizontal) addClassNames("carousel-horizontal")
        if (start) addClassNames("carousel-start")
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
