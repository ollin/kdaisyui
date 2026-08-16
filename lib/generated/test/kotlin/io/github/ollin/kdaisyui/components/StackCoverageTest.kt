package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StackCoverageTest {

    @Test
    fun stack_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stack", actualClasses, "Stack defaults")
    }

    @Test
    fun stack_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                id = htmlId("x-cov-id"),
                bottom = true,
                end = true,
                start = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stack stack-bottom stack-end stack-start stack-top zz-extra", actualClasses, "Stack all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Stack id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Stack attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Stack content")
    }
}
