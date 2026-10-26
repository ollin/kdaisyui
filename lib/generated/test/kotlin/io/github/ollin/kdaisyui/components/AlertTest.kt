package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AlertTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun alert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert() {
            }
        }
        assertDocumented(html, "div", "alert", "Alert")
    }

    @Test
    fun info_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info) {
            }
        }
        assertDocumented(html, "div", "alert alert-info", "Info color")
    }

    @Test
    fun success_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success) {
            }
        }
        assertDocumented(html, "div", "alert alert-success", "Success color")
    }

    @Test
    fun warning_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning) {
            }
        }
        assertDocumented(html, "div", "alert alert-warning", "Warning color")
    }

    @Test
    fun error_color() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error) {
            }
        }
        assertDocumented(html, "div", "alert alert-error", "Error color")
    }

    @Test
    fun alert_soft_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, soft = true) {
            }
        }
        assertDocumented(html, "div", "alert alert-info alert-soft", "Alert soft style")
    }

    @Test
    fun alert_soft_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, soft = true) {
            }
        }
        assertDocumented(html, "div", "alert alert-soft alert-success", "Alert soft style")
    }

    @Test
    fun alert_soft_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, soft = true) {
            }
        }
        assertDocumented(html, "div", "alert alert-soft alert-warning", "Alert soft style")
    }

    @Test
    fun alert_soft_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, soft = true) {
            }
        }
        assertDocumented(html, "div", "alert alert-error alert-soft", "Alert soft style")
    }

    @Test
    fun alert_outline_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, style = AlertStyle.Outline) {
            }
        }
        assertDocumented(html, "div", "alert alert-info alert-outline", "Alert outline style")
    }

    @Test
    fun alert_outline_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, style = AlertStyle.Outline) {
            }
        }
        assertDocumented(html, "div", "alert alert-outline alert-success", "Alert outline style")
    }

    @Test
    fun alert_outline_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, style = AlertStyle.Outline) {
            }
        }
        assertDocumented(html, "div", "alert alert-outline alert-warning", "Alert outline style")
    }

    @Test
    fun alert_outline_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, style = AlertStyle.Outline) {
            }
        }
        assertDocumented(html, "div", "alert alert-error alert-outline", "Alert outline style")
    }

    @Test
    fun alert_dash_style() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Info, style = AlertStyle.Dash) {
            }
        }
        assertDocumented(html, "div", "alert alert-dash alert-info", "Alert dash style")
    }

    @Test
    fun alert_dash_style_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Success, style = AlertStyle.Dash) {
            }
        }
        assertDocumented(html, "div", "alert alert-dash alert-success", "Alert dash style")
    }

    @Test
    fun alert_dash_style_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Warning, style = AlertStyle.Dash) {
            }
        }
        assertDocumented(html, "div", "alert alert-dash alert-warning", "Alert dash style")
    }

    @Test
    fun alert_dash_style_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(color = AlertColor.Error, style = AlertStyle.Dash) {
            }
        }
        assertDocumented(html, "div", "alert alert-dash alert-error", "Alert dash style")
    }

    @Test
    fun alert_with_buttons_responsive() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(direction = AlertDirection.Vertical, extraClasses = "sm:alert-horizontal") {
            }
        }
        assertDocumented(html, "div", "alert alert-vertical sm:alert-horizontal", "Alert with buttons + responsive")
    }

    @Test
    fun alert_with_buttons_responsive_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm) {
            }
        }
        assertDocumented(html, "button", "btn btn-sm", "Alert with buttons + responsive")
    }

    @Test
    fun alert_with_buttons_responsive_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(size = ButtonSize.Sm, color = ButtonColor.Primary) {
            }
        }
        assertDocumented(html, "button", "btn btn-primary btn-sm", "Alert with buttons + responsive")
    }
}
