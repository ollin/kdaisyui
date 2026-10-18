package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RangeTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun range() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange()
        }
        assertDocumented(html, "input", "range", "Range")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Neutral)
        }
        assertDocumented(html, "input", "range range-neutral", "Neutral color")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Primary)
        }
        assertDocumented(html, "input", "range range-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Secondary)
        }
        assertDocumented(html, "input", "range range-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Accent)
        }
        assertDocumented(html, "input", "range range-accent", "Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Success)
        }
        assertDocumented(html, "input", "range range-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Warning)
        }
        assertDocumented(html, "input", "range range-warning", "Warning color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Info)
        }
        assertDocumented(html, "input", "range range-info", "Info color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(color = RangeColor.Error)
        }
        assertDocumented(html, "input", "range range-error", "Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Xs)
        }
        assertDocumented(html, "input", "range range-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Sm)
        }
        assertDocumented(html, "input", "range range-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Md)
        }
        assertDocumented(html, "input", "range range-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Lg)
        }
        assertDocumented(html, "input", "range range-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(size = RangeSize.Xl)
        }
        assertDocumented(html, "input", "range range-xl", "Sizes")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(vertical = true)
        }
        assertDocumented(html, "input", "range range-vertical", "Vertical")
    }
}
