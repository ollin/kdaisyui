package kdaisyui.components

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
    fun read_only_rating() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Read-only Rating")
    }

    @Test
    fun mask_star_2_with_warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mask-star-2 with warning color")
    }

    @Test
    fun mask_heart_with_multiple_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mask-heart with multiple colors")
    }

    @Test
    fun mask_star_2_with_green_500_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mask-star-2 with green-500 color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating() {
            }
        }
        val expectedClasses = "rating"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun with_rating_hidden() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(hidden = true) {
            }
        }
        val expectedClasses = "rating rating-hidden"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for with `rating-hidden`")
    }

    @Test
    fun half_stars() {
        val html = createHTML(prettyPrint = false).div {
            daisyRating(half = true, hidden = true) {
            }
        }
        val expectedClasses = "rating rating-half rating-hidden"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for half stars")
    }
}
