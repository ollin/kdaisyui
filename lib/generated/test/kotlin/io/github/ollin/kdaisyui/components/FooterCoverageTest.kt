package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FooterCoverageTest {

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
    fun footer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                content = { },
            )
        }
        assertRendered(html, Expected("Footer defaults", "footer", closes = "</footer></div>"))
    }

    @Test
    fun footer_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                id = htmlId("x-cov-id"),
                center = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Footer all flags", "footer footer-center zz-extra", closes = "</footer></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun footer_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                direction = FooterDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Footer direction Horizontal", "footer footer-horizontal"))
    }

    @Test
    fun footer_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                direction = FooterDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Footer direction Vertical", "footer footer-vertical"))
    }

    @Test
    fun footerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterClasses()
        }
        assertRendered(html, Expected("FooterClasses defaults", "footer"))
    }

    @Test
    fun footerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterClasses(
                center = true,
            )
        }
        val expected = Expected("FooterClasses all flags", "footer footer-center", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun footerClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterClasses(
                direction = FooterDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("FooterClasses direction Horizontal", "footer footer-horizontal"))
    }

    @Test
    fun footerClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterClasses(
                direction = FooterDirection.Vertical,
            )
        }
        assertRendered(html, Expected("FooterClasses direction Vertical", "footer footer-vertical"))
    }

    @Test
    fun footerTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle()
        }
        assertRendered(html, Expected("FooterTitle defaults", "footer-title", closes = "</h6></div>"))
    }

    @Test
    fun footerTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("FooterTitle all flags", "footer-title zz-extra", closes = "</h6></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun footerTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("FooterTitle text", "footer-title"))
        assertTrue(html.contains("txtmark"), "FooterTitle text content")
    }

    @Test
    fun footerTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitleClasses()
        }
        assertRendered(html, Expected("FooterTitleClasses defaults", "footer-title"))
    }

    @Test
    fun footerTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitleClasses()
        }
        val expected = Expected("FooterTitleClasses all flags", "footer-title", content = false)
        assertRendered(html, expected)
    }
}
