package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatusTest {

    @Test
    fun status() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status")
    }

    @Test
    fun status_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status sizes")
    }

    @Test
    fun status_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with colors")
    }

    @Test
    fun status_with_ping_animation() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with ping animation")
    }

    @Test
    fun status_with_bounce_animation() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatus() {
            }
        }
        val expectedClasses = "status"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status with bounce animation")
    }
}
