package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RadialProgressTest {

    @Test
    fun radial_progress() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress() {
            }
        }
        val expectedClasses = "radial-progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Radial progress")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Radial progress")
    }
}
