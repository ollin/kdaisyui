package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class HeroCoverageTest {

    @Test
    fun hero_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero", actualClasses, "Hero defaults")
    }

    @Test
    fun hero_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero zz-extra", actualClasses, "Hero all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Hero id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Hero attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Hero content")
    }

    @Test
    fun heroContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContent(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero-content", actualClasses, "HeroContent defaults")
    }

    @Test
    fun heroContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero-content zz-extra", actualClasses, "HeroContent all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "HeroContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "HeroContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "HeroContent content")
    }

    @Test
    fun heroOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlay(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero-overlay", actualClasses, "HeroOverlay defaults")
    }

    @Test
    fun heroOverlay_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlay(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("hero-overlay zz-extra", actualClasses, "HeroOverlay all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "HeroOverlay id")
        assertTrue(html.contains("data-attrs=\"yes\""), "HeroOverlay attrs")
        assertTrue(html.contains("data-content=\"yes\""), "HeroOverlay content")
    }
}
