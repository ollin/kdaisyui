package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.span

class FabTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun fab_and_speed_dial_vertical() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab() {
            }
        }
        assertDocumented(html, "div", "fab", "FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_vertical_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Primary) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-lg btn-primary", "FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_vertical_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg, shape = ButtonShape.Circle) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-lg", "FAB and Speed Dial (vertical)")
    }

    @Test
    fun fab_and_speed_dial_with_svg_icons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Secondary) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-lg btn-secondary", "FAB and Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_speed_dial_with_labels_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Success) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-lg btn-success", "FAB and Speed Dial with labels")
    }

    @Test
    fun fab_and_speed_dial_with_rectangle_buttons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Lg) {
            }
        }
        assertDocumented(html, "button", "btn btn-lg", "FAB and Speed Dial with rectangle buttons")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(size = ButtonSize.Lg, shape = ButtonShape.Circle, color = ButtonColor.Info) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-info btn-lg", "FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyFabClose() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabClose() {
            }
        }
        assertDocumented(html, "div", "fab-close", "FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_close_button_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            span { daisyButtonClasses(shape = ButtonShape.Circle, size = ButtonSize.Lg, color = ButtonColor.Error) }
        }
        assertDocumented(html, "span", "btn btn-circle btn-error btn-lg", "FAB and Speed Dial with labels and fab-close button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_main_action_button_daisyFabMainAction() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyFabMainActionClasses() }
        }
        assertDocumented(html, "div", "fab-main-action", "FAB and Speed Dial with labels and fab-main-action Button")
    }

    @Test
    fun fab_and_speed_dial_with_labels_and_fab_main_action_button_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(shape = ButtonShape.Circle, color = ButtonColor.Secondary, size = ButtonSize.Lg) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-lg btn-secondary", "FAB and Speed Dial with labels and fab-main-action Button")
    }

    @Test
    fun fab_flower_and_speed_dial() {
        val html = createHTML(prettyPrint = false).div {
            daisyFab(flower = true) {
            }
        }
        assertDocumented(html, "div", "fab fab-flower", "FAB Flower and Speed Dial")
    }

    @Test
    fun fab_flower_and_speed_dial_daisyFabMainAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(attrs = { daisyButtonClasses(shape = ButtonShape.Circle, size = ButtonSize.Lg) }) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-lg fab-main-action", "FAB Flower and Speed Dial")
    }

    @Test
    fun fab_and_flower_speed_dial_with_svg_icons_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(shape = ButtonShape.Circle, size = ButtonSize.Lg) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-lg", "FAB and Flower Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_flower_speed_dial_with_svg_icons_daisyFabMainAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(attrs = { daisyButtonClasses(shape = ButtonShape.Circle, size = ButtonSize.Lg, color = ButtonColor.Primary) }) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-lg btn-primary fab-main-action", "FAB and Flower Speed Dial with SVG icons")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyButtonClasses(size = ButtonSize.Lg, color = ButtonColor.Info, shape = ButtonShape.Circle) }
        }
        assertDocumented(html, "div", "btn btn-circle btn-info btn-lg", "FAB and Flower Speed Dial with tooltip")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyFabMainAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyFabMainAction(attrs = { daisyButtonClasses(shape = ButtonShape.Circle, size = ButtonSize.Lg, color = ButtonColor.Success) }) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-lg btn-success fab-main-action", "FAB and Flower Speed Dial with tooltip")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyTooltip() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip(side = TooltipSide.Left) {
            }
        }
        assertDocumented(html, "div", "tooltip tooltip-left", "FAB and Flower Speed Dial with tooltip")
    }

    @Test
    fun fab_and_flower_speed_dial_with_tooltip_daisyTooltip_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyTooltip() {
            }
        }
        assertDocumented(html, "div", "tooltip", "FAB and Flower Speed Dial with tooltip")
    }
}
