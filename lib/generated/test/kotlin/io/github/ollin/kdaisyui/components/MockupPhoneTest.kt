package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class MockupPhoneTest {

    @Test
    fun iphone_mockup() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone() {
            }
        }
        val expectedClasses = "mockup-phone"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for iPhone mockup")
    }

    @Test
    fun iphone_mockup_daisyMockupPhoneCamera() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera() {
            }
        }
        val expectedClasses = "mockup-phone-camera"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for iPhone mockup")
    }

    @Test
    fun iphone_mockup_daisyMockupPhoneDisplay() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay() {
            }
        }
        val expectedClasses = "mockup-phone-display"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for iPhone mockup")
    }
}
