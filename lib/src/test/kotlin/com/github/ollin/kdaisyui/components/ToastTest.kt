package com.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ToastTest {

    @Test
    fun toast_with_alert_inside() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast() {
            }
        }
        val expectedClasses = "toast"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast with alert inside")
    }

    @Test
    fun toast_top_toast_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(top = true, start = true) {
            }
        }
        val expectedClasses = "toast toast-start toast-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-top toast-start")
    }

    @Test
    fun toast_top_toast_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(top = true, center = true) {
            }
        }
        val expectedClasses = "toast toast-center toast-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-top toast-center")
    }

    @Test
    fun toast_top_toast_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(top = true, end = true) {
            }
        }
        val expectedClasses = "toast toast-end toast-top"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-top toast-end")
    }

    @Test
    fun toast_start_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(start = true, middle = true) {
            }
        }
        val expectedClasses = "toast toast-middle toast-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-start toast-middle")
    }

    @Test
    fun toast_center_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(center = true, middle = true) {
            }
        }
        val expectedClasses = "toast toast-center toast-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-center toast-middle")
    }

    @Test
    fun toast_end_toast_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(end = true, middle = true) {
            }
        }
        val expectedClasses = "toast toast-end toast-middle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-end toast-middle")
    }

    @Test
    fun toast_start_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(start = true) {
            }
        }
        val expectedClasses = "toast toast-start"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-start toast-bottom (default)")
    }

    @Test
    fun toast_center_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(center = true) {
            }
        }
        val expectedClasses = "toast toast-center"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-center toast-bottom (default)")
    }

    @Test
    fun toast_end_default_toast_bottom_default() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(end = true) {
            }
        }
        val expectedClasses = "toast toast-end"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for toast-end (default) toast-bottom (default)")
    }
}
