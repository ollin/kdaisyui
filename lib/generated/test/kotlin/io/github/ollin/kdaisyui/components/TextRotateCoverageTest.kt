package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextRotateCoverageTest {

    @Test
    fun textRotate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("text-rotate", actualClasses, "TextRotate defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("text-rotate zz-extra", actualClasses, "TextRotate all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TextRotate id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TextRotate attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TextRotate content")
    }
}
