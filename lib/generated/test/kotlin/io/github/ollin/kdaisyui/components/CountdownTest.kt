package io.github.ollin.kdaisyui.components

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
}
