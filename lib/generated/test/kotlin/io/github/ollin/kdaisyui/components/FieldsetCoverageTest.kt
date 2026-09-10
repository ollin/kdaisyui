package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FieldsetCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun fieldset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset(
                content = { },
            )
        }
        assertRendered(html, "fieldset", "Fieldset defaults", closes = "</fieldset></div>")
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
        assertRendered(html, "fieldset zz-extra", "Fieldset all flags", closes = "</fieldset></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "Fieldset id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Fieldset attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Fieldset content")
    }

    @Test
    fun fieldsetLegend_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetLegend(
                content = { },
            )
        }
        assertRendered(html, "fieldset-legend", "FieldsetLegend defaults", closes = "</div></div>")
    }

    @Test
    fun fieldsetLegend_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldsetLegend(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "fieldset-legend zz-extra", "FieldsetLegend all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "FieldsetLegend id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FieldsetLegend attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FieldsetLegend content")
    }
}
