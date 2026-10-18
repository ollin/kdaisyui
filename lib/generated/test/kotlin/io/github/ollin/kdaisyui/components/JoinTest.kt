package io.github.ollin.kdaisyui.components

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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Join")
    }

    @Test
    fun join_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Join")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Join")
    }

    @Test
    fun group_items_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        val expectedClasses = "join join-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Group items vertically")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Group items vertically")
    }

    @Test
    fun responsive_it_s_vertical_on_small_screen_and_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical, extraClasses = "lg:join-horizontal") {
            }
        }
        val expectedClasses = "join join-vertical lg:join-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive: it's vertical on small screen and horizontal on large screen")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive: it's vertical on small screen and horizontal on large screen")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With extra elements in the group")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        val expectedClasses = "select"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With extra elements in the group")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyIndicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With extra elements in the group")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(color = BadgeColor.Secondary, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With extra elements in the group")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for With extra elements in the group")
    }
}
