// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/kbd/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.kbd
import kotlinx.html.KBD

enum class KbdSize(internal val className: String) {
    Xs("kbd-xs"),
    Sm("kbd-sm"),
    Md("kbd-md"),
    Lg("kbd-lg"),
    Xl("kbd-xl"),
}


fun FlowContent.daisyKbd(
    text: String? = null,
    size: KbdSize? = null,
    extraClasses: String? = null,
    attrs: (KBD.() -> Unit)? = null,
    content: (KBD.() -> Unit)? = null,
) {
    kbd {
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
