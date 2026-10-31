package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RangeCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun range_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange()
        }
        assertRendered(html, Expected("Range defaults", "range"))
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
        val expected = Expected("Range all flags", "range range-vertical zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
    fun range_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Neutral,
            )
        }
        assertRendered(html, Expected("Range color Neutral", "range range-neutral"))
    }

    @Test
    fun range_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Primary,
            )
        }
        assertRendered(html, Expected("Range color Primary", "range range-primary"))
    }

    @Test
    fun range_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Secondary,
            )
        }
        assertRendered(html, Expected("Range color Secondary", "range range-secondary"))
    }

    @Test
    fun range_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Accent,
            )
        }
        assertRendered(html, Expected("Range color Accent", "range range-accent"))
    }

    @Test
    fun range_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Success,
            )
        }
        assertRendered(html, Expected("Range color Success", "range range-success"))
    }

    @Test
    fun range_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Warning,
            )
        }
        assertRendered(html, Expected("Range color Warning", "range range-warning"))
    }

    @Test
    fun range_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Info,
            )
        }
        assertRendered(html, Expected("Range color Info", "range range-info"))
    }

    @Test
    fun range_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                color = RangeColor.Error,
            )
        }
        assertRendered(html, Expected("Range color Error", "range range-error"))
    }

    @Test
    fun range_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xs,
            )
        }
        assertRendered(html, Expected("Range size Xs", "range range-xs"))
    }

    @Test
    fun range_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Sm,
            )
        }
        assertRendered(html, Expected("Range size Sm", "range range-sm"))
    }

    @Test
    fun range_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Md,
            )
        }
        assertRendered(html, Expected("Range size Md", "range range-md"))
    }

    @Test
    fun range_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Lg,
            )
        }
        assertRendered(html, Expected("Range size Lg", "range range-lg"))
    }

    @Test
    fun range_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(
                size = RangeSize.Xl,
            )
        }
        assertRendered(html, Expected("Range size Xl", "range range-xl"))
    }

    @Test
    fun rangeClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses()
        }
        assertRendered(html, Expected("RangeClasses defaults", "range"))
    }

    @Test
    fun rangeClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                vertical = true,
            )
        }
        val expected = Expected("RangeClasses all flags", "range range-vertical", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun rangeClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Neutral,
            )
        }
        assertRendered(html, Expected("RangeClasses color Neutral", "range range-neutral"))
    }

    @Test
    fun rangeClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Primary,
            )
        }
        assertRendered(html, Expected("RangeClasses color Primary", "range range-primary"))
    }

    @Test
    fun rangeClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Secondary,
            )
        }
        assertRendered(html, Expected("RangeClasses color Secondary", "range range-secondary"))
    }

    @Test
    fun rangeClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Accent,
            )
        }
        assertRendered(html, Expected("RangeClasses color Accent", "range range-accent"))
    }

    @Test
    fun rangeClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Success,
            )
        }
        assertRendered(html, Expected("RangeClasses color Success", "range range-success"))
    }

    @Test
    fun rangeClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Warning,
            )
        }
        assertRendered(html, Expected("RangeClasses color Warning", "range range-warning"))
    }

    @Test
    fun rangeClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Info,
            )
        }
        assertRendered(html, Expected("RangeClasses color Info", "range range-info"))
    }

    @Test
    fun rangeClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                color = RangeColor.Error,
            )
        }
        assertRendered(html, Expected("RangeClasses color Error", "range range-error"))
    }

    @Test
    fun rangeClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                size = RangeSize.Xs,
            )
        }
        assertRendered(html, Expected("RangeClasses size Xs", "range range-xs"))
    }

    @Test
    fun rangeClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                size = RangeSize.Sm,
            )
        }
        assertRendered(html, Expected("RangeClasses size Sm", "range range-sm"))
    }

    @Test
    fun rangeClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                size = RangeSize.Md,
            )
        }
        assertRendered(html, Expected("RangeClasses size Md", "range range-md"))
    }

    @Test
    fun rangeClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                size = RangeSize.Lg,
            )
        }
        assertRendered(html, Expected("RangeClasses size Lg", "range range-lg"))
    }

    @Test
    fun rangeClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRangeClasses(
                size = RangeSize.Xl,
            )
        }
        assertRendered(html, Expected("RangeClasses size Xl", "range range-xl"))
    }
}
