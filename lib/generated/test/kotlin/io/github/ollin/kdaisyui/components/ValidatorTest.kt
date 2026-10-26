package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.select

class ValidatorTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyInputClasses() })
        }
        assertDocumented(html, "input", "input validator", "Validator")
    }

    @Test
    fun validator_and_validator_hint_daisyValidatorHint() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyValidatorHintClasses() }
        }
        assertDocumented(html, "div", "validator-hint", "Validator and validator-hint")
    }

    @Test
    fun checkbox_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyCheckboxClasses() })
        }
        assertDocumented(html, "input", "checkbox validator", "Checkbox requirement validator")
    }

    @Test
    fun toggle_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyToggleClasses() })
        }
        assertDocumented(html, "input", "toggle validator", "Toggle requirement validator")
    }

    @Test
    fun select_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            select { daisyValidatorClasses(); daisySelectClasses() }
        }
        assertDocumented(html, "select", "select validator", "Select requirement validator")
    }

    @Test
    fun select_requirement_validator_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        assertDocumented(html, "button", "btn", "Select requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            form { daisyFieldsetClasses() }
        }
        assertDocumented(html, "form", "fieldset", "Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyLabelClasses() }
        }
        assertDocumented(html, "label", "label", "Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        assertDocumented(html, "button", "btn btn-neutral", "Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        assertDocumented(html, "button", "btn btn-ghost", "Form requirement validator")
    }
}
