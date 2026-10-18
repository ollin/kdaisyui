package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class TextRotateTest {

    @Test
    fun text_rotate() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate() {
            }
        }
        val expectedClasses = "text-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Text Rotate")
    }
}
