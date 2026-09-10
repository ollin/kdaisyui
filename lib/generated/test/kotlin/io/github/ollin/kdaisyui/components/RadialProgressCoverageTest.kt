package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadialProgressCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun radialProgress_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadialProgress(
                content = { },
            )
        }
        assertRendered(html, "radial-progress", "RadialProgress defaults", closes = "</div></div>")
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
        assertRendered(html, "radial-progress zz-extra", "RadialProgress all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "RadialProgress id")
        assertTrue(html.contains("data-attrs=\"yes\""), "RadialProgress attrs")
        assertTrue(html.contains("data-content=\"yes\""), "RadialProgress content")
    }
}
