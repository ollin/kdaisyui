package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AccordionTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun accordion_using_radio_inputs_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        assertDocumented(html, "div", "collapse", "Accordion using radio inputs")
    }

    @Test
    fun accordion_using_radio_inputs_daisyCollapseTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle() {
            }
        }
        assertDocumented(html, "div", "collapse-title", "Accordion using radio inputs")
    }

    @Test
    fun accordion_using_radio_inputs_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        assertDocumented(html, "div", "collapse-content", "Accordion using radio inputs")
    }

    @Test
    fun accordion_with_arrow_icon_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Arrow) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-arrow", "Accordion with arrow icon")
    }

    @Test
    fun accordion_with_plus_minus_icon_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Plus) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-plus", "Accordion with plus/minus icon")
    }

    @Test
    fun using_accordion_and_join_together_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        assertDocumented(html, "div", "join join-vertical", "Using Accordion and Join together")
    }
}
