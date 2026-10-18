package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.label

class CheckboxTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertDocumented(html, "input", "checkbox", "Checkbox")
    }

    @Test
    fun with_fieldset_and_label_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "With fieldset and label")
    }

    @Test
    fun with_fieldset_and_label_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        assertDocumented(html, "legend", "fieldset-legend", "With fieldset and label")
    }

    @Test
    fun with_fieldset_and_label_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyLabelClasses() }
        }
        assertDocumented(html, "label", "label", "With fieldset and label")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Xs)
        }
        assertDocumented(html, "input", "checkbox checkbox-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Sm)
        }
        assertDocumented(html, "input", "checkbox checkbox-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Md)
        }
        assertDocumented(html, "input", "checkbox checkbox-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Lg)
        }
        assertDocumented(html, "input", "checkbox checkbox-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Xl)
        }
        assertDocumented(html, "input", "checkbox checkbox-xl", "Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Primary)
        }
        assertDocumented(html, "input", "checkbox checkbox-primary", "Colors")
    }

    @Test
    fun colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Secondary)
        }
        assertDocumented(html, "input", "checkbox checkbox-secondary", "Colors")
    }

    @Test
    fun colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Accent)
        }
        assertDocumented(html, "input", "checkbox checkbox-accent", "Colors")
    }

    @Test
    fun colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Neutral)
        }
        assertDocumented(html, "input", "checkbox checkbox-neutral", "Colors")
    }

    @Test
    fun colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Info)
        }
        assertDocumented(html, "input", "checkbox checkbox-info", "Colors")
    }

    @Test
    fun colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Success)
        }
        assertDocumented(html, "input", "checkbox checkbox-success", "Colors")
    }

    @Test
    fun colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Warning)
        }
        assertDocumented(html, "input", "checkbox checkbox-warning", "Colors")
    }

    @Test
    fun colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Error)
        }
        assertDocumented(html, "input", "checkbox checkbox-error", "Colors")
    }
}
