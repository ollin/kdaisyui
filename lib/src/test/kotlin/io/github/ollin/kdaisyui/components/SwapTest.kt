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
    }

    @Test
    fun swap_volume_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap() {
            }
        }
        val expectedClasses = "swap"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap volume icons")
    }

    @Test
    fun swap_icons_with_rotate_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(rotate = true) {
            }
        }
        val expectedClasses = "swap swap-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap icons with rotate effect")
    }

    @Test
    fun hamburger_button() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(rotate = true) {
            }
        }
        val expectedClasses = "swap swap-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hamburger button")
    }

    @Test
    fun swap_icons_with_flip_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(flip = true) {
            }
        }
        val expectedClasses = "swap swap-flip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Swap icons with flip effect")
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
    }
}
