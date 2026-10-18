// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/stat/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

enum class StatsDirection(internal val className: String) : ClassValues<StatsDirection> {
    /** CSS: `stats-horizontal` */
    Horizontal("stats-horizontal"),
    /** CSS: `stats-vertical` */
    Vertical("stats-vertical"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Stat is used to show numbers and data in a block. Renders `<div class="stats ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyStats(
    id: HtmlId? = null,
    direction: ClassValues<StatsDirection>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stats")
        addClassNames(direction)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyStats` writes, on the element this is called on. */
fun Tag.daisyStatsClasses(
    direction: ClassValues<StatsDirection>? = null,
) {
    addClassNames("stats")
    addClassNames(direction)
}

/** Renders `<div class="stat ...">`. */
fun FlowContent.daisyStat(
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

/** The classes `daisyStat` writes, on the element this is called on. */
fun Tag.daisyStatClasses() {
    addClassNames("stat")
}

/** Renders `<div class="stat-title ...">`. */
fun FlowContent.daisyStatTitle(
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

/** The classes `daisyStatTitle` writes, on the element this is called on. */
fun Tag.daisyStatTitleClasses() {
    addClassNames("stat-title")
}

/** Renders `<div class="stat-value ...">`. */
fun FlowContent.daisyStatValue(
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

/** The classes `daisyStatValue` writes, on the element this is called on. */
fun Tag.daisyStatValueClasses() {
    addClassNames("stat-value")
}

/** Renders `<div class="stat-desc ...">`. */
fun FlowContent.daisyStatDesc(
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

/** The classes `daisyStatDesc` writes, on the element this is called on. */
fun Tag.daisyStatDescClasses() {
    addClassNames("stat-desc")
}

/** Renders `<div class="stat-figure ...">`. */
fun FlowContent.daisyStatFigure(
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

/** The classes `daisyStatFigure` writes, on the element this is called on. */
fun Tag.daisyStatFigureClasses() {
    addClassNames("stat-figure")
}

/** Renders `<div class="stat-actions ...">`. */
fun FlowContent.daisyStatActions(
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

/** The classes `daisyStatActions` writes, on the element this is called on. */
fun Tag.daisyStatActionsClasses() {
    addClassNames("stat-actions")
}
