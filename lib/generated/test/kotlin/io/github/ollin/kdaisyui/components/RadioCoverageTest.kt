package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class RadioCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun radio_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio()
        }
        assertRendered(html, "radio", "Radio defaults")
        assertTrue(html.contains("type=\""), "Radio sets type")
    }

    @Test
    fun radio_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                id = htmlId("x-cov-id"),
                checked = true,
                disabled = true,
                name = "x",
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "radio zz-extra", "Radio all flags")
        assertCommonFlags(html, "Radio", content = false)
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
        assertRendered(html, "radio radio-neutral", "Radio color Neutral")
    }

    @Test
    fun radio_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Primary,
            )
        }
        assertRendered(html, "radio radio-primary", "Radio color Primary")
    }

    @Test
    fun radio_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Secondary,
            )
        }
        assertRendered(html, "radio radio-secondary", "Radio color Secondary")
    }

    @Test
    fun radio_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Accent,
            )
        }
        assertRendered(html, "radio radio-accent", "Radio color Accent")
    }

    @Test
    fun radio_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Success,
            )
        }
        assertRendered(html, "radio radio-success", "Radio color Success")
    }

    @Test
    fun radio_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Warning,
            )
        }
        assertRendered(html, "radio radio-warning", "Radio color Warning")
    }

    @Test
    fun radio_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Info,
            )
        }
        assertRendered(html, "radio radio-info", "Radio color Info")
    }

    @Test
    fun radio_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                color = RadioColor.Error,
            )
        }
        assertRendered(html, "radio radio-error", "Radio color Error")
    }

    @Test
    fun radio_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xs,
            )
        }
        assertRendered(html, "radio radio-xs", "Radio size Xs")
    }

    @Test
    fun radio_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Sm,
            )
        }
        assertRendered(html, "radio radio-sm", "Radio size Sm")
    }

    @Test
    fun radio_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Md,
            )
        }
        assertRendered(html, "radio radio-md", "Radio size Md")
    }

    @Test
    fun radio_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Lg,
            )
        }
        assertRendered(html, "radio radio-lg", "Radio size Lg")
    }

    @Test
    fun radio_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(
                size = RadioSize.Xl,
            )
        }
        assertRendered(html, "radio radio-xl", "Radio size Xl")
    }

    @Test
    fun radioClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses()
        }
        assertRendered(html, "radio", "RadioClasses defaults")
    }

    @Test
    fun radioClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses()
        }
        assertRendered(html, "radio", "RadioClasses all flags")
    }

    @Test
    fun radioClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Neutral,
            )
        }
        assertRendered(html, "radio radio-neutral", "RadioClasses color Neutral")
    }

    @Test
    fun radioClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Primary,
            )
        }
        assertRendered(html, "radio radio-primary", "RadioClasses color Primary")
    }

    @Test
    fun radioClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Secondary,
            )
        }
        assertRendered(html, "radio radio-secondary", "RadioClasses color Secondary")
    }

    @Test
    fun radioClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Accent,
            )
        }
        assertRendered(html, "radio radio-accent", "RadioClasses color Accent")
    }

    @Test
    fun radioClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Success,
            )
        }
        assertRendered(html, "radio radio-success", "RadioClasses color Success")
    }

    @Test
    fun radioClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Warning,
            )
        }
        assertRendered(html, "radio radio-warning", "RadioClasses color Warning")
    }

    @Test
    fun radioClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Info,
            )
        }
        assertRendered(html, "radio radio-info", "RadioClasses color Info")
    }

    @Test
    fun radioClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                color = RadioColor.Error,
            )
        }
        assertRendered(html, "radio radio-error", "RadioClasses color Error")
    }

    @Test
    fun radioClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Xs,
            )
        }
        assertRendered(html, "radio radio-xs", "RadioClasses size Xs")
    }

    @Test
    fun radioClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Sm,
            )
        }
        assertRendered(html, "radio radio-sm", "RadioClasses size Sm")
    }

    @Test
    fun radioClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Md,
            )
        }
        assertRendered(html, "radio radio-md", "RadioClasses size Md")
    }

    @Test
    fun radioClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Lg,
            )
        }
        assertRendered(html, "radio radio-lg", "RadioClasses size Lg")
    }

    @Test
    fun radioClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadioClasses(
                size = RadioSize.Xl,
            )
        }
        assertRendered(html, "radio radio-xl", "RadioClasses size Xl")
    }
}
