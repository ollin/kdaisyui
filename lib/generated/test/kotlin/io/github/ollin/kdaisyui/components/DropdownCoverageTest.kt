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
                close = true,
                end = true,
                start = true,
                top = true,
                bottom = true,
                left = true,
                right = true,
                center = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "dropdown dropdown-bottom dropdown-center dropdown-close dropdown-end dropdown-left dropdown-right dropdown-start dropdown-top zz-extra", "Dropdown all flags", closes = "</details></div>")
        assertCommonFlags(html, "Dropdown")
    }

    @Test
    fun dropdown_modifier_hover() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                modifier = DropdownModifier.Hover,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-hover", "Dropdown modifier Hover")
    }

    @Test
    fun dropdown_modifier_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                modifier = DropdownModifier.Open,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-open", "Dropdown modifier Open")
    }

    @Test
    fun dropdown_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                horizontalPlacement = DropdownHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-start", "Dropdown horizontalPlacement Start")
    }

    @Test
    fun dropdown_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                horizontalPlacement = DropdownHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-center", "Dropdown horizontalPlacement Center")
    }

    @Test
    fun dropdown_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                horizontalPlacement = DropdownHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-end", "Dropdown horizontalPlacement End")
    }

    @Test
    fun dropdown_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                verticalPlacement = DropdownVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-top", "Dropdown verticalPlacement Top")
    }

    @Test
    fun dropdown_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                verticalPlacement = DropdownVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "dropdown dropdown-bottom", "Dropdown verticalPlacement Bottom")
    }

    @Test
    fun dropdownContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(
                content = { },
            )
        }
        assertRendered(html, "dropdown-content", "DropdownContent defaults", closes = "</div></div>")
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
        assertRendered(html, "dropdown-content zz-extra", "DropdownContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "DropdownContent")
    }
}
