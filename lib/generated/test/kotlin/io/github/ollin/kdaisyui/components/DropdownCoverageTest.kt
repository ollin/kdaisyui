package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DropdownCoverageTest {

    @Test
    fun dropdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dropdown", actualClasses, "Dropdown defaults")
    }

    @Test
    fun dropdown_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdown(
                id = htmlId("x-cov-id"),
                close = true,
                hover = true,
                open = true,
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dropdown dropdown-bottom dropdown-center dropdown-close dropdown-end dropdown-hover dropdown-left dropdown-open dropdown-right dropdown-start dropdown-top zz-extra", actualClasses, "Dropdown all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Dropdown id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Dropdown attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Dropdown content")
    }

    @Test
    fun dropdownContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownContent(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dropdown-content", actualClasses, "DropdownContent defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dropdown-content zz-extra", actualClasses, "DropdownContent all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DropdownContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DropdownContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DropdownContent content")
    }
}
