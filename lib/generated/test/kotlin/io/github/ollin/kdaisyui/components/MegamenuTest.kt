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

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        assertDocumented(html, "div", "max-sm:megamenu-vertical megamenu", "Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyMegamenuActive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive() {
            }
        }
        assertDocumented(html, "span", "megamenu-active", "Responsive megamenu with small vertical menus")
    }

    @Test
    fun responsive_megamenu_with_small_vertical_menus_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        assertDocumented(html, "ul", "menu", "Responsive megamenu with small vertical menus")
    }

    @Test
    fun megamenu_with_wide_popovers_and_horizontal_menus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(width = MegamenuWidth.Wide, extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        assertDocumented(html, "div", "max-sm:megamenu-vertical megamenu megamenu-wide", "Megamenu with wide popovers and horizontal menus")
    }

    @Test
    fun megamenu_with_wide_popovers_and_horizontal_menus_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        assertDocumented(html, "ul", "menu menu-horizontal", "Megamenu with wide popovers and horizontal menus")
    }

    @Test
    fun megamenu_with_a_menus_and_lots_of_links_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "md:menu-horizontal") {
            }
        }
        assertDocumented(html, "ul", "md:menu-horizontal menu", "Megamenu with a menus and lots of links")
    }

    @Test
    fun megamenu_with_a_menus_and_lots_of_links_daisyMenuTitle() {
        val html = createHTML(prettyPrint = false).ul {
            li { daisyMenuTitleClasses() }
        }
        assertDocumented(html, "li", "menu-title", "Megamenu with a menus and lots of links")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        assertDocumented(html, "div", "navbar", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart() {
            }
        }
        assertDocumented(html, "div", "navbar-start", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(style = ButtonStyle.Ghost) }
        }
        assertDocumented(html, "a", "btn btn-ghost", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarCenter() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter() {
            }
        }
        assertDocumented(html, "div", "navbar-center", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(width = MegamenuWidth.Full, extraClasses = "max-sm:megamenu-vertical") {
            }
        }
        assertDocumented(html, "div", "max-sm:megamenu-vertical megamenu megamenu-full", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_in_a_navbar_daisyNavbarEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd() {
            }
        }
        assertDocumented(html, "div", "navbar-end", "Megamenu in a navbar")
    }

    @Test
    fun megamenu_without_arrows() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu() {
            }
        }
        assertDocumented(html, "div", "megamenu", "megamenu without arrows")
    }

    @Test
    fun megamenu_in_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Xs) {
            }
        }
        assertDocumented(html, "div", "megamenu megamenu-xs", "Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Sm) {
            }
        }
        assertDocumented(html, "div", "megamenu megamenu-sm", "Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Md) {
            }
        }
        assertDocumented(html, "div", "megamenu megamenu-md", "Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Lg) {
            }
        }
        assertDocumented(html, "div", "megamenu megamenu-lg", "Megamenu in different sizes")
    }

    @Test
    fun megamenu_in_different_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(size = MegamenuSize.Xl) {
            }
        }
        assertDocumented(html, "div", "megamenu megamenu-xl", "Megamenu in different sizes")
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
