// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/card/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.h2
import kotlinx.html.H2
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `card-`) */
enum class CardSize(internal val className: String) : ClassValues<CardSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Style variants for this component (CSS prefix: `card-`) */
enum class CardStyle(internal val className: String) : ClassValues<CardStyle> {
    /** CSS: `card-border` — Adds border to <card> */
    Border("card-border"),
    /** CSS: `card-dash` — dash style */
    Dash("card-dash"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Layout variants for this component (CSS prefix: `card-`) */
enum class CardLayout(internal val className: String) : ClassValues<CardLayout> {
    /** CSS: `card-side` — The image in <figure> will be on to the side */
    Side("card-side"),
    /** CSS: `image-full` — The image in <figure> element will be the background */
    ImageFull("image-full"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Cards are used to group and display content in a way that is easily readable. Renders `<div class="card ...">`.
 * The same classes on another element: `a { daisyCardClasses(…) }`; see [daisyCardClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param style — Style variant
 * @param layout — Layout variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyCard(
    id: HtmlId? = null,
    size: ClassValues<CardSize>? = null,
    style: ClassValues<CardStyle>? = null,
    layout: ClassValues<CardLayout>? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card")
        addClassNames(size)
        addClassNames(style)
        addClassNames(layout)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCard` writes, on the element this is called on. */
fun Tag.daisyCardClasses(
    size: ClassValues<CardSize>? = null,
    style: ClassValues<CardStyle>? = null,
    layout: ClassValues<CardLayout>? = null,
) {
    addClassNames("card")
    addClassNames(size)
    addClassNames(style)
    addClassNames(layout)
}

/**
 * Renders `<h2 class="card-title ...">`.
 * The same classes on another element: `a { daisyCardTitleClasses(…) }`; see [daisyCardTitleClasses].
 */
fun FlowContent.daisyCardTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (H2.() -> Unit) = {},
) {
    h2 {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-title")
        addClassNames(extraClasses)
        block()
        if (text != null) +text
    }
}

/** The classes `daisyCardTitle` writes, on the element this is called on. */
fun Tag.daisyCardTitleClasses() {
    addClassNames("card-title")
}

/**
 * Renders `<div class="card-body ...">`.
 * The same classes on another element: `a { daisyCardBodyClasses(…) }`; see [daisyCardBodyClasses].
 */
fun FlowContent.daisyCardBody(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-body")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCardBody` writes, on the element this is called on. */
fun Tag.daisyCardBodyClasses() {
    addClassNames("card-body")
}

/**
 * Renders `<div class="card-actions ...">`.
 * The same classes on another element: `a { daisyCardActionsClasses(…) }`; see [daisyCardActionsClasses].
 */
fun FlowContent.daisyCardActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("card-actions")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyCardActions` writes, on the element this is called on. */
fun Tag.daisyCardActionsClasses() {
    addClassNames("card-actions")
}
