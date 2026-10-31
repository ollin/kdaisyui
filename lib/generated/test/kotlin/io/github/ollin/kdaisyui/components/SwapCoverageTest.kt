package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SwapCoverageTest {

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
    fun swap_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwap(
                content = { },
            )
        }
        assertRendered(html, Expected("Swap defaults", "swap", closes = "</label></div>"))
    }

    @Test
    fun swap_all_flags() {
        val html = allFlags(Expected("Swap all flags", "swap swap-active zz-extra", closes = "</label></div>")) { daisySwap(id = ID, active = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun swap_style() = assertEachEntry("Swap style", mapOf(SwapStyle.Rotate to "swap swap-rotate", SwapStyle.Flip to "swap swap-flip")) { daisySwap(style = it, content = { }) }

    @Test
    fun swapClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapClasses()
        }
        assertRendered(html, Expected("SwapClasses defaults", "swap"))
    }

    @Test
    fun swapClasses_all_flags() = assertRenders(Expected("SwapClasses all flags", "swap swap-active", content = false)) { daisySwapClasses(active = true) }

    @Test
    fun swapClasses_style() = assertEachEntry("SwapClasses style", mapOf(SwapStyle.Rotate to "swap swap-rotate", SwapStyle.Flip to "swap swap-flip")) { daisySwapClasses(style = it) }

    @Test
    fun swapOn_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOn(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapOn defaults", "swap-on", closes = "</div></div>"))
    }

    @Test
    fun swapOn_all_flags() {
        val html = allFlags(Expected("SwapOn all flags", "swap-on zz-extra", closes = "</div></div>")) { daisySwapOn(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun swapOnClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOnClasses()
        }
        assertRendered(html, Expected("SwapOnClasses defaults", "swap-on"))
    }

    @Test
    fun swapOnClasses_all_flags() = assertRenders(Expected("SwapOnClasses all flags", "swap-on", content = false)) { daisySwapOnClasses() }

    @Test
    fun swapOff_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOff(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapOff defaults", "swap-off", closes = "</div></div>"))
    }

    @Test
    fun swapOff_all_flags() {
        val html = allFlags(Expected("SwapOff all flags", "swap-off zz-extra", closes = "</div></div>")) { daisySwapOff(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun swapOffClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapOffClasses()
        }
        assertRendered(html, Expected("SwapOffClasses defaults", "swap-off"))
    }

    @Test
    fun swapOffClasses_all_flags() = assertRenders(Expected("SwapOffClasses all flags", "swap-off", content = false)) { daisySwapOffClasses() }

    @Test
    fun swapIndeterminate_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminate(
                content = { },
            )
        }
        assertRendered(html, Expected("SwapIndeterminate defaults", "swap-indeterminate", closes = "</div></div>"))
    }

    @Test
    fun swapIndeterminate_all_flags() {
        val html = allFlags(Expected("SwapIndeterminate all flags", "swap-indeterminate zz-extra", closes = "</div></div>")) { daisySwapIndeterminate(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun swapIndeterminateClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySwapIndeterminateClasses()
        }
        assertRendered(html, Expected("SwapIndeterminateClasses defaults", "swap-indeterminate"))
    }

    @Test
    fun swapIndeterminateClasses_all_flags() = assertRenders(Expected("SwapIndeterminateClasses all flags", "swap-indeterminate", content = false)) { daisySwapIndeterminateClasses() }
}
