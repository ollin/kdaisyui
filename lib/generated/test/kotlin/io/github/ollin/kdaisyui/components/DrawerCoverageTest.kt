package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class DrawerCoverageTest {

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
    fun drawer_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawer(
                content = { },
            )
        }
        assertRendered(html, Expected("Drawer defaults", "drawer", closes = "</div></div>"))
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
        val expected = Expected("Drawer all flags", "drawer drawer-end drawer-open zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerClasses()
        }
        assertRendered(html, Expected("DrawerClasses defaults", "drawer"))
    }

    @Test
    fun drawerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerClasses(
                end = true,
                open = true,
            )
        }
        val expected = Expected("DrawerClasses all flags", "drawer drawer-end drawer-open", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun drawerToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle()
        }
        assertRendered(html, Expected("DrawerToggle defaults", "drawer-toggle"))
    }

    @Test
    fun drawerToggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        val expected = Expected("DrawerToggle all flags", "drawer-toggle zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggleClasses()
        }
        assertRendered(html, Expected("DrawerToggleClasses defaults", "drawer-toggle"))
    }

    @Test
    fun drawerToggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggleClasses()
        }
        val expected = Expected("DrawerToggleClasses all flags", "drawer-toggle", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun drawerContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContent(
                content = { },
            )
        }
        assertRendered(html, Expected("DrawerContent defaults", "drawer-content", closes = "</div></div>"))
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
        val expected = Expected("DrawerContent all flags", "drawer-content zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContentClasses()
        }
        assertRendered(html, Expected("DrawerContentClasses defaults", "drawer-content"))
    }

    @Test
    fun drawerContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContentClasses()
        }
        val expected = Expected("DrawerContentClasses all flags", "drawer-content", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun drawerSide_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSide(
                content = { },
            )
        }
        assertRendered(html, Expected("DrawerSide defaults", "drawer-side", closes = "</div></div>"))
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
        val expected = Expected("DrawerSide all flags", "drawer-side zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerSideClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSideClasses()
        }
        assertRendered(html, Expected("DrawerSideClasses defaults", "drawer-side"))
    }

    @Test
    fun drawerSideClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSideClasses()
        }
        val expected = Expected("DrawerSideClasses all flags", "drawer-side", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun drawerOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlay(
                content = { },
            )
        }
        assertRendered(html, Expected("DrawerOverlay defaults", "drawer-overlay", closes = "</label></div>"))
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
        val expected = Expected("DrawerOverlay all flags", "drawer-overlay zz-extra", closes = "</label></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerOverlayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlayClasses()
        }
        assertRendered(html, Expected("DrawerOverlayClasses defaults", "drawer-overlay"))
    }

    @Test
    fun drawerOverlayClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlayClasses()
        }
        val expected = Expected("DrawerOverlayClasses all flags", "drawer-overlay", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun drawerButton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(
                content = { },
            )
        }
        assertRendered(html, Expected("DrawerButton defaults", "drawer-button", closes = "</label></div>"))
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
        val expected = Expected("DrawerButton all flags", "drawer-button zz-extra", closes = "</label></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun drawerButtonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButtonClasses()
        }
        assertRendered(html, Expected("DrawerButtonClasses defaults", "drawer-button"))
    }

    @Test
    fun drawerButtonClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButtonClasses()
        }
        val expected = Expected("DrawerButtonClasses all flags", "drawer-button", content = false)
        assertRendered(html, expected)
    }
}
