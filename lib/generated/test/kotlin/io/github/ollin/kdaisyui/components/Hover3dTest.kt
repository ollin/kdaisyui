package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class Hover3dTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun test_3d_image_hover_effect() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d() {
            }
        }
        assertDocumented(html, "div", "hover-3d", "3D image hover effect")
    }

    @Test
    fun test_3d_card_hover_effect_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "3D card hover effect")
    }

    @Test
    fun test_3d_card_hover_effect_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "3D card hover effect")
    }
}
