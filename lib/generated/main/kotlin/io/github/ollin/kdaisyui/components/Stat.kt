// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/stat/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class StatDirection(internal val className: String) {
    /** CSS: `stats-horizontal` */
    Horizontal("stats-horizontal"),
    /** CSS: `stats-vertical` */
    Vertical("stats-vertical"),
}


/**
 * Stat is used to show numbers and data in a block. Renders `<div class="stats ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyStat(
    id: HtmlId? = null,
    direction: StatDirection? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stats")
        if (direction != null) addClassNames(direction.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="stat ...">`. */
fun FlowContent.daisyStatStat(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="stat-title ...">`. */
fun FlowContent.daisyStatStatTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat-title")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="stat-value ...">`. */
fun FlowContent.daisyStatStatValue(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat-value")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="stat-desc ...">`. */
fun FlowContent.daisyStatStatDesc(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat-desc")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

/** Renders `<div class="stat-figure ...">`. */
fun FlowContent.daisyStatStatFigure(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat-figure")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="stat-actions ...">`. */
fun FlowContent.daisyStatStatActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stat-actions")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
