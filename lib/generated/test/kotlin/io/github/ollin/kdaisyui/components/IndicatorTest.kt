package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a

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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Status Indicator")
    }

    @Test
    fun status_indicator_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyStatusClasses(color = StatusColor.Success) }) {
            }
        }
        val expectedClasses = "indicator-item status status-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Status Indicator")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Status Indicator")
    }

    @Test
    fun badge_as_indicator_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses(color = BadgeColor.Primary) }) {
            }
        }
        val expectedClasses = "badge badge-primary indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Badge as indicator")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Badge as indicator")
    }

    @Test
    fun for_button_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for button")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for button")
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
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for button")
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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for tab")
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
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for tab")
    }

    @Test
    fun for_tab() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyIndicatorClasses(); daisyTabClasses(active = true) }
        }
        val expectedClasses = "indicator tab tab-active"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for tab")
        assertEquals("a", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for tab")
    }

    @Test
    fun for_tab_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for tab")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for tab")
    }

    @Test
    fun for_avatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(attrs = { daisyAvatarClasses() }) {
            }
        }
        val expectedClasses = "avatar indicator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for avatar")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for avatar")
    }

    @Test
    fun for_an_input_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        val expectedClasses = "input"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for for an input")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for for an input")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyIndicatorItemClasses(verticalPlacement = IndicatorItemVerticalPlacement.Bottom) }
        }
        val expectedClasses = "indicator-bottom indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for A button as an indicator for a card")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for A button as an indicator for a card")
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
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for A button as an indicator for a card")
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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for A button as an indicator for a card")
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
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for A button as an indicator for a card")
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
        assertEquals("h2", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for A button as an indicator for a card")
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
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for in center of an image")
    }

    @Test
    fun indicator_top_default_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-top (default) indicator-start")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-top (default) indicator-start")
    }

    @Test
    fun indicator_top_default_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-center indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-top (default) indicator-center")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-top (default) indicator-center")
    }

    @Test
    fun indicator_middle_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item indicator-middle indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-start")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-middle indicator-start")
    }

    @Test
    fun indicator_middle_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-center indicator-item indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-center")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-middle indicator-center")
    }

    @Test
    fun indicator_middle_indicator_end_default_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-middle indicator-end (default)")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-middle indicator-end (default)")
    }

    @Test
    fun indicator_bottom_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-bottom indicator-item indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-start")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-bottom indicator-start")
    }

    @Test
    fun indicator_bottom_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-bottom indicator-center indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-center")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-bottom indicator-center")
    }

    @Test
    fun indicator_bottom_indicator_end_default_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-bottom indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for indicator-bottom indicator-end (default)")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for indicator-bottom indicator-end (default)")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-item indicator-start indicator-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-center indicator-item indicator-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-end indicator-item indicator-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-item indicator-middle indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-center indicator-item indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-end indicator-item indicator-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-bottom indicator-item indicator-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-bottom indicator-center indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_9() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        val expectedClasses = "badge indicator-bottom indicator-end indicator-item"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for multiple indicators")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for multiple indicators")
    }

    @Test
    fun responsive_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Start, extraClasses = "sm:indicator-middle md:indicator-bottom lg:indicator-center xl:indicator-end", attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        val expectedClasses = "badge badge-secondary indicator-item indicator-start lg:indicator-center md:indicator-bottom sm:indicator-middle xl:indicator-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Responsive")
    }
}
