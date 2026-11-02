// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/navbar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Navbar is used to show a navigation bar on the top of the page. Renders `<div class="navbar ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyNavbar(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("navbar")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyNavbar` writes, on the element this is called on. */
fun Tag.daisyNavbarClasses() {
    addClassNames("navbar")
}

/** Renders `<div class="navbar-start ...">`. */
fun FlowContent.daisyNavbarStart(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("navbar-start")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyNavbarStart` writes, on the element this is called on. */
fun Tag.daisyNavbarStartClasses() {
    addClassNames("navbar-start")
}

/** Renders `<div class="navbar-center ...">`. */
fun FlowContent.daisyNavbarCenter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("navbar-center")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyNavbarCenter` writes, on the element this is called on. */
fun Tag.daisyNavbarCenterClasses() {
    addClassNames("navbar-center")
}

/** Renders `<div class="navbar-end ...">`. */
fun FlowContent.daisyNavbarEnd(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("navbar-end")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyNavbarEnd` writes, on the element this is called on. */
fun Tag.daisyNavbarEndClasses() {
    addClassNames("navbar-end")
}
