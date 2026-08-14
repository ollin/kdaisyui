package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class HeroTest {

    @Test
    fun centered_hero() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        val expectedClasses = "hero"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered hero")
    }

    @Test
    fun hero_with_figure() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        val expectedClasses = "hero"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hero with figure")
    }

    @Test
    fun hero_with_figure_but_reverse_order() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        val expectedClasses = "hero"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hero with figure but reverse order")
    }

    @Test
    fun hero_with_form() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        val expectedClasses = "hero"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hero with form")
    }

    @Test
    fun hero_with_overlay_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        val expectedClasses = "hero"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Hero with overlay image")
    }
}
