package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun stats_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStats()
        }
        assertRendered(html, Expected("Stats defaults", "stats", closes = "</div></div>"))
    }

    @Test
    fun stats_all_flags() {
        val html = allFlags(Expected("Stats all flags", "stats zz-extra", closes = "</div></div>")) { daisyStats(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun stats_direction() = assertEachEntry("Stats direction", mapOf(StatsDirection.Horizontal to "stats stats-horizontal", StatsDirection.Vertical to "stats stats-vertical")) { daisyStats(direction = it) }

    @Test
    fun statsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatsClasses()
        }
        assertRendered(html, Expected("StatsClasses defaults", "stats"))
    }

    @Test
    fun statsClasses_all_flags() = assertRenders(Expected("StatsClasses all flags", "stats")) { daisyStatsClasses() }

    @Test
    fun statsClasses_direction() = assertEachEntry("StatsClasses direction", mapOf(StatsDirection.Horizontal to "stats stats-horizontal", StatsDirection.Vertical to "stats stats-vertical")) { daisyStatsClasses(direction = it) }

    @Test
    fun stat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat()
        }
        assertRendered(html, Expected("Stat defaults", "stat", closes = "</div></div>"))
    }

    @Test
    fun stat_all_flags() {
        val html = allFlags(Expected("Stat all flags", "stat zz-extra", closes = "</div></div>")) { daisyStat(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun statClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatClasses()
        }
        assertRendered(html, Expected("StatClasses defaults", "stat"))
    }

    @Test
    fun statClasses_all_flags() = assertRenders(Expected("StatClasses all flags", "stat")) { daisyStatClasses() }

    @Test
    fun statTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatTitle()
        }
        assertRendered(html, Expected("StatTitle defaults", "stat-title", closes = "</div></div>"))
    }

    @Test
    fun statTitle_all_flags() {
        val html = allFlags(Expected("StatTitle all flags", "stat-title zz-extra", closes = "</div></div>")) { daisyStatTitle(id = ID, extraClasses = EXTRA, block = BLOCK) }
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
    fun statTitleClasses_all_flags() = assertRenders(Expected("StatTitleClasses all flags", "stat-title")) { daisyStatTitleClasses() }

    @Test
    fun statValue_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatValue()
        }
        assertRendered(html, Expected("StatValue defaults", "stat-value", closes = "</div></div>"))
    }

    @Test
    fun statValue_all_flags() {
        val html = allFlags(Expected("StatValue all flags", "stat-value zz-extra", closes = "</div></div>")) { daisyStatValue(id = ID, extraClasses = EXTRA, block = BLOCK) }
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
    fun statValueClasses_all_flags() = assertRenders(Expected("StatValueClasses all flags", "stat-value")) { daisyStatValueClasses() }

    @Test
    fun statDesc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatDesc()
        }
        assertRendered(html, Expected("StatDesc defaults", "stat-desc", closes = "</div></div>"))
    }

    @Test
    fun statDesc_all_flags() {
        val html = allFlags(Expected("StatDesc all flags", "stat-desc zz-extra", closes = "</div></div>")) { daisyStatDesc(id = ID, extraClasses = EXTRA, block = BLOCK) }
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
    fun statDescClasses_all_flags() = assertRenders(Expected("StatDescClasses all flags", "stat-desc")) { daisyStatDescClasses() }

    @Test
    fun statFigure_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigure()
        }
        assertRendered(html, Expected("StatFigure defaults", "stat-figure", closes = "</div></div>"))
    }

    @Test
    fun statFigure_all_flags() {
        val html = allFlags(Expected("StatFigure all flags", "stat-figure zz-extra", closes = "</div></div>")) { daisyStatFigure(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun statFigureClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatFigureClasses()
        }
        assertRendered(html, Expected("StatFigureClasses defaults", "stat-figure"))
    }

    @Test
    fun statFigureClasses_all_flags() = assertRenders(Expected("StatFigureClasses all flags", "stat-figure")) { daisyStatFigureClasses() }

    @Test
    fun statActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActions()
        }
        assertRendered(html, Expected("StatActions defaults", "stat-actions", closes = "</div></div>"))
    }

    @Test
    fun statActions_all_flags() {
        val html = allFlags(Expected("StatActions all flags", "stat-actions zz-extra", closes = "</div></div>")) { daisyStatActions(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun statActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatActionsClasses()
        }
        assertRendered(html, Expected("StatActionsClasses defaults", "stat-actions"))
    }

    @Test
    fun statActionsClasses_all_flags() = assertRenders(Expected("StatActionsClasses all flags", "stat-actions")) { daisyStatActionsClasses() }
}
