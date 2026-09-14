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
    fun tooltip_with_tooltip_content() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        val expectedClasses = "tooltip"
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
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Top, alignPlacement = TooltipAlignPlacement.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-start tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun top_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Top) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun top_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Top, alignPlacement = TooltipAlignPlacement.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Bottom, alignPlacement = TooltipAlignPlacement.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun bottom_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Bottom) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun bottom_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Bottom, alignPlacement = TooltipAlignPlacement.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-end tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Left, alignPlacement = TooltipAlignPlacement.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-left tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun left_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Left) {
            }
        }
        val expectedClasses = "tooltip tooltip-left tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun left_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Left, alignPlacement = TooltipAlignPlacement.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-left tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Right, alignPlacement = TooltipAlignPlacement.Start) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-right tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun right_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Right) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun right_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, sidePlacement = TooltipSidePlacement.Right, alignPlacement = TooltipAlignPlacement.End) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun responsive_tooltip_position() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(alignPlacement = TooltipAlignPlacement.Start, extraClasses = "md:tooltip-right md:tooltip-center") {
            }
        }
        val expectedClasses = "md:tooltip-center md:tooltip-right tooltip tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive tooltip position")
    }
}
