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
    fun step_variant_neutral() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "step step-neutral", "Step variant Neutral")
    }

    @Test
    fun step_variant_primary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "step step-primary", "Step variant Primary")
    }

    @Test
    fun step_variant_secondary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "step step-secondary", "Step variant Secondary")
    }

    @Test
    fun step_variant_accent() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "step step-accent", "Step variant Accent")
    }

    @Test
    fun step_variant_info() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "step step-info", "Step variant Info")
    }

    @Test
    fun step_variant_success() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "step step-success", "Step variant Success")
    }

    @Test
    fun step_variant_warning() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "step step-warning", "Step variant Warning")
    }

    @Test
    fun step_variant_error() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                variant = StepsVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "step step-error", "Step variant Error")
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
