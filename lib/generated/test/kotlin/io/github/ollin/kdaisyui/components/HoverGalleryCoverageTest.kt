package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class HoverGalleryCoverageTest {

    @Test
    fun hoverGallery_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hover-gallery", actualClasses, "HoverGallery defaults")
    }

    @Test
    fun hoverGallery_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hover-gallery zz-extra", actualClasses, "HoverGallery all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "HoverGallery id")
        assertTrue(html.contains("data-attrs=\"yes\""), "HoverGallery attrs")
        assertTrue(html.contains("data-content=\"yes\""), "HoverGallery content")
    }
}
