package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TextareaCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun textarea_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea defaults", "textarea", closes = "</textarea></div>"))
    }

    @Test
    fun textarea_all_flags() {
        val html = allFlags(Expected("Textarea all flags", "textarea textarea-ghost zz-extra", closes = "</textarea></div>")) { daisyTextarea(id = ID, ghost = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun textarea_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Neutral", "textarea textarea-neutral"))
    }

    @Test
    fun textarea_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Primary", "textarea textarea-primary"))
    }

    @Test
    fun textarea_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Secondary", "textarea textarea-secondary"))
    }

    @Test
    fun textarea_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Accent", "textarea textarea-accent"))
    }

    @Test
    fun textarea_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Info", "textarea textarea-info"))
    }

    @Test
    fun textarea_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Success", "textarea textarea-success"))
    }

    @Test
    fun textarea_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Warning", "textarea textarea-warning"))
    }

    @Test
    fun textarea_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                color = TextareaColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea color Error", "textarea textarea-error"))
    }

    @Test
    fun textarea_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea size Xs", "textarea textarea-xs"))
    }

    @Test
    fun textarea_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea size Sm", "textarea textarea-sm"))
    }

    @Test
    fun textarea_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea size Md", "textarea textarea-md"))
    }

    @Test
    fun textarea_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea size Lg", "textarea textarea-lg"))
    }

    @Test
    fun textarea_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(
                size = TextareaSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Textarea size Xl", "textarea textarea-xl"))
    }

    @Test
    fun textareaClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses()
        }
        assertRendered(html, Expected("TextareaClasses defaults", "textarea"))
    }

    @Test
    fun textareaClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                ghost = true,
            )
        }
        val expected = Expected("TextareaClasses all flags", "textarea textarea-ghost", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun textareaClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Neutral,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Neutral", "textarea textarea-neutral"))
    }

    @Test
    fun textareaClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Primary,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Primary", "textarea textarea-primary"))
    }

    @Test
    fun textareaClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Secondary,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Secondary", "textarea textarea-secondary"))
    }

    @Test
    fun textareaClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Accent,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Accent", "textarea textarea-accent"))
    }

    @Test
    fun textareaClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Info,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Info", "textarea textarea-info"))
    }

    @Test
    fun textareaClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Success,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Success", "textarea textarea-success"))
    }

    @Test
    fun textareaClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Warning,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Warning", "textarea textarea-warning"))
    }

    @Test
    fun textareaClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                color = TextareaColor.Error,
            )
        }
        assertRendered(html, Expected("TextareaClasses color Error", "textarea textarea-error"))
    }

    @Test
    fun textareaClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Xs,
            )
        }
        assertRendered(html, Expected("TextareaClasses size Xs", "textarea textarea-xs"))
    }

    @Test
    fun textareaClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Sm,
            )
        }
        assertRendered(html, Expected("TextareaClasses size Sm", "textarea textarea-sm"))
    }

    @Test
    fun textareaClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Md,
            )
        }
        assertRendered(html, Expected("TextareaClasses size Md", "textarea textarea-md"))
    }

    @Test
    fun textareaClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Lg,
            )
        }
        assertRendered(html, Expected("TextareaClasses size Lg", "textarea textarea-lg"))
    }

    @Test
    fun textareaClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextareaClasses(
                size = TextareaSize.Xl,
            )
        }
        assertRendered(html, Expected("TextareaClasses size Xl", "textarea textarea-xl"))
    }
}
