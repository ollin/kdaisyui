package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class LoadingCoverageTest {

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
    fun loading_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(
                content = { },
            )
        }
        assertRendered(html, Expected("Loading defaults", "loading", closes = "</span></div>"))
    }

    @Test
    fun loading_all_flags() {
        val html = allFlags(Expected("Loading all flags", "loading zz-extra", closes = "</span></div>")) { daisyLoading(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun loading_size() = assertEachEntry("Loading size", mapOf(LoadingSize.Xs to "loading loading-xs", LoadingSize.Sm to "loading loading-sm", LoadingSize.Md to "loading loading-md", LoadingSize.Lg to "loading loading-lg", LoadingSize.Xl to "loading loading-xl")) { daisyLoading(size = it, content = { }) }

    @Test
    fun loading_style() = assertEachEntry("Loading style", mapOf(LoadingStyle.Spinner to "loading loading-spinner", LoadingStyle.Dots to "loading loading-dots", LoadingStyle.Ring to "loading loading-ring", LoadingStyle.Ball to "loading loading-ball", LoadingStyle.Bars to "loading loading-bars", LoadingStyle.Infinity to "loading loading-infinity")) { daisyLoading(style = it, content = { }) }

    @Test
    fun loadingClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoadingClasses()
        }
        assertRendered(html, Expected("LoadingClasses defaults", "loading"))
    }

    @Test
    fun loadingClasses_all_flags() = assertRenders(Expected("LoadingClasses all flags", "loading", content = false)) { daisyLoadingClasses() }

    @Test
    fun loadingClasses_size() = assertEachEntry("LoadingClasses size", mapOf(LoadingSize.Xs to "loading loading-xs", LoadingSize.Sm to "loading loading-sm", LoadingSize.Md to "loading loading-md", LoadingSize.Lg to "loading loading-lg", LoadingSize.Xl to "loading loading-xl")) { daisyLoadingClasses(size = it) }

    @Test
    fun loadingClasses_style() = assertEachEntry("LoadingClasses style", mapOf(LoadingStyle.Spinner to "loading loading-spinner", LoadingStyle.Dots to "loading loading-dots", LoadingStyle.Ring to "loading loading-ring", LoadingStyle.Ball to "loading loading-ball", LoadingStyle.Bars to "loading loading-bars", LoadingStyle.Infinity to "loading loading-infinity")) { daisyLoadingClasses(style = it) }
}
