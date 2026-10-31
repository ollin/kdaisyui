package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MaskCoverageTest {

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
    fun mask_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask()
        }
        assertRendered(html, Expected("Mask defaults", "mask"))
    }

    @Test
    fun mask_all_flags() {
        val html = allFlags(Expected("Mask all flags", "mask zz-extra", content = false)) { daisyMask(id = ID, extraClasses = EXTRA, attrs = ATTRS) }
    }

    @Test
    fun mask_style() = assertEachEntry("Mask style", mapOf(MaskStyle.Squircle to "mask mask-squircle", MaskStyle.Heart to "mask mask-heart", MaskStyle.Hexagon to "mask mask-hexagon", MaskStyle.Hexagon2 to "mask mask-hexagon-2", MaskStyle.Decagon to "mask mask-decagon", MaskStyle.Pentagon to "mask mask-pentagon", MaskStyle.Diamond to "mask mask-diamond", MaskStyle.Circle to "mask mask-circle", MaskStyle.Star to "mask mask-star", MaskStyle.Star2 to "mask mask-star-2", MaskStyle.Triangle to "mask mask-triangle", MaskStyle.Triangle2 to "mask mask-triangle-2", MaskStyle.Triangle3 to "mask mask-triangle-3", MaskStyle.Triangle4 to "mask mask-triangle-4")) { daisyMask(style = it) }

    @Test
    fun mask_half() = assertEachEntry("Mask half", mapOf(MaskHalf.Half1 to "mask mask-half-1", MaskHalf.Half2 to "mask mask-half-2")) { daisyMask(half = it) }

    @Test
    fun maskClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses()
        }
        assertRendered(html, Expected("MaskClasses defaults", "mask"))
    }

    @Test
    fun maskClasses_all_flags() = assertRenders(Expected("MaskClasses all flags", "mask", content = false)) { daisyMaskClasses() }

    @Test
    fun maskClasses_style() = assertEachEntry("MaskClasses style", mapOf(MaskStyle.Squircle to "mask mask-squircle", MaskStyle.Heart to "mask mask-heart", MaskStyle.Hexagon to "mask mask-hexagon", MaskStyle.Hexagon2 to "mask mask-hexagon-2", MaskStyle.Decagon to "mask mask-decagon", MaskStyle.Pentagon to "mask mask-pentagon", MaskStyle.Diamond to "mask mask-diamond", MaskStyle.Circle to "mask mask-circle", MaskStyle.Star to "mask mask-star", MaskStyle.Star2 to "mask mask-star-2", MaskStyle.Triangle to "mask mask-triangle", MaskStyle.Triangle2 to "mask mask-triangle-2", MaskStyle.Triangle3 to "mask mask-triangle-3", MaskStyle.Triangle4 to "mask mask-triangle-4")) { daisyMaskClasses(style = it) }

    @Test
    fun maskClasses_half() = assertEachEntry("MaskClasses half", mapOf(MaskHalf.Half1 to "mask mask-half-1", MaskHalf.Half2 to "mask mask-half-2")) { daisyMaskClasses(half = it) }
}
