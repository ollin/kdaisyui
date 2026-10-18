package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MenuCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun menu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                content = { },
            )
        }
        assertRendered(html, "menu", "Menu defaults", closes = "</ul></div>")
    }

    @Test
    fun menu_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                id = htmlId("x-cov-id"),
                active = true,
                disabled = true,
                dropdownShow = true,
                focus = true,
                paged = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "menu menu-active menu-disabled menu-dropdown-show menu-focus menu-paged zz-extra", "Menu all flags", closes = "</ul></div>")
        assertCommonFlags(html, "Menu")
    }

    @Test
    fun menu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "menu menu-xs", "Menu size Xs")
    }

    @Test
    fun menu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "menu menu-sm", "Menu size Sm")
    }

    @Test
    fun menu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Md,
                content = { },
            )
        }
        assertRendered(html, "menu menu-md", "Menu size Md")
    }

    @Test
    fun menu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "menu menu-lg", "Menu size Lg")
    }

    @Test
    fun menu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "menu menu-xl", "Menu size Xl")
    }

    @Test
    fun menu_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                direction = MenuDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "menu menu-vertical", "Menu direction Vertical")
    }

    @Test
    fun menu_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                direction = MenuDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "menu menu-horizontal", "Menu direction Horizontal")
    }

    @Test
    fun menuClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses()
        }
        assertRendered(html, "menu", "MenuClasses defaults")
    }

    @Test
    fun menuClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                active = true,
                disabled = true,
                dropdownShow = true,
                focus = true,
                paged = true,
            )
        }
        assertRendered(html, "menu menu-active menu-disabled menu-dropdown-show menu-focus menu-paged", "MenuClasses all flags")
    }

    @Test
    fun menuClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Xs,
            )
        }
        assertRendered(html, "menu menu-xs", "MenuClasses size Xs")
    }

    @Test
    fun menuClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Sm,
            )
        }
        assertRendered(html, "menu menu-sm", "MenuClasses size Sm")
    }

    @Test
    fun menuClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Md,
            )
        }
        assertRendered(html, "menu menu-md", "MenuClasses size Md")
    }

    @Test
    fun menuClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Lg,
            )
        }
        assertRendered(html, "menu menu-lg", "MenuClasses size Lg")
    }

    @Test
    fun menuClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Xl,
            )
        }
        assertRendered(html, "menu menu-xl", "MenuClasses size Xl")
    }

    @Test
    fun menuClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                direction = MenuDirection.Vertical,
            )
        }
        assertRendered(html, "menu menu-vertical", "MenuClasses direction Vertical")
    }

    @Test
    fun menuClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                direction = MenuDirection.Horizontal,
            )
        }
        assertRendered(html, "menu menu-horizontal", "MenuClasses direction Horizontal")
    }

    @Test
    fun menuTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle()
        }
        assertRendered(html, "menu-title", "MenuTitle defaults", closes = "</h2></div>")
    }

    @Test
    fun menuTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "menu-title zz-extra", "MenuTitle all flags", closes = "</h2></div>")
        assertCommonFlags(html, "MenuTitle")
    }

    @Test
    fun menuTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "menu-title", "MenuTitle text")
        assertTrue(html.contains("txtmark"), "MenuTitle text content")
    }

    @Test
    fun menuTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitleClasses()
        }
        assertRendered(html, "menu-title", "MenuTitleClasses defaults")
    }

    @Test
    fun menuTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitleClasses()
        }
        assertRendered(html, "menu-title", "MenuTitleClasses all flags")
    }

    @Test
    fun menuDropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(
                content = { },
            )
        }
        assertRendered(html, "menu-dropdown", "MenuDropdown defaults", closes = "</ul></div>")
    }

    @Test
    fun menuDropdown_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "menu-dropdown zz-extra", "MenuDropdown all flags", closes = "</ul></div>")
        assertCommonFlags(html, "MenuDropdown")
    }

    @Test
    fun menuDropdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownClasses()
        }
        assertRendered(html, "menu-dropdown", "MenuDropdownClasses defaults")
    }

    @Test
    fun menuDropdownClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownClasses()
        }
        assertRendered(html, "menu-dropdown", "MenuDropdownClasses all flags")
    }

    @Test
    fun menuDropdownToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(
                content = { },
            )
        }
        assertRendered(html, "menu-dropdown-toggle", "MenuDropdownToggle defaults", closes = "</span></div>")
    }

    @Test
    fun menuDropdownToggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "menu-dropdown-toggle zz-extra", "MenuDropdownToggle all flags", closes = "</span></div>")
        assertCommonFlags(html, "MenuDropdownToggle")
    }

    @Test
    fun menuDropdownToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggleClasses()
        }
        assertRendered(html, "menu-dropdown-toggle", "MenuDropdownToggleClasses defaults")
    }

    @Test
    fun menuDropdownToggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggleClasses()
        }
        assertRendered(html, "menu-dropdown-toggle", "MenuDropdownToggleClasses all flags")
    }
}
