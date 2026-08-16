package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FooterCoverageTest {

    @Test
    fun footer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooter(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("footer", actualClasses, "Footer defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("footer footer-center footer-horizontal footer-vertical zz-extra", actualClasses, "Footer all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Footer id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Footer attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Footer content")
    }

    @Test
    fun footerTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFooterTitle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("footer-title", actualClasses, "FooterTitle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("footer-title zz-extra", actualClasses, "FooterTitle all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("footer-title", actualClasses, "FooterTitle text")
        assertTrue(html.contains("txtmark"), "FooterTitle text content")
    }
}
