package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "steps zz-extra", "Steps all flags", closes = "</ul></div>")
        assertCommonFlags(html, "Steps")
    }

    @Test
    fun steps_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                direction = StepsDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "steps steps-vertical", "Steps direction Vertical")
    }

    @Test
    fun steps_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                direction = StepsDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "steps steps-horizontal", "Steps direction Horizontal")
    }

    @Test
    fun stepsStep_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                content = { },
            )
        }
        assertRendered(html, "step", "StepsStep defaults", closes = "</li></ul>")
    }

    @Test
    fun stepsStep_all_flags() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "step zz-extra", "StepsStep all flags", closes = "</li></ul>")
        assertCommonFlags(html, "StepsStep")
    }

    @Test
    fun stepsStep_variant_stepneutral() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepNeutral,
                content = { },
            )
        }
        assertRendered(html, "step step-neutral", "StepsStep variant StepNeutral")
    }

    @Test
    fun stepsStep_variant_stepprimary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepPrimary,
                content = { },
            )
        }
        assertRendered(html, "step step-primary", "StepsStep variant StepPrimary")
    }

    @Test
    fun stepsStep_variant_stepsecondary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepSecondary,
                content = { },
            )
        }
        assertRendered(html, "step step-secondary", "StepsStep variant StepSecondary")
    }

    @Test
    fun stepsStep_variant_stepaccent() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepAccent,
                content = { },
            )
        }
        assertRendered(html, "step step-accent", "StepsStep variant StepAccent")
    }

    @Test
    fun stepsStep_variant_stepinfo() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepInfo,
                content = { },
            )
        }
        assertRendered(html, "step step-info", "StepsStep variant StepInfo")
    }

    @Test
    fun stepsStep_variant_stepsuccess() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepSuccess,
                content = { },
            )
        }
        assertRendered(html, "step step-success", "StepsStep variant StepSuccess")
    }

    @Test
    fun stepsStep_variant_stepwarning() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepWarning,
                content = { },
            )
        }
        assertRendered(html, "step step-warning", "StepsStep variant StepWarning")
    }

    @Test
    fun stepsStep_variant_steperror() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStepsStep(
                variant = StepsVariant.StepError,
                content = { },
            )
        }
        assertRendered(html, "step step-error", "StepsStep variant StepError")
    }

    @Test
    fun stepsStepIcon_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsStepIcon(
                content = { },
            )
        }
        assertRendered(html, "step-icon", "StepsStepIcon defaults", closes = "</span></div>")
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
        assertRendered(html, "step-icon zz-extra", "StepsStepIcon all flags", closes = "</span></div>")
        assertCommonFlags(html, "StepsStepIcon")
    }
}
