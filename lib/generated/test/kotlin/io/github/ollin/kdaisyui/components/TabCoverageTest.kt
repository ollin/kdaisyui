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
        assertRendered(html, "tabs", "Tabs defaults", closes = "</div></div>")
    }

    @Test
    fun tabs_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "tabs zz-extra", "Tabs all flags", closes = "</div></div>")
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
    fun tabs_style_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Box,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-box", "Tabs style Box")
    }

    @Test
    fun tabs_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Border,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-border", "Tabs style Border")
    }

    @Test
    fun tabs_style_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Lift,
                content = { },
            )
        }
        assertRendered(html, "tabs tabs-lift", "Tabs style Lift")
    }

    @Test
    fun tabsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses()
        }
        assertRendered(html, "tabs", "TabsClasses defaults")
    }

    @Test
    fun tabsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses()
        }
        assertRendered(html, "tabs", "TabsClasses all flags")
    }

    @Test
    fun tabsClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Xs,
            )
        }
        assertRendered(html, "tabs tabs-xs", "TabsClasses size Xs")
    }

    @Test
    fun tabsClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Sm,
            )
        }
        assertRendered(html, "tabs tabs-sm", "TabsClasses size Sm")
    }

    @Test
    fun tabsClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Md,
            )
        }
        assertRendered(html, "tabs tabs-md", "TabsClasses size Md")
    }

    @Test
    fun tabsClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Lg,
            )
        }
        assertRendered(html, "tabs tabs-lg", "TabsClasses size Lg")
    }

    @Test
    fun tabsClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Xl,
            )
        }
        assertRendered(html, "tabs tabs-xl", "TabsClasses size Xl")
    }

    @Test
    fun tabsClasses_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                placement = TabsPlacement.Top,
            )
        }
        assertRendered(html, "tabs tabs-top", "TabsClasses placement Top")
    }

    @Test
    fun tabsClasses_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                placement = TabsPlacement.Bottom,
            )
        }
        assertRendered(html, "tabs tabs-bottom", "TabsClasses placement Bottom")
    }

    @Test
    fun tabsClasses_style_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Box,
            )
        }
        assertRendered(html, "tabs tabs-box", "TabsClasses style Box")
    }

    @Test
    fun tabsClasses_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Border,
            )
        }
        assertRendered(html, "tabs tabs-border", "TabsClasses style Border")
    }

    @Test
    fun tabsClasses_style_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Lift,
            )
        }
        assertRendered(html, "tabs tabs-lift", "TabsClasses style Lift")
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
    fun tabClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabClasses()
        }
        assertRendered(html, "tab", "TabClasses defaults")
    }

    @Test
    fun tabClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabClasses(
                active = true,
                disabled = true,
            )
        }
        assertRendered(html, "tab tab-active tab-disabled", "TabClasses all flags")
    }

    @Test
    fun tabRadio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio()
        }
        assertRendered(html, "tab", "TabRadio defaults")
    }

    @Test
    fun tabRadio_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio(
                id = htmlId("x-cov-id"),
                active = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "tab tab-active tab-disabled zz-extra", "TabRadio all flags")
        assertCommonFlags(html, "TabRadio", content = false)
    }

    @Test
    fun tabRadioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadioClasses()
        }
        assertRendered(html, "tab", "TabRadioClasses defaults")
    }

    @Test
    fun tabRadioClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadioClasses(
                active = true,
                disabled = true,
            )
        }
        assertRendered(html, "tab tab-active tab-disabled", "TabRadioClasses all flags")
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

    @Test
    fun tabContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContentClasses()
        }
        assertRendered(html, "tab-content", "TabContentClasses defaults")
    }

    @Test
    fun tabContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContentClasses()
        }
        assertRendered(html, "tab-content", "TabContentClasses all flags")
    }
}
