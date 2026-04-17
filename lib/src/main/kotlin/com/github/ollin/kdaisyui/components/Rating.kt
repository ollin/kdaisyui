// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/rating/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class RatingSize(internal val className: String) {
    Xs("rating-xs"),
    Sm("rating-sm"),
    Md("rating-md"),
    Lg("rating-lg"),
    Xl("rating-xl"),
}


fun FlowContent.daisyRating(
    size: RatingSize? = null,
    half: Boolean = false,
    hidden: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("rating")
        if (size != null) addClassNames(size.className)
        if (half) addClassNames("rating-half")
        if (hidden) addClassNames("rating-hidden")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
