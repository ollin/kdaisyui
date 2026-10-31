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

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
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
    fun carousel_snap_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel snap Start", "carousel carousel-start"))
    }

    @Test
    fun carousel_snap_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.Center,
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel snap Center", "carousel carousel-center"))
    }

    @Test
    fun carousel_snap_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel snap End", "carousel carousel-end"))
    }

    @Test
    fun carousel_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                direction = CarouselDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel direction Horizontal", "carousel carousel-horizontal"))
    }

    @Test
    fun carousel_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                direction = CarouselDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Carousel direction Vertical", "carousel carousel-vertical"))
    }

    @Test
    fun carouselClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses()
        }
        assertRendered(html, Expected("CarouselClasses defaults", "carousel"))
    }

    @Test
    fun carouselClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses()
        }
        val expected = Expected("CarouselClasses all flags", "carousel", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun carouselClasses_snap_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.Start,
            )
        }
        assertRendered(html, Expected("CarouselClasses snap Start", "carousel carousel-start"))
    }

    @Test
    fun carouselClasses_snap_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.Center,
            )
        }
        assertRendered(html, Expected("CarouselClasses snap Center", "carousel carousel-center"))
    }

    @Test
    fun carouselClasses_snap_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.End,
            )
        }
        assertRendered(html, Expected("CarouselClasses snap End", "carousel carousel-end"))
    }

    @Test
    fun carouselClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                direction = CarouselDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("CarouselClasses direction Horizontal", "carousel carousel-horizontal"))
    }

    @Test
    fun carouselClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                direction = CarouselDirection.Vertical,
            )
        }
        assertRendered(html, Expected("CarouselClasses direction Vertical", "carousel carousel-vertical"))
    }

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
    fun carouselItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItemClasses()
        }
        val expected = Expected("CarouselItemClasses all flags", "carousel-item", content = false)
        assertRendered(html, expected)
    }
}
