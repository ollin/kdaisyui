package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextareaCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun textarea_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                content = { },
            )
        }
        assertRendered(html, "textarea", "Textarea defaults", closes = "</textarea></div>")
    }

    @Test
    fun textarea_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                id = htmlId("x-cov-id"),
                ghost = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "textarea textarea-ghost zz-extra", "Textarea all flags", closes = "</textarea></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Textarea id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Textarea attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Textarea content")
    }

    @Test
    fun textarea_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-neutral", "Textarea variant Neutral")
    }

    @Test
    fun textarea_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-primary", "Textarea variant Primary")
    }

    @Test
    fun textarea_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-secondary", "Textarea variant Secondary")
    }

    @Test
    fun textarea_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-accent", "Textarea variant Accent")
    }

    @Test
    fun textarea_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-info", "Textarea variant Info")
    }

    @Test
    fun textarea_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-success", "Textarea variant Success")
    }

    @Test
    fun textarea_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-warning", "Textarea variant Warning")
    }

    @Test
    fun textarea_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-error", "Textarea variant Error")
    }

    @Test
    fun textarea_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-xs", "Textarea size Xs")
    }

    @Test
    fun textarea_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-sm", "Textarea size Sm")
    }

    @Test
    fun textarea_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Md,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-md", "Textarea size Md")
    }

    @Test
    fun textarea_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-lg", "Textarea size Lg")
    }

    @Test
    fun textarea_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-xl", "Textarea size Xl")
    }
}
