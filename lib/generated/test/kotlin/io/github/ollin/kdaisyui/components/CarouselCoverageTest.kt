package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CarouselCoverageTest {

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
    fun carousel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel defaults", "carousel", closes = "</div></div>"))
    }

    @Test
    fun carousel_all_flags() {
        val html = allFlags(Expected("Carousel all flags", "carousel zz-extra", closes = "</div></div>")) { daisyCarousel(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun carousel_snap() = assertEachEntry("Carousel snap", mapOf(CarouselSnap.Start to "carousel carousel-start", CarouselSnap.Center to "carousel carousel-center", CarouselSnap.End to "carousel carousel-end")) { daisyCarousel(snap = it, content = { }) }

    @Test
    fun carousel_direction() = assertEachEntry("Carousel direction", mapOf(CarouselDirection.Horizontal to "carousel carousel-horizontal", CarouselDirection.Vertical to "carousel carousel-vertical")) { daisyCarousel(direction = it, content = { }) }

    @Test
    fun carouselClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses()
        }
        assertRendered(html, Expected("CarouselClasses defaults", "carousel"))
    }

    @Test
    fun carouselClasses_all_flags() = assertRenders(Expected("CarouselClasses all flags", "carousel", content = false)) { daisyCarouselClasses() }

    @Test
    fun carouselClasses_snap() = assertEachEntry("CarouselClasses snap", mapOf(CarouselSnap.Start to "carousel carousel-start", CarouselSnap.Center to "carousel carousel-center", CarouselSnap.End to "carousel carousel-end")) { daisyCarouselClasses(snap = it) }

    @Test
    fun carouselClasses_direction() = assertEachEntry("CarouselClasses direction", mapOf(CarouselDirection.Horizontal to "carousel carousel-horizontal", CarouselDirection.Vertical to "carousel carousel-vertical")) { daisyCarouselClasses(direction = it) }

    @Test
    fun carouselItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItem(
                content = { },
            )
        }
        assertRendered(html, Expected("CarouselItem defaults", "carousel-item", closes = "</div></div>"))
    }

    @Test
    fun carouselItem_all_flags() {
        val html = allFlags(Expected("CarouselItem all flags", "carousel-item zz-extra", closes = "</div></div>")) { daisyCarouselItem(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun carouselItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItemClasses()
        }
        assertRendered(html, Expected("CarouselItemClasses defaults", "carousel-item"))
    }

    @Test
    fun carouselItemClasses_all_flags() = assertRenders(Expected("CarouselItemClasses all flags", "carousel-item", content = false)) { daisyCarouselItemClasses() }
}
