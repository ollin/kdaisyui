package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.a
import kotlinx.html.li
import kotlinx.html.ul

class MegamenuTest {

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive megamenu with small vertical menus")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        val expectedClasses = "max-sm:megamenu-vertical megamenu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive megamenu with small vertical menus")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyMegamenuActive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive() {
            }
        }
        val expectedClasses = "megamenu-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive megamenu with small vertical menus")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive megamenu with small vertical menus")
        assertEquals("ul", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive megamenu with small vertical menus")
    }

    @Test
    fun megamenu_with_wide_popovers_and_horizontal_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(width = MegamenuWidth.Wide, extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        val expectedClasses = "max-sm:megamenu-vertical megamenu megamenu-wide"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with wide popovers and horizontal menus")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu with wide popovers and horizontal menus")
    }

    @Test
    fun megamenu_with_wide_popovers_and_horizontal_menus_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with wide popovers and horizontal menus")
        assertEquals("ul", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu with wide popovers and horizontal menus")
    }

    @Test
    fun megamenu_with_a_menus_and_lots_of_links_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "md:menu-horizontal") {
            }
        }
        val expectedClasses = "md:menu-horizontal menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with a menus and lots of links")
        assertEquals("ul", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu with a menus and lots of links")
    }

    @Test
    fun megamenu_with_a_menus_and_lots_of_links_daisyMenuTitle() {
        val html = createHTML(prettyPrint = false).ul {
            li { daisyMenuTitleClasses() }
        }
        val expectedClasses = "menu-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu with a menus and lots of links")
        assertEquals("li", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu with a menus and lots of links")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart() {
            }
        }
        val expectedClasses = "navbar-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(style = ButtonStyle.Ghost) }
        }
        val expectedClasses = "btn btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarCenter() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter() {
            }
        }
        val expectedClasses = "navbar-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(width = MegamenuWidth.Full, extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        val expectedClasses = "max-sm:megamenu-vertical megamenu megamenu-full"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd() {
            }
        }
        val expectedClasses = "navbar-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in a navbar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in a navbar")
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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for megamenu without arrows")
    }

    @Test
    fun megamenu_in_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Xs) {
            }
        }
        val expectedClasses = "megamenu megamenu-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Sm) {
            }
        }
        val expectedClasses = "megamenu megamenu-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Md) {
            }
        }
        val expectedClasses = "megamenu megamenu-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Lg) {
            }
        }
        val expectedClasses = "megamenu megamenu-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Xl) {
            }
        }
        val expectedClasses = "megamenu megamenu-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Megamenu in different sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Megamenu in different sizes")
    }

    @Test
    fun renders_static_attributes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu {
            }
        }
        assertTrue(html.contains("popover=\"\""))
    }

    @Test
    fun custom_part_panel_renders_div() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuPanel {
            }
        }
        assertTrue(html.contains("<div"))
        assertTrue(html.contains("popover=\"\""))
    }
}
