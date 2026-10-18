package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DividerTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun divider_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "Divider")
    }

    @Test
    fun divider() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider() {
            }
        }
        assertDocumented(html, "div", "divider", "Divider")
    }

    @Test
    fun divider_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal) {
            }
        }
        assertDocumented(html, "div", "divider divider-horizontal", "Divider horizontal")
    }

    @Test
    fun responsive_lg_divider_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(extraClasses = "lg:divider-horizontal") {
            }
        }
        assertDocumented(html, "div", "divider lg:divider-horizontal", "responsive (lg:divider-horizontal)")
    }

    @Test
    fun divider_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Neutral) {
            }
        }
        assertDocumented(html, "div", "divider divider-neutral", "Divider with colors")
    }

    @Test
    fun divider_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Primary) {
            }
        }
        assertDocumented(html, "div", "divider divider-primary", "Divider with colors")
    }

    @Test
    fun divider_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Secondary) {
            }
        }
        assertDocumented(html, "div", "divider divider-secondary", "Divider with colors")
    }

    @Test
    fun divider_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Accent) {
            }
        }
        assertDocumented(html, "div", "divider divider-accent", "Divider with colors")
    }

    @Test
    fun divider_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Success) {
            }
        }
        assertDocumented(html, "div", "divider divider-success", "Divider with colors")
    }

    @Test
    fun divider_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Warning) {
            }
        }
        assertDocumented(html, "div", "divider divider-warning", "Divider with colors")
    }

    @Test
    fun divider_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Info) {
            }
        }
        assertDocumented(html, "div", "divider divider-info", "Divider with colors")
    }

    @Test
    fun divider_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(color = DividerColor.Error) {
            }
        }
        assertDocumented(html, "div", "divider divider-error", "Divider with colors")
    }

    @Test
    fun divider_in_different_positions() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(placement = DividerPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "divider divider-start", "Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(placement = DividerPlacement.End) {
            }
        }
        assertDocumented(html, "div", "divider divider-end", "Divider in different positions")
    }

    @Test
    fun divider_in_different_positions_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, placement = DividerPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "divider divider-horizontal divider-start", "Divider in different positions (horizontal)")
    }

    @Test
    fun divider_in_different_positions_horizontal_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(direction = DividerDirection.Horizontal, placement = DividerPlacement.End) {
            }
        }
        assertDocumented(html, "div", "divider divider-end divider-horizontal", "Divider in different positions (horizontal)")
    }
}
