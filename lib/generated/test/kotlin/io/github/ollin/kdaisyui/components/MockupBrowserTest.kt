package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupBrowserTest {

    @Test
    fun browser_mockup_with_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser() {
            }
        }
        val expectedClasses = "mockup-browser"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for browser mockup with border")
    }

    @Test
    fun browser_mockup_with_border_daisyMockupBrowserToolbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar() {
            }
        }
        val expectedClasses = "mockup-browser-toolbar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for browser mockup with border")
    }

    @Test
    fun browser_mockup_with_border_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for browser mockup with border")
    }
}
