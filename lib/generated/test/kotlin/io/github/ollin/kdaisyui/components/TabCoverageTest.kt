package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TabCoverageTest {

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
    fun tabs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs defaults", "tabs", closes = "</div></div>"))
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
        val expected = Expected("Tabs all flags", "tabs zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tabs_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs size Xs", "tabs tabs-xs"))
    }

    @Test
    fun tabs_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs size Sm", "tabs tabs-sm"))
    }

    @Test
    fun tabs_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs size Md", "tabs tabs-md"))
    }

    @Test
    fun tabs_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs size Lg", "tabs tabs-lg"))
    }

    @Test
    fun tabs_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                size = TabsSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs size Xl", "tabs tabs-xl"))
    }

    @Test
    fun tabs_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                placement = TabsPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs placement Top", "tabs tabs-top"))
    }

    @Test
    fun tabs_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                placement = TabsPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs placement Bottom", "tabs tabs-bottom"))
    }

    @Test
    fun tabs_style_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Box,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs style Box", "tabs tabs-box"))
    }

    @Test
    fun tabs_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Border,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs style Border", "tabs tabs-border"))
    }

    @Test
    fun tabs_style_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(
                style = TabsStyle.Lift,
                content = { },
            )
        }
        assertRendered(html, Expected("Tabs style Lift", "tabs tabs-lift"))
    }

    @Test
    fun tabsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses()
        }
        assertRendered(html, Expected("TabsClasses defaults", "tabs"))
    }

    @Test
    fun tabsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses()
        }
        val expected = Expected("TabsClasses all flags", "tabs", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun tabsClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Xs,
            )
        }
        assertRendered(html, Expected("TabsClasses size Xs", "tabs tabs-xs"))
    }

    @Test
    fun tabsClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Sm,
            )
        }
        assertRendered(html, Expected("TabsClasses size Sm", "tabs tabs-sm"))
    }

    @Test
    fun tabsClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Md,
            )
        }
        assertRendered(html, Expected("TabsClasses size Md", "tabs tabs-md"))
    }

    @Test
    fun tabsClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Lg,
            )
        }
        assertRendered(html, Expected("TabsClasses size Lg", "tabs tabs-lg"))
    }

    @Test
    fun tabsClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                size = TabsSize.Xl,
            )
        }
        assertRendered(html, Expected("TabsClasses size Xl", "tabs tabs-xl"))
    }

    @Test
    fun tabsClasses_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                placement = TabsPlacement.Top,
            )
        }
        assertRendered(html, Expected("TabsClasses placement Top", "tabs tabs-top"))
    }

    @Test
    fun tabsClasses_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                placement = TabsPlacement.Bottom,
            )
        }
        assertRendered(html, Expected("TabsClasses placement Bottom", "tabs tabs-bottom"))
    }

    @Test
    fun tabsClasses_style_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Box,
            )
        }
        assertRendered(html, Expected("TabsClasses style Box", "tabs tabs-box"))
    }

    @Test
    fun tabsClasses_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Border,
            )
        }
        assertRendered(html, Expected("TabsClasses style Border", "tabs tabs-border"))
    }

    @Test
    fun tabsClasses_style_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses(
                style = TabsStyle.Lift,
            )
        }
        assertRendered(html, Expected("TabsClasses style Lift", "tabs tabs-lift"))
    }

    @Test
    fun tab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                content = { },
            )
        }
        assertRendered(html, Expected("Tab defaults", "tab", closes = "</a></div>"))
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
        val expected = Expected("Tab all flags", "tab tab-active tab-disabled zz-extra", closes = "</a></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tabClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabClasses()
        }
        assertRendered(html, Expected("TabClasses defaults", "tab"))
    }

    @Test
    fun tabClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabClasses(
                active = true,
                disabled = true,
            )
        }
        val expected = Expected("TabClasses all flags", "tab tab-active tab-disabled", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun tabRadio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio()
        }
        assertRendered(html, Expected("TabRadio defaults", "tab"))
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
        val expected = Expected("TabRadio all flags", "tab tab-active tab-disabled zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tabRadioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadioClasses()
        }
        assertRendered(html, Expected("TabRadioClasses defaults", "tab"))
    }

    @Test
    fun tabRadioClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadioClasses(
                active = true,
                disabled = true,
            )
        }
        val expected = Expected("TabRadioClasses all flags", "tab tab-active tab-disabled", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun tabContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent(
                content = { },
            )
        }
        assertRendered(html, Expected("TabContent defaults", "tab-content", closes = "</div></div>"))
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
        val expected = Expected("TabContent all flags", "tab-content zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun tabContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContentClasses()
        }
        assertRendered(html, Expected("TabContentClasses defaults", "tab-content"))
    }

    @Test
    fun tabContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContentClasses()
        }
        val expected = Expected("TabContentClasses all flags", "tab-content", content = false)
        assertRendered(html, expected)
    }
}
