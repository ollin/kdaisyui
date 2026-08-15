package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StatCoverageTest {

    @Test
    fun stat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stats", actualClasses, "Stat defaults")
    }

    @Test
    fun stat_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                id = htmlId("x-cov-id"),
                horizontal = true,
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stats stats-horizontal stats-vertical zz-extra", actualClasses, "Stat all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Stat id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Stat attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Stat content")
    }

    @Test
    fun statStat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStat(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat", actualClasses, "StatStat defaults")
    }

    @Test
    fun statStat_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStat(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat zz-extra", actualClasses, "StatStat all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStat id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStat attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStat content")
    }

    @Test
    fun statStatTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatTitle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-title", actualClasses, "StatStatTitle defaults")
    }

    @Test
    fun statStatTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-title zz-extra", actualClasses, "StatStatTitle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStatTitle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStatTitle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStatTitle content")
    }

    @Test
    fun statStatTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatTitle(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-title", actualClasses, "StatStatTitle text")
        assertTrue(html.contains("txtmark"), "StatStatTitle text content")
    }

    @Test
    fun statStatValue_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatValue()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-value", actualClasses, "StatStatValue defaults")
    }

    @Test
    fun statStatValue_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatValue(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-value zz-extra", actualClasses, "StatStatValue all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStatValue id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStatValue attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStatValue content")
    }

    @Test
    fun statStatValue_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatValue(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-value", actualClasses, "StatStatValue text")
        assertTrue(html.contains("txtmark"), "StatStatValue text content")
    }

    @Test
    fun statStatDesc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatDesc()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-desc", actualClasses, "StatStatDesc defaults")
    }

    @Test
    fun statStatDesc_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatDesc(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-desc zz-extra", actualClasses, "StatStatDesc all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStatDesc id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStatDesc attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStatDesc content")
    }

    @Test
    fun statStatDesc_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatDesc(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-desc", actualClasses, "StatStatDesc text")
        assertTrue(html.contains("txtmark"), "StatStatDesc text content")
    }

    @Test
    fun statStatFigure_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatFigure(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-figure", actualClasses, "StatStatFigure defaults")
    }

    @Test
    fun statStatFigure_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatFigure(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-figure zz-extra", actualClasses, "StatStatFigure all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStatFigure id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStatFigure attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStatFigure content")
    }

    @Test
    fun statStatActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatActions(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-actions", actualClasses, "StatStatActions defaults")
    }

    @Test
    fun statStatActions_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatActions(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("stat-actions zz-extra", actualClasses, "StatStatActions all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "StatStatActions id")
        assertTrue(html.contains("data-attrs=\"yes\""), "StatStatActions attrs")
        assertTrue(html.contains("data-content=\"yes\""), "StatStatActions content")
    }
}
