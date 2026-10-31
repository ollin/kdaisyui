package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
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

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
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
        val html = allFlags(Expected("Steps all flags", "steps zz-extra", closes = "</ul></div>")) { daisySteps(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun steps_direction() = assertEachEntry("Steps direction", mapOf(StepsDirection.Vertical to "steps steps-vertical", StepsDirection.Horizontal to "steps steps-horizontal")) { daisySteps(direction = it, content = { }) }

    @Test
    fun stepsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepsClasses()
        }
        assertRendered(html, Expected("StepsClasses defaults", "steps"))
    }

    @Test
    fun stepsClasses_all_flags() = assertRenders(Expected("StepsClasses all flags", "steps", content = false)) { daisyStepsClasses() }

    @Test
    fun stepsClasses_direction() = assertEachEntry("StepsClasses direction", mapOf(StepsDirection.Vertical to "steps steps-vertical", StepsDirection.Horizontal to "steps steps-horizontal")) { daisyStepsClasses(direction = it) }

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
    fun stepClasses_all_flags() = assertRenders(Expected("StepClasses all flags", "step", content = false)) { daisyStepClasses() }

    @Test
    fun stepClasses_color() = assertEachEntry("StepClasses color", mapOf(StepColor.Neutral to "step step-neutral", StepColor.Primary to "step step-primary", StepColor.Secondary to "step step-secondary", StepColor.Accent to "step step-accent", StepColor.Info to "step step-info", StepColor.Success to "step step-success", StepColor.Warning to "step step-warning", StepColor.Error to "step step-error")) { daisyStepClasses(color = it) }

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
        val html = allFlags(Expected("StepIcon all flags", "step-icon zz-extra", closes = "</span></div>")) { daisyStepIcon(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun stepIconClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStepIconClasses()
        }
        assertRendered(html, Expected("StepIconClasses defaults", "step-icon"))
    }

    @Test
    fun stepIconClasses_all_flags() = assertRenders(Expected("StepIconClasses all flags", "step-icon", content = false)) { daisyStepIconClasses() }
}
