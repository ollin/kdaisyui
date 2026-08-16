package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class FieldsetCoverageTest {

    @Test
    fun fieldset_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fieldset", actualClasses, "Fieldset defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fieldset zz-extra", actualClasses, "Fieldset all flags")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fieldset-legend", actualClasses, "FieldsetLegend defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("fieldset-legend zz-extra", actualClasses, "FieldsetLegend all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "FieldsetLegend id")
        assertTrue(html.contains("data-attrs=\"yes\""), "FieldsetLegend attrs")
        assertTrue(html.contains("data-content=\"yes\""), "FieldsetLegend content")
    }
}
