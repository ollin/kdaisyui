package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupWindowCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun mockupWindow_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupWindow defaults", "mockup-window", closes = "</div></div>"))
    }

    @Test
    fun mockupWindow_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MockupWindow all flags", "mockup-window zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupWindowClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindowClasses()
        }
        assertRendered(html, Expected("MockupWindowClasses defaults", "mockup-window"))
    }

    @Test
    fun mockupWindowClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindowClasses()
        }
        val expected = Expected("MockupWindowClasses all flags", "mockup-window", content = false)
        assertRendered(html, expected)
    }
}
