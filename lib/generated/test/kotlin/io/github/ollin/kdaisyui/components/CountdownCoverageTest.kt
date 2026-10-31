package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CountdownCoverageTest {

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
    fun countdown_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdown(
                content = { },
            )
        }
        assertRendered(html, Expected("Countdown defaults", "countdown", closes = "</span></div>"))
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
        val expected = Expected("Countdown all flags", "countdown zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun countdownClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdownClasses()
        }
        assertRendered(html, Expected("CountdownClasses defaults", "countdown"))
    }

    @Test
    fun countdownClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCountdownClasses()
        }
        val expected = Expected("CountdownClasses all flags", "countdown", content = false)
        assertRendered(html, expected)
    }
}
