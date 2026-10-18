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
    fun step_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                content = { },
            )
        }
        assertRendered(html, "step", "Step defaults", closes = "</li></ul>")
    }

    @Test
    fun step_all_flags() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "step zz-extra", "Step all flags", closes = "</li></ul>")
        assertCommonFlags(html, "Step")
    }

    @Test
    fun step_variant_stepneutral() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepNeutral,
                content = { },
            )
        }
        assertRendered(html, "step step-neutral", "Step variant StepNeutral")
    }

    @Test
    fun step_variant_stepprimary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepPrimary,
                content = { },
            )
        }
        assertRendered(html, "step step-primary", "Step variant StepPrimary")
    }

    @Test
    fun step_variant_stepsecondary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepSecondary,
                content = { },
            )
        }
        assertRendered(html, "step step-secondary", "Step variant StepSecondary")
    }

    @Test
    fun step_variant_stepaccent() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepAccent,
                content = { },
            )
        }
        assertRendered(html, "step step-accent", "Step variant StepAccent")
    }

    @Test
    fun step_variant_stepinfo() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepInfo,
                content = { },
            )
        }
        assertRendered(html, "step step-info", "Step variant StepInfo")
    }

    @Test
    fun step_variant_stepsuccess() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepSuccess,
                content = { },
            )
        }
        assertRendered(html, "step step-success", "Step variant StepSuccess")
    }

    @Test
    fun step_variant_stepwarning() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepWarning,
                content = { },
            )
        }
        assertRendered(html, "step step-warning", "Step variant StepWarning")
    }

    @Test
    fun step_variant_steperror() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.StepError,
                content = { },
            )
        }
        assertRendered(html, "step step-error", "Step variant StepError")
    }

    @Test
    fun stepIcon_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIcon(
                content = { },
            )
        }
        assertRendered(html, "step-icon", "StepIcon defaults", closes = "</span></div>")
    }

    @Test
    fun stepIcon_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIcon(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "step-icon zz-extra", "StepIcon all flags", closes = "</span></div>")
        assertCommonFlags(html, "StepIcon")
    }
}
