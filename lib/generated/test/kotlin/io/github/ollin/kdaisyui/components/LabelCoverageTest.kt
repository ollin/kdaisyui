package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LabelCoverageTest {

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
    fun label_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel()
        }
        assertRendered(html, Expected("Label defaults", "label", closes = "</span></div>"))
    }

    @Test
    fun label_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Label all flags", "label zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun label_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Label text", "label"))
        assertTrue(html.contains("txtmark"), "Label text content")
    }

    @Test
    fun labelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabelClasses()
        }
        assertRendered(html, Expected("LabelClasses defaults", "label"))
    }

    @Test
    fun labelClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabelClasses()
        }
        val expected = Expected("LabelClasses all flags", "label", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun floatingLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabel(
                content = { },
            )
        }
        assertRendered(html, Expected("FloatingLabel defaults", "floating-label", closes = "</label></div>"))
    }

    @Test
    fun floatingLabel_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabel(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("FloatingLabel all flags", "floating-label zz-extra", closes = "</label></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun floatingLabelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabelClasses()
        }
        assertRendered(html, Expected("FloatingLabelClasses defaults", "floating-label"))
    }

    @Test
    fun floatingLabelClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabelClasses()
        }
        val expected = Expected("FloatingLabelClasses all flags", "floating-label", content = false)
        assertRendered(html, expected)
    }
}
