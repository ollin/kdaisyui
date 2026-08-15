package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LabelCoverageTest {

    @Test
    fun label_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("label", actualClasses, "Label defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("label zz-extra", actualClasses, "Label all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("label", actualClasses, "Label text")
        assertTrue(html.contains("txtmark"), "Label text content")
    }
}
