package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SelectCoverageTest {

    @Test
    fun select_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select", actualClasses, "Select defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-ghost zz-extra", actualClasses, "Select all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Select id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Select attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Select content")
    }

    @Test
    fun select_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Neutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-neutral", actualClasses, "Select variant Neutral")
    }

    @Test
    fun select_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-primary", actualClasses, "Select variant Primary")
    }

    @Test
    fun select_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-secondary", actualClasses, "Select variant Secondary")
    }

    @Test
    fun select_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-accent", actualClasses, "Select variant Accent")
    }

    @Test
    fun select_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-info", actualClasses, "Select variant Info")
    }

    @Test
    fun select_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-success", actualClasses, "Select variant Success")
    }

    @Test
    fun select_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-warning", actualClasses, "Select variant Warning")
    }

    @Test
    fun select_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                variant = SelectVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-error", actualClasses, "Select variant Error")
    }

    @Test
    fun select_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-xs", actualClasses, "Select size Xs")
    }

    @Test
    fun select_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-sm", actualClasses, "Select size Sm")
    }

    @Test
    fun select_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-md", actualClasses, "Select size Md")
    }

    @Test
    fun select_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-lg", actualClasses, "Select size Lg")
    }

    @Test
    fun select_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("select select-xl", actualClasses, "Select size Xl")
    }
}
