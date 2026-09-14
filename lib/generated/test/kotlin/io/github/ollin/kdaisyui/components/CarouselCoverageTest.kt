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
                horizontal = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "carousel carousel-horizontal carousel-vertical zz-extra", "Carousel all flags", closes = "</div></div>")
        assertCommonFlags(html, "Carousel")
    }

    @Test
    fun carousel_modifier_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                modifier = CarouselModifier.Start,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-start", "Carousel modifier Start")
    }

    @Test
    fun carousel_modifier_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                modifier = CarouselModifier.Center,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-center", "Carousel modifier Center")
    }

    @Test
    fun carousel_modifier_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyCarousel(
                modifier = CarouselModifier.End,
                content = { },
            )
        }
        assertRendered(html, "carousel carousel-end", "Carousel modifier End")
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
}
