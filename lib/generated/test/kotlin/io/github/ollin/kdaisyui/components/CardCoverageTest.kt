package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CardCoverageTest {

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
    fun card_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                content = { },
            )
        }
        assertRendered(html, "card", "Card defaults", closes = "</div></div>")
    }

    @Test
    fun card_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "card zz-extra", "Card all flags", closes = "</div></div>")
        assertCommonFlags(html, "Card")
    }

    @Test
    fun card_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "card card-xs", "Card size Xs")
    }

    @Test
    fun card_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "card card-sm", "Card size Sm")
    }

    @Test
    fun card_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Md,
                content = { },
            )
        }
        assertRendered(html, "card card-md", "Card size Md")
    }

    @Test
    fun card_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "card card-lg", "Card size Lg")
    }

    @Test
    fun card_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "card card-xl", "Card size Xl")
    }

    @Test
    fun card_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                style = CardStyle.Border,
                content = { },
            )
        }
        assertRendered(html, "card card-border", "Card style Border")
    }

    @Test
    fun card_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                style = CardStyle.Dash,
                content = { },
            )
        }
        assertRendered(html, "card card-dash", "Card style Dash")
    }

    @Test
    fun card_modifier_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                modifier = CardModifier.Side,
                content = { },
            )
        }
        assertRendered(html, "card card-side", "Card modifier Side")
    }

    @Test
    fun card_modifier_imagefull() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                modifier = CardModifier.ImageFull,
                content = { },
            )
        }
        assertRendered(html, "card card-image-full", "Card modifier ImageFull")
    }

    @Test
    fun cardTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle()
        }
        assertRendered(html, "card-title", "CardTitle defaults", closes = "</h2></div>")
    }

    @Test
    fun cardTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "card-title zz-extra", "CardTitle all flags", closes = "</h2></div>")
        assertCommonFlags(html, "CardTitle")
    }

    @Test
    fun cardTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "card-title", "CardTitle text")
        assertTrue(html.contains("txtmark"), "CardTitle text content")
    }

    @Test
    fun cardBody_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody(
                content = { },
            )
        }
        assertRendered(html, "card-body", "CardBody defaults", closes = "</div></div>")
    }

    @Test
    fun cardBody_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "card-body zz-extra", "CardBody all flags", closes = "</div></div>")
        assertCommonFlags(html, "CardBody")
    }

    @Test
    fun cardActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions(
                content = { },
            )
        }
        assertRendered(html, "card-actions", "CardActions defaults", closes = "</div></div>")
    }

    @Test
    fun cardActions_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "card-actions zz-extra", "CardActions all flags", closes = "</div></div>")
        assertCommonFlags(html, "CardActions")
    }
}
