package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class JoinCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun join_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                content = { },
            )
        }
        assertRendered(html, Expected("Join defaults", "join", closes = "</div></div>"))
    }

    @Test
    fun join_all_flags() {
        val html = allFlags(Expected("Join all flags", "join zz-extra", closes = "</div></div>")) { daisyJoin(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun join_direction() = assertEachEntry("Join direction", mapOf(JoinDirection.Vertical to "join join-vertical", JoinDirection.Horizontal to "join join-horizontal")) { daisyJoin(direction = it, content = { }) }

    @Test
    fun joinClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoinClasses()
        }
        assertRendered(html, Expected("JoinClasses defaults", "join"))
    }

    @Test
    fun joinClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoinClasses()
        }
        val expected = Expected("JoinClasses all flags", "join", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun joinClasses_direction() = assertEachEntry("JoinClasses direction", mapOf(JoinDirection.Vertical to "join join-vertical", JoinDirection.Horizontal to "join join-horizontal")) { daisyJoinClasses(direction = it) }

    @Test
    fun scope_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyButton()
            }
        }
        assertTrue(html.contains("join-item"), "daisyButton in a daisyJoin")
    }

    @Test
    fun scope_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyCard(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisyCard in a daisyJoin")
    }

    @Test
    fun scope_daisyCollapse() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyCollapse(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisyCollapse in a daisyJoin")
    }

    @Test
    fun scope_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyInput()
            }
        }
        assertTrue(html.contains("join-item"), "daisyInput in a daisyJoin")
    }

    @Test
    fun scope_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisySelect(content = { })
            }
        }
        assertTrue(html.contains("join-item"), "daisySelect in a daisyJoin")
    }

    @Test
    fun scope_daisyThemeController() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyThemeController()
            }
        }
        assertTrue(html.contains("join-item"), "daisyThemeController in a daisyJoin")
    }

    @Test
    fun scope_daisyValidator() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyValidator()
            }
        }
        assertTrue(html.contains("join-item"), "daisyValidator in a daisyJoin")
    }
}
