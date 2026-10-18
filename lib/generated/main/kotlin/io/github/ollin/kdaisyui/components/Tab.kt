// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.a
import kotlinx.html.A
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `tabs-`) */
enum class TabsSize(internal val className: String) : ClassValues<TabsSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Placement variants for this component (CSS prefix: `tabs-`) */
enum class TabsPlacement(internal val className: String) : ClassValues<TabsPlacement> {
    /** CSS: `tabs-top` — Puts tab buttons on top of the tab-content (default) */
    Top("tabs-top"),
    /** CSS: `tabs-bottom` — Puts tabs on under the tab-content */
    Bottom("tabs-bottom"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Style variants for this component (CSS prefix: `tabs-`) */
enum class TabsStyle(internal val className: String) : ClassValues<TabsStyle> {
    /** CSS: `tabs-box` — box style */
    Box("tabs-box"),
    /** CSS: `tabs-border` — bottom border style */
    Border("tabs-border"),
    /** CSS: `tabs-lift` — lift style */
    Lift("tabs-lift"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Tabs can be used to show a list of links in a tabbed format. Renders `<div class="tabs ..." role="tablist">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param placement — Placement variant
 * @param style — Style variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTabs(
    id: HtmlId? = null,
    size: ClassValues<TabsSize>? = null,
    placement: ClassValues<TabsPlacement>? = null,
    style: ClassValues<TabsStyle>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        attributes["role"] = "tablist"
        addClassNames("tabs")
        addClassNames(size)
        addClassNames(placement)
        addClassNames(style)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyTabs` writes, on the element this is called on. */
fun Tag.daisyTabsClasses(
    size: ClassValues<TabsSize>? = null,
    placement: ClassValues<TabsPlacement>? = null,
    style: ClassValues<TabsStyle>? = null,
) {
    addClassNames("tabs")
    addClassNames(size)
    addClassNames(placement)
    addClassNames(style)
}

/** A single tab button (can be button, link, div, radio input, etc) Renders `<a class="tab ..." role="tab">`. */
fun FlowContent.daisyTab(
    id: HtmlId? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit),
) {
    a {
        if (id != null) attributes["id"] = id.id
        attributes["role"] = "tab"
        addClassNames("tab")
        if (active) addClassNames("tab-active")
        if (disabled) addClassNames("tab-disabled")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyTab` writes, on the element this is called on. */
fun Tag.daisyTabClasses(
    active: Boolean = false,
    disabled: Boolean = false,
) {
    addClassNames("tab")
    if (active) addClassNames("tab-active")
    if (disabled) addClassNames("tab-disabled")
}

/** Renders `<input class="tab ..." type="radio" role="tab">`. */
fun FlowContent.daisyTabRadio(
    id: HtmlId? = null,
    active: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        attributes["type"] = "radio"
        attributes["role"] = "tab"
        addClassNames("tab")
        if (active) addClassNames("tab-active")
        if (disabled) addClassNames("tab-disabled")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}

/** The classes `daisyTabRadio` writes, on the element this is called on. */
fun Tag.daisyTabRadioClasses(
    active: Boolean = false,
    disabled: Boolean = false,
) {
    addClassNames("tab")
    if (active) addClassNames("tab-active")
    if (disabled) addClassNames("tab-disabled")
}

/** Tab content that comes immediately after a tab Renders `<div class="tab-content ...">`. */
fun FlowContent.daisyTabContent(
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

/** The classes `daisyTabContent` writes, on the element this is called on. */
fun Tag.daisyTabContentClasses() {
    addClassNames("tab-content")
}
