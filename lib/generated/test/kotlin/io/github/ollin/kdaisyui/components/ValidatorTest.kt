package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidatorTest {

    @Test
    fun validator_and_validator_hint_daisyValidatorHint() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint() {
            }
        }
        val expectedClasses = "validator-hint"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Validator and validator-hint")
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
    }

    @Test
    fun form_requirement_validator_daisyFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset() {
            }
        }
        val expectedClasses = "fieldset"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
    }

    @Test
    fun form_requirement_validator_daisyLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel() {
            }
        }
        val expectedClasses = "label"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
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
    }
}
