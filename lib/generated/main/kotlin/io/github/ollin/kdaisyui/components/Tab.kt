// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Size variants for this component (CSS prefix: `tabs-`) */
enum class TabSize(internal val className: String) {
    /** CSS: `tabs-xs` — Extra small size */
    Xs("tabs-xs"),
    /** CSS: `tabs-sm` — Small size */
    Sm("tabs-sm"),
    /** CSS: `tabs-md` — Medium size */
    Md("tabs-md"),
    /** CSS: `tabs-lg` — Large size */
    Lg("tabs-lg"),
    /** CSS: `tabs-xl` — Extra large size */
    Xl("tabs-xl"),
}


/**
 * Tabs can be used to show a list of links in a tabbed format. Renders `<button class="tabs ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param border — bottom border style
 * @param bottom — Puts tabs on under the tab-content
 * @param box — box style
 * @param lift — lift style
 * @param tabActive — Makes a single tab look active
 * @param tabDisabled — Makes a single tab look disabled
 * @param top — Puts tab buttons on top of the tab-content (default)
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    size: TabSize? = null,
    border: Boolean = false,
    bottom: Boolean = false,
    box: Boolean = false,
    lift: Boolean = false,
    tabActive: Boolean = false,
    tabDisabled: Boolean = false,
    top: Boolean = false,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit),
) {
    button {
        if (id != null) attributes["id"] = id.id
        addClassNames("tabs")
        if (size != null) addClassNames(size.className)
        if (border) addClassNames("tabs-border")
        if (bottom) addClassNames("tabs-bottom")
        if (box) addClassNames("tabs-box")
        if (lift) addClassNames("tabs-lift")
        if (tabActive) addClassNames("tabs-tab-active")
        if (tabDisabled) addClassNames("tabs-tab-disabled")
        if (top) addClassNames("tabs-top")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** A single tab button (can be button, link, div, radio input, etc) Renders `<div class="tab ...">`. */
fun FlowContent.daisyTabTab(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tab")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Tab content that comes immediately after a tab Renders `<div class="tab-content ...">`. */
fun FlowContent.daisyTabTabContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("tab-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
