package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class FileInputTest {

    @Test
    fun file_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput() {
            }
        }
        val expectedClasses = "file-input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for File input")
    }

    @Test
    fun file_input_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(ghost = true) {
            }
        }
        val expectedClasses = "file-input file-input-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for File input ghost")
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
            daisyFileInput(size = FileInputSize.Xs) {
            }
        }
        val expectedClasses = "file-input file-input-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Sm) {
            }
        }
        val expectedClasses = "file-input file-input-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Md) {
            }
        }
        val expectedClasses = "file-input file-input-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Lg) {
            }
        }
        val expectedClasses = "file-input file-input-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(size = FileInputSize.Xl) {
            }
        }
        val expectedClasses = "file-input file-input-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Primary) {
            }
        }
        val expectedClasses = "file-input file-input-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Secondary) {
            }
        }
        val expectedClasses = "file-input file-input-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Accent) {
            }
        }
        val expectedClasses = "file-input file-input-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Neutral) {
            }
        }
        val expectedClasses = "file-input file-input-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Info) {
            }
        }
        val expectedClasses = "file-input file-input-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Success) {
            }
        }
        val expectedClasses = "file-input file-input-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Warning) {
            }
        }
        val expectedClasses = "file-input file-input-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }

    @Test
    fun primary_color_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyFileInput(color = FileInputColor.Error) {
            }
        }
        val expectedClasses = "file-input file-input-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
    }
}
