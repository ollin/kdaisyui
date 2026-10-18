package io.github.ollin.kdaisyui.components

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
    fun navbar_with_title_only_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with title only")
    }

    @Test
    fun navbar_with_title_and_icon_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square, style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with title and icon")
    }

    @Test
    fun navbar_with_menu_and_submenu_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(direction = MenuDirection.Horizontal) {
            }
        }
        val expectedClasses = "menu menu-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with menu and submenu")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(align = DropdownAlign.End) {
            }
        }
        val expectedClasses = "dropdown dropdown-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(attrs = { daisyButtonClasses(style = ButtonStyle.Ghost, shape = ButtonShape.Circle) }) {
            }
        }
        val expectedClasses = "avatar btn btn-circle btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyDropdownContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(attrs = { daisyMenuClasses(size = MenuSize.Sm) }) {
            }
        }
        val expectedClasses = "dropdown-content menu menu-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_search_input_and_dropdown_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with search input and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost, shape = ButtonShape.Circle) {
            }
        }
        val expectedClasses = "btn btn-circle btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyIndicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Sm, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        val expectedClasses = "badge badge-sm indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Sm, attrs = { daisyDropdownContentClasses() }) {
            }
        }
        val expectedClasses = "card card-sm dropdown-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyCardActions() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions() {
            }
        }
        val expectedClasses = "card-actions"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_icon_indicator_and_dropdown_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary, width = ButtonWidth.Block) {
            }
        }
        val expectedClasses = "btn btn-block btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with icon, indicator and dropdown")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarStart() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarStart() {
            }
        }
        val expectedClasses = "navbar-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyDropdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown() {
            }
        }
        val expectedClasses = "dropdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarCenter() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarCenter() {
            }
        }
        val expectedClasses = "navbar-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyNavbarEnd() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbarEnd() {
            }
        }
        val expectedClasses = "navbar-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun navbar_with_dropdown_center_logo_and_icon_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Primary, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        val expectedClasses = "badge badge-primary badge-xs indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Navbar with dropdown, center logo and icon")
    }

    @Test
    fun responsive_dropdown_menu_on_small_screen_center_menu_on_large_screen_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (dropdown menu on small screen, center menu on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyNavbar(attrs = { daisyCollapseTitleClasses() }) {
            }
        }
        val expectedClasses = "collapse-title navbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (collapse on small screen, full content on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen_daisyCollapseContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent() {
            }
        }
        val expectedClasses = "collapse-content"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (collapse on small screen, full content on large screen)")
    }

    @Test
    fun responsive_collapse_on_small_screen_full_content_on_large_screen_daisyMenu() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu() {
            }
        }
        val expectedClasses = "menu"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (collapse on small screen, full content on large screen)")
    }
}
