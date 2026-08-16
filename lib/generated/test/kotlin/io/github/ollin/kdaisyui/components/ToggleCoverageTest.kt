package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToggleCoverageTest {

    @Test
    fun toggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle", actualClasses, "Toggle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle zz-extra", actualClasses, "Toggle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Toggle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Toggle attrs")
    }

    @Test
    fun toggle_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-primary", actualClasses, "Toggle variant Primary")
    }

    @Test
    fun toggle_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-secondary", actualClasses, "Toggle variant Secondary")
    }

    @Test
    fun toggle_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-accent", actualClasses, "Toggle variant Accent")
    }

    @Test
    fun toggle_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-neutral", actualClasses, "Toggle variant Neutral")
    }

    @Test
    fun toggle_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-success", actualClasses, "Toggle variant Success")
    }

    @Test
    fun toggle_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-warning", actualClasses, "Toggle variant Warning")
    }

    @Test
    fun toggle_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-info", actualClasses, "Toggle variant Info")
    }

    @Test
    fun toggle_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                variant = ToggleVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-error", actualClasses, "Toggle variant Error")
    }

    @Test
    fun toggle_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-xs", actualClasses, "Toggle size Xs")
    }

    @Test
    fun toggle_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-sm", actualClasses, "Toggle size Sm")
    }

    @Test
    fun toggle_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-md", actualClasses, "Toggle size Md")
    }

    @Test
    fun toggle_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-lg", actualClasses, "Toggle size Lg")
    }

    @Test
    fun toggle_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(
                size = ToggleSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toggle toggle-xl", actualClasses, "Toggle size Xl")
    }
}
