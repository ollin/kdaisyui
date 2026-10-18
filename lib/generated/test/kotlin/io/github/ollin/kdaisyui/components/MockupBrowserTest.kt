package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupBrowserTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun browser_mockup_with_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowser() {
            }
        }
        assertDocumented(html, "div", "mockup-browser", "browser mockup with border")
    }

    @Test
    fun browser_mockup_with_border_daisyMockupBrowserToolbar() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupBrowserToolbar() {
            }
        }
        assertDocumented(html, "div", "mockup-browser-toolbar", "browser mockup with border")
    }

    @Test
    fun browser_mockup_with_border_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyInputClasses() }
        }
        assertDocumented(html, "div", "input", "browser mockup with border")
    }
}
