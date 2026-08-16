package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class BadgeTest {

    @Test
    fun badge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge")
    }

    @Test
    fun badge_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge sizes")
    }

    @Test
    fun badge_with_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with colors")
    }

    @Test
    fun badge_with_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(soft = true) {
            }
        }
        val expectedClasses = "badge badge-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with soft style")
    }

    @Test
    fun badge_with_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(outline = true) {
            }
        }
        val expectedClasses = "badge badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with outline style")
    }

    @Test
    fun badge_with_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(dash = true) {
            }
        }
        val expectedClasses = "badge badge-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with dash style")
    }

    @Test
    fun neutral_badge_with_outline_or_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(outline = true, dash = true) {
            }
        }
        val expectedClasses = "badge badge-dash badge-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for neutral badge with outline or dash style")
    }

    @Test
    fun badge_ghost() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(ghost = true) {
            }
        }
        val expectedClasses = "badge badge-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge ghost")
    }

    @Test
    fun empty_badge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Empty badge")
    }

    @Test
    fun badge_with_icon() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge with icon")
    }

    @Test
    fun badge_in_a_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge in a text")
    }

    @Test
    fun badge_in_a_button() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge() {
            }
        }
        val expectedClasses = "badge"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge in a button")
    }
}
