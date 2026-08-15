package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupCodeCoverageTest {

    @Test
    fun mockupCode_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupCode(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-code", actualClasses, "MockupCode defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-code zz-extra", actualClasses, "MockupCode all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupCode id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupCode attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupCode content")
    }
}
