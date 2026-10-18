package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.label

class ToggleTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun toggle_switch() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertDocumented(html, "input", "toggle", "Toggle (switch)")
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
            daisyToggle(size = ToggleSize.Xs)
        }
        assertDocumented(html, "input", "toggle toggle-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Sm)
        }
        assertDocumented(html, "input", "toggle toggle-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Md)
        }
        assertDocumented(html, "input", "toggle toggle-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Lg)
        }
        assertDocumented(html, "input", "toggle toggle-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Xl)
        }
        assertDocumented(html, "input", "toggle toggle-xl", "Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Primary)
        }
        assertDocumented(html, "input", "toggle toggle-primary", "Colors")
    }

    @Test
    fun colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Secondary)
        }
        assertDocumented(html, "input", "toggle toggle-secondary", "Colors")
    }

    @Test
    fun colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Accent)
        }
        assertDocumented(html, "input", "toggle toggle-accent", "Colors")
    }

    @Test
    fun colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Neutral)
        }
        assertDocumented(html, "input", "toggle toggle-neutral", "Colors")
    }

    @Test
    fun colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Info)
        }
        assertDocumented(html, "input", "toggle toggle-info", "Colors")
    }

    @Test
    fun colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Success)
        }
        assertDocumented(html, "input", "toggle toggle-success", "Colors")
    }

    @Test
    fun colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Warning)
        }
        assertDocumented(html, "input", "toggle toggle-warning", "Colors")
    }

    @Test
    fun colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Error)
        }
        assertDocumented(html, "input", "toggle toggle-error", "Colors")
    }
}
