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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "aura zz-extra", "Aura all flags", closes = "</div></div>")
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

    @Test
    fun aura_style_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Dual,
                content = { },
            )
        }
        assertRendered(html, "aura aura-dual", "Aura style Dual")
    }

    @Test
    fun aura_style_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Rainbow,
                content = { },
            )
        }
        assertRendered(html, "aura aura-rainbow", "Aura style Rainbow")
    }

    @Test
    fun aura_style_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Holo,
                content = { },
            )
        }
        assertRendered(html, "aura aura-holo", "Aura style Holo")
    }

    @Test
    fun aura_style_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Gold,
                content = { },
            )
        }
        assertRendered(html, "aura aura-gold", "Aura style Gold")
    }

    @Test
    fun aura_style_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Silver,
                content = { },
            )
        }
        assertRendered(html, "aura aura-silver", "Aura style Silver")
    }

    @Test
    fun aura_style_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(
                style = AuraStyle.Glow,
                content = { },
            )
        }
        assertRendered(html, "aura aura-glow", "Aura style Glow")
    }

    @Test
    fun auraClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        assertRendered(html, "aura", "AuraClasses defaults")
    }

    @Test
    fun auraClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses()
        }
        assertRendered(html, "aura", "AuraClasses all flags")
    }

    @Test
    fun auraClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Xs,
            )
        }
        assertRendered(html, "aura aura-xs", "AuraClasses size Xs")
    }

    @Test
    fun auraClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Sm,
            )
        }
        assertRendered(html, "aura aura-sm", "AuraClasses size Sm")
    }

    @Test
    fun auraClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Md,
            )
        }
        assertRendered(html, "aura aura-md", "AuraClasses size Md")
    }

    @Test
    fun auraClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Lg,
            )
        }
        assertRendered(html, "aura aura-lg", "AuraClasses size Lg")
    }

    @Test
    fun auraClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                size = AuraSize.Xl,
            )
        }
        assertRendered(html, "aura aura-xl", "AuraClasses size Xl")
    }

    @Test
    fun auraClasses_style_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Dual,
            )
        }
        assertRendered(html, "aura aura-dual", "AuraClasses style Dual")
    }

    @Test
    fun auraClasses_style_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Rainbow,
            )
        }
        assertRendered(html, "aura aura-rainbow", "AuraClasses style Rainbow")
    }

    @Test
    fun auraClasses_style_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Holo,
            )
        }
        assertRendered(html, "aura aura-holo", "AuraClasses style Holo")
    }

    @Test
    fun auraClasses_style_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Gold,
            )
        }
        assertRendered(html, "aura aura-gold", "AuraClasses style Gold")
    }

    @Test
    fun auraClasses_style_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Silver,
            )
        }
        assertRendered(html, "aura aura-silver", "AuraClasses style Silver")
    }

    @Test
    fun auraClasses_style_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAuraClasses(
                style = AuraStyle.Glow,
            )
        }
        assertRendered(html, "aura aura-glow", "AuraClasses style Glow")
    }
}
