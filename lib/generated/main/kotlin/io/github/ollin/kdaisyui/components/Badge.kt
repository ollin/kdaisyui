// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/badge/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

/** Color variants for this component (CSS prefix: `badge-`) */
enum class BadgeVariant(internal val className: String) : ClassValues<BadgeVariant> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Size variants for this component (CSS prefix: `badge-`) */
enum class BadgeSize(internal val className: String) : ClassValues<BadgeSize> {
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
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** OutlineStyle variants for this component (CSS prefix: `badge-`) */
enum class BadgeOutlineStyle(internal val className: String) : ClassValues<BadgeOutlineStyle> {
    /** CSS: `badge-outline` — outline style */
    Outline("badge-outline"),
    /** CSS: `badge-dash` — dash outline style */
    Dash("badge-dash"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** FillStyle variants for this component (CSS prefix: `badge-`) */
enum class BadgeFillStyle(internal val className: String) : ClassValues<BadgeFillStyle> {
    /** CSS: `badge-soft` — soft style */
    Soft("badge-soft"),
    /** CSS: `badge-ghost` — ghost style */
    Ghost("badge-ghost"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Badges are used to inform the user of the status of specific data. Renders `<span class="badge ...">`.
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param outlineStyle — OutlineStyle variant
 * @param fillStyle — FillStyle variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyBadge(
    text: String? = null,
    id: HtmlId? = null,
    variant: ClassValues<BadgeVariant>? = null,
    size: ClassValues<BadgeSize>? = null,
    outlineStyle: ClassValues<BadgeOutlineStyle>? = null,
    fillStyle: ClassValues<BadgeFillStyle>? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("badge")
        addClassNames(variant)
        addClassNames(size)
        addClassNames(outlineStyle)
        addClassNames(fillStyle)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
