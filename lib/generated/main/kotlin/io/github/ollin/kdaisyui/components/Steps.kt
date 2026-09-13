// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/steps/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

/** Color variants for this component (CSS prefix: `steps-`) */
enum class StepsVariant(internal val className: String) : ClassValues<StepsVariant> {
    /** CSS: `steps-step-neutral` — neutral color */
    StepNeutral("steps-step-neutral"),
    /** CSS: `steps-step-primary` — primary color */
    StepPrimary("steps-step-primary"),
    /** CSS: `steps-step-secondary` — secondary color */
    StepSecondary("steps-step-secondary"),
    /** CSS: `steps-step-accent` — accent color */
    StepAccent("steps-step-accent"),
    /** CSS: `steps-step-info` — info color */
    StepInfo("steps-step-info"),
    /** CSS: `steps-step-success` — success color */
    StepSuccess("steps-step-success"),
    /** CSS: `steps-step-warning` — warning color */
    StepWarning("steps-step-warning"),
    /** CSS: `steps-step-error` — error color */
    StepError("steps-step-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Direction variants for this component (CSS prefix: `steps-`) */
enum class StepsDirection(internal val className: String) : ClassValues<StepsDirection> {
    /** CSS: `steps-vertical` — Vertical layout */
    Vertical("steps-vertical"),
    /** CSS: `steps-horizontal` — Makes steps horizontal */
    Horizontal("steps-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Steps can be used to show a list of steps in a process. Renders `<ul class="steps ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySteps(
    id: HtmlId? = null,
    variant: ClassValues<StepsVariant>? = null,
    direction: ClassValues<StepsDirection>? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("steps")
        addClassNames(variant)
        addClassNames(direction)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** A single step node Renders `<div class="step ...">`. */
fun FlowContent.daisyStepsStep(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("step")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** For custom icon inside step Renders `<div class="step-icon ...">`. */
fun FlowContent.daisyStepsStepIcon(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("step-icon")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
