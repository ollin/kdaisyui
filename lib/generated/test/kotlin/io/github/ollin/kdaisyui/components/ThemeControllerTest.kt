package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.label
import kotlinx.html.svg

class ThemeControllerTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun theme_controller_using_a_toggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyToggleClasses() })
        }
        assertDocumented(html, "input", "theme-controller toggle", "Theme Controller using a toggle")
    }

    @Test
    fun theme_controller_using_a_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyCheckboxClasses() })
        }
        assertDocumented(html, "input", "checkbox theme-controller", "Theme Controller using a checkbox")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwap() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate) {
            }
        }
        assertDocumented(html, "label", "swap swap-rotate", "Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController()
        }
        assertDocumented(html, "input", "theme-controller", "Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwapOff() {
        val html = createHTML(prettyPrint = false).div {
            svg { daisySwapOffClasses() }
        }
        assertDocumented(html, "svg", "swap-off", "Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwapOn() {
        val html = createHTML(prettyPrint = false).div {
            svg { daisySwapOnClasses() }
        }
        assertDocumented(html, "svg", "swap-on", "Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_toggle_with_icons_inside_daisyToggle() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyToggleClasses() }
        }
        assertDocumented(html, "label", "toggle", "Theme Controller using a toggle with icons inside")
    }

    @Test
    fun theme_controller_using_a_radio_input_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "Theme Controller using a radio input")
    }

    @Test
    fun theme_controller_using_a_radio_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyRadioClasses(size = RadioSize.Sm) })
        }
        assertDocumented(html, "input", "radio radio-sm theme-controller", "Theme Controller using a radio input")
    }

    @Test
    fun theme_controller_using_a_radio_button_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        assertDocumented(html, "div", "join join-vertical", "Theme Controller using a radio button")
    }

    @Test
    fun theme_controller_using_a_radio_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyButtonClasses() })
        }
        assertDocumented(html, "input", "btn theme-controller", "Theme Controller using a radio button")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses() }
        }
        assertDocumented(html, "div", "dropdown", "Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses() }
        }
        assertDocumented(html, "div", "btn", "Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent() {
            }
        }
        assertDocumented(html, "ul", "dropdown-content", "Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyButtonClasses(size = ButtonSize.Sm, width = ButtonWidth.Block, style = ButtonStyle.Ghost) })
        }
        assertDocumented(html, "input", "btn btn-block btn-ghost btn-sm theme-controller", "Theme Controller using a dropdown")
    }
}
