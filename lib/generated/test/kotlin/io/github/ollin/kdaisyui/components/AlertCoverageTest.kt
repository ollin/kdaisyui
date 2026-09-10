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
                dash = true,
                horizontal = true,
                outline = true,
                soft = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "alert alert-dash alert-horizontal alert-outline alert-soft alert-vertical zz-extra", "Alert all flags", closes = "</div></div>")
        assertCommonFlags(html, "Alert")
        assertTrue(html.contains("role=\""), "Alert sets role")
    }

    @Test
    fun alert_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "alert alert-info", "Alert variant Info")
    }

    @Test
    fun alert_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "alert alert-success", "Alert variant Success")
    }

    @Test
    fun alert_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "alert alert-warning", "Alert variant Warning")
    }

    @Test
    fun alert_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "alert alert-error", "Alert variant Error")
    }
}
