package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatTest {

    @Test
    fun stat() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats() {
            }
        }
        val expectedClasses = "stats"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_daisyStat() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        val expectedClasses = "stat"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_daisyStatTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle() {
            }
        }
        val expectedClasses = "stat-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_daisyStatValue() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue() {
            }
        }
        val expectedClasses = "stat-value"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_daisyStatDesc() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc() {
            }
        }
        val expectedClasses = "stat-desc"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat")
    }

    @Test
    fun stat_with_icons_or_image_daisyStatFigure() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure() {
            }
        }
        val expectedClasses = "stat-figure"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat with icons or image")
    }

    @Test
    fun stat_with_icons_or_image_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online) {
            }
        }
        val expectedClasses = "avatar avatar-online"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Stat with icons or image")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(direction = StatsDirection.Vertical) {
            }
        }
        val expectedClasses = "stats stats-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(direction = StatsDirection.Vertical, extraClasses = "lg:stats-horizontal") {
            }
        }
        val expectedClasses = "lg:stats-horizontal stats stats-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_colors_and_button_daisyStatActions() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions() {
            }
        }
        val expectedClasses = "stat-actions"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom colors and button")
    }

    @Test
    fun with_custom_colors_and_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-success btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom colors and button")
    }

    @Test
    fun with_custom_colors_and_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        val expectedClasses = "btn btn-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for With custom colors and button")
    }
}
