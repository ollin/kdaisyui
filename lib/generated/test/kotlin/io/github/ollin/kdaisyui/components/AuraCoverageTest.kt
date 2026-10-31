package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AuraCoverageTest {

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
    fun aura_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                content = { },
            )
        }
        assertRendered(html, Expected("Aura defaults", "aura", closes = "</div></div>"))
    }

    @Test
    fun aura_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Aura all flags", "aura zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun aura_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura size Xs", "aura aura-xs"))
    }

    @Test
    fun aura_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura size Sm", "aura aura-sm"))
    }

    @Test
    fun aura_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura size Md", "aura aura-md"))
    }

    @Test
    fun aura_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura size Lg", "aura aura-lg"))
    }

    @Test
    fun aura_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                size = AuraSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura size Xl", "aura aura-xl"))
    }

    @Test
    fun aura_style_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Dual,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Dual", "aura aura-dual"))
    }

    @Test
    fun aura_style_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Rainbow,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Rainbow", "aura aura-rainbow"))
    }

    @Test
    fun aura_style_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Holo,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Holo", "aura aura-holo"))
    }

    @Test
    fun aura_style_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Gold,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Gold", "aura aura-gold"))
    }

    @Test
    fun aura_style_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Silver,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Silver", "aura aura-silver"))
    }

    @Test
    fun aura_style_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Glow,
                content = { },
            )
        }
        assertRendered(html, Expected("Aura style Glow", "aura aura-glow"))
    }

    @Test
    fun auraClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        assertRendered(html, Expected("AuraClasses defaults", "aura"))
    }

    @Test
    fun auraClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        val expected = Expected("AuraClasses all flags", "aura", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun auraClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Xs,
            )
        }
        assertRendered(html, Expected("AuraClasses size Xs", "aura aura-xs"))
    }

    @Test
    fun auraClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Sm,
            )
        }
        assertRendered(html, Expected("AuraClasses size Sm", "aura aura-sm"))
    }

    @Test
    fun auraClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Md,
            )
        }
        assertRendered(html, Expected("AuraClasses size Md", "aura aura-md"))
    }

    @Test
    fun auraClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Lg,
            )
        }
        assertRendered(html, Expected("AuraClasses size Lg", "aura aura-lg"))
    }

    @Test
    fun auraClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Xl,
            )
        }
        assertRendered(html, Expected("AuraClasses size Xl", "aura aura-xl"))
    }

    @Test
    fun auraClasses_style_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Dual,
            )
        }
        assertRendered(html, Expected("AuraClasses style Dual", "aura aura-dual"))
    }

    @Test
    fun auraClasses_style_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Rainbow,
            )
        }
        assertRendered(html, Expected("AuraClasses style Rainbow", "aura aura-rainbow"))
    }

    @Test
    fun auraClasses_style_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Holo,
            )
        }
        assertRendered(html, Expected("AuraClasses style Holo", "aura aura-holo"))
    }

    @Test
    fun auraClasses_style_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Gold,
            )
        }
        assertRendered(html, Expected("AuraClasses style Gold", "aura aura-gold"))
    }

    @Test
    fun auraClasses_style_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Silver,
            )
        }
        assertRendered(html, Expected("AuraClasses style Silver", "aura aura-silver"))
    }

    @Test
    fun auraClasses_style_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Glow,
            )
        }
        assertRendered(html, Expected("AuraClasses style Glow", "aura aura-glow"))
    }
}
