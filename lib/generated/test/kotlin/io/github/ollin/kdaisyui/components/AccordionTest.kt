package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AccordionTest {

    @Test
    fun accordion_using_radio_inputs_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        val expectedClasses = "collapse"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accordion using radio inputs")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accordion using radio inputs")
    }

    @Test
    fun accordion_using_radio_inputs_daisyCollapseTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle() {
            }
        }
        val expectedClasses = "collapse-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accordion using radio inputs")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accordion using radio inputs")
    }

    @Test
    fun accordion_using_radio_inputs_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        val expectedClasses = "collapse-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accordion using radio inputs")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accordion using radio inputs")
    }

    @Test
    fun accordion_with_arrow_icon_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Arrow) {
            }
        }
        val expectedClasses = "collapse collapse-arrow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accordion with arrow icon")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accordion with arrow icon")
    }

    @Test
    fun accordion_with_plus_minus_icon_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Plus) {
            }
        }
        val expectedClasses = "collapse collapse-plus"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accordion with plus/minus icon")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accordion with plus/minus icon")
    }

    @Test
    fun using_accordion_and_join_together_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        val expectedClasses = "join join-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Using Accordion and Join together")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Using Accordion and Join together")
    }
}
