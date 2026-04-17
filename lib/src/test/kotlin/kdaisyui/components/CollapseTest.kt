package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CollapseTest {

    @Test
    fun collapse_with_focus() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse with focus")
    }

    @Test
    fun collapse_with_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse with checkbox")
    }

    @Test
    fun collapse_with_checkbox_and_close_on_click_outside() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse with checkbox and close on click outside")
    }

    @Test
    fun collapse_using_details_and_summary_tag() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse using details and summary tag")
    }

    @Test
    fun without_border_and_background_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Without border and background color")
    }

    @Test
    fun with_arrow_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(arrow = true) {
            }
        }
        val expectedClasses = "collapse collapse-arrow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With arrow icon")
    }

    @Test
    fun with_arrow_plus_minus_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(plus = true) {
            }
        }
        val expectedClasses = "collapse collapse-plus"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With arrow plus/minus icon")
    }

    @Test
    fun moving_collapse_icon_to_the_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(arrow = true) {
            }
        }
        val expectedClasses = "collapse collapse-arrow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Moving collapse icon to the start")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(open = true) {
            }
        }
        val expectedClasses = "collapse collapse-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force open")
    }

    @Test
    fun force_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(close = true) {
            }
        }
        val expectedClasses = "collapse collapse-close"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force close")
    }

    @Test
    fun custom_colors_for_collapse_that_works_with_focus() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom colors for collapse that works with focus")
    }

    @Test
    fun custom_colors_for_collapse_that_works_with_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom colors for collapse that works with checkbox")
    }
}
