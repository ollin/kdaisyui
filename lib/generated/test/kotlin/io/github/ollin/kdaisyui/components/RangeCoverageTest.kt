package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RangeCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun range_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange()
        }
        assertRendered(html, "range", "Range defaults")
        assertTrue(html.contains("type=\""), "Range sets type")
    }

    @Test
    fun range_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                id = htmlId("x-cov-id"),
                vertical = true,
                disabled = true,
                min = "x",
                max = "x",
                value = "x",
                step = "x",
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "range range-vertical zz-extra", "Range all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Range id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Range attrs")
        assertTrue(html.contains("min=\"x\""), "Range min")
        assertTrue(html.contains("max=\"x\""), "Range max")
        assertTrue(html.contains("value=\"x\""), "Range value")
        assertTrue(html.contains("step=\"x\""), "Range step")
        assertTrue(html.contains("type=\""), "Range sets type")
        assertTrue(html.contains("min=\""), "Range sets min")
        assertTrue(html.contains("max=\""), "Range sets max")
        assertTrue(html.contains("value=\""), "Range sets value")
        assertTrue(html.contains("step=\""), "Range sets step")
        assertTrue(html.contains("disabled=\""), "Range sets disabled")
    }

    @Test
    fun range_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Neutral,
            )
        }
        assertRendered(html, "range range-neutral", "Range variant Neutral")
    }

    @Test
    fun range_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Primary,
            )
        }
        assertRendered(html, "range range-primary", "Range variant Primary")
    }

    @Test
    fun range_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Secondary,
            )
        }
        assertRendered(html, "range range-secondary", "Range variant Secondary")
    }

    @Test
    fun range_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Accent,
            )
        }
        assertRendered(html, "range range-accent", "Range variant Accent")
    }

    @Test
    fun range_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Success,
            )
        }
        assertRendered(html, "range range-success", "Range variant Success")
    }

    @Test
    fun range_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Warning,
            )
        }
        assertRendered(html, "range range-warning", "Range variant Warning")
    }

    @Test
    fun range_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Info,
            )
        }
        assertRendered(html, "range range-info", "Range variant Info")
    }

    @Test
    fun range_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Error,
            )
        }
        assertRendered(html, "range range-error", "Range variant Error")
    }

    @Test
    fun range_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xs,
            )
        }
        assertRendered(html, "range range-xs", "Range size Xs")
    }

    @Test
    fun range_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Sm,
            )
        }
        assertRendered(html, "range range-sm", "Range size Sm")
    }

    @Test
    fun range_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Md,
            )
        }
        assertRendered(html, "range range-md", "Range size Md")
    }

    @Test
    fun range_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Lg,
            )
        }
        assertRendered(html, "range range-lg", "Range size Lg")
    }

    @Test
    fun range_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xl,
            )
        }
        assertRendered(html, "range range-xl", "Range size Xl")
    }
}
