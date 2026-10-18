package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun stats_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                content = { },
            )
        }
        assertRendered(html, "stats", "Stats defaults", closes = "</div></div>")
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
        assertRendered(html, "stats zz-extra", "Stats all flags", closes = "</div></div>")
        assertCommonFlags(html, "Stats")
    }

    @Test
    fun stats_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                direction = StatsDirection.Horizontal,
                content = { },
            )
        }
        assertRendered(html, "stats stats-horizontal", "Stats direction Horizontal")
    }

    @Test
    fun stats_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats(
                direction = StatsDirection.Vertical,
                content = { },
            )
        }
        assertRendered(html, "stats stats-vertical", "Stats direction Vertical")
    }

    @Test
    fun statsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses()
        }
        assertRendered(html, "stats", "StatsClasses defaults")
    }

    @Test
    fun statsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses()
        }
        assertRendered(html, "stats", "StatsClasses all flags")
    }

    @Test
    fun statsClasses_direction_horizontal() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses(
                direction = StatsDirection.Horizontal,
            )
        }
        assertRendered(html, "stats stats-horizontal", "StatsClasses direction Horizontal")
    }

    @Test
    fun statsClasses_direction_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses(
                direction = StatsDirection.Vertical,
            )
        }
        assertRendered(html, "stats stats-vertical", "StatsClasses direction Vertical")
    }

    @Test
    fun stat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                content = { },
            )
        }
        assertRendered(html, "stat", "Stat defaults", closes = "</div></div>")
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
        assertRendered(html, "stat zz-extra", "Stat all flags", closes = "</div></div>")
        assertCommonFlags(html, "Stat")
    }

    @Test
    fun statClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatClasses()
        }
        assertRendered(html, "stat", "StatClasses defaults")
    }

    @Test
    fun statClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatClasses()
        }
        assertRendered(html, "stat", "StatClasses all flags")
    }

    @Test
    fun statTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle()
        }
        assertRendered(html, "stat-title", "StatTitle defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-title zz-extra", "StatTitle all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatTitle")
    }

    @Test
    fun statTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-title", "StatTitle text")
        assertTrue(html.contains("txtmark"), "StatTitle text content")
    }

    @Test
    fun statTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitleClasses()
        }
        assertRendered(html, "stat-title", "StatTitleClasses defaults")
    }

    @Test
    fun statTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitleClasses()
        }
        assertRendered(html, "stat-title", "StatTitleClasses all flags")
    }

    @Test
    fun statValue_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue()
        }
        assertRendered(html, "stat-value", "StatValue defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-value zz-extra", "StatValue all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatValue")
    }

    @Test
    fun statValue_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-value", "StatValue text")
        assertTrue(html.contains("txtmark"), "StatValue text content")
    }

    @Test
    fun statValueClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValueClasses()
        }
        assertRendered(html, "stat-value", "StatValueClasses defaults")
    }

    @Test
    fun statValueClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValueClasses()
        }
        assertRendered(html, "stat-value", "StatValueClasses all flags")
    }

    @Test
    fun statDesc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc()
        }
        assertRendered(html, "stat-desc", "StatDesc defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-desc zz-extra", "StatDesc all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatDesc")
    }

    @Test
    fun statDesc_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-desc", "StatDesc text")
        assertTrue(html.contains("txtmark"), "StatDesc text content")
    }

    @Test
    fun statDescClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDescClasses()
        }
        assertRendered(html, "stat-desc", "StatDescClasses defaults")
    }

    @Test
    fun statDescClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDescClasses()
        }
        assertRendered(html, "stat-desc", "StatDescClasses all flags")
    }

    @Test
    fun statFigure_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure(
                content = { },
            )
        }
        assertRendered(html, "stat-figure", "StatFigure defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-figure zz-extra", "StatFigure all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatFigure")
    }

    @Test
    fun statFigureClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigureClasses()
        }
        assertRendered(html, "stat-figure", "StatFigureClasses defaults")
    }

    @Test
    fun statFigureClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigureClasses()
        }
        assertRendered(html, "stat-figure", "StatFigureClasses all flags")
    }

    @Test
    fun statActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions(
                content = { },
            )
        }
        assertRendered(html, "stat-actions", "StatActions defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-actions zz-extra", "StatActions all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatActions")
    }

    @Test
    fun statActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActionsClasses()
        }
        assertRendered(html, "stat-actions", "StatActionsClasses defaults")
    }

    @Test
    fun statActionsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActionsClasses()
        }
        assertRendered(html, "stat-actions", "StatActionsClasses all flags")
    }
}
