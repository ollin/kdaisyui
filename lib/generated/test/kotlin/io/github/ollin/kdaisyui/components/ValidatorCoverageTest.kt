package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ValidatorCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun validator_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidator()
        }
        assertRendered(html, "validator", "Validator defaults")
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
        assertRendered(html, "validator zz-extra", "Validator all flags")
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
        assertRendered(html, "validator-hint", "ValidatorHint defaults", closes = "</div></div>")
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
        assertRendered(html, "validator-hint zz-extra", "ValidatorHint all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "ValidatorHint id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ValidatorHint attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ValidatorHint content")
    }
}
