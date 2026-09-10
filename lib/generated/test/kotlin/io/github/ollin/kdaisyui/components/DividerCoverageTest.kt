package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DividerCoverageTest {

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
    fun divider_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                content = { },
            )
        }
        assertRendered(html, "divider", "Divider defaults", closes = "</div></div>")
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
        assertRendered(html, "divider divider-end divider-horizontal divider-start divider-vertical zz-extra", "Divider all flags", closes = "</div></div>")
        assertCommonFlags(html, "Divider")
    }

    @Test
    fun divider_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "divider divider-neutral", "Divider variant Neutral")
    }

    @Test
    fun divider_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "divider divider-primary", "Divider variant Primary")
    }

    @Test
    fun divider_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "divider divider-secondary", "Divider variant Secondary")
    }

    @Test
    fun divider_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "divider divider-accent", "Divider variant Accent")
    }

    @Test
    fun divider_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "divider divider-success", "Divider variant Success")
    }

    @Test
    fun divider_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "divider divider-warning", "Divider variant Warning")
    }

    @Test
    fun divider_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "divider divider-info", "Divider variant Info")
    }

    @Test
    fun divider_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyDivider(
                variant = DividerVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "divider divider-error", "Divider variant Error")
    }
}
