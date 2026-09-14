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
    fun tooltip_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-primary", "Tooltip variant Primary")
    }

    @Test
    fun tooltip_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-secondary", "Tooltip variant Secondary")
    }

    @Test
    fun tooltip_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-accent", "Tooltip variant Accent")
    }

    @Test
    fun tooltip_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-info", "Tooltip variant Info")
    }

    @Test
    fun tooltip_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-success", "Tooltip variant Success")
    }

    @Test
    fun tooltip_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-warning", "Tooltip variant Warning")
    }

    @Test
    fun tooltip_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-error", "Tooltip variant Error")
    }

    @Test
    fun tooltip_sidePlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                sidePlacement = TooltipSidePlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-top", "Tooltip sidePlacement Top")
    }

    @Test
    fun tooltip_sidePlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                sidePlacement = TooltipSidePlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-bottom", "Tooltip sidePlacement Bottom")
    }

    @Test
    fun tooltip_sidePlacement_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                sidePlacement = TooltipSidePlacement.Left,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-left", "Tooltip sidePlacement Left")
    }

    @Test
    fun tooltip_sidePlacement_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                sidePlacement = TooltipSidePlacement.Right,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-right", "Tooltip sidePlacement Right")
    }

    @Test
    fun tooltip_alignPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                alignPlacement = TooltipAlignPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-start", "Tooltip alignPlacement Start")
    }

    @Test
    fun tooltip_alignPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                alignPlacement = TooltipAlignPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-center", "Tooltip alignPlacement Center")
    }

    @Test
    fun tooltip_alignPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                alignPlacement = TooltipAlignPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "tooltip tooltip-end", "Tooltip alignPlacement End")
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
}
