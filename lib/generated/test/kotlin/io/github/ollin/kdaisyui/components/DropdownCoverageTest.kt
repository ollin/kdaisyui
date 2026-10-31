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

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

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
    fun dropdown_side() = assertEachEntry("Dropdown side", mapOf(DropdownSide.Top to "dropdown dropdown-top", DropdownSide.Bottom to "dropdown dropdown-bottom", DropdownSide.Left to "dropdown dropdown-left", DropdownSide.Right to "dropdown dropdown-right")) { daisyDropdown(side = it, content = { }) }

    @Test
    fun dropdown_align() = assertEachEntry("Dropdown align", mapOf(DropdownAlign.Start to "dropdown dropdown-start", DropdownAlign.Center to "dropdown dropdown-center", DropdownAlign.End to "dropdown dropdown-end")) { daisyDropdown(align = it, content = { }) }

    @Test
    fun dropdown_state() = assertEachEntry("Dropdown state", mapOf(DropdownState.Open to "dropdown dropdown-open", DropdownState.Close to "dropdown dropdown-close")) { daisyDropdown(state = it, content = { }) }

    @Test
    fun dropdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownClasses()
        }
        assertRendered(html, Expected("DropdownClasses defaults", "dropdown"))
    }

    @Test
    fun dropdownClasses_all_flags() = assertRenders(Expected("DropdownClasses all flags", "dropdown dropdown-hover", content = false)) { daisyDropdownClasses(hover = true) }

    @Test
    fun dropdownClasses_side() = assertEachEntry("DropdownClasses side", mapOf(DropdownSide.Top to "dropdown dropdown-top", DropdownSide.Bottom to "dropdown dropdown-bottom", DropdownSide.Left to "dropdown dropdown-left", DropdownSide.Right to "dropdown dropdown-right")) { daisyDropdownClasses(side = it) }

    @Test
    fun dropdownClasses_align() = assertEachEntry("DropdownClasses align", mapOf(DropdownAlign.Start to "dropdown dropdown-start", DropdownAlign.Center to "dropdown dropdown-center", DropdownAlign.End to "dropdown dropdown-end")) { daisyDropdownClasses(align = it) }

    @Test
    fun dropdownClasses_state() = assertEachEntry("DropdownClasses state", mapOf(DropdownState.Open to "dropdown dropdown-open", DropdownState.Close to "dropdown dropdown-close")) { daisyDropdownClasses(state = it) }

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
    fun dropdownPopover_side() = assertEachEntry("DropdownPopover side", mapOf(DropdownSide.Top to "dropdown dropdown-top", DropdownSide.Bottom to "dropdown dropdown-bottom", DropdownSide.Left to "dropdown dropdown-left", DropdownSide.Right to "dropdown dropdown-right")) { daisyDropdownPopover(side = it, id = htmlId("x-cov-id"), content = { }) }

    @Test
    fun dropdownPopover_align() = assertEachEntry("DropdownPopover align", mapOf(DropdownAlign.Start to "dropdown dropdown-start", DropdownAlign.Center to "dropdown dropdown-center", DropdownAlign.End to "dropdown dropdown-end")) { daisyDropdownPopover(align = it, id = htmlId("x-cov-id"), content = { }) }

    @Test
    fun dropdownPopoverClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDropdownPopoverClasses()
        }
        assertRendered(html, Expected("DropdownPopoverClasses defaults", "dropdown"))
    }

    @Test
    fun dropdownPopoverClasses_all_flags() = assertRenders(Expected("DropdownPopoverClasses all flags", "dropdown", content = false)) { daisyDropdownPopoverClasses() }

    @Test
    fun dropdownPopoverClasses_side() = assertEachEntry("DropdownPopoverClasses side", mapOf(DropdownSide.Top to "dropdown dropdown-top", DropdownSide.Bottom to "dropdown dropdown-bottom", DropdownSide.Left to "dropdown dropdown-left", DropdownSide.Right to "dropdown dropdown-right")) { daisyDropdownPopoverClasses(side = it) }

    @Test
    fun dropdownPopoverClasses_align() = assertEachEntry("DropdownPopoverClasses align", mapOf(DropdownAlign.Start to "dropdown dropdown-start", DropdownAlign.Center to "dropdown dropdown-center", DropdownAlign.End to "dropdown dropdown-end")) { daisyDropdownPopoverClasses(align = it) }

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
    fun dropdownContentClasses_all_flags() = assertRenders(Expected("DropdownContentClasses all flags", "dropdown-content", content = false)) { daisyDropdownContentClasses() }
}
