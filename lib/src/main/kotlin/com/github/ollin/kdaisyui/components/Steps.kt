// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/steps/+page.md
// Regenerate: cd codegen && npm run generate

package com.github.ollin.kdaisyui.components

import com.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

enum class StepsVariant(internal val className: String) {
    StepNeutral("steps-step-neutral"),
    StepPrimary("steps-step-primary"),
    StepSecondary("steps-step-secondary"),
    StepAccent("steps-step-accent"),
    StepInfo("steps-step-info"),
    StepSuccess("steps-step-success"),
    StepWarning("steps-step-warning"),
    StepError("steps-step-error"),
}


fun FlowContent.daisySteps(
    variant: StepsVariant? = null,
    horizontal: Boolean = false,
    vertical: Boolean = false,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        addClassNames("steps")
        if (variant != null) addClassNames(variant.className)
        if (horizontal) addClassNames("steps-horizontal")
        if (vertical) addClassNames("steps-vertical")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyStepsStep(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("step")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyStepsStepIcon(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("step-icon")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
