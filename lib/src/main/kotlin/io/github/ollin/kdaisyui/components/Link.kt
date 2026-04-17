// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/link/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.a
import kotlinx.html.A
import kotlinx.html.FlowContent

enum class LinkVariant(internal val className: String) {
    Neutral("link-neutral"),
    Primary("link-primary"),
    Secondary("link-secondary"),
    Accent("link-accent"),
    Success("link-success"),
    Info("link-info"),
    Warning("link-warning"),
    Error("link-error"),
}


fun FlowContent.daisyLink(
    text: String? = null,
    variant: LinkVariant? = null,
    hover: Boolean = false,
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit)? = null,
) {
    a {
        addClassNames("link")
        if (variant != null) addClassNames(variant.className)
        if (hover) addClassNames("link-hover")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
