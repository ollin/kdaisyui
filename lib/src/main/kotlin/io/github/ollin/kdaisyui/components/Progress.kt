// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/progress/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.progress
import kotlinx.html.PROGRESS

enum class ProgressVariant(internal val className: String) {
    Neutral("progress-neutral"),
    Primary("progress-primary"),
    Secondary("progress-secondary"),
    Accent("progress-accent"),
    Info("progress-info"),
    Success("progress-success"),
    Warning("progress-warning"),
    Error("progress-error"),
}


fun FlowContent.daisyProgress(
    variant: ProgressVariant? = null,
    extraClasses: String? = null,
    attrs: (PROGRESS.() -> Unit)? = null,
    content: (PROGRESS.() -> Unit),
) {
    progress {
        addClassNames("progress")
        if (variant != null) addClassNames(variant.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
