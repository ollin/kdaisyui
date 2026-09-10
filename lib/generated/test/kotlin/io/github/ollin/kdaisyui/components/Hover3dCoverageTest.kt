package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class Hover3dCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun hover3d_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHover3d(
                content = { },
            )
        }
        assertRendered(html, "hover-3d", "Hover3d defaults", closes = "</div></div>")
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
        assertRendered(html, "hover-3d zz-extra", "Hover3d all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Hover3d id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Hover3d attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Hover3d content")
    }
}
