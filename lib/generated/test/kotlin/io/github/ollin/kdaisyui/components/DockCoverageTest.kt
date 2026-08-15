package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DockCoverageTest {

    @Test
    fun dock_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock", actualClasses, "Dock defaults")
    }

    @Test
    fun dock_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                id = htmlId("x-cov-id"),
                active = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-active zz-extra", actualClasses, "Dock all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Dock id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Dock attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Dock content")
    }

    @Test
    fun dock_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-xs", actualClasses, "Dock size Xs")
    }

    @Test
    fun dock_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-sm", actualClasses, "Dock size Sm")
    }

    @Test
    fun dock_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-md", actualClasses, "Dock size Md")
    }

    @Test
    fun dock_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-lg", actualClasses, "Dock size Lg")
    }

    @Test
    fun dock_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock dock-xl", actualClasses, "Dock size Xl")
    }

    @Test
    fun dockLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock-label", actualClasses, "DockLabel defaults")
    }

    @Test
    fun dockLabel_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("dock-label zz-extra", actualClasses, "DockLabel all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DockLabel id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DockLabel attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DockLabel content")
    }
}
