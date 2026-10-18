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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "rating rating-half zz-extra", "Rating all flags", closes = "</div></div>")
        assertCommonFlags(html, "Rating")
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

    @Test
    fun ratingClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses()
        }
        assertRendered(html, "rating", "RatingClasses defaults")
    }

    @Test
    fun ratingClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                half = true,
            )
        }
        assertRendered(html, "rating rating-half", "RatingClasses all flags")
    }

    @Test
    fun ratingClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Xs,
            )
        }
        assertRendered(html, "rating rating-xs", "RatingClasses size Xs")
    }

    @Test
    fun ratingClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Sm,
            )
        }
        assertRendered(html, "rating rating-sm", "RatingClasses size Sm")
    }

    @Test
    fun ratingClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Md,
            )
        }
        assertRendered(html, "rating rating-md", "RatingClasses size Md")
    }

    @Test
    fun ratingClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Lg,
            )
        }
        assertRendered(html, "rating rating-lg", "RatingClasses size Lg")
    }

    @Test
    fun ratingClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Xl,
            )
        }
        assertRendered(html, "rating rating-xl", "RatingClasses size Xl")
    }

    @Test
    fun ratingItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingItemClasses()
        }
        assertTrue(!html.contains("class=\""), "RatingItemClasses defaults emits no class")
    }

    @Test
    fun ratingItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingItemClasses(
                hidden = true,
            )
        }
        assertRendered(html, "rating-hidden", "RatingItemClasses all flags")
    }
}
