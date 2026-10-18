package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StackTest {

    @Test
    fun test_3_divs_in_a_stack() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        val expectedClasses = "stack"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for 3 divs in a stack")
    }

    @Test
    fun stacked_cards_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards")
    }

    @Test
    fun stacked_cards_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards")
    }

    @Test
    fun stacked_cards_top_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.Top) {
            }
        }
        val expectedClasses = "stack stack-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (top direction)")
    }

    @Test
    fun stacked_cards_start_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.Start) {
            }
        }
        val expectedClasses = "stack stack-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (start direction)")
    }

    @Test
    fun stacked_cards_end_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(placement = StackPlacement.End) {
            }
        }
        val expectedClasses = "stack stack-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (end direction)")
    }

    @Test
    fun stacked_cards_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        val expectedClasses = "card-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards")
    }
}
