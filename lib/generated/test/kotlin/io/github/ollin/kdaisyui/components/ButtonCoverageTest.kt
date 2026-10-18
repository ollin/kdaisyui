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
                block = true,
                circle = true,
                square = true,
                wide = true,
                disabled = true,
                type = ButtonType.button,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "btn btn-active btn-block btn-circle btn-disabled btn-square btn-wide zz-extra", "Button all flags", closes = "</button></div>")
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
    fun button_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                text = "txtmark",
            )
        }
        assertRendered(html, "btn", "Button text")
        assertTrue(html.contains("txtmark"), "Button text content")
    }
}
