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
    }

    @Test
    fun aura_around_a_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        val expectedClasses = "aura"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura around a button")
    }

    @Test
    fun aura_dual() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(dual = true) {
            }
        }
        val expectedClasses = "aura aura-dual"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura dual")
    }

    @Test
    fun aura_rainbow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(rainbow = true) {
            }
        }
        val expectedClasses = "aura aura-rainbow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura rainbow")
    }

    @Test
    fun aura_holo() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(holo = true) {
            }
        }
        val expectedClasses = "aura aura-holo"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura holo")
    }

    @Test
    fun aura_glow() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(glow = true) {
            }
        }
        val expectedClasses = "aura aura-glow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura glow")
    }

    @Test
    fun aura_gold() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(gold = true) {
            }
        }
        val expectedClasses = "aura aura-gold"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura gold")
    }

    @Test
    fun aura_silver() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(silver = true) {
            }
        }
        val expectedClasses = "aura aura-silver"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura silver")
    }

    @Test
    fun aura_with_custom_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        val expectedClasses = "aura"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura with custom color")
    }

    @Test
    fun aura_with_custom_color_and_custom_background_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        val expectedClasses = "aura"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura with custom color and custom background color")
    }

    @Test
    fun aura_rainbow_around_a_pricing_card() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(rainbow = true) {
            }
        }
        val expectedClasses = "aura aura-rainbow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura rainbow around a pricing card")
    }

    @Test
    fun aura_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura() {
            }
        }
        val expectedClasses = "aura"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura sizes")
    }

    @Test
    fun aura_with_custom_animation_duration() {
        val html = createHTML(prettyPrint = false).div {
            daisyAura(rainbow = true) {
            }
        }
        val expectedClasses = "aura aura-rainbow"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Aura with custom animation duration")
    }
}
