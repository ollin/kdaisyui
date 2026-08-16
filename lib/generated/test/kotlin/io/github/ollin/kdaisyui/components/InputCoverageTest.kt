package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class InputCoverageTest {

    @Test
    fun input_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input", actualClasses, "Input defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-ghost zz-extra", actualClasses, "Input all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Input id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Input attrs")
        assertTrue(html.contains("placeholder=\"x\""), "Input placeholder")
        assertTrue(html.contains("value=\"x\""), "Input value")
    }

    @Test
    fun input_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-neutral", actualClasses, "Input variant Neutral")
    }

    @Test
    fun input_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-primary", actualClasses, "Input variant Primary")
    }

    @Test
    fun input_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-secondary", actualClasses, "Input variant Secondary")
    }

    @Test
    fun input_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-accent", actualClasses, "Input variant Accent")
    }

    @Test
    fun input_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-info", actualClasses, "Input variant Info")
    }

    @Test
    fun input_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-success", actualClasses, "Input variant Success")
    }

    @Test
    fun input_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-warning", actualClasses, "Input variant Warning")
    }

    @Test
    fun input_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                variant = InputVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-error", actualClasses, "Input variant Error")
    }

    @Test
    fun input_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-xs", actualClasses, "Input size Xs")
    }

    @Test
    fun input_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-sm", actualClasses, "Input size Sm")
    }

    @Test
    fun input_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-md", actualClasses, "Input size Md")
    }

    @Test
    fun input_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-lg", actualClasses, "Input size Lg")
    }

    @Test
    fun input_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("input input-xl", actualClasses, "Input size Xl")
    }
}
