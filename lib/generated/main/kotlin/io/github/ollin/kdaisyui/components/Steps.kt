// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/steps/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.li
import kotlinx.html.LI
import kotlinx.html.span
import kotlinx.html.SPAN
import kotlinx.html.Tag
import kotlinx.html.ul
import kotlinx.html.UL

/** Color variants for this component (CSS prefix: `steps-`) */
enum class StepColor(internal val className: String) : ClassValues<StepColor> {
    /** CSS: `step-neutral` — neutral color */
    Neutral("step-neutral"),
    /** CSS: `step-primary` — primary color */
    Primary("step-primary"),
    /** CSS: `step-secondary` — secondary color */
    Secondary("step-secondary"),
    /** CSS: `step-accent` — accent color */
    Accent("step-accent"),
    /** CSS: `step-info` — info color */
    Info("step-info"),
    /** CSS: `step-success` — success color */
    Success("step-success"),
    /** CSS: `step-warning` — warning color */
    Warning("step-warning"),
    /** CSS: `step-error` — error color */
    Error("step-error"),
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
 * The same classes on another element: `a { daisyStepsClasses(…) }`; see [daisyStepsClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisySteps(
    id: HtmlId? = null,
    direction: ClassValues<StepsDirection>? = null,
    extraClasses: String? = null,
    block: (UL.() -> Unit) = {},
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("steps")
        addClassNames(direction)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisySteps` writes, on the element this is called on. */
fun Tag.daisyStepsClasses(
    direction: ClassValues<StepsDirection>? = null,
) {
    addClassNames("steps")
    addClassNames(direction)
}

/**
 * A single step node Renders `<li class="step ...">`.
 * The same classes on another element: `a { daisyStepClasses(…) }`; see [daisyStepClasses].
 */
fun UL.daisyStep(
    id: HtmlId? = null,
    color: ClassValues<StepColor>? = null,
    extraClasses: String? = null,
    block: (LI.() -> Unit) = {},
) {
    li {
        if (id != null) attributes["id"] = id.id
        addClassNames("step")
        addClassNames(color)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyStep` writes, on the element this is called on. */
fun Tag.daisyStepClasses(
    color: ClassValues<StepColor>? = null,
) {
    addClassNames("step")
    addClassNames(color)
}

/**
 * For custom icon inside step Renders `<span class="step-icon ...">`.
 * The same classes on another element: `a { daisyStepIconClasses(…) }`; see [daisyStepIconClasses].
 */
fun FlowContent.daisyStepIcon(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (SPAN.() -> Unit) = {},
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("step-icon")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyStepIcon` writes, on the element this is called on. */
fun Tag.daisyStepIconClasses() {
    addClassNames("step-icon")
}
