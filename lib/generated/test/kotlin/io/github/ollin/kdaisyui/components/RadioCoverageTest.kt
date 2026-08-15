package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadioCoverageTest {

    @Test
    fun radio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio", actualClasses, "Radio defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio zz-extra", actualClasses, "Radio all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Radio id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Radio attrs")
        assertTrue(html.contains("name=\"x\""), "Radio name")
    }

    @Test
    fun radio_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-neutral", actualClasses, "Radio variant Neutral")
    }

    @Test
    fun radio_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-primary", actualClasses, "Radio variant Primary")
    }

    @Test
    fun radio_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-secondary", actualClasses, "Radio variant Secondary")
    }

    @Test
    fun radio_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-accent", actualClasses, "Radio variant Accent")
    }

    @Test
    fun radio_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-success", actualClasses, "Radio variant Success")
    }

    @Test
    fun radio_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-warning", actualClasses, "Radio variant Warning")
    }

    @Test
    fun radio_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-info", actualClasses, "Radio variant Info")
    }

    @Test
    fun radio_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                variant = RadioVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-error", actualClasses, "Radio variant Error")
    }

    @Test
    fun radio_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-xs", actualClasses, "Radio size Xs")
    }

    @Test
    fun radio_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-sm", actualClasses, "Radio size Sm")
    }

    @Test
    fun radio_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-md", actualClasses, "Radio size Md")
    }

    @Test
    fun radio_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-lg", actualClasses, "Radio size Lg")
    }

    @Test
    fun radio_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radio radio-xl", actualClasses, "Radio size Xl")
    }
}
