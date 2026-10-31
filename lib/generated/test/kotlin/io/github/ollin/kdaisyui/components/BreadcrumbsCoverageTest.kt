package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

class BreadcrumbsCoverageTest {

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
    fun breadcrumbs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs(
                content = { },
            )
        }
        assertRendered(html, Expected("Breadcrumbs defaults", "breadcrumbs", closes = "</div></div>"))
    }

    @Test
    fun breadcrumbs_all_flags() {
        val html = allFlags(Expected("Breadcrumbs all flags", "breadcrumbs zz-extra", closes = "</div></div>")) { daisyBreadcrumbs(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun breadcrumbsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsClasses()
        }
        assertRendered(html, Expected("BreadcrumbsClasses defaults", "breadcrumbs"))
    }

    @Test
    fun breadcrumbsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsClasses()
        }
        val expected = Expected("BreadcrumbsClasses all flags", "breadcrumbs", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun breadcrumbsItems_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsItems(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "BreadcrumbsItems defaults emits no class")
        assertTrue(html.endsWith("</ul></div>"), "BreadcrumbsItems closes")
    }

    @Test
    fun breadcrumbsItems_all_flags() {
        val html = allFlags(Expected("BreadcrumbsItems all flags", "zz-extra", closes = "</ul></div>")) { daisyBreadcrumbsItems(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun breadcrumbsItem_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyBreadcrumbsItem(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "BreadcrumbsItem defaults emits no class")
        assertTrue(html.endsWith("</li></ul>"), "BreadcrumbsItem closes")
    }

    @Test
    fun breadcrumbsItem_all_flags() {
        val html = createHTML(prettyPrint = false).ul {
            daisyBreadcrumbsItem(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("BreadcrumbsItem all flags", "zz-extra", closes = "</li></ul>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }
}
