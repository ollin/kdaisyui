package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class JoinTest {

    @Test
    fun join() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Join")
    }

    @Test
    fun group_items_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(vertical = true) {
            }
        }
        val expectedClasses = "join join-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Group items vertically")
    }

    @Test
    fun responsive_it_s_vertical_on_small_screen_and_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(vertical = true, horizontal = true) {
            }
        }
        val expectedClasses = "join join-horizontal join-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: it's vertical on small screen and horizontal on large screen")
    }

    @Test
    fun with_extra_elements_in_the_group() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With extra elements in the group")
    }

    @Test
    fun custom_border_radius() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Custom border radius")
    }

    @Test
    fun join_radio_inputs_with_btn_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        val expectedClasses = "join"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Join radio inputs with btn style")
    }
}
