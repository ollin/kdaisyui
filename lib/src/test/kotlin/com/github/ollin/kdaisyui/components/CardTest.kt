package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CardTest {

    @Test
    fun card() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card")
    }

    @Test
    fun pricing_card() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Pricing Card")
    }

    @Test
    fun card_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card sizes")
    }

    @Test
    fun card_with_a_card_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(border = true) {
            }
        }
        val expectedClasses = "card card-border"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with a card-border")
    }

    @Test
    fun card_with_a_dash_border() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(dash = true) {
            }
        }
        val expectedClasses = "card card-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with a dash border")
    }

    @Test
    fun card_with_badge() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with badge")
    }

    @Test
    fun card_with_bottom_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with bottom image")
    }

    @Test
    fun card_with_centered_content_and_paddings() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with centered content and paddings")
    }

    @Test
    fun card_with_image_overlay() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(imageFull = true) {
            }
        }
        val expectedClasses = "card card-image-full"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with image overlay")
    }

    @Test
    fun card_with_no_image() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with no image")
    }

    @Test
    fun card_with_custom_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with custom color")
    }

    @Test
    fun centered_card_with_neutral_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Centered card with neutral color")
    }

    @Test
    fun card_with_action_on_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard() {
            }
        }
        val expectedClasses = "card"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with action on top")
    }

    @Test
    fun card_with_image_on_side() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(side = true) {
            }
        }
        val expectedClasses = "card card-side"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Card with image on side")
    }

    @Test
    fun responsive_card_vertical_on_small_screen_horizontal_on_large_screen() {
        val html = createHTML(prettyPrint = false).div {
            daisyCard(side = true) {
            }
        }
        val expectedClasses = "card card-side"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive card (vertical on small screen, horizontal on large screen)")
    }
}
