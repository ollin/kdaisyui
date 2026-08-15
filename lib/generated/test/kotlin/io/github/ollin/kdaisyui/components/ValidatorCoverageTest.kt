package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ValidatorCoverageTest {

    @Test
    fun validator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("validator", actualClasses, "Validator defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("validator zz-extra", actualClasses, "Validator all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Validator id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Validator attrs")
    }

    @Test
    fun validatorHint_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("validator-hint", actualClasses, "ValidatorHint defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("validator-hint zz-extra", actualClasses, "ValidatorHint all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ValidatorHint id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ValidatorHint attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ValidatorHint content")
    }
}
