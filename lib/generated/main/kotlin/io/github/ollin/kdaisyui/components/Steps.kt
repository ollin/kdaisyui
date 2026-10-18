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
import kotlinx.html.ul
import kotlinx.html.UL

/** Color variants for this component (CSS prefix: `steps-`) */
enum class StepsVariant(internal val className: String) : ClassValues<StepsVariant> {
    /** CSS: `step-neutral` — neutral color */
    StepNeutral("step-neutral"),
    /** CSS: `step-primary` — primary color */
    StepPrimary("step-primary"),
    /** CSS: `step-secondary` — secondary color */
    StepSecondary("step-secondary"),
    /** CSS: `step-accent` — accent color */
    StepAccent("step-accent"),
    /** CSS: `step-info` — info color */
    StepInfo("step-info"),
    /** CSS: `step-success` — success color */
    StepSuccess("step-success"),
    /** CSS: `step-warning` — warning color */
    StepWarning("step-warning"),
    /** CSS: `step-error` — error color */
    StepError("step-error"),
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
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisySteps(
    id: HtmlId? = null,
    direction: ClassValues<StepsDirection>? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("steps")
        addClassNames(direction)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** A single step node Renders `<li class="step ...">`. */
fun UL.daisyStepsStep(
    id: HtmlId? = null,
    variant: ClassValues<StepsVariant>? = null,
    extraClasses: String? = null,
    attrs: (LI.() -> Unit)? = null,
    content: (LI.() -> Unit),
) {
    li {
        if (id != null) attributes["id"] = id.id
        addClassNames("step")
        addClassNames(variant)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** For custom icon inside step Renders `<span class="step-icon ...">`. */
fun FlowContent.daisyStepsStepIcon(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit),
) {
    span {
        if (id != null) attributes["id"] = id.id
        addClassNames("step-icon")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
