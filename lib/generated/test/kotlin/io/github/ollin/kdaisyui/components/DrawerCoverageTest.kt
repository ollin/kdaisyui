package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DrawerCoverageTest {

    @Test
    fun drawer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer", actualClasses, "Drawer defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer drawer-end drawer-open zz-extra", actualClasses, "Drawer all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-toggle", actualClasses, "DrawerToggle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-toggle zz-extra", actualClasses, "DrawerToggle all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-content", actualClasses, "DrawerContent defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-content zz-extra", actualClasses, "DrawerContent all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-side", actualClasses, "DrawerSide defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-side zz-extra", actualClasses, "DrawerSide all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-overlay", actualClasses, "DrawerOverlay defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-overlay zz-extra", actualClasses, "DrawerOverlay all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-button", actualClasses, "DrawerButton defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("drawer-button zz-extra", actualClasses, "DrawerButton all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "DrawerButton id")
        assertTrue(html.contains("data-attrs=\"yes\""), "DrawerButton attrs")
        assertTrue(html.contains("data-content=\"yes\""), "DrawerButton content")
    }
}
