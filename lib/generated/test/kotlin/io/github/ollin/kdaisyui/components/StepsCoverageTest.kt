package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StepsCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun steps_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                content = { },
            )
        }
        assertRendered(html, "steps", "Steps defaults", closes = "</ul></div>")
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
        assertRendered(html, "steps steps-horizontal steps-vertical zz-extra", "Steps all flags", closes = "</ul></div>")
        assertCommonFlags(html, "Steps")
    }

    @Test
    fun steps_variant_stepneutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepNeutral,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-neutral", "Steps variant StepNeutral")
    }

    @Test
    fun steps_variant_stepprimary() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepPrimary,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-primary", "Steps variant StepPrimary")
    }

    @Test
    fun steps_variant_stepsecondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepSecondary,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-secondary", "Steps variant StepSecondary")
    }

    @Test
    fun steps_variant_stepaccent() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepAccent,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-accent", "Steps variant StepAccent")
    }

    @Test
    fun steps_variant_stepinfo() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepInfo,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-info", "Steps variant StepInfo")
    }

    @Test
    fun steps_variant_stepsuccess() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepSuccess,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-success", "Steps variant StepSuccess")
    }

    @Test
    fun steps_variant_stepwarning() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepWarning,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-warning", "Steps variant StepWarning")
    }

    @Test
    fun steps_variant_steperror() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                variant = StepsVariant.StepError,
                content = { },
            )
        }
        assertRendered(html, "steps steps-step-error", "Steps variant StepError")
    }

    @Test
    fun stepsStep_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStep(
                content = { },
            )
        }
        assertRendered(html, "step", "StepsStep defaults", closes = "</div></div>")
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
        assertRendered(html, "step zz-extra", "StepsStep all flags", closes = "</div></div>")
        assertCommonFlags(html, "StepsStep")
    }

    @Test
    fun stepsStepIcon_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStepIcon(
                content = { },
            )
        }
        assertRendered(html, "step-icon", "StepsStepIcon defaults", closes = "</div></div>")
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
        assertRendered(html, "step-icon zz-extra", "StepsStepIcon all flags", closes = "</div></div>")
        assertCommonFlags(html, "StepsStepIcon")
    }
}
