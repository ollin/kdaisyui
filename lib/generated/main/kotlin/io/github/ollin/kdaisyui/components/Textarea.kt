// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/textarea/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.textArea
import kotlinx.html.TEXTAREA

enum class TextareaVariant(internal val className: String) : ClassValues<TextareaVariant> {
    /** CSS: `textarea-neutral` */
    Neutral("textarea-neutral"),
    /** CSS: `textarea-primary` */
    Primary("textarea-primary"),
    /** CSS: `textarea-secondary` */
    Secondary("textarea-secondary"),
    /** CSS: `textarea-accent` */
    Accent("textarea-accent"),
    /** CSS: `textarea-info` */
    Info("textarea-info"),
    /** CSS: `textarea-success` */
    Success("textarea-success"),
    /** CSS: `textarea-warning` */
    Warning("textarea-warning"),
    /** CSS: `textarea-error` */
    Error("textarea-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class TextareaSize(internal val className: String) : ClassValues<TextareaSize> {
    /** CSS: `textarea-xs` */
    Xs("textarea-xs"),
    /** CSS: `textarea-sm` */
    Sm("textarea-sm"),
    /** CSS: `textarea-md` */
    Md("textarea-md"),
    /** CSS: `textarea-lg` */
    Lg("textarea-lg"),
    /** CSS: `textarea-xl` */
    Xl("textarea-xl"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Textarea allows users to enter text in multiple lines. Renders `<textarea class="textarea ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param ghost
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTextarea(
    id: HtmlId? = null,
    variant: ClassValues<TextareaVariant>? = null,
    size: ClassValues<TextareaSize>? = null,
    ghost: Boolean = false,
    extraClasses: String? = null,
    attrs: (TEXTAREA.() -> Unit)? = null,
    content: (TEXTAREA.() -> Unit),
) {
    textArea {
        if (id != null) attributes["id"] = id.id
        addClassNames("textarea")
        addClassNames(variant)
        addClassNames(size)
        if (ghost) addClassNames("textarea-ghost")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
