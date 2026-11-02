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

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
    }

    @Test
    fun card_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard()
        }
        assertRendered(html, Expected("Card defaults", "card", closes = "</div></div>"))
    }

    @Test
    fun card_all_flags() {
        val html = allFlags(Expected("Card all flags", "card zz-extra", closes = "</div></div>")) { daisyCard(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun card_size() = assertEachEntry("Card size", mapOf(CardSize.Xs to "card card-xs", CardSize.Sm to "card card-sm", CardSize.Md to "card card-md", CardSize.Lg to "card card-lg", CardSize.Xl to "card card-xl")) { daisyCard(size = it) }

    @Test
    fun card_style() = assertEachEntry("Card style", mapOf(CardStyle.Border to "card card-border", CardStyle.Dash to "card card-dash")) { daisyCard(style = it) }

    @Test
    fun card_layout() = assertEachEntry("Card layout", mapOf(CardLayout.Side to "card card-side", CardLayout.ImageFull to "card image-full")) { daisyCard(layout = it) }

    @Test
    fun cardClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardClasses()
        }
        assertRendered(html, Expected("CardClasses defaults", "card"))
    }

    @Test
    fun cardClasses_all_flags() = assertRenders(Expected("CardClasses all flags", "card")) { daisyCardClasses() }

    @Test
    fun cardClasses_size() = assertEachEntry("CardClasses size", mapOf(CardSize.Xs to "card card-xs", CardSize.Sm to "card card-sm", CardSize.Md to "card card-md", CardSize.Lg to "card card-lg", CardSize.Xl to "card card-xl")) { daisyCardClasses(size = it) }

    @Test
    fun cardClasses_style() = assertEachEntry("CardClasses style", mapOf(CardStyle.Border to "card card-border", CardStyle.Dash to "card card-dash")) { daisyCardClasses(style = it) }

    @Test
    fun cardClasses_layout() = assertEachEntry("CardClasses layout", mapOf(CardLayout.Side to "card card-side", CardLayout.ImageFull to "card image-full")) { daisyCardClasses(layout = it) }

    @Test
    fun cardTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle()
        }
        assertRendered(html, Expected("CardTitle defaults", "card-title", closes = "</h2></div>"))
    }

    @Test
    fun cardTitle_all_flags() {
        val html = allFlags(Expected("CardTitle all flags", "card-title zz-extra", closes = "</h2></div>")) { daisyCardTitle(id = ID, extraClasses = EXTRA, block = BLOCK) }
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
    fun cardTitleClasses_all_flags() = assertRenders(Expected("CardTitleClasses all flags", "card-title")) { daisyCardTitleClasses() }

    @Test
    fun cardBody_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody()
        }
        assertRendered(html, Expected("CardBody defaults", "card-body", closes = "</div></div>"))
    }

    @Test
    fun cardBody_all_flags() {
        val html = allFlags(Expected("CardBody all flags", "card-body zz-extra", closes = "</div></div>")) { daisyCardBody(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun cardBodyClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBodyClasses()
        }
        assertRendered(html, Expected("CardBodyClasses defaults", "card-body"))
    }

    @Test
    fun cardBodyClasses_all_flags() = assertRenders(Expected("CardBodyClasses all flags", "card-body")) { daisyCardBodyClasses() }

    @Test
    fun cardActions_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions()
        }
        assertRendered(html, Expected("CardActions defaults", "card-actions", closes = "</div></div>"))
    }

    @Test
    fun cardActions_all_flags() {
        val html = allFlags(Expected("CardActions all flags", "card-actions zz-extra", closes = "</div></div>")) { daisyCardActions(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun cardActionsClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActionsClasses()
        }
        assertRendered(html, Expected("CardActionsClasses defaults", "card-actions"))
    }

    @Test
    fun cardActionsClasses_all_flags() = assertRenders(Expected("CardActionsClasses all flags", "card-actions")) { daisyCardActionsClasses() }
}
