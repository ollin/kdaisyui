package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextareaCoverageTest {

    @Test
    fun textarea_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea", actualClasses, "Textarea defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-ghost zz-extra", actualClasses, "Textarea all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-neutral", actualClasses, "Textarea variant Neutral")
    }

    @Test
    fun textarea_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-primary", actualClasses, "Textarea variant Primary")
    }

    @Test
    fun textarea_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-secondary", actualClasses, "Textarea variant Secondary")
    }

    @Test
    fun textarea_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-accent", actualClasses, "Textarea variant Accent")
    }

    @Test
    fun textarea_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-info", actualClasses, "Textarea variant Info")
    }

    @Test
    fun textarea_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-success", actualClasses, "Textarea variant Success")
    }

    @Test
    fun textarea_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-warning", actualClasses, "Textarea variant Warning")
    }

    @Test
    fun textarea_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                variant = TextareaVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-error", actualClasses, "Textarea variant Error")
    }

    @Test
    fun textarea_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-xs", actualClasses, "Textarea size Xs")
    }

    @Test
    fun textarea_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-sm", actualClasses, "Textarea size Sm")
    }

    @Test
    fun textarea_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-md", actualClasses, "Textarea size Md")
    }

    @Test
    fun textarea_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-lg", actualClasses, "Textarea size Lg")
    }

    @Test
    fun textarea_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("textarea textarea-xl", actualClasses, "Textarea size Xl")
    }
}
