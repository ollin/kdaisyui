package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RatingCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun rating_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                content = { },
            )
        }
        assertRendered(html, Expected("Rating defaults", "rating", closes = "</div></div>"))
    }

    @Test
    fun rating_all_flags() {
        val html = allFlags(Expected("Rating all flags", "rating rating-half zz-extra", closes = "</div></div>")) { daisyRating(id = ID, half = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun rating_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Rating size Xs", "rating rating-xs"))
    }

    @Test
    fun rating_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Rating size Sm", "rating rating-sm"))
    }

    @Test
    fun rating_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Rating size Md", "rating rating-md"))
    }

    @Test
    fun rating_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Rating size Lg", "rating rating-lg"))
    }

    @Test
    fun rating_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(
                size = RatingSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Rating size Xl", "rating rating-xl"))
    }

    @Test
    fun ratingClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses()
        }
        assertRendered(html, Expected("RatingClasses defaults", "rating"))
    }

    @Test
    fun ratingClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                half = true,
            )
        }
        val expected = Expected("RatingClasses all flags", "rating rating-half", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun ratingClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Xs,
            )
        }
        assertRendered(html, Expected("RatingClasses size Xs", "rating rating-xs"))
    }

    @Test
    fun ratingClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Sm,
            )
        }
        assertRendered(html, Expected("RatingClasses size Sm", "rating rating-sm"))
    }

    @Test
    fun ratingClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Md,
            )
        }
        assertRendered(html, Expected("RatingClasses size Md", "rating rating-md"))
    }

    @Test
    fun ratingClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Lg,
            )
        }
        assertRendered(html, Expected("RatingClasses size Lg", "rating rating-lg"))
    }

    @Test
    fun ratingClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRatingClasses(
                size = RatingSize.Xl,
            )
        }
        assertRendered(html, Expected("RatingClasses size Xl", "rating rating-xl"))
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
        val expected = Expected("RatingItemClasses all flags", "rating-hidden", content = false)
        assertRendered(html, expected)
    }
}
