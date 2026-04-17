package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ModalTest {

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
    fun dialog_modal_closes_when_clicked_outside() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal, closes when clicked outside")
    }

    @Test
    fun dialog_modal_with_a_close_button_at_corner() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal with a close button at corner")
    }

    @Test
    fun dialog_modal_with_custom_width() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Dialog modal with custom width")
    }

    @Test
    fun responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(bottom = true, middle = true) {
            }
        }
        val expectedClasses = "modal modal-bottom modal-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Responsive")
    }

    @Test
    fun modal_using_checkbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Modal using checkbox")
    }

    @Test
    fun modal_that_closes_when_clicked_outside() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Modal that closes when clicked outside")
    }

    @Test
    fun modal_using_anchor_link() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal() {
            }
        }
        val expectedClasses = "modal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Modal using anchor link")
    }
}
