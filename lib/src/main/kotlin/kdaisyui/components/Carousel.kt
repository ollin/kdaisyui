// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/carousel/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyCarousel(
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

fun FlowContent.daisyCarouselItem(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("carousel-item")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
