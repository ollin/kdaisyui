package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun stats_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                content = { },
            )
        }
        assertRendered(html, Expected("Stats defaults", "stats", closes = "</div></div>"))
    }

    @Test
    fun stats_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Stats all flags", "stats zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun stats_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                direction = StatsDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, Expected("Stats direction Horizontal", "stats stats-horizontal"))
    }

    @Test
    fun stats_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                direction = StatsDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, Expected("Stats direction Vertical", "stats stats-vertical"))
    }

    @Test
    fun statsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses()
        }
        assertRendered(html, Expected("StatsClasses defaults", "stats"))
    }

    @Test
    fun statsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses()
        }
        val expected = Expected("StatsClasses all flags", "stats", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statsClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses(
                direction = StatsDirection.Horizontal,
            )
        }
        assertRendered(html, Expected("StatsClasses direction Horizontal", "stats stats-horizontal"))
    }

    @Test
    fun statsClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses(
                direction = StatsDirection.Vertical,
            )
        }
        assertRendered(html, Expected("StatsClasses direction Vertical", "stats stats-vertical"))
    }

    @Test
    fun stat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                content = { },
            )
        }
        assertRendered(html, Expected("Stat defaults", "stat", closes = "</div></div>"))
    }

    @Test
    fun stat_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Stat all flags", "stat zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatClasses()
        }
        assertRendered(html, Expected("StatClasses defaults", "stat"))
    }

    @Test
    fun statClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatClasses()
        }
        val expected = Expected("StatClasses all flags", "stat", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle()
        }
        assertRendered(html, Expected("StatTitle defaults", "stat-title", closes = "</div></div>"))
    }

    @Test
    fun statTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("StatTitle all flags", "stat-title zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("StatTitle text", "stat-title"))
        assertTrue(html.contains("txtmark"), "StatTitle text content")
    }

    @Test
    fun statTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitleClasses()
        }
        assertRendered(html, Expected("StatTitleClasses defaults", "stat-title"))
    }

    @Test
    fun statTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitleClasses()
        }
        val expected = Expected("StatTitleClasses all flags", "stat-title", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statValue_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue()
        }
        assertRendered(html, Expected("StatValue defaults", "stat-value", closes = "</div></div>"))
    }

    @Test
    fun statValue_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("StatValue all flags", "stat-value zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statValue_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("StatValue text", "stat-value"))
        assertTrue(html.contains("txtmark"), "StatValue text content")
    }

    @Test
    fun statValueClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValueClasses()
        }
        assertRendered(html, Expected("StatValueClasses defaults", "stat-value"))
    }

    @Test
    fun statValueClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValueClasses()
        }
        val expected = Expected("StatValueClasses all flags", "stat-value", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statDesc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc()
        }
        assertRendered(html, Expected("StatDesc defaults", "stat-desc", closes = "</div></div>"))
    }

    @Test
    fun statDesc_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("StatDesc all flags", "stat-desc zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statDesc_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("StatDesc text", "stat-desc"))
        assertTrue(html.contains("txtmark"), "StatDesc text content")
    }

    @Test
    fun statDescClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDescClasses()
        }
        assertRendered(html, Expected("StatDescClasses defaults", "stat-desc"))
    }

    @Test
    fun statDescClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDescClasses()
        }
        val expected = Expected("StatDescClasses all flags", "stat-desc", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statFigure_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure(
                content = { },
            )
        }
        assertRendered(html, Expected("StatFigure defaults", "stat-figure", closes = "</div></div>"))
    }

    @Test
    fun statFigure_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("StatFigure all flags", "stat-figure zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statFigureClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigureClasses()
        }
        assertRendered(html, Expected("StatFigureClasses defaults", "stat-figure"))
    }

    @Test
    fun statFigureClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigureClasses()
        }
        val expected = Expected("StatFigureClasses all flags", "stat-figure", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun statActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions(
                content = { },
            )
        }
        assertRendered(html, Expected("StatActions defaults", "stat-actions", closes = "</div></div>"))
    }

    @Test
    fun statActions_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("StatActions all flags", "stat-actions zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun statActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActionsClasses()
        }
        assertRendered(html, Expected("StatActionsClasses defaults", "stat-actions"))
    }

    @Test
    fun statActionsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActionsClasses()
        }
        val expected = Expected("StatActionsClasses all flags", "stat-actions", content = false)
        assertRendered(html, expected)
    }
}
