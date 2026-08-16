// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/divider/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Color variants for this component (CSS prefix: `divider-`) */
enum class DividerVariant(internal val className: String) {
    /** CSS: `divider-neutral` — neutral color */
    Neutral("divider-neutral"),
    /** CSS: `divider-primary` — primary color */
    Primary("divider-primary"),
    /** CSS: `divider-secondary` — secondary color */
    Secondary("divider-secondary"),
    /** CSS: `divider-accent` — accent color */
    Accent("divider-accent"),
    /** CSS: `divider-success` — success color */
    Success("divider-success"),
    /** CSS: `divider-warning` — warning color */
    Warning("divider-warning"),
    /** CSS: `divider-info` — info color */
    Info("divider-info"),
    /** CSS: `divider-error` — error color */
    Error("divider-error"),
}


/**
 * Divider will be used to separate content vertically or horizontally. Renders `<div class="divider ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param end — Pushes the divider text to the end
 * @param horizontal — Divide horizontal elements (next to each other)
 * @param start — Pushes the divider text to the start
 * @param vertical — Divide vertical elements (on top of each other)
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyDivider(
    id: HtmlId? = null,
    variant: DividerVariant? = null,
    end: Boolean = false,
    horizontal: Boolean = false,
    start: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("divider")
        if (variant != null) addClassNames(variant.className)
        if (end) addClassNames("divider-end")
        if (horizontal) addClassNames("divider-horizontal")
        if (start) addClassNames("divider-start")
        if (vertical) addClassNames("divider-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
