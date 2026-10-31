package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadioCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun radio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        assertRendered(html, Expected("Radio defaults", "radio"))
        assertTrue(html.contains("type=\""), "Radio sets type")
    }

    @Test
    fun radio_all_flags() {
        val html = allFlags(Expected("Radio all flags", "radio zz-extra", content = false)) { daisyRadio(id = ID, checked = true, disabled = true, name = "x", extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("name=\"x\""), "Radio name")
        assertTrue(html.contains("type=\""), "Radio sets type")
        assertTrue(html.contains("name=\""), "Radio sets name")
        assertTrue(html.contains("checked=\""), "Radio sets checked")
        assertTrue(html.contains("disabled=\""), "Radio sets disabled")
    }

    @Test
    fun radio_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Neutral,
            )
        }
        assertRendered(html, Expected("Radio color Neutral", "radio radio-neutral"))
    }

    @Test
    fun radio_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Primary,
            )
        }
        assertRendered(html, Expected("Radio color Primary", "radio radio-primary"))
    }

    @Test
    fun radio_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Secondary,
            )
        }
        assertRendered(html, Expected("Radio color Secondary", "radio radio-secondary"))
    }

    @Test
    fun radio_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Accent,
            )
        }
        assertRendered(html, Expected("Radio color Accent", "radio radio-accent"))
    }

    @Test
    fun radio_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Success,
            )
        }
        assertRendered(html, Expected("Radio color Success", "radio radio-success"))
    }

    @Test
    fun radio_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Warning,
            )
        }
        assertRendered(html, Expected("Radio color Warning", "radio radio-warning"))
    }

    @Test
    fun radio_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Info,
            )
        }
        assertRendered(html, Expected("Radio color Info", "radio radio-info"))
    }

    @Test
    fun radio_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Error,
            )
        }
        assertRendered(html, Expected("Radio color Error", "radio radio-error"))
    }

    @Test
    fun radio_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xs,
            )
        }
        assertRendered(html, Expected("Radio size Xs", "radio radio-xs"))
    }

    @Test
    fun radio_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Sm,
            )
        }
        assertRendered(html, Expected("Radio size Sm", "radio radio-sm"))
    }

    @Test
    fun radio_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Md,
            )
        }
        assertRendered(html, Expected("Radio size Md", "radio radio-md"))
    }

    @Test
    fun radio_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Lg,
            )
        }
        assertRendered(html, Expected("Radio size Lg", "radio radio-lg"))
    }

    @Test
    fun radio_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xl,
            )
        }
        assertRendered(html, Expected("Radio size Xl", "radio radio-xl"))
    }

    @Test
    fun radioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses()
        }
        assertRendered(html, Expected("RadioClasses defaults", "radio"))
    }

    @Test
    fun radioClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses()
        }
        val expected = Expected("RadioClasses all flags", "radio", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun radioClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Neutral,
            )
        }
        assertRendered(html, Expected("RadioClasses color Neutral", "radio radio-neutral"))
    }

    @Test
    fun radioClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Primary,
            )
        }
        assertRendered(html, Expected("RadioClasses color Primary", "radio radio-primary"))
    }

    @Test
    fun radioClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Secondary,
            )
        }
        assertRendered(html, Expected("RadioClasses color Secondary", "radio radio-secondary"))
    }

    @Test
    fun radioClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Accent,
            )
        }
        assertRendered(html, Expected("RadioClasses color Accent", "radio radio-accent"))
    }

    @Test
    fun radioClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Success,
            )
        }
        assertRendered(html, Expected("RadioClasses color Success", "radio radio-success"))
    }

    @Test
    fun radioClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Warning,
            )
        }
        assertRendered(html, Expected("RadioClasses color Warning", "radio radio-warning"))
    }

    @Test
    fun radioClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Info,
            )
        }
        assertRendered(html, Expected("RadioClasses color Info", "radio radio-info"))
    }

    @Test
    fun radioClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Error,
            )
        }
        assertRendered(html, Expected("RadioClasses color Error", "radio radio-error"))
    }

    @Test
    fun radioClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Xs,
            )
        }
        assertRendered(html, Expected("RadioClasses size Xs", "radio radio-xs"))
    }

    @Test
    fun radioClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Sm,
            )
        }
        assertRendered(html, Expected("RadioClasses size Sm", "radio radio-sm"))
    }

    @Test
    fun radioClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Md,
            )
        }
        assertRendered(html, Expected("RadioClasses size Md", "radio radio-md"))
    }

    @Test
    fun radioClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Lg,
            )
        }
        assertRendered(html, Expected("RadioClasses size Lg", "radio radio-lg"))
    }

    @Test
    fun radioClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Xl,
            )
        }
        assertRendered(html, Expected("RadioClasses size Xl", "radio radio-xl"))
    }
}
