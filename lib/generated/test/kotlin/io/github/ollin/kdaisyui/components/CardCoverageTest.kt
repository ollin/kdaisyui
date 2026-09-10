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
                border = true,
                dash = true,
                imageFull = true,
                side = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "card card-border card-dash card-image-full card-side zz-extra", "Card all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Card id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Card attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Card content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "CardTitle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CardTitle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CardTitle content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "CardBody id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CardBody attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CardBody content")
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
        assertTrue(html.contains("id=\"x-cov-id\""), "CardActions id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CardActions attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CardActions content")
    }
}
