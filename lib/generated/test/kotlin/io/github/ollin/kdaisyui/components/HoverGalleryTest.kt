package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class HoverGalleryTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun hover_gallery() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery() {
            }
        }
        assertDocumented(html, "figure", "hover-gallery", "Hover Gallery")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Sm) {
            }
        }
        assertDocumented(html, "div", "card card-sm", "Hover Gallery in a card")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Hover Gallery in a card")
    }

    @Test
    fun hover_gallery_in_a_card_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        assertDocumented(html, "h2", "card-title", "Hover Gallery in a card")
    }
}
