package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ThemeControllerTest {

    @Test
    fun theme_controller_using_a_toggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyToggleClasses() })
        }
        val expectedClasses = "theme-controller toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle")
    }

    @Test
    fun theme_controller_using_a_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyCheckboxClasses() })
        }
        val expectedClasses = "checkbox theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a checkbox")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwap() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate) {
            }
        }
        val expectedClasses = "swap swap-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController()
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwapOff() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff() {
            }
        }
        val expectedClasses = "swap-off"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_swap_daisySwapOn() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn() {
            }
        }
        val expectedClasses = "swap-on"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_toggle_with_icons_inside_daisyToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle with icons inside")
    }

    @Test
    fun theme_controller_using_a_radio_input_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio input")
    }

    @Test
    fun theme_controller_using_a_radio_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyRadioClasses(size = RadioSize.Sm) })
        }
        val expectedClasses = "radio radio-sm theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio input")
    }

    @Test
    fun theme_controller_using_a_radio_button_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        val expectedClasses = "join join-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio button")
    }

    @Test
    fun theme_controller_using_a_radio_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyButtonClasses() })
        }
        val expectedClasses = "btn theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio button")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown() {
            }
        }
        val expectedClasses = "dropdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent() {
            }
        }
        val expectedClasses = "dropdown-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a dropdown")
    }

    @Test
    fun theme_controller_using_a_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(attrs = { daisyButtonClasses(size = ButtonSize.Sm, width = ButtonWidth.Block, style = ButtonStyle.Ghost) })
        }
        val expectedClasses = "btn btn-block btn-ghost btn-sm theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a dropdown")
    }
}
