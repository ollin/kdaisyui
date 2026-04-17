package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AlertTest {

    @Test
    fun alert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        val expectedClasses = "alert"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        val expectedClasses = "alert"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        val expectedClasses = "alert"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        val expectedClasses = "alert"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        val expectedClasses = "alert"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun alert_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(soft = true) {
            }
        }
        val expectedClasses = "alert alert-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert soft style")
    }

    @Test
    fun alert_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(outline = true) {
            }
        }
        val expectedClasses = "alert alert-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert outline style")
    }

    @Test
    fun alert_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(dash = true) {
            }
        }
        val expectedClasses = "alert alert-dash"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert dash style")
    }

    @Test
    fun alert_with_buttons_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(vertical = true, horizontal = true) {
            }
        }
        val expectedClasses = "alert alert-horizontal alert-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert with buttons + responsive")
    }

    @Test
    fun alert_with_title_and_description() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(vertical = true, horizontal = true) {
            }
        }
        val expectedClasses = "alert alert-horizontal alert-vertical"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert with title and description")
    }
}
