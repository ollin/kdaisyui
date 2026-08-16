package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DividerCoverageTest {

    @Test
    fun divider_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider", actualClasses, "Divider defaults")
    }

    @Test
    fun divider_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                id = htmlId("x-cov-id"),
                end = true,
                horizontal = true,
                start = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-end divider-horizontal divider-start divider-vertical zz-extra", actualClasses, "Divider all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Divider id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Divider attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Divider content")
    }

    @Test
    fun divider_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Neutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-neutral", actualClasses, "Divider variant Neutral")
    }

    @Test
    fun divider_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-primary", actualClasses, "Divider variant Primary")
    }

    @Test
    fun divider_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-secondary", actualClasses, "Divider variant Secondary")
    }

    @Test
    fun divider_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-accent", actualClasses, "Divider variant Accent")
    }

    @Test
    fun divider_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-success", actualClasses, "Divider variant Success")
    }

    @Test
    fun divider_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-warning", actualClasses, "Divider variant Warning")
    }

    @Test
    fun divider_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-info", actualClasses, "Divider variant Info")
    }

    @Test
    fun divider_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("divider divider-error", actualClasses, "Divider variant Error")
    }
}
