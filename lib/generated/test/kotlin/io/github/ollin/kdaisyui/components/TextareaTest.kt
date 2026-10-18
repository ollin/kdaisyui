package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class TextareaTest {

    @Test
    fun textarea() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        val expectedClasses = "textarea"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(ghost = true) {
            }
        }
        val expectedClasses = "textarea textarea-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost (no background)")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Ghost (no background)")
    }

    @Test
    fun with_form_control_and_labels_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With form control and labels")
        assertEquals("fieldset", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With form control and labels")
    }

    @Test
    fun with_form_control_and_labels_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        val expectedClasses = "fieldset-legend"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With form control and labels")
        assertEquals("legend", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With form control and labels")
    }

    @Test
    fun with_form_control_and_labels_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyLabelClasses() }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With form control and labels")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With form control and labels")
    }

    @Test
    fun textarea_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Primary) {
            }
        }
        val expectedClasses = "textarea textarea-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Secondary) {
            }
        }
        val expectedClasses = "textarea textarea-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Accent) {
            }
        }
        val expectedClasses = "textarea textarea-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Neutral) {
            }
        }
        val expectedClasses = "textarea textarea-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Info) {
            }
        }
        val expectedClasses = "textarea textarea-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Success) {
            }
        }
        val expectedClasses = "textarea textarea-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Warning) {
            }
        }
        val expectedClasses = "textarea textarea-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun textarea_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Error) {
            }
        }
        val expectedClasses = "textarea textarea-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Textarea colors")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Textarea colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xs) {
            }
        }
        val expectedClasses = "textarea textarea-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Sm) {
            }
        }
        val expectedClasses = "textarea textarea-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Md) {
            }
        }
        val expectedClasses = "textarea textarea-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Lg) {
            }
        }
        val expectedClasses = "textarea textarea-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xl) {
            }
        }
        val expectedClasses = "textarea textarea-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("textarea", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }
}
