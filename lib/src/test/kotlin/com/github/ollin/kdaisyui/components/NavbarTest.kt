package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class NavbarTest {

    @Test
    fun navbar_with_title_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with title only")
    }

    @Test
    fun navbar_with_title_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with title and icon")
    }

    @Test
    fun navbar_with_icon_at_start_and_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon at start and end")
    }

    @Test
    fun navbar_with_menu_and_submenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with menu and submenu")
    }

    @Test
    fun navbar_with_search_input_and_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun responsive_dropdown_menu_on_small_screen_center_menu_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (dropdown menu on small screen, center menu on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (collapse on small screen, full content on large screen)")
    }

    @Test
    fun navbar_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        val expectedClasses = "navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with colors")
    }
}
