package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.input

class PaginationTest {

    @Test
    fun pagination_with_an_active_button_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Pagination with an active button")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Pagination with an active button")
    }

    @Test
    fun pagination_with_an_active_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Pagination with an active button")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Pagination with an active button")
    }

    @Test
    fun pagination_with_an_active_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(active = true) {
            }
        }
        val expectedClasses = "btn btn-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Pagination with an active button")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Pagination with an active button")
    }

    @Test
    fun sizes_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        val expectedClasses = "btn btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, active = true) {
            }
        }
        val expectedClasses = "btn btn-active btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        val expectedClasses = "btn btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, active = true) {
            }
        }
        val expectedClasses = "btn btn-active btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Md) {
            }
        }
        val expectedClasses = "btn btn-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Md, active = true) {
            }
        }
        val expectedClasses = "btn btn-active btn-md"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        val expectedClasses = "btn btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, active = true) {
            }
        }
        val expectedClasses = "btn btn-active btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_9() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl) {
            }
        }
        val expectedClasses = "btn btn-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun sizes_daisyButton_10() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xl, active = true) {
            }
        }
        val expectedClasses = "btn btn-active btn-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Sizes")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Sizes")
    }

    @Test
    fun with_a_disabled_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(disabled = true) {
            }
        }
        val expectedClasses = "btn btn-disabled"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With a disabled button")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With a disabled button")
    }

    @Test
    fun nex_prev_outline_buttons_with_equal_width_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Outline) {
            }
        }
        val expectedClasses = "btn btn-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Nex/Prev outline buttons with equal width")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Nex/Prev outline buttons with equal width")
    }

    @Test
    fun using_radio_inputs_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            input { daisyButtonClasses(shape = ButtonShape.Square) }
        }
        val expectedClasses = "btn btn-square"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Using radio inputs")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Using radio inputs")
    }
}
