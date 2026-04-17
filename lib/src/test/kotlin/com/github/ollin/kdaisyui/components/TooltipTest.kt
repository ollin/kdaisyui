package com.github.ollin.kdaisyui.components

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
            daisyTooltip(open = true, top = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Top")
    }

    @Test
    fun bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, bottom = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-bottom tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Bottom")
    }

    @Test
    fun left() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, left = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-left tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Left")
    }

    @Test
    fun right() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true, right = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Right")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(open = true) {
            }
        }
        val expectedClasses = "tooltip tooltip-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
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
}
