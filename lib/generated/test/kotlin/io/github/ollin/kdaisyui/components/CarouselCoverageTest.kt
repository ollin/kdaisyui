package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CarouselCoverageTest {

    @Test
    fun carousel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("carousel", actualClasses, "Carousel defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("carousel carousel-center carousel-end carousel-horizontal carousel-start carousel-vertical zz-extra", actualClasses, "Carousel all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("carousel-item", actualClasses, "CarouselItem defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("carousel-item zz-extra", actualClasses, "CarouselItem all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "CarouselItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CarouselItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CarouselItem content")
    }
}
