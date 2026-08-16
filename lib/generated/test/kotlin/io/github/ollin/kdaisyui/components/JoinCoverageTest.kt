package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class JoinCoverageTest {

    @Test
    fun join_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("join", actualClasses, "Join defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("join join-horizontal join-vertical zz-extra", actualClasses, "Join all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Join id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Join attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Join content")
    }
}
