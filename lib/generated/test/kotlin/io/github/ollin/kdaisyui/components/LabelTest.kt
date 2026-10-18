package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LabelTest {

    @Test
    fun label_for_input_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Label for input")
    }

    @Test
    fun label_for_input() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Label for input")
    }

    @Test
    fun label_for_select_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Label for select")
    }

    @Test
    fun floating_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Md)
        }
        val expectedClasses = "input input-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Floating Label")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs)
        }
        val expectedClasses = "input input-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Sm)
        }
        val expectedClasses = "input input-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Lg)
        }
        val expectedClasses = "input input-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Floating Label with Different Sizes")
    }

    @Test
    fun floating_label_with_different_sizes_daisyInput_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xl)
        }
        val expectedClasses = "input input-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Floating Label with Different Sizes")
    }

    @Test
    fun responsive_size_floating_label_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Xs, extraClasses = "sm:input-sm md:input-md lg:input-lg xl:input-xl")
        }
        val expectedClasses = "input input-xs lg:input-lg md:input-md sm:input-sm xl:input-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive Size Floating Label")
    }

    @Test
    fun responsive_size_floating_label_daisyTextarea() {
        val html = createHTML(prettyPrint = false).div {
            daisyTextarea(size = TextareaSize.Xs, extraClasses = "sm:textarea-sm md:textarea-md lg:textarea-lg xl:textarea-xl") {
            }
        }
        val expectedClasses = "lg:textarea-lg md:textarea-md sm:textarea-sm textarea textarea-xs xl:textarea-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive Size Floating Label")
    }

    @Test
    fun responsive_size_floating_label_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(size = SelectSize.Xs, extraClasses = "sm:select-sm md:select-md lg:select-lg xl:select-xl") {
            }
        }
        val expectedClasses = "lg:select-lg md:select-md select select-xs sm:select-sm xl:select-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive Size Floating Label")
    }
}
