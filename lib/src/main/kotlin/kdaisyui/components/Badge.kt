// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/badge/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.span
import kotlinx.html.SPAN

enum class BadgeVariant(internal val className: String) {
    Neutral("badge-neutral"),
    Primary("badge-primary"),
    Secondary("badge-secondary"),
    Accent("badge-accent"),
    Info("badge-info"),
    Success("badge-success"),
    Warning("badge-warning"),
    Error("badge-error"),
}

enum class BadgeSize(internal val className: String) {
    Xs("badge-xs"),
    Sm("badge-sm"),
    Md("badge-md"),
    Lg("badge-lg"),
    Xl("badge-xl"),
}


fun FlowContent.daisyBadge(
    text: String? = null,
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
