package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MegamenuCoverageTest {

    @Test
    fun megamenu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu", actualClasses, "Megamenu defaults")
        assertTrue(html.endsWith("</div></div>"), "Megamenu closes <div>")
    }

    @Test
    fun megamenu_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                id = htmlId("x-cov-id"),
                full = true,
                vertical = true,
                wide = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-full megamenu-vertical megamenu-wide zz-extra", actualClasses, "Megamenu all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Megamenu id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Megamenu attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Megamenu content")
        assertTrue(html.endsWith("</div></div>"), "Megamenu closes <div>")
    }

    @Test
    fun megamenu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-xs", actualClasses, "Megamenu size Xs")
    }

    @Test
    fun megamenu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-sm", actualClasses, "Megamenu size Sm")
    }

    @Test
    fun megamenu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-md", actualClasses, "Megamenu size Md")
    }

    @Test
    fun megamenu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-lg", actualClasses, "Megamenu size Lg")
    }

    @Test
    fun megamenu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu megamenu-xl", actualClasses, "Megamenu size Xl")
    }

    @Test
    fun megamenuActive_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu-active", actualClasses, "MegamenuActive defaults")
        assertTrue(html.endsWith("</span></div>"), "MegamenuActive closes <span>")
    }

    @Test
    fun megamenuActive_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("megamenu-active zz-extra", actualClasses, "MegamenuActive all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MegamenuActive id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MegamenuActive attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MegamenuActive content")
        assertTrue(html.endsWith("</span></div>"), "MegamenuActive closes <span>")
    }

    @Test
    fun megamenuPanel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuPanel(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "MegamenuPanel defaults emits no class")
        assertTrue(html.endsWith("</div></div>"), "MegamenuPanel closes <div>")
    }

    @Test
    fun megamenuPanel_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuPanel(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("zz-extra", actualClasses, "MegamenuPanel all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MegamenuPanel id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MegamenuPanel attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MegamenuPanel content")
        assertTrue(html.endsWith("</div></div>"), "MegamenuPanel closes <div>")
    }
}
