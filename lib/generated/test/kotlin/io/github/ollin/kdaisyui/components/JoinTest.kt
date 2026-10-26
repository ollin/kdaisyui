package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class JoinTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun join() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        assertDocumented(html, "div", "join", "Join")
    }

    @Test
    fun join_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Join")
    }

    @Test
    fun group_items_vertically() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical) {
            }
        }
        assertDocumented(html, "div", "join join-vertical", "Group items vertically")
    }

    @Test
    fun responsive_it_s_vertical_on_small_screen_and_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(direction = JoinDirection.Vertical, extraClasses = "lg:join-horizontal") {
            }
        }
        assertDocumented(html, "div", "join join-vertical lg:join-horizontal", "Responsive: it's vertical on small screen and horizontal on large screen")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisySelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect() {
            }
        }
        assertDocumented(html, "select", "select", "With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyIndicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        assertDocumented(html, "div", "indicator", "With extra elements in the group")
    }

    @Test
    fun with_extra_elements_in_the_group_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(color = BadgeColor.Secondary, attrs = { daisyIndicatorItemClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item", "With extra elements in the group")
    }
}
