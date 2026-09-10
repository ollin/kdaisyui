package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class HeroCoverageTest {

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
    fun hero_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHero(
                content = { },
            )
        }
        assertRendered(html, "hero", "Hero defaults", closes = "</div></div>")
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
        assertRendered(html, "hero zz-extra", "Hero all flags", closes = "</div></div>")
        assertCommonFlags(html, "Hero")
    }

    @Test
    fun heroContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroContent(
                content = { },
            )
        }
        assertRendered(html, "hero-content", "HeroContent defaults", closes = "</div></div>")
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
        assertRendered(html, "hero-content zz-extra", "HeroContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "HeroContent")
    }

    @Test
    fun heroOverlay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyHeroOverlay(
                content = { },
            )
        }
        assertRendered(html, "hero-overlay", "HeroOverlay defaults", closes = "</label></div>")
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
        assertRendered(html, "hero-overlay zz-extra", "HeroOverlay all flags", closes = "</label></div>")
        assertCommonFlags(html, "HeroOverlay")
    }
}
