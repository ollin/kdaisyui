// GENERATED — DO NOT EDIT
// Source: codegen/src/config/swap.yml + daisyui swap.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.label
import kotlinx.html.LABEL

fun FlowContent.daisySwap(
    active: Boolean = false,
    flip: Boolean = false,
    indeterminate: Boolean = false,
    off: Boolean = false,
    on: Boolean = false,
    rotate: Boolean = false,
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
) {
    label {
        addClassNames("swap")
        if (active) addClassNames("swap-active")
        if (flip) addClassNames("swap-flip")
        if (indeterminate) addClassNames("swap-indeterminate")
        if (off) addClassNames("swap-off")
        if (on) addClassNames("swap-on")
        if (rotate) addClassNames("swap-rotate")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
