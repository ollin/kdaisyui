package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a

class NavbarTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun navbar_with_title_only() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar() {
            }
        }
        assertDocumented(html, "div", "navbar", "Navbar with title only")
    }

    @Test
    fun navbar_with_title_only_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(style = ButtonStyle.Ghost) }
        }
        assertDocumented(html, "a", "btn btn-ghost", "Navbar with title only")
    }

    @Test
    fun navbar_with_title_and_icon_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square, style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost btn-square", "Navbar with title and icon")
    }

    @Test
    fun navbar_with_menu_and_submenu_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        assertDocumented(html, "ul", "menu menu-horizontal", "Navbar with menu and submenu")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses(align = DropdownAlign.End) }
        }
        assertDocumented(html, "div", "dropdown dropdown-end", "Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(attrs = { daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Circle) }) {
            }
        }
        assertDocumented(html, "div", "avatar btn btn-circle btn-ghost", "Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(attrs = { daisyMenuClasses(size = MenuSize.Sm) }) {
            }
        }
        assertDocumented(html, "ul", "dropdown-content menu menu-sm", "Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        assertDocumented(html, "span", "badge", "Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Circle) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-ghost", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyIndicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        assertDocumented(html, "div", "indicator", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Sm, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge badge-sm indicator-item", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Sm, attrs = { daisyDropdownContentClasses() }) {
            }
        }
        assertDocumented(html, "div", "card card-sm dropdown-content", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCardActions() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions() {
            }
        }
        assertDocumented(html, "div", "card-actions", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary, width = ButtonWidth.Block) {
            }
        }
        assertDocumented(html, "button", "btn btn-block btn-primary", "Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart() {
            }
        }
        assertDocumented(html, "div", "navbar-start", "Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyDropdownClasses() }
        }
        assertDocumented(html, "div", "dropdown", "Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarCenter() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter() {
            }
        }
        assertDocumented(html, "div", "navbar-center", "Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd() {
            }
        }
        assertDocumented(html, "div", "navbar-end", "Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Primary, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge badge-primary badge-xs indicator-item", "Navbar with dropdown, center logo and icon")
    }

    @Test
    fun responsive_dropdown_menu_on_small_screen_center_menu_on_large_screen_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses() }
        }
        assertDocumented(html, "a", "btn", "Responsive (dropdown menu on small screen, center menu on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(attrs = { daisyCollapseTitleClasses() }) {
            }
        }
        assertDocumented(html, "div", "collapse-title navbar", "Responsive (collapse on small screen, full content on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        assertDocumented(html, "div", "collapse-content", "Responsive (collapse on small screen, full content on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        assertDocumented(html, "ul", "menu", "Responsive (collapse on small screen, full content on large screen)")
    }
}
