package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MaskCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun mask_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask()
        }
        assertRendered(html, "mask", "Mask defaults")
    }

    @Test
    fun mask_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "mask zz-extra", "Mask all flags")
        assertCommonFlags(html, "Mask", content = false)
    }

    @Test
    fun mask_shape_squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Squircle,
            )
        }
        assertRendered(html, "mask mask-squircle", "Mask shape Squircle")
    }

    @Test
    fun mask_shape_heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Heart,
            )
        }
        assertRendered(html, "mask mask-heart", "Mask shape Heart")
    }

    @Test
    fun mask_shape_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Hexagon,
            )
        }
        assertRendered(html, "mask mask-hexagon", "Mask shape Hexagon")
    }

    @Test
    fun mask_shape_hexagon2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Hexagon2,
            )
        }
        assertRendered(html, "mask mask-hexagon-2", "Mask shape Hexagon2")
    }

    @Test
    fun mask_shape_decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Decagon,
            )
        }
        assertRendered(html, "mask mask-decagon", "Mask shape Decagon")
    }

    @Test
    fun mask_shape_pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Pentagon,
            )
        }
        assertRendered(html, "mask mask-pentagon", "Mask shape Pentagon")
    }

    @Test
    fun mask_shape_diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Diamond,
            )
        }
        assertRendered(html, "mask mask-diamond", "Mask shape Diamond")
    }

    @Test
    fun mask_shape_square() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Square,
            )
        }
        assertRendered(html, "mask mask-square", "Mask shape Square")
    }

    @Test
    fun mask_shape_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Circle,
            )
        }
        assertRendered(html, "mask mask-circle", "Mask shape Circle")
    }

    @Test
    fun mask_shape_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Star,
            )
        }
        assertRendered(html, "mask mask-star", "Mask shape Star")
    }

    @Test
    fun mask_shape_star2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Star2,
            )
        }
        assertRendered(html, "mask mask-star-2", "Mask shape Star2")
    }

    @Test
    fun mask_shape_triangle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Triangle,
            )
        }
        assertRendered(html, "mask mask-triangle", "Mask shape Triangle")
    }

    @Test
    fun mask_shape_triangle2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Triangle2,
            )
        }
        assertRendered(html, "mask mask-triangle-2", "Mask shape Triangle2")
    }

    @Test
    fun mask_shape_triangle3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Triangle3,
            )
        }
        assertRendered(html, "mask mask-triangle-3", "Mask shape Triangle3")
    }

    @Test
    fun mask_shape_triangle4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                shape = MaskShape.Triangle4,
            )
        }
        assertRendered(html, "mask mask-triangle-4", "Mask shape Triangle4")
    }

    @Test
    fun mask_modifier_half1() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                modifier = MaskModifier.Half1,
            )
        }
        assertRendered(html, "mask mask-half-1", "Mask modifier Half1")
    }

    @Test
    fun mask_modifier_half2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                modifier = MaskModifier.Half2,
            )
        }
        assertRendered(html, "mask mask-half-2", "Mask modifier Half2")
    }
}
