package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CollapseTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun collapse_with_focus() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse() {
            }
        }
        assertDocumented(html, "div", "collapse", "Collapse with focus")
    }

    @Test
    fun collapse_with_focus_daisyCollapseTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle() {
            }
        }
        assertDocumented(html, "div", "collapse-title", "Collapse with focus")
    }

    @Test
    fun collapse_with_focus_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        assertDocumented(html, "div", "collapse-content", "Collapse with focus")
    }

    @Test
    fun with_arrow_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Arrow) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-arrow", "With arrow icon")
    }

    @Test
    fun with_arrow_plus_minus_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Plus) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-plus", "With arrow plus/minus icon")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(state = CollapseState.Open) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-open", "Force open")
    }

    @Test
    fun force_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(state = CollapseState.Close) {
            }
        }
        assertDocumented(html, "div", "collapse collapse-close", "Force close")
    }
}
