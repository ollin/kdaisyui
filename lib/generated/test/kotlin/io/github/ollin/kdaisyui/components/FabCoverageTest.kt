package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FabCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun fab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(
                content = { },
            )
        }
        assertRendered(html, "fab", "Fab defaults", closes = "</div></div>")
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
        assertRendered(html, "fab fab-flower zz-extra", "Fab all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Fab id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Fab attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Fab content")
    }

    @Test
    fun fabClose_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose(
                content = { },
            )
        }
        assertRendered(html, "fab-close", "FabClose defaults", closes = "</div></div>")
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
        assertRendered(html, "fab-close zz-extra", "FabClose all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "FabClose id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FabClose attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FabClose content")
    }

    @Test
    fun fabMainAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(
                content = { },
            )
        }
        assertRendered(html, "fab-main-action", "FabMainAction defaults", closes = "</div></div>")
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
        assertRendered(html, "fab-main-action zz-extra", "FabMainAction all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "FabMainAction id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FabMainAction attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FabMainAction content")
    }
}
