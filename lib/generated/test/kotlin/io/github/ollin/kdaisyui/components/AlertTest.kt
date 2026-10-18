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
            daisyAlert(color = AlertColor.Info) {
            }
        }
        val expectedClasses = "alert alert-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success) {
            }
        }
        val expectedClasses = "alert alert-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning) {
            }
        }
        val expectedClasses = "alert alert-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error) {
            }
        }
        val expectedClasses = "alert alert-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Error color")
    }

    @Test
    fun alert_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, soft = true) {
            }
        }
        val expectedClasses = "alert alert-info alert-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert soft style")
    }

    @Test
    fun alert_soft_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, soft = true) {
            }
        }
        val expectedClasses = "alert alert-soft alert-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert soft style")
    }

    @Test
    fun alert_soft_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, soft = true) {
            }
        }
        val expectedClasses = "alert alert-soft alert-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert soft style")
    }

    @Test
    fun alert_soft_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, soft = true) {
            }
        }
        val expectedClasses = "alert alert-error alert-soft"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert soft style")
    }

    @Test
    fun alert_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, style = AlertStyle.Outline) {
            }
        }
        val expectedClasses = "alert alert-info alert-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert outline style")
    }

    @Test
    fun alert_outline_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, style = AlertStyle.Outline) {
            }
        }
        val expectedClasses = "alert alert-outline alert-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert outline style")
    }

    @Test
    fun alert_outline_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, style = AlertStyle.Outline) {
            }
        }
        val expectedClasses = "alert alert-outline alert-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert outline style")
    }

    @Test
    fun alert_outline_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, style = AlertStyle.Outline) {
            }
        }
        val expectedClasses = "alert alert-error alert-outline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert outline style")
    }

    @Test
    fun alert_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, style = AlertStyle.Dash) {
            }
        }
        val expectedClasses = "alert alert-dash alert-info"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert dash style")
    }

    @Test
    fun alert_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, style = AlertStyle.Dash) {
            }
        }
        val expectedClasses = "alert alert-dash alert-success"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert dash style")
    }

    @Test
    fun alert_dash_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, style = AlertStyle.Dash) {
            }
        }
        val expectedClasses = "alert alert-dash alert-warning"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert dash style")
    }

    @Test
    fun alert_dash_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, style = AlertStyle.Dash) {
            }
        }
        val expectedClasses = "alert alert-dash alert-error"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert dash style")
    }

    @Test
    fun alert_with_buttons_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(direction = AlertDirection.Vertical, extraClasses = "sm:alert-horizontal") {
            }
        }
        val expectedClasses = "alert alert-vertical sm:alert-horizontal"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert with buttons + responsive")
    }

    @Test
    fun alert_with_buttons_responsive_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        val expectedClasses = "btn btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert with buttons + responsive")
    }

    @Test
    fun alert_with_buttons_responsive_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, color = ButtonColor.Primary) {
            }
        }
        val expectedClasses = "btn btn-primary btn-sm"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Alert with buttons + responsive")
    }
}
