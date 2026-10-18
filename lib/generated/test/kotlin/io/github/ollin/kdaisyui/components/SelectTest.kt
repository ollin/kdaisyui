package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.fieldSet

class SelectTest {

    @Test
    fun select() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Select")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Select")
    }

    @Test
    fun ghost_no_background() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(ghost = true) {
            }
        }
        val expectedClasses = "select select-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Ghost (no background)")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Ghost (no background)")
    }

    @Test
    fun with_fieldset_and_labels_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and labels")
        assertEquals("fieldset", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and labels")
    }

    @Test
    fun with_fieldset_and_labels_daisyFieldsetLegend() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend() {
            }
        }
        val expectedClasses = "fieldset-legend"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and labels")
        assertEquals("legend", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and labels")
    }

    @Test
    fun with_fieldset_and_labels_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With fieldset and labels")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With fieldset and labels")
    }

    @Test
    fun primary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Primary) {
            }
        }
        val expectedClasses = "select select-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Primary color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Primary color")
    }

    @Test
    fun secondary_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Secondary) {
            }
        }
        val expectedClasses = "select select-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Secondary color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Secondary color")
    }

    @Test
    fun accent_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Accent) {
            }
        }
        val expectedClasses = "select select-accent"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Accent color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Accent color")
    }

    @Test
    fun neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Neutral) {
            }
        }
        val expectedClasses = "select select-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Neutral color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Neutral color")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Info) {
            }
        }
        val expectedClasses = "select select-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Success) {
            }
        }
        val expectedClasses = "select select-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Warning) {
            }
        }
        val expectedClasses = "select select-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(color = SelectColor.Error) {
            }
        }
        val expectedClasses = "select select-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Error color")
    }

    @Test
    fun sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xs) {
            }
        }
        val expectedClasses = "select select-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Sm) {
            }
        }
        val expectedClasses = "select select-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Md) {
            }
        }
        val expectedClasses = "select select-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Lg) {
            }
        }
        val expectedClasses = "select select-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xl) {
            }
        }
        val expectedClasses = "select select-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }
}
