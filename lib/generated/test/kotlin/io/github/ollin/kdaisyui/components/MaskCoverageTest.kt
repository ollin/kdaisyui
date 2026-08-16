package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MaskCoverageTest {

    @Test
    fun mask_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mask", actualClasses, "Mask defaults")
    }

    @Test
    fun mask_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(
                id = htmlId("x-cov-id"),
                circle = true,
                decagon = true,
                diamond = true,
                half1 = true,
                half2 = true,
                heart = true,
                hexagon = true,
                hexagon2 = true,
                pentagon = true,
                square = true,
                squircle = true,
                star = true,
                star2 = true,
                triangle = true,
                triangle2 = true,
                triangle3 = true,
                triangle4 = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mask mask-circle mask-decagon mask-diamond mask-half-1 mask-half-2 mask-heart mask-hexagon mask-hexagon-2 mask-pentagon mask-square mask-squircle mask-star mask-star-2 mask-triangle mask-triangle-2 mask-triangle-3 mask-triangle-4 zz-extra", actualClasses, "Mask all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Mask id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Mask attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Mask content")
    }
}
