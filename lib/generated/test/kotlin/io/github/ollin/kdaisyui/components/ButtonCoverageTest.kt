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
                dash = true,
                ghost = true,
                link = true,
                outline = true,
                soft = true,
                square = true,
                wide = true,
                disabled = true,
                type = ButtonType.button,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "btn btn-active btn-block btn-circle btn-dash btn-disabled btn-ghost btn-link btn-outline btn-soft btn-square btn-wide zz-extra", "Button all flags", closes = "</button></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Button id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Button attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Button content")
        assertTrue(html.contains("disabled=\""), "Button sets disabled")
        assertTrue(html.contains("type=\""), "Button sets type")
    }

    @Test
    fun button_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Neutral,
            )
        }
        assertRendered(html, "btn btn-neutral", "Button variant Neutral")
    }

    @Test
    fun button_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Primary,
            )
        }
        assertRendered(html, "btn btn-primary", "Button variant Primary")
    }

    @Test
    fun button_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Secondary,
            )
        }
        assertRendered(html, "btn btn-secondary", "Button variant Secondary")
    }

    @Test
    fun button_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Accent,
            )
        }
        assertRendered(html, "btn btn-accent", "Button variant Accent")
    }

    @Test
    fun button_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Info,
            )
        }
        assertRendered(html, "btn btn-info", "Button variant Info")
    }

    @Test
    fun button_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Success,
            )
        }
        assertRendered(html, "btn btn-success", "Button variant Success")
    }

    @Test
    fun button_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Warning,
            )
        }
        assertRendered(html, "btn btn-warning", "Button variant Warning")
    }

    @Test
    fun button_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Error,
            )
        }
        assertRendered(html, "btn btn-error", "Button variant Error")
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
