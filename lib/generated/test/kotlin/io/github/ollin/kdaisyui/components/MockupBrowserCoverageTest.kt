package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupBrowserCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun mockupBrowser_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupBrowser defaults", "mockup-browser", closes = "</div></div>"))
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
        val expected = Expected("MockupBrowser all flags", "mockup-browser zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupBrowserClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserClasses()
        }
        assertRendered(html, Expected("MockupBrowserClasses defaults", "mockup-browser"))
    }

    @Test
    fun mockupBrowserClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserClasses()
        }
        val expected = Expected("MockupBrowserClasses all flags", "mockup-browser", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun mockupBrowserToolbar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar(
                content = { },
            )
        }
        assertRendered(html, Expected("MockupBrowserToolbar defaults", "mockup-browser-toolbar", closes = "</div></div>"))
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
        val expected = Expected("MockupBrowserToolbar all flags", "mockup-browser-toolbar zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mockupBrowserToolbarClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbarClasses()
        }
        assertRendered(html, Expected("MockupBrowserToolbarClasses defaults", "mockup-browser-toolbar"))
    }

    @Test
    fun mockupBrowserToolbarClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbarClasses()
        }
        val expected = Expected("MockupBrowserToolbarClasses all flags", "mockup-browser-toolbar", content = false)
        assertRendered(html, expected)
    }
}
