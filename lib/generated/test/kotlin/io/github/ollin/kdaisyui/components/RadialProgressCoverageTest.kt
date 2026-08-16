package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadialProgressCoverageTest {

    @Test
    fun radialProgress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radial-progress", actualClasses, "RadialProgress defaults")
    }

    @Test
    fun radialProgress_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("radial-progress zz-extra", actualClasses, "RadialProgress all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "RadialProgress id")
        assertTrue(html.contains("data-attrs=\"yes\""), "RadialProgress attrs")
        assertTrue(html.contains("data-content=\"yes\""), "RadialProgress content")
    }
}
