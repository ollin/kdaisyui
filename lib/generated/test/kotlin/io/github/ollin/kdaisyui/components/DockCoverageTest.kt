package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DockCoverageTest {

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
    fun dock_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                content = { },
            )
        }
        assertRendered(html, Expected("Dock defaults", "dock", closes = "</div></div>"))
    }

    @Test
    fun dock_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Dock all flags", "dock zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun dock_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Dock size Xs", "dock dock-xs"))
    }

    @Test
    fun dock_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Dock size Sm", "dock dock-sm"))
    }

    @Test
    fun dock_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Dock size Md", "dock dock-md"))
    }

    @Test
    fun dock_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Dock size Lg", "dock dock-lg"))
    }

    @Test
    fun dock_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(
                size = DockSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Dock size Xl", "dock dock-xl"))
    }

    @Test
    fun dockClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses()
        }
        assertRendered(html, Expected("DockClasses defaults", "dock"))
    }

    @Test
    fun dockClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses()
        }
        val expected = Expected("DockClasses all flags", "dock", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun dockClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses(
                size = DockSize.Xs,
            )
        }
        assertRendered(html, Expected("DockClasses size Xs", "dock dock-xs"))
    }

    @Test
    fun dockClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses(
                size = DockSize.Sm,
            )
        }
        assertRendered(html, Expected("DockClasses size Sm", "dock dock-sm"))
    }

    @Test
    fun dockClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses(
                size = DockSize.Md,
            )
        }
        assertRendered(html, Expected("DockClasses size Md", "dock dock-md"))
    }

    @Test
    fun dockClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses(
                size = DockSize.Lg,
            )
        }
        assertRendered(html, Expected("DockClasses size Lg", "dock dock-lg"))
    }

    @Test
    fun dockClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockClasses(
                size = DockSize.Xl,
            )
        }
        assertRendered(html, Expected("DockClasses size Xl", "dock dock-xl"))
    }

    @Test
    fun dockItem_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItem(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "DockItem defaults emits no class")
        assertTrue(html.endsWith("</button></div>"), "DockItem closes")
    }

    @Test
    fun dockItem_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItem(
                id = htmlId("x-cov-id"),
                active = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("DockItem all flags", "dock-active zz-extra", closes = "</button></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun dockItemClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItemClasses()
        }
        assertTrue(!html.contains("class=\""), "DockItemClasses defaults emits no class")
    }

    @Test
    fun dockItemClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItemClasses(
                active = true,
            )
        }
        val expected = Expected("DockItemClasses all flags", "dock-active", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun dockLabel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel(
                content = { },
            )
        }
        assertRendered(html, Expected("DockLabel defaults", "dock-label", closes = "</span></div>"))
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
        val expected = Expected("DockLabel all flags", "dock-label zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun dockLabelClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabelClasses()
        }
        assertRendered(html, Expected("DockLabelClasses defaults", "dock-label"))
    }

    @Test
    fun dockLabelClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabelClasses()
        }
        val expected = Expected("DockLabelClasses all flags", "dock-label", content = false)
        assertRendered(html, expected)
    }
}
