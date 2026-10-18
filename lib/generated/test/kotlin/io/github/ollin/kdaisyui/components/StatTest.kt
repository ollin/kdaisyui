package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun stat() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats() {
            }
        }
        assertDocumented(html, "div", "stats", "Stat")
    }

    @Test
    fun stat_daisyStat() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat() {
            }
        }
        assertDocumented(html, "div", "stat", "Stat")
    }

    @Test
    fun stat_daisyStatTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle() {
            }
        }
        assertDocumented(html, "div", "stat-title", "Stat")
    }

    @Test
    fun stat_daisyStatValue() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue() {
            }
        }
        assertDocumented(html, "div", "stat-value", "Stat")
    }

    @Test
    fun stat_daisyStatDesc() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc() {
            }
        }
        assertDocumented(html, "div", "stat-desc", "Stat")
    }

    @Test
    fun stat_with_icons_or_image_daisyStatFigure() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure() {
            }
        }
        assertDocumented(html, "div", "stat-figure", "Stat with icons or image")
    }

    @Test
    fun stat_with_icons_or_image_daisyAvatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online) {
            }
        }
        assertDocumented(html, "div", "avatar avatar-online", "Stat with icons or image")
    }

    @Test
    fun vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(direction = StatsDirection.Vertical) {
            }
        }
        assertDocumented(html, "div", "stats stats-vertical", "Vertical")
    }

    @Test
    fun responsive_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(direction = StatsDirection.Vertical, extraClasses = "lg:stats-horizontal") {
            }
        }
        assertDocumented(html, "div", "lg:stats-horizontal stats stats-vertical", "Responsive (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun with_custom_colors_and_button_daisyStatActions() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions() {
            }
        }
        assertDocumented(html, "div", "stat-actions", "With custom colors and button")
    }

    @Test
    fun with_custom_colors_and_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs, color = ButtonColor.Success) {
            }
        }
        assertDocumented(html, "button", "btn btn-success btn-xs", "With custom colors and button")
    }

    @Test
    fun with_custom_colors_and_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Xs) {
            }
        }
        assertDocumented(html, "button", "btn btn-xs", "With custom colors and button")
    }
}
