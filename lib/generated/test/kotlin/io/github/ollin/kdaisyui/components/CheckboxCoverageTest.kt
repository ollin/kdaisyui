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
        assertTrue(html.contains("id=\"x-cov-id\""), "Checkbox id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Checkbox attrs")
        assertTrue(html.contains("type=\""), "Checkbox sets type")
        assertTrue(html.contains("checked=\""), "Checkbox sets checked")
        assertTrue(html.contains("disabled=\""), "Checkbox sets disabled")
    }

    @Test
    fun checkbox_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Primary,
            )
        }
        assertRendered(html, "checkbox checkbox-primary", "Checkbox variant Primary")
    }

    @Test
    fun checkbox_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Secondary,
            )
        }
        assertRendered(html, "checkbox checkbox-secondary", "Checkbox variant Secondary")
    }

    @Test
    fun checkbox_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Accent,
            )
        }
        assertRendered(html, "checkbox checkbox-accent", "Checkbox variant Accent")
    }

    @Test
    fun checkbox_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Neutral,
            )
        }
        assertRendered(html, "checkbox checkbox-neutral", "Checkbox variant Neutral")
    }

    @Test
    fun checkbox_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Success,
            )
        }
        assertRendered(html, "checkbox checkbox-success", "Checkbox variant Success")
    }

    @Test
    fun checkbox_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Warning,
            )
        }
        assertRendered(html, "checkbox checkbox-warning", "Checkbox variant Warning")
    }

    @Test
    fun checkbox_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Info,
            )
        }
        assertRendered(html, "checkbox checkbox-info", "Checkbox variant Info")
    }

    @Test
    fun checkbox_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Error,
            )
        }
        assertRendered(html, "checkbox checkbox-error", "Checkbox variant Error")
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
}
