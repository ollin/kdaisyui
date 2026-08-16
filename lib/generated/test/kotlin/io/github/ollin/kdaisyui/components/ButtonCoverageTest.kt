package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.ButtonType
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ButtonCoverageTest {

    @Test
    fun button_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn", actualClasses, "Button defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-active btn-block btn-circle btn-dash btn-disabled btn-ghost btn-link btn-outline btn-soft btn-square btn-wide zz-extra", actualClasses, "Button all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Button id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Button attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Button content")
    }

    @Test
    fun button_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-neutral", actualClasses, "Button variant Neutral")
    }

    @Test
    fun button_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-primary", actualClasses, "Button variant Primary")
    }

    @Test
    fun button_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-secondary", actualClasses, "Button variant Secondary")
    }

    @Test
    fun button_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-accent", actualClasses, "Button variant Accent")
    }

    @Test
    fun button_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-info", actualClasses, "Button variant Info")
    }

    @Test
    fun button_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-success", actualClasses, "Button variant Success")
    }

    @Test
    fun button_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-warning", actualClasses, "Button variant Warning")
    }

    @Test
    fun button_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                variant = ButtonVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-error", actualClasses, "Button variant Error")
    }

    @Test
    fun button_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-xs", actualClasses, "Button size Xs")
    }

    @Test
    fun button_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-sm", actualClasses, "Button size Sm")
    }

    @Test
    fun button_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-md", actualClasses, "Button size Md")
    }

    @Test
    fun button_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-lg", actualClasses, "Button size Lg")
    }

    @Test
    fun button_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                size = ButtonSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn btn-xl", actualClasses, "Button size Xl")
    }

    @Test
    fun button_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("btn", actualClasses, "Button text")
        assertTrue(html.contains("txtmark"), "Button text content")
    }
}
