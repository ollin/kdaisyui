package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ButtonTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Button")
    }

    @Test
    fun button_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        assertDocumented(html, "button", "btn btn-xs", "Button sizes")
    }

    @Test
    fun button_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        assertDocumented(html, "button", "btn btn-sm", "Button sizes")
    }

    @Test
    fun button_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        assertDocumented(html, "button", "btn btn-lg", "Button sizes")
    }

    @Test
    fun button_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl) {
            }
        }
        assertDocumented(html, "button", "btn btn-xl", "Button sizes")
    }

    @Test
    fun responsive_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, extraClasses = "sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl") {
            }
        }
        assertDocumented(html, "button", "btn btn-xs lg:btn-lg md:btn-md sm:btn-sm xl:btn-xl", "Responsive button")
    }

    @Test
    fun buttons_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral", "Buttons colors")
    }

    @Test
    fun buttons_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary", "Buttons colors")
    }

    @Test
    fun buttons_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-secondary", "Buttons colors")
    }

    @Test
    fun buttons_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent", "Buttons colors")
    }

    @Test
    fun buttons_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-info", "Buttons colors")
    }

    @Test
    fun buttons_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-success", "Buttons colors")
    }

    @Test
    fun buttons_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-warning", "Buttons colors")
    }

    @Test
    fun buttons_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-error", "Buttons colors")
    }

    @Test
    fun soft_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft) {
            }
        }
        assertDocumented(html, "button", "btn btn-soft", "Soft buttons")
    }

    @Test
    fun soft_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary btn-soft", "Soft buttons")
    }

    @Test
    fun soft_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-secondary btn-soft", "Soft buttons")
    }

    @Test
    fun soft_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent btn-soft", "Soft buttons")
    }

    @Test
    fun soft_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-info btn-soft", "Soft buttons")
    }

    @Test
    fun soft_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-soft btn-success", "Soft buttons")
    }

    @Test
    fun soft_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-soft btn-warning", "Soft buttons")
    }

    @Test
    fun soft_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-error btn-soft", "Soft buttons")
    }

    @Test
    fun outline_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline", "Outline buttons")
    }

    @Test
    fun outline_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline btn-primary", "Outline buttons")
    }

    @Test
    fun outline_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline btn-secondary", "Outline buttons")
    }

    @Test
    fun outline_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent btn-outline", "Outline buttons")
    }

    @Test
    fun outline_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-info btn-outline", "Outline buttons")
    }

    @Test
    fun outline_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline btn-success", "Outline buttons")
    }

    @Test
    fun outline_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline btn-warning", "Outline buttons")
    }

    @Test
    fun outline_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-error btn-outline", "Outline buttons")
    }

    @Test
    fun dash_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash", "Dash buttons")
    }

    @Test
    fun dash_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-primary", "Dash buttons")
    }

    @Test
    fun dash_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-secondary", "Dash buttons")
    }

    @Test
    fun dash_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent btn-dash", "Dash buttons")
    }

    @Test
    fun dash_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-info", "Dash buttons")
    }

    @Test
    fun dash_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-success", "Dash buttons")
    }

    @Test
    fun dash_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-warning", "Dash buttons")
    }

    @Test
    fun dash_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-error", "Dash buttons")
    }

    @Test
    fun neutral_button_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral, style = ButtonStyle.Outline) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral btn-outline", "neutral button with outline or dash style")
    }

    @Test
    fun neutral_button_with_outline_or_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral, style = ButtonStyle.Dash) {
            }
        }
        assertDocumented(html, "button", "btn btn-dash btn-neutral", "neutral button with outline or dash style")
    }

    @Test
    fun active_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active", "Active buttons")
    }

    @Test
    fun active_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-primary", "Active buttons")
    }

    @Test
    fun active_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Secondary) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-secondary", "Active buttons")
    }

    @Test
    fun active_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Accent) {
            }
        }
        assertDocumented(html, "button", "btn btn-accent btn-active", "Active buttons")
    }

    @Test
    fun active_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Info) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-info", "Active buttons")
    }

    @Test
    fun active_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-success", "Active buttons")
    }

    @Test
    fun active_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Warning) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-warning", "Active buttons")
    }

    @Test
    fun active_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Error) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-error", "Active buttons")
    }

    @Test
    fun buttons_ghost_and_button_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost", "Buttons ghost and button link")
    }

    @Test
    fun buttons_ghost_and_button_link_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Link) {
            }
        }
        assertDocumented(html, "button", "btn btn-link", "Buttons ghost and button link")
    }

    @Test
    fun wide_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(width = ButtonWidth.Wide) {
            }
        }
        assertDocumented(html, "button", "btn btn-wide", "Wide button")
    }

    @Test
    fun disabled_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(disabled = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-disabled", "Disabled buttons")
    }

    @Test
    fun square_button_and_circle_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square) {
            }
        }
        assertDocumented(html, "button", "btn btn-square", "Square button and circle button")
    }

    @Test
    fun square_button_and_circle_button_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle", "Square button and circle button")
    }

    @Test
    fun button_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(width = ButtonWidth.Block) {
            }
        }
        assertDocumented(html, "button", "btn btn-block", "Button block")
    }

    @Test
    fun button_with_loading_spinner_daisyLoading() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner) {
            }
        }
        assertDocumented(html, "span", "loading loading-spinner", "Button with loading spinner")
    }
}
