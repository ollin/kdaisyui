// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/status/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.role

/** Color variants for this component (CSS prefix: `status-`) */
enum class StatusVariant(internal val className: String) : ClassValues<StatusVariant> {
    /** CSS: `status-neutral` — neutral color */
    Neutral("status-neutral"),
    /** CSS: `status-primary` — primary color */
    Primary("status-primary"),
    /** CSS: `status-secondary` — secondary color */
    Secondary("status-secondary"),
    /** CSS: `status-accent` — accent color */
    Accent("status-accent"),
    /** CSS: `status-info` — info color */
    Info("status-info"),
    /** CSS: `status-success` — success color */
    Success("status-success"),
    /** CSS: `status-warning` — warning color */
    Warning("status-warning"),
    /** CSS: `status-error` — error color */
    Error("status-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Size variants for this component (CSS prefix: `status-`) */
enum class StatusSize(internal val className: String) : ClassValues<StatusSize> {
    /** CSS: `status-xs` — extra small size */
    Xs("status-xs"),
    /** CSS: `status-sm` — small size */
    Sm("status-sm"),
    /** CSS: `status-md` — medium size */
    Md("status-md"),
    /** CSS: `status-lg` — large size */
    Lg("status-lg"),
    /** CSS: `status-xl` — extra large size */
    Xl("status-xl"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Status is a really small icon to visually show the current status of an element, like online, offline, error, etc. Renders `<div class="status ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyStatus(
    id: HtmlId? = null,
    variant: ClassValues<StatusVariant>? = null,
    size: ClassValues<StatusSize>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        role = "status"
        addClassNames("status")
        addClassNames(variant)
        addClassNames(size)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
