package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextRotateCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun textRotate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate(
                content = { },
            )
        }
        assertRendered(html, "text-rotate", "TextRotate defaults", closes = "</span></div>")
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
        assertRendered(html, "text-rotate zz-extra", "TextRotate all flags", closes = "</span></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "TextRotate id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TextRotate attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TextRotate content")
    }
}
