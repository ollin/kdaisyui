package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet
import kotlinx.html.label

class FileInputTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun file_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput()
        }
        assertDocumented(html, "input", "file-input", "File input")
    }

    @Test
    fun file_input_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(ghost = true)
        }
        assertDocumented(html, "input", "file-input file-input-ghost", "File input ghost")
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
            daisyFileInput(size = FileInputSize.Xs)
        }
        assertDocumented(html, "input", "file-input file-input-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Sm)
        }
        assertDocumented(html, "input", "file-input file-input-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Md)
        }
        assertDocumented(html, "input", "file-input file-input-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Lg)
        }
        assertDocumented(html, "input", "file-input file-input-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Xl)
        }
        assertDocumented(html, "input", "file-input file-input-xl", "Sizes")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Primary)
        }
        assertDocumented(html, "input", "file-input file-input-primary", "Primary color")
    }

    @Test
    fun primary_color_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Secondary)
        }
        assertDocumented(html, "input", "file-input file-input-secondary", "Primary color")
    }

    @Test
    fun primary_color_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Accent)
        }
        assertDocumented(html, "input", "file-input file-input-accent", "Primary color")
    }

    @Test
    fun primary_color_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Neutral)
        }
        assertDocumented(html, "input", "file-input file-input-neutral", "Primary color")
    }

    @Test
    fun primary_color_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Info)
        }
        assertDocumented(html, "input", "file-input file-input-info", "Primary color")
    }

    @Test
    fun primary_color_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Success)
        }
        assertDocumented(html, "input", "file-input file-input-success", "Primary color")
    }

    @Test
    fun primary_color_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Warning)
        }
        assertDocumented(html, "input", "file-input file-input-warning", "Primary color")
    }

    @Test
    fun primary_color_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Error)
        }
        assertDocumented(html, "input", "file-input file-input-error", "Primary color")
    }
}
