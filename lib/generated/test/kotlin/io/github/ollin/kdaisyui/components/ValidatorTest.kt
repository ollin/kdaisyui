package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidatorTest {

    @Test
    fun validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Validator")
    }

    @Test
    fun validator_and_validator_hint() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Validator and validator-hint")
    }

    @Test
    fun password_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Password requirement validator")
    }

    @Test
    fun username_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Username requirement validator")
    }

    @Test
    fun phone_number_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Phone Number requirement validator")
    }

    @Test
    fun url_input_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for URL input requirement validator")
    }

    @Test
    fun date_input_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Date input requirement validator")
    }

    @Test
    fun number_input_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Number input requirement validator")
    }

    @Test
    fun checkbox_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Checkbox requirement validator")
    }

    @Test
    fun toggle_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Toggle requirement validator")
    }

    @Test
    fun select_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Select requirement validator")
    }

    @Test
    fun form_requirement_validator() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator() {
            }
        }
        val expectedClasses = "validator"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Form requirement validator")
    }
}
