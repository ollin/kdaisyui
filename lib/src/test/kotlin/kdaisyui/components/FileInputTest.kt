package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FileInputTest {

    @Test
    fun file_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for File input")
    }

    @Test
    fun file_input_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(ghost = true) {
            }
        }
        val expectedClasses = "file-input file-input-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for File input ghost")
    }

    @Test
    fun with_fieldset_and_label() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun disabled() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled")
    }
}
