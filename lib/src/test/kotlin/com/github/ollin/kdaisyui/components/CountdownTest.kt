package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CountdownTest {

    @Test
    fun countdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Countdown")
    }

    @Test
    fun large_text_with_2_digits() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Large text with 2 digits")
    }

    @Test
    fun clock_countdown() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Clock countdown")
    }

    @Test
    fun clock_countdown_with_colons() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Clock countdown with colons")
    }

    @Test
    fun large_text_with_labels() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Large text with labels")
    }

    @Test
    fun large_text_with_labels_under() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Large text with labels under")
    }

    @Test
    fun in_boxes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown() {
            }
        }
        val expectedClasses = "countdown"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for In boxes")
    }
}
