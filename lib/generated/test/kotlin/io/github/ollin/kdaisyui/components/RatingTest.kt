package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.input

class RatingTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun rating() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        assertDocumented(html, "div", "rating", "Rating")
    }

    @Test
    fun rating_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyMaskClasses(style = MaskStyle.Star) }
        }
        assertDocumented(html, "input", "mask mask-star", "Rating")
    }

    @Test
    fun mask_star_2_with_warning_color_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyMaskClasses(style = MaskStyle.Star2) }
        }
        assertDocumented(html, "input", "mask mask-star-2", "mask-star-2 with warning color")
    }

    @Test
    fun mask_heart_with_multiple_colors_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyMaskClasses(style = MaskStyle.Heart) }
        }
        assertDocumented(html, "input", "mask mask-heart", "mask-heart with multiple colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Xs) {
            }
        }
        assertDocumented(html, "div", "rating rating-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Sm) {
            }
        }
        assertDocumented(html, "div", "rating rating-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Md) {
            }
        }
        assertDocumented(html, "div", "rating rating-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Lg) {
            }
        }
        assertDocumented(html, "div", "rating rating-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Xl) {
            }
        }
        assertDocumented(html, "div", "rating rating-xl", "Sizes")
    }

    @Test
    fun with_rating_hidden_daisyRatingItem() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyRatingItemClasses(hidden = true) }
        }
        assertDocumented(html, "input", "rating-hidden", "with `rating-hidden`")
    }

    @Test
    fun half_stars() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Lg, half = true) {
            }
        }
        assertDocumented(html, "div", "rating rating-half rating-lg", "half stars")
    }

    @Test
    fun half_stars_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyMaskClasses(style = MaskStyle.Star2, half = MaskHalf.Half1) }
        }
        assertDocumented(html, "input", "mask mask-half-1 mask-star-2", "half stars")
    }

    @Test
    fun half_stars_daisyMask_2() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyMaskClasses(style = MaskStyle.Star2, half = MaskHalf.Half2) }
        }
        assertDocumented(html, "input", "mask mask-half-2 mask-star-2", "half stars")
    }
}
