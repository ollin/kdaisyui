// GENERATED — DO NOT EDIT
// Source: codegen/src/config/link.yml + daisyui link.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.a
import kotlinx.html.A
import kotlinx.html.FlowContent

enum class LinkVariant(internal val className: String) {
    Accent("link-accent"),
    Error("link-error"),
    Info("link-info"),
    Neutral("link-neutral"),
    Primary("link-primary"),
    Secondary("link-secondary"),
    Success("link-success"),
    Warning("link-warning"),
}


fun FlowContent.daisyLink(
    text: String? = null,
    variant: LinkVariant? = null,
    hover: Boolean = false,
    href: String? = null,
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit)? = null,
) {
    a {
        addClassNames("link")
        if (variant != null) addClassNames(variant.className)
        if (hover) addClassNames("link-hover")
        if (href != null) this.href = href
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
