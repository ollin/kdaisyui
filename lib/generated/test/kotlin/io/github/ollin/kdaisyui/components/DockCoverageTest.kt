package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DockCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun dock_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                content = { },
            )
        }
        assertRendered(html, "dock", "Dock defaults", closes = "</div></div>")
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
        assertRendered(html, "dock dock-active zz-extra", "Dock all flags", closes = "</div></div>")
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
        assertRendered(html, "dock dock-xs", "Dock size Xs")
    }

    @Test
    fun dock_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "dock dock-sm", "Dock size Sm")
    }

    @Test
    fun dock_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Md,
                content = { },
            )
        }
        assertRendered(html, "dock dock-md", "Dock size Md")
    }

    @Test
    fun dock_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "dock dock-lg", "Dock size Lg")
    }

    @Test
    fun dock_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "dock dock-xl", "Dock size Xl")
    }

    @Test
    fun dockLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel(
                content = { },
            )
        }
        assertRendered(html, "dock-label", "DockLabel defaults", closes = "</div></div>")
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
        assertRendered(html, "dock-label zz-extra", "DockLabel all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DockLabel id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DockLabel attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DockLabel content")
    }
}
