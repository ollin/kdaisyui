package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadioCoverageTest {

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
    fun radio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        assertRendered(html, "radio", "Radio defaults")
        assertTrue(html.contains("type=\""), "Radio sets type")
    }

    @Test
    fun radio_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                id = htmlId("x-cov-id"),
                checked = true,
                disabled = true,
                name = "x",
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "radio zz-extra", "Radio all flags")
        assertCommonFlags(html, "Radio", content = false)
        assertTrue(html.contains("name=\"x\""), "Radio name")
        assertTrue(html.contains("type=\""), "Radio sets type")
        assertTrue(html.contains("name=\""), "Radio sets name")
        assertTrue(html.contains("checked=\""), "Radio sets checked")
        assertTrue(html.contains("disabled=\""), "Radio sets disabled")
    }

    @Test
    fun radio_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Neutral,
            )
        }
        assertRendered(html, "radio radio-neutral", "Radio variant Neutral")
    }

    @Test
    fun radio_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Primary,
            )
        }
        assertRendered(html, "radio radio-primary", "Radio variant Primary")
    }

    @Test
    fun radio_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Secondary,
            )
        }
        assertRendered(html, "radio radio-secondary", "Radio variant Secondary")
    }

    @Test
    fun radio_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Accent,
            )
        }
        assertRendered(html, "radio radio-accent", "Radio variant Accent")
    }

    @Test
    fun radio_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Success,
            )
        }
        assertRendered(html, "radio radio-success", "Radio variant Success")
    }

    @Test
    fun radio_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Warning,
            )
        }
        assertRendered(html, "radio radio-warning", "Radio variant Warning")
    }

    @Test
    fun radio_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Info,
            )
        }
        assertRendered(html, "radio radio-info", "Radio variant Info")
    }

    @Test
    fun radio_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Error,
            )
        }
        assertRendered(html, "radio radio-error", "Radio variant Error")
    }

    @Test
    fun radio_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xs,
            )
        }
        assertRendered(html, "radio radio-xs", "Radio size Xs")
    }

    @Test
    fun radio_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Sm,
            )
        }
        assertRendered(html, "radio radio-sm", "Radio size Sm")
    }

    @Test
    fun radio_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Md,
            )
        }
        assertRendered(html, "radio radio-md", "Radio size Md")
    }

    @Test
    fun radio_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Lg,
            )
        }
        assertRendered(html, "radio radio-lg", "Radio size Lg")
    }

    @Test
    fun radio_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xl,
            )
        }
        assertRendered(html, "radio radio-xl", "Radio size Xl")
    }
}
