package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.input

class PaginationTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun pagination_with_an_active_button_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        assertDocumented(html, "div", "join", "Pagination with an active button")
    }

    @Test
    fun pagination_with_an_active_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Pagination with an active button")
    }

    @Test
    fun pagination_with_an_active_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active", "Pagination with an active button")
    }

    @Test
    fun sizes_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        assertDocumented(html, "button", "btn btn-xs", "Sizes")
    }

    @Test
    fun sizes_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-xs", "Sizes")
    }

    @Test
    fun sizes_daisyButton_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        assertDocumented(html, "button", "btn btn-sm", "Sizes")
    }

    @Test
    fun sizes_daisyButton_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-sm", "Sizes")
    }

    @Test
    fun sizes_daisyButton_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Md) {
            }
        }
        assertDocumented(html, "button", "btn btn-md", "Sizes")
    }

    @Test
    fun sizes_daisyButton_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Md, active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-md", "Sizes")
    }

    @Test
    fun sizes_daisyButton_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        assertDocumented(html, "button", "btn btn-lg", "Sizes")
    }

    @Test
    fun sizes_daisyButton_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-lg", "Sizes")
    }

    @Test
    fun sizes_daisyButton_9() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl) {
            }
        }
        assertDocumented(html, "button", "btn btn-xl", "Sizes")
    }

    @Test
    fun sizes_daisyButton_10() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl, active = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-active btn-xl", "Sizes")
    }

    @Test
    fun with_a_disabled_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(disabled = true) {
            }
        }
        assertDocumented(html, "button", "btn btn-disabled", "With a disabled button")
    }

    @Test
    fun nex_prev_outline_buttons_with_equal_width_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline) {
            }
        }
        assertDocumented(html, "button", "btn btn-outline", "Nex/Prev outline buttons with equal width")
    }

    @Test
    fun using_radio_inputs_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses(shape = ButtonShape.Square) }
        }
        assertDocumented(html, "input", "btn btn-square", "Using radio inputs")
    }
}
