package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RangeCoverageTest {

    @Test
    fun range_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range", actualClasses, "Range defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-vertical zz-extra", actualClasses, "Range all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Range id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Range attrs")
        assertTrue(html.contains("min=\"x\""), "Range min")
        assertTrue(html.contains("max=\"x\""), "Range max")
        assertTrue(html.contains("value=\"x\""), "Range value")
        assertTrue(html.contains("step=\"x\""), "Range step")
    }

    @Test
    fun range_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-neutral", actualClasses, "Range variant Neutral")
    }

    @Test
    fun range_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-primary", actualClasses, "Range variant Primary")
    }

    @Test
    fun range_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-secondary", actualClasses, "Range variant Secondary")
    }

    @Test
    fun range_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-accent", actualClasses, "Range variant Accent")
    }

    @Test
    fun range_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-success", actualClasses, "Range variant Success")
    }

    @Test
    fun range_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-warning", actualClasses, "Range variant Warning")
    }

    @Test
    fun range_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-info", actualClasses, "Range variant Info")
    }

    @Test
    fun range_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                variant = RangeVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-error", actualClasses, "Range variant Error")
    }

    @Test
    fun range_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-xs", actualClasses, "Range size Xs")
    }

    @Test
    fun range_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-sm", actualClasses, "Range size Sm")
    }

    @Test
    fun range_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-md", actualClasses, "Range size Md")
    }

    @Test
    fun range_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-lg", actualClasses, "Range size Lg")
    }

    @Test
    fun range_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("range range-xl", actualClasses, "Range size Xl")
    }
}
