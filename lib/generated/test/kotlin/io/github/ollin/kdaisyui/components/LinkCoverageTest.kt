package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LinkCoverageTest {

    @Test
    fun link_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link", actualClasses, "Link defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-hover zz-extra", actualClasses, "Link all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Link id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Link attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Link content")
    }

    @Test
    fun link_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-neutral", actualClasses, "Link variant Neutral")
    }

    @Test
    fun link_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-primary", actualClasses, "Link variant Primary")
    }

    @Test
    fun link_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-secondary", actualClasses, "Link variant Secondary")
    }

    @Test
    fun link_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-accent", actualClasses, "Link variant Accent")
    }

    @Test
    fun link_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-success", actualClasses, "Link variant Success")
    }

    @Test
    fun link_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-info", actualClasses, "Link variant Info")
    }

    @Test
    fun link_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-warning", actualClasses, "Link variant Warning")
    }

    @Test
    fun link_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                variant = LinkVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link link-error", actualClasses, "Link variant Error")
    }

    @Test
    fun link_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("link", actualClasses, "Link text")
        assertTrue(html.contains("txtmark"), "Link text content")
    }
}
