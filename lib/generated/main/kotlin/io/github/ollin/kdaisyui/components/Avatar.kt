// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/avatar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/**
 * Avatars are used to show a thumbnail representation of an individual or business in the interface. Renders `<div class="avatar ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param offline
 * @param online
 * @param placeholder
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAvatar(
    id: HtmlId? = null,
    offline: Boolean = false,
    online: Boolean = false,
    placeholder: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("avatar")
        if (offline) addClassNames("avatar-offline")
        if (online) addClassNames("avatar-online")
        if (placeholder) addClassNames("avatar-placeholder")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
