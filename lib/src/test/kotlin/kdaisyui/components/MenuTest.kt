package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MenuTest {

    @Test
    fun menu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(vertical = true, horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal menu-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: vertical on small screen, horizontal on large screen")
    }

    @Test
    fun menu_with_icon_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only")
    }

    @Test
    fun menu_with_icon_only_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only (horizontal)")
    }

    @Test
    fun menu_with_icon_only_with_tooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only with tooltip")
    }

    @Test
    fun menu_with_icon_only_horizontal_with_tooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only (horizontal) with tooltip")
    }

    @Test
    fun menu_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_with_disabled_items() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(disabled = true) {
            }
        }
        val expectedClasses = "menu menu-disabled"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with disabled items")
    }

    @Test
    fun menu_with_icons() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons")
    }

    @Test
    fun menu_with_icons_and_badge_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_without_padding_and_border_radius() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu without padding and border radius")
    }

    @Test
    fun menu_with_title() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with title")
    }

    @Test
    fun menu_with_title_as_a_parent() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with title as a parent")
    }

    @Test
    fun submenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Submenu")
    }

    @Test
    fun collapsible_submenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapsible submenu")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(dropdownShow = true) {
            }
        }
        val expectedClasses = "menu menu-dropdown-show"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapsible submenu that works with class names")
    }

    @Test
    fun file_tree() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for File tree")
    }

    @Test
    fun menu_with_active_item() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(active = true) {
            }
        }
        val expectedClasses = "menu menu-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with active item")
    }

    @Test
    fun horizontal_menu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Horizontal menu")
    }

    @Test
    fun horizontal_submenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Horizontal submenu")
    }

    @Test
    fun mega_menu_with_submenu_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Mega menu with submenu (responsive)")
    }

    @Test
    fun collapsible_with_submenu_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(horizontal = true) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapsible with submenu (responsive)")
    }
}
