package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TabCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun tabs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs()
        }
        assertRendered(html, Expected("Tabs defaults", "tabs", closes = "</div></div>"))
    }

    @Test
    fun tabs_all_flags() {
        val html = allFlags(Expected("Tabs all flags", "tabs zz-extra", closes = "</div></div>")) { daisyTabs(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun tabs_size() = assertEachEntry("Tabs size", mapOf(TabsSize.Xs to "tabs tabs-xs", TabsSize.Sm to "tabs tabs-sm", TabsSize.Md to "tabs tabs-md", TabsSize.Lg to "tabs tabs-lg", TabsSize.Xl to "tabs tabs-xl")) { daisyTabs(size = it) }

    @Test
    fun tabs_placement() = assertEachEntry("Tabs placement", mapOf(TabsPlacement.Top to "tabs tabs-top", TabsPlacement.Bottom to "tabs tabs-bottom")) { daisyTabs(placement = it) }

    @Test
    fun tabs_style() = assertEachEntry("Tabs style", mapOf(TabsStyle.Box to "tabs tabs-box", TabsStyle.Border to "tabs tabs-border", TabsStyle.Lift to "tabs tabs-lift")) { daisyTabs(style = it) }

    @Test
    fun tabsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabsClasses()
        }
        assertRendered(html, Expected("TabsClasses defaults", "tabs"))
    }

    @Test
    fun tabsClasses_all_flags() = assertRenders(Expected("TabsClasses all flags", "tabs")) { daisyTabsClasses() }

    @Test
    fun tabsClasses_size() = assertEachEntry("TabsClasses size", mapOf(TabsSize.Xs to "tabs tabs-xs", TabsSize.Sm to "tabs tabs-sm", TabsSize.Md to "tabs tabs-md", TabsSize.Lg to "tabs tabs-lg", TabsSize.Xl to "tabs tabs-xl")) { daisyTabsClasses(size = it) }

    @Test
    fun tabsClasses_placement() = assertEachEntry("TabsClasses placement", mapOf(TabsPlacement.Top to "tabs tabs-top", TabsPlacement.Bottom to "tabs tabs-bottom")) { daisyTabsClasses(placement = it) }

    @Test
    fun tabsClasses_style() = assertEachEntry("TabsClasses style", mapOf(TabsStyle.Box to "tabs tabs-box", TabsStyle.Border to "tabs tabs-border", TabsStyle.Lift to "tabs tabs-lift")) { daisyTabsClasses(style = it) }

    @Test
    fun tab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab()
        }
        assertRendered(html, Expected("Tab defaults", "tab", closes = "</a></div>"))
    }

    @Test
    fun tab_all_flags() {
        val html = allFlags(Expected("Tab all flags", "tab tab-active tab-disabled zz-extra", closes = "</a></div>")) { daisyTab(id = ID, active = true, disabled = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun tabClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabClasses()
        }
        assertRendered(html, Expected("TabClasses defaults", "tab"))
    }

    @Test
    fun tabClasses_all_flags() = assertRenders(Expected("TabClasses all flags", "tab tab-active tab-disabled")) { daisyTabClasses(active = true, disabled = true) }

    @Test
    fun tabRadio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio()
        }
        assertRendered(html, Expected("TabRadio defaults", "tab"))
    }

    @Test
    fun tabRadio_all_flags() {
        val html = allFlags(Expected("TabRadio all flags", "tab tab-active tab-disabled zz-extra")) { daisyTabRadio(id = ID, active = true, disabled = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun tabRadioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadioClasses()
        }
        assertRendered(html, Expected("TabRadioClasses defaults", "tab"))
    }

    @Test
    fun tabRadioClasses_all_flags() = assertRenders(Expected("TabRadioClasses all flags", "tab tab-active tab-disabled")) { daisyTabRadioClasses(active = true, disabled = true) }

    @Test
    fun tabContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent()
        }
        assertRendered(html, Expected("TabContent defaults", "tab-content", closes = "</div></div>"))
    }

    @Test
    fun tabContent_all_flags() {
        val html = allFlags(Expected("TabContent all flags", "tab-content zz-extra", closes = "</div></div>")) { daisyTabContent(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun tabContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContentClasses()
        }
        assertRendered(html, Expected("TabContentClasses defaults", "tab-content"))
    }

    @Test
    fun tabContentClasses_all_flags() = assertRenders(Expected("TabContentClasses all flags", "tab-content")) { daisyTabContentClasses() }
}
