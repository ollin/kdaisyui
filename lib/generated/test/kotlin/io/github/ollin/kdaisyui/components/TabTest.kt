package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TabTest {

    @Test
    fun tabs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs() {
            }
        }
        val expectedClasses = "tabs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs")
    }

    @Test
    fun tabs_daisyTab() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab() {
            }
        }
        val expectedClasses = "tab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs")
    }

    @Test
    fun tabs_daisyTab_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(active = true) {
            }
        }
        val expectedClasses = "tab tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs")
    }

    @Test
    fun tabs_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Border) {
            }
        }
        val expectedClasses = "tabs tabs-border"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-border")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs-border")
    }

    @Test
    fun tabs_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift) {
            }
        }
        val expectedClasses = "tabs tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-lift")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs-lift")
    }

    @Test
    fun tabs_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Box) {
            }
        }
        val expectedClasses = "tabs tabs-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-box")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs-box")
    }

    @Test
    fun tabs_box_using_radio_inputs_daisyTabRadio() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabRadio()
        }
        val expectedClasses = "tab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-box using radio inputs")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for tabs-box using radio inputs")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Xs) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Sm) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Lg) {
            }
        }
        val expectedClasses = "tabs tabs-lg tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, size = TabsSize.Xl) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun radio_tabs_border_tab_content_daisyTabContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabContent() {
            }
        }
        val expectedClasses = "tab-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-border + tab content")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for radio tabs-border + tab content")
    }

    @Test
    fun radio_tabs_lift_tab_content_on_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift, placement = TabsPlacement.Bottom) {
            }
        }
        val expectedClasses = "tabs tabs-bottom tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-lift + tab content on bottom")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for radio tabs-lift + tab content on bottom")
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
