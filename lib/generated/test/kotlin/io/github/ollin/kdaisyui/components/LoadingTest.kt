package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadingTest {

    @Test
    fun loading_spinner() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(spinner = true) {
            }
        }
        val expectedClasses = "loading loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading spinner")
    }

    @Test
    fun loading_dots() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(dots = true) {
            }
        }
        val expectedClasses = "loading loading-dots"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading dots")
    }

    @Test
    fun loading_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(ring = true) {
            }
        }
        val expectedClasses = "loading loading-ring"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ring")
    }

    @Test
    fun loading_ball() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(ball = true) {
            }
        }
        val expectedClasses = "loading loading-ball"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading ball")
    }

    @Test
    fun loading_bars() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(bars = true) {
            }
        }
        val expectedClasses = "loading loading-bars"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading bars")
    }

    @Test
    fun loading_infinity() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(infinity = true) {
            }
        }
        val expectedClasses = "loading loading-infinity"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading infinity")
    }

    @Test
    fun loading_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(spinner = true) {
            }
        }
        val expectedClasses = "loading loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Loading with colors")
    }
}
