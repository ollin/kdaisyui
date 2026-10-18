package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SelectCoverageTest {

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
    fun select_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                content = { },
            )
        }
        assertRendered(html, "select", "Select defaults", closes = "</select></div>")
    }

    @Test
    fun select_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                id = htmlId("x-cov-id"),
                ghost = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "select select-ghost zz-extra", "Select all flags", closes = "</select></div>")
        assertCommonFlags(html, "Select")
        assertTrue(html.contains("disabled=\""), "Select sets disabled")
    }

    @Test
    fun select_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "select select-neutral", "Select color Neutral")
    }

    @Test
    fun select_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "select select-primary", "Select color Primary")
    }

    @Test
    fun select_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "select select-secondary", "Select color Secondary")
    }

    @Test
    fun select_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "select select-accent", "Select color Accent")
    }

    @Test
    fun select_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Info,
                content = { },
            )
        }
        assertRendered(html, "select select-info", "Select color Info")
    }

    @Test
    fun select_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Success,
                content = { },
            )
        }
        assertRendered(html, "select select-success", "Select color Success")
    }

    @Test
    fun select_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "select select-warning", "Select color Warning")
    }

    @Test
    fun select_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Error,
                content = { },
            )
        }
        assertRendered(html, "select select-error", "Select color Error")
    }

    @Test
    fun select_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "select select-xs", "Select size Xs")
    }

    @Test
    fun select_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "select select-sm", "Select size Sm")
    }

    @Test
    fun select_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Md,
                content = { },
            )
        }
        assertRendered(html, "select select-md", "Select size Md")
    }

    @Test
    fun select_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "select select-lg", "Select size Lg")
    }

    @Test
    fun select_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "select select-xl", "Select size Xl")
    }
}
