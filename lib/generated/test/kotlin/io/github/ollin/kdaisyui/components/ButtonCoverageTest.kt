package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.ButtonType
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ButtonCoverageTest {

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
    fun button_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton()
        }
        assertRendered(html, "btn", "Button defaults", closes = "</button></div>")
    }

    @Test
    fun button_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                id = htmlId("x-cov-id"),
                active = true,
                disabled = true,
                type = ButtonType.button,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "btn btn-active btn-disabled zz-extra", "Button all flags", closes = "</button></div>")
        assertCommonFlags(html, "Button")
        assertTrue(html.contains("disabled=\""), "Button sets disabled")
        assertTrue(html.contains("type=\""), "Button sets type")
    }

    @Test
    fun button_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Neutral,
            )
        }
        assertRendered(html, "btn btn-neutral", "Button color Neutral")
    }

    @Test
    fun button_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Primary,
            )
        }
        assertRendered(html, "btn btn-primary", "Button color Primary")
    }

    @Test
    fun button_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Secondary,
            )
        }
        assertRendered(html, "btn btn-secondary", "Button color Secondary")
    }

    @Test
    fun button_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Accent,
            )
        }
        assertRendered(html, "btn btn-accent", "Button color Accent")
    }

    @Test
    fun button_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Info,
            )
        }
        assertRendered(html, "btn btn-info", "Button color Info")
    }

    @Test
    fun button_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Success,
            )
        }
        assertRendered(html, "btn btn-success", "Button color Success")
    }

    @Test
    fun button_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Warning,
            )
        }
        assertRendered(html, "btn btn-warning", "Button color Warning")
    }

    @Test
    fun button_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Error,
            )
        }
        assertRendered(html, "btn btn-error", "Button color Error")
    }

    @Test
    fun button_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xs,
            )
        }
        assertRendered(html, "btn btn-xs", "Button size Xs")
    }

    @Test
    fun button_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Sm,
            )
        }
        assertRendered(html, "btn btn-sm", "Button size Sm")
    }

    @Test
    fun button_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Md,
            )
        }
        assertRendered(html, "btn btn-md", "Button size Md")
    }

    @Test
    fun button_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Lg,
            )
        }
        assertRendered(html, "btn btn-lg", "Button size Lg")
    }

    @Test
    fun button_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xl,
            )
        }
        assertRendered(html, "btn btn-xl", "Button size Xl")
    }

    @Test
    fun button_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Outline,
            )
        }
        assertRendered(html, "btn btn-outline", "Button style Outline")
    }

    @Test
    fun button_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Dash,
            )
        }
        assertRendered(html, "btn btn-dash", "Button style Dash")
    }

    @Test
    fun button_style_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Soft,
            )
        }
        assertRendered(html, "btn btn-soft", "Button style Soft")
    }

    @Test
    fun button_style_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Ghost,
            )
        }
        assertRendered(html, "btn btn-ghost", "Button style Ghost")
    }

    @Test
    fun button_style_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Link,
            )
        }
        assertRendered(html, "btn btn-link", "Button style Link")
    }

    @Test
    fun button_shape_square() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                shape = ButtonShape.Square,
            )
        }
        assertRendered(html, "btn btn-square", "Button shape Square")
    }

    @Test
    fun button_shape_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                shape = ButtonShape.Circle,
            )
        }
        assertRendered(html, "btn btn-circle", "Button shape Circle")
    }

    @Test
    fun button_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                width = ButtonWidth.Wide,
            )
        }
        assertRendered(html, "btn btn-wide", "Button width Wide")
    }

    @Test
    fun button_width_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                width = ButtonWidth.Block,
            )
        }
        assertRendered(html, "btn btn-block", "Button width Block")
    }

    @Test
    fun button_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                text = "txtmark",
            )
        }
        assertRendered(html, "btn", "Button text")
        assertTrue(html.contains("txtmark"), "Button text content")
    }

    @Test
    fun buttonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses()
        }
        assertRendered(html, "btn", "ButtonClasses defaults")
    }

    @Test
    fun buttonClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                active = true,
                disabled = true,
            )
        }
        assertRendered(html, "btn btn-active btn-disabled", "ButtonClasses all flags")
    }

    @Test
    fun buttonClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Neutral,
            )
        }
        assertRendered(html, "btn btn-neutral", "ButtonClasses color Neutral")
    }

    @Test
    fun buttonClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Primary,
            )
        }
        assertRendered(html, "btn btn-primary", "ButtonClasses color Primary")
    }

    @Test
    fun buttonClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Secondary,
            )
        }
        assertRendered(html, "btn btn-secondary", "ButtonClasses color Secondary")
    }

    @Test
    fun buttonClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Accent,
            )
        }
        assertRendered(html, "btn btn-accent", "ButtonClasses color Accent")
    }

    @Test
    fun buttonClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Info,
            )
        }
        assertRendered(html, "btn btn-info", "ButtonClasses color Info")
    }

    @Test
    fun buttonClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Success,
            )
        }
        assertRendered(html, "btn btn-success", "ButtonClasses color Success")
    }

    @Test
    fun buttonClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Warning,
            )
        }
        assertRendered(html, "btn btn-warning", "ButtonClasses color Warning")
    }

    @Test
    fun buttonClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Error,
            )
        }
        assertRendered(html, "btn btn-error", "ButtonClasses color Error")
    }

    @Test
    fun buttonClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Xs,
            )
        }
        assertRendered(html, "btn btn-xs", "ButtonClasses size Xs")
    }

    @Test
    fun buttonClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Sm,
            )
        }
        assertRendered(html, "btn btn-sm", "ButtonClasses size Sm")
    }

    @Test
    fun buttonClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Md,
            )
        }
        assertRendered(html, "btn btn-md", "ButtonClasses size Md")
    }

    @Test
    fun buttonClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Lg,
            )
        }
        assertRendered(html, "btn btn-lg", "ButtonClasses size Lg")
    }

    @Test
    fun buttonClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Xl,
            )
        }
        assertRendered(html, "btn btn-xl", "ButtonClasses size Xl")
    }

    @Test
    fun buttonClasses_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Outline,
            )
        }
        assertRendered(html, "btn btn-outline", "ButtonClasses style Outline")
    }

    @Test
    fun buttonClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Dash,
            )
        }
        assertRendered(html, "btn btn-dash", "ButtonClasses style Dash")
    }

    @Test
    fun buttonClasses_style_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Soft,
            )
        }
        assertRendered(html, "btn btn-soft", "ButtonClasses style Soft")
    }

    @Test
    fun buttonClasses_style_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Ghost,
            )
        }
        assertRendered(html, "btn btn-ghost", "ButtonClasses style Ghost")
    }

    @Test
    fun buttonClasses_style_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Link,
            )
        }
        assertRendered(html, "btn btn-link", "ButtonClasses style Link")
    }

    @Test
    fun buttonClasses_shape_square() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                shape = ButtonShape.Square,
            )
        }
        assertRendered(html, "btn btn-square", "ButtonClasses shape Square")
    }

    @Test
    fun buttonClasses_shape_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                shape = ButtonShape.Circle,
            )
        }
        assertRendered(html, "btn btn-circle", "ButtonClasses shape Circle")
    }

    @Test
    fun buttonClasses_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                width = ButtonWidth.Wide,
            )
        }
        assertRendered(html, "btn btn-wide", "ButtonClasses width Wide")
    }

    @Test
    fun buttonClasses_width_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                width = ButtonWidth.Block,
            )
        }
        assertRendered(html, "btn btn-block", "ButtonClasses width Block")
    }
}
