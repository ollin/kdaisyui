package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MenuCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
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
        val html = allFlags(Expected("Menu all flags", "menu menu-focus menu-paged zz-extra", closes = "</ul></div>")) { daisyMenu(id = ID, focus = true, paged = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun menu_size() = assertEachEntry("Menu size", mapOf(MenuSize.Xs to "menu menu-xs", MenuSize.Sm to "menu menu-sm", MenuSize.Md to "menu menu-md", MenuSize.Lg to "menu menu-lg", MenuSize.Xl to "menu menu-xl")) { daisyMenu(size = it, content = { }) }

    @Test
    fun menu_direction() = assertEachEntry("Menu direction", mapOf(MenuDirection.Vertical to "menu menu-vertical", MenuDirection.Horizontal to "menu menu-horizontal")) { daisyMenu(direction = it, content = { }) }

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
    fun menuClasses_size() = assertEachEntry("MenuClasses size", mapOf(MenuSize.Xs to "menu menu-xs", MenuSize.Sm to "menu menu-sm", MenuSize.Md to "menu menu-md", MenuSize.Lg to "menu menu-lg", MenuSize.Xl to "menu menu-xl")) { daisyMenuClasses(size = it) }

    @Test
    fun menuClasses_direction() = assertEachEntry("MenuClasses direction", mapOf(MenuDirection.Vertical to "menu menu-vertical", MenuDirection.Horizontal to "menu menu-horizontal")) { daisyMenuClasses(direction = it) }

    @Test
    fun menuTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMenuTitle()
        }
        assertRendered(html, Expected("MenuTitle defaults", "menu-title", closes = "</h2></div>"))
    }

    @Test
    fun menuTitle_all_flags() {
        val html = allFlags(Expected("MenuTitle all flags", "menu-title zz-extra", closes = "</h2></div>")) { daisyMenuTitle(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
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
        val html = allFlags(Expected("MenuDropdown all flags", "menu-dropdown menu-dropdown-show zz-extra", closes = "</ul></div>")) { daisyMenuDropdown(id = ID, show = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
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
        val html = allFlags(Expected("MenuDropdownToggle all flags", "menu-dropdown-show menu-dropdown-toggle zz-extra", closes = "</span></div>")) { daisyMenuDropdownToggle(id = ID, dropdownShow = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
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
