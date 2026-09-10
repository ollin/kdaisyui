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
                bottom = true,
                center = true,
                end = true,
                left = true,
                open = true,
                right = true,
                start = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tooltip tooltip-bottom tooltip-center tooltip-end tooltip-left tooltip-open tooltip-right tooltip-start tooltip-top zz-extra", "Tooltip all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Tooltip id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Tooltip attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Tooltip content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "TooltipContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TooltipContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TooltipContent content")
    }
}
