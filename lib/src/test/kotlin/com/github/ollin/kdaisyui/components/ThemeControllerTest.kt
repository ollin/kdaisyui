package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ThemeControllerTest {

    @Test
    fun theme_controller_using_a_toggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle")
    }

    @Test
    fun theme_controller_using_a_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a checkbox")
    }

    @Test
    fun theme_controller_using_a_swap() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a swap")
    }

    @Test
    fun theme_controller_using_a_toggle_with_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle with text")
    }

    @Test
    fun theme_controller_using_a_toggle_with_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle with icons")
    }

    @Test
    fun theme_controller_using_a_toggle_with_icons_inside() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle with icons inside")
    }

    @Test
    fun theme_controller_using_a_toggle_with_custom_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a toggle with custom colors")
    }

    @Test
    fun theme_controller_using_a_radio_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio input")
    }

    @Test
    fun theme_controller_using_a_radio_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a radio button")
    }

    @Test
    fun theme_controller_using_a_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController() {
            }
        }
        val expectedClasses = "theme-controller"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Theme Controller using a dropdown")
    }
}
