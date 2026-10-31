package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class BadgeCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun badge_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge()
        }
        assertRendered(html, Expected("Badge defaults", "badge", closes = "</span></div>"))
    }

    @Test
    fun badge_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Badge all flags", "badge zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun badge_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Neutral,
            )
        }
        assertRendered(html, Expected("Badge color Neutral", "badge badge-neutral"))
    }

    @Test
    fun badge_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Primary,
            )
        }
        assertRendered(html, Expected("Badge color Primary", "badge badge-primary"))
    }

    @Test
    fun badge_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Secondary,
            )
        }
        assertRendered(html, Expected("Badge color Secondary", "badge badge-secondary"))
    }

    @Test
    fun badge_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Accent,
            )
        }
        assertRendered(html, Expected("Badge color Accent", "badge badge-accent"))
    }

    @Test
    fun badge_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Info,
            )
        }
        assertRendered(html, Expected("Badge color Info", "badge badge-info"))
    }

    @Test
    fun badge_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Success,
            )
        }
        assertRendered(html, Expected("Badge color Success", "badge badge-success"))
    }

    @Test
    fun badge_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Warning,
            )
        }
        assertRendered(html, Expected("Badge color Warning", "badge badge-warning"))
    }

    @Test
    fun badge_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                color = BadgeColor.Error,
            )
        }
        assertRendered(html, Expected("Badge color Error", "badge badge-error"))
    }

    @Test
    fun badge_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xs,
            )
        }
        assertRendered(html, Expected("Badge size Xs", "badge badge-xs"))
    }

    @Test
    fun badge_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Sm,
            )
        }
        assertRendered(html, Expected("Badge size Sm", "badge badge-sm"))
    }

    @Test
    fun badge_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Md,
            )
        }
        assertRendered(html, Expected("Badge size Md", "badge badge-md"))
    }

    @Test
    fun badge_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Lg,
            )
        }
        assertRendered(html, Expected("Badge size Lg", "badge badge-lg"))
    }

    @Test
    fun badge_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                size = BadgeSize.Xl,
            )
        }
        assertRendered(html, Expected("Badge size Xl", "badge badge-xl"))
    }

    @Test
    fun badge_outlineStyle_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                outlineStyle = BadgeOutlineStyle.Outline,
            )
        }
        assertRendered(html, Expected("Badge outlineStyle Outline", "badge badge-outline"))
    }

    @Test
    fun badge_outlineStyle_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                outlineStyle = BadgeOutlineStyle.Dash,
            )
        }
        assertRendered(html, Expected("Badge outlineStyle Dash", "badge badge-dash"))
    }

    @Test
    fun badge_fillStyle_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                fillStyle = BadgeFillStyle.Soft,
            )
        }
        assertRendered(html, Expected("Badge fillStyle Soft", "badge badge-soft"))
    }

    @Test
    fun badge_fillStyle_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                fillStyle = BadgeFillStyle.Ghost,
            )
        }
        assertRendered(html, Expected("Badge fillStyle Ghost", "badge badge-ghost"))
    }

    @Test
    fun badge_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Badge text", "badge"))
        assertTrue(html.contains("txtmark"), "Badge text content")
    }

    @Test
    fun badgeClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses()
        }
        assertRendered(html, Expected("BadgeClasses defaults", "badge"))
    }

    @Test
    fun badgeClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses()
        }
        val expected = Expected("BadgeClasses all flags", "badge", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun badgeClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Neutral,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Neutral", "badge badge-neutral"))
    }

    @Test
    fun badgeClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Primary,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Primary", "badge badge-primary"))
    }

    @Test
    fun badgeClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Secondary,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Secondary", "badge badge-secondary"))
    }

    @Test
    fun badgeClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Accent,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Accent", "badge badge-accent"))
    }

    @Test
    fun badgeClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Info,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Info", "badge badge-info"))
    }

    @Test
    fun badgeClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Success,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Success", "badge badge-success"))
    }

    @Test
    fun badgeClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Warning,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Warning", "badge badge-warning"))
    }

    @Test
    fun badgeClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                color = BadgeColor.Error,
            )
        }
        assertRendered(html, Expected("BadgeClasses color Error", "badge badge-error"))
    }

    @Test
    fun badgeClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                size = BadgeSize.Xs,
            )
        }
        assertRendered(html, Expected("BadgeClasses size Xs", "badge badge-xs"))
    }

    @Test
    fun badgeClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                size = BadgeSize.Sm,
            )
        }
        assertRendered(html, Expected("BadgeClasses size Sm", "badge badge-sm"))
    }

    @Test
    fun badgeClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                size = BadgeSize.Md,
            )
        }
        assertRendered(html, Expected("BadgeClasses size Md", "badge badge-md"))
    }

    @Test
    fun badgeClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                size = BadgeSize.Lg,
            )
        }
        assertRendered(html, Expected("BadgeClasses size Lg", "badge badge-lg"))
    }

    @Test
    fun badgeClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                size = BadgeSize.Xl,
            )
        }
        assertRendered(html, Expected("BadgeClasses size Xl", "badge badge-xl"))
    }

    @Test
    fun badgeClasses_outlineStyle_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                outlineStyle = BadgeOutlineStyle.Outline,
            )
        }
        assertRendered(html, Expected("BadgeClasses outlineStyle Outline", "badge badge-outline"))
    }

    @Test
    fun badgeClasses_outlineStyle_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                outlineStyle = BadgeOutlineStyle.Dash,
            )
        }
        assertRendered(html, Expected("BadgeClasses outlineStyle Dash", "badge badge-dash"))
    }

    @Test
    fun badgeClasses_fillStyle_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                fillStyle = BadgeFillStyle.Soft,
            )
        }
        assertRendered(html, Expected("BadgeClasses fillStyle Soft", "badge badge-soft"))
    }

    @Test
    fun badgeClasses_fillStyle_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadgeClasses(
                fillStyle = BadgeFillStyle.Ghost,
            )
        }
        assertRendered(html, Expected("BadgeClasses fillStyle Ghost", "badge badge-ghost"))
    }
}
