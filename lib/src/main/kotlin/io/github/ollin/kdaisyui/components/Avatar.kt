// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/avatar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyAvatar(
    offline: Boolean = false,
    online: Boolean = false,
    placeholder: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("avatar")
        if (offline) addClassNames("avatar-offline")
        if (online) addClassNames("avatar-online")
        if (placeholder) addClassNames("avatar-placeholder")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
