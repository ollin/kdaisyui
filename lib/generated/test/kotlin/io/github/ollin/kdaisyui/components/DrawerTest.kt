package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DrawerTest {

    @Test
    fun drawer_sidebar() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer() {
            }
        }
        val expectedClasses = "drawer"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle()
        }
        val expectedClasses = "drawer-toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent() {
            }
        }
        val expectedClasses = "drawer-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses() }) {
            }
        }
        val expectedClasses = "btn drawer-button"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerSide() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide() {
            }
        }
        val expectedClasses = "drawer-side"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyDrawerOverlay() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay() {
            }
        }
        val expectedClasses = "drawer-overlay"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun drawer_sidebar_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyNavbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses(shape = ButtonShape.Square, style = ButtonStyle.Ghost) }) {
            }
        }
        val expectedClasses = "btn btn-ghost btn-square drawer-button"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun responsive_sidebar_is_always_visible_on_large_screen_can_be_toggled_on_small_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(extraClasses = "lg:drawer-open") {
            }
        }
        val expectedClasses = "drawer lg:drawer-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: Sidebar is always visible on large screen, can be toggled on small screen")
    }

    @Test
    fun drawer_sidebar_that_opens_from_right_side_of_page() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(end = true) {
            }
        }
        val expectedClasses = "drawer drawer-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar that opens from right side of page")
    }

    @Test
    fun drawer_sidebar_that_opens_from_right_side_of_page_daisyDrawerButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(attrs = { daisyButtonClasses(color = ButtonColor.Primary) }) {
            }
        }
        val expectedClasses = "btn btn-primary drawer-button"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Drawer sidebar that opens from right side of page")
    }
}
