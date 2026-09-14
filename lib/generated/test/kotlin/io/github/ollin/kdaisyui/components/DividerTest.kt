package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DividerTest {

    @Test
    fun divider() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider() {
            }
        }
        val expectedClasses = "divider"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider")
    }

    @Test
    fun divider_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal) {
            }
        }
        val expectedClasses = "divider divider-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider horizontal")
    }

    @Test
    fun divider_with_no_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider() {
            }
        }
        val expectedClasses = "divider"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with no text")
    }

    @Test
    fun responsive_lg_divider_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(extraClasses = "lg:divider-horizontal") {
            }
        }
        val expectedClasses = "divider lg:divider-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for responsive (lg:divider-horizontal)")
    }

    @Test
    fun divider_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider() {
            }
        }
        val expectedClasses = "divider"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_in_different_positions() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(start = true) {
            }
        }
        val expectedClasses = "divider divider-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider() {
            }
        }
        val expectedClasses = "divider"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(end = true) {
            }
        }
        val expectedClasses = "divider divider-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, start = true) {
            }
        }
        val expectedClasses = "divider divider-horizontal divider-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions (horizontal)")
    }

    @Test
    fun divider_in_different_positions_horizontal_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal) {
            }
        }
        val expectedClasses = "divider divider-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions (horizontal)")
    }

    @Test
    fun divider_in_different_positions_horizontal_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, end = true) {
            }
        }
        val expectedClasses = "divider divider-end divider-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions (horizontal)")
    }
}
