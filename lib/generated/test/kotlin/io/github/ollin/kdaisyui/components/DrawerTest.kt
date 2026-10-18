package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DrawerTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun drawer_sidebar() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer() {
            }
        }
        assertDocumented(html, "div", "drawer", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle()
        }
        assertDocumented(html, "input", "drawer-toggle", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent() {
            }
        }
        assertDocumented(html, "div", "drawer-content", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses() }) {
            }
        }
        assertDocumented(html, "label", "btn drawer-button", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerSide() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide() {
            }
        }
        assertDocumented(html, "div", "drawer-side", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerOverlay() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay() {
            }
        }
        assertDocumented(html, "label", "drawer-overlay", "Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        assertDocumented(html, "ul", "menu", "Drawer sidebar")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        assertDocumented(html, "div", "navbar", "Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses(shape = ButtonShape.Square, style = ButtonStyle.Ghost) }) {
            }
        }
        assertDocumented(html, "label", "btn btn-ghost btn-square drawer-button", "Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        assertDocumented(html, "ul", "menu menu-horizontal", "Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun responsive_sidebar_is_always_visible_on_large_screen_can_be_toggled_on_small_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(extraClasses = "lg:drawer-open") {
            }
        }
        assertDocumented(html, "div", "drawer lg:drawer-open", "Responsive: Sidebar is always visible on large screen, can be toggled on small screen")
    }

    @Test
    fun drawer_sidebar_that_opens_from_right_side_of_page() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(end = true) {
            }
        }
        assertDocumented(html, "div", "drawer drawer-end", "Drawer sidebar that opens from right side of page")
    }

    @Test
    fun drawer_sidebar_that_opens_from_right_side_of_page_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses(color = ButtonColor.Primary) }) {
            }
        }
        assertDocumented(html, "label", "btn btn-primary drawer-button", "Drawer sidebar that opens from right side of page")
    }
}
