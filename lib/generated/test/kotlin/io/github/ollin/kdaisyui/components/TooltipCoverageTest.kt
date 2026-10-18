package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TooltipCoverageTest {

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
    fun tooltip_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                content = { },
            )
        }
        assertRendered(html, "tooltip", "Tooltip defaults", closes = "</div></div>")
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
        assertRendered(html, "tooltip tooltip-open zz-extra", "Tooltip all flags", closes = "</div></div>")
        assertCommonFlags(html, "Tooltip")
    }

    @Test
    fun tooltip_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-primary", "Tooltip color Primary")
    }

    @Test
    fun tooltip_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-secondary", "Tooltip color Secondary")
    }

    @Test
    fun tooltip_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-accent", "Tooltip color Accent")
    }

    @Test
    fun tooltip_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Info,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-info", "Tooltip color Info")
    }

    @Test
    fun tooltip_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Success,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-success", "Tooltip color Success")
    }

    @Test
    fun tooltip_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-warning", "Tooltip color Warning")
    }

    @Test
    fun tooltip_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                color = TooltipColor.Error,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-error", "Tooltip color Error")
    }

    @Test
    fun tooltip_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Top,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-top", "Tooltip side Top")
    }

    @Test
    fun tooltip_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Bottom,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-bottom", "Tooltip side Bottom")
    }

    @Test
    fun tooltip_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Left,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-left", "Tooltip side Left")
    }

    @Test
    fun tooltip_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                side = TooltipSide.Right,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-right", "Tooltip side Right")
    }

    @Test
    fun tooltip_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.Start,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-start", "Tooltip align Start")
    }

    @Test
    fun tooltip_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.Center,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-center", "Tooltip align Center")
    }

    @Test
    fun tooltip_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                align = TooltipAlign.End,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-end", "Tooltip align End")
    }

    @Test
    fun tooltipClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses()
        }
        assertRendered(html, "tooltip", "TooltipClasses defaults")
    }

    @Test
    fun tooltipClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                open = true,
            )
        }
        assertRendered(html, "tooltip tooltip-open", "TooltipClasses all flags")
    }

    @Test
    fun tooltipClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Primary,
            )
        }
        assertRendered(html, "tooltip tooltip-primary", "TooltipClasses color Primary")
    }

    @Test
    fun tooltipClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Secondary,
            )
        }
        assertRendered(html, "tooltip tooltip-secondary", "TooltipClasses color Secondary")
    }

    @Test
    fun tooltipClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Accent,
            )
        }
        assertRendered(html, "tooltip tooltip-accent", "TooltipClasses color Accent")
    }

    @Test
    fun tooltipClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Info,
            )
        }
        assertRendered(html, "tooltip tooltip-info", "TooltipClasses color Info")
    }

    @Test
    fun tooltipClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Success,
            )
        }
        assertRendered(html, "tooltip tooltip-success", "TooltipClasses color Success")
    }

    @Test
    fun tooltipClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Warning,
            )
        }
        assertRendered(html, "tooltip tooltip-warning", "TooltipClasses color Warning")
    }

    @Test
    fun tooltipClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                color = TooltipColor.Error,
            )
        }
        assertRendered(html, "tooltip tooltip-error", "TooltipClasses color Error")
    }

    @Test
    fun tooltipClasses_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Top,
            )
        }
        assertRendered(html, "tooltip tooltip-top", "TooltipClasses side Top")
    }

    @Test
    fun tooltipClasses_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Bottom,
            )
        }
        assertRendered(html, "tooltip tooltip-bottom", "TooltipClasses side Bottom")
    }

    @Test
    fun tooltipClasses_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Left,
            )
        }
        assertRendered(html, "tooltip tooltip-left", "TooltipClasses side Left")
    }

    @Test
    fun tooltipClasses_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                side = TooltipSide.Right,
            )
        }
        assertRendered(html, "tooltip tooltip-right", "TooltipClasses side Right")
    }

    @Test
    fun tooltipClasses_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.Start,
            )
        }
        assertRendered(html, "tooltip tooltip-start", "TooltipClasses align Start")
    }

    @Test
    fun tooltipClasses_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.Center,
            )
        }
        assertRendered(html, "tooltip tooltip-center", "TooltipClasses align Center")
    }

    @Test
    fun tooltipClasses_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipClasses(
                align = TooltipAlign.End,
            )
        }
        assertRendered(html, "tooltip tooltip-end", "TooltipClasses align End")
    }

    @Test
    fun tooltipContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent(
                content = { },
            )
        }
        assertRendered(html, "tooltip-content", "TooltipContent defaults", closes = "</div></div>")
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
        assertRendered(html, "tooltip-content zz-extra", "TooltipContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "TooltipContent")
    }

    @Test
    fun tooltipContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContentClasses()
        }
        assertRendered(html, "tooltip-content", "TooltipContentClasses defaults")
    }

    @Test
    fun tooltipContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContentClasses()
        }
        assertRendered(html, "tooltip-content", "TooltipContentClasses all flags")
    }
}
