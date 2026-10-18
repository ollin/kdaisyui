package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ModalTest {

    @Test
    fun dialog_modal_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal")
    }

    @Test
    fun dialog_modal() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal")
    }

    @Test
    fun dialog_modal_daisyModalBox() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox() {
            }
        }
        val expectedClasses = "modal-box"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal")
    }

    @Test
    fun dialog_modal_daisyModalAction() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction() {
            }
        }
        val expectedClasses = "modal-action"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal")
    }

    @Test
    fun dialog_modal_closes_when_clicked_outside_daisyModalBackdrop() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdrop() {
            }
        }
        val expectedClasses = "modal-backdrop"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal, closes when clicked outside")
    }

    @Test
    fun dialog_modal_with_a_close_button_at_corner_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, shape = ButtonShape.Circle, style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-circle btn-ghost btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal with a close button at corner")
    }

    @Test
    fun responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(verticalPlacement = ModalVerticalPlacement.Bottom, extraClasses = "sm:modal-middle") {
            }
        }
        val expectedClasses = "modal modal-bottom sm:modal-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive")
    }

    @Test
    fun modal_using_checkbox_daisyModalToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle() {
            }
        }
        val expectedClasses = "modal-toggle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Modal using checkbox")
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
