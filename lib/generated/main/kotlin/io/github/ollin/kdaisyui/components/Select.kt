// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/select/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.select
import kotlinx.html.SELECT

enum class SelectVariant(internal val className: String) {
    /** CSS: `select-neutral` */
    Neutral("select-neutral"),
    /** CSS: `select-primary` */
    Primary("select-primary"),
    /** CSS: `select-secondary` */
    Secondary("select-secondary"),
    /** CSS: `select-accent` */
    Accent("select-accent"),
    /** CSS: `select-info` */
    Info("select-info"),
    /** CSS: `select-success` */
    Success("select-success"),
    /** CSS: `select-warning` */
    Warning("select-warning"),
    /** CSS: `select-error` */
    Error("select-error"),
}

enum class SelectSize(internal val className: String) {
    /** CSS: `select-xs` */
    Xs("select-xs"),
    /** CSS: `select-sm` */
    Sm("select-sm"),
    /** CSS: `select-md` */
    Md("select-md"),
    /** CSS: `select-lg` */
    Lg("select-lg"),
    /** CSS: `select-xl` */
    Xl("select-xl"),
}


/**
 * Select is used to pick a value from a list of options. Renders `<select class="select ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param ghost
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySelect(
    id: HtmlId? = null,
    variant: SelectVariant? = null,
    size: SelectSize? = null,
    ghost: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (SELECT.() -> Unit)? = null,
    content: (SELECT.() -> Unit),
) {
    select {
        if (id != null) attributes["id"] = id.id
        addClassNames("select")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("select-ghost")
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
