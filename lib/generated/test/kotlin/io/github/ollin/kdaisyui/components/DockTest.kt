package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DockTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun dock() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock() {
            }
        }
        assertDocumented(html, "div", "dock", "Dock")
    }

    @Test
    fun dock_daisyDockLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockLabel() {
            }
        }
        assertDocumented(html, "span", "dock-label", "Dock")
    }

    @Test
    fun dock_daisyDockItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyDockItem(active = true) {
            }
        }
        assertDocumented(html, "button", "dock-active", "Dock")
    }

    @Test
    fun dock_extra_small_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(size = DockSize.Xs) {
            }
        }
        assertDocumented(html, "div", "dock dock-xs", "Dock Extra Small size")
    }

    @Test
    fun dock_small_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(size = DockSize.Sm) {
            }
        }
        assertDocumented(html, "div", "dock dock-sm", "Dock Small size")
    }

    @Test
    fun dock_medium_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(size = DockSize.Md) {
            }
        }
        assertDocumented(html, "div", "dock dock-md", "Dock Medium size")
    }

    @Test
    fun dock_large_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(size = DockSize.Lg) {
            }
        }
        assertDocumented(html, "div", "dock dock-lg", "Dock Large size")
    }

    @Test
    fun dock_extra_large_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(size = DockSize.Xl) {
            }
        }
        assertDocumented(html, "div", "dock dock-xl", "Dock Extra Large size")
    }
}
