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
    fun step_color_neutral() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "step step-neutral", "Step color Neutral")
    }

    @Test
    fun step_color_primary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "step step-primary", "Step color Primary")
    }

    @Test
    fun step_color_secondary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "step step-secondary", "Step color Secondary")
    }

    @Test
    fun step_color_accent() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "step step-accent", "Step color Accent")
    }

    @Test
    fun step_color_info() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Info,
                content = { },
            )
        }
        assertRendered(html, "step step-info", "Step color Info")
    }

    @Test
    fun step_color_success() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Success,
                content = { },
            )
        }
        assertRendered(html, "step step-success", "Step color Success")
    }

    @Test
    fun step_color_warning() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "step step-warning", "Step color Warning")
    }

    @Test
    fun step_color_error() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Error,
                content = { },
            )
        }
        assertRendered(html, "step step-error", "Step color Error")
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
