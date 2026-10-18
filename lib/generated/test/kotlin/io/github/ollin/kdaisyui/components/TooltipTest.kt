package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TooltipTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun tooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        assertDocumented(html, "div", "tooltip", "Tooltip")
    }

    @Test
    fun tooltip_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Tooltip")
    }

    @Test
    fun tooltip_with_tooltip_content_daisyTooltipContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent() {
            }
        }
        assertDocumented(html, "div", "tooltip-content", "Tooltip with tooltip-content")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open", "Force open")
    }

    @Test
    fun top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top, align = TooltipAlign.Start) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-start tooltip-top", "Top")
    }

    @Test
    fun top_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-top", "Top")
    }

    @Test
    fun top_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top, align = TooltipAlign.End) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-end tooltip-open tooltip-top", "Top")
    }

    @Test
    fun bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom, align = TooltipAlign.Start) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-bottom tooltip-open tooltip-start", "Bottom")
    }

    @Test
    fun bottom_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-bottom tooltip-open", "Bottom")
    }

    @Test
    fun bottom_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom, align = TooltipAlign.End) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-bottom tooltip-end tooltip-open", "Bottom")
    }

    @Test
    fun left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left, align = TooltipAlign.Start) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-left tooltip-open tooltip-start", "Left")
    }

    @Test
    fun left_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-left tooltip-open", "Left")
    }

    @Test
    fun left_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left, align = TooltipAlign.End) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-end tooltip-left tooltip-open", "Left")
    }

    @Test
    fun right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right, align = TooltipAlign.Start) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-right tooltip-start", "Right")
    }

    @Test
    fun right_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-right", "Right")
    }

    @Test
    fun right_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right, align = TooltipAlign.End) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-end tooltip-open tooltip-right", "Right")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Primary) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-primary", "Primary color")
    }

    @Test
    fun primary_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Secondary) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-secondary", "Secondary color")
    }

    @Test
    fun secondary_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Accent) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-accent tooltip-open", "Accent color")
    }

    @Test
    fun accent_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent", "Accent color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Info) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-info tooltip-open", "Info color")
    }

    @Test
    fun info_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-info", "Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Success) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-success", "Success color")
    }

    @Test
    fun success_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Warning) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-open tooltip-warning", "Warning color")
    }

    @Test
    fun warning_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-warning", "Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Error) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-error tooltip-open", "Error color")
    }

    @Test
    fun error_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-error", "Error color")
    }

    @Test
    fun responsive_tooltip_position() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(align = TooltipAlign.Start, extraClasses = "md:tooltip-right md:tooltip-center") {
            }
        }
        assertDocumented(html, "div", "md:tooltip-center md:tooltip-right tooltip tooltip-start", "Responsive tooltip position")
    }
}
