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
    fun tabs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                content = { },
            )
        }
        assertRendered(html, "tabs", "Tabs defaults", closes = "</button></div>")
    }

    @Test
    fun tabs_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                id = htmlId("x-cov-id"),
                border = true,
                box = true,
                lift = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tabs tabs-border tabs-box tabs-lift zz-extra", "Tabs all flags", closes = "</button></div>")
        assertCommonFlags(html, "Tabs")
    }

    @Test
    fun tabs_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-xs", "Tabs size Xs")
    }

    @Test
    fun tabs_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-sm", "Tabs size Sm")
    }

    @Test
    fun tabs_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Md,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-md", "Tabs size Md")
    }

    @Test
    fun tabs_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-lg", "Tabs size Lg")
    }

    @Test
    fun tabs_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-xl", "Tabs size Xl")
    }

    @Test
    fun tabs_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                placement = TabsPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-top", "Tabs placement Top")
    }

    @Test
    fun tabs_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                placement = TabsPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-bottom", "Tabs placement Bottom")
    }

    @Test
    fun tab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                content = { },
            )
        }
        assertRendered(html, "tab", "Tab defaults", closes = "</a></div>")
    }

    @Test
    fun tab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                id = htmlId("x-cov-id"),
                active = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tab tab-active tab-disabled zz-extra", "Tab all flags", closes = "</a></div>")
        assertCommonFlags(html, "Tab")
    }

    @Test
    fun tabContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent(
                content = { },
            )
        }
        assertRendered(html, "tab-content", "TabContent defaults", closes = "</div></div>")
    }

    @Test
    fun tabContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tab-content zz-extra", "TabContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "TabContent")
    }
}
