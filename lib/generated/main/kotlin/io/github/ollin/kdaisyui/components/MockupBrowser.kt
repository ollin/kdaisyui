// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mockup-browser/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Browser mockup shows a box that looks like a browser window. Renders `<div class="mockup-browser ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyMockupBrowser(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-browser")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupBrowser` writes, on the element this is called on. */
fun Tag.daisyMockupBrowserClasses() {
    addClassNames("mockup-browser")
}

/** Renders `<div class="mockup-browser-toolbar ...">`. */
fun FlowContent.daisyMockupBrowserToolbar(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("mockup-browser-toolbar")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyMockupBrowserToolbar` writes, on the element this is called on. */
fun Tag.daisyMockupBrowserToolbarClasses() {
    addClassNames("mockup-browser-toolbar")
}
