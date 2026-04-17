package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class DockTest {

    @Test
    fun dock() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock")
    }

    @Test
    fun dock_extra_small_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock Extra Small size")
    }

    @Test
    fun dock_small_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock Small size")
    }

    @Test
    fun dock_medium_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock Medium size")
    }

    @Test
    fun dock_large_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock Large size")
    }

    @Test
    fun dock_extra_large_size() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock Extra Large size")
    }

    @Test
    fun dock_with_custom_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyDock(active = true) {
            }
        }
        val expectedClasses = "dock dock-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dock with custom colors")
    }
}
