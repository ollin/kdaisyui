package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TooltipCoverageTest {

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
    fun tooltip_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip defaults", "tooltip", closes = "</div></div>"))
    }

    @Test
    fun tooltip_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                id = htmlId("x-cov-id"),
                open = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Tooltip all flags", "tooltip tooltip-open zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tooltip_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Primary", "tooltip tooltip-primary"))
    }

    @Test
    fun tooltip_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Secondary", "tooltip tooltip-secondary"))
    }

    @Test
    fun tooltip_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Accent", "tooltip tooltip-accent"))
    }

    @Test
    fun tooltip_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Info", "tooltip tooltip-info"))
    }

    @Test
    fun tooltip_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Success", "tooltip tooltip-success"))
    }

    @Test
    fun tooltip_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Warning", "tooltip tooltip-warning"))
    }

    @Test
    fun tooltip_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip color Error", "tooltip tooltip-error"))
    }

    @Test
    fun tooltip_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip side Top", "tooltip tooltip-top"))
    }

    @Test
    fun tooltip_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip side Bottom", "tooltip tooltip-bottom"))
    }

    @Test
    fun tooltip_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Left,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip side Left", "tooltip tooltip-left"))
    }

    @Test
    fun tooltip_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Right,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip side Right", "tooltip tooltip-right"))
    }

    @Test
    fun tooltip_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip align Start", "tooltip tooltip-start"))
    }

    @Test
    fun tooltip_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.Center,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip align Center", "tooltip tooltip-center"))
    }

    @Test
    fun tooltip_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Tooltip align End", "tooltip tooltip-end"))
    }

    @Test
    fun tooltipClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses()
        }
        assertRendered(html, Expected("TooltipClasses defaults", "tooltip"))
    }

    @Test
    fun tooltipClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                open = true,
            )
        }
        val expected = Expected("TooltipClasses all flags", "tooltip tooltip-open", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun tooltipClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Primary,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Primary", "tooltip tooltip-primary"))
    }

    @Test
    fun tooltipClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Secondary,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Secondary", "tooltip tooltip-secondary"))
    }

    @Test
    fun tooltipClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Accent,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Accent", "tooltip tooltip-accent"))
    }

    @Test
    fun tooltipClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Info,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Info", "tooltip tooltip-info"))
    }

    @Test
    fun tooltipClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Success,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Success", "tooltip tooltip-success"))
    }

    @Test
    fun tooltipClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Warning,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Warning", "tooltip tooltip-warning"))
    }

    @Test
    fun tooltipClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Error,
            )
        }
        assertRendered(html, Expected("TooltipClasses color Error", "tooltip tooltip-error"))
    }

    @Test
    fun tooltipClasses_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Top,
            )
        }
        assertRendered(html, Expected("TooltipClasses side Top", "tooltip tooltip-top"))
    }

    @Test
    fun tooltipClasses_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Bottom,
            )
        }
        assertRendered(html, Expected("TooltipClasses side Bottom", "tooltip tooltip-bottom"))
    }

    @Test
    fun tooltipClasses_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Left,
            )
        }
        assertRendered(html, Expected("TooltipClasses side Left", "tooltip tooltip-left"))
    }

    @Test
    fun tooltipClasses_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Right,
            )
        }
        assertRendered(html, Expected("TooltipClasses side Right", "tooltip tooltip-right"))
    }

    @Test
    fun tooltipClasses_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.Start,
            )
        }
        assertRendered(html, Expected("TooltipClasses align Start", "tooltip tooltip-start"))
    }

    @Test
    fun tooltipClasses_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.Center,
            )
        }
        assertRendered(html, Expected("TooltipClasses align Center", "tooltip tooltip-center"))
    }

    @Test
    fun tooltipClasses_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.End,
            )
        }
        assertRendered(html, Expected("TooltipClasses align End", "tooltip tooltip-end"))
    }

    @Test
    fun tooltipContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent(
                content = { },
            )
        }
        assertRendered(html, Expected("TooltipContent defaults", "tooltip-content", closes = "</div></div>"))
    }

    @Test
    fun tooltipContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("TooltipContent all flags", "tooltip-content zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tooltipContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContentClasses()
        }
        assertRendered(html, Expected("TooltipContentClasses defaults", "tooltip-content"))
    }

    @Test
    fun tooltipContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContentClasses()
        }
        val expected = Expected("TooltipContentClasses all flags", "tooltip-content", content = false)
        assertRendered(html, expected)
    }
}
