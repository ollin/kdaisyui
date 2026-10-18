package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TooltipTest {

    @Test
    fun tooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        val expectedClasses = "tooltip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Tooltip")
    }

    @Test
    fun tooltip_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Tooltip")
    }

    @Test
    fun tooltip_with_tooltip_content_daisyTooltipContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltipContent() {
            }
        }
        val expectedClasses = "tooltip-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Tooltip with tooltip-content")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force open")
    }

    @Test
    fun top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top, align = TooltipAlign.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-start tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun top_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun top_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Top, align = TooltipAlign.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom, align = TooltipAlign.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun bottom_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun bottom_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Bottom, align = TooltipAlign.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-end tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left, align = TooltipAlign.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-left tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun left_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left) {
            }
        }
        val expectedClasses = "tooltip tooltip-left tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun left_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Left, align = TooltipAlign.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-left tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right, align = TooltipAlign.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-right tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun right_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun right_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, side = TooltipSide.Right, align = TooltipAlign.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Primary) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Secondary) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun secondary_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Accent) {
            }
        }
        val expectedClasses = "tooltip tooltip-accent tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun accent_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Info) {
            }
        }
        val expectedClasses = "tooltip tooltip-info tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun info_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Success) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun success_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Warning) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun warning_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, color = TooltipColor.Error) {
            }
        }
        val expectedClasses = "tooltip tooltip-error tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun error_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun responsive_tooltip_position() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(align = TooltipAlign.Start, extraClasses = "md:tooltip-right md:tooltip-center") {
            }
        }
        val expectedClasses = "md:tooltip-center md:tooltip-right tooltip tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive tooltip position")
    }
}
