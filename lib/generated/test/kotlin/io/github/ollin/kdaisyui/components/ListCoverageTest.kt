package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ListCoverageTest {

    @Test
    fun list_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyList(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("list", actualClasses, "List defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("list list-col-grow list-col-wrap zz-extra", actualClasses, "List all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "List id")
        assertTrue(html.contains("data-attrs=\"yes\""), "List attrs")
        assertTrue(html.contains("data-content=\"yes\""), "List content")
    }
}
