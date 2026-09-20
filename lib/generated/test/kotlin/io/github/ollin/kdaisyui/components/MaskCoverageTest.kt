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
    fun mask_style_squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Squircle,
            )
        }
        assertRendered(html, "mask mask-squircle", "Mask style Squircle")
    }

    @Test
    fun mask_style_heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Heart,
            )
        }
        assertRendered(html, "mask mask-heart", "Mask style Heart")
    }

    @Test
    fun mask_style_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Hexagon,
            )
        }
        assertRendered(html, "mask mask-hexagon", "Mask style Hexagon")
    }

    @Test
    fun mask_style_hexagon2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Hexagon2,
            )
        }
        assertRendered(html, "mask mask-hexagon-2", "Mask style Hexagon2")
    }

    @Test
    fun mask_style_decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Decagon,
            )
        }
        assertRendered(html, "mask mask-decagon", "Mask style Decagon")
    }

    @Test
    fun mask_style_pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Pentagon,
            )
        }
        assertRendered(html, "mask mask-pentagon", "Mask style Pentagon")
    }

    @Test
    fun mask_style_diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Diamond,
            )
        }
        assertRendered(html, "mask mask-diamond", "Mask style Diamond")
    }

    @Test
    fun mask_style_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Circle,
            )
        }
        assertRendered(html, "mask mask-circle", "Mask style Circle")
    }

    @Test
    fun mask_style_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Star,
            )
        }
        assertRendered(html, "mask mask-star", "Mask style Star")
    }

    @Test
    fun mask_style_star2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Star2,
            )
        }
        assertRendered(html, "mask mask-star-2", "Mask style Star2")
    }

    @Test
    fun mask_style_triangle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle,
            )
        }
        assertRendered(html, "mask mask-triangle", "Mask style Triangle")
    }

    @Test
    fun mask_style_triangle2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle2,
            )
        }
        assertRendered(html, "mask mask-triangle-2", "Mask style Triangle2")
    }

    @Test
    fun mask_style_triangle3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle3,
            )
        }
        assertRendered(html, "mask mask-triangle-3", "Mask style Triangle3")
    }

    @Test
    fun mask_style_triangle4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle4,
            )
        }
        assertRendered(html, "mask mask-triangle-4", "Mask style Triangle4")
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
