package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupWindowTest {

    @Test
    fun window_mockup_with_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow() {
            }
        }
        val expectedClasses = "mockup-window"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for window mockup with border")
    }

    @Test
    fun window_mockup_with_background_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupWindow() {
            }
        }
        val expectedClasses = "mockup-window"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for window mockup with background color")
    }
}
