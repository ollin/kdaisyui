package kdaisyui.components

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

    @Test
    fun rotating_6_words() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate() {
            }
        }
        val expectedClasses = "text-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Rotating 6 words")
    }

    @Test
    fun rotating_words_in_a_sentence() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate() {
            }
        }
        val expectedClasses = "text-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Rotating words in a sentence")
    }

    @Test
    fun rotating_3_words_with_custom_duration() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate() {
            }
        }
        val expectedClasses = "text-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Rotating 3 words with custom duration")
    }

    @Test
    fun custom_line_height() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextRotate() {
            }
        }
        val expectedClasses = "text-rotate"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom line height")
    }
}
