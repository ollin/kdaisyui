package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DividerTest {

    @Test
    fun divider_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider")
    }

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
            daisyDivider(color = DividerColor.Neutral) {
            }
        }
        val expectedClasses = "divider divider-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Primary) {
            }
        }
        val expectedClasses = "divider divider-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Secondary) {
            }
        }
        val expectedClasses = "divider divider-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Accent) {
            }
        }
        val expectedClasses = "divider divider-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Success) {
            }
        }
        val expectedClasses = "divider divider-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Warning) {
            }
        }
        val expectedClasses = "divider divider-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Info) {
            }
        }
        val expectedClasses = "divider divider-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Error) {
            }
        }
        val expectedClasses = "divider divider-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider with colors")
    }

    @Test
    fun divider_in_different_positions() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(placement = DividerPlacement.Start) {
            }
        }
        val expectedClasses = "divider divider-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(placement = DividerPlacement.End) {
            }
        }
        val expectedClasses = "divider divider-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, placement = DividerPlacement.Start) {
            }
        }
        val expectedClasses = "divider divider-horizontal divider-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions (horizontal)")
    }

    @Test
    fun divider_in_different_positions_horizontal_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, placement = DividerPlacement.End) {
            }
        }
        val expectedClasses = "divider divider-end divider-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Divider in different positions (horizontal)")
    }
}
