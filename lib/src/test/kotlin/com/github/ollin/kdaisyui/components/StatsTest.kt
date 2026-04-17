package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class StatsTest {
    @Test
    fun rendersStats() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat {
                daisyStatStat {
                    daisyStatStatTitle("Page Views")
                    daisyStatStatValue("89,400")
                    daisyStatStatDesc("21% more than last month")
                }
            }
        }
        assertEquals(
            expected = """<div><div class="stats"><div class="stat"><div class="stat-title">Page Views</div><div class="stat-value">89,400</div><div class="stat-desc">21% more than last month</div></div></div></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersVerticalAndHorizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(vertical = true, extraClasses = "bg-base-100") {
                daisyStatStat {
                    daisyStatStatTitle("Views")
                    daisyStatStatValue("100")
                }
            }
        }
        assertEquals(
            expected = """<div><div class="stats stats-vertical bg-base-100"><div class="stat"><div class="stat-title">Views</div><div class="stat-value">100</div></div></div></div>""",
            actual = html.trim(),
        )
    }
}
