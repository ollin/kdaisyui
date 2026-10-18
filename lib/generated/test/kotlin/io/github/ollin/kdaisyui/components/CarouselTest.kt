package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a

class CarouselTest {

    @Test
    fun snap_to_start_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        val expectedClasses = "carousel"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to start (default)")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Snap to start (default)")
    }

    @Test
    fun snap_to_start_default_daisyCarouselItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItem() {
            }
        }
        val expectedClasses = "carousel-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to start (default)")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Snap to start (default)")
    }

    @Test
    fun snap_to_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(snap = CarouselSnap.Center) {
            }
        }
        val expectedClasses = "carousel carousel-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to center")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Snap to center")
    }

    @Test
    fun snap_to_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(snap = CarouselSnap.End) {
            }
        }
        val expectedClasses = "carousel carousel-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to end")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Snap to end")
    }

    @Test
    fun vertical_carousel() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(direction = CarouselDirection.Vertical) {
            }
        }
        val expectedClasses = "carousel carousel-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical carousel")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Vertical carousel")
    }

    @Test
    fun carousel_with_indicator_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(size = ButtonSize.Xs) }
        }
        val expectedClasses = "btn btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with indicator buttons")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Carousel with indicator buttons")
    }

    @Test
    fun carousel_with_next_prev_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(shape = ButtonShape.Circle) }
        }
        val expectedClasses = "btn btn-circle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with next/prev buttons")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Carousel with next/prev buttons")
    }
}
