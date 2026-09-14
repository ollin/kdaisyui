package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatTest {

    @Test
    fun stat() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_with_icons_or_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat with icons or image")
    }

    @Test
    fun stat_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun centered_items() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered items")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(direction = StatDirection.Vertical) {
            }
        }
        val expectedClasses = "stats stats-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(direction = StatDirection.Vertical, extraClasses = "lg:stats-horizontal") {
            }
        }
        val expectedClasses = "lg:stats-horizontal stats stats-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_colors_and_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom colors and button")
    }
}
