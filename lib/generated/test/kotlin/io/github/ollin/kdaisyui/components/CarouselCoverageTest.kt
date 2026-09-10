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
                center = true,
                end = true,
                horizontal = true,
                start = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "carousel carousel-center carousel-end carousel-horizontal carousel-start carousel-vertical zz-extra", "Carousel all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Carousel id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Carousel attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Carousel content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "CarouselItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CarouselItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CarouselItem content")
    }
}
