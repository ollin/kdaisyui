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
    fun card_layout_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                layout = CardLayout.Side,
                content = { },
            )
        }
        assertRendered(html, "card card-side", "Card layout Side")
    }

    @Test
    fun card_layout_imagefull() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(
                layout = CardLayout.ImageFull,
                content = { },
            )
        }
        assertRendered(html, "card image-full", "Card layout ImageFull")
    }

    @Test
    fun cardClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses()
        }
        assertRendered(html, "card", "CardClasses defaults")
    }

    @Test
    fun cardClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses()
        }
        assertRendered(html, "card", "CardClasses all flags")
    }

    @Test
    fun cardClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Xs,
            )
        }
        assertRendered(html, "card card-xs", "CardClasses size Xs")
    }

    @Test
    fun cardClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Sm,
            )
        }
        assertRendered(html, "card card-sm", "CardClasses size Sm")
    }

    @Test
    fun cardClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Md,
            )
        }
        assertRendered(html, "card card-md", "CardClasses size Md")
    }

    @Test
    fun cardClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Lg,
            )
        }
        assertRendered(html, "card card-lg", "CardClasses size Lg")
    }

    @Test
    fun cardClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                size = CardSize.Xl,
            )
        }
        assertRendered(html, "card card-xl", "CardClasses size Xl")
    }

    @Test
    fun cardClasses_style_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                style = CardStyle.Border,
            )
        }
        assertRendered(html, "card card-border", "CardClasses style Border")
    }

    @Test
    fun cardClasses_style_dash() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                style = CardStyle.Dash,
            )
        }
        assertRendered(html, "card card-dash", "CardClasses style Dash")
    }

    @Test
    fun cardClasses_layout_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                layout = CardLayout.Side,
            )
        }
        assertRendered(html, "card card-side", "CardClasses layout Side")
    }

    @Test
    fun cardClasses_layout_imagefull() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses(
                layout = CardLayout.ImageFull,
            )
        }
        assertRendered(html, "card image-full", "CardClasses layout ImageFull")
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
    fun cardTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitleClasses()
        }
        assertRendered(html, "card-title", "CardTitleClasses defaults")
    }

    @Test
    fun cardTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitleClasses()
        }
        assertRendered(html, "card-title", "CardTitleClasses all flags")
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
    fun cardBodyClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBodyClasses()
        }
        assertRendered(html, "card-body", "CardBodyClasses defaults")
    }

    @Test
    fun cardBodyClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBodyClasses()
        }
        assertRendered(html, "card-body", "CardBodyClasses all flags")
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

    @Test
    fun cardActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActionsClasses()
        }
        assertRendered(html, "card-actions", "CardActionsClasses defaults")
    }

    @Test
    fun cardActionsClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActionsClasses()
        }
        assertRendered(html, "card-actions", "CardActionsClasses all flags")
    }
}
