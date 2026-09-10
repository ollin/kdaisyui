package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LinkCoverageTest {

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
    fun link_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink()
        }
        assertRendered(html, "link", "Link defaults", closes = "</a></div>")
    }

    @Test
    fun link_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                id = htmlId("x-cov-id"),
                hover = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "link link-hover zz-extra", "Link all flags", closes = "</a></div>")
        assertCommonFlags(html, "Link")
    }

    @Test
    fun link_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Neutral,
            )
        }
        assertRendered(html, "link link-neutral", "Link variant Neutral")
    }

    @Test
    fun link_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Primary,
            )
        }
        assertRendered(html, "link link-primary", "Link variant Primary")
    }

    @Test
    fun link_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Secondary,
            )
        }
        assertRendered(html, "link link-secondary", "Link variant Secondary")
    }

    @Test
    fun link_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Accent,
            )
        }
        assertRendered(html, "link link-accent", "Link variant Accent")
    }

    @Test
    fun link_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Success,
            )
        }
        assertRendered(html, "link link-success", "Link variant Success")
    }

    @Test
    fun link_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Info,
            )
        }
        assertRendered(html, "link link-info", "Link variant Info")
    }

    @Test
    fun link_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Warning,
            )
        }
        assertRendered(html, "link link-warning", "Link variant Warning")
    }

    @Test
    fun link_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Error,
            )
        }
        assertRendered(html, "link link-error", "Link variant Error")
    }

    @Test
    fun link_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                text = "txtmark",
            )
        }
        assertRendered(html, "link", "Link text")
        assertTrue(html.contains("txtmark"), "Link text content")
    }
}
