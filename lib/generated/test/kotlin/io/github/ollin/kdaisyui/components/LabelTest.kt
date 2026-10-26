package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.label

class LabelTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun label_for_input_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyInputClasses() }
        }
        assertDocumented(html, "label", "input", "Label for input")
    }

    @Test
    fun label_for_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        assertDocumented(html, "span", "label", "Label for input")
    }

    @Test
    fun label_for_select_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            label { daisySelectClasses() }
        }
        assertDocumented(html, "label", "select", "Label for select")
    }

    @Test
    fun floating_label_daisyFloatingLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyFloatingLabel() {
            }
        }
        assertDocumented(html, "label", "floating-label", "Floating Label")
    }

    @Test
    fun floating_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Md)
        }
        assertDocumented(html, "input", "input input-md", "Floating Label")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs)
        }
        assertDocumented(html, "input", "input input-xs", "Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Sm)
        }
        assertDocumented(html, "input", "input input-sm", "Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Lg)
        }
        assertDocumented(html, "input", "input input-lg", "Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xl)
        }
        assertDocumented(html, "input", "input input-xl", "Floating Label with Different Sizes")
    }

    @Test
    fun responsive_size_floating_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs, extraClasses = "sm:input-sm md:input-md lg:input-lg xl:input-xl")
        }
        assertDocumented(html, "input", "input input-xs lg:input-lg md:input-md sm:input-sm xl:input-xl", "Responsive Size Floating Label")
    }

    @Test
    fun responsive_size_floating_label_daisyTextarea() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xs, extraClasses = "sm:textarea-sm md:textarea-md lg:textarea-lg xl:textarea-xl") {
            }
        }
        assertDocumented(html, "textarea", "lg:textarea-lg md:textarea-md sm:textarea-sm textarea textarea-xs xl:textarea-xl", "Responsive Size Floating Label")
    }

    @Test
    fun responsive_size_floating_label_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xs, extraClasses = "sm:select-sm md:select-md lg:select-lg xl:select-xl") {
            }
        }
        assertDocumented(html, "select", "lg:select-lg md:select-md select select-xs sm:select-sm xl:select-xl", "Responsive Size Floating Label")
    }
}
