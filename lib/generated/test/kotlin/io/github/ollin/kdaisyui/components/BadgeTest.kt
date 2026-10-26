package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class BadgeTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun badge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        assertDocumented(html, "span", "badge", "Badge")
    }

    @Test
    fun badge_sizes() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Xs) }
        }
        assertDocumented(html, "div", "badge badge-xs", "Badge sizes")
    }

    @Test
    fun badge_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Sm) }
        }
        assertDocumented(html, "div", "badge badge-sm", "Badge sizes")
    }

    @Test
    fun badge_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Md) }
        }
        assertDocumented(html, "div", "badge badge-md", "Badge sizes")
    }

    @Test
    fun badge_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Lg) }
        }
        assertDocumented(html, "div", "badge badge-lg", "Badge sizes")
    }

    @Test
    fun badge_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Xl) }
        }
        assertDocumented(html, "div", "badge badge-xl", "Badge sizes")
    }

    @Test
    fun badge_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary) }
        }
        assertDocumented(html, "div", "badge badge-primary", "Badge with colors")
    }

    @Test
    fun badge_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-secondary", "Badge with colors")
    }

    @Test
    fun badge_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Accent) }
        }
        assertDocumented(html, "div", "badge badge-accent", "Badge with colors")
    }

    @Test
    fun badge_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral) }
        }
        assertDocumented(html, "div", "badge badge-neutral", "Badge with colors")
    }

    @Test
    fun badge_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Info) }
        }
        assertDocumented(html, "div", "badge badge-info", "Badge with colors")
    }

    @Test
    fun badge_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Success) }
        }
        assertDocumented(html, "div", "badge badge-success", "Badge with colors")
    }

    @Test
    fun badge_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Warning) }
        }
        assertDocumented(html, "div", "badge badge-warning", "Badge with colors")
    }

    @Test
    fun badge_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Error) }
        }
        assertDocumented(html, "div", "badge badge-error", "Badge with colors")
    }

    @Test
    fun badge_with_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Primary) }
        }
        assertDocumented(html, "div", "badge badge-primary badge-soft", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-secondary badge-soft", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Accent) }
        }
        assertDocumented(html, "div", "badge badge-accent badge-soft", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Info) }
        }
        assertDocumented(html, "div", "badge badge-info badge-soft", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Success) }
        }
        assertDocumented(html, "div", "badge badge-soft badge-success", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Warning) }
        }
        assertDocumented(html, "div", "badge badge-soft badge-warning", "Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Error) }
        }
        assertDocumented(html, "div", "badge badge-error badge-soft", "Badge with soft style")
    }

    @Test
    fun badge_with_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Primary) }
        }
        assertDocumented(html, "div", "badge badge-outline badge-primary", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-outline badge-secondary", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Accent) }
        }
        assertDocumented(html, "div", "badge badge-accent badge-outline", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Info) }
        }
        assertDocumented(html, "div", "badge badge-info badge-outline", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Success) }
        }
        assertDocumented(html, "div", "badge badge-outline badge-success", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Warning) }
        }
        assertDocumented(html, "div", "badge badge-outline badge-warning", "Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Error) }
        }
        assertDocumented(html, "div", "badge badge-error badge-outline", "Badge with outline style")
    }

    @Test
    fun badge_with_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Primary) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-primary", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-secondary", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Accent) }
        }
        assertDocumented(html, "div", "badge badge-accent badge-dash", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Info) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-info", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Success) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-success", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Warning) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-warning", "Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Error) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-error", "Badge with dash style")
    }

    @Test
    fun neutral_badge_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral, outlineStyle = BadgeOutlineStyle.Outline) }
        }
        assertDocumented(html, "div", "badge badge-neutral badge-outline", "neutral badge with outline or dash style")
    }

    @Test
    fun neutral_badge_with_outline_or_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral, outlineStyle = BadgeOutlineStyle.Dash) }
        }
        assertDocumented(html, "div", "badge badge-dash badge-neutral", "neutral badge with outline or dash style")
    }

    @Test
    fun badge_ghost() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Ghost) }
        }
        assertDocumented(html, "div", "badge badge-ghost", "Badge ghost")
    }

    @Test
    fun empty_badge() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Lg) }
        }
        assertDocumented(html, "div", "badge badge-lg badge-primary", "Empty badge")
    }

    @Test
    fun empty_badge_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Md) }
        }
        assertDocumented(html, "div", "badge badge-md badge-primary", "Empty badge")
    }

    @Test
    fun empty_badge_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Sm) }
        }
        assertDocumented(html, "div", "badge badge-primary badge-sm", "Empty badge")
    }

    @Test
    fun empty_badge_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Xs) }
        }
        assertDocumented(html, "div", "badge badge-primary badge-xs", "Empty badge")
    }

    @Test
    fun badge_in_a_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Badge in a button")
    }

    @Test
    fun badge_in_a_button() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Sm, color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-secondary badge-sm", "Badge in a button")
    }
}
