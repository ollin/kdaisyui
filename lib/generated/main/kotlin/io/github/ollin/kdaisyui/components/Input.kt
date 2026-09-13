// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/input/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class InputVariant(internal val className: String) : ClassValues<InputVariant> {
    /** CSS: `input-neutral` */
    Neutral("input-neutral"),
    /** CSS: `input-primary` */
    Primary("input-primary"),
    /** CSS: `input-secondary` */
    Secondary("input-secondary"),
    /** CSS: `input-accent` */
    Accent("input-accent"),
    /** CSS: `input-info` */
    Info("input-info"),
    /** CSS: `input-success` */
    Success("input-success"),
    /** CSS: `input-warning` */
    Warning("input-warning"),
    /** CSS: `input-error` */
    Error("input-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class InputSize(internal val className: String) : ClassValues<InputSize> {
    /** CSS: `input-xs` */
    Xs("input-xs"),
    /** CSS: `input-sm` */
    Sm("input-sm"),
    /** CSS: `input-md` */
    Md("input-md"),
    /** CSS: `input-lg` */
    Lg("input-lg"),
    /** CSS: `input-xl` */
    Xl("input-xl"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Text Input is a simple input field. Renders `<input class="input ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param ghost
 * @param type
 * @param placeholder
 * @param value
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyInput(
    id: HtmlId? = null,
    variant: ClassValues<InputVariant>? = null,
    size: ClassValues<InputSize>? = null,
    ghost: Boolean = false,
    type: InputType = InputType.text,
    placeholder: String? = null,
    value: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        this.type = type
        addClassNames("input")
        addClassNames(variant)
        addClassNames(size)
        if (ghost) addClassNames("input-ghost")
        if (placeholder != null) this.placeholder = placeholder
        if (value != null) this.value = value
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
