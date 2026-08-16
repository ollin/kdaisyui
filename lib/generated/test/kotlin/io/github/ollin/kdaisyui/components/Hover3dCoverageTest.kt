package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class Hover3dCoverageTest {

    @Test
    fun hover3d_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hover-3d", actualClasses, "Hover3d defaults")
    }

    @Test
    fun hover3d_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hover-3d zz-extra", actualClasses, "Hover3d all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Hover3d id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Hover3d attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Hover3d content")
    }
}
