package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class SwapTest {

    @Test
    fun swap_text() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap() {
            }
        }
        val expectedClasses = "swap"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap text")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Swap text")
    }

    @Test
    fun swap_text_daisySwapOn() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn() {
            }
        }
        val expectedClasses = "swap-on"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap text")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Swap text")
    }

    @Test
    fun swap_text_daisySwapOff() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff() {
            }
        }
        val expectedClasses = "swap-off"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap text")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Swap text")
    }

    @Test
    fun swap_icons_with_rotate_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate) {
            }
        }
        val expectedClasses = "swap swap-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap icons with rotate effect")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Swap icons with rotate effect")
    }

    @Test
    fun hamburger_button() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate, attrs = { daisyButtonClasses(shape = ButtonShape.Circle) }) {
            }
        }
        val expectedClasses = "btn btn-circle swap swap-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hamburger button")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Hamburger button")
    }

    @Test
    fun swap_icons_with_flip_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Flip) {
            }
        }
        val expectedClasses = "swap swap-flip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap icons with flip effect")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Swap icons with flip effect")
    }

    @Test
    fun activate_using_class_name_instead_of_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(active = true) {
            }
        }
        val expectedClasses = "swap swap-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Activate using class name instead of checkbox")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Activate using class name instead of checkbox")
    }
}
