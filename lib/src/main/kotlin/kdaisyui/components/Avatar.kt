// GENERATED — DO NOT EDIT
// Source: codegen/src/config/avatar.yml + daisyui avatar.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

fun FlowContent.daisyAvatar(
    group: Boolean = false,
    offline: Boolean = false,
    online: Boolean = false,
    placeholder: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("avatar")
        if (group) addClassNames("avatar-group")
        if (offline) addClassNames("avatar-offline")
        if (online) addClassNames("avatar-online")
        if (placeholder) addClassNames("avatar-placeholder")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
