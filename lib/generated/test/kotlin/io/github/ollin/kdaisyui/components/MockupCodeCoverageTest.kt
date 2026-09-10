package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupCodeCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun mockupCode_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode(
                content = { },
            )
        }
        assertRendered(html, "mockup-code", "MockupCode defaults", closes = "</div></div>")
    }

    @Test
    fun mockupCode_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "mockup-code zz-extra", "MockupCode all flags", closes = "</div></div>")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupCode id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupCode attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupCode content")
    }
}
