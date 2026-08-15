package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CountdownCoverageTest {

    @Test
    fun countdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("countdown", actualClasses, "Countdown defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("countdown zz-extra", actualClasses, "Countdown all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Countdown id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Countdown attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Countdown content")
    }
}
