package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class BadgeCoverageTest {

    @Test
    fun badge_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge", actualClasses, "Badge defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-dash badge-ghost badge-outline badge-soft zz-extra", actualClasses, "Badge all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Badge id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Badge attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Badge content")
    }

    @Test
    fun badge_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-neutral", actualClasses, "Badge variant Neutral")
    }

    @Test
    fun badge_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-primary", actualClasses, "Badge variant Primary")
    }

    @Test
    fun badge_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-secondary", actualClasses, "Badge variant Secondary")
    }

    @Test
    fun badge_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-accent", actualClasses, "Badge variant Accent")
    }

    @Test
    fun badge_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-info", actualClasses, "Badge variant Info")
    }

    @Test
    fun badge_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-success", actualClasses, "Badge variant Success")
    }

    @Test
    fun badge_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-warning", actualClasses, "Badge variant Warning")
    }

    @Test
    fun badge_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                variant = BadgeVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-error", actualClasses, "Badge variant Error")
    }

    @Test
    fun badge_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-xs", actualClasses, "Badge size Xs")
    }

    @Test
    fun badge_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-sm", actualClasses, "Badge size Sm")
    }

    @Test
    fun badge_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-md", actualClasses, "Badge size Md")
    }

    @Test
    fun badge_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-lg", actualClasses, "Badge size Lg")
    }

    @Test
    fun badge_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge badge-xl", actualClasses, "Badge size Xl")
    }

    @Test
    fun badge_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("badge", actualClasses, "Badge text")
        assertTrue(html.contains("txtmark"), "Badge text content")
    }
}
