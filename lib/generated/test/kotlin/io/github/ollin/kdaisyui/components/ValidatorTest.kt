package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.html.form
import kotlinx.html.label
import kotlinx.html.select

class ValidatorTest {

    @Test
    fun validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyInputClasses() })
        }
        val expectedClasses = "input validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Validator")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Validator")
    }

    @Test
    fun validator_and_validator_hint_daisyValidatorHint() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyValidatorHintClasses() }
        }
        val expectedClasses = "validator-hint"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Validator and validator-hint")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Validator and validator-hint")
    }

    @Test
    fun checkbox_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyCheckboxClasses() })
        }
        val expectedClasses = "checkbox validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Checkbox requirement validator")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Checkbox requirement validator")
    }

    @Test
    fun toggle_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(attrs = { daisyToggleClasses() })
        }
        val expectedClasses = "toggle validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle requirement validator")
        assertEquals("input", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Toggle requirement validator")
    }

    @Test
    fun select_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            select { daisyValidatorClasses(); daisySelectClasses() }
        }
        val expectedClasses = "select validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Select requirement validator")
        assertEquals("select", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Select requirement validator")
    }

    @Test
    fun select_requirement_validator_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton() {
            }
        }
        val expectedClasses = "btn"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Select requirement validator")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Select requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            form { daisyFieldsetClasses() }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
        assertEquals("form", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            label { daisyLabelClasses() }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
        assertEquals("label", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyButton() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(color = ButtonColor.Neutral) {
            }
        }
        val expectedClasses = "btn btn-neutral"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyButton_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyButton(style = ButtonStyle.Ghost) {
            }
        }
        val expectedClasses = "btn btn-ghost"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
        assertEquals("button", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Form requirement validator")
    }
}
