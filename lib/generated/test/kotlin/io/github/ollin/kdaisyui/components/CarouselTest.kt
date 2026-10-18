package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a

class CarouselTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun snap_to_start_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        assertDocumented(html, "div", "carousel", "Snap to start (default)")
    }

    @Test
    fun snap_to_start_default_daisyCarouselItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItem() {
            }
        }
        assertDocumented(html, "div", "carousel-item", "Snap to start (default)")
    }

    @Test
    fun snap_to_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(snap = CarouselSnap.Center) {
            }
        }
        assertDocumented(html, "div", "carousel carousel-center", "Snap to center")
    }

    @Test
    fun snap_to_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(snap = CarouselSnap.End) {
            }
        }
        assertDocumented(html, "div", "carousel carousel-end", "Snap to end")
    }

    @Test
    fun vertical_carousel() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(direction = CarouselDirection.Vertical) {
            }
        }
        assertDocumented(html, "div", "carousel carousel-vertical", "Vertical carousel")
    }

    @Test
    fun carousel_with_indicator_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(size = ButtonSize.Xs) }
        }
        assertDocumented(html, "a", "btn btn-xs", "Carousel with indicator buttons")
    }

    @Test
    fun carousel_with_next_prev_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyButtonClasses(shape = ButtonShape.Circle) }
        }
        assertDocumented(html, "a", "btn btn-circle", "Carousel with next/prev buttons")
    }
}
