package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MaskCoverageTest {

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
    fun mask_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask()
        }
        assertRendered(html, Expected("Mask defaults", "mask"))
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
        val expected = Expected("Mask all flags", "mask zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun mask_style_squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Squircle,
            )
        }
        assertRendered(html, Expected("Mask style Squircle", "mask mask-squircle"))
    }

    @Test
    fun mask_style_heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Heart,
            )
        }
        assertRendered(html, Expected("Mask style Heart", "mask mask-heart"))
    }

    @Test
    fun mask_style_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Hexagon,
            )
        }
        assertRendered(html, Expected("Mask style Hexagon", "mask mask-hexagon"))
    }

    @Test
    fun mask_style_hexagon2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Hexagon2,
            )
        }
        assertRendered(html, Expected("Mask style Hexagon2", "mask mask-hexagon-2"))
    }

    @Test
    fun mask_style_decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Decagon,
            )
        }
        assertRendered(html, Expected("Mask style Decagon", "mask mask-decagon"))
    }

    @Test
    fun mask_style_pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Pentagon,
            )
        }
        assertRendered(html, Expected("Mask style Pentagon", "mask mask-pentagon"))
    }

    @Test
    fun mask_style_diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Diamond,
            )
        }
        assertRendered(html, Expected("Mask style Diamond", "mask mask-diamond"))
    }

    @Test
    fun mask_style_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Circle,
            )
        }
        assertRendered(html, Expected("Mask style Circle", "mask mask-circle"))
    }

    @Test
    fun mask_style_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Star,
            )
        }
        assertRendered(html, Expected("Mask style Star", "mask mask-star"))
    }

    @Test
    fun mask_style_star2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Star2,
            )
        }
        assertRendered(html, Expected("Mask style Star2", "mask mask-star-2"))
    }

    @Test
    fun mask_style_triangle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle,
            )
        }
        assertRendered(html, Expected("Mask style Triangle", "mask mask-triangle"))
    }

    @Test
    fun mask_style_triangle2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle2,
            )
        }
        assertRendered(html, Expected("Mask style Triangle2", "mask mask-triangle-2"))
    }

    @Test
    fun mask_style_triangle3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle3,
            )
        }
        assertRendered(html, Expected("Mask style Triangle3", "mask mask-triangle-3"))
    }

    @Test
    fun mask_style_triangle4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                style = MaskStyle.Triangle4,
            )
        }
        assertRendered(html, Expected("Mask style Triangle4", "mask mask-triangle-4"))
    }

    @Test
    fun mask_half_half1() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                half = MaskHalf.Half1,
            )
        }
        assertRendered(html, Expected("Mask half Half1", "mask mask-half-1"))
    }

    @Test
    fun mask_half_half2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                half = MaskHalf.Half2,
            )
        }
        assertRendered(html, Expected("Mask half Half2", "mask mask-half-2"))
    }

    @Test
    fun maskClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses()
        }
        assertRendered(html, Expected("MaskClasses defaults", "mask"))
    }

    @Test
    fun maskClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses()
        }
        val expected = Expected("MaskClasses all flags", "mask", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun maskClasses_style_squircle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Squircle,
            )
        }
        assertRendered(html, Expected("MaskClasses style Squircle", "mask mask-squircle"))
    }

    @Test
    fun maskClasses_style_heart() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Heart,
            )
        }
        assertRendered(html, Expected("MaskClasses style Heart", "mask mask-heart"))
    }

    @Test
    fun maskClasses_style_hexagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Hexagon,
            )
        }
        assertRendered(html, Expected("MaskClasses style Hexagon", "mask mask-hexagon"))
    }

    @Test
    fun maskClasses_style_hexagon2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Hexagon2,
            )
        }
        assertRendered(html, Expected("MaskClasses style Hexagon2", "mask mask-hexagon-2"))
    }

    @Test
    fun maskClasses_style_decagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Decagon,
            )
        }
        assertRendered(html, Expected("MaskClasses style Decagon", "mask mask-decagon"))
    }

    @Test
    fun maskClasses_style_pentagon() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Pentagon,
            )
        }
        assertRendered(html, Expected("MaskClasses style Pentagon", "mask mask-pentagon"))
    }

    @Test
    fun maskClasses_style_diamond() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Diamond,
            )
        }
        assertRendered(html, Expected("MaskClasses style Diamond", "mask mask-diamond"))
    }

    @Test
    fun maskClasses_style_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Circle,
            )
        }
        assertRendered(html, Expected("MaskClasses style Circle", "mask mask-circle"))
    }

    @Test
    fun maskClasses_style_star() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Star,
            )
        }
        assertRendered(html, Expected("MaskClasses style Star", "mask mask-star"))
    }

    @Test
    fun maskClasses_style_star2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Star2,
            )
        }
        assertRendered(html, Expected("MaskClasses style Star2", "mask mask-star-2"))
    }

    @Test
    fun maskClasses_style_triangle() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Triangle,
            )
        }
        assertRendered(html, Expected("MaskClasses style Triangle", "mask mask-triangle"))
    }

    @Test
    fun maskClasses_style_triangle2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Triangle2,
            )
        }
        assertRendered(html, Expected("MaskClasses style Triangle2", "mask mask-triangle-2"))
    }

    @Test
    fun maskClasses_style_triangle3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Triangle3,
            )
        }
        assertRendered(html, Expected("MaskClasses style Triangle3", "mask mask-triangle-3"))
    }

    @Test
    fun maskClasses_style_triangle4() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                style = MaskStyle.Triangle4,
            )
        }
        assertRendered(html, Expected("MaskClasses style Triangle4", "mask mask-triangle-4"))
    }

    @Test
    fun maskClasses_half_half1() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                half = MaskHalf.Half1,
            )
        }
        assertRendered(html, Expected("MaskClasses half Half1", "mask mask-half-1"))
    }

    @Test
    fun maskClasses_half_half2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMaskClasses(
                half = MaskHalf.Half2,
            )
        }
        assertRendered(html, Expected("MaskClasses half Half2", "mask mask-half-2"))
    }
}
