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

    @Test
    fun textareaClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses()
        }
        assertRendered(html, "textarea", "TextareaClasses defaults")
    }

    @Test
    fun textareaClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                ghost = true,
            )
        }
        assertRendered(html, "textarea textarea-ghost", "TextareaClasses all flags")
    }

    @Test
    fun textareaClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Neutral,
            )
        }
        assertRendered(html, "textarea textarea-neutral", "TextareaClasses color Neutral")
    }

    @Test
    fun textareaClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Primary,
            )
        }
        assertRendered(html, "textarea textarea-primary", "TextareaClasses color Primary")
    }

    @Test
    fun textareaClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Secondary,
            )
        }
        assertRendered(html, "textarea textarea-secondary", "TextareaClasses color Secondary")
    }

    @Test
    fun textareaClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Accent,
            )
        }
        assertRendered(html, "textarea textarea-accent", "TextareaClasses color Accent")
    }

    @Test
    fun textareaClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Info,
            )
        }
        assertRendered(html, "textarea textarea-info", "TextareaClasses color Info")
    }

    @Test
    fun textareaClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Success,
            )
        }
        assertRendered(html, "textarea textarea-success", "TextareaClasses color Success")
    }

    @Test
    fun textareaClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Warning,
            )
        }
        assertRendered(html, "textarea textarea-warning", "TextareaClasses color Warning")
    }

    @Test
    fun textareaClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Error,
            )
        }
        assertRendered(html, "textarea textarea-error", "TextareaClasses color Error")
    }

    @Test
    fun textareaClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Xs,
            )
        }
        assertRendered(html, "textarea textarea-xs", "TextareaClasses size Xs")
    }

    @Test
    fun textareaClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Sm,
            )
        }
        assertRendered(html, "textarea textarea-sm", "TextareaClasses size Sm")
    }

    @Test
    fun textareaClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Md,
            )
        }
        assertRendered(html, "textarea textarea-md", "TextareaClasses size Md")
    }

    @Test
    fun textareaClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Lg,
            )
        }
        assertRendered(html, "textarea textarea-lg", "TextareaClasses size Lg")
    }

    @Test
    fun textareaClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Xl,
            )
        }
        assertRendered(html, "textarea textarea-xl", "TextareaClasses size Xl")
    }
}
