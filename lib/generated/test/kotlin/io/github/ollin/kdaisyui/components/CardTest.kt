package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CardTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun card() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        assertDocumented(html, "div", "card", "Card")
    }

    @Test
    fun card_daisyCardBody() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardBody() {
            }
        }
        assertDocumented(html, "div", "card-body", "Card")
    }

    @Test
    fun card_daisyCardTitle() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardTitle() {
            }
        }
        assertDocumented(html, "h2", "card-title", "Card")
    }

    @Test
    fun card_daisyCardActions() {
        val html = createHTML(prettyPrint = false).div {
            daisyCardActions() {
            }
        }
        assertDocumented(html, "div", "card-actions", "Card")
    }

    @Test
    fun card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary", "Card")
    }

    @Test
    fun pricing_card_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            daisyBadge(size = BadgeSize.Xs, color = BadgeColor.Warning) {
            }
        }
        assertDocumented(html, "span", "badge badge-warning badge-xs", "Pricing Card")
    }

    @Test
    fun pricing_card_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Primary, width = ButtonWidth.Block) {
            }
        }
        assertDocumented(html, "button", "btn btn-block btn-primary", "Pricing Card")
    }

    @Test
    fun card_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Xs) {
            }
        }
        assertDocumented(html, "div", "card card-xs", "Card sizes")
    }

    @Test
    fun card_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Sm) {
            }
        }
        assertDocumented(html, "div", "card card-sm", "Card sizes")
    }

    @Test
    fun card_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Md) {
            }
        }
        assertDocumented(html, "div", "card card-md", "Card sizes")
    }

    @Test
    fun card_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Lg) {
            }
        }
        assertDocumented(html, "div", "card card-lg", "Card sizes")
    }

    @Test
    fun card_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(size = CardSize.Xl) {
            }
        }
        assertDocumented(html, "div", "card card-xl", "Card sizes")
    }

    @Test
    fun card_with_a_card_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(style = CardStyle.Border) {
            }
        }
        assertDocumented(html, "div", "card card-border", "Card with a card-border")
    }

    @Test
    fun card_with_a_dash_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(style = CardStyle.Dash) {
            }
        }
        assertDocumented(html, "div", "card card-dash", "Card with a dash border")
    }

    @Test
    fun card_with_badge_daisyBadge() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(color = BadgeColor.Secondary) }
        }
        assertDocumented(html, "div", "badge badge-secondary", "Card with badge")
    }

    @Test
    fun card_with_badge_daisyBadge_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyBadgeClasses(outlineStyle = BadgeOutlineStyle.Outline) }
        }
        assertDocumented(html, "div", "badge badge-outline", "Card with badge")
    }

    @Test
    fun card_with_image_overlay() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(layout = CardLayout.ImageFull) {
            }
        }
        assertDocumented(html, "div", "card image-full", "Card with image overlay")
    }

    @Test
    fun card_with_custom_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Card with custom color")
    }

    @Test
    fun centered_card_with_neutral_color_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost", "Centered card with neutral color")
    }

    @Test
    fun card_with_action_on_top_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Square, size = ButtonSize.Sm) {
            }
        }
        assertDocumented(html, "button", "btn btn-sm btn-square", "Card with action on top")
    }

    @Test
    fun card_with_image_on_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(layout = CardLayout.Side) {
            }
        }
        assertDocumented(html, "div", "card card-side", "Card with image on side")
    }

    @Test
    fun responsive_card_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(extraClasses = "lg:card-side") {
            }
        }
        assertDocumented(html, "div", "card lg:card-side", "Responsive card (vertical on small screen, horizontal on large screen)")
    }

    @Test
    fun selectable_cards_daisyJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin() {
            }
        }
        assertDocumented(html, "div", "join", "Selectable cards")
    }
}
