package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class TextareaTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun textarea() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea() {
            }
        }
        assertDocumented(html, "textarea", "textarea", "Textarea")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(ghost = true) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-ghost", "Ghost (no background)")
    }

    @Test
    fun with_form_control_and_labels_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "With form control and labels")
    }

    @Test
    fun with_form_control_and_labels_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        assertDocumented(html, "legend", "fieldset-legend", "With form control and labels")
    }

    @Test
    fun with_form_control_and_labels_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyLabelClasses() }
        }
        assertDocumented(html, "div", "label", "With form control and labels")
    }

    @Test
    fun textarea_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Primary) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-primary", "Textarea colors")
    }

    @Test
    fun textarea_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Secondary) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-secondary", "Textarea colors")
    }

    @Test
    fun textarea_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Accent) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-accent", "Textarea colors")
    }

    @Test
    fun textarea_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Neutral) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-neutral", "Textarea colors")
    }

    @Test
    fun textarea_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Info) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-info", "Textarea colors")
    }

    @Test
    fun textarea_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Success) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-success", "Textarea colors")
    }

    @Test
    fun textarea_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Warning) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-warning", "Textarea colors")
    }

    @Test
    fun textarea_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(color = TextareaColor.Error) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-error", "Textarea colors")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xs) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Sm) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Md) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Lg) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xl) {
            }
        }
        assertDocumented(html, "textarea", "textarea textarea-xl", "Sizes")
    }
}
