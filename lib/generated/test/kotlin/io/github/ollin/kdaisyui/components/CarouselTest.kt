package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

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
    }

    @Test
    fun snap_to_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(center = true) {
            }
        }
        val expectedClasses = "carousel carousel-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to center")
    }

    @Test
    fun snap_to_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(end = true) {
            }
        }
        val expectedClasses = "carousel carousel-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Snap to end")
    }

    @Test
    fun carousel_with_full_width_items() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        val expectedClasses = "carousel"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with full width items")
    }

    @Test
    fun vertical_carousel() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(vertical = true) {
            }
        }
        val expectedClasses = "carousel carousel-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical carousel")
    }

    @Test
    fun carousel_with_half_width_items() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        val expectedClasses = "carousel"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with half width items")
    }

    @Test
    fun full_bleed_carousel() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(center = true) {
            }
        }
        val expectedClasses = "carousel carousel-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Full-bleed carousel")
    }

    @Test
    fun carousel_with_indicator_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        val expectedClasses = "carousel"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with indicator buttons")
    }

    @Test
    fun carousel_with_next_prev_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel() {
            }
        }
        val expectedClasses = "carousel"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Carousel with next/prev buttons")
    }
}
