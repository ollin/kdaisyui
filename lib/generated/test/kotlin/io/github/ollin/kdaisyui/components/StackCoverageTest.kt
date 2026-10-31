package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StackCoverageTest {

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

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun stack_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                content = { },
            )
        }
        assertRendered(html, Expected("Stack defaults", "stack", closes = "</div></div>"))
    }

    @Test
    fun stack_all_flags() {
        val html = allFlags(Expected("Stack all flags", "stack zz-extra", closes = "</div></div>")) { daisyStack(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun stack_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Stack placement Top", "stack stack-top"))
    }

    @Test
    fun stack_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Stack placement Bottom", "stack stack-bottom"))
    }

    @Test
    fun stack_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Stack placement Start", "stack stack-start"))
    }

    @Test
    fun stack_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Stack placement End", "stack stack-end"))
    }

    @Test
    fun stackClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses()
        }
        assertRendered(html, Expected("StackClasses defaults", "stack"))
    }

    @Test
    fun stackClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses()
        }
        val expected = Expected("StackClasses all flags", "stack", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun stackClasses_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Top,
            )
        }
        assertRendered(html, Expected("StackClasses placement Top", "stack stack-top"))
    }

    @Test
    fun stackClasses_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Bottom,
            )
        }
        assertRendered(html, Expected("StackClasses placement Bottom", "stack stack-bottom"))
    }

    @Test
    fun stackClasses_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Start,
            )
        }
        assertRendered(html, Expected("StackClasses placement Start", "stack stack-start"))
    }

    @Test
    fun stackClasses_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.End,
            )
        }
        assertRendered(html, Expected("StackClasses placement End", "stack stack-end"))
    }
}
