package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupWindowCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun mockupWindow_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow(
                content = { },
            )
        }
        assertRendered(html, "mockup-window", "MockupWindow defaults", closes = "</div></div>")
    }

    @Test
    fun mockupWindow_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "mockup-window zz-extra", "MockupWindow all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupWindow id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupWindow attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupWindow content")
    }
}
