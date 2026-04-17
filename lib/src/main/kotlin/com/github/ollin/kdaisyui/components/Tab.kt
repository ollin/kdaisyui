// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/tab/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.button
import kotlinx.html.BUTTON
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class TabSize(internal val className: String) {
    Xs("tabs-xs"),
    Sm("tabs-sm"),
    Md("tabs-md"),
    Lg("tabs-lg"),
    Xl("tabs-xl"),
}


fun FlowContent.daisyTab(
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

fun FlowContent.daisyTabTab(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("tab")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyTabTabContent(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("tab-content")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
