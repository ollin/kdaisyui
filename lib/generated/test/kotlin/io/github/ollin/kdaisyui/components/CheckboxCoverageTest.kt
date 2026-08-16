package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CheckboxCoverageTest {

    @Test
    fun checkbox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox", actualClasses, "Checkbox defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox zz-extra", actualClasses, "Checkbox all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Checkbox id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Checkbox attrs")
    }

    @Test
    fun checkbox_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Primary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-primary", actualClasses, "Checkbox variant Primary")
    }

    @Test
    fun checkbox_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Secondary,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-secondary", actualClasses, "Checkbox variant Secondary")
    }

    @Test
    fun checkbox_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Accent,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-accent", actualClasses, "Checkbox variant Accent")
    }

    @Test
    fun checkbox_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Neutral,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-neutral", actualClasses, "Checkbox variant Neutral")
    }

    @Test
    fun checkbox_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Success,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-success", actualClasses, "Checkbox variant Success")
    }

    @Test
    fun checkbox_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Warning,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-warning", actualClasses, "Checkbox variant Warning")
    }

    @Test
    fun checkbox_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Info,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-info", actualClasses, "Checkbox variant Info")
    }

    @Test
    fun checkbox_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                variant = CheckboxVariant.Error,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-error", actualClasses, "Checkbox variant Error")
    }

    @Test
    fun checkbox_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xs,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-xs", actualClasses, "Checkbox size Xs")
    }

    @Test
    fun checkbox_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Sm,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-sm", actualClasses, "Checkbox size Sm")
    }

    @Test
    fun checkbox_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Md,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-md", actualClasses, "Checkbox size Md")
    }

    @Test
    fun checkbox_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Lg,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-lg", actualClasses, "Checkbox size Lg")
    }

    @Test
    fun checkbox_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(
                size = CheckboxSize.Xl,
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("checkbox checkbox-xl", actualClasses, "Checkbox size Xl")
    }
}
