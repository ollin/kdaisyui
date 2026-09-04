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
            daisyTooltip(open = true, top = true, start = true, end = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-start tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, bottom = true, start = true, end = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-end tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, left = true, start = true, end = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-left tooltip-open tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, right = true, start = true, end = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-end tooltip-open tooltip-right tooltip-start"
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
    fun responsive_tooltip_only_show_for_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        val expectedClasses = "tooltip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive tooltip. only show for large screen")
    }

    @Test
    fun responsive_tooltip_position() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(start = true, right = true, center = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-center tooltip-right tooltip-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive tooltip position")
    }
}
