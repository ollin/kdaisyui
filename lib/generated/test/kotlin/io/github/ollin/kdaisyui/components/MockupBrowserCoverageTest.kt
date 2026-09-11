package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupBrowserCoverageTest {

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
    fun mockupBrowser_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser(
                content = { },
            )
        }
        assertRendered(html, "mockup-browser", "MockupBrowser defaults", closes = "</div></div>")
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
        assertRendered(html, "mockup-browser zz-extra", "MockupBrowser all flags", closes = "</div></div>")
        assertCommonFlags(html, "MockupBrowser")
    }

    @Test
    fun mockupBrowserToolbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar(
                content = { },
            )
        }
        assertRendered(html, "mockup-browser-toolbar", "MockupBrowserToolbar defaults", closes = "</div></div>")
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
        assertRendered(html, "mockup-browser-toolbar zz-extra", "MockupBrowserToolbar all flags", closes = "</div></div>")
        assertCommonFlags(html, "MockupBrowserToolbar")
    }
}
