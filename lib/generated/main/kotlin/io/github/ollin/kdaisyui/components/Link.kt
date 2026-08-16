// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/link/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.a
import kotlinx.html.A
import kotlinx.html.FlowContent

/** Color variants for this component (CSS prefix: `link-`) */
enum class LinkVariant(internal val className: String) {
    /** CSS: `link-neutral` — neutral color */
    Neutral("link-neutral"),
    /** CSS: `link-primary` — primary color */
    Primary("link-primary"),
    /** CSS: `link-secondary` — secondary color */
    Secondary("link-secondary"),
    /** CSS: `link-accent` — accent color */
    Accent("link-accent"),
    /** CSS: `link-success` — success color */
    Success("link-success"),
    /** CSS: `link-info` — info color */
    Info("link-info"),
    /** CSS: `link-warning` — warning color */
    Warning("link-warning"),
    /** CSS: `link-error` — error color */
    Error("link-error"),
}


/**
 * Link adds the missing underline style to links. Renders `<a class="link ...">`.
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param hover — Only shows underline on hover
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyLink(
    text: String? = null,
    id: HtmlId? = null,
    variant: LinkVariant? = null,
    hover: Boolean = false,
    extraClasses: String? = null,
    attrs: (A.() -> Unit)? = null,
    content: (A.() -> Unit)? = null,
) {
    a {
        if (id != null) attributes["id"] = id.id
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
