package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MegamenuCoverageTest {

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
    fun megamenu_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                content = { },
            )
        }
        assertRendered(html, "megamenu", "Megamenu defaults", closes = "</div></div>")
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
        assertRendered(html, "megamenu megamenu-vertical zz-extra", "Megamenu all flags", closes = "</div></div>")
        assertCommonFlags(html, "Megamenu")
    }

    @Test
    fun megamenu_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-xs", "Megamenu size Xs")
    }

    @Test
    fun megamenu_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-sm", "Megamenu size Sm")
    }

    @Test
    fun megamenu_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Md,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-md", "Megamenu size Md")
    }

    @Test
    fun megamenu_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-lg", "Megamenu size Lg")
    }

    @Test
    fun megamenu_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                size = MegamenuSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-xl", "Megamenu size Xl")
    }

    @Test
    fun megamenu_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                width = MegamenuWidth.Wide,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-wide", "Megamenu width Wide")
    }

    @Test
    fun megamenu_width_full() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenu(
                width = MegamenuWidth.Full,
                content = { },
            )
        }
        assertRendered(html, "megamenu megamenu-full", "Megamenu width Full")
    }

    @Test
    fun megamenuClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses()
        }
        assertRendered(html, "megamenu", "MegamenuClasses defaults")
    }

    @Test
    fun megamenuClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                vertical = true,
            )
        }
        assertRendered(html, "megamenu megamenu-vertical", "MegamenuClasses all flags")
    }

    @Test
    fun megamenuClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Xs,
            )
        }
        assertRendered(html, "megamenu megamenu-xs", "MegamenuClasses size Xs")
    }

    @Test
    fun megamenuClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Sm,
            )
        }
        assertRendered(html, "megamenu megamenu-sm", "MegamenuClasses size Sm")
    }

    @Test
    fun megamenuClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Md,
            )
        }
        assertRendered(html, "megamenu megamenu-md", "MegamenuClasses size Md")
    }

    @Test
    fun megamenuClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Lg,
            )
        }
        assertRendered(html, "megamenu megamenu-lg", "MegamenuClasses size Lg")
    }

    @Test
    fun megamenuClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                size = MegamenuSize.Xl,
            )
        }
        assertRendered(html, "megamenu megamenu-xl", "MegamenuClasses size Xl")
    }

    @Test
    fun megamenuClasses_width_wide() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                width = MegamenuWidth.Wide,
            )
        }
        assertRendered(html, "megamenu megamenu-wide", "MegamenuClasses width Wide")
    }

    @Test
    fun megamenuClasses_width_full() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuClasses(
                width = MegamenuWidth.Full,
            )
        }
        assertRendered(html, "megamenu megamenu-full", "MegamenuClasses width Full")
    }

    @Test
    fun megamenuActive_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActive(
                content = { },
            )
        }
        assertRendered(html, "megamenu-active", "MegamenuActive defaults", closes = "</span></div>")
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
        assertRendered(html, "megamenu-active zz-extra", "MegamenuActive all flags", closes = "</span></div>")
        assertCommonFlags(html, "MegamenuActive")
    }

    @Test
    fun megamenuActiveClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        assertRendered(html, "megamenu-active", "MegamenuActiveClasses defaults")
    }

    @Test
    fun megamenuActiveClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMegamenuActiveClasses()
        }
        assertRendered(html, "megamenu-active", "MegamenuActiveClasses all flags")
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
        assertRendered(html, "zz-extra", "MegamenuPanel all flags", closes = "</div></div>")
        assertCommonFlags(html, "MegamenuPanel")
    }
}
