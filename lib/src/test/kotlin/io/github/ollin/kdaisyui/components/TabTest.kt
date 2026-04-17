package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TabTest {

    @Test
    fun tabs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs")
    }

    @Test
    fun tabs_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(border = true, tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-border tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-border")
    }

    @Test
    fun tabs_lift() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true, tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-lift")
    }

    @Test
    fun tabs_box() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(box = true, tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-box tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-box")
    }

    @Test
    fun tabs_box_using_radio_inputs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(box = true) {
            }
        }
        val expectedClasses = "tabs tabs-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-box using radio inputs")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true, tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun radio_tabs_border_tab_content() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(border = true) {
            }
        }
        val expectedClasses = "tabs tabs-border"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-border + tab content")
    }

    @Test
    fun radio_tabs_lift_tab_content() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-lift + tab content")
    }

    @Test
    fun radio_tabs_lift_with_icons_tab_content() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-lift with icons + tab content")
    }

    @Test
    fun radio_tabs_lift_tab_content_on_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true, bottom = true) {
            }
        }
        val expectedClasses = "tabs tabs-bottom tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-lift + tab content on bottom")
    }

    @Test
    fun radio_tabs_box_tab_content() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(box = true) {
            }
        }
        val expectedClasses = "tabs tabs-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for radio tabs-box + tab content")
    }

    @Test
    fun tabs_box_with_a_horizontal_scroll_when_there_s_no_space() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for tabs-box with a horizontal scroll when there's no space")
    }

    @Test
    fun tabs_with_custom_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(lift = true, tabActive = true) {
            }
        }
        val expectedClasses = "tabs tabs-lift tabs-tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Tabs with custom color")
    }
}
