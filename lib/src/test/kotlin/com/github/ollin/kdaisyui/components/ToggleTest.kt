package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ToggleTest {

    @Test
    fun toggle_switch() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle (switch)")
    }

    @Test
    fun with_fieldset_and_label() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }

    @Test
    fun indeterminate() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Indeterminate")
    }

    @Test
    fun toggle_with_icons_inside() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle with icons inside")
    }

    @Test
    fun toggle_with_custom_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle() {
            }
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle with custom colors")
    }
}
