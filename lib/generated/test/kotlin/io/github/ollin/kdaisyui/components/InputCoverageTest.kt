package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class InputCoverageTest {

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
    fun input_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertRendered(html, Expected("Input defaults", "input"))
        assertTrue(html.contains("type=\""), "Input sets type")
    }

    @Test
    fun input_all_flags() {
        val html = allFlags(Expected("Input all flags", "input input-ghost zz-extra", content = false)) { daisyInput(id = ID, ghost = true, disabled = true, placeholder = "x", value = "x", extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("placeholder=\"x\""), "Input placeholder")
        assertTrue(html.contains("value=\"x\""), "Input value")
        assertTrue(html.contains("type=\""), "Input sets type")
        assertTrue(html.contains("placeholder=\""), "Input sets placeholder")
        assertTrue(html.contains("value=\""), "Input sets value")
        assertTrue(html.contains("disabled=\""), "Input sets disabled")
    }

    @Test
    fun input_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Neutral,
            )
        }
        assertRendered(html, Expected("Input color Neutral", "input input-neutral"))
    }

    @Test
    fun input_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Primary,
            )
        }
        assertRendered(html, Expected("Input color Primary", "input input-primary"))
    }

    @Test
    fun input_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Secondary,
            )
        }
        assertRendered(html, Expected("Input color Secondary", "input input-secondary"))
    }

    @Test
    fun input_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Accent,
            )
        }
        assertRendered(html, Expected("Input color Accent", "input input-accent"))
    }

    @Test
    fun input_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Info,
            )
        }
        assertRendered(html, Expected("Input color Info", "input input-info"))
    }

    @Test
    fun input_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Success,
            )
        }
        assertRendered(html, Expected("Input color Success", "input input-success"))
    }

    @Test
    fun input_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Warning,
            )
        }
        assertRendered(html, Expected("Input color Warning", "input input-warning"))
    }

    @Test
    fun input_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                color = InputColor.Error,
            )
        }
        assertRendered(html, Expected("Input color Error", "input input-error"))
    }

    @Test
    fun input_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xs,
            )
        }
        assertRendered(html, Expected("Input size Xs", "input input-xs"))
    }

    @Test
    fun input_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Sm,
            )
        }
        assertRendered(html, Expected("Input size Sm", "input input-sm"))
    }

    @Test
    fun input_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Md,
            )
        }
        assertRendered(html, Expected("Input size Md", "input input-md"))
    }

    @Test
    fun input_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Lg,
            )
        }
        assertRendered(html, Expected("Input size Lg", "input input-lg"))
    }

    @Test
    fun input_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(
                size = InputSize.Xl,
            )
        }
        assertRendered(html, Expected("Input size Xl", "input input-xl"))
    }

    @Test
    fun inputClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses()
        }
        assertRendered(html, Expected("InputClasses defaults", "input"))
    }

    @Test
    fun inputClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                ghost = true,
            )
        }
        val expected = Expected("InputClasses all flags", "input input-ghost", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun inputClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Neutral,
            )
        }
        assertRendered(html, Expected("InputClasses color Neutral", "input input-neutral"))
    }

    @Test
    fun inputClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Primary,
            )
        }
        assertRendered(html, Expected("InputClasses color Primary", "input input-primary"))
    }

    @Test
    fun inputClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Secondary,
            )
        }
        assertRendered(html, Expected("InputClasses color Secondary", "input input-secondary"))
    }

    @Test
    fun inputClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Accent,
            )
        }
        assertRendered(html, Expected("InputClasses color Accent", "input input-accent"))
    }

    @Test
    fun inputClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Info,
            )
        }
        assertRendered(html, Expected("InputClasses color Info", "input input-info"))
    }

    @Test
    fun inputClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Success,
            )
        }
        assertRendered(html, Expected("InputClasses color Success", "input input-success"))
    }

    @Test
    fun inputClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Warning,
            )
        }
        assertRendered(html, Expected("InputClasses color Warning", "input input-warning"))
    }

    @Test
    fun inputClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                color = InputColor.Error,
            )
        }
        assertRendered(html, Expected("InputClasses color Error", "input input-error"))
    }

    @Test
    fun inputClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                size = InputSize.Xs,
            )
        }
        assertRendered(html, Expected("InputClasses size Xs", "input input-xs"))
    }

    @Test
    fun inputClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                size = InputSize.Sm,
            )
        }
        assertRendered(html, Expected("InputClasses size Sm", "input input-sm"))
    }

    @Test
    fun inputClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                size = InputSize.Md,
            )
        }
        assertRendered(html, Expected("InputClasses size Md", "input input-md"))
    }

    @Test
    fun inputClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                size = InputSize.Lg,
            )
        }
        assertRendered(html, Expected("InputClasses size Lg", "input input-lg"))
    }

    @Test
    fun inputClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyInputClasses(
                size = InputSize.Xl,
            )
        }
        assertRendered(html, Expected("InputClasses size Xl", "input input-xl"))
    }
}
