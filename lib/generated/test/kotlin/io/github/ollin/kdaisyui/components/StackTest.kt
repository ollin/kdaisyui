package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StackTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun test_3_divs_in_a_stack() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        assertDocumented(html, "div", "stack", "3 divs in a stack")
    }

    @Test
    fun stacked_cards_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "stacked cards")
    }

    @Test
    fun stacked_cards_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "stacked cards")
    }

    @Test
    fun stacked_cards_top_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.Top) {
            }
        }
        assertDocumented(html, "div", "stack stack-top", "stacked cards (top direction)")
    }

    @Test
    fun stacked_cards_start_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "stack stack-start", "stacked cards (start direction)")
    }

    @Test
    fun stacked_cards_end_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.End) {
            }
        }
        assertDocumented(html, "div", "stack stack-end", "stacked cards (end direction)")
    }

    @Test
    fun stacked_cards_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        assertDocumented(html, "h2", "card-title", "stacked cards")
    }
}
