package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CardCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun card_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                content = { },
            )
        }
        assertRendered(html, Expected("Card defaults", "card", closes = "</div></div>"))
    }

    @Test
    fun card_all_flags() {
        val html = allFlags(Expected("Card all flags", "card zz-extra", closes = "</div></div>")) { daisyCard(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun card_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Card size Xs", "card card-xs"))
    }

    @Test
    fun card_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Card size Sm", "card card-sm"))
    }

    @Test
    fun card_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Card size Md", "card card-md"))
    }

    @Test
    fun card_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Card size Lg", "card card-lg"))
    }

    @Test
    fun card_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                size = CardSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Card size Xl", "card card-xl"))
    }

    @Test
    fun card_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                style = CardStyle.Border,
                content = { },
            )
        }
        assertRendered(html, Expected("Card style Border", "card card-border"))
    }

    @Test
    fun card_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                style = CardStyle.Dash,
                content = { },
            )
        }
        assertRendered(html, Expected("Card style Dash", "card card-dash"))
    }

    @Test
    fun card_layout_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                layout = CardLayout.Side,
                content = { },
            )
        }
        assertRendered(html, Expected("Card layout Side", "card card-side"))
    }

    @Test
    fun card_layout_imagefull() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                layout = CardLayout.ImageFull,
                content = { },
            )
        }
        assertRendered(html, Expected("Card layout ImageFull", "card image-full"))
    }

    @Test
    fun cardClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses()
        }
        assertRendered(html, Expected("CardClasses defaults", "card"))
    }

    @Test
    fun cardClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses()
        }
        val expected = Expected("CardClasses all flags", "card", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun cardClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Xs,
            )
        }
        assertRendered(html, Expected("CardClasses size Xs", "card card-xs"))
    }

    @Test
    fun cardClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Sm,
            )
        }
        assertRendered(html, Expected("CardClasses size Sm", "card card-sm"))
    }

    @Test
    fun cardClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Md,
            )
        }
        assertRendered(html, Expected("CardClasses size Md", "card card-md"))
    }

    @Test
    fun cardClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Lg,
            )
        }
        assertRendered(html, Expected("CardClasses size Lg", "card card-lg"))
    }

    @Test
    fun cardClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Xl,
            )
        }
        assertRendered(html, Expected("CardClasses size Xl", "card card-xl"))
    }

    @Test
    fun cardClasses_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                style = CardStyle.Border,
            )
        }
        assertRendered(html, Expected("CardClasses style Border", "card card-border"))
    }

    @Test
    fun cardClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                style = CardStyle.Dash,
            )
        }
        assertRendered(html, Expected("CardClasses style Dash", "card card-dash"))
    }

    @Test
    fun cardClasses_layout_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                layout = CardLayout.Side,
            )
        }
        assertRendered(html, Expected("CardClasses layout Side", "card card-side"))
    }

    @Test
    fun cardClasses_layout_imagefull() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                layout = CardLayout.ImageFull,
            )
        }
        assertRendered(html, Expected("CardClasses layout ImageFull", "card image-full"))
    }

    @Test
    fun cardTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle()
        }
        assertRendered(html, Expected("CardTitle defaults", "card-title", closes = "</h2></div>"))
    }

    @Test
    fun cardTitle_all_flags() {
        val html = allFlags(Expected("CardTitle all flags", "card-title zz-extra", closes = "</h2></div>")) { daisyCardTitle(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun cardTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, Expected("CardTitle text", "card-title"))
        assertTrue(html.contains("txtmark"), "CardTitle text content")
    }

    @Test
    fun cardTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitleClasses()
        }
        assertRendered(html, Expected("CardTitleClasses defaults", "card-title"))
    }

    @Test
    fun cardTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitleClasses()
        }
        val expected = Expected("CardTitleClasses all flags", "card-title", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun cardBody_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody(
                content = { },
            )
        }
        assertRendered(html, Expected("CardBody defaults", "card-body", closes = "</div></div>"))
    }

    @Test
    fun cardBody_all_flags() {
        val html = allFlags(Expected("CardBody all flags", "card-body zz-extra", closes = "</div></div>")) { daisyCardBody(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun cardBodyClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBodyClasses()
        }
        assertRendered(html, Expected("CardBodyClasses defaults", "card-body"))
    }

    @Test
    fun cardBodyClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBodyClasses()
        }
        val expected = Expected("CardBodyClasses all flags", "card-body", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun cardActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions(
                content = { },
            )
        }
        assertRendered(html, Expected("CardActions defaults", "card-actions", closes = "</div></div>"))
    }

    @Test
    fun cardActions_all_flags() {
        val html = allFlags(Expected("CardActions all flags", "card-actions zz-extra", closes = "</div></div>")) { daisyCardActions(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun cardActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActionsClasses()
        }
        assertRendered(html, Expected("CardActionsClasses defaults", "card-actions"))
    }

    @Test
    fun cardActionsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActionsClasses()
        }
        val expected = Expected("CardActionsClasses all flags", "card-actions", content = false)
        assertRendered(html, expected)
    }
}
