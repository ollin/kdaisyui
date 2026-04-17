package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DiffTest {

    @Test
    fun diff() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff() {
            }
        }
        val expectedClasses = "diff"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Diff")
    }

    @Test
    fun diff_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyDiff() {
            }
        }
        val expectedClasses = "diff"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Diff text")
    }
}
