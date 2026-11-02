package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DrawerCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun drawer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer()
        }
        assertRendered(html, Expected("Drawer defaults", "drawer", closes = "</div></div>"))
    }

    @Test
    fun drawer_all_flags() {
        val html = allFlags(Expected("Drawer all flags", "drawer drawer-end drawer-open zz-extra", closes = "</div></div>")) { daisyDrawer(id = ID, end = true, open = true, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerClasses()
        }
        assertRendered(html, Expected("DrawerClasses defaults", "drawer"))
    }

    @Test
    fun drawerClasses_all_flags() = assertRenders(Expected("DrawerClasses all flags", "drawer drawer-end drawer-open")) { daisyDrawerClasses(end = true, open = true) }

    @Test
    fun drawerToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle()
        }
        assertRendered(html, Expected("DrawerToggle defaults", "drawer-toggle"))
    }

    @Test
    fun drawerToggle_all_flags() {
        val html = allFlags(Expected("DrawerToggle all flags", "drawer-toggle zz-extra")) { daisyDrawerToggle(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggleClasses()
        }
        assertRendered(html, Expected("DrawerToggleClasses defaults", "drawer-toggle"))
    }

    @Test
    fun drawerToggleClasses_all_flags() = assertRenders(Expected("DrawerToggleClasses all flags", "drawer-toggle")) { daisyDrawerToggleClasses() }

    @Test
    fun drawerContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent()
        }
        assertRendered(html, Expected("DrawerContent defaults", "drawer-content", closes = "</div></div>"))
    }

    @Test
    fun drawerContent_all_flags() {
        val html = allFlags(Expected("DrawerContent all flags", "drawer-content zz-extra", closes = "</div></div>")) { daisyDrawerContent(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContentClasses()
        }
        assertRendered(html, Expected("DrawerContentClasses defaults", "drawer-content"))
    }

    @Test
    fun drawerContentClasses_all_flags() = assertRenders(Expected("DrawerContentClasses all flags", "drawer-content")) { daisyDrawerContentClasses() }

    @Test
    fun drawerSide_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide()
        }
        assertRendered(html, Expected("DrawerSide defaults", "drawer-side", closes = "</div></div>"))
    }

    @Test
    fun drawerSide_all_flags() {
        val html = allFlags(Expected("DrawerSide all flags", "drawer-side zz-extra", closes = "</div></div>")) { daisyDrawerSide(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerSideClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSideClasses()
        }
        assertRendered(html, Expected("DrawerSideClasses defaults", "drawer-side"))
    }

    @Test
    fun drawerSideClasses_all_flags() = assertRenders(Expected("DrawerSideClasses all flags", "drawer-side")) { daisyDrawerSideClasses() }

    @Test
    fun drawerOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay()
        }
        assertRendered(html, Expected("DrawerOverlay defaults", "drawer-overlay", closes = "</label></div>"))
    }

    @Test
    fun drawerOverlay_all_flags() {
        val html = allFlags(Expected("DrawerOverlay all flags", "drawer-overlay zz-extra", closes = "</label></div>")) { daisyDrawerOverlay(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerOverlayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlayClasses()
        }
        assertRendered(html, Expected("DrawerOverlayClasses defaults", "drawer-overlay"))
    }

    @Test
    fun drawerOverlayClasses_all_flags() = assertRenders(Expected("DrawerOverlayClasses all flags", "drawer-overlay")) { daisyDrawerOverlayClasses() }

    @Test
    fun drawerButton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton()
        }
        assertRendered(html, Expected("DrawerButton defaults", "drawer-button", closes = "</label></div>"))
    }

    @Test
    fun drawerButton_all_flags() {
        val html = allFlags(Expected("DrawerButton all flags", "drawer-button zz-extra", closes = "</label></div>")) { daisyDrawerButton(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun drawerButtonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButtonClasses()
        }
        assertRendered(html, Expected("DrawerButtonClasses defaults", "drawer-button"))
    }

    @Test
    fun drawerButtonClasses_all_flags() = assertRenders(Expected("DrawerButtonClasses all flags", "drawer-button")) { daisyDrawerButtonClasses() }
}
