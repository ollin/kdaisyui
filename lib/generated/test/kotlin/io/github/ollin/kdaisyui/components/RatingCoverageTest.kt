package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RatingCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun rating_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                content = { },
            )
        }
        assertRendered(html, "rating", "Rating defaults", closes = "</div></div>")
    }

    @Test
    fun rating_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                id = htmlId("x-cov-id"),
                half = true,
                hidden = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "rating rating-half rating-hidden zz-extra", "Rating all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Rating id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Rating attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Rating content")
    }

    @Test
    fun rating_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "rating rating-xs", "Rating size Xs")
    }

    @Test
    fun rating_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "rating rating-sm", "Rating size Sm")
    }

    @Test
    fun rating_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Md,
                content = { },
            )
        }
        assertRendered(html, "rating rating-md", "Rating size Md")
    }

    @Test
    fun rating_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "rating rating-lg", "Rating size Lg")
    }

    @Test
    fun rating_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "rating rating-xl", "Rating size Xl")
    }
}
