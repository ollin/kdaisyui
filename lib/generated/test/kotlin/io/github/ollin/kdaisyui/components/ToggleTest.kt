package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class ToggleTest {

    @Test
    fun toggle_switch() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        val expectedClasses = "toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle (switch)")
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
            daisyToggle(size = ToggleSize.Xs)
        }
        val expectedClasses = "toggle toggle-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Sm)
        }
        val expectedClasses = "toggle toggle-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Md)
        }
        val expectedClasses = "toggle toggle-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Lg)
        }
        val expectedClasses = "toggle toggle-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Xl)
        }
        val expectedClasses = "toggle toggle-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Primary)
        }
        val expectedClasses = "toggle toggle-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Secondary)
        }
        val expectedClasses = "toggle toggle-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Accent)
        }
        val expectedClasses = "toggle toggle-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Neutral)
        }
        val expectedClasses = "toggle toggle-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Info)
        }
        val expectedClasses = "toggle toggle-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Success)
        }
        val expectedClasses = "toggle toggle-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Warning)
        }
        val expectedClasses = "toggle toggle-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }

    @Test
    fun colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(color = ToggleColor.Error)
        }
        val expectedClasses = "toggle toggle-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Colors")
    }
}
