package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LabelCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun label_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel()
        }
        assertRendered(html, "label", "Label defaults", closes = "</span></div>")
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
        assertRendered(html, "label zz-extra", "Label all flags", closes = "</span></div>")
        assertCommonFlags(html, "Label")
    }

    @Test
    fun label_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel(
                text = "txtmark",
            )
        }
        assertRendered(html, "label", "Label text")
        assertTrue(html.contains("txtmark"), "Label text content")
    }

    @Test
    fun labelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabelClasses()
        }
        assertRendered(html, "label", "LabelClasses defaults")
    }

    @Test
    fun labelClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabelClasses()
        }
        assertRendered(html, "label", "LabelClasses all flags")
    }

    @Test
    fun floatingLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabel(
                content = { },
            )
        }
        assertRendered(html, "floating-label", "FloatingLabel defaults", closes = "</label></div>")
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
        assertRendered(html, "floating-label zz-extra", "FloatingLabel all flags", closes = "</label></div>")
        assertCommonFlags(html, "FloatingLabel")
    }

    @Test
    fun floatingLabelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabelClasses()
        }
        assertRendered(html, "floating-label", "FloatingLabelClasses defaults")
    }

    @Test
    fun floatingLabelClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabelClasses()
        }
        assertRendered(html, "floating-label", "FloatingLabelClasses all flags")
    }
}
