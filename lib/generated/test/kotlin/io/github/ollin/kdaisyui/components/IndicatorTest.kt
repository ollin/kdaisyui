package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.a

class IndicatorTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun status_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator() {
            }
        }
        assertDocumented(html, "div", "indicator", "Status Indicator")
    }

    @Test
    fun status_indicator_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyStatusClasses(color = StatusColor.Success) }) {
            }
        }
        assertDocumented(html, "span", "indicator-item status status-success", "Status Indicator")
    }

    @Test
    fun badge_as_indicator_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses(color = BadgeColor.Primary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-primary indicator-item", "Badge as indicator")
    }

    @Test
    fun for_button_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item", "for button")
    }

    @Test
    fun for_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "for button")
    }

    @Test
    fun for_tab_daisyTabs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabs(style = TabsStyle.Lift) {
            }
        }
        assertDocumented(html, "div", "tabs tabs-lift", "for tab")
    }

    @Test
    fun for_tab_daisyTab() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab() {
            }
        }
        assertDocumented(html, "a", "tab", "for tab")
    }

    @Test
    fun for_tab() {
        val html = createHTML(prettyPrint = false).div {
            a { daisyIndicatorClasses(); daisyTabClasses(active = true) }
        }
        assertDocumented(html, "a", "indicator tab tab-active", "for tab")
    }

    @Test
    fun for_tab_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-item", "for tab")
    }

    @Test
    fun for_avatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicator(attrs = { daisyAvatarClasses() }) {
            }
        }
        assertDocumented(html, "div", "avatar indicator", "for avatar")
    }

    @Test
    fun for_an_input_daisyInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput()
        }
        assertDocumented(html, "input", "input", "for an input")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyIndicatorItemClasses(verticalPlacement = IndicatorItemVerticalPlacement.Bottom) }
        }
        assertDocumented(html, "div", "indicator-bottom indicator-item", "A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary", "A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCard() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "A button as an indicator for a card")
    }

    @Test
    fun a_button_as_an_indicator_for_a_card_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        assertDocumented(html, "h2", "card-title", "A button as an indicator for a card")
    }

    @Test
    fun in_center_of_an_image_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Center, verticalPlacement = IndicatorItemVerticalPlacement.Middle) {
            }
        }
        assertDocumented(html, "span", "indicator-center indicator-item indicator-middle", "in center of an image")
    }

    @Test
    fun indicator_top_default_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item indicator-start", "indicator-top (default) indicator-start")
    }

    @Test
    fun indicator_top_default_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-center indicator-item", "indicator-top (default) indicator-center")
    }

    @Test
    fun indicator_middle_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item indicator-middle indicator-start", "indicator-middle indicator-start")
    }

    @Test
    fun indicator_middle_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-center indicator-item indicator-middle", "indicator-middle indicator-center")
    }

    @Test
    fun indicator_middle_indicator_end_default_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item indicator-middle", "indicator-middle indicator-end (default)")
    }

    @Test
    fun indicator_bottom_indicator_start_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-bottom indicator-item indicator-start", "indicator-bottom indicator-start")
    }

    @Test
    fun indicator_bottom_indicator_center_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-bottom indicator-center indicator-item", "indicator-bottom indicator-center")
    }

    @Test
    fun indicator_bottom_indicator_end_default_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-bottom indicator-item", "indicator-bottom indicator-end (default)")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-item indicator-start indicator-top", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-center indicator-item indicator-top", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Top, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-end indicator-item indicator-top", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-item indicator-middle indicator-start", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-center indicator-item indicator-middle", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Middle, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-end indicator-item indicator-middle", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Start, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-bottom indicator-item indicator-start", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.Center, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-bottom indicator-center indicator-item", "multiple indicators")
    }

    @Test
    fun multiple_indicators_daisyIndicatorItem_9() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(verticalPlacement = IndicatorItemVerticalPlacement.Bottom, horizontalPlacement = IndicatorItemHorizontalPlacement.End, attrs = { daisyBadgeClasses() }) {
            }
        }
        assertDocumented(html, "span", "badge indicator-bottom indicator-end indicator-item", "multiple indicators")
    }

    @Test
    fun responsive_daisyIndicatorItem() {
        val html = createHTML(prettyPrint = false).div {
            daisyIndicatorItem(horizontalPlacement = IndicatorItemHorizontalPlacement.Start, extraClasses = "sm:indicator-middle md:indicator-bottom lg:indicator-center xl:indicator-end", attrs = { daisyBadgeClasses(color = BadgeColor.Secondary) }) {
            }
        }
        assertDocumented(html, "span", "badge badge-secondary indicator-item indicator-start lg:indicator-center md:indicator-bottom sm:indicator-middle xl:indicator-end", "Responsive")
    }
}
