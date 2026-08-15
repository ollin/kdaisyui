package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AuraCoverageTest {

    @Test
    fun aura_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura", actualClasses, "Aura defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-dual aura-glow aura-gold aura-holo aura-rainbow aura-silver zz-extra", actualClasses, "Aura all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Aura id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Aura attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Aura content")
    }

    @Test
    fun aura_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-xs", actualClasses, "Aura size Xs")
    }

    @Test
    fun aura_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-sm", actualClasses, "Aura size Sm")
    }

    @Test
    fun aura_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-md", actualClasses, "Aura size Md")
    }

    @Test
    fun aura_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-lg", actualClasses, "Aura size Lg")
    }

    @Test
    fun aura_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("aura aura-xl", actualClasses, "Aura size Xl")
    }
}
