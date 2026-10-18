package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MaskTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Squircle)
        }
        assertDocumented(html, "img", "mask mask-squircle", "Squircle")
    }

    @Test
    fun heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Heart)
        }
        assertDocumented(html, "img", "mask mask-heart", "Heart")
    }

    @Test
    fun hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Hexagon)
        }
        assertDocumented(html, "img", "mask mask-hexagon", "Hexagon")
    }

    @Test
    fun hexagon_2_horizontal_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Hexagon2)
        }
        assertDocumented(html, "img", "mask mask-hexagon-2", "Hexagon-2 (horizontal hexagon)")
    }

    @Test
    fun decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Decagon)
        }
        assertDocumented(html, "img", "mask mask-decagon", "Decagon")
    }

    @Test
    fun pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Pentagon)
        }
        assertDocumented(html, "img", "mask mask-pentagon", "Pentagon")
    }

    @Test
    fun diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Diamond)
        }
        assertDocumented(html, "img", "mask mask-diamond", "Diamond")
    }

    @Test
    fun circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Circle)
        }
        assertDocumented(html, "img", "mask mask-circle", "Circle")
    }

    @Test
    fun star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star)
        }
        assertDocumented(html, "img", "mask mask-star", "Star")
    }

    @Test
    fun star_2_bold_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Star2)
        }
        assertDocumented(html, "img", "mask mask-star-2", "Star-2 (bold star)")
    }

    @Test
    fun triangle_pointing_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Triangle)
        }
        assertDocumented(html, "img", "mask mask-triangle", "Triangle (pointing top)")
    }

    @Test
    fun triangle_2_pointing_down() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Triangle2)
        }
        assertDocumented(html, "img", "mask mask-triangle-2", "Triangle-2 (pointing down)")
    }

    @Test
    fun triangle_3_pointing_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Triangle3)
        }
        assertDocumented(html, "img", "mask mask-triangle-3", "Triangle-3 (pointing left)")
    }

    @Test
    fun triangle_4_pointing_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Triangle4)
        }
        assertDocumented(html, "img", "mask mask-triangle-4", "Triangle-4 (pointing right)")
    }
}
