package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MenuCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun menu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                content = { },
            )
        }
        assertRendered(html, Expected("Menu defaults", "menu", closes = "</ul></div>"))
    }

    @Test
    fun menu_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                id = htmlId("x-cov-id"),
                focus = true,
                paged = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Menu all flags", "menu menu-focus menu-paged zz-extra", closes = "</ul></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun menu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu size Xs", "menu menu-xs"))
    }

    @Test
    fun menu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu size Sm", "menu menu-sm"))
    }

    @Test
    fun menu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu size Md", "menu menu-md"))
    }

    @Test
    fun menu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu size Lg", "menu menu-lg"))
    }

    @Test
    fun menu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu size Xl", "menu menu-xl"))
    }

    @Test
    fun menu_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                direction = MenuDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu direction Vertical", "menu menu-vertical"))
    }

    @Test
    fun menu_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                direction = MenuDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Menu direction Horizontal", "menu menu-horizontal"))
    }

    @Test
    fun menuClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses()
        }
        assertRendered(html, Expected("MenuClasses defaults", "menu"))
    }

    @Test
    fun menuClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                focus = true,
                paged = true,
            )
        }
        val expected = Expected("MenuClasses all flags", "menu menu-focus menu-paged", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun menuClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Xs,
            )
        }
        assertRendered(html, Expected("MenuClasses size Xs", "menu menu-xs"))
    }

    @Test
    fun menuClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Sm,
            )
        }
        assertRendered(html, Expected("MenuClasses size Sm", "menu menu-sm"))
    }

    @Test
    fun menuClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Md,
            )
        }
        assertRendered(html, Expected("MenuClasses size Md", "menu menu-md"))
    }

    @Test
    fun menuClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Lg,
            )
        }
        assertRendered(html, Expected("MenuClasses size Lg", "menu menu-lg"))
    }

    @Test
    fun menuClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                size = MenuSize.Xl,
            )
        }
        assertRendered(html, Expected("MenuClasses size Xl", "menu menu-xl"))
    }

    @Test
    fun menuClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                direction = MenuDirection.Vertical,
            )
        }
        assertRendered(html, Expected("MenuClasses direction Vertical", "menu menu-vertical"))
    }

    @Test
    fun menuClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuClasses(
                direction = MenuDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("MenuClasses direction Horizontal", "menu menu-horizontal"))
    }

    @Test
    fun menuTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle()
        }
        assertRendered(html, Expected("MenuTitle defaults", "menu-title", closes = "</h2></div>"))
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
        val expected = Expected("MenuTitle all flags", "menu-title zz-extra", closes = "</h2></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun menuTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("MenuTitle text", "menu-title"))
        assertTrue(html.contains("txtmark"), "MenuTitle text content")
    }

    @Test
    fun menuTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitleClasses()
        }
        assertRendered(html, Expected("MenuTitleClasses defaults", "menu-title"))
    }

    @Test
    fun menuTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitleClasses()
        }
        val expected = Expected("MenuTitleClasses all flags", "menu-title", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun menuDropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(
                content = { },
            )
        }
        assertRendered(html, Expected("MenuDropdown defaults", "menu-dropdown", closes = "</ul></div>"))
    }

    @Test
    fun menuDropdown_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(
                id = htmlId("x-cov-id"),
                show = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MenuDropdown all flags", "menu-dropdown menu-dropdown-show zz-extra", closes = "</ul></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun menuDropdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownClasses()
        }
        assertRendered(html, Expected("MenuDropdownClasses defaults", "menu-dropdown"))
    }

    @Test
    fun menuDropdownClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownClasses(
                show = true,
            )
        }
        val expected = Expected("MenuDropdownClasses all flags", "menu-dropdown menu-dropdown-show", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun menuDropdownToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(
                content = { },
            )
        }
        assertRendered(html, Expected("MenuDropdownToggle defaults", "menu-dropdown-toggle", closes = "</span></div>"))
    }

    @Test
    fun menuDropdownToggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(
                id = htmlId("x-cov-id"),
                dropdownShow = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("MenuDropdownToggle all flags", "menu-dropdown-show menu-dropdown-toggle zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun menuDropdownToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggleClasses()
        }
        assertRendered(html, Expected("MenuDropdownToggleClasses defaults", "menu-dropdown-toggle"))
    }

    @Test
    fun menuDropdownToggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggleClasses(
                dropdownShow = true,
            )
        }
        val expected = Expected("MenuDropdownToggleClasses all flags", "menu-dropdown-show menu-dropdown-toggle", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun menuItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuItemClasses()
        }
        assertTrue(!html.contains("class=\""), "MenuItemClasses defaults emits no class")
    }

    @Test
    fun menuItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuItemClasses(
                active = true,
                disabled = true,
            )
        }
        val expected = Expected("MenuItemClasses all flags", "menu-active menu-disabled", content = false)
        assertRendered(html, expected)
    }
}
