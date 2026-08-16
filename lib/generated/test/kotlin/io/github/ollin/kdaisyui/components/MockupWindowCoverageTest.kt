package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupWindowCoverageTest {

    @Test
    fun mockupWindow_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-window", actualClasses, "MockupWindow defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-window zz-extra", actualClasses, "MockupWindow all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupWindow id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupWindow attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupWindow content")
    }
}
