package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ThemeControllerCoverageTest {

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
    fun themeController_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController()
        }
        assertRendered(html, Expected("ThemeController defaults", "theme-controller"))
    }

    @Test
    fun themeController_all_flags() {
        val html = allFlags(Expected("ThemeController all flags", "theme-controller zz-extra", content = false)) { daisyThemeController(id = ID, extraClasses = EXTRA, attrs = ATTRS) }
    }

    @Test
    fun themeControllerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeControllerClasses()
        }
        assertRendered(html, Expected("ThemeControllerClasses defaults", "theme-controller"))
    }

    @Test
    fun themeControllerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeControllerClasses()
        }
        val expected = Expected("ThemeControllerClasses all flags", "theme-controller", content = false)
        assertRendered(html, expected)
    }
}
