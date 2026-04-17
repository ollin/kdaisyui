package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupCodeTest {

    @Test
    fun mockup_code_with_line_prefix() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for mockup code with line prefix")
    }

    @Test
    fun multi_line() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Multi line")
    }

    @Test
    fun highlighted_line() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Highlighted line")
    }

    @Test
    fun long_line_will_scroll() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Long line will scroll")
    }

    @Test
    fun without_prefix() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Without prefix")
    }

    @Test
    fun with_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode() {
            }
        }
        val expectedClasses = "mockup-code"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With color")
    }
}
