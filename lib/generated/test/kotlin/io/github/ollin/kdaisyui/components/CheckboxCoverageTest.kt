package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CheckboxCoverageTest {

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
    fun checkbox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertRendered(html, Expected("Checkbox defaults", "checkbox"))
        assertTrue(html.contains("type=\""), "Checkbox sets type")
    }

    @Test
    fun checkbox_all_flags() {
        val html = allFlags(Expected("Checkbox all flags", "checkbox zz-extra", content = false)) { daisyCheckbox(id = ID, checked = true, disabled = true, extraClasses = EXTRA, attrs = ATTRS) }
        assertTrue(html.contains("type=\""), "Checkbox sets type")
        assertTrue(html.contains("checked=\""), "Checkbox sets checked")
        assertTrue(html.contains("disabled=\""), "Checkbox sets disabled")
    }

    @Test
    fun checkbox_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Primary,
            )
        }
        assertRendered(html, Expected("Checkbox color Primary", "checkbox checkbox-primary"))
    }

    @Test
    fun checkbox_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Secondary,
            )
        }
        assertRendered(html, Expected("Checkbox color Secondary", "checkbox checkbox-secondary"))
    }

    @Test
    fun checkbox_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Accent,
            )
        }
        assertRendered(html, Expected("Checkbox color Accent", "checkbox checkbox-accent"))
    }

    @Test
    fun checkbox_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Neutral,
            )
        }
        assertRendered(html, Expected("Checkbox color Neutral", "checkbox checkbox-neutral"))
    }

    @Test
    fun checkbox_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Success,
            )
        }
        assertRendered(html, Expected("Checkbox color Success", "checkbox checkbox-success"))
    }

    @Test
    fun checkbox_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Warning,
            )
        }
        assertRendered(html, Expected("Checkbox color Warning", "checkbox checkbox-warning"))
    }

    @Test
    fun checkbox_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Info,
            )
        }
        assertRendered(html, Expected("Checkbox color Info", "checkbox checkbox-info"))
    }

    @Test
    fun checkbox_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Error,
            )
        }
        assertRendered(html, Expected("Checkbox color Error", "checkbox checkbox-error"))
    }

    @Test
    fun checkbox_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xs,
            )
        }
        assertRendered(html, Expected("Checkbox size Xs", "checkbox checkbox-xs"))
    }

    @Test
    fun checkbox_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Sm,
            )
        }
        assertRendered(html, Expected("Checkbox size Sm", "checkbox checkbox-sm"))
    }

    @Test
    fun checkbox_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Md,
            )
        }
        assertRendered(html, Expected("Checkbox size Md", "checkbox checkbox-md"))
    }

    @Test
    fun checkbox_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Lg,
            )
        }
        assertRendered(html, Expected("Checkbox size Lg", "checkbox checkbox-lg"))
    }

    @Test
    fun checkbox_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xl,
            )
        }
        assertRendered(html, Expected("Checkbox size Xl", "checkbox checkbox-xl"))
    }

    @Test
    fun checkboxClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        assertRendered(html, Expected("CheckboxClasses defaults", "checkbox"))
    }

    @Test
    fun checkboxClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        val expected = Expected("CheckboxClasses all flags", "checkbox", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun checkboxClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Primary,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Primary", "checkbox checkbox-primary"))
    }

    @Test
    fun checkboxClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Secondary,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Secondary", "checkbox checkbox-secondary"))
    }

    @Test
    fun checkboxClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Accent,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Accent", "checkbox checkbox-accent"))
    }

    @Test
    fun checkboxClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Neutral,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Neutral", "checkbox checkbox-neutral"))
    }

    @Test
    fun checkboxClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Success,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Success", "checkbox checkbox-success"))
    }

    @Test
    fun checkboxClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Warning,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Warning", "checkbox checkbox-warning"))
    }

    @Test
    fun checkboxClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Info,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Info", "checkbox checkbox-info"))
    }

    @Test
    fun checkboxClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Error,
            )
        }
        assertRendered(html, Expected("CheckboxClasses color Error", "checkbox checkbox-error"))
    }

    @Test
    fun checkboxClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Xs,
            )
        }
        assertRendered(html, Expected("CheckboxClasses size Xs", "checkbox checkbox-xs"))
    }

    @Test
    fun checkboxClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Sm,
            )
        }
        assertRendered(html, Expected("CheckboxClasses size Sm", "checkbox checkbox-sm"))
    }

    @Test
    fun checkboxClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Md,
            )
        }
        assertRendered(html, Expected("CheckboxClasses size Md", "checkbox checkbox-md"))
    }

    @Test
    fun checkboxClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Lg,
            )
        }
        assertRendered(html, Expected("CheckboxClasses size Lg", "checkbox checkbox-lg"))
    }

    @Test
    fun checkboxClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Xl,
            )
        }
        assertRendered(html, Expected("CheckboxClasses size Xl", "checkbox checkbox-xl"))
    }
}
