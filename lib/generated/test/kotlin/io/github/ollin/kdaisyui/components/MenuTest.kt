package io.github.ollin.kdaisyui.components

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
            daisyMenu(direction = MenuDirection.Vertical, extraClasses = "lg:menu-horizontal") {
            }
        }
        val expectedClasses = "lg:menu-horizontal menu menu-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: vertical on small screen, horizontal on large screen")
    }

    @Test
    fun paged_menu_with_nested_submenus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(paged = true, direction = MenuDirection.Vertical) {
            }
        }
        val expectedClasses = "menu menu-paged menu-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Paged menu with nested submenus")
    }

    @Test
    fun menu_with_icon_only_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only (horizontal)")
    }

    @Test
    fun menu_with_icon_only_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(side = TooltipSide.Right) {
            }
        }
        val expectedClasses = "tooltip tooltip-right"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only with tooltip")
    }

    @Test
    fun menu_with_icon_only_horizontal_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        val expectedClasses = "tooltip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icon only (horizontal) with tooltip")
    }

    @Test
    fun menu_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Xs) {
            }
        }
        val expectedClasses = "menu menu-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Sm) {
            }
        }
        val expectedClasses = "menu menu-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Md) {
            }
        }
        val expectedClasses = "menu menu-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Lg) {
            }
        }
        val expectedClasses = "menu menu-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Xl) {
            }
        }
        val expectedClasses = "menu menu-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu sizes")
    }

    @Test
    fun menu_with_icons_and_badge_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "lg:menu-horizontal") {
            }
        }
        val expectedClasses = "lg:menu-horizontal menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs) {
            }
        }
        val expectedClasses = "badge badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Warning) {
            }
        }
        val expectedClasses = "badge badge-warning badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Info) {
            }
        }
        val expectedClasses = "badge badge-info badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_title_daisyMenuTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle() {
            }
        }
        val expectedClasses = "menu-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Menu with title")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdownToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle() {
            }
        }
        val expectedClasses = "menu-dropdown-toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapsible submenu that works with class names")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown() {
            }
        }
        val expectedClasses = "menu-dropdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Collapsible submenu that works with class names")
    }

    @Test
    fun mega_menu_with_submenu_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "xl:menu-horizontal") {
            }
        }
        val expectedClasses = "menu xl:menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Mega menu with submenu (responsive)")
    }
}
