package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AuraTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun aura() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        assertDocumented(html, "div", "aura", "Aura")
    }

    @Test
    fun aura_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "Aura")
    }

    @Test
    fun aura_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Aura")
    }

    @Test
    fun aura_around_a_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Aura around a button")
    }

    @Test
    fun aura_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Dual) {
            }
        }
        assertDocumented(html, "div", "aura aura-dual", "Aura dual")
    }

    @Test
    fun aura_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Rainbow) {
            }
        }
        assertDocumented(html, "div", "aura aura-rainbow", "Aura rainbow")
    }

    @Test
    fun aura_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Holo) {
            }
        }
        assertDocumented(html, "div", "aura aura-holo", "Aura holo")
    }

    @Test
    fun aura_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Glow) {
            }
        }
        assertDocumented(html, "div", "aura aura-glow", "Aura glow")
    }

    @Test
    fun aura_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Gold) {
            }
        }
        assertDocumented(html, "div", "aura aura-gold", "Aura gold")
    }

    @Test
    fun aura_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Silver) {
            }
        }
        assertDocumented(html, "div", "aura aura-silver", "Aura silver")
    }

    @Test
    fun aura_rainbow_around_a_pricing_card_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Warning) {
            }
        }
        assertDocumented(html, "span", "badge badge-warning badge-xs", "Aura rainbow around a pricing card")
    }

    @Test
    fun aura_rainbow_around_a_pricing_card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary, width = ButtonWidth.Block) {
            }
        }
        assertDocumented(html, "button", "btn btn-block btn-primary", "Aura rainbow around a pricing card")
    }

    @Test
    fun aura_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Xs) {
            }
        }
        assertDocumented(html, "div", "aura aura-xs", "Aura sizes")
    }

    @Test
    fun aura_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Sm) {
            }
        }
        assertDocumented(html, "div", "aura aura-sm", "Aura sizes")
    }

    @Test
    fun aura_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Lg) {
            }
        }
        assertDocumented(html, "div", "aura aura-lg", "Aura sizes")
    }

    @Test
    fun aura_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Xl) {
            }
        }
        assertDocumented(html, "div", "aura aura-xl", "Aura sizes")
    }
}
