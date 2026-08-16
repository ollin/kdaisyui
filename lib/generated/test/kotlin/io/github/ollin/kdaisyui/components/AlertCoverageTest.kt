package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AlertCoverageTest {

    @Test
    fun alert_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert", actualClasses, "Alert defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert alert-dash alert-horizontal alert-outline alert-soft alert-vertical zz-extra", actualClasses, "Alert all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Alert id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Alert attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Alert content")
    }

    @Test
    fun alert_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert alert-info", actualClasses, "Alert variant Info")
    }

    @Test
    fun alert_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert alert-success", actualClasses, "Alert variant Success")
    }

    @Test
    fun alert_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert alert-warning", actualClasses, "Alert variant Warning")
    }

    @Test
    fun alert_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(
                variant = AlertVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("alert alert-error", actualClasses, "Alert variant Error")
    }
}
