package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StepsCoverageTest {

    @Test
    fun steps_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps", actualClasses, "Steps defaults")
    }

    @Test
    fun steps_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                id = htmlId("x-cov-id"),
                horizontal = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-horizontal steps-vertical zz-extra", actualClasses, "Steps all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Steps id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Steps attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Steps content")
    }

    @Test
    fun steps_variant_stepneutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepNeutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-neutral", actualClasses, "Steps variant StepNeutral")
    }

    @Test
    fun steps_variant_stepprimary() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepPrimary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-primary", actualClasses, "Steps variant StepPrimary")
    }

    @Test
    fun steps_variant_stepsecondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepSecondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-secondary", actualClasses, "Steps variant StepSecondary")
    }

    @Test
    fun steps_variant_stepaccent() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepAccent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-accent", actualClasses, "Steps variant StepAccent")
    }

    @Test
    fun steps_variant_stepinfo() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepInfo,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-info", actualClasses, "Steps variant StepInfo")
    }

    @Test
    fun steps_variant_stepsuccess() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepSuccess,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-success", actualClasses, "Steps variant StepSuccess")
    }

    @Test
    fun steps_variant_stepwarning() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepWarning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-warning", actualClasses, "Steps variant StepWarning")
    }

    @Test
    fun steps_variant_steperror() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepError,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("steps steps-step-error", actualClasses, "Steps variant StepError")
    }

    @Test
    fun stepsStep_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStep(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("step", actualClasses, "StepsStep defaults")
    }

    @Test
    fun stepsStep_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStep(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("step zz-extra", actualClasses, "StepsStep all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StepsStep id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StepsStep attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StepsStep content")
    }

    @Test
    fun stepsStepIcon_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStepIcon(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("step-icon", actualClasses, "StepsStepIcon defaults")
    }

    @Test
    fun stepsStepIcon_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStepIcon(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("step-icon zz-extra", actualClasses, "StepsStepIcon all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StepsStepIcon id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StepsStepIcon attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StepsStepIcon content")
    }
}
