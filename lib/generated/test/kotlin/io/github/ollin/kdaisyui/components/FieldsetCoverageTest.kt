package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.fieldSet
import kotlinx.html.stream.createHTML

class FieldsetCoverageTest {

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
    fun fieldset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset(
                content = { },
            )
        }
        assertRendered(html, Expected("Fieldset defaults", "fieldset", closes = "</fieldset></div>"))
    }

    @Test
    fun fieldset_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Fieldset all flags", "fieldset zz-extra", closes = "</fieldset></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fieldsetClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetClasses()
        }
        assertRendered(html, Expected("FieldsetClasses defaults", "fieldset"))
    }

    @Test
    fun fieldsetClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetClasses()
        }
        val expected = Expected("FieldsetClasses all flags", "fieldset", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun fieldsetLegend_defaults() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend(
                content = { },
            )
        }
        assertRendered(html, Expected("FieldsetLegend defaults", "fieldset-legend", closes = "</legend></fieldset>"))
    }

    @Test
    fun fieldsetLegend_all_flags() {
        val html = createHTML(prettyPrint = false).fieldSet {
            daisyFieldsetLegend(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("FieldsetLegend all flags", "fieldset-legend zz-extra", closes = "</legend></fieldset>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun fieldsetLegendClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetLegendClasses()
        }
        assertRendered(html, Expected("FieldsetLegendClasses defaults", "fieldset-legend"))
    }

    @Test
    fun fieldsetLegendClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetLegendClasses()
        }
        val expected = Expected("FieldsetLegendClasses all flags", "fieldset-legend", content = false)
        assertRendered(html, expected)
    }
}
