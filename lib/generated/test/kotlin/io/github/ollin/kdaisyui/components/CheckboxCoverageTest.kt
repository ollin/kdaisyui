package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CheckboxCoverageTest {

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
    fun checkbox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertRendered(html, "checkbox", "Checkbox defaults")
        assertTrue(html.contains("type=\""), "Checkbox sets type")
    }

    @Test
    fun checkbox_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                id = htmlId("x-cov-id"),
                checked = true,
                disabled = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        assertRendered(html, "checkbox zz-extra", "Checkbox all flags")
        assertCommonFlags(html, "Checkbox", content = false)
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
        assertRendered(html, "checkbox checkbox-primary", "Checkbox color Primary")
    }

    @Test
    fun checkbox_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Secondary,
            )
        }
        assertRendered(html, "checkbox checkbox-secondary", "Checkbox color Secondary")
    }

    @Test
    fun checkbox_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Accent,
            )
        }
        assertRendered(html, "checkbox checkbox-accent", "Checkbox color Accent")
    }

    @Test
    fun checkbox_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Neutral,
            )
        }
        assertRendered(html, "checkbox checkbox-neutral", "Checkbox color Neutral")
    }

    @Test
    fun checkbox_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Success,
            )
        }
        assertRendered(html, "checkbox checkbox-success", "Checkbox color Success")
    }

    @Test
    fun checkbox_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Warning,
            )
        }
        assertRendered(html, "checkbox checkbox-warning", "Checkbox color Warning")
    }

    @Test
    fun checkbox_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Info,
            )
        }
        assertRendered(html, "checkbox checkbox-info", "Checkbox color Info")
    }

    @Test
    fun checkbox_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                color = CheckboxColor.Error,
            )
        }
        assertRendered(html, "checkbox checkbox-error", "Checkbox color Error")
    }

    @Test
    fun checkbox_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xs,
            )
        }
        assertRendered(html, "checkbox checkbox-xs", "Checkbox size Xs")
    }

    @Test
    fun checkbox_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Sm,
            )
        }
        assertRendered(html, "checkbox checkbox-sm", "Checkbox size Sm")
    }

    @Test
    fun checkbox_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Md,
            )
        }
        assertRendered(html, "checkbox checkbox-md", "Checkbox size Md")
    }

    @Test
    fun checkbox_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Lg,
            )
        }
        assertRendered(html, "checkbox checkbox-lg", "Checkbox size Lg")
    }

    @Test
    fun checkbox_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xl,
            )
        }
        assertRendered(html, "checkbox checkbox-xl", "Checkbox size Xl")
    }

    @Test
    fun checkboxClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        assertRendered(html, "checkbox", "CheckboxClasses defaults")
    }

    @Test
    fun checkboxClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses()
        }
        assertRendered(html, "checkbox", "CheckboxClasses all flags")
    }

    @Test
    fun checkboxClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Primary,
            )
        }
        assertRendered(html, "checkbox checkbox-primary", "CheckboxClasses color Primary")
    }

    @Test
    fun checkboxClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Secondary,
            )
        }
        assertRendered(html, "checkbox checkbox-secondary", "CheckboxClasses color Secondary")
    }

    @Test
    fun checkboxClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Accent,
            )
        }
        assertRendered(html, "checkbox checkbox-accent", "CheckboxClasses color Accent")
    }

    @Test
    fun checkboxClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Neutral,
            )
        }
        assertRendered(html, "checkbox checkbox-neutral", "CheckboxClasses color Neutral")
    }

    @Test
    fun checkboxClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Success,
            )
        }
        assertRendered(html, "checkbox checkbox-success", "CheckboxClasses color Success")
    }

    @Test
    fun checkboxClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Warning,
            )
        }
        assertRendered(html, "checkbox checkbox-warning", "CheckboxClasses color Warning")
    }

    @Test
    fun checkboxClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Info,
            )
        }
        assertRendered(html, "checkbox checkbox-info", "CheckboxClasses color Info")
    }

    @Test
    fun checkboxClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                color = CheckboxColor.Error,
            )
        }
        assertRendered(html, "checkbox checkbox-error", "CheckboxClasses color Error")
    }

    @Test
    fun checkboxClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Xs,
            )
        }
        assertRendered(html, "checkbox checkbox-xs", "CheckboxClasses size Xs")
    }

    @Test
    fun checkboxClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Sm,
            )
        }
        assertRendered(html, "checkbox checkbox-sm", "CheckboxClasses size Sm")
    }

    @Test
    fun checkboxClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Md,
            )
        }
        assertRendered(html, "checkbox checkbox-md", "CheckboxClasses size Md")
    }

    @Test
    fun checkboxClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Lg,
            )
        }
        assertRendered(html, "checkbox checkbox-lg", "CheckboxClasses size Lg")
    }

    @Test
    fun checkboxClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckboxClasses(
                size = CheckboxSize.Xl,
            )
        }
        assertRendered(html, "checkbox checkbox-xl", "CheckboxClasses size Xl")
    }
}
