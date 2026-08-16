package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MegamenuTest {

    @Test
    fun responsive_megamenu_with_small_vertical_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(vertical = true) {
            }
        }
        val expectedClasses = "megamenu megamenu-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive megamenu with small vertical menus")
    }

    @Test
    fun megamenu_with_wide_popovers_and_horizontal_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(vertical = true, wide = true) {
            }
        }
        val expectedClasses = "megamenu megamenu-vertical megamenu-wide"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with wide popovers and horizontal menus")
    }

    @Test
    fun megamenu_with_a_menus_and_lots_of_links() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(vertical = true, wide = true) {
            }
        }
        val expectedClasses = "megamenu megamenu-vertical megamenu-wide"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with a menus and lots of links")
    }

    @Test
    fun megamenu_in_a_navbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(vertical = true, full = true) {
            }
        }
        val expectedClasses = "megamenu megamenu-full megamenu-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_without_arrows() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu() {
            }
        }
        val expectedClasses = "megamenu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for megamenu without arrows")
    }

    @Test
    fun megamenu_in_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu() {
            }
        }
        val expectedClasses = "megamenu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
    }
}
