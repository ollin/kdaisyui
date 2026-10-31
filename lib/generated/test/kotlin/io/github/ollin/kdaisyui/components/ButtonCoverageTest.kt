package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.ButtonType
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ButtonCoverageTest {

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
    fun button_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton()
        }
        assertRendered(html, Expected("Button defaults", "btn", closes = "</button></div>"))
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
        val expected = Expected("Button all flags", "btn btn-active btn-disabled zz-extra", closes = "</button></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        assertRendered(html, Expected("Button color Neutral", "btn btn-neutral"))
    }

    @Test
    fun button_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Primary,
            )
        }
        assertRendered(html, Expected("Button color Primary", "btn btn-primary"))
    }

    @Test
    fun button_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Secondary,
            )
        }
        assertRendered(html, Expected("Button color Secondary", "btn btn-secondary"))
    }

    @Test
    fun button_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Accent,
            )
        }
        assertRendered(html, Expected("Button color Accent", "btn btn-accent"))
    }

    @Test
    fun button_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Info,
            )
        }
        assertRendered(html, Expected("Button color Info", "btn btn-info"))
    }

    @Test
    fun button_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Success,
            )
        }
        assertRendered(html, Expected("Button color Success", "btn btn-success"))
    }

    @Test
    fun button_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Warning,
            )
        }
        assertRendered(html, Expected("Button color Warning", "btn btn-warning"))
    }

    @Test
    fun button_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                color = ButtonColor.Error,
            )
        }
        assertRendered(html, Expected("Button color Error", "btn btn-error"))
    }

    @Test
    fun button_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xs,
            )
        }
        assertRendered(html, Expected("Button size Xs", "btn btn-xs"))
    }

    @Test
    fun button_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Sm,
            )
        }
        assertRendered(html, Expected("Button size Sm", "btn btn-sm"))
    }

    @Test
    fun button_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Md,
            )
        }
        assertRendered(html, Expected("Button size Md", "btn btn-md"))
    }

    @Test
    fun button_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Lg,
            )
        }
        assertRendered(html, Expected("Button size Lg", "btn btn-lg"))
    }

    @Test
    fun button_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xl,
            )
        }
        assertRendered(html, Expected("Button size Xl", "btn btn-xl"))
    }

    @Test
    fun button_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Outline,
            )
        }
        assertRendered(html, Expected("Button style Outline", "btn btn-outline"))
    }

    @Test
    fun button_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Dash,
            )
        }
        assertRendered(html, Expected("Button style Dash", "btn btn-dash"))
    }

    @Test
    fun button_style_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Soft,
            )
        }
        assertRendered(html, Expected("Button style Soft", "btn btn-soft"))
    }

    @Test
    fun button_style_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Ghost,
            )
        }
        assertRendered(html, Expected("Button style Ghost", "btn btn-ghost"))
    }

    @Test
    fun button_style_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                style = ButtonStyle.Link,
            )
        }
        assertRendered(html, Expected("Button style Link", "btn btn-link"))
    }

    @Test
    fun button_shape_square() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                shape = ButtonShape.Square,
            )
        }
        assertRendered(html, Expected("Button shape Square", "btn btn-square"))
    }

    @Test
    fun button_shape_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                shape = ButtonShape.Circle,
            )
        }
        assertRendered(html, Expected("Button shape Circle", "btn btn-circle"))
    }

    @Test
    fun button_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                width = ButtonWidth.Wide,
            )
        }
        assertRendered(html, Expected("Button width Wide", "btn btn-wide"))
    }

    @Test
    fun button_width_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                width = ButtonWidth.Block,
            )
        }
        assertRendered(html, Expected("Button width Block", "btn btn-block"))
    }

    @Test
    fun button_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("Button text", "btn"))
        assertTrue(html.contains("txtmark"), "Button text content")
    }

    @Test
    fun buttonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses()
        }
        assertRendered(html, Expected("ButtonClasses defaults", "btn"))
    }

    @Test
    fun buttonClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                active = true,
                disabled = true,
            )
        }
        val expected = Expected("ButtonClasses all flags", "btn btn-active btn-disabled", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun buttonClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Neutral,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Neutral", "btn btn-neutral"))
    }

    @Test
    fun buttonClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Primary,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Primary", "btn btn-primary"))
    }

    @Test
    fun buttonClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Secondary,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Secondary", "btn btn-secondary"))
    }

    @Test
    fun buttonClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Accent,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Accent", "btn btn-accent"))
    }

    @Test
    fun buttonClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Info,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Info", "btn btn-info"))
    }

    @Test
    fun buttonClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Success,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Success", "btn btn-success"))
    }

    @Test
    fun buttonClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Warning,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Warning", "btn btn-warning"))
    }

    @Test
    fun buttonClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                color = ButtonColor.Error,
            )
        }
        assertRendered(html, Expected("ButtonClasses color Error", "btn btn-error"))
    }

    @Test
    fun buttonClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Xs,
            )
        }
        assertRendered(html, Expected("ButtonClasses size Xs", "btn btn-xs"))
    }

    @Test
    fun buttonClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Sm,
            )
        }
        assertRendered(html, Expected("ButtonClasses size Sm", "btn btn-sm"))
    }

    @Test
    fun buttonClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Md,
            )
        }
        assertRendered(html, Expected("ButtonClasses size Md", "btn btn-md"))
    }

    @Test
    fun buttonClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Lg,
            )
        }
        assertRendered(html, Expected("ButtonClasses size Lg", "btn btn-lg"))
    }

    @Test
    fun buttonClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                size = ButtonSize.Xl,
            )
        }
        assertRendered(html, Expected("ButtonClasses size Xl", "btn btn-xl"))
    }

    @Test
    fun buttonClasses_style_outline() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Outline,
            )
        }
        assertRendered(html, Expected("ButtonClasses style Outline", "btn btn-outline"))
    }

    @Test
    fun buttonClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Dash,
            )
        }
        assertRendered(html, Expected("ButtonClasses style Dash", "btn btn-dash"))
    }

    @Test
    fun buttonClasses_style_soft() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Soft,
            )
        }
        assertRendered(html, Expected("ButtonClasses style Soft", "btn btn-soft"))
    }

    @Test
    fun buttonClasses_style_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Ghost,
            )
        }
        assertRendered(html, Expected("ButtonClasses style Ghost", "btn btn-ghost"))
    }

    @Test
    fun buttonClasses_style_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                style = ButtonStyle.Link,
            )
        }
        assertRendered(html, Expected("ButtonClasses style Link", "btn btn-link"))
    }

    @Test
    fun buttonClasses_shape_square() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                shape = ButtonShape.Square,
            )
        }
        assertRendered(html, Expected("ButtonClasses shape Square", "btn btn-square"))
    }

    @Test
    fun buttonClasses_shape_circle() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                shape = ButtonShape.Circle,
            )
        }
        assertRendered(html, Expected("ButtonClasses shape Circle", "btn btn-circle"))
    }

    @Test
    fun buttonClasses_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                width = ButtonWidth.Wide,
            )
        }
        assertRendered(html, Expected("ButtonClasses width Wide", "btn btn-wide"))
    }

    @Test
    fun buttonClasses_width_block() {
        val html = createHTML(prettyPrint = false).div {
            daisyButtonClasses(
                width = ButtonWidth.Block,
            )
        }
        assertRendered(html, Expected("ButtonClasses width Block", "btn btn-block"))
    }
}
