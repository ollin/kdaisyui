package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AlertCoverageTest {

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
    fun alert_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                content = { },
            )
        }
        assertRendered(html, "alert", "Alert defaults", closes = "</div></div>")
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                id = htmlId("x-cov-id"),
                soft = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "alert alert-soft zz-extra", "Alert all flags", closes = "</div></div>")
        assertCommonFlags(html, "Alert")
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
        assertRendered(html, "alert alert-info", "Alert color Info")
    }

    @Test
    fun alert_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Success,
                content = { },
            )
        }
        assertRendered(html, "alert alert-success", "Alert color Success")
    }

    @Test
    fun alert_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "alert alert-warning", "Alert color Warning")
    }

    @Test
    fun alert_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                color = AlertColor.Error,
                content = { },
            )
        }
        assertRendered(html, "alert alert-error", "Alert color Error")
    }

    @Test
    fun alert_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                style = AlertStyle.Outline,
                content = { },
            )
        }
        assertRendered(html, "alert alert-outline", "Alert style Outline")
    }

    @Test
    fun alert_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                style = AlertStyle.Dash,
                content = { },
            )
        }
        assertRendered(html, "alert alert-dash", "Alert style Dash")
    }

    @Test
    fun alert_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                direction = AlertDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "alert alert-vertical", "Alert direction Vertical")
    }

    @Test
    fun alert_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                direction = AlertDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "alert alert-horizontal", "Alert direction Horizontal")
    }

    @Test
    fun alertClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses()
        }
        assertRendered(html, "alert", "AlertClasses defaults")
    }

    @Test
    fun alertClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                soft = true,
            )
        }
        assertRendered(html, "alert alert-soft", "AlertClasses all flags")
    }

    @Test
    fun alertClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Info,
            )
        }
        assertRendered(html, "alert alert-info", "AlertClasses color Info")
    }

    @Test
    fun alertClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Success,
            )
        }
        assertRendered(html, "alert alert-success", "AlertClasses color Success")
    }

    @Test
    fun alertClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Warning,
            )
        }
        assertRendered(html, "alert alert-warning", "AlertClasses color Warning")
    }

    @Test
    fun alertClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                color = AlertColor.Error,
            )
        }
        assertRendered(html, "alert alert-error", "AlertClasses color Error")
    }

    @Test
    fun alertClasses_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                style = AlertStyle.Outline,
            )
        }
        assertRendered(html, "alert alert-outline", "AlertClasses style Outline")
    }

    @Test
    fun alertClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                style = AlertStyle.Dash,
            )
        }
        assertRendered(html, "alert alert-dash", "AlertClasses style Dash")
    }

    @Test
    fun alertClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                direction = AlertDirection.Vertical,
            )
        }
        assertRendered(html, "alert alert-vertical", "AlertClasses direction Vertical")
    }

    @Test
    fun alertClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlertClasses(
                direction = AlertDirection.Horizontal,
            )
        }
        assertRendered(html, "alert alert-horizontal", "AlertClasses direction Horizontal")
    }
}
