package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

class StepsCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun steps_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                content = { },
            )
        }
        assertRendered(html, Expected("Steps defaults", "steps", closes = "</ul></div>"))
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
        val expected = Expected("Steps all flags", "steps zz-extra", closes = "</ul></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun steps_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                direction = StepsDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Steps direction Vertical", "steps steps-vertical"))
    }

    @Test
    fun steps_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisySteps(
                direction = StepsDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Steps direction Horizontal", "steps steps-horizontal"))
    }

    @Test
    fun stepsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsClasses()
        }
        assertRendered(html, Expected("StepsClasses defaults", "steps"))
    }

    @Test
    fun stepsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsClasses()
        }
        val expected = Expected("StepsClasses all flags", "steps", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun stepsClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsClasses(
                direction = StepsDirection.Vertical,
            )
        }
        assertRendered(html, Expected("StepsClasses direction Vertical", "steps steps-vertical"))
    }

    @Test
    fun stepsClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsClasses(
                direction = StepsDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("StepsClasses direction Horizontal", "steps steps-horizontal"))
    }

    @Test
    fun step_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                content = { },
            )
        }
        assertRendered(html, Expected("Step defaults", "step", closes = "</li></ul>"))
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
        val expected = Expected("Step all flags", "step zz-extra", closes = "</li></ul>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun step_color_neutral() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Neutral", "step step-neutral"))
    }

    @Test
    fun step_color_primary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Primary", "step step-primary"))
    }

    @Test
    fun step_color_secondary() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Secondary", "step step-secondary"))
    }

    @Test
    fun step_color_accent() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Accent", "step step-accent"))
    }

    @Test
    fun step_color_info() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Info", "step step-info"))
    }

    @Test
    fun step_color_success() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Success", "step step-success"))
    }

    @Test
    fun step_color_warning() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Warning", "step step-warning"))
    }

    @Test
    fun step_color_error() {
        val html = createHTML(prettyPrint = false).ul {
            daisyStep(
                color = StepColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Step color Error", "step step-error"))
    }

    @Test
    fun stepClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses()
        }
        assertRendered(html, Expected("StepClasses defaults", "step"))
    }

    @Test
    fun stepClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses()
        }
        val expected = Expected("StepClasses all flags", "step", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun stepClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Neutral,
            )
        }
        assertRendered(html, Expected("StepClasses color Neutral", "step step-neutral"))
    }

    @Test
    fun stepClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Primary,
            )
        }
        assertRendered(html, Expected("StepClasses color Primary", "step step-primary"))
    }

    @Test
    fun stepClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Secondary,
            )
        }
        assertRendered(html, Expected("StepClasses color Secondary", "step step-secondary"))
    }

    @Test
    fun stepClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Accent,
            )
        }
        assertRendered(html, Expected("StepClasses color Accent", "step step-accent"))
    }

    @Test
    fun stepClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Info,
            )
        }
        assertRendered(html, Expected("StepClasses color Info", "step step-info"))
    }

    @Test
    fun stepClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Success,
            )
        }
        assertRendered(html, Expected("StepClasses color Success", "step step-success"))
    }

    @Test
    fun stepClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Warning,
            )
        }
        assertRendered(html, Expected("StepClasses color Warning", "step step-warning"))
    }

    @Test
    fun stepClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepClasses(
                color = StepColor.Error,
            )
        }
        assertRendered(html, Expected("StepClasses color Error", "step step-error"))
    }

    @Test
    fun stepIcon_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIcon(
                content = { },
            )
        }
        assertRendered(html, Expected("StepIcon defaults", "step-icon", closes = "</span></div>"))
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
        val expected = Expected("StepIcon all flags", "step-icon zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun stepIconClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIconClasses()
        }
        assertRendered(html, Expected("StepIconClasses defaults", "step-icon"))
    }

    @Test
    fun stepIconClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIconClasses()
        }
        val expected = Expected("StepIconClasses all flags", "step-icon", content = false)
        assertRendered(html, expected)
    }
}
