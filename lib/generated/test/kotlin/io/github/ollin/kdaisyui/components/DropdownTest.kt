package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.assertTrue
import kotlinx.html.a
import kotlinx.html.summary

class DropdownTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun dropdown_using_details_and_summary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown() {
            }
        }
        assertDocumented(html, "details", "dropdown", "Dropdown using details and summary")
    }

    @Test
    fun dropdown_using_details_and_summary_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            summary { daisyButtonClasses() }
        }
        assertDocumented(html, "summary", "btn", "Dropdown using details and summary")
    }

    @Test
    fun dropdown_using_details_and_summary_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(attrs = { daisyMenuClasses() }) {
            }
        }
        assertDocumented(html, "ul", "dropdown-content menu", "Dropdown using details and summary")
    }

    @Test
    fun dropdown_using_popover_api_and_anchor_positioning_daisyDropdownPopover() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(id = htmlId("documented"), attrs = { daisyMenuClasses() }) {
            }
        }
        assertDocumented(html, "ul", "dropdown menu", "Dropdown using popover API and anchor positioning")
    }

    @Test
    fun dropdown_aligns_to_start_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(align = DropdownAlign.Start) }
        }
        assertDocumented(html, "div", "dropdown dropdown-start", "Dropdown / aligns to start of button horizontally")
    }

    @Test
    fun dropdown_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-end", "Dropdown / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(align = DropdownAlign.Center) }
        }
        assertDocumented(html, "div", "dropdown dropdown-center", "Dropdown / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_top() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Top) }
        }
        assertDocumented(html, "div", "dropdown dropdown-top", "Dropdown top")
    }

    @Test
    fun dropdown_top_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Top, align = DropdownAlign.Center) }
        }
        assertDocumented(html, "div", "dropdown dropdown-center dropdown-top", "Dropdown top / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_top_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Top, align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-end dropdown-top", "Dropdown top / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Bottom) }
        }
        assertDocumented(html, "div", "dropdown dropdown-bottom", "Dropdown bottom (default)")
    }

    @Test
    fun dropdown_bottom_default_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Bottom, align = DropdownAlign.Center) }
        }
        assertDocumented(html, "div", "dropdown dropdown-bottom dropdown-center", "Dropdown bottom (default) / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_bottom_default_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Bottom, align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-bottom dropdown-end", "Dropdown bottom (default) / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_left() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Left) }
        }
        assertDocumented(html, "div", "dropdown dropdown-left", "Dropdown left")
    }

    @Test
    fun dropdown_left_aligns_to_center_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Left, align = DropdownAlign.Center) }
        }
        assertDocumented(html, "div", "dropdown dropdown-center dropdown-left", "Dropdown left / aligns to center of button vertically")
    }

    @Test
    fun dropdown_left_aligns_to_end_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Left, align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-end dropdown-left", "Dropdown left / aligns to end of button vertically")
    }

    @Test
    fun dropdown_right() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Right) }
        }
        assertDocumented(html, "div", "dropdown dropdown-right", "Dropdown right")
    }

    @Test
    fun dropdown_right_aligns_to_end_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Right, align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-end dropdown-right", "Dropdown right / aligns to end of button vertically")
    }

    @Test
    fun dropdown_right_aligns_to_center_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(side = DropdownSide.Right, align = DropdownAlign.Center) }
        }
        assertDocumented(html, "div", "dropdown dropdown-center dropdown-right", "Dropdown right / aligns to center of button vertically")
    }

    @Test
    fun dropdown_on_hover() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(hover = true) }
        }
        assertDocumented(html, "div", "dropdown dropdown-hover", "Dropdown on hover")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(state = DropdownState.Open) }
        }
        assertDocumented(html, "div", "dropdown dropdown-open", "Force open")
    }

    @Test
    fun force_close() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(state = DropdownState.Close) }
        }
        assertDocumented(html, "div", "dropdown dropdown-close", "Force close")
    }

    @Test
    fun card_as_dropdown_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownContentClasses(); daisyCardClasses(size = CardSize.Sm) }
        }
        assertDocumented(html, "div", "card card-sm dropdown-content", "Card as dropdown")
    }

    @Test
    fun card_as_dropdown_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Card as dropdown")
    }

    @Test
    fun dropdown_in_navbar_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        assertDocumented(html, "div", "navbar", "Dropdown in navbar")
    }

    @Test
    fun dropdown_in_navbar_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(style = ButtonStyle.Ghost) }
        }
        assertDocumented(html, "a", "btn btn-ghost", "Dropdown in navbar")
    }

    @Test
    fun helper_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(shape = ButtonShape.Circle, style = ButtonStyle.Ghost, size = ButtonSize.Xs) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-ghost btn-xs", "Helper dropdown")
    }

    @Test
    fun helper_dropdown_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        assertDocumented(html, "h2", "card-title", "Helper dropdown")
    }

    @Test
    fun custom_part_popover_renders_ul() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(id = htmlId("custom-part")) {
            }
        }
        assertTrue(html.contains("<ul"))
        assertTrue(html.contains("class=\"dropdown"))
        assertTrue(html.contains("popover=\"\""))
        assertTrue(html.contains("style=\"position-anchor:--custom-part\""))
    }
}
