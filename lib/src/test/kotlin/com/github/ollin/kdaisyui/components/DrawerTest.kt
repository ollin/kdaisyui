package com.github.ollin.kdaisyui.components

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
    fun navbar_menu_for_desktop_sidebar_drawer_for_mobile() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer() {
            }
        }
        val expectedClasses = "drawer"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar menu for desktop + sidebar drawer for mobile")
    }

    @Test
    fun responsive_sidebar_is_always_visible_on_large_screen_can_be_toggled_on_small_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(open = true) {
            }
        }
        val expectedClasses = "drawer drawer-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: Sidebar is always visible on large screen, can be toggled on small screen")
    }

    @Test
    fun responsive_collapsible_icon_only_drawer_sidebar_using_is_drawer_close_and_is_drawer_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(open = true) {
            }
        }
        val expectedClasses = "drawer drawer-open"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive collapsible Icon-only drawer sidebar. Using is-drawer-close and is-drawer-open")
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
}
