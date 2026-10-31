package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MegamenuCoverageTest {

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
    fun megamenu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu defaults", "megamenu", closes = "</div></div>"))
    }

    @Test
    fun megamenu_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                id = htmlId("x-cov-id"),
                vertical = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Megamenu all flags", "megamenu megamenu-vertical zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun megamenu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu size Xs", "megamenu megamenu-xs"))
    }

    @Test
    fun megamenu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu size Sm", "megamenu megamenu-sm"))
    }

    @Test
    fun megamenu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu size Md", "megamenu megamenu-md"))
    }

    @Test
    fun megamenu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu size Lg", "megamenu megamenu-lg"))
    }

    @Test
    fun megamenu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu size Xl", "megamenu megamenu-xl"))
    }

    @Test
    fun megamenu_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                width = MegamenuWidth.Wide,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu width Wide", "megamenu megamenu-wide"))
    }

    @Test
    fun megamenu_width_full() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                width = MegamenuWidth.Full,
                content = { },
            )
        }
        assertRendered(html, Expected("Megamenu width Full", "megamenu megamenu-full"))
    }

    @Test
    fun megamenuClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses()
        }
        assertRendered(html, Expected("MegamenuClasses defaults", "megamenu"))
    }

    @Test
    fun megamenuClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                vertical = true,
            )
        }
        val expected = Expected("MegamenuClasses all flags", "megamenu megamenu-vertical", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun megamenuClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Xs,
            )
        }
        assertRendered(html, Expected("MegamenuClasses size Xs", "megamenu megamenu-xs"))
    }

    @Test
    fun megamenuClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Sm,
            )
        }
        assertRendered(html, Expected("MegamenuClasses size Sm", "megamenu megamenu-sm"))
    }

    @Test
    fun megamenuClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Md,
            )
        }
        assertRendered(html, Expected("MegamenuClasses size Md", "megamenu megamenu-md"))
    }

    @Test
    fun megamenuClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Lg,
            )
        }
        assertRendered(html, Expected("MegamenuClasses size Lg", "megamenu megamenu-lg"))
    }

    @Test
    fun megamenuClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Xl,
            )
        }
        assertRendered(html, Expected("MegamenuClasses size Xl", "megamenu megamenu-xl"))
    }

    @Test
    fun megamenuClasses_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                width = MegamenuWidth.Wide,
            )
        }
        assertRendered(html, Expected("MegamenuClasses width Wide", "megamenu megamenu-wide"))
    }

    @Test
    fun megamenuClasses_width_full() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                width = MegamenuWidth.Full,
            )
        }
        assertRendered(html, Expected("MegamenuClasses width Full", "megamenu megamenu-full"))
    }

    @Test
    fun megamenuActive_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive(
                content = { },
            )
        }
        assertRendered(html, Expected("MegamenuActive defaults", "megamenu-active", closes = "</span></div>"))
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
        val expected = Expected("MegamenuActive all flags", "megamenu-active zz-extra", closes = "</span></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun megamenuActiveClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        assertRendered(html, Expected("MegamenuActiveClasses defaults", "megamenu-active"))
    }

    @Test
    fun megamenuActiveClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        val expected = Expected("MegamenuActiveClasses all flags", "megamenu-active", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun megamenuPanel_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuPanel(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "MegamenuPanel defaults emits no class")
        assertTrue(html.endsWith("</div></div>"), "MegamenuPanel closes")
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
        val expected = Expected("MegamenuPanel all flags", "zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }
}
