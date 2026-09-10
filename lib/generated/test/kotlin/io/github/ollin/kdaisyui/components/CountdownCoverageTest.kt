package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CountdownCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun countdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown(
                content = { },
            )
        }
        assertRendered(html, "countdown", "Countdown defaults", closes = "</span></div>")
    }

    @Test
    fun countdown_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "countdown zz-extra", "Countdown all flags", closes = "</span></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Countdown id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Countdown attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Countdown content")
    }
}
