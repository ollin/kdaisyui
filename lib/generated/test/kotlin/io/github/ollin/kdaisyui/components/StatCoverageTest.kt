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
    fun stat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStat(
                content = { },
            )
        }
        assertRendered(html, "stats", "Stat defaults", closes = "</div></div>")
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
        assertRendered(html, "stats stats-horizontal stats-vertical zz-extra", "Stat all flags", closes = "</div></div>")
        assertCommonFlags(html, "Stat")
    }

    @Test
    fun statStat_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStat(
                content = { },
            )
        }
        assertRendered(html, "stat", "StatStat defaults", closes = "</div></div>")
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
        assertRendered(html, "stat zz-extra", "StatStat all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStat")
    }

    @Test
    fun statStatTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatTitle()
        }
        assertRendered(html, "stat-title", "StatStatTitle defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-title zz-extra", "StatStatTitle all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStatTitle")
    }

    @Test
    fun statStatTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-title", "StatStatTitle text")
        assertTrue(html.contains("txtmark"), "StatStatTitle text content")
    }

    @Test
    fun statStatValue_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatValue()
        }
        assertRendered(html, "stat-value", "StatStatValue defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-value zz-extra", "StatStatValue all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStatValue")
    }

    @Test
    fun statStatValue_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatValue(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-value", "StatStatValue text")
        assertTrue(html.contains("txtmark"), "StatStatValue text content")
    }

    @Test
    fun statStatDesc_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatDesc()
        }
        assertRendered(html, "stat-desc", "StatStatDesc defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-desc zz-extra", "StatStatDesc all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStatDesc")
    }

    @Test
    fun statStatDesc_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatDesc(
                text = "txtmark",
            )
        }
        assertRendered(html, "stat-desc", "StatStatDesc text")
        assertTrue(html.contains("txtmark"), "StatStatDesc text content")
    }

    @Test
    fun statStatFigure_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatFigure(
                content = { },
            )
        }
        assertRendered(html, "stat-figure", "StatStatFigure defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-figure zz-extra", "StatStatFigure all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStatFigure")
    }

    @Test
    fun statStatActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStatStatActions(
                content = { },
            )
        }
        assertRendered(html, "stat-actions", "StatStatActions defaults", closes = "</div></div>")
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
        assertRendered(html, "stat-actions zz-extra", "StatStatActions all flags", closes = "</div></div>")
        assertCommonFlags(html, "StatStatActions")
    }
}
