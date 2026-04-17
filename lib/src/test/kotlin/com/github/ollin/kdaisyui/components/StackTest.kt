package com.github.ollin.kdaisyui.components

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
    fun stacked_images() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        val expectedClasses = "stack"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked images")
    }

    @Test
    fun stacked_cards() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        val expectedClasses = "stack"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards")
    }

    @Test
    fun stacked_cards_top_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(top = true) {
            }
        }
        val expectedClasses = "stack stack-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (top direction)")
    }

    @Test
    fun stacked_cards_start_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(start = true) {
            }
        }
        val expectedClasses = "stack stack-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (start direction)")
    }

    @Test
    fun stacked_cards_end_direction() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(end = true) {
            }
        }
        val expectedClasses = "stack stack-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards (end direction)")
    }

    @Test
    fun stacked_cards_with_shadow() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        val expectedClasses = "stack"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards with shadow")
    }

    @Test
    fun stacked_cards_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack() {
            }
        }
        val expectedClasses = "stack"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for stacked cards")
    }
}
