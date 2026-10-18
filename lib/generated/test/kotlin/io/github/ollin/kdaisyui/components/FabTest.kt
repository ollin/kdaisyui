package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FabTest {

    @Test
    fun fab_and_speed_dial_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        val expectedClasses = "fab"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_vertical_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg btn-primary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_vertical_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_with_svg_icons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Secondary) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_speed_dial_with_labels_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Success) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg btn-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels")
    }

    @Test
    fun fab_and_speed_dial_with_rectangle_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        val expectedClasses = "btn btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with rectangle buttons")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Info) {
            }
        }
        val expectedClasses = "btn btn-circle btn-info btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyFabClose() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose() {
            }
        }
        val expectedClasses = "fab-close"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle, size = ButtonSize.Lg, color = ButtonColor.Error) {
            }
        }
        val expectedClasses = "btn btn-circle btn-error btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_main_action_button_daisyFabMainAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction() {
            }
        }
        val expectedClasses = "fab-main-action"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-main-action Button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_main_action_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle, color = ButtonColor.Secondary, size = ButtonSize.Lg) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg btn-secondary"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Speed Dial with labels and fab-main-action Button")
    }

    @Test
    fun fab_flower_and_speed_dial() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(flower = true) {
            }
        }
        val expectedClasses = "fab fab-flower"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB Flower and Speed Dial")
    }

    @Test
    fun fab_and_flower_speed_dial_with_svg_icons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle, size = ButtonSize.Lg) {
            }
        }
        val expectedClasses = "btn btn-circle btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, color = ButtonColor.Info, shape = ButtonShape.Circle) {
            }
        }
        val expectedClasses = "btn btn-circle btn-info btn-lg"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with tooltip")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(side = TooltipSide.Left) {
            }
        }
        val expectedClasses = "tooltip tooltip-left"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with tooltip")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyTooltip_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        val expectedClasses = "tooltip"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for FAB and Flower Speed Dial with tooltip")
    }
}
