package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AuraCoverageTest {

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
    fun aura_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                content = { },
            )
        }
        assertRendered(html, "aura", "Aura defaults", closes = "</div></div>")
    }

    @Test
    fun aura_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                id = htmlId("x-cov-id"),
                dual = true,
                glow = true,
                gold = true,
                holo = true,
                rainbow = true,
                silver = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "aura aura-dual aura-glow aura-gold aura-holo aura-rainbow aura-silver zz-extra", "Aura all flags", closes = "</div></div>")
        assertCommonFlags(html, "Aura")
    }

    @Test
    fun aura_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "aura aura-xs", "Aura size Xs")
    }

    @Test
    fun aura_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "aura aura-sm", "Aura size Sm")
    }

    @Test
    fun aura_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Md,
                content = { },
            )
        }
        assertRendered(html, "aura aura-md", "Aura size Md")
    }

    @Test
    fun aura_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "aura aura-lg", "Aura size Lg")
    }

    @Test
    fun aura_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "aura aura-xl", "Aura size Xl")
    }
}
