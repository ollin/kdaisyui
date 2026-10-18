package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a
import kotlinx.html.li
import kotlinx.html.ul

class MenuTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun menu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        assertDocumented(html, "ul", "menu", "Menu")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Vertical, extraClasses = "lg:menu-horizontal") {
            }
        }
        assertDocumented(html, "ul", "lg:menu-horizontal menu menu-vertical", "Responsive: vertical on small screen, horizontal on large screen")
    }

    @Test
    fun paged_menu_with_nested_submenus() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(paged = true, direction = MenuDirection.Vertical) {
            }
        }
        assertDocumented(html, "ul", "menu menu-paged menu-vertical", "Paged menu with nested submenus")
    }

    @Test
    fun menu_with_icon_only_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        assertDocumented(html, "ul", "menu menu-horizontal", "Menu with icon only (horizontal)")
    }

    @Test
    fun menu_with_icon_only_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyTooltipClasses(side = TooltipSide.Right) }
        }
        assertDocumented(html, "a", "tooltip tooltip-right", "Menu with icon only with tooltip")
    }

    @Test
    fun menu_with_icon_only_horizontal_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyTooltipClasses() }
        }
        assertDocumented(html, "a", "tooltip", "Menu with icon only (horizontal) with tooltip")
    }

    @Test
    fun menu_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Xs) {
            }
        }
        assertDocumented(html, "ul", "menu menu-xs", "Menu sizes")
    }

    @Test
    fun menu_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Sm) {
            }
        }
        assertDocumented(html, "ul", "menu menu-sm", "Menu sizes")
    }

    @Test
    fun menu_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Md) {
            }
        }
        assertDocumented(html, "ul", "menu menu-md", "Menu sizes")
    }

    @Test
    fun menu_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Lg) {
            }
        }
        assertDocumented(html, "ul", "menu menu-lg", "Menu sizes")
    }

    @Test
    fun menu_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(size = MenuSize.Xl) {
            }
        }
        assertDocumented(html, "ul", "menu menu-xl", "Menu sizes")
    }

    @Test
    fun menu_with_disabled_items_daisyMenuItem() {
        val html = createHTML(prettyPrint = false).ul {
            li { daisyMenuItemClasses(disabled = true) }
        }
        assertDocumented(html, "li", "menu-disabled", "Menu with disabled items")
    }

    @Test
    fun menu_with_icons_and_badge_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "lg:menu-horizontal") {
            }
        }
        assertDocumented(html, "ul", "lg:menu-horizontal menu", "Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs) {
            }
        }
        assertDocumented(html, "span", "badge badge-xs", "Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Warning) {
            }
        }
        assertDocumented(html, "span", "badge badge-warning badge-xs", "Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_icons_and_badge_responsive_daisyBadge_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Info) {
            }
        }
        assertDocumented(html, "span", "badge badge-info badge-xs", "Menu with icons and badge (responsive)")
    }

    @Test
    fun menu_with_title_daisyMenuTitle() {
        val html = createHTML(prettyPrint = false).ul {
            li { daisyMenuTitleClasses() }
        }
        assertDocumented(html, "li", "menu-title", "Menu with title")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdownToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle() {
            }
        }
        assertDocumented(html, "span", "menu-dropdown-toggle", "Collapsible submenu that works with class names")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown() {
            }
        }
        assertDocumented(html, "ul", "menu-dropdown", "Collapsible submenu that works with class names")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdownToggle_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(dropdownShow = true) {
            }
        }
        assertDocumented(html, "span", "menu-dropdown-show menu-dropdown-toggle", "Collapsible submenu that works with class names")
    }

    @Test
    fun collapsible_submenu_that_works_with_class_names_daisyMenuDropdown_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(show = true) {
            }
        }
        assertDocumented(html, "ul", "menu-dropdown menu-dropdown-show", "Collapsible submenu that works with class names")
    }

    @Test
    fun menu_with_active_item_daisyMenuItem() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyMenuItemClasses(active = true) }
        }
        assertDocumented(html, "a", "menu-active", "Menu with active item")
    }

    @Test
    fun mega_menu_with_submenu_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(extraClasses = "xl:menu-horizontal") {
            }
        }
        assertDocumented(html, "ul", "menu xl:menu-horizontal", "Mega menu with submenu (responsive)")
    }
}
