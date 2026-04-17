package com.github.ollin.kdaisyui.components

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
    fun with_color_and_wallpaper() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone() {
            }
        }
        val expectedClasses = "mockup-phone"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With color and wallpaper")
    }
}
