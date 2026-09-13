// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/card/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2

/** Size variants for this component (CSS prefix: `card-`) */
enum class CardSize(internal val className: String) {
    /** CSS: `card-xs` — Extra small size */
    Xs("card-xs"),
    /** CSS: `card-sm` — Small size */
    Sm("card-sm"),
    /** CSS: `card-md` — Medium size (default) */
    Md("card-md"),
    /** CSS: `card-lg` — Large size */
    Lg("card-lg"),
    /** CSS: `card-xl` — Extra large size */
    Xl("card-xl"),
}

/** Style variants for this component (CSS prefix: `card-`) */
enum class CardStyle(internal val className: String) {
    /** CSS: `card-border` — Adds border to <card> */
    Border("card-border"),
    /** CSS: `card-dash` — dash style */
    Dash("card-dash"),
}

/** Modifier variants for this component (CSS prefix: `card-`) */
enum class CardModifier(internal val className: String) {
    /** CSS: `card-side` — The image in <figure> will be on to the side */
    Side("card-side"),
    /** CSS: `card-image-full` — The image in <figure> element will be the background */
    ImageFull("card-image-full"),
}


/**
 * Cards are used to group and display content in a way that is easily readable. Renders `<div class="card ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param style — Style variant
 * @param modifier — Modifier variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyCard(
    id: HtmlId? = null,
    size: CardSize? = null,
    style: CardStyle? = null,
    modifier: CardModifier? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card")
        if (size != null) addClassNames(size.className)
        if (style != null) addClassNames(style.className)
        if (modifier != null) addClassNames(modifier.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<h2 class="card-title ...">`. */
fun FlowContent.daisyCardTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="card-body ...">`. */
fun FlowContent.daisyCardBody(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-body")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="card-actions ...">`. */
fun FlowContent.daisyCardActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-actions")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
