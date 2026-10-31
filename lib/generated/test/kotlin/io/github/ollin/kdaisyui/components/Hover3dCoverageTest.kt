package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class Hover3dCoverageTest {

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
    fun hover3d_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d(
                content = { },
            )
        }
        assertRendered(html, Expected("Hover3d defaults", "hover-3d", closes = "</div></div>"))
    }

    @Test
    fun hover3d_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Hover3d all flags", "hover-3d zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun hover3dClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3dClasses()
        }
        assertRendered(html, Expected("Hover3dClasses defaults", "hover-3d"))
    }

    @Test
    fun hover3dClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3dClasses()
        }
        val expected = Expected("Hover3dClasses all flags", "hover-3d", content = false)
        assertRendered(html, expected)
    }
}
