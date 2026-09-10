package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FooterCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun footer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                content = { },
            )
        }
        assertRendered(html, "footer", "Footer defaults", closes = "</footer></div>")
    }

    @Test
    fun footer_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                id = htmlId("x-cov-id"),
                center = true,
                horizontal = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "footer footer-center footer-horizontal footer-vertical zz-extra", "Footer all flags", closes = "</footer></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Footer id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Footer attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Footer content")
    }

    @Test
    fun footerTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle()
        }
        assertRendered(html, "footer-title", "FooterTitle defaults", closes = "</h2></div>")
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
        assertRendered(html, "footer-title zz-extra", "FooterTitle all flags", closes = "</h2></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "FooterTitle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FooterTitle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FooterTitle content")
    }

    @Test
    fun footerTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "footer-title", "FooterTitle text")
        assertTrue(html.contains("txtmark"), "FooterTitle text content")
    }
}
