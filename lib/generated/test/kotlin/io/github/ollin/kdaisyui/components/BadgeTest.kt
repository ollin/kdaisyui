package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class BadgeTest {

    @Test
    fun badge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge")
    }

    @Test
    fun badge_sizes() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Xs) }
        }
        val expectedClasses = "badge badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge sizes")
    }

    @Test
    fun badge_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Sm) }
        }
        val expectedClasses = "badge badge-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge sizes")
    }

    @Test
    fun badge_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Md) }
        }
        val expectedClasses = "badge badge-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge sizes")
    }

    @Test
    fun badge_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Lg) }
        }
        val expectedClasses = "badge badge-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge sizes")
    }

    @Test
    fun badge_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Xl) }
        }
        val expectedClasses = "badge badge-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge sizes")
    }

    @Test
    fun badge_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary) }
        }
        val expectedClasses = "badge badge-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Secondary) }
        }
        val expectedClasses = "badge badge-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Accent) }
        }
        val expectedClasses = "badge badge-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral) }
        }
        val expectedClasses = "badge badge-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Info) }
        }
        val expectedClasses = "badge badge-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Success) }
        }
        val expectedClasses = "badge badge-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Warning) }
        }
        val expectedClasses = "badge badge-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Error) }
        }
        val expectedClasses = "badge badge-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with colors")
    }

    @Test
    fun badge_with_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Primary) }
        }
        val expectedClasses = "badge badge-primary badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Secondary) }
        }
        val expectedClasses = "badge badge-secondary badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Accent) }
        }
        val expectedClasses = "badge badge-accent badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Info) }
        }
        val expectedClasses = "badge badge-info badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Success) }
        }
        val expectedClasses = "badge badge-soft badge-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Warning) }
        }
        val expectedClasses = "badge badge-soft badge-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_soft_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Soft, color = BadgeColor.Error) }
        }
        val expectedClasses = "badge badge-error badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Primary) }
        }
        val expectedClasses = "badge badge-outline badge-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Secondary) }
        }
        val expectedClasses = "badge badge-outline badge-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Accent) }
        }
        val expectedClasses = "badge badge-accent badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Info) }
        }
        val expectedClasses = "badge badge-info badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Success) }
        }
        val expectedClasses = "badge badge-outline badge-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Warning) }
        }
        val expectedClasses = "badge badge-outline badge-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_outline_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline, color = BadgeColor.Error) }
        }
        val expectedClasses = "badge badge-error badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Primary) }
        }
        val expectedClasses = "badge badge-dash badge-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Secondary) }
        }
        val expectedClasses = "badge badge-dash badge-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Accent) }
        }
        val expectedClasses = "badge badge-accent badge-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Info) }
        }
        val expectedClasses = "badge badge-dash badge-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_5() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Success) }
        }
        val expectedClasses = "badge badge-dash badge-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_6() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Warning) }
        }
        val expectedClasses = "badge badge-dash badge-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun badge_with_dash_style_7() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Dash, color = BadgeColor.Error) }
        }
        val expectedClasses = "badge badge-dash badge-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge with dash style")
    }

    @Test
    fun neutral_badge_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral, outlineStyle = BadgeOutlineStyle.Outline) }
        }
        val expectedClasses = "badge badge-neutral badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral badge with outline or dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for neutral badge with outline or dash style")
    }

    @Test
    fun neutral_badge_with_outline_or_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Neutral, outlineStyle = BadgeOutlineStyle.Dash) }
        }
        val expectedClasses = "badge badge-dash badge-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral badge with outline or dash style")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for neutral badge with outline or dash style")
    }

    @Test
    fun badge_ghost() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(fillStyle = BadgeFillStyle.Ghost) }
        }
        val expectedClasses = "badge badge-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge ghost")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge ghost")
    }

    @Test
    fun empty_badge() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Lg) }
        }
        val expectedClasses = "badge badge-lg badge-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Empty badge")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Empty badge")
    }

    @Test
    fun empty_badge_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Md) }
        }
        val expectedClasses = "badge badge-md badge-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Empty badge")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Empty badge")
    }

    @Test
    fun empty_badge_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Sm) }
        }
        val expectedClasses = "badge badge-primary badge-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Empty badge")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Empty badge")
    }

    @Test
    fun empty_badge_4() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Primary, size = BadgeSize.Xs) }
        }
        val expectedClasses = "badge badge-primary badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Empty badge")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Empty badge")
    }

    @Test
    fun badge_in_a_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge in a button")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge in a button")
    }

    @Test
    fun badge_in_a_button() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(size = BadgeSize.Sm, color = BadgeColor.Secondary) }
        }
        val expectedClasses = "badge badge-secondary badge-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge in a button")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge in a button")
    }
}
