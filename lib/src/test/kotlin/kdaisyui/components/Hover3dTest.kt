package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class Hover3dTest {

    @Test
    fun test_3d_image_hover_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d() {
            }
        }
        val expectedClasses = "hover-3d"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for 3D image hover effect")
    }

    @Test
    fun test_3d_card_hover_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d() {
            }
        }
        val expectedClasses = "hover-3d"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for 3D card hover effect")
    }

    @Test
    fun test_3d_hover_effect_for_image_gallery() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d() {
            }
        }
        val expectedClasses = "hover-3d"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for 3D hover effect for image gallery")
    }
}
