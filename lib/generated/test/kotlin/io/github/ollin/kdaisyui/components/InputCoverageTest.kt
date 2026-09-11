package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class InputCoverageTest {

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
    fun input_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertRendered(html, "input", "Input defaults")
        assertTrue(html.contains("type=\""), "Input sets type")
    }

    @Test
    fun input_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                id = htmlId("x-cov-id"),
                ghost = true,
                disabled = true,
                placeholder = "x",
                value = "x",
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "input input-ghost zz-extra", "Input all flags")
        assertCommonFlags(html, "Input", content = false)
        assertTrue(html.contains("placeholder=\"x\""), "Input placeholder")
        assertTrue(html.contains("value=\"x\""), "Input value")
        assertTrue(html.contains("type=\""), "Input sets type")
        assertTrue(html.contains("placeholder=\""), "Input sets placeholder")
        assertTrue(html.contains("value=\""), "Input sets value")
        assertTrue(html.contains("disabled=\""), "Input sets disabled")
    }

    @Test
    fun input_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Neutral,
            )
        }
        assertRendered(html, "input input-neutral", "Input variant Neutral")
    }

    @Test
    fun input_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Primary,
            )
        }
        assertRendered(html, "input input-primary", "Input variant Primary")
    }

    @Test
    fun input_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Secondary,
            )
        }
        assertRendered(html, "input input-secondary", "Input variant Secondary")
    }

    @Test
    fun input_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Accent,
            )
        }
        assertRendered(html, "input input-accent", "Input variant Accent")
    }

    @Test
    fun input_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Info,
            )
        }
        assertRendered(html, "input input-info", "Input variant Info")
    }

    @Test
    fun input_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Success,
            )
        }
        assertRendered(html, "input input-success", "Input variant Success")
    }

    @Test
    fun input_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Warning,
            )
        }
        assertRendered(html, "input input-warning", "Input variant Warning")
    }

    @Test
    fun input_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Error,
            )
        }
        assertRendered(html, "input input-error", "Input variant Error")
    }

    @Test
    fun input_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xs,
            )
        }
        assertRendered(html, "input input-xs", "Input size Xs")
    }

    @Test
    fun input_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Sm,
            )
        }
        assertRendered(html, "input input-sm", "Input size Sm")
    }

    @Test
    fun input_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Md,
            )
        }
        assertRendered(html, "input input-md", "Input size Md")
    }

    @Test
    fun input_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Lg,
            )
        }
        assertRendered(html, "input input-lg", "Input size Lg")
    }

    @Test
    fun input_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xl,
            )
        }
        assertRendered(html, "input input-xl", "Input size Xl")
    }
}
