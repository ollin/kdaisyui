// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/textarea/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.textArea
import kotlinx.html.TEXTAREA

enum class TextareaVariant(internal val className: String) {
    Neutral("textarea-neutral"),
    Primary("textarea-primary"),
    Secondary("textarea-secondary"),
    Accent("textarea-accent"),
    Info("textarea-info"),
    Success("textarea-success"),
    Warning("textarea-warning"),
    Error("textarea-error"),
}

enum class TextareaSize(internal val className: String) {
    Xs("textarea-xs"),
    Sm("textarea-sm"),
    Md("textarea-md"),
    Lg("textarea-lg"),
    Xl("textarea-xl"),
}


fun FlowContent.daisyTextarea(
    variant: TextareaVariant? = null,
    size: TextareaSize? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (TEXTAREA.() -> Unit)? = null,
    content: (TEXTAREA.() -> Unit),
) {
    textArea {
        addClassNames("textarea")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("textarea-ghost")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
