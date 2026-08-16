package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MenuCoverageTest {

    @Test
    fun menu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu", actualClasses, "Menu defaults")
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
                horizontal = true,
                paged = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-active menu-disabled menu-dropdown-show menu-focus menu-horizontal menu-paged menu-vertical zz-extra", actualClasses, "Menu all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Menu id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Menu attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Menu content")
    }

    @Test
    fun menu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-xs", actualClasses, "Menu size Xs")
    }

    @Test
    fun menu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-sm", actualClasses, "Menu size Sm")
    }

    @Test
    fun menu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-md", actualClasses, "Menu size Md")
    }

    @Test
    fun menu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-lg", actualClasses, "Menu size Lg")
    }

    @Test
    fun menu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenu(
                size = MenuSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu menu-xl", actualClasses, "Menu size Xl")
    }

    @Test
    fun menuTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-title", actualClasses, "MenuTitle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-title zz-extra", actualClasses, "MenuTitle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MenuTitle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MenuTitle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MenuTitle content")
    }

    @Test
    fun menuTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-title", actualClasses, "MenuTitle text")
        assertTrue(html.contains("txtmark"), "MenuTitle text content")
    }

    @Test
    fun menuDropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdown(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-dropdown", actualClasses, "MenuDropdown defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-dropdown zz-extra", actualClasses, "MenuDropdown all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MenuDropdown id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MenuDropdown attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MenuDropdown content")
    }

    @Test
    fun menuDropdownToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuDropdownToggle(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-dropdown-toggle", actualClasses, "MenuDropdownToggle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("menu-dropdown-toggle zz-extra", actualClasses, "MenuDropdownToggle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MenuDropdownToggle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MenuDropdownToggle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MenuDropdownToggle content")
    }
}
