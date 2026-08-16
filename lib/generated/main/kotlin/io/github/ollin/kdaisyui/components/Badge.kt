// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/badge/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

/** Color variants for this component (CSS prefix: `badge-`) */
enum class BadgeVariant(internal val className: String) {
    /** CSS: `badge-neutral` — neutral color */
    Neutral("badge-neutral"),
    /** CSS: `badge-primary` — primary color */
    Primary("badge-primary"),
    /** CSS: `badge-secondary` — secondary color */
    Secondary("badge-secondary"),
    /** CSS: `badge-accent` — accent color */
    Accent("badge-accent"),
    /** CSS: `badge-info` — info color */
    Info("badge-info"),
    /** CSS: `badge-success` — success color */
    Success("badge-success"),
    /** CSS: `badge-warning` — warning color */
    Warning("badge-warning"),
    /** CSS: `badge-error` — error color */
    Error("badge-error"),
}

/** Size variants for this component (CSS prefix: `badge-`) */
enum class BadgeSize(internal val className: String) {
    /** CSS: `badge-xs` — extra small size */
    Xs("badge-xs"),
    /** CSS: `badge-sm` — small size */
    Sm("badge-sm"),
    /** CSS: `badge-md` — medium size (default) */
    Md("badge-md"),
    /** CSS: `badge-lg` — large size */
    Lg("badge-lg"),
    /** CSS: `badge-xl` — extra large size */
    Xl("badge-xl"),
}


/**
 * Badges are used to inform the user of the status of specific data. Renders `<span class="badge ...">`.
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param dash — dash outline style
 * @param ghost — ghost style
 * @param outline — outline style
 * @param soft — soft style
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyBadge(
    text: String? = null,
    id: HtmlId? = null,
    variant: BadgeVariant? = null,
    size: BadgeSize? = null,
    dash: Boolean = false,
    ghost: Boolean = false,
    outline: Boolean = false,
    soft: Boolean = false,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("badge")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (dash) addClassNames("badge-dash")
        if (ghost) addClassNames("badge-ghost")
        if (outline) addClassNames("badge-outline")
        if (soft) addClassNames("badge-soft")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
