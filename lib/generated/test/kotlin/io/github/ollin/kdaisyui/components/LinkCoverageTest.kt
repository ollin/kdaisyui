package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LinkCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun link_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink()
        }
        assertRendered(html, Expected("Link defaults", "link", closes = "</a></div>"))
    }

    @Test
    fun link_all_flags() {
        val html = allFlags(Expected("Link all flags", "link link-hover zz-extra", closes = "</a></div>")) { daisyLink(id = ID, hover = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun link_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Neutral,
            )
        }
        assertRendered(html, Expected("Link color Neutral", "link link-neutral"))
    }

    @Test
    fun link_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Primary,
            )
        }
        assertRendered(html, Expected("Link color Primary", "link link-primary"))
    }

    @Test
    fun link_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Secondary,
            )
        }
        assertRendered(html, Expected("Link color Secondary", "link link-secondary"))
    }

    @Test
    fun link_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Accent,
            )
        }
        assertRendered(html, Expected("Link color Accent", "link link-accent"))
    }

    @Test
    fun link_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Success,
            )
        }
        assertRendered(html, Expected("Link color Success", "link link-success"))
    }

    @Test
    fun link_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Info,
            )
        }
        assertRendered(html, Expected("Link color Info", "link link-info"))
    }

    @Test
    fun link_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Warning,
            )
        }
        assertRendered(html, Expected("Link color Warning", "link link-warning"))
    }

    @Test
    fun link_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                color = LinkColor.Error,
            )
        }
        assertRendered(html, Expected("Link color Error", "link link-error"))
    }

    @Test
    fun link_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Link text", "link"))
        assertTrue(html.contains("txtmark"), "Link text content")
    }

    @Test
    fun linkClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses()
        }
        assertRendered(html, Expected("LinkClasses defaults", "link"))
    }

    @Test
    fun linkClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                hover = true,
            )
        }
        val expected = Expected("LinkClasses all flags", "link link-hover", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun linkClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Neutral,
            )
        }
        assertRendered(html, Expected("LinkClasses color Neutral", "link link-neutral"))
    }

    @Test
    fun linkClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Primary,
            )
        }
        assertRendered(html, Expected("LinkClasses color Primary", "link link-primary"))
    }

    @Test
    fun linkClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Secondary,
            )
        }
        assertRendered(html, Expected("LinkClasses color Secondary", "link link-secondary"))
    }

    @Test
    fun linkClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Accent,
            )
        }
        assertRendered(html, Expected("LinkClasses color Accent", "link link-accent"))
    }

    @Test
    fun linkClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Success,
            )
        }
        assertRendered(html, Expected("LinkClasses color Success", "link link-success"))
    }

    @Test
    fun linkClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Info,
            )
        }
        assertRendered(html, Expected("LinkClasses color Info", "link link-info"))
    }

    @Test
    fun linkClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Warning,
            )
        }
        assertRendered(html, Expected("LinkClasses color Warning", "link link-warning"))
    }

    @Test
    fun linkClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyLinkClasses(
                color = LinkColor.Error,
            )
        }
        assertRendered(html, Expected("LinkClasses color Error", "link link-error"))
    }
}
