package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ThemeControllerCoverageTest {

    @Test
    fun themeController_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("theme-controller", actualClasses, "ThemeController defaults")
    }

    @Test
    fun themeController_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyThemeController(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("theme-controller zz-extra", actualClasses, "ThemeController all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ThemeController id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ThemeController attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ThemeController content")
    }
}
