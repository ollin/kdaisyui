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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
        assertCommonFlags(html, "Textarea")
    }

    @Test
    fun textarea_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-neutral", "Textarea color Neutral")
    }

    @Test
    fun textarea_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-primary", "Textarea color Primary")
    }

    @Test
    fun textarea_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-secondary", "Textarea color Secondary")
    }

    @Test
    fun textarea_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-accent", "Textarea color Accent")
    }

    @Test
    fun textarea_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Info,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-info", "Textarea color Info")
    }

    @Test
    fun textarea_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Success,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-success", "Textarea color Success")
    }

    @Test
    fun textarea_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-warning", "Textarea color Warning")
    }

    @Test
    fun textarea_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Error,
                content = { },
            )
        }
        assertRendered(html, "textarea textarea-error", "Textarea color Error")
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
