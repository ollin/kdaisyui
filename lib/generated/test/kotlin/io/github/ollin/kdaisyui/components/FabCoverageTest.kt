package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FabCoverageTest {

    @Test
    fun fab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab", actualClasses, "Fab defaults")
        assertTrue(html.endsWith("</div></div>"), "Fab closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab fab-flower zz-extra", actualClasses, "Fab all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Fab id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Fab attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Fab content")
        assertTrue(html.endsWith("</div></div>"), "Fab closes <div>")
    }

    @Test
    fun fabClose_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab-close", actualClasses, "FabClose defaults")
        assertTrue(html.endsWith("</div></div>"), "FabClose closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab-close zz-extra", actualClasses, "FabClose all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "FabClose id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FabClose attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FabClose content")
        assertTrue(html.endsWith("</div></div>"), "FabClose closes <div>")
    }

    @Test
    fun fabMainAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab-main-action", actualClasses, "FabMainAction defaults")
        assertTrue(html.endsWith("</div></div>"), "FabMainAction closes <div>")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fab-main-action zz-extra", actualClasses, "FabMainAction all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "FabMainAction id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FabMainAction attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FabMainAction content")
        assertTrue(html.endsWith("</div></div>"), "FabMainAction closes <div>")
    }
}
