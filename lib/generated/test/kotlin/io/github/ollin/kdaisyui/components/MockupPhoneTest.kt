package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupPhoneTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun iphone_mockup() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone() {
            }
        }
        assertDocumented(html, "div", "mockup-phone", "iPhone mockup")
    }

    @Test
    fun iphone_mockup_daisyMockupPhoneCamera() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera() {
            }
        }
        assertDocumented(html, "div", "mockup-phone-camera", "iPhone mockup")
    }

    @Test
    fun iphone_mockup_daisyMockupPhoneDisplay() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay() {
            }
        }
        assertDocumented(html, "div", "mockup-phone-display", "iPhone mockup")
    }
}
