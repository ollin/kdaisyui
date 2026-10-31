package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class HeroCoverageTest {

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
    fun hero_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero(
                content = { },
            )
        }
        assertRendered(html, Expected("Hero defaults", "hero", closes = "</div></div>"))
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
        val expected = Expected("Hero all flags", "hero zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun heroClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroClasses()
        }
        assertRendered(html, Expected("HeroClasses defaults", "hero"))
    }

    @Test
    fun heroClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroClasses()
        }
        val expected = Expected("HeroClasses all flags", "hero", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun heroContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContent(
                content = { },
            )
        }
        assertRendered(html, Expected("HeroContent defaults", "hero-content", closes = "</div></div>"))
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
        val expected = Expected("HeroContent all flags", "hero-content zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun heroContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContentClasses()
        }
        assertRendered(html, Expected("HeroContentClasses defaults", "hero-content"))
    }

    @Test
    fun heroContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContentClasses()
        }
        val expected = Expected("HeroContentClasses all flags", "hero-content", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun heroOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlay(
                content = { },
            )
        }
        assertRendered(html, Expected("HeroOverlay defaults", "hero-overlay", closes = "</div></div>"))
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
        val expected = Expected("HeroOverlay all flags", "hero-overlay zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun heroOverlayClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlayClasses()
        }
        assertRendered(html, Expected("HeroOverlayClasses defaults", "hero-overlay"))
    }

    @Test
    fun heroOverlayClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlayClasses()
        }
        val expected = Expected("HeroOverlayClasses all flags", "hero-overlay", content = false)
        assertRendered(html, expected)
    }
}
