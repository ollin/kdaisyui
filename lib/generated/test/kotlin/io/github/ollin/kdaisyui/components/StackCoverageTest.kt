package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StackCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun stack_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                content = { },
            )
        }
        assertRendered(html, "stack", "Stack defaults", closes = "</div></div>")
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
        assertRendered(html, "stack stack-bottom stack-end stack-start stack-top zz-extra", "Stack all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Stack id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Stack attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Stack content")
    }
}
