// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/radio/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

/** Color variants for this component (CSS prefix: `radio-`) */
enum class RadioVariant(internal val className: String) {
    /** CSS: `radio-neutral` — neutral color */
    Neutral("radio-neutral"),
    /** CSS: `radio-primary` — primary color */
    Primary("radio-primary"),
    /** CSS: `radio-secondary` — secondary color */
    Secondary("radio-secondary"),
    /** CSS: `radio-accent` — accent color */
    Accent("radio-accent"),
    /** CSS: `radio-success` — success color */
    Success("radio-success"),
    /** CSS: `radio-warning` — warning color */
    Warning("radio-warning"),
    /** CSS: `radio-info` — info color */
    Info("radio-info"),
    /** CSS: `radio-error` — error color */
    Error("radio-error"),
}

/** Size variants for this component (CSS prefix: `radio-`) */
enum class RadioSize(internal val className: String) {
    /** CSS: `radio-xs` — Extra small size */
    Xs("radio-xs"),
    /** CSS: `radio-sm` — Small size */
    Sm("radio-sm"),
    /** CSS: `radio-md` — Medium size */
    Md("radio-md"),
    /** CSS: `radio-lg` — Large size */
    Lg("radio-lg"),
    /** CSS: `radio-xl` — Extra large size */
    Xl("radio-xl"),
}


/**
 * Radio buttons allow the user to select one option from a set. Renders `<input class="radio ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param name
 * @param checked
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyRadio(
    id: HtmlId? = null,
    variant: RadioVariant? = null,
    size: RadioSize? = null,
    name: String? = null,
    checked: Boolean = false,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        type = InputType.radio
        addClassNames("radio")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (name != null) this.name = name
        if (checked) this.checked = true
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
