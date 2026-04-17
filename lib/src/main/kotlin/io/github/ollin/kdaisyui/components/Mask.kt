// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mask/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.img
import kotlinx.html.IMG

fun FlowContent.daisyMask(
    circle: Boolean = false,
    decagon: Boolean = false,
    diamond: Boolean = false,
    half1: Boolean = false,
    half2: Boolean = false,
    heart: Boolean = false,
    hexagon: Boolean = false,
    hexagon2: Boolean = false,
    pentagon: Boolean = false,
    square: Boolean = false,
    squircle: Boolean = false,
    star: Boolean = false,
    star2: Boolean = false,
    triangle: Boolean = false,
    triangle2: Boolean = false,
    triangle3: Boolean = false,
    triangle4: Boolean = false,
    extraClasses: String? = null,
    attrs: (IMG.() -> Unit)? = null,
    content: (IMG.() -> Unit),
) {
    img {
        addClassNames("mask")
        if (circle) addClassNames("mask-circle")
        if (decagon) addClassNames("mask-decagon")
        if (diamond) addClassNames("mask-diamond")
        if (half1) addClassNames("mask-half-1")
        if (half2) addClassNames("mask-half-2")
        if (heart) addClassNames("mask-heart")
        if (hexagon) addClassNames("mask-hexagon")
        if (hexagon2) addClassNames("mask-hexagon-2")
        if (pentagon) addClassNames("mask-pentagon")
        if (square) addClassNames("mask-square")
        if (squircle) addClassNames("mask-squircle")
        if (star) addClassNames("mask-star")
        if (star2) addClassNames("mask-star-2")
        if (triangle) addClassNames("mask-triangle")
        if (triangle2) addClassNames("mask-triangle-2")
        if (triangle3) addClassNames("mask-triangle-3")
        if (triangle4) addClassNames("mask-triangle-4")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
