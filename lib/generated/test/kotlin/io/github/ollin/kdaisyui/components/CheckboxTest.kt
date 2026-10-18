package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class CheckboxTest {

    @Test
    fun checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox() {
            }
        }
        val expectedClasses = "checkbox"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Checkbox")
    }

    @Test
    fun with_fieldset_and_label_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun with_fieldset_and_label_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        val expectedClasses = "fieldset-legend"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun with_fieldset_and_label_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and label")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Xs) {
            }
        }
        val expectedClasses = "checkbox checkbox-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Sm) {
            }
        }
        val expectedClasses = "checkbox checkbox-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Md) {
            }
        }
        val expectedClasses = "checkbox checkbox-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Lg) {
            }
        }
        val expectedClasses = "checkbox checkbox-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Xl) {
            }
        }
        val expectedClasses = "checkbox checkbox-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Primary) {
            }
        }
        val expectedClasses = "checkbox checkbox-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Secondary) {
            }
        }
        val expectedClasses = "checkbox checkbox-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Accent) {
            }
        }
        val expectedClasses = "checkbox checkbox-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Neutral) {
            }
        }
        val expectedClasses = "checkbox checkbox-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Info) {
            }
        }
        val expectedClasses = "checkbox checkbox-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Success) {
            }
        }
        val expectedClasses = "checkbox checkbox-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Warning) {
            }
        }
        val expectedClasses = "checkbox checkbox-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(color = CheckboxColor.Error) {
            }
        }
        val expectedClasses = "checkbox checkbox-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }
}
