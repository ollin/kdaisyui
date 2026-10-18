package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class HoverGalleryTest {

    @Test
    fun hover_gallery() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery() {
            }
        }
        val expectedClasses = "hover-gallery"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hover Gallery")
        assertEquals("figure", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Hover Gallery")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Sm) {
            }
        }
        val expectedClasses = "card card-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hover Gallery in a card")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Hover Gallery in a card")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hover Gallery in a card")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Hover Gallery in a card")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        val expectedClasses = "card-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hover Gallery in a card")
        assertEquals("h2", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Hover Gallery in a card")
    }
}
