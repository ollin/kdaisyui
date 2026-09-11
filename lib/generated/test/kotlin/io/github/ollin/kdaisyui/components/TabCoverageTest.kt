package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TabCoverageTest {

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
    fun tab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                content = { },
            )
        }
        assertRendered(html, "tabs", "Tab defaults", closes = "</button></div>")
    }

    @Test
    fun tab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                id = htmlId("x-cov-id"),
                border = true,
                bottom = true,
                box = true,
                lift = true,
                tabActive = true,
                tabDisabled = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tabs tabs-border tabs-bottom tabs-box tabs-lift tabs-tab-active tabs-tab-disabled tabs-top zz-extra", "Tab all flags", closes = "</button></div>")
        assertCommonFlags(html, "Tab")
    }

    @Test
    fun tab_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-xs", "Tab size Xs")
    }

    @Test
    fun tab_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-sm", "Tab size Sm")
    }

    @Test
    fun tab_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Md,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-md", "Tab size Md")
    }

    @Test
    fun tab_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-lg", "Tab size Lg")
    }

    @Test
    fun tab_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-xl", "Tab size Xl")
    }

    @Test
    fun tabTab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTab(
                content = { },
            )
        }
        assertRendered(html, "tab", "TabTab defaults", closes = "</div></div>")
    }

    @Test
    fun tabTab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTab(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tab zz-extra", "TabTab all flags", closes = "</div></div>")
        assertCommonFlags(html, "TabTab")
    }

    @Test
    fun tabTabContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTabContent(
                content = { },
            )
        }
        assertRendered(html, "tab-content", "TabTabContent defaults", closes = "</div></div>")
    }

    @Test
    fun tabTabContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTabContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tab-content zz-extra", "TabTabContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "TabTabContent")
    }
}
