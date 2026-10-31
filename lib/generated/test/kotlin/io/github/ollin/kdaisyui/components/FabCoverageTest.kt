package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FabCoverageTest {

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
    fun fab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(
                content = { },
            )
        }
        assertRendered(html, Expected("Fab defaults", "fab", closes = "</div></div>"))
    }

    @Test
    fun fab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(
                id = htmlId("x-cov-id"),
                flower = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Fab all flags", "fab fab-flower zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fabClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClasses()
        }
        assertRendered(html, Expected("FabClasses defaults", "fab"))
    }

    @Test
    fun fabClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClasses(
                flower = true,
            )
        }
        val expected = Expected("FabClasses all flags", "fab fab-flower", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun fabClose_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose(
                content = { },
            )
        }
        assertRendered(html, Expected("FabClose defaults", "fab-close", closes = "</div></div>"))
    }

    @Test
    fun fabClose_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("FabClose all flags", "fab-close zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fabCloseClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabCloseClasses()
        }
        assertRendered(html, Expected("FabCloseClasses defaults", "fab-close"))
    }

    @Test
    fun fabCloseClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabCloseClasses()
        }
        val expected = Expected("FabCloseClasses all flags", "fab-close", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun fabMainAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(
                content = { },
            )
        }
        assertRendered(html, Expected("FabMainAction defaults", "fab-main-action", closes = "</button></div>"))
    }

    @Test
    fun fabMainAction_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("FabMainAction all flags", "fab-main-action zz-extra", closes = "</button></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fabMainActionClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainActionClasses()
        }
        assertRendered(html, Expected("FabMainActionClasses defaults", "fab-main-action"))
    }

    @Test
    fun fabMainActionClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainActionClasses()
        }
        val expected = Expected("FabMainActionClasses all flags", "fab-main-action", content = false)
        assertRendered(html, expected)
    }
}
