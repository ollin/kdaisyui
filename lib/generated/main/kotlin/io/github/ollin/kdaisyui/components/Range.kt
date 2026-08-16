// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/range/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.InputType

/** Color variants for this component (CSS prefix: `range-`) */
enum class RangeVariant(internal val className: String) {
    /** CSS: `range-neutral` — neutral color */
    Neutral("range-neutral"),
    /** CSS: `range-primary` — primary color */
    Primary("range-primary"),
    /** CSS: `range-secondary` — secondary color */
    Secondary("range-secondary"),
    /** CSS: `range-accent` — accent color */
    Accent("range-accent"),
    /** CSS: `range-success` — success color */
    Success("range-success"),
    /** CSS: `range-warning` — warning color */
    Warning("range-warning"),
    /** CSS: `range-info` — info color */
    Info("range-info"),
    /** CSS: `range-error` — error color */
    Error("range-error"),
}

/** Size variants for this component (CSS prefix: `range-`) */
enum class RangeSize(internal val className: String) {
    /** CSS: `range-xs` — Extra small size */
    Xs("range-xs"),
    /** CSS: `range-sm` — Small size */
    Sm("range-sm"),
    /** CSS: `range-md` — Medium size */
    Md("range-md"),
    /** CSS: `range-lg` — Large size */
    Lg("range-lg"),
    /** CSS: `range-xl` — Extra large size */
    Xl("range-xl"),
}


/**
 * Range slider is used to select a value by sliding a handle. Renders `<input class="range ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param vertical — Vertical slider
 * @param min
 * @param max
 * @param value
 * @param step
 * @param disabled
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyRange(
    id: HtmlId? = null,
    variant: RangeVariant? = null,
    size: RangeSize? = null,
    vertical: Boolean = false,
    min: String? = null,
    max: String? = null,
    value: String? = null,
    step: String? = null,
    disabled: Boolean = false,
    extraClasses: String? = null,
    attrs: (INPUT.() -> Unit)? = null,
) {
    input {
        if (id != null) attributes["id"] = id.id
        type = InputType.range
        addClassNames("range")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (vertical) addClassNames("range-vertical")
        if (min != null) this.min = min
        if (max != null) this.max = max
        if (value != null) this.value = value
        if (step != null) this.step = step
        if (disabled) this.disabled = true
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
