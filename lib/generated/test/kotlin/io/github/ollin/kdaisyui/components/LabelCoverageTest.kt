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
        assertTrue(html.contains("id=\"x-cov-id\""), "Label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Label attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Label content")
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
}
