// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/fileinput/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT

enum class FileInputVariant(internal val className: String) {
    /** CSS: `file-input-neutral` */
    Neutral("file-input-neutral"),
    /** CSS: `file-input-primary` */
    Primary("file-input-primary"),
    /** CSS: `file-input-secondary` */
    Secondary("file-input-secondary"),
    /** CSS: `file-input-accent` */
    Accent("file-input-accent"),
    /** CSS: `file-input-info` */
    Info("file-input-info"),
    /** CSS: `file-input-success` */
    Success("file-input-success"),
    /** CSS: `file-input-warning` */
    Warning("file-input-warning"),
    /** CSS: `file-input-error` */
    Error("file-input-error"),
}

enum class FileInputSize(internal val className: String) {
    /** CSS: `file-input-xs` */
    Xs("file-input-xs"),
    /** CSS: `file-input-sm` */
    Sm("file-input-sm"),
    /** CSS: `file-input-md` */
    Md("file-input-md"),
    /** CSS: `file-input-lg` */
    Lg("file-input-lg"),
    /** CSS: `file-input-xl` */
    Xl("file-input-xl"),
}


/**
 * File Input is an input field for uploading files. Renders `<input class="file-input ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param ghost
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyFileInput(
    id: HtmlId? = null,
    variant: FileInputVariant? = null,
    size: FileInputSize? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
    content: (INPUT.() -> Unit),
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("file-input")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (ghost) addClassNames("file-input-ghost")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
