package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class IndicatorTest {

    @Test
    fun status_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status Indicator")
    }

    @Test
    fun badge_as_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge as indicator")
    }

    @Test
    fun for_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for button")
    }

    @Test
    fun for_tab() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for tab")
    }

    @Test
    fun for_avatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for avatar")
    }

    @Test
    fun for_an_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for an input")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(bottom = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun in_center_of_an_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(center = true, middle = true) {
            }
        }
        val expectedClasses = "indicator indicator-center indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for in center of an image")
    }

    @Test
    fun indicator_top_default_indicator_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(start = true) {
            }
        }
        val expectedClasses = "indicator indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-top (default) indicator-start")
    }

    @Test
    fun indicator_top_default_indicator_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(center = true) {
            }
        }
        val expectedClasses = "indicator indicator-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-top (default) indicator-center")
    }

    @Test
    fun indicator_top_default_indicator_end_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-top (default) indicator-end (default)")
    }

    @Test
    fun indicator_middle_indicator_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(middle = true, start = true) {
            }
        }
        val expectedClasses = "indicator indicator-middle indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-start")
    }

    @Test
    fun indicator_middle_indicator_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(middle = true, center = true) {
            }
        }
        val expectedClasses = "indicator indicator-center indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-center")
    }

    @Test
    fun indicator_middle_indicator_end_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(middle = true) {
            }
        }
        val expectedClasses = "indicator indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-end (default)")
    }

    @Test
    fun indicator_bottom_indicator_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(bottom = true, start = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-start")
    }

    @Test
    fun indicator_bottom_indicator_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(bottom = true, center = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom indicator-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-center")
    }

    @Test
    fun indicator_bottom_indicator_end_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(bottom = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-end (default)")
    }

    @Test
    fun multiple_indicators() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(top = true, start = true, center = true, end = true, middle = true, bottom = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom indicator-center indicator-end indicator-middle indicator-start indicator-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
    }

    @Test
    fun responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(start = true, middle = true, bottom = true, center = true, end = true) {
            }
        }
        val expectedClasses = "indicator indicator-bottom indicator-center indicator-end indicator-middle indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive")
    }
}
