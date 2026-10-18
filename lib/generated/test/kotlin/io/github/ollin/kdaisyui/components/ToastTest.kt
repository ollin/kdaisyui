package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ToastTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun toast_with_alert_inside() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast() {
            }
        }
        assertDocumented(html, "div", "toast", "toast with alert inside")
    }

    @Test
    fun toast_with_alert_inside_daisyAlert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info) {
            }
        }
        assertDocumented(html, "div", "alert alert-info", "toast with alert inside")
    }

    @Test
    fun toast_top_toast_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(verticalPlacement = ToastVerticalPlacement.Top, horizontalPlacement = ToastHorizontalPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "toast toast-start toast-top", "toast-top toast-start")
    }

    @Test
    fun toast_top_toast_start_daisyAlert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success) {
            }
        }
        assertDocumented(html, "div", "alert alert-success", "toast-top toast-start")
    }

    @Test
    fun toast_top_toast_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(verticalPlacement = ToastVerticalPlacement.Top, horizontalPlacement = ToastHorizontalPlacement.Center) {
            }
        }
        assertDocumented(html, "div", "toast toast-center toast-top", "toast-top toast-center")
    }

    @Test
    fun toast_top_toast_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(verticalPlacement = ToastVerticalPlacement.Top, horizontalPlacement = ToastHorizontalPlacement.End) {
            }
        }
        assertDocumented(html, "div", "toast toast-end toast-top", "toast-top toast-end")
    }

    @Test
    fun toast_start_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.Start, verticalPlacement = ToastVerticalPlacement.Middle) {
            }
        }
        assertDocumented(html, "div", "toast toast-middle toast-start", "toast-start toast-middle")
    }

    @Test
    fun toast_center_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.Center, verticalPlacement = ToastVerticalPlacement.Middle) {
            }
        }
        assertDocumented(html, "div", "toast toast-center toast-middle", "toast-center toast-middle")
    }

    @Test
    fun toast_end_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.End, verticalPlacement = ToastVerticalPlacement.Middle) {
            }
        }
        assertDocumented(html, "div", "toast toast-end toast-middle", "toast-end toast-middle")
    }

    @Test
    fun toast_start_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.Start) {
            }
        }
        assertDocumented(html, "div", "toast toast-start", "toast-start toast-bottom (default)")
    }

    @Test
    fun toast_center_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.Center) {
            }
        }
        assertDocumented(html, "div", "toast toast-center", "toast-center toast-bottom (default)")
    }

    @Test
    fun toast_end_default_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(horizontalPlacement = ToastHorizontalPlacement.End) {
            }
        }
        assertDocumented(html, "div", "toast toast-end", "toast-end (default) toast-bottom (default)")
    }
}
