package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AlertCoverageTest {

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
    fun alert_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                content = { },
            )
        }
        assertRendered(html, Expected("Alert defaults", "alert", closes = "</div></div>"))
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_all_flags() {
        val html = allFlags(Expected("Alert all flags", "alert alert-soft zz-extra", closes = "</div></div>")) { daisyAlert(id = ID, soft = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert color Info", "alert alert-info"))
    }

    @Test
    fun alert_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert color Success", "alert alert-success"))
    }

    @Test
    fun alert_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert color Warning", "alert alert-warning"))
    }

    @Test
    fun alert_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert color Error", "alert alert-error"))
    }

    @Test
    fun alert_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                style = AlertStyle.Outline,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert style Outline", "alert alert-outline"))
    }

    @Test
    fun alert_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                style = AlertStyle.Dash,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert style Dash", "alert alert-dash"))
    }

    @Test
    fun alert_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                direction = AlertDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert direction Vertical", "alert alert-vertical"))
    }

    @Test
    fun alert_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                direction = AlertDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Alert direction Horizontal", "alert alert-horizontal"))
    }

    @Test
    fun alertClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses()
        }
        assertRendered(html, Expected("AlertClasses defaults", "alert"))
    }

    @Test
    fun alertClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                soft = true,
            )
        }
        val expected = Expected("AlertClasses all flags", "alert alert-soft", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun alertClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Info,
            )
        }
        assertRendered(html, Expected("AlertClasses color Info", "alert alert-info"))
    }

    @Test
    fun alertClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Success,
            )
        }
        assertRendered(html, Expected("AlertClasses color Success", "alert alert-success"))
    }

    @Test
    fun alertClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Warning,
            )
        }
        assertRendered(html, Expected("AlertClasses color Warning", "alert alert-warning"))
    }

    @Test
    fun alertClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Error,
            )
        }
        assertRendered(html, Expected("AlertClasses color Error", "alert alert-error"))
    }

    @Test
    fun alertClasses_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                style = AlertStyle.Outline,
            )
        }
        assertRendered(html, Expected("AlertClasses style Outline", "alert alert-outline"))
    }

    @Test
    fun alertClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                style = AlertStyle.Dash,
            )
        }
        assertRendered(html, Expected("AlertClasses style Dash", "alert alert-dash"))
    }

    @Test
    fun alertClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                direction = AlertDirection.Vertical,
            )
        }
        assertRendered(html, Expected("AlertClasses direction Vertical", "alert alert-vertical"))
    }

    @Test
    fun alertClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                direction = AlertDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("AlertClasses direction Horizontal", "alert alert-horizontal"))
    }
}
