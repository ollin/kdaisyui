package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupBrowserCoverageTest {

    @Test
    fun mockupBrowser_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-browser", actualClasses, "MockupBrowser defaults")
    }

    @Test
    fun mockupBrowser_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-browser zz-extra", actualClasses, "MockupBrowser all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupBrowser id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupBrowser attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupBrowser content")
    }

    @Test
    fun mockupBrowserToolbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-browser-toolbar", actualClasses, "MockupBrowserToolbar defaults")
    }

    @Test
    fun mockupBrowserToolbar_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-browser-toolbar zz-extra", actualClasses, "MockupBrowserToolbar all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupBrowserToolbar id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupBrowserToolbar attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupBrowserToolbar content")
    }
}
