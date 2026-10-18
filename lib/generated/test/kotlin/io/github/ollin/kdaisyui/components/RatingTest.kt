package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RatingTest {

    @Test
    fun rating() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Rating")
    }

    @Test
    fun rating_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star) {
            }
        }
        val expectedClasses = "mask mask-star"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Rating")
    }

    @Test
    fun mask_star_2_with_warning_color_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star2) {
            }
        }
        val expectedClasses = "mask mask-star-2"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mask-star-2 with warning color")
    }

    @Test
    fun mask_heart_with_multiple_colors_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Heart) {
            }
        }
        val expectedClasses = "mask mask-heart"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mask-heart with multiple colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Xs) {
            }
        }
        val expectedClasses = "rating rating-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Sm) {
            }
        }
        val expectedClasses = "rating rating-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Md) {
            }
        }
        val expectedClasses = "rating rating-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Lg) {
            }
        }
        val expectedClasses = "rating rating-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Xl) {
            }
        }
        val expectedClasses = "rating rating-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun half_stars() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(size = RatingSize.Lg, half = true) {
            }
        }
        val expectedClasses = "rating rating-half rating-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for half stars")
    }

    @Test
    fun half_stars_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star2, half = MaskHalf.Half1) {
            }
        }
        val expectedClasses = "mask mask-half-1 mask-star-2"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for half stars")
    }

    @Test
    fun half_stars_daisyMask_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star2, half = MaskHalf.Half2) {
            }
        }
        val expectedClasses = "mask mask-half-2 mask-star-2"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for half stars")
    }
}
