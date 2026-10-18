package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.label

class HeroTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun centered_hero() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero() {
            }
        }
        assertDocumented(html, "div", "hero", "Centered hero")
    }

    @Test
    fun centered_hero_daisyHeroContent() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContent() {
            }
        }
        assertDocumented(html, "div", "hero-content", "Centered hero")
    }

    @Test
    fun centered_hero_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary", "Centered hero")
    }

    @Test
    fun hero_with_form_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        assertDocumented(html, "fieldset", "fieldset", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyLabelClasses() }
        }
        assertDocumented(html, "label", "label", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyLink() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink(hover = true) {
            }
        }
        assertDocumented(html, "a", "link link-hover", "Hero with form")
    }

    @Test
    fun hero_with_form_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral", "Hero with form")
    }

    @Test
    fun hero_with_overlay_image_daisyHeroOverlay() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlay() {
            }
        }
        assertDocumented(html, "div", "hero-overlay", "Hero with overlay image")
    }
}
