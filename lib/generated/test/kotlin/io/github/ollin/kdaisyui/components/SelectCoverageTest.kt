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
    fun select_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "select select-neutral", "Select variant Neutral")
    }

    @Test
    fun select_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "select select-primary", "Select variant Primary")
    }

    @Test
    fun select_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "select select-secondary", "Select variant Secondary")
    }

    @Test
    fun select_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "select select-accent", "Select variant Accent")
    }

    @Test
    fun select_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "select select-info", "Select variant Info")
    }

    @Test
    fun select_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "select select-success", "Select variant Success")
    }

    @Test
    fun select_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "select select-warning", "Select variant Warning")
    }

    @Test
    fun select_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "select select-error", "Select variant Error")
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
