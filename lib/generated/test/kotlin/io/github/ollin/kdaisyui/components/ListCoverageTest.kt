package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ListCoverageTest {

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
    fun list_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(
                content = { },
            )
        }
        assertRendered(html, "list", "List defaults", closes = "</ul></div>")
    }

    @Test
    fun list_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(
                id = htmlId("x-cov-id"),
                colGrow = true,
                colWrap = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "list list-col-grow list-col-wrap zz-extra", "List all flags", closes = "</ul></div>")
        assertCommonFlags(html, "List")
    }
}
