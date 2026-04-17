package kdaisyui.components

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
    }

    @Test
    fun different_values() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress() {
            }
        }
        val expectedClasses = "radial-progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Different values")
    }

    @Test
    fun custom_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress() {
            }
        }
        val expectedClasses = "radial-progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom color")
    }

    @Test
    fun with_background_color_and_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress() {
            }
        }
        val expectedClasses = "radial-progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With background color and border")
    }

    @Test
    fun custom_size_and_custom_thickness() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress() {
            }
        }
        val expectedClasses = "radial-progress"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom size and custom thickness")
    }
}
