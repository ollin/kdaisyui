package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MaskTest {

    @Test
    fun squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(squircle = true) {
            }
        }
        val expectedClasses = "mask mask-squircle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Squircle")
    }

    @Test
    fun heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(heart = true) {
            }
        }
        val expectedClasses = "mask mask-heart"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Heart")
    }

    @Test
    fun hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(hexagon = true) {
            }
        }
        val expectedClasses = "mask mask-hexagon"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hexagon")
    }

    @Test
    fun hexagon_2_horizontal_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask() {
            }
        }
        val expectedClasses = "mask"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hexagon-2 (horizontal hexagon)")
    }

    @Test
    fun decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(decagon = true) {
            }
        }
        val expectedClasses = "mask mask-decagon"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Decagon")
    }

    @Test
    fun pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(pentagon = true) {
            }
        }
        val expectedClasses = "mask mask-pentagon"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Pentagon")
    }

    @Test
    fun diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(diamond = true) {
            }
        }
        val expectedClasses = "mask mask-diamond"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Diamond")
    }

    @Test
    fun square() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(square = true) {
            }
        }
        val expectedClasses = "mask mask-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Square")
    }

    @Test
    fun circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(circle = true) {
            }
        }
        val expectedClasses = "mask mask-circle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Circle")
    }

    @Test
    fun star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(star = true) {
            }
        }
        val expectedClasses = "mask mask-star"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Star")
    }

    @Test
    fun star_2_bold_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask() {
            }
        }
        val expectedClasses = "mask"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Star-2 (bold star)")
    }

    @Test
    fun triangle_pointing_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(triangle = true) {
            }
        }
        val expectedClasses = "mask mask-triangle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Triangle (pointing top)")
    }

    @Test
    fun triangle_2_pointing_down() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask() {
            }
        }
        val expectedClasses = "mask"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Triangle-2 (pointing down)")
    }

    @Test
    fun triangle_3_pointing_left() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask() {
            }
        }
        val expectedClasses = "mask"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Triangle-3 (pointing left)")
    }

    @Test
    fun triangle_4_pointing_right() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask() {
            }
        }
        val expectedClasses = "mask"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Triangle-4 (pointing right)")
    }
}
