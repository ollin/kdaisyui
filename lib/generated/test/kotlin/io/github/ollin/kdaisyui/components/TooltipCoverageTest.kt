package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TooltipCoverageTest {

    @Test
    fun tooltip_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip", actualClasses, "Tooltip defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-bottom tooltip-center tooltip-end tooltip-left tooltip-open tooltip-right tooltip-start tooltip-top zz-extra", actualClasses, "Tooltip all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-primary", actualClasses, "Tooltip variant Primary")
    }

    @Test
    fun tooltip_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-secondary", actualClasses, "Tooltip variant Secondary")
    }

    @Test
    fun tooltip_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-accent", actualClasses, "Tooltip variant Accent")
    }

    @Test
    fun tooltip_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-info", actualClasses, "Tooltip variant Info")
    }

    @Test
    fun tooltip_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-success", actualClasses, "Tooltip variant Success")
    }

    @Test
    fun tooltip_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-warning", actualClasses, "Tooltip variant Warning")
    }

    @Test
    fun tooltip_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(
                variant = TooltipVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip tooltip-error", actualClasses, "Tooltip variant Error")
    }

    @Test
    fun tooltipContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip-content", actualClasses, "TooltipContent defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tooltip-content zz-extra", actualClasses, "TooltipContent all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TooltipContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TooltipContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TooltipContent content")
    }
}
