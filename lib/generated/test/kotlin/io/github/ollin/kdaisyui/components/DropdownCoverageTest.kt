package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DropdownCoverageTest {

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
    fun dropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                content = { },
            )
        }
        assertRendered(html, "dropdown", "Dropdown defaults", closes = "</details></div>")
    }

    @Test
    fun dropdown_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                id = htmlId("x-cov-id"),
                hover = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "dropdown dropdown-hover zz-extra", "Dropdown all flags", closes = "</details></div>")
        assertCommonFlags(html, "Dropdown")
    }

    @Test
    fun dropdown_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Top,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-top", "Dropdown side Top")
    }

    @Test
    fun dropdown_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Bottom,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-bottom", "Dropdown side Bottom")
    }

    @Test
    fun dropdown_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Left,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-left", "Dropdown side Left")
    }

    @Test
    fun dropdown_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                side = DropdownSide.Right,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-right", "Dropdown side Right")
    }

    @Test
    fun dropdown_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.Start,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-start", "Dropdown align Start")
    }

    @Test
    fun dropdown_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.Center,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-center", "Dropdown align Center")
    }

    @Test
    fun dropdown_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                align = DropdownAlign.End,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-end", "Dropdown align End")
    }

    @Test
    fun dropdown_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                state = DropdownState.Open,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-open", "Dropdown state Open")
    }

    @Test
    fun dropdown_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                state = DropdownState.Close,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-close", "Dropdown state Close")
    }

    @Test
    fun dropdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses()
        }
        assertRendered(html, "dropdown", "DropdownClasses defaults")
    }

    @Test
    fun dropdownClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                hover = true,
            )
        }
        assertRendered(html, "dropdown dropdown-hover", "DropdownClasses all flags")
    }

    @Test
    fun dropdownClasses_side_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Top,
            )
        }
        assertRendered(html, "dropdown dropdown-top", "DropdownClasses side Top")
    }

    @Test
    fun dropdownClasses_side_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Bottom,
            )
        }
        assertRendered(html, "dropdown dropdown-bottom", "DropdownClasses side Bottom")
    }

    @Test
    fun dropdownClasses_side_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Left,
            )
        }
        assertRendered(html, "dropdown dropdown-left", "DropdownClasses side Left")
    }

    @Test
    fun dropdownClasses_side_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                side = DropdownSide.Right,
            )
        }
        assertRendered(html, "dropdown dropdown-right", "DropdownClasses side Right")
    }

    @Test
    fun dropdownClasses_align_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.Start,
            )
        }
        assertRendered(html, "dropdown dropdown-start", "DropdownClasses align Start")
    }

    @Test
    fun dropdownClasses_align_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.Center,
            )
        }
        assertRendered(html, "dropdown dropdown-center", "DropdownClasses align Center")
    }

    @Test
    fun dropdownClasses_align_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                align = DropdownAlign.End,
            )
        }
        assertRendered(html, "dropdown dropdown-end", "DropdownClasses align End")
    }

    @Test
    fun dropdownClasses_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                state = DropdownState.Open,
            )
        }
        assertRendered(html, "dropdown dropdown-open", "DropdownClasses state Open")
    }

    @Test
    fun dropdownClasses_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses(
                state = DropdownState.Close,
            )
        }
        assertRendered(html, "dropdown dropdown-close", "DropdownClasses state Close")
    }

    @Test
    fun dropdownContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(
                content = { },
            )
        }
        assertRendered(html, "dropdown-content", "DropdownContent defaults", closes = "</ul></div>")
    }

    @Test
    fun dropdownContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "dropdown-content zz-extra", "DropdownContent all flags", closes = "</ul></div>")
        assertCommonFlags(html, "DropdownContent")
    }

    @Test
    fun dropdownContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContentClasses()
        }
        assertRendered(html, "dropdown-content", "DropdownContentClasses defaults")
    }

    @Test
    fun dropdownContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContentClasses()
        }
        assertRendered(html, "dropdown-content", "DropdownContentClasses all flags")
    }
}
