// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/rating/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Size variants for this component (CSS prefix: `rating-`) */
enum class RatingSize(internal val className: String) {
    /** CSS: `rating-xs` — Extra small size */
    Xs("rating-xs"),
    /** CSS: `rating-sm` — Small size */
    Sm("rating-sm"),
    /** CSS: `rating-md` — Medium size */
    Md("rating-md"),
    /** CSS: `rating-lg` — Large size */
    Lg("rating-lg"),
    /** CSS: `rating-xl` — Extra large size */
    Xl("rating-xl"),
}


/**
 * Rating is a set of radio buttons that allow the user to rate something. Renders `<div class="rating ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param half — To shows half of the shapes. Useful for half star ratings
 * @param hidden — For the first radio to make it hidden so user can clear the rating
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyRating(
    id: HtmlId? = null,
    size: RatingSize? = null,
    half: Boolean = false,
    hidden: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("rating")
        if (size != null) addClassNames(size.className)
        if (half) addClassNames("rating-half")
        if (hidden) addClassNames("rating-hidden")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
