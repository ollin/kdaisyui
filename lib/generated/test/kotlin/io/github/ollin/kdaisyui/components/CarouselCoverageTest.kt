package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CarouselCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun carousel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                content = { },
            )
        }
        assertRendered(html, "carousel", "Carousel defaults", closes = "</div></div>")
    }

    @Test
    fun carousel_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "carousel zz-extra", "Carousel all flags", closes = "</div></div>")
        assertCommonFlags(html, "Carousel")
    }

    @Test
    fun carousel_snap_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.Start,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-start", "Carousel snap Start")
    }

    @Test
    fun carousel_snap_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.Center,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-center", "Carousel snap Center")
    }

    @Test
    fun carousel_snap_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                snap = CarouselSnap.End,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-end", "Carousel snap End")
    }

    @Test
    fun carousel_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                direction = CarouselDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-horizontal", "Carousel direction Horizontal")
    }

    @Test
    fun carousel_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                direction = CarouselDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-vertical", "Carousel direction Vertical")
    }

    @Test
    fun carouselClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses()
        }
        assertRendered(html, "carousel", "CarouselClasses defaults")
    }

    @Test
    fun carouselClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses()
        }
        assertRendered(html, "carousel", "CarouselClasses all flags")
    }

    @Test
    fun carouselClasses_snap_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.Start,
            )
        }
        assertRendered(html, "carousel carousel-start", "CarouselClasses snap Start")
    }

    @Test
    fun carouselClasses_snap_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.Center,
            )
        }
        assertRendered(html, "carousel carousel-center", "CarouselClasses snap Center")
    }

    @Test
    fun carouselClasses_snap_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                snap = CarouselSnap.End,
            )
        }
        assertRendered(html, "carousel carousel-end", "CarouselClasses snap End")
    }

    @Test
    fun carouselClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                direction = CarouselDirection.Horizontal,
            )
        }
        assertRendered(html, "carousel carousel-horizontal", "CarouselClasses direction Horizontal")
    }

    @Test
    fun carouselClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselClasses(
                direction = CarouselDirection.Vertical,
            )
        }
        assertRendered(html, "carousel carousel-vertical", "CarouselClasses direction Vertical")
    }

    @Test
    fun carouselItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItem(
                content = { },
            )
        }
        assertRendered(html, "carousel-item", "CarouselItem defaults", closes = "</div></div>")
    }

    @Test
    fun carouselItem_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItem(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "carousel-item zz-extra", "CarouselItem all flags", closes = "</div></div>")
        assertCommonFlags(html, "CarouselItem")
    }

    @Test
    fun carouselItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItemClasses()
        }
        assertRendered(html, "carousel-item", "CarouselItemClasses defaults")
    }

    @Test
    fun carouselItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarouselItemClasses()
        }
        assertRendered(html, "carousel-item", "CarouselItemClasses all flags")
    }
}
