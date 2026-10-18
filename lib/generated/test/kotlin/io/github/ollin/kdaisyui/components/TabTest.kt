package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TabTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun tabs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs() {
            }
        }
        assertDocumented(html, "div", "tabs", "tabs")
    }

    @Test
    fun tabs_daisyTab() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab() {
            }
        }
        assertDocumented(html, "a", "tab", "tabs")
    }

    @Test
    fun tabs_daisyTab_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(active = true) {
            }
        }
        assertDocumented(html, "a", "tab tab-active", "tabs")
    }

    @Test
    fun tabs_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Border) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-border", "tabs-border")
    }

    @Test
    fun tabs_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lift", "tabs-lift")
    }

    @Test
    fun tabs_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Box) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-box", "tabs-box")
    }

    @Test
    fun tabs_box_using_radio_inputs_daisyTabRadio() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio()
        }
        assertDocumented(html, "input", "tab", "tabs-box using radio inputs")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Xs) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lift tabs-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Sm) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lift tabs-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Lg) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lg tabs-lift", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Xl) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lift tabs-xl", "Sizes")
    }

    @Test
    fun radio_tabs_border_tab_content_daisyTabContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent() {
            }
        }
        assertDocumented(html, "div", "tab-content", "radio tabs-border + tab content")
    }

    @Test
    fun radio_tabs_lift_tab_content_on_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, placement = TabsPlacement.Bottom) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-bottom tabs-lift", "radio tabs-lift + tab content on bottom")
    }

    @Test
    fun renders_static_attributes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs {
            }
        }
        assertTrue(html.contains("role=\"tablist\""))
    }

    @Test
    fun custom_part_radio_renders_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio {
            }
        }
        assertTrue(html.contains("<input"))
        assertTrue(html.contains("class=\"tab"))
        assertTrue(html.contains("type=\"radio\""))
        assertTrue(html.contains("role=\"tab\""))
    }
}
