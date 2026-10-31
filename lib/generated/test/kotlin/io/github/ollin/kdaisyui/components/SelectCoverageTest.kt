package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SelectCoverageTest {

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
    fun select_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                content = { },
            )
        }
        assertRendered(html, Expected("Select defaults", "select", closes = "</select></div>"))
    }

    @Test
    fun select_all_flags() {
        val html = allFlags(Expected("Select all flags", "select select-ghost zz-extra", closes = "</select></div>")) { daisySelect(id = ID, ghost = true, disabled = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
        assertTrue(html.contains("disabled=\""), "Select sets disabled")
    }

    @Test
    fun select_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Neutral", "select select-neutral"))
    }

    @Test
    fun select_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Primary", "select select-primary"))
    }

    @Test
    fun select_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Secondary", "select select-secondary"))
    }

    @Test
    fun select_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Accent", "select select-accent"))
    }

    @Test
    fun select_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Info", "select select-info"))
    }

    @Test
    fun select_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Success", "select select-success"))
    }

    @Test
    fun select_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Warning", "select select-warning"))
    }

    @Test
    fun select_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                color = SelectColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Select color Error", "select select-error"))
    }

    @Test
    fun select_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Select size Xs", "select select-xs"))
    }

    @Test
    fun select_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Select size Sm", "select select-sm"))
    }

    @Test
    fun select_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Select size Md", "select select-md"))
    }

    @Test
    fun select_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Select size Lg", "select select-lg"))
    }

    @Test
    fun select_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(
                size = SelectSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Select size Xl", "select select-xl"))
    }

    @Test
    fun selectClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses()
        }
        assertRendered(html, Expected("SelectClasses defaults", "select"))
    }

    @Test
    fun selectClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                ghost = true,
            )
        }
        val expected = Expected("SelectClasses all flags", "select select-ghost", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun selectClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Neutral,
            )
        }
        assertRendered(html, Expected("SelectClasses color Neutral", "select select-neutral"))
    }

    @Test
    fun selectClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Primary,
            )
        }
        assertRendered(html, Expected("SelectClasses color Primary", "select select-primary"))
    }

    @Test
    fun selectClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Secondary,
            )
        }
        assertRendered(html, Expected("SelectClasses color Secondary", "select select-secondary"))
    }

    @Test
    fun selectClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Accent,
            )
        }
        assertRendered(html, Expected("SelectClasses color Accent", "select select-accent"))
    }

    @Test
    fun selectClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Info,
            )
        }
        assertRendered(html, Expected("SelectClasses color Info", "select select-info"))
    }

    @Test
    fun selectClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Success,
            )
        }
        assertRendered(html, Expected("SelectClasses color Success", "select select-success"))
    }

    @Test
    fun selectClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Warning,
            )
        }
        assertRendered(html, Expected("SelectClasses color Warning", "select select-warning"))
    }

    @Test
    fun selectClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                color = SelectColor.Error,
            )
        }
        assertRendered(html, Expected("SelectClasses color Error", "select select-error"))
    }

    @Test
    fun selectClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                size = SelectSize.Xs,
            )
        }
        assertRendered(html, Expected("SelectClasses size Xs", "select select-xs"))
    }

    @Test
    fun selectClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                size = SelectSize.Sm,
            )
        }
        assertRendered(html, Expected("SelectClasses size Sm", "select select-sm"))
    }

    @Test
    fun selectClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                size = SelectSize.Md,
            )
        }
        assertRendered(html, Expected("SelectClasses size Md", "select select-md"))
    }

    @Test
    fun selectClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                size = SelectSize.Lg,
            )
        }
        assertRendered(html, Expected("SelectClasses size Lg", "select select-lg"))
    }

    @Test
    fun selectClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisySelectClasses(
                size = SelectSize.Xl,
            )
        }
        assertRendered(html, Expected("SelectClasses size Xl", "select select-xl"))
    }
}
