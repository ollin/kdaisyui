package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ValidatorCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun validator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator()
        }
        assertRendered(html, Expected("Validator defaults", "validator"))
    }

    @Test
    fun validator_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        val expected = Expected("Validator all flags", "validator zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun validatorClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorClasses()
        }
        assertRendered(html, Expected("ValidatorClasses defaults", "validator"))
    }

    @Test
    fun validatorClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorClasses()
        }
        val expected = Expected("ValidatorClasses all flags", "validator", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun validatorHint_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint(
                content = { },
            )
        }
        assertRendered(html, Expected("ValidatorHint defaults", "validator-hint", closes = "</p></div>"))
    }

    @Test
    fun validatorHint_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ValidatorHint all flags", "validator-hint zz-extra", closes = "</p></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun validatorHintClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHintClasses()
        }
        assertRendered(html, Expected("ValidatorHintClasses defaults", "validator-hint"))
    }

    @Test
    fun validatorHintClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHintClasses()
        }
        val expected = Expected("ValidatorHintClasses all flags", "validator-hint", content = false)
        assertRendered(html, expected)
    }
}
