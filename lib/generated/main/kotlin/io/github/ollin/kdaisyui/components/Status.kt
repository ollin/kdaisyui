// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/status/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.role
import kotlinx.html.span
import kotlinx.html.SPAN

/** Color variants for this component (CSS prefix: `status-`) */
enum class StatusVariant(internal val className: String) {
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
}

/** Size variants for this component (CSS prefix: `status-`) */
enum class StatusSize(internal val className: String) {
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
}


/**
 * Status is a really small icon to visually show the current status of an element, like online, offline, error, etc. Renders `<span class="status ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyStatus(
    id: HtmlId? = null,
    variant: StatusVariant? = null,
    size: StatusSize? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        role = "status"
        addClassNames("status")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
