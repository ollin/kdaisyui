package io.github.ollin.kdaisyui.components

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
    fun collapse_with_focus_daisyCollapseTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle() {
            }
        }
        val expectedClasses = "collapse-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse with focus")
    }

    @Test
    fun collapse_with_focus_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        val expectedClasses = "collapse-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapse with focus")
    }

    @Test
    fun with_arrow_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Arrow) {
            }
        }
        val expectedClasses = "collapse collapse-arrow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With arrow icon")
    }

    @Test
    fun with_arrow_plus_minus_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(icon = CollapseIcon.Plus) {
            }
        }
        val expectedClasses = "collapse collapse-plus"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With arrow plus/minus icon")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(state = CollapseState.Open) {
            }
        }
        val expectedClasses = "collapse collapse-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force open")
    }

    @Test
    fun force_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(state = CollapseState.Close) {
            }
        }
        val expectedClasses = "collapse collapse-close"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force close")
    }
}
