package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class IndicatorTest {

    @Test
    fun status_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        val expectedClasses = "indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status Indicator")
    }

    @Test
    fun for_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for button")
    }

    @Test
    fun for_tab_daisyTabs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift) {
            }
        }
        val expectedClasses = "tabs tabs-lift"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for tab")
    }

    @Test
    fun for_tab_daisyTab() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab() {
            }
        }
        val expectedClasses = "tab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for tab")
    }

    @Test
    fun for_an_input_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput() {
            }
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for an input")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom) {
            }
        }
        val expectedClasses = "indicator-bottom indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        val expectedClasses = "card-body"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        val expectedClasses = "card-title"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
    }

    @Test
    fun in_center_of_an_image_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Center, verticalPlacement = IndicatorItemVerticalPlacement.Middle) {
            }
        }
        val expectedClasses = "indicator-center indicator-item indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for in center of an image")
    }
}
