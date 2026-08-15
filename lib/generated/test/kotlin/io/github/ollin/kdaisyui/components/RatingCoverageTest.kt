package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RatingCoverageTest {

    @Test
    fun rating_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating", actualClasses, "Rating defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-half rating-hidden zz-extra", actualClasses, "Rating all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-xs", actualClasses, "Rating size Xs")
    }

    @Test
    fun rating_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-sm", actualClasses, "Rating size Sm")
    }

    @Test
    fun rating_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-md", actualClasses, "Rating size Md")
    }

    @Test
    fun rating_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-lg", actualClasses, "Rating size Lg")
    }

    @Test
    fun rating_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("rating rating-xl", actualClasses, "Rating size Xl")
    }
}
