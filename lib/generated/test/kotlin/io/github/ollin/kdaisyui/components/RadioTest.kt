package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RadioTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun radio() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        assertDocumented(html, "input", "radio", "Radio")
    }

    @Test
    fun radio_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Xs)
        }
        assertDocumented(html, "input", "radio radio-xs", "Radio sizes")
    }

    @Test
    fun radio_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Sm)
        }
        assertDocumented(html, "input", "radio radio-sm", "Radio sizes")
    }

    @Test
    fun radio_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Md)
        }
        assertDocumented(html, "input", "radio radio-md", "Radio sizes")
    }

    @Test
    fun radio_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Lg)
        }
        assertDocumented(html, "input", "radio radio-lg", "Radio sizes")
    }

    @Test
    fun radio_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(size = RadioSize.Xl)
        }
        assertDocumented(html, "input", "radio radio-xl", "Radio sizes")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Neutral)
        }
        assertDocumented(html, "input", "radio radio-neutral", "Neutral color")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Primary)
        }
        assertDocumented(html, "input", "radio radio-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Secondary)
        }
        assertDocumented(html, "input", "radio radio-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Accent)
        }
        assertDocumented(html, "input", "radio radio-accent", "Accent color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Success)
        }
        assertDocumented(html, "input", "radio radio-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Warning)
        }
        assertDocumented(html, "input", "radio radio-warning", "Warning color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Info)
        }
        assertDocumented(html, "input", "radio radio-info", "Info color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(color = RadioColor.Error)
        }
        assertDocumented(html, "input", "radio radio-error", "Error color")
    }
}
