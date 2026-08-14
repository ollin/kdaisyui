// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/progress/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.progress
import kotlinx.html.PROGRESS

/** Color variants for this component (CSS prefix: `progress-`) */
enum class ProgressVariant(internal val className: String) {
    /** CSS: `progress-neutral` — neutral color */
    Neutral("progress-neutral"),
    /** CSS: `progress-primary` — primary color */
    Primary("progress-primary"),
    /** CSS: `progress-secondary` — secondary color */
    Secondary("progress-secondary"),
    /** CSS: `progress-accent` — accent color */
    Accent("progress-accent"),
    /** CSS: `progress-info` — info color */
    Info("progress-info"),
    /** CSS: `progress-success` — success color */
    Success("progress-success"),
    /** CSS: `progress-warning` — warning color */
    Warning("progress-warning"),
    /** CSS: `progress-error` — error color */
    Error("progress-error"),
}


/**
 * Progress bar can be used to show the progress of a task or to show the passing of time. Renders `<progress class="progress ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyProgress(
    id: HtmlId? = null,
    variant: ProgressVariant? = null,
    extraClasses: String? = null,
    attrs: (PROGRESS.() -> Unit)? = null,
    content: (PROGRESS.() -> Unit),
) {
    progress {
        if (id != null) attributes["id"] = id.id
        addClassNames("progress")
        if (variant != null) addClassNames(variant.className)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
