package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextRotateCoverageTest {

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
    fun textRotate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate(
                content = { },
            )
        }
        assertRendered(html, Expected("TextRotate defaults", "text-rotate", closes = "</span></div>"))
    }

    @Test
    fun textRotate_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("TextRotate all flags", "text-rotate zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun textRotateClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotateClasses()
        }
        assertRendered(html, Expected("TextRotateClasses defaults", "text-rotate"))
    }

    @Test
    fun textRotateClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotateClasses()
        }
        val expected = Expected("TextRotateClasses all flags", "text-rotate", content = false)
        assertRendered(html, expected)
    }
}
