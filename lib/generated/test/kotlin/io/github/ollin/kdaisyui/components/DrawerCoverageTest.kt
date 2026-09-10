package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DrawerCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun drawer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(
                content = { },
            )
        }
        assertRendered(html, "drawer", "Drawer defaults", closes = "</div></div>")
    }

    @Test
    fun drawer_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(
                id = htmlId("x-cov-id"),
                end = true,
                open = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer drawer-end drawer-open zz-extra", "Drawer all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Drawer id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Drawer attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Drawer content")
    }

    @Test
    fun drawerToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle(
                content = { },
            )
        }
        assertRendered(html, "drawer-toggle", "DrawerToggle defaults", closes = "</div></div>")
    }

    @Test
    fun drawerToggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer-toggle zz-extra", "DrawerToggle all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerToggle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerToggle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerToggle content")
    }

    @Test
    fun drawerContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent(
                content = { },
            )
        }
        assertRendered(html, "drawer-content", "DrawerContent defaults", closes = "</div></div>")
    }

    @Test
    fun drawerContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer-content zz-extra", "DrawerContent all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerContent content")
    }

    @Test
    fun drawerSide_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide(
                content = { },
            )
        }
        assertRendered(html, "drawer-side", "DrawerSide defaults", closes = "</div></div>")
    }

    @Test
    fun drawerSide_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer-side zz-extra", "DrawerSide all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerSide id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerSide attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerSide content")
    }

    @Test
    fun drawerOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay(
                content = { },
            )
        }
        assertRendered(html, "drawer-overlay", "DrawerOverlay defaults", closes = "</label></div>")
    }

    @Test
    fun drawerOverlay_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer-overlay zz-extra", "DrawerOverlay all flags", closes = "</label></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerOverlay id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerOverlay attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerOverlay content")
    }

    @Test
    fun drawerButton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(
                content = { },
            )
        }
        assertRendered(html, "drawer-button", "DrawerButton defaults", closes = "</div></div>")
    }

    @Test
    fun drawerButton_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "drawer-button zz-extra", "DrawerButton all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerButton id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerButton attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerButton content")
    }
}
