package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class BadgeCoverageTest {

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
    fun badge_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge()
        }
        assertRendered(html, "badge", "Badge defaults", closes = "</span></div>")
    }

    @Test
    fun badge_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                id = htmlId("x-cov-id"),
                dash = true,
                ghost = true,
                outline = true,
                soft = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "badge badge-dash badge-ghost badge-outline badge-soft zz-extra", "Badge all flags", closes = "</span></div>")
        assertCommonFlags(html, "Badge")
    }

    @Test
    fun badge_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Neutral,
            )
        }
        assertRendered(html, "badge badge-neutral", "Badge variant Neutral")
    }

    @Test
    fun badge_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Primary,
            )
        }
        assertRendered(html, "badge badge-primary", "Badge variant Primary")
    }

    @Test
    fun badge_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Secondary,
            )
        }
        assertRendered(html, "badge badge-secondary", "Badge variant Secondary")
    }

    @Test
    fun badge_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Accent,
            )
        }
        assertRendered(html, "badge badge-accent", "Badge variant Accent")
    }

    @Test
    fun badge_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Info,
            )
        }
        assertRendered(html, "badge badge-info", "Badge variant Info")
    }

    @Test
    fun badge_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Success,
            )
        }
        assertRendered(html, "badge badge-success", "Badge variant Success")
    }

    @Test
    fun badge_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Warning,
            )
        }
        assertRendered(html, "badge badge-warning", "Badge variant Warning")
    }

    @Test
    fun badge_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Error,
            )
        }
        assertRendered(html, "badge badge-error", "Badge variant Error")
    }

    @Test
    fun badge_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xs,
            )
        }
        assertRendered(html, "badge badge-xs", "Badge size Xs")
    }

    @Test
    fun badge_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Sm,
            )
        }
        assertRendered(html, "badge badge-sm", "Badge size Sm")
    }

    @Test
    fun badge_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Md,
            )
        }
        assertRendered(html, "badge badge-md", "Badge size Md")
    }

    @Test
    fun badge_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Lg,
            )
        }
        assertRendered(html, "badge badge-lg", "Badge size Lg")
    }

    @Test
    fun badge_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xl,
            )
        }
        assertRendered(html, "badge badge-xl", "Badge size Xl")
    }

    @Test
    fun badge_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                text = "txtmark",
            )
        }
        assertRendered(html, "badge", "Badge text")
        assertTrue(html.contains("txtmark"), "Badge text content")
    }
}
