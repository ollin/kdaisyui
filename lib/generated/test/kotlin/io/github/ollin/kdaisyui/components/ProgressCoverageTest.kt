package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ProgressCoverageTest {

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

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun progress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                content = { },
            )
        }
        assertRendered(html, Expected("Progress defaults", "progress", closes = "</progress></div>"))
    }

    @Test
    fun progress_all_flags() {
        val html = allFlags(Expected("Progress all flags", "progress zz-extra", closes = "</progress></div>")) { daisyProgress(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun progress_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Neutral", "progress progress-neutral"))
    }

    @Test
    fun progress_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Primary", "progress progress-primary"))
    }

    @Test
    fun progress_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Secondary", "progress progress-secondary"))
    }

    @Test
    fun progress_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Accent", "progress progress-accent"))
    }

    @Test
    fun progress_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Info", "progress progress-info"))
    }

    @Test
    fun progress_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Success", "progress progress-success"))
    }

    @Test
    fun progress_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Warning", "progress progress-warning"))
    }

    @Test
    fun progress_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgress(
                color = ProgressColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Progress color Error", "progress progress-error"))
    }

    @Test
    fun progressClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses()
        }
        assertRendered(html, Expected("ProgressClasses defaults", "progress"))
    }

    @Test
    fun progressClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses()
        }
        val expected = Expected("ProgressClasses all flags", "progress", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun progressClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Neutral,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Neutral", "progress progress-neutral"))
    }

    @Test
    fun progressClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Primary,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Primary", "progress progress-primary"))
    }

    @Test
    fun progressClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Secondary,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Secondary", "progress progress-secondary"))
    }

    @Test
    fun progressClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Accent,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Accent", "progress progress-accent"))
    }

    @Test
    fun progressClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Info,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Info", "progress progress-info"))
    }

    @Test
    fun progressClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Success,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Success", "progress progress-success"))
    }

    @Test
    fun progressClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Warning,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Warning", "progress progress-warning"))
    }

    @Test
    fun progressClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyProgressClasses(
                color = ProgressColor.Error,
            )
        }
        assertRendered(html, Expected("ProgressClasses color Error", "progress progress-error"))
    }
}
