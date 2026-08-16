// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/toggle/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

enum class ToggleVariant(internal val className: String) {
    /** CSS: `toggle-primary` */
    Primary("toggle-primary"),
    /** CSS: `toggle-secondary` */
    Secondary("toggle-secondary"),
    /** CSS: `toggle-accent` */
    Accent("toggle-accent"),
    /** CSS: `toggle-neutral` */
    Neutral("toggle-neutral"),
    /** CSS: `toggle-success` */
    Success("toggle-success"),
    /** CSS: `toggle-warning` */
    Warning("toggle-warning"),
    /** CSS: `toggle-info` */
    Info("toggle-info"),
    /** CSS: `toggle-error` */
    Error("toggle-error"),
}

enum class ToggleSize(internal val className: String) {
    /** CSS: `toggle-xs` */
    Xs("toggle-xs"),
    /** CSS: `toggle-sm` */
    Sm("toggle-sm"),
    /** CSS: `toggle-md` */
    Md("toggle-md"),
    /** CSS: `toggle-lg` */
    Lg("toggle-lg"),
    /** CSS: `toggle-xl` */
    Xl("toggle-xl"),
}


/**
 * Toggle is a checkbox that is styled to look like a switch button. Renders `<input class="toggle ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param checked
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyToggle(
    id: HtmlId? = null,
    variant: ToggleVariant? = null,
    size: ToggleSize? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        type = InputType.checkBox
        addClassNames("toggle")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
