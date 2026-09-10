package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class JoinCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun join_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                content = { },
            )
        }
        assertRendered(html, "join", "Join defaults", closes = "</div></div>")
    }

    @Test
    fun join_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                id = htmlId("x-cov-id"),
                horizontal = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "join join-horizontal join-vertical zz-extra", "Join all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Join id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Join attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Join content")
    }
}
