package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AuraTest {

    @Test
    fun aura() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        val expectedClasses = "aura"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura")
    }

    @Test
    fun aura_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura")
    }

    @Test
    fun aura_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura")
    }

    @Test
    fun aura_around_a_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura around a button")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura around a button")
    }

    @Test
    fun aura_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Dual) {
            }
        }
        val expectedClasses = "aura aura-dual"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura dual")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura dual")
    }

    @Test
    fun aura_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Rainbow) {
            }
        }
        val expectedClasses = "aura aura-rainbow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura rainbow")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura rainbow")
    }

    @Test
    fun aura_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Holo) {
            }
        }
        val expectedClasses = "aura aura-holo"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura holo")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura holo")
    }

    @Test
    fun aura_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Glow) {
            }
        }
        val expectedClasses = "aura aura-glow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura glow")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura glow")
    }

    @Test
    fun aura_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Gold) {
            }
        }
        val expectedClasses = "aura aura-gold"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura gold")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura gold")
    }

    @Test
    fun aura_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(style = AuraStyle.Silver) {
            }
        }
        val expectedClasses = "aura aura-silver"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura silver")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura silver")
    }

    @Test
    fun aura_rainbow_around_a_pricing_card_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Warning) {
            }
        }
        val expectedClasses = "badge badge-warning badge-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura rainbow around a pricing card")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura rainbow around a pricing card")
    }

    @Test
    fun aura_rainbow_around_a_pricing_card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary, width = ButtonWidth.Block) {
            }
        }
        val expectedClasses = "btn btn-block btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura rainbow around a pricing card")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura rainbow around a pricing card")
    }

    @Test
    fun aura_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Xs) {
            }
        }
        val expectedClasses = "aura aura-xs"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura sizes")
    }

    @Test
    fun aura_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Sm) {
            }
        }
        val expectedClasses = "aura aura-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura sizes")
    }

    @Test
    fun aura_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Lg) {
            }
        }
        val expectedClasses = "aura aura-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura sizes")
    }

    @Test
    fun aura_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(size = AuraSize.Xl) {
            }
        }
        val expectedClasses = "aura aura-xl"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura sizes")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Aura sizes")
    }
}
