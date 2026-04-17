package com.github.ollin.kdaisyui.components

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
    }

    @Test
    fun hover_gallery_in_a_card() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery() {
            }
        }
        val expectedClasses = "hover-gallery"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hover Gallery in a card")
    }
}
