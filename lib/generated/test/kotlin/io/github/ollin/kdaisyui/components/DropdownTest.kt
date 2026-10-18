package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DropdownTest {

    @Test
    fun dropdown_using_details_and_summary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown() {
            }
        }
        val expectedClasses = "dropdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown using details and summary")
    }

    @Test
    fun dropdown_using_details_and_summary_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown using details and summary")
    }

    @Test
    fun dropdown_aligns_to_start_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(align = DropdownAlign.Start) {
            }
        }
        val expectedClasses = "dropdown dropdown-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown / aligns to start of button horizontally")
    }

    @Test
    fun dropdown_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(align = DropdownAlign.Center) {
            }
        }
        val expectedClasses = "dropdown dropdown-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Top) {
            }
        }
        val expectedClasses = "dropdown dropdown-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown top")
    }

    @Test
    fun dropdown_top_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Top, align = DropdownAlign.Center) {
            }
        }
        val expectedClasses = "dropdown dropdown-center dropdown-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown top / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_top_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Top, align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-end dropdown-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown top / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Bottom) {
            }
        }
        val expectedClasses = "dropdown dropdown-bottom"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown bottom (default)")
    }

    @Test
    fun dropdown_bottom_default_aligns_to_center_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Bottom, align = DropdownAlign.Center) {
            }
        }
        val expectedClasses = "dropdown dropdown-bottom dropdown-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown bottom (default) / aligns to center of button horizontally")
    }

    @Test
    fun dropdown_bottom_default_aligns_to_end_of_button_horizontally() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Bottom, align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-bottom dropdown-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown bottom (default) / aligns to end of button horizontally")
    }

    @Test
    fun dropdown_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Left) {
            }
        }
        val expectedClasses = "dropdown dropdown-left"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown left")
    }

    @Test
    fun dropdown_left_aligns_to_center_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Left, align = DropdownAlign.Center) {
            }
        }
        val expectedClasses = "dropdown dropdown-center dropdown-left"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown left / aligns to center of button vertically")
    }

    @Test
    fun dropdown_left_aligns_to_end_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Left, align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-end dropdown-left"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown left / aligns to end of button vertically")
    }

    @Test
    fun dropdown_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Right) {
            }
        }
        val expectedClasses = "dropdown dropdown-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown right")
    }

    @Test
    fun dropdown_right_aligns_to_end_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Right, align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-end dropdown-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown right / aligns to end of button vertically")
    }

    @Test
    fun dropdown_right_aligns_to_center_of_button_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(side = DropdownSide.Right, align = DropdownAlign.Center) {
            }
        }
        val expectedClasses = "dropdown dropdown-center dropdown-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown right / aligns to center of button vertically")
    }

    @Test
    fun dropdown_on_hover() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(hover = true) {
            }
        }
        val expectedClasses = "dropdown dropdown-hover"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown on hover")
    }

    @Test
    fun force_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(state = DropdownState.Open) {
            }
        }
        val expectedClasses = "dropdown dropdown-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force open")
    }

    @Test
    fun force_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(state = DropdownState.Close) {
            }
        }
        val expectedClasses = "dropdown dropdown-close"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Force close")
    }

    @Test
    fun card_as_dropdown_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card as dropdown")
    }

    @Test
    fun dropdown_in_navbar_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown in navbar")
    }

    @Test
    fun dropdown_in_navbar_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dropdown in navbar")
    }

    @Test
    fun helper_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle, style = ButtonStyle.Ghost, size = ButtonSize.Xs) {
            }
        }
        val expectedClasses = "btn btn-circle btn-ghost btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Helper dropdown")
    }

    @Test
    fun helper_dropdown_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        val expectedClasses = "card-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Helper dropdown")
    }
}
