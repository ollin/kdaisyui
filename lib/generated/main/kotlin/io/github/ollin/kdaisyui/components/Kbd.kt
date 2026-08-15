// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/kbd/+page.md
// Regenerate: cd codegen && npm run generate
// TEMPORARY: hand-edit to prove the drift check catches exactly this.

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.kbd
import kotlinx.html.KBD

/** Size variants for this component (CSS prefix: `kbd-`) */
enum class KbdSize(internal val className: String) {
    /** CSS: `kbd-xs` — Extra small size */
    Xs("kbd-xs"),
    /** CSS: `kbd-sm` — Small size */
    Sm("kbd-sm"),
    /** CSS: `kbd-md` — Medium size */
    Md("kbd-md"),
    /** CSS: `kbd-lg` — Large size */
    Lg("kbd-lg"),
    /** CSS: `kbd-xl` — Extra large size */
    Xl("kbd-xl"),
}


/**
 * Kbd is used to display keyboard shortcuts. Renders `<kbd class="kbd ...">`.
 * @param text — Shortcut for inline text content (mutually exclusive with [content])
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content (takes precedence over [text] if both are set)
 */
fun FlowContent.daisyKbd(
    text: String? = null,
    id: HtmlId? = null,
    size: KbdSize? = null,
    extraClasses: String? = null,
    attrs: (KBD.() -> Unit)? = null,
    content: (KBD.() -> Unit)? = null,
) {
    kbd {
        if (id != null) attributes["id"] = id.id
        addClassNames("kbd")
        if (size != null) addClassNames(size.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
