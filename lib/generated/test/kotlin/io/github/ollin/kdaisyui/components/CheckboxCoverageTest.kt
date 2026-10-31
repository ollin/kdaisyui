package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CheckboxCoverageTest {

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
    fun checkbox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertRendered(html, Expected("Checkbox defaults", "checkbox"))
        assertTrue(html.contains("type=\""), "Checkbox sets type")
    }

    @Test
    fun checkbox_all_flags() {
        val html = allFlags(Expected("Checkbox all flags", "checkbox zz-extra", content = false)) { daisyCheckbox(id = ID, checked = true, disabled = true, extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("type=\""), "Checkbox sets type")
        assertTrue(html.contains("checked=\""), "Checkbox sets checked")
        assertTrue(html.contains("disabled=\""), "Checkbox sets disabled")
    }

    @Test
    fun checkbox_color() = assertEachEntry("Checkbox color", mapOf(CheckboxColor.Primary to "checkbox checkbox-primary", CheckboxColor.Secondary to "checkbox checkbox-secondary", CheckboxColor.Accent to "checkbox checkbox-accent", CheckboxColor.Neutral to "checkbox checkbox-neutral", CheckboxColor.Success to "checkbox checkbox-success", CheckboxColor.Warning to "checkbox checkbox-warning", CheckboxColor.Info to "checkbox checkbox-info", CheckboxColor.Error to "checkbox checkbox-error")) { daisyCheckbox(color = it) }

    @Test
    fun checkbox_size() = assertEachEntry("Checkbox size", mapOf(CheckboxSize.Xs to "checkbox checkbox-xs", CheckboxSize.Sm to "checkbox checkbox-sm", CheckboxSize.Md to "checkbox checkbox-md", CheckboxSize.Lg to "checkbox checkbox-lg", CheckboxSize.Xl to "checkbox checkbox-xl")) { daisyCheckbox(size = it) }

    @Test
    fun checkboxClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        assertRendered(html, Expected("CheckboxClasses defaults", "checkbox"))
    }

    @Test
    fun checkboxClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        val expected = Expected("CheckboxClasses all flags", "checkbox", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun checkboxClasses_color() = assertEachEntry("CheckboxClasses color", mapOf(CheckboxColor.Primary to "checkbox checkbox-primary", CheckboxColor.Secondary to "checkbox checkbox-secondary", CheckboxColor.Accent to "checkbox checkbox-accent", CheckboxColor.Neutral to "checkbox checkbox-neutral", CheckboxColor.Success to "checkbox checkbox-success", CheckboxColor.Warning to "checkbox checkbox-warning", CheckboxColor.Info to "checkbox checkbox-info", CheckboxColor.Error to "checkbox checkbox-error")) { daisyCheckboxClasses(color = it) }

    @Test
    fun checkboxClasses_size() = assertEachEntry("CheckboxClasses size", mapOf(CheckboxSize.Xs to "checkbox checkbox-xs", CheckboxSize.Sm to "checkbox checkbox-sm", CheckboxSize.Md to "checkbox checkbox-md", CheckboxSize.Lg to "checkbox checkbox-lg", CheckboxSize.Xl to "checkbox checkbox-xl")) { daisyCheckboxClasses(size = it) }
}
