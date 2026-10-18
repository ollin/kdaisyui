package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ButtonTest {

    @Test
    fun button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button")
    }

    @Test
    fun button_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        val expectedClasses = "btn btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button sizes")
    }

    @Test
    fun button_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        val expectedClasses = "btn btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button sizes")
    }

    @Test
    fun button_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        val expectedClasses = "btn btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button sizes")
    }

    @Test
    fun button_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl) {
            }
        }
        val expectedClasses = "btn btn-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button sizes")
    }

    @Test
    fun responsive_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, extraClasses = "sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl") {
            }
        }
        val expectedClasses = "btn btn-xs lg:btn-lg md:btn-md sm:btn-sm xl:btn-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive button")
    }

    @Test
    fun buttons_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        val expectedClasses = "btn btn-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun buttons_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons colors")
    }

    @Test
    fun soft_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft) {
            }
        }
        val expectedClasses = "btn btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-secondary btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-info btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-soft btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-soft btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun soft_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Soft, color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-error btn-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Soft buttons")
    }

    @Test
    fun outline_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline) {
            }
        }
        val expectedClasses = "btn btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-outline btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-outline btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-info btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-outline btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-outline btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun outline_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline, color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-error btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Outline buttons")
    }

    @Test
    fun dash_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash) {
            }
        }
        val expectedClasses = "btn btn-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-dash btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-dash btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent btn-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-dash btn-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-dash btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-dash btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun dash_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Dash, color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-dash btn-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dash buttons")
    }

    @Test
    fun neutral_button_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral, style = ButtonStyle.Outline) {
            }
        }
        val expectedClasses = "btn btn-neutral btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral button with outline or dash style")
    }

    @Test
    fun neutral_button_with_outline_or_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral, style = ButtonStyle.Dash) {
            }
        }
        val expectedClasses = "btn btn-dash btn-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral button with outline or dash style")
    }

    @Test
    fun active_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true) {
            }
        }
        val expectedClasses = "btn btn-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-active btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-active btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Accent) {
            }
        }
        val expectedClasses = "btn btn-accent btn-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-active btn-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-active btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Warning) {
            }
        }
        val expectedClasses = "btn btn-active btn-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun active_buttons_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true, color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-active btn-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Active buttons")
    }

    @Test
    fun buttons_ghost_and_button_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons ghost and button link")
    }

    @Test
    fun buttons_ghost_and_button_link_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Link) {
            }
        }
        val expectedClasses = "btn btn-link"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Buttons ghost and button link")
    }

    @Test
    fun wide_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(width = ButtonWidth.Wide) {
            }
        }
        val expectedClasses = "btn btn-wide"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Wide button")
    }

    @Test
    fun disabled_buttons() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(disabled = true) {
            }
        }
        val expectedClasses = "btn btn-disabled"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Disabled buttons")
    }

    @Test
    fun square_button_and_circle_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square) {
            }
        }
        val expectedClasses = "btn btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Square button and circle button")
    }

    @Test
    fun square_button_and_circle_button_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle) {
            }
        }
        val expectedClasses = "btn btn-circle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Square button and circle button")
    }

    @Test
    fun button_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(width = ButtonWidth.Block) {
            }
        }
        val expectedClasses = "btn btn-block"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button block")
    }

    @Test
    fun button_with_loading_spinner_daisyLoading() {
        val html = createHTML(prettyPrint = false).div {
            daisyLoading(style = LoadingStyle.Spinner) {
            }
        }
        val expectedClasses = "loading loading-spinner"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Button with loading spinner")
    }
}
