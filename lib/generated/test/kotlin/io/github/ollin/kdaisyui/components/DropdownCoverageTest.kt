package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DropdownCoverageTest {

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

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun dropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown defaults", "dropdown", closes = "</details></div>"))
    }

    @Test
    fun dropdown_all_flags() {
        val html = allFlags(Expected("Dropdown all flags", "dropdown dropdown-hover zz-extra", closes = "</details></div>")) { daisyDropdown(id = ID, hover = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dropdown_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown side Top", "dropdown dropdown-top"))
    }

    @Test
    fun dropdown_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown side Bottom", "dropdown dropdown-bottom"))
    }

    @Test
    fun dropdown_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Left,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown side Left", "dropdown dropdown-left"))
    }

    @Test
    fun dropdown_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Right,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown side Right", "dropdown dropdown-right"))
    }

    @Test
    fun dropdown_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown align Start", "dropdown dropdown-start"))
    }

    @Test
    fun dropdown_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.Center,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown align Center", "dropdown dropdown-center"))
    }

    @Test
    fun dropdown_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown align End", "dropdown dropdown-end"))
    }

    @Test
    fun dropdown_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                state = DropdownState.Open,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown state Open", "dropdown dropdown-open"))
    }

    @Test
    fun dropdown_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                state = DropdownState.Close,
                content = { },
            )
        }
        assertRendered(html, Expected("Dropdown state Close", "dropdown dropdown-close"))
    }

    @Test
    fun dropdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses()
        }
        assertRendered(html, Expected("DropdownClasses defaults", "dropdown"))
    }

    @Test
    fun dropdownClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                hover = true,
            )
        }
        val expected = Expected("DropdownClasses all flags", "dropdown dropdown-hover", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun dropdownClasses_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Top,
            )
        }
        assertRendered(html, Expected("DropdownClasses side Top", "dropdown dropdown-top"))
    }

    @Test
    fun dropdownClasses_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Bottom,
            )
        }
        assertRendered(html, Expected("DropdownClasses side Bottom", "dropdown dropdown-bottom"))
    }

    @Test
    fun dropdownClasses_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Left,
            )
        }
        assertRendered(html, Expected("DropdownClasses side Left", "dropdown dropdown-left"))
    }

    @Test
    fun dropdownClasses_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Right,
            )
        }
        assertRendered(html, Expected("DropdownClasses side Right", "dropdown dropdown-right"))
    }

    @Test
    fun dropdownClasses_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.Start,
            )
        }
        assertRendered(html, Expected("DropdownClasses align Start", "dropdown dropdown-start"))
    }

    @Test
    fun dropdownClasses_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.Center,
            )
        }
        assertRendered(html, Expected("DropdownClasses align Center", "dropdown dropdown-center"))
    }

    @Test
    fun dropdownClasses_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.End,
            )
        }
        assertRendered(html, Expected("DropdownClasses align End", "dropdown dropdown-end"))
    }

    @Test
    fun dropdownClasses_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                state = DropdownState.Open,
            )
        }
        assertRendered(html, Expected("DropdownClasses state Open", "dropdown dropdown-open"))
    }

    @Test
    fun dropdownClasses_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                state = DropdownState.Close,
            )
        }
        assertRendered(html, Expected("DropdownClasses state Close", "dropdown dropdown-close"))
    }

    @Test
    fun dropdownPopover_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover defaults", "dropdown", closes = "</ul></div>"))
    }

    @Test
    fun dropdownPopover_all_flags() {
        val html = allFlags(Expected("DropdownPopover all flags", "dropdown zz-extra", closes = "</ul></div>")) { daisyDropdownPopover(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dropdownPopover_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                side = DropdownSide.Top,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover side Top", "dropdown dropdown-top"))
    }

    @Test
    fun dropdownPopover_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                side = DropdownSide.Bottom,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover side Bottom", "dropdown dropdown-bottom"))
    }

    @Test
    fun dropdownPopover_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                side = DropdownSide.Left,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover side Left", "dropdown dropdown-left"))
    }

    @Test
    fun dropdownPopover_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                side = DropdownSide.Right,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover side Right", "dropdown dropdown-right"))
    }

    @Test
    fun dropdownPopover_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                align = DropdownAlign.Start,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover align Start", "dropdown dropdown-start"))
    }

    @Test
    fun dropdownPopover_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                align = DropdownAlign.Center,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover align Center", "dropdown dropdown-center"))
    }

    @Test
    fun dropdownPopover_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopover(
                align = DropdownAlign.End,
                id = htmlId("x-cov-id"),
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownPopover align End", "dropdown dropdown-end"))
    }

    @Test
    fun dropdownPopoverClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses()
        }
        assertRendered(html, Expected("DropdownPopoverClasses defaults", "dropdown"))
    }

    @Test
    fun dropdownPopoverClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses()
        }
        val expected = Expected("DropdownPopoverClasses all flags", "dropdown", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun dropdownPopoverClasses_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                side = DropdownSide.Top,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses side Top", "dropdown dropdown-top"))
    }

    @Test
    fun dropdownPopoverClasses_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                side = DropdownSide.Bottom,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses side Bottom", "dropdown dropdown-bottom"))
    }

    @Test
    fun dropdownPopoverClasses_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                side = DropdownSide.Left,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses side Left", "dropdown dropdown-left"))
    }

    @Test
    fun dropdownPopoverClasses_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                side = DropdownSide.Right,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses side Right", "dropdown dropdown-right"))
    }

    @Test
    fun dropdownPopoverClasses_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                align = DropdownAlign.Start,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses align Start", "dropdown dropdown-start"))
    }

    @Test
    fun dropdownPopoverClasses_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                align = DropdownAlign.Center,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses align Center", "dropdown dropdown-center"))
    }

    @Test
    fun dropdownPopoverClasses_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses(
                align = DropdownAlign.End,
            )
        }
        assertRendered(html, Expected("DropdownPopoverClasses align End", "dropdown dropdown-end"))
    }

    @Test
    fun dropdownContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(
                content = { },
            )
        }
        assertRendered(html, Expected("DropdownContent defaults", "dropdown-content", closes = "</ul></div>"))
    }

    @Test
    fun dropdownContent_all_flags() {
        val html = allFlags(Expected("DropdownContent all flags", "dropdown-content zz-extra", closes = "</ul></div>")) { daisyDropdownContent(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun dropdownContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContentClasses()
        }
        assertRendered(html, Expected("DropdownContentClasses defaults", "dropdown-content"))
    }

    @Test
    fun dropdownContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContentClasses()
        }
        val expected = Expected("DropdownContentClasses all flags", "dropdown-content", content = false)
        assertRendered(html, expected)
    }
}
