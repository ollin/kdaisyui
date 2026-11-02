package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ValidatorCoverageTest {

    /** What a case must render: its classes, sorted, and the tail it closes with. */
    private class Expected(val label: String, val classes: String, val closes: String = "")

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val BLOCK: Tag.() -> Unit = { attributes["data-block"] = "yes" }

    /** Renders one call and asserts the classes it writes. */
    private fun assertRenders(expected: Expected, render: FlowContent.() -> Unit) {
        assertRendered(createHTML(prettyPrint = false).div { render() }, expected)
    }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-block=\"yes\""), "${expected.label} block")
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
        val html = allFlags(Expected("Validator all flags", "validator zz-extra")) { daisyValidator(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun validatorClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorClasses()
        }
        assertRendered(html, Expected("ValidatorClasses defaults", "validator"))
    }

    @Test
    fun validatorClasses_all_flags() = assertRenders(Expected("ValidatorClasses all flags", "validator")) { daisyValidatorClasses() }

    @Test
    fun validatorHint_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHint()
        }
        assertRendered(html, Expected("ValidatorHint defaults", "validator-hint", closes = "</p></div>"))
    }

    @Test
    fun validatorHint_all_flags() {
        val html = allFlags(Expected("ValidatorHint all flags", "validator-hint zz-extra", closes = "</p></div>")) { daisyValidatorHint(id = ID, extraClasses = EXTRA, block = BLOCK) }
    }

    @Test
    fun validatorHintClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyValidatorHintClasses()
        }
        assertRendered(html, Expected("ValidatorHintClasses defaults", "validator-hint"))
    }

    @Test
    fun validatorHintClasses_all_flags() = assertRenders(Expected("ValidatorHintClasses all flags", "validator-hint")) { daisyValidatorHintClasses() }
}
