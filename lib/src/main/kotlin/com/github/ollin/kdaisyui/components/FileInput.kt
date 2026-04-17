// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/fileinput/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT

enum class FileInputVariant(internal val className: String) {
    Neutral("file-input-neutral"),
    Primary("file-input-primary"),
    Secondary("file-input-secondary"),
    Accent("file-input-accent"),
    Info("file-input-info"),
    Success("file-input-success"),
    Warning("file-input-warning"),
    Error("file-input-error"),
}

enum class FileInputSize(internal val className: String) {
    Xs("file-input-xs"),
    Sm("file-input-sm"),
    Md("file-input-md"),
    Lg("file-input-lg"),
    Xl("file-input-xl"),
}


fun FlowContent.daisyFileInput(
    variant: FileInputVariant? = null,
    size: FileInputSize? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
    content: (INPUT.() -> Unit),
) {
    input {
        addClassNames("file-input")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("file-input-ghost")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
