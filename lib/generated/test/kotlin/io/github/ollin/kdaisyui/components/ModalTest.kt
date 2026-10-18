package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.form

class ModalTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun dialog_modal_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Dialog modal")
    }

    @Test
    fun dialog_modal() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        assertDocumented(html, "dialog", "modal", "Dialog modal")
    }

    @Test
    fun dialog_modal_daisyModalBox() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox() {
            }
        }
        assertDocumented(html, "div", "modal-box", "Dialog modal")
    }

    @Test
    fun dialog_modal_daisyModalAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction() {
            }
        }
        assertDocumented(html, "div", "modal-action", "Dialog modal")
    }

    @Test
    fun dialog_modal_closes_when_clicked_outside_daisyModalBackdrop() {
        val html = createHTML(prettyPrint = false).div {
            form { daisyModalBackdropClasses() }
        }
        assertDocumented(html, "form", "modal-backdrop", "Dialog modal, closes when clicked outside")
    }

    @Test
    fun dialog_modal_with_a_close_button_at_corner_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, shape = ButtonShape.Circle, style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-circle btn-ghost btn-sm", "Dialog modal with a close button at corner")
    }

    @Test
    fun responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(verticalPlacement = ModalVerticalPlacement.Bottom, extraClasses = "sm:modal-middle") {
            }
        }
        assertDocumented(html, "dialog", "modal modal-bottom sm:modal-middle", "Responsive")
    }

    @Test
    fun popover_modal_daisyModalPopover() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover() {
            }
        }
        assertDocumented(html, "div", "modal", "Popover modal")
    }

    @Test
    fun modal_using_checkbox_daisyModalToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle()
        }
        assertDocumented(html, "input", "modal-toggle", "Modal using checkbox")
    }

    @Test
    fun custom_part_popover_renders_div() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover {
            }
        }
        assertTrue(html.contains("<div"))
        assertTrue(html.contains("class=\"modal"))
        assertTrue(html.contains("popover=\"\""))
    }
}
