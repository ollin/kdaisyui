package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CardCoverageTest {

    @Test
    fun card_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card", actualClasses, "Card defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-border card-dash card-image-full card-side zz-extra", actualClasses, "Card all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-xs", actualClasses, "Card size Xs")
    }

    @Test
    fun card_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-sm", actualClasses, "Card size Sm")
    }

    @Test
    fun card_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-md", actualClasses, "Card size Md")
    }

    @Test
    fun card_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-lg", actualClasses, "Card size Lg")
    }

    @Test
    fun card_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card card-xl", actualClasses, "Card size Xl")
    }

    @Test
    fun cardTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-title", actualClasses, "CardTitle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-title zz-extra", actualClasses, "CardTitle all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-title", actualClasses, "CardTitle text")
        assertTrue(html.contains("txtmark"), "CardTitle text content")
    }

    @Test
    fun cardBody_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-body", actualClasses, "CardBody defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-body zz-extra", actualClasses, "CardBody all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-actions", actualClasses, "CardActions defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("card-actions zz-extra", actualClasses, "CardActions all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "CardActions id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CardActions attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CardActions content")
    }
}
