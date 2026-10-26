package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class SelectTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun select() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        assertDocumented(html, "select", "select", "Select")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(ghost = true) {
            }
        }
        assertDocumented(html, "select", "select select-ghost", "Ghost (no background)")
    }

    @Test
    fun with_fieldset_and_labels_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "With fieldset and labels")
    }

    @Test
    fun with_fieldset_and_labels_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        assertDocumented(html, "legend", "fieldset-legend", "With fieldset and labels")
    }

    @Test
    fun with_fieldset_and_labels_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        assertDocumented(html, "span", "label", "With fieldset and labels")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Primary) {
            }
        }
        assertDocumented(html, "select", "select select-primary", "Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Secondary) {
            }
        }
        assertDocumented(html, "select", "select select-secondary", "Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Accent) {
            }
        }
        assertDocumented(html, "select", "select select-accent", "Accent color")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Neutral) {
            }
        }
        assertDocumented(html, "select", "select select-neutral", "Neutral color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Info) {
            }
        }
        assertDocumented(html, "select", "select select-info", "Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Success) {
            }
        }
        assertDocumented(html, "select", "select select-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Warning) {
            }
        }
        assertDocumented(html, "select", "select select-warning", "Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Error) {
            }
        }
        assertDocumented(html, "select", "select select-error", "Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xs) {
            }
        }
        assertDocumented(html, "select", "select select-xs", "Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Sm) {
            }
        }
        assertDocumented(html, "select", "select select-sm", "Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Md) {
            }
        }
        assertDocumented(html, "select", "select select-md", "Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Lg) {
            }
        }
        assertDocumented(html, "select", "select select-lg", "Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xl) {
            }
        }
        assertDocumented(html, "select", "select select-xl", "Sizes")
    }
}
