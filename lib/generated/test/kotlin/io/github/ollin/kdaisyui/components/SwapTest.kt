package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class SwapTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun swap_text() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap() {
            }
        }
        assertDocumented(html, "label", "swap", "Swap text")
    }

    @Test
    fun swap_text_daisySwapOn() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn() {
            }
        }
        assertDocumented(html, "div", "swap-on", "Swap text")
    }

    @Test
    fun swap_text_daisySwapOff() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff() {
            }
        }
        assertDocumented(html, "div", "swap-off", "Swap text")
    }

    @Test
    fun swap_icons_with_rotate_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate) {
            }
        }
        assertDocumented(html, "label", "swap swap-rotate", "Swap icons with rotate effect")
    }

    @Test
    fun hamburger_button() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Rotate, attrs = { daisyButtonClasses(shape = ButtonShape.Circle) }) {
            }
        }
        assertDocumented(html, "label", "btn btn-circle swap swap-rotate", "Hamburger button")
    }

    @Test
    fun swap_icons_with_flip_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(style = SwapStyle.Flip) {
            }
        }
        assertDocumented(html, "label", "swap swap-flip", "Swap icons with flip effect")
    }

    @Test
    fun activate_using_class_name_instead_of_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(active = true) {
            }
        }
        assertDocumented(html, "label", "swap swap-active", "Activate using class name instead of checkbox")
    }
}
