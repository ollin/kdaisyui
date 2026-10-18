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

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
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
        assertCommonFlags(html, "Drawer")
    }

    @Test
    fun drawerClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerClasses()
        }
        assertRendered(html, "drawer", "DrawerClasses defaults")
    }

    @Test
    fun drawerClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerClasses(
                end = true,
                open = true,
            )
        }
        assertRendered(html, "drawer drawer-end drawer-open", "DrawerClasses all flags")
    }

    @Test
    fun drawerToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggle()
        }
        assertRendered(html, "drawer-toggle", "DrawerToggle defaults")
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
        assertRendered(html, "drawer-toggle zz-extra", "DrawerToggle all flags")
        assertCommonFlags(html, "DrawerToggle", content = false)
    }

    @Test
    fun drawerToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggleClasses()
        }
        assertRendered(html, "drawer-toggle", "DrawerToggleClasses defaults")
    }

    @Test
    fun drawerToggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerToggleClasses()
        }
        assertRendered(html, "drawer-toggle", "DrawerToggleClasses all flags")
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
        assertCommonFlags(html, "DrawerContent")
    }

    @Test
    fun drawerContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContentClasses()
        }
        assertRendered(html, "drawer-content", "DrawerContentClasses defaults")
    }

    @Test
    fun drawerContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerContentClasses()
        }
        assertRendered(html, "drawer-content", "DrawerContentClasses all flags")
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
        assertCommonFlags(html, "DrawerSide")
    }

    @Test
    fun drawerSideClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSideClasses()
        }
        assertRendered(html, "drawer-side", "DrawerSideClasses defaults")
    }

    @Test
    fun drawerSideClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerSideClasses()
        }
        assertRendered(html, "drawer-side", "DrawerSideClasses all flags")
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
        assertCommonFlags(html, "DrawerOverlay")
    }

    @Test
    fun drawerOverlayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlayClasses()
        }
        assertRendered(html, "drawer-overlay", "DrawerOverlayClasses defaults")
    }

    @Test
    fun drawerOverlayClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerOverlayClasses()
        }
        assertRendered(html, "drawer-overlay", "DrawerOverlayClasses all flags")
    }

    @Test
    fun drawerButton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButton(
                content = { },
            )
        }
        assertRendered(html, "drawer-button", "DrawerButton defaults", closes = "</label></div>")
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
        assertRendered(html, "drawer-button zz-extra", "DrawerButton all flags", closes = "</label></div>")
        assertCommonFlags(html, "DrawerButton")
    }

    @Test
    fun drawerButtonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButtonClasses()
        }
        assertRendered(html, "drawer-button", "DrawerButtonClasses defaults")
    }

    @Test
    fun drawerButtonClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyDrawerButtonClasses()
        }
        assertRendered(html, "drawer-button", "DrawerButtonClasses all flags")
    }
}
