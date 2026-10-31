package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class HoverGalleryCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun hoverGallery_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGallery(
                content = { },
            )
        }
        assertRendered(html, Expected("HoverGallery defaults", "hover-gallery", closes = "</figure></div>"))
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
        val expected = Expected("HoverGallery all flags", "hover-gallery zz-extra", closes = "</figure></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun hoverGalleryClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGalleryClasses()
        }
        assertRendered(html, Expected("HoverGalleryClasses defaults", "hover-gallery"))
    }

    @Test
    fun hoverGalleryClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHoverGalleryClasses()
        }
        val expected = Expected("HoverGalleryClasses all flags", "hover-gallery", content = false)
        assertRendered(html, expected)
    }
}
