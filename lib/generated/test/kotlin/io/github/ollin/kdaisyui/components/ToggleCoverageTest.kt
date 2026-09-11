package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToggleCoverageTest {

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
    fun toggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertRendered(html, "toggle", "Toggle defaults")
        assertTrue(html.contains("type=\""), "Toggle sets type")
    }

    @Test
    fun toggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                id = htmlId("x-cov-id"),
                checked = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "toggle zz-extra", "Toggle all flags")
        assertCommonFlags(html, "Toggle", content = false)
        assertTrue(html.contains("type=\""), "Toggle sets type")
        assertTrue(html.contains("checked=\""), "Toggle sets checked")
        assertTrue(html.contains("disabled=\""), "Toggle sets disabled")
    }

    @Test
    fun toggle_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Primary,
            )
        }
        assertRendered(html, "toggle toggle-primary", "Toggle variant Primary")
    }

    @Test
    fun toggle_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Secondary,
            )
        }
        assertRendered(html, "toggle toggle-secondary", "Toggle variant Secondary")
    }

    @Test
    fun toggle_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Accent,
            )
        }
        assertRendered(html, "toggle toggle-accent", "Toggle variant Accent")
    }

    @Test
    fun toggle_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Neutral,
            )
        }
        assertRendered(html, "toggle toggle-neutral", "Toggle variant Neutral")
    }

    @Test
    fun toggle_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Success,
            )
        }
        assertRendered(html, "toggle toggle-success", "Toggle variant Success")
    }

    @Test
    fun toggle_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Warning,
            )
        }
        assertRendered(html, "toggle toggle-warning", "Toggle variant Warning")
    }

    @Test
    fun toggle_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Info,
            )
        }
        assertRendered(html, "toggle toggle-info", "Toggle variant Info")
    }

    @Test
    fun toggle_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Error,
            )
        }
        assertRendered(html, "toggle toggle-error", "Toggle variant Error")
    }

    @Test
    fun toggle_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xs,
            )
        }
        assertRendered(html, "toggle toggle-xs", "Toggle size Xs")
    }

    @Test
    fun toggle_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Sm,
            )
        }
        assertRendered(html, "toggle toggle-sm", "Toggle size Sm")
    }

    @Test
    fun toggle_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Md,
            )
        }
        assertRendered(html, "toggle toggle-md", "Toggle size Md")
    }

    @Test
    fun toggle_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Lg,
            )
        }
        assertRendered(html, "toggle toggle-lg", "Toggle size Lg")
    }

    @Test
    fun toggle_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xl,
            )
        }
        assertRendered(html, "toggle toggle-xl", "Toggle size Xl")
    }
}
