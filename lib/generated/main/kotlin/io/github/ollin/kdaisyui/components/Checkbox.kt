// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/checkbox/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class CheckboxVariant(internal val className: String) {
    /** CSS: `checkbox-primary` */
    Primary("checkbox-primary"),
    /** CSS: `checkbox-secondary` */
    Secondary("checkbox-secondary"),
    /** CSS: `checkbox-accent` */
    Accent("checkbox-accent"),
    /** CSS: `checkbox-neutral` */
    Neutral("checkbox-neutral"),
    /** CSS: `checkbox-success` */
    Success("checkbox-success"),
    /** CSS: `checkbox-warning` */
    Warning("checkbox-warning"),
    /** CSS: `checkbox-info` */
    Info("checkbox-info"),
    /** CSS: `checkbox-error` */
    Error("checkbox-error"),
}

enum class CheckboxSize(internal val className: String) {
    /** CSS: `checkbox-xs` */
    Xs("checkbox-xs"),
    /** CSS: `checkbox-sm` */
    Sm("checkbox-sm"),
    /** CSS: `checkbox-md` */
    Md("checkbox-md"),
    /** CSS: `checkbox-lg` */
    Lg("checkbox-lg"),
    /** CSS: `checkbox-xl` */
    Xl("checkbox-xl"),
}


/**
 * Checkboxes are used to select or deselect a value. Renders `<input class="checkbox ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param checked
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyCheckbox(
    id: HtmlId? = null,
    variant: CheckboxVariant? = null,
    size: CheckboxSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        type = InputType.checkBox
        addClassNames("checkbox")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
